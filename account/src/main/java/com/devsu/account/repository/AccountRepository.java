package com.devsu.account.repository;

import com.devsu.account.model.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "cuentas", path = "cuentas")
public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = {"movements"})
    List<Account> findByClientId(Long clientId);
}
