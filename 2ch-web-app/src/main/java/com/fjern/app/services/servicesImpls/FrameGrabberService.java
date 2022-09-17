package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.fileEntities.ImageDao;
import com.fjern.app.services.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import static com.fjern.app.utils.fileUtils.removeFileExt;

@Service
@RequiredArgsConstructor

public class FrameGrabberService {
    @Autowired
    private ImageFileService imageFileService;

    @Value("${data.previewPicture.folder}")
    private String PreviewImageFolder;

    public void generatePreviewPictures(Path filePath, Long id) throws IOException {
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(filePath.toString())) {
            Java2DFrameConverter converter = new Java2DFrameConverter();

            g.start();
            BufferedImage image;
            for (int i = 0; i < 50; i++) {
                image = converter.convert(g.grabKeyFrame());
                if (image != null) {
                    ImageDao imageDao = new ImageDao();
                    imageDao.setId(id);
                    imageDao.setBufferedImage(image);
                    imageDao.setName(removeFileExt(filePath.getFileName().toString()));
                    imageDao.setDirectory(PreviewImageFolder);
                    imageDao.setExtension("jpeg");
                    imageFileService.save(imageDao);

                    break;
                }
            }
            g.stop();
        }
    }
}
