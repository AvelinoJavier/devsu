package com.devsu.person.repository;

import com.devsu.person.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes")
public interface ClientRepository extends JpaRepository<Client, Long> {
}
