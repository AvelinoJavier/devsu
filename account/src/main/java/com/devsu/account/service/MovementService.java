package com.devsu.account.service;

import com.devsu.account.exception.AccountNotFoundException;
import com.devsu.account.exception.InvalidMovementException;
import com.devsu.account.exception.NoBalanceException;
import com.devsu.account.model.Movement;

public interface MovementService {
    Movement createMovement(Movement movement) throws AccountNotFoundException, InvalidMovementException, NoBalanceException;
}
