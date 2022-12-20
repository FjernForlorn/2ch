package com.fjern.common.web.listeners;

import com.fjern.common.util.LinkUtil;
import com.fjern.common.web.events.ResourceRetrievedEvent;
import com.fjern.common.web.util.RestPreconditions;
import com.fjern.common.web.util.UriMapper;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Component
public class ResourceRetrievedListener implements ApplicationListener<ResourceRetrievedEvent> {

    @Autowired
    private UriMapper uriMapper;

    @Override
    public void onApplicationEvent(ResourceRetrievedEvent event) {
        RestPreconditions.checkNotNull(event);
        generateAllResourcesLinkHeader(event.getClazz(), event.getResponse(), event.getUriBuilder());
    }

    private void generateAllResourcesLinkHeader(Class clazz, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
       String resourcePath = "/" + uriMapper.getUriBase(clazz);
       String uriForResources = uriBuilder.path(resourcePath).build().toUriString();
       String linkHeaderValue = LinkUtil.createLinkHeader(uriForResources, LinkUtil.REL_COLLECTION);
       response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }
}
