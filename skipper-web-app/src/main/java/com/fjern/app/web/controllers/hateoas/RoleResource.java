package com.fjern.app.web.controllers.hateoas;

import com.fjern.app.persistence.entities.DTOs.RoleDto;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class RoleResource extends RepresentationModel<RoleResource> {

    private final RoleDto role;

    public RoleResource(RoleDto role) {
        this.role = role;
        this.add(linkTo(RoleHateoasController.class).withRel("roles"));
        this.add(linkTo(methodOn(RoleHateoasController.class, role).findOne(role.getId())).withSelfRel());
    }

    public RoleDto getRole() {return role;}

}
