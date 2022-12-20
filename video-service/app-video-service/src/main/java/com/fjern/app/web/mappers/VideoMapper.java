package com.fjern.app.web.mappers;

import com.fjern.app.persistence.entities.DTOs.NewVideoDto;
import com.fjern.app.persistence.entities.DTOs.VideoMetadataDto;
import com.fjern.app.persistence.entities.fileEntities.VideoDao;
import com.fjern.app.persistence.entities.fileEntities.VideoMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {

    @Value("${api.video.address}")
    private String apiVideoAddress;

    public VideoMetadataDto videoMetadataToDto(VideoMetadata vmd) {
        VideoMetadataDto repr = new VideoMetadataDto();
        repr.setId(vmd.getId());
        repr.setPreviewUrl(apiVideoAddress + "/preview/" + vmd.getId());
        repr.setStreamUrl(apiVideoAddress + "/stream/" + vmd.getId());
        repr.setDescription(vmd.getDescription());
        repr.setContentType(vmd.getContentType());
        return repr;
    }

    public VideoDao newVideoDtoToVideoDao(NewVideoDto newVideoDto) {
        VideoDao videoDao = new VideoDao();
        videoDao.setFile(newVideoDto.getFile());
        videoDao.setName(newVideoDto.getDescription());
        return videoDao;
    }

    public VideoMetadata newVideoDtoToMetadata(NewVideoDto newVideoDto) {
        VideoMetadata videoMetadata = new VideoMetadata();
        videoMetadata.setName(newVideoDto.getFile().getOriginalFilename());
        videoMetadata.setContentType(newVideoDto.getFile().getContentType());
        videoMetadata.setFileSize(newVideoDto.getFile().getSize());
        videoMetadata.setDescription(newVideoDto.getDescription());
        return videoMetadata;
    }
}
