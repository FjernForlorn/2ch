package com.fjern.common.web.controllers;

import com.fjern.common.interfaces.IEntity;
import com.fjern.common.web.events.AfterResourceCreatedEvent;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController<T extends IEntity> extends AbstractReadOnlyController<T> {

    public AbstractController(Class<T> classToSet) {
        super(classToSet);
    }

    protected final T createInternal(T resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final T createdResource = getService().create(resource);

        eventPublisher.publishEvent(new AfterResourceCreatedEvent<T>(clazz, createdResource.getId().toString(), response, uriBuilder));
        return createdResource;
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
}
