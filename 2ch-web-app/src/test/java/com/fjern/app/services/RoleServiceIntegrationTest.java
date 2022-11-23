package com.fjern.app.services;

import com.fjern.app.client.ops.RoleDtoOps;
import com.fjern.app.persistence.entities.Role;
import com.fjern.client.IDtoOps;
import com.fjern.common.services.RawService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceIntegrationTest extends SecServiceIntegrationTest<Role> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoOps roleDtoOps;

    @Override
    public RawService<Role> getApi() {
        return roleService;
    }

    @Override
    public IDtoOps<Role> getDtoOps() {
        return roleDtoOps;
    }


}
