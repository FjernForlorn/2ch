package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataDto implements IEntity {

    private Long id;

    private String contentType;

    private String description;

    private String previewUrl;

    private String streamUrl;

}
