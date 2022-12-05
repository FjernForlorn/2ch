package com.fjern.roles;

import com.fjern.app.client.template.RolesSimpleApiClient;
import com.fjern.app.persistence.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RoleSimpleLiveTest extends GenericSimpleLiveTest<Role> {

    @Autowired
    protected RolesSimpleApiClient rolesSimpleApiClient;

    @Override
    protected RolesSimpleApiClient getApi() {
        return this.rolesSimpleApiClient;
    }

    @Override
    protected Role createNewResource() {
        return new Role(randomAlphabetic(4));
    }
}
