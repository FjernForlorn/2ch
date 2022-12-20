package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Response {

    private Long id;

    private String name;

    private String email;


}
