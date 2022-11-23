package com.fjern.common.web.listeners;

import com.fjern.common.web.events.AfterResourceCreatedEvent;
import com.fjern.common.web.util.RestPreconditions;
import com.fjern.common.web.util.UriMapper;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Component
public class CreatedResourceDiscoverabilityListener implements ApplicationListener<AfterResourceCreatedEvent> {

    @Autowired
    private UriMapper uriMapper;

    public CreatedResourceDiscoverabilityListener() {super();
    }

    @Override
    public void onApplicationEvent(AfterResourceCreatedEvent event) {
        RestPreconditions.checkNotNull(event);
        final String idOfNewResource = event.getIdOfNewResource();
        addLinkHeaderOnResourceCreation(event.getUriBuilder(), event.getResponse(), idOfNewResource, event.getClazz() );

    }

    public void addLinkHeaderOnResourceCreation(final UriComponentsBuilder uriBuilder, HttpServletResponse response, String idOfNewResource, Class clazz) {
        final String path = calculatePathToNewResource(clazz);
        String locationHeader = uriBuilder.path(path).build().expand(idOfNewResource).encode().toUriString();
        response.addHeader(HttpHeaders.LOCATION, locationHeader);
    }

    private String calculatePathToNewResource(Class clazz) {
        String resourcePath = uriMapper.getUriBase(clazz);
        String path = "/" + resourcePath + "/{id}";
        return path;
    }
}
