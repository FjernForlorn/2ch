package com.fjern.app.services;

import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;
@Service
public interface VideoPreviewImageFileService {
    Optional<InputStream> getImageInputStream(VideoMetadata videoMetadata);
}
