package com.fjern.app.web.controllers.hateoas;

import com.fjern.app.persistence.entities.DTOs.RoleDto;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.services.RoleService;
import com.fjern.app.web.util.UtilMappings;
import com.fjern.app.web.util.mappers.RoleDtoMapper;
import com.fjern.common.services.RawService;
import com.fjern.common.web.controllers.AbstractHateoasController;
import com.fjern.common.web.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UtilMappings.Hateoas.ROLES)
public class RoleHateoasController extends AbstractHateoasController<Role, RoleDto, RoleResource> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoMapper mapper;

    public RoleHateoasController() {
        super();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResource> getAll (HttpServletRequest request) {
        return findAllInternal(request);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResource findOne (@PathVariable Long id) {
        return findOneInternal(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResource create (@RequestBody @Valid Role role){
        return createInternal(role);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role update (@PathVariable Long id, @RequestBody Role role) {
        return updateInternal(id, role);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id) {
        deleteInternal(id);
    }

    @Override
    protected RawService<Role> getService() {
        return roleService;
    }

    @Override
    protected DtoMapper<Role, RoleDto> getMapper() {
        return mapper;
    }

    @Override
    protected RoleResource convertToRepresentation(RoleDto dto) {
       return new RoleResource(dto);
    }

}
