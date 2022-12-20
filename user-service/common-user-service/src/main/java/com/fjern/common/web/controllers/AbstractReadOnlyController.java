package com.fjern.common.web.controllers;

import com.fjern.common.exception.MyResourceNotFoundException;
import com.fjern.common.interfaces.NameableEntity;
import com.fjern.common.interfaces.Request;
import com.fjern.common.interfaces.Response;
import com.fjern.common.services.RawService;
import com.fjern.common.web.events.ResourceRetrievedEvent;
import com.fjern.common.web.util.DtoMapper;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractReadOnlyController<T extends NameableEntity, D extends Response, E  extends Request> {

    protected Class<T> clazz;

    public AbstractReadOnlyController(Class<T> classToSet) {
        RestPreconditions.checkNotNull(classToSet);
        this.clazz=classToSet;
    }

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    protected final D findOneInternal(final Long id, HttpServletResponse response, UriComponentsBuilder uriBuilder){
        T retrievedResource = getService().findById(id);
        RestPreconditions.checkNotNull(retrievedResource, "Resource is not found");

        eventPublisher.publishEvent(new ResourceRetrievedEvent<T>(clazz, response, uriBuilder));
        return getMapper().convertToResponse(retrievedResource);
    }

    protected final List<D> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }
        return getMapper().convertListToResponse(getService().findAll());
    }

    protected abstract RawService<T> getService();

    protected abstract DtoMapper<T, D, E> getMapper();
}
