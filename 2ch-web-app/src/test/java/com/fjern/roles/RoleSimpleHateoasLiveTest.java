package com.fjern.roles;

import com.fjern.app.client.template.RolesHateoasSimpleApiClient;
import com.fjern.app.persistence.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RoleSimpleHateoasLiveTest extends GenericSimpleLiveTest<Role> {

    @Autowired
    protected RolesHateoasSimpleApiClient rolesSimpleApiClient;

    @Override
    protected RolesHateoasSimpleApiClient getApi() {
        return this.rolesSimpleApiClient;
    }

    @Override
    protected Role createNewResource() {
        return new Role(randomAlphabetic(4));
    }
}
