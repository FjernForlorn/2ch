package com.fjern.app.web.controllers;

import com.fjern.app.persistence.entities.DTOs.RoleRequest;
import com.fjern.app.persistence.entities.DTOs.RoleResponse;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.services.RoleService;
import com.fjern.app.web.util.UtilMappings;
import com.fjern.app.web.util.mappers.RoleDtoMapper;
import com.fjern.common.services.RawService;
import com.fjern.common.web.controllers.AbstractController;
import com.fjern.common.web.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(UtilMappings.Plural.ROLES)
public class RoleController extends AbstractController<Role, RoleResponse, RoleRequest> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoMapper roleDtoMapper;

    public RoleController() {
        super(Role.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getAll (HttpServletRequest request) {
        return findAllInternal(request);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse findOne (@PathVariable Long id, HttpServletResponse response
            , UriComponentsBuilder uriBuilder) {
        return findOneInternal(id, response, uriBuilder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse create (@RequestBody RoleRequest role
            , final UriComponentsBuilder uriBuilder, HttpServletResponse response){
        return createInternal(role, uriBuilder, response);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse update (@PathVariable Long id, @RequestBody RoleRequest role) {
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
    protected DtoMapper<Role, RoleResponse, RoleRequest> getMapper() {
        return roleDtoMapper;
    }
}
