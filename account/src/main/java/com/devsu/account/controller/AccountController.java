package com.devsu.account.controller;

import com.devsu.account.exception.PersonNotFoundException;
import com.devsu.account.model.Account;
import com.devsu.account.service.AccountService;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@BasePathAwareController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/cuentas")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) throws PersonNotFoundException {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping(value = "/cuentas/cliente/{cliente}")
    public ResponseEntity<List<Account>> getAccountByClient(@PathVariable("cliente") Long client) throws PersonNotFoundException {
        List<Account> clientAccounts = accountService.getAccountByClient(client);
        return ResponseEntity.ok(clientAccounts);
    }

    @GetMapping(value = "/cuentas/cliente/{cliente}/movimientos/{dateFrom}/{dateTo}")
    public ResponseEntity<List<Account>> getAccountByClient(@PathVariable("cliente") Long client, @PathVariable("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom, @PathVariable("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo) throws PersonNotFoundException {
        List<Account> clientAccounts = accountService.getAccountByClientAndMovementDate(client, dateFrom, dateTo);
        return ResponseEntity.ok(clientAccounts);
    }
}
