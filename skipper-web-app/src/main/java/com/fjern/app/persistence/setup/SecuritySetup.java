package com.fjern.app.persistence.setup;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.persistence.entities.User;
import com.fjern.app.services.PrivilegeService;
import com.fjern.app.services.RoleService;
import com.fjern.app.services.UserService;
import com.fjern.app.web.util.UtilNames;
import com.fjern.app.web.util.UtilNames.Privileges;
import com.fjern.app.web.util.UtilNames.Roles;
import com.fjern.common.web.util.RestPreconditions;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecuritySetup implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger log = LoggerFactory.getLogger(SecuritySetup.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    private boolean setupDone;

    public SecuritySetup() {
        super();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!setupDone) {
            log.info("Executing setup");

            createPrivileges();
            createRoles();
            createUsers();

            setupDone = true;
            log.info("Setup done");
        }

    }

    private void createPrivileges() {
        createPrivilegeIfNotExisting(Privileges.CAN_USER_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_USER_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_ROLE_WRITE);

        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGES_READ);
        createPrivilegeIfNotExisting(Privileges.CAN_PRIVILEGES_WRITE);
    }

    private void createPrivilegeIfNotExisting(String name) {
        Privilege existingResource = privilegeService.findByName(name);
        if (existingResource == null) {
            privilegeService.create(new Privilege(name));
        }
    }

    private void createRoles() {
        final Privilege canPrivilegeRead = privilegeService.findByName(Privileges.CAN_PRIVILEGES_READ);
        final Privilege canPrivilegeWrite = privilegeService.findByName(Privileges.CAN_PRIVILEGES_WRITE);

        final Privilege canRoleRead = privilegeService.findByName(Privileges.CAN_ROLE_READ);
        final Privilege canRoleWrite = privilegeService.findByName(Privileges.CAN_ROLE_WRITE);

        final Privilege canUserRead = privilegeService.findByName(Privileges.CAN_USER_READ);
        final Privilege canUserWrite = privilegeService.findByName(Privileges.CAN_USER_WRITE);

        RestPreconditions.checkNotNull(canPrivilegeRead);
        RestPreconditions.checkNotNull(canPrivilegeWrite);
        RestPreconditions.checkNotNull(canRoleRead);
        RestPreconditions.checkNotNull(canRoleWrite);
        RestPreconditions.checkNotNull(canUserRead);
        RestPreconditions.checkNotNull(canUserWrite);

        createRoleIfNotExisting(Roles.ROLE_USER, Sets.<Privilege>newHashSet(canRoleRead, canPrivilegeRead, canUserRead));
        createRoleIfNotExisting(Roles.ROLE_ADMIN, Sets.<Privilege>newHashSet(canRoleRead, canRoleWrite,
                canPrivilegeRead, canPrivilegeWrite, canUserRead, canUserWrite));
    }

    private void createRoleIfNotExisting(String name, Set<Privilege> privileges) {
        Role existingResource = roleService.findByName(name);
        if (existingResource == null) {
            roleService.create(new Role(name, privileges));
        }
    }

    private void createUsers() {
        Role roleAdmin = roleService.findByName(Roles.ROLE_ADMIN);
        Role roleUser = roleService.findByName(Roles.ROLE_USER);

        createUserIfNotExisting(roleAdmin, UtilNames.ADMIN_USERNAME, UtilNames.ADMIN_EMAIL, UtilNames.ADMIN_PASS);
        createUserIfNotExisting(roleUser, UtilNames.USER_USERNAME, UtilNames.USER_EMAIL, UtilNames.USER_PASS);
    }

    private void createUserIfNotExisting(Role role, String name, String email, String password) {
        User existingResource = userService.findByName(name);
        if (existingResource == null) {
            userService.create(new User(name, email, password, true, Sets.<Role> newHashSet(role)));
        }
    }

}
