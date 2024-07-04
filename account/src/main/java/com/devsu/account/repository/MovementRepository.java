package com.devsu.account.repository;

import com.devsu.account.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movimientos", path = "movimientos")
public interface MovementRepository extends JpaRepository<Movement, Long> {
    @Query("SELECT m FROM Movement m WHERE m.account.id = :accountId AND m.date BETWEEN :startDate AND :endDate")
    List<Movement> findByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );
}
