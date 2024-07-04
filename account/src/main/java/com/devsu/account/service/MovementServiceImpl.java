package com.devsu.account.service;

import com.devsu.account.enums.MovementType;
import com.devsu.account.exception.AccountNotFoundException;
import com.devsu.account.exception.InvalidMovementException;
import com.devsu.account.exception.NoBalanceException;
import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.repository.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MovementServiceImpl implements MovementService {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public MovementServiceImpl(AccountRepository accountRepository, MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public Movement createMovement(Movement movement) throws AccountNotFoundException, InvalidMovementException, NoBalanceException {
        int operation = movement.getMovementType().equals(MovementType.DEPOSITO) ? 1 : -1;

        int signum = movement.getValue().signum();
        if (signum == -1 && operation > 0 || signum == 1 && operation < 0)
            throw new InvalidMovementException("Invalid movement");

        Long accountId = movement.getAccount().getId();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " not found"));

        BigDecimal newBalance = account.getBalance().add(movement.getValue()).multiply(new BigDecimal(operation));
        if (newBalance.signum() == -1)
            throw new NoBalanceException("Saldo no disponible");

        account.setBalance(newBalance);
        accountRepository.save(account);

        return movementRepository.save(movement);
    }
}
