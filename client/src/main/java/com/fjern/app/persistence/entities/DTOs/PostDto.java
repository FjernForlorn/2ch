package com.fjern.app.persistence.entities.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PostDto {
    private Long id;
    private String text;
}
