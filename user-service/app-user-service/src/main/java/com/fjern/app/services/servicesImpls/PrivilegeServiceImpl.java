package com.fjern.app.services.servicesImpls;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.app.persistence.repositories.PrivilegeRepository;
import com.fjern.app.services.PrivilegeService;
import com.fjern.common.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl extends AbstractService<Privilege> implements PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public Privilege findByName(String name) {
        return getRepo().findByName(name);
    }

    @Override
    protected PrivilegeRepository getRepo() {
        return privilegeRepository;
    }
}
