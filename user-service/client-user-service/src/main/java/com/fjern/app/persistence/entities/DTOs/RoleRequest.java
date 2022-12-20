package com.fjern.app.persistence.entities.DTOs;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.common.interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest implements Request {

    private Long id;

    private String name;

    private List<Privilege> privileges;
}
