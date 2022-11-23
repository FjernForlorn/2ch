package com.fjern.app.client.ops;

import com.fjern.app.persistence.entities.Role;
import com.fjern.client.IDtoOps;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class RoleDtoOps implements IDtoOps<Role> {
    @Override
    public Role createNewResource() {
        return new Role(randomAlphabetic(4));
    }

    @Override
    public Role change(Role resource) {
        resource.setName(randomAlphabetic(4));
        return resource;
    }
}
