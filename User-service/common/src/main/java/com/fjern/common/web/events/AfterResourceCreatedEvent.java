package com.fjern.common.web.events;

import com.fjern.common.web.util.RestPreconditions;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public final class AfterResourceCreatedEvent<T extends Serializable> extends ApplicationEvent {

    public AfterResourceCreatedEvent(Class<T> clazz, String idOfNewResource, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        super(clazz);

        RestPreconditions.checkNotNull(idOfNewResource);
        RestPreconditions.checkNotNull(response);
        RestPreconditions.checkNotNull(uriBuilder);

        this.idOfNewResource = idOfNewResource;
        this.response = response;
        this.uriBuilder = uriBuilder;
    }

    private final String idOfNewResource;

    private final HttpServletResponse response;

    private final UriComponentsBuilder uriBuilder;


    public String getIdOfNewResource() {
        return idOfNewResource;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public UriComponentsBuilder getUriBuilder() {
        return uriBuilder;
    }

    @SuppressWarnings("unchecked")
    public final Class<T> getClazz() {
        return (Class<T>) getSource();
    }
}
