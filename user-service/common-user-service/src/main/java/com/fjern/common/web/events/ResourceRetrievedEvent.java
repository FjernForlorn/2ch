package com.fjern.common.web.events;

import com.fjern.common.web.util.RestPreconditions;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;


public class ResourceRetrievedEvent<T extends Serializable> extends ApplicationEvent {
    public ResourceRetrievedEvent(Class<T> clazz, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        super(clazz);
        RestPreconditions.checkNotNull(response);
        RestPreconditions.checkNotNull(uriBuilder);

        this.response = response;
        this.uriBuilder = uriBuilder;
    }

    private HttpServletResponse response;

    private UriComponentsBuilder uriBuilder;

    public HttpServletResponse getResponse() {
        return response;
    }

    public UriComponentsBuilder getUriBuilder() {
        return uriBuilder;
    }

    public Class<T> getClazz() {
        return (Class<T>) getSource();
    }
}
