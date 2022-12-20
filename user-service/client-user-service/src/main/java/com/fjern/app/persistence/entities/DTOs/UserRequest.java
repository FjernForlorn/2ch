package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Request {

    private Long id;

    private String name;

    private String email;

    private String password;
}
