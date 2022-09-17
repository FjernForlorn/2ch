package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import com.fjern.app.persistence.repositories.VideoMetadataRepository;
import com.fjern.common.services.AbstractService;
import com.fjern.app.services.VideoMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoMetadataServiceImpl extends AbstractService<VideoMetadata> implements VideoMetadataService {

    public VideoMetadataServiceImpl(){
        super();
    }

    @Autowired
    private VideoMetadataRepository videoMetadataRepository;

    @Override
    protected VideoMetadataRepository getRepo() {
        return videoMetadataRepository;
    }


    @Override
    public VideoMetadata findByName(String name) {
        return getRepo().findByName(name);
    }
}
