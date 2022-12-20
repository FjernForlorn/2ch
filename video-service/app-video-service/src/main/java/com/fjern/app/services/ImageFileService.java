package com.fjern.app.services;

import com.fjern.app.persistence.entities.fileEntities.ImageDao;
import org.springframework.stereotype.Service;

@Service
public interface ImageFileService extends FileService<ImageDao> {

    void save(ImageDao imageDao);
}
