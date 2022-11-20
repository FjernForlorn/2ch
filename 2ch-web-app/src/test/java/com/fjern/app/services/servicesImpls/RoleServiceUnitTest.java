package com.fjern.app.services.servicesImpls;

import com.fjern.app.common.FixtureEntityFactory;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.persistence.repositories.RoleRepository;
import com.fjern.common.services.IService;
import com.fjern.test.common.service.AbstractServiceUnitTest;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceUnitTest extends AbstractServiceUnitTest<Role> {

    private RoleServiceImpl instance;

    private RoleRepository repositoryMock;

    @Override
    @Before
    public final void before() {
        instance = new RoleServiceImpl();
        repositoryMock = mock(RoleRepository.class);
        when(repositoryMock.save(any(Role.class))).thenReturn(new Role());
        when(repositoryMock.findAll()).thenReturn(Lists.<Role> newArrayList());
        instance.roleRepository=repositoryMock;
    }

    @Override
    protected Role createNewEntity() {
        return FixtureEntityFactory.createNewRole();
    }

    @Override
    protected IService<Role> getApi() {
        return instance;
    }

    @Override
    protected JpaRepository<Role, Long> getRepo() {
        return repositoryMock;
    }
}
