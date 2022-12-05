package com.fjern.common.web.controllers;

import com.fjern.common.web.events.ResourceRetrievedEvent;
import com.fjern.common.web.util.RestPreconditions;
import com.fjern.common.services.RawService;
import com.fjern.common.exception.MyResourceNotFoundException;
import com.fjern.common.interfaces.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractReadOnlyController<T extends IEntity> {

    protected Class<T> clazz;

    public AbstractReadOnlyController(Class<T> classToSet) {
        RestPreconditions.checkNotNull(classToSet);
        this.clazz=classToSet;
    }

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    protected final T findOneInternal(final Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder){
        T retrievedResource = getService().findById(id);
        RestPreconditions.checkNotNull(retrievedResource, "Resource is not found");

        eventPublisher.publishEvent(new ResourceRetrievedEvent<T>(clazz, response, uriBuilder));
        return retrievedResource;
    }

    protected final List<T> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }

        return getService().findAll();
    }

    protected abstract RawService<T> getService();
}
