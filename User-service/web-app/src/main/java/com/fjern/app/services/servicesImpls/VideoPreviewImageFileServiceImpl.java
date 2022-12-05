package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import com.fjern.app.services.VideoPreviewImageFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static com.fjern.app.utils.fileUtils.removeFileExt;

@Service
public class VideoPreviewImageFileServiceImpl implements VideoPreviewImageFileService {

    @Value("${data.previewPicture.folder}")
    private String directory;

    @Override
    public Optional<InputStream> getImageInputStream(VideoMetadata videoMetadata) {
        Path path = Path.of(directory, videoMetadata.getId().toString(), removeFileExt(videoMetadata.getName())+".jpeg");
        if(!Files.exists(path)){
            return Optional.empty();
        }
        try {
            return Optional.of(Files.newInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
