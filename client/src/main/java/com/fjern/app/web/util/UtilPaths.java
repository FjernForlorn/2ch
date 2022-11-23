package com.fjern.app.web.util;

import com.fjern.app.persistence.entities.Role;
import com.fjern.app.persistence.entities.User;
import com.fjern.common.web.client.CommonPaths;
import com.fjern.common.web.util.UriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilPaths {

    @Value("${http.sec.path}")
    private String secPath;

    @Autowired
    private CommonPaths commonPaths;

    @Autowired
    private UriMapper uriMapper;

    public final String getContext() {
        return commonPaths.getServerRoot()+secPath;
    }

    public final String getRootUri() {
        return getContext();
    }

    public final String getUserUri() {
        return getRootUri()+uriMapper.getUriBase(User.class);
    }

    public final String getRoleUri() {
        return getRootUri()+uriMapper.getUriBase(Role.class);
    }

    public final String getRoleHateoasUri() {
        return getRootUri()+ UtilMappings.Hateoas.ROLES;
    }
}
