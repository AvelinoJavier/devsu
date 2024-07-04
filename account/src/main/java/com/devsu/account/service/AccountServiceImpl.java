package com.devsu.account.service;

import com.devsu.account.client.PersonClient;
import com.devsu.account.dto.ClientDTO;
import com.devsu.account.exception.PersonNotFoundException;
import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import com.devsu.account.repository.AccountRepository;
import com.devsu.account.repository.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;
    private final PersonClient personClient;

    public AccountServiceImpl(AccountRepository accountRepository, MovementRepository movementRepository, PersonClient personClient) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
        this.personClient = personClient;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) throws PersonNotFoundException {
        personClient.getClientById(account.getClientId());
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountByClient(Long clientId) throws PersonNotFoundException {
        ClientDTO client = personClient.getClientById(clientId);
        return accountRepository.findByClientId(clientId).stream().peek(account -> account.setClientName(client.getName())).collect(Collectors.toList());
    }

    @Override
    public List<Account> getAccountByClientAndMovementDate(Long clientId, Date dateFrom, Date dateTo) throws PersonNotFoundException {
        ClientDTO client = personClient.getClientById(clientId);

        List<Account> accounts = accountRepository.findByClientId(clientId).stream().peek(account -> account.setClientName(client.getName())).collect(Collectors.toList());

        for (Account account : accounts) {
            List<Movement> movements = movementRepository.findByAccountIdAndDateRange(account.getId(), dateFrom, dateTo);
            account.setMovements(movements);
        }

        return accounts;
    }
}
