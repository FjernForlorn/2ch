package com.fjern.app.client.template;

import com.fjern.app.persistence.entities.Role;
import com.fjern.app.web.util.UtilNames;
import com.fjern.app.web.util.UtilPaths;
import com.fjern.test.common.client.template.AbstractRestClient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesSimpleApiClient extends AbstractRestClient<Role> {

    @Autowired
    private UtilPaths utilPaths;

    public RolesSimpleApiClient() {
        super(Role.class);
    }

    @Override
    protected Pair<String, String> getCredentials() {
        return new ImmutablePair<String, String>(UtilNames.ADMIN_USERNAME, UtilNames.ADMIN_PASS);
    }

    @Override
    public String getUri() {
        return utilPaths.getRoleUri();
    }
}
