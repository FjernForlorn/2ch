package com.fjern.app.persistence.repositories;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.common.interfaces.ByNameApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, ByNameApi<Privilege> {
}
