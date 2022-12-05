package com.fjern.app.services.servicesImpls;


import com.fjern.app.persistence.entities.Role;
import com.fjern.app.persistence.repositories.RoleRepository;
import com.fjern.app.services.RoleService;
import com.fjern.common.services.AbstractService;
import com.fjern.common.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Autowired
    protected RoleRepository roleRepository;
    @Override
    protected JpaRepository<Role, Long> getRepo() {
        return this.roleRepository;
    }

    @Override
    public Role create(Role object) {
        Role objectWithSameName = findByName(object.getName());
        if (objectWithSameName != null) {
            RestPreconditions.checkIfConstraintViolation(false, "Duplicate name");
        }
        return super.create(object);
    }

    @Override
    public Role findByName(String name) {
       return roleRepository.findByName(name);
    }
}
