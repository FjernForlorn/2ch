package com.fjern.common.web.controllers;

import com.fjern.common.exception.MyResourceNotFoundException;
import com.fjern.common.interfaces.NameableDto;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.services.RawService;
import com.fjern.common.web.util.DtoMapper;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.hateoas.RepresentationModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


public abstract class AbstractHateoasController<T extends NameableEntity, D extends NameableDto, R extends RepresentationModel<R>> {

    protected final R findOneInternal(final Long id){
        T retrievedResource = getService().findById(id);
        RestPreconditions.checkNotNull(retrievedResource, "Resource is not found");

        return convertToRepresentation(getMapper().convertToDto(retrievedResource));
    }

    protected final List<R> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }

        return convertListToRepresentation(getMapper().convertListToDto(getService().findAll()));
    }

    protected final R createInternal(T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final T createdResource = getService().create(resource);

        return convertToRepresentation(getMapper().convertToDto(createdResource));
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

    protected abstract DtoMapper<T, D> getMapper();

    private final List<R> convertListToRepresentation(List<D> dList) {
        return dList.stream().map(this::convertToRepresentation).collect(Collectors.toList());
    }

    protected abstract R convertToRepresentation(final D entity);

}
