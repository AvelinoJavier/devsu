package com.devsu.account.controller;

import com.devsu.account.exception.AccountNotFoundException;
import com.devsu.account.exception.InvalidMovementException;
import com.devsu.account.exception.NoBalanceException;
import com.devsu.account.model.Movement;
import com.devsu.account.service.MovementService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController
public class MovementController {
    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "/movimientos")
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) throws InvalidMovementException, NoBalanceException, AccountNotFoundException {
        Movement createdMovement = movementService.createMovement(movement);
        return ResponseEntity.ok(createdMovement);
    }
}
