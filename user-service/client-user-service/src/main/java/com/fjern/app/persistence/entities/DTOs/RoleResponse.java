package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class RoleResponse implements Response {

    public RoleResponse() {
        super();
    }

    public RoleResponse(String name) {
        super();
        this.name = name;
    }

    public RoleResponse(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleResponse)) return false;
        RoleResponse roleResponse = (RoleResponse) o;
        return name.equals(roleResponse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
