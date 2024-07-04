package com.devsu.account.service;

import com.devsu.account.exception.PersonNotFoundException;
import com.devsu.account.model.Account;

import java.util.Date;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account) throws PersonNotFoundException;

    List<Account> getAccountByClient(Long clientId) throws PersonNotFoundException;

    List<Account> getAccountByClientAndMovementDate(Long clientId, Date dateFrom, Date dateTo) throws PersonNotFoundException;
}
