package com.fjern.app.persistence.entities.DTOs;

import com.fjern.common.interfaces.NameableDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class RoleDto implements NameableDto {

    public RoleDto() {
        super();
    }

    public RoleDto(String name) {
        super();
        this.name = name;
    }

    public RoleDto(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDto)) return false;
        RoleDto roleDto = (RoleDto) o;
        return name.equals(roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
