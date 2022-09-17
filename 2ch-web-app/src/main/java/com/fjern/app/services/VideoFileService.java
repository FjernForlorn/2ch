package com.fjern.app.services;

import com.fjern.app.persistence.entities.fileEntities.StreamBytesInfo;
import com.fjern.app.persistence.entities.fileEntities.VideoDao;
import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VideoFileService extends FileService<VideoDao> {

    void save(VideoDao videoDao);

    Optional<StreamBytesInfo> getStreamBytes(VideoMetadata videoMetadata, HttpRange httpRange);
}
