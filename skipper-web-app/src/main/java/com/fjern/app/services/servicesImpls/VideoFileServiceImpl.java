package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.fileEntities.StreamBytesInfo;
import com.fjern.app.persistence.entities.fileEntities.VideoDao;
import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import com.fjern.app.services.VideoFileService;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

@Service
@Slf4j
public class VideoFileServiceImpl implements VideoFileService {

    @Value("${data.video.folder}")
    private String videoDataFolder;

    @Value("${data.previewPicture.folder}")
    private String previewPicture;

    @Autowired
    private FrameGrabberService frameGrabberService;

    @Value("${data.chunkRate}")
    private long chunkRate;


    @Override
    public void save(VideoDao videoDao) {
        Long id = videoDao.getId();
        Path directory = Path.of(videoDataFolder, id.toString());
        try {
            Files.createDirectory(directory);
            Path file = Path.of(directory.toString(), videoDao.getFile().getOriginalFilename());
            try(OutputStream outputStream = Files.newOutputStream(file, CREATE, WRITE)) {
                videoDao.getFile().getInputStream().transferTo(outputStream);
            long videoLength = getLength(file);
            videoDao.setLength(videoLength);
            frameGrabberService.generatePreviewPictures(file, id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<StreamBytesInfo> getStreamBytes(VideoMetadata videoMetadata, HttpRange httpRange) {
        File file = Path.of(videoDataFolder, videoMetadata.getId().toString(), videoMetadata.getName()).toFile();
        if(!file.exists()) {
            log.error("File {} not found", file);
            return Optional.empty();
        }
        long fileSize = videoMetadata.getFileSize();
        if (httpRange == null) {
            return Optional.of(new StreamBytesInfo(outputStream ->
                    Files.newInputStream(Path.of(file.getPath())).transferTo(outputStream),
                    fileSize, 0, fileSize, videoMetadata.getContentType()));
        }
        long rangeStart = httpRange.getRangeStart(0);
        long chunkSize = fileSize / Math.round(fileSize / chunkRate);
        long rangeEnd = rangeStart + chunkSize;

        if (rangeEnd >= fileSize) {
            rangeEnd = fileSize - 1;
        }
        long finalRangeEnd = rangeEnd;
        return Optional.of(new StreamBytesInfo(
                outputStream -> {
                    try (InputStream inputStream = Files.newInputStream(Path.of(file.getPath()))) {
                        inputStream.skip(rangeStart);
                        byte[] bytes = inputStream.readNBytes((int) (finalRangeEnd - rangeStart) + 1);
                        outputStream.write(bytes);
                    }
                }, fileSize, rangeStart, rangeEnd, videoMetadata.getContentType()));
    }

    public long getLength(Path filePath) throws IOException {
        try(FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(filePath.toString())) {
            fFmpegFrameGrabber.start();
            long length = fFmpegFrameGrabber.getLengthInTime();
            fFmpegFrameGrabber.stop();
            return length;
        }
    }

}
