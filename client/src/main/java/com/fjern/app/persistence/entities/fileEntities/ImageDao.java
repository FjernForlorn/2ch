package com.fjern.app.persistence.entities.fileEntities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class ImageDao extends FileDao{

    private Long id;

    private String name;

    private BufferedImage bufferedImage;

    private String directory;

    private String extension;
}
