package com.fjern.app.persistence.repositories;

import com.fjern.common.interfaces.ByNameApi;
import com.fjern.app.persistence.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>, ByNameApi<VerificationToken> {
}
