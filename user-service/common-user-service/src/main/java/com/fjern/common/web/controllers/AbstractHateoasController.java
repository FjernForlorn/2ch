package com.fjern.common.web.controllers;

import com.fjern.common.exception.MyResourceNotFoundException;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import com.fjern.common.services.RawService;
import com.fjern.common.web.util.DtoHateoasMapper;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.hateoas.RepresentationModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public abstract class AbstractHateoasController<T extends NameableEntity
        , E extends Response, D extends Request, R extends RepresentationModel<R>> {

    protected final R findOneInternal(final Long id){
        T retrievedResource = getService().findById(id);
        RestPreconditions.checkNotNull(retrievedResource, "Resource is not found");

        return getMapper().convertToRepresentation(retrievedResource);
    }

    protected final List<R> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }

        return getMapper().convertListToRepresentation(getService().findAll());
    }

    protected final R createInternal(T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final T createdResource = getService().create(resource);

        return getMapper().convertToRepresentation(createdResource);
    }

    protected final T updateInternal(final Long id, T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkIfBadRequest(resource.getId().equals(id),
                resource.getClass().getSimpleName()+ " id and URI id don't match");
        RestPreconditions.checkNotNull(getService().findById(resource.getId()));
        return getService().update(resource);
    }

    protected final void deleteInternal(final Long id) {
        T resource = getService().findById(id);
        RestPreconditions.checkNotNull(resource);
        getService().delete(id);
    }

    protected abstract RawService<T> getService();

    protected abstract DtoHateoasMapper<T, E, D, R> getMapper();



}
