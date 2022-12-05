package com.fjern.app.web.util.mappers;

import com.fjern.app.persistence.entities.DTOs.RoleDto;
import com.fjern.app.persistence.entities.Role;
import com.fjern.common.web.util.AbstractDtoMapper;
import com.fjern.common.web.util.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RoleDtoMapper extends AbstractDtoMapper<Role, RoleDto> implements DtoMapper<Role, RoleDto>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleDto convertToDto(Role entity) {
        return mapper.map(entity, RoleDto.class);
    }

    @Override
    public Role convertToEntity(RoleDto entity) {
        return mapper.map(entity, Role.class);
    }
}
