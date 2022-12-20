package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.Response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostResponse implements Response {

    private Long id;

    private String text;

    private String name;

    private UserResponse user;
}
