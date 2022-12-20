package com.fjern.app.web.controllers.hateoas;

import com.fjern.app.persistence.entities.DTOs.RoleResponse;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


public class RoleResource extends RepresentationModel<RoleResource>{
    private RoleResponse role;
    public RoleResource(RoleResponse role) {
        this.role = role;
        this.add(linkTo(RoleHateoasController.class).withRel("roles"));
        this.add(linkTo(WebMvcLinkBuilder.methodOn(RoleHateoasController.class, role).findOne(role.getId())).withSelfRel());
    }
}
