package com.fjern.app.web.util.mappers;

import com.fjern.app.persistence.entities.DTOs.RoleRequest;
import com.fjern.app.persistence.entities.DTOs.RoleResponse;
import com.fjern.app.persistence.entities.Role;
import com.fjern.common.web.util.AbstractDtoMapper;
import com.fjern.common.web.util.DtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RoleDtoMapper extends AbstractDtoMapper<Role, RoleResponse, RoleRequest>
        implements DtoMapper<Role, RoleResponse, RoleRequest>{

    @Autowired
    private ModelMapper mapper;

    @Override
    public RoleResponse convertToResponse(Role entity) {
        return mapper.map(entity, RoleResponse.class);
    }

    @Override
    public Role convertToEntity(RoleRequest entity) {
        return mapper.map(entity, Role.class);
    }
}
