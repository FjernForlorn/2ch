package com.fjern.app.common;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.app.persistence.entities.Role;
import com.google.common.collect.Sets;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class FixtureEntityFactory {

    public static Role createNewRole() {
       return createNewRole(randomAlphabetic(8));
    }

    public static Role createNewRole(String name) {
        return new Role(name, Sets.<Privilege> newHashSet());
    }
}
