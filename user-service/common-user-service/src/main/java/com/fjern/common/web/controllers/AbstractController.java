package com.fjern.common.web.controllers;

import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import com.fjern.common.web.events.AfterResourceCreatedEvent;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController<T extends NameableEntity, D extends Response, E extends Request>
        extends AbstractReadOnlyController<T, D, E> {

    public AbstractController(Class<T> classToSet) {
        super(classToSet);
    }

    protected final D createInternal(E resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null, "id must be null");
        final T createdResource = getService().create(getMapper().convertToEntity(resource));

        eventPublisher.publishEvent(new AfterResourceCreatedEvent<T>(clazz, createdResource.getId().toString(), response, uriBuilder));
        return getMapper().convertToResponse(createdResource);
    }
    protected final D updateInternal(final Long id, E resource) {
        return updateInternal(id, getMapper().convertToEntity(resource));
    }
    protected final D updateInternal(final Long id, T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkIfBadRequest(resource.getId().equals(id),
                resource.getClass().getSimpleName()+ " id and URI id don't match");
        RestPreconditions.checkNotNull(getService().findById(resource.getId()));
        return getMapper().convertToResponse(getService().update(resource));
    }

    protected final void deleteInternal(final Long id) {
        T resource = getService().findById(id);
        RestPreconditions.checkNotNull(resource);
        getService().delete(id);
    }
}
