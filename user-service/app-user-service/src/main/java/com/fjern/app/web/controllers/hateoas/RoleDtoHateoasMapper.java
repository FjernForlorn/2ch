package com.fjern.app.web.controllers.hateoas;

import com.fjern.app.persistence.entities.DTOs.RoleRequest;
import com.fjern.app.persistence.entities.DTOs.RoleResponse;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.web.util.mappers.RoleDtoMapper;
import com.fjern.common.web.util.DtoHateoasMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDtoHateoasMapper extends RoleDtoMapper
        implements DtoHateoasMapper<Role, RoleResponse, RoleRequest, RoleResource> {

    @Override
    public List<RoleResource> convertListToRepresentation(List<Role> dList) {
        return dList.stream().map(this::convertToRepresentation).collect(Collectors.toList());
    }

    @Override
    public RoleResource convertToRepresentation(Role entity) {
        return new RoleResource(convertToResponse(entity));
    }
}
