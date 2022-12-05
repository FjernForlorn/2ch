package com.fjern.app.services;


import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import com.fjern.common.services.RawService;
import org.springframework.stereotype.Service;

@Service
public interface VideoMetadataService extends RawService<VideoMetadata> {
}
