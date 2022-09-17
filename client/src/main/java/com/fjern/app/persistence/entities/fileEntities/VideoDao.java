package com.fjern.app.persistence.entities.fileEntities;

import com.fjern.common.interfaces.IEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Data
public class VideoDao implements IEntity {

    private Long id;

    private String name;

    private MultipartFile file;

    private Long length;
}
