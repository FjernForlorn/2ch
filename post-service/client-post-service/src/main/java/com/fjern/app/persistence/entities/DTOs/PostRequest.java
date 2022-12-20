package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.Request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostRequest implements Request {
    private Long id;

    private String text;

    private String name;

    private Long userId;
}
