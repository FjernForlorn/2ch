package com.fjern.app.persistence.entities.DTOs;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewVideoDto implements Serializable {

    private String description;

    private MultipartFile file;

}
