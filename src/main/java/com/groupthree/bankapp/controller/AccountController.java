package com.groupthree.bankapp.controller;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.AccountRequest;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    @Autowired
    AccountService service;


    @PostMapping
    public ResponseEntity<Account> createOrUpdateAccount(@RequestBody Account account) throws AccountNotFoundException, AccountTypeNotSupportedException {
        if (account.getAccountNumber() == null) {
            Account newAcc = service.createAccount(account.getName(), account.getType());
            return new ResponseEntity<>(newAcc, new HttpHeaders(), HttpStatus.CREATED);
        }
        // check for existing
        Account acc = service.getByAccountNumber(account.getAccountNumber());
//        acc.setType(account.getType());
//        acc.setBalance(account.getBalance());
//        acc.setName(account.getName());
        Account updatedAcc = service.updateAccount(account);
        return new ResponseEntity<>(updatedAcc, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{accId}")
    public ResponseEntity<Account> getAccountByAccId(@PathVariable String accId) throws AccountNotFoundException {
        Account acc = service.getByAccountNumber(accId);
        return new ResponseEntity<>(acc, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Account>> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = service.getAllAccounts(pageable);
        return new ResponseEntity<>(accounts, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{accId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accId) throws AccountNotFoundException {
        service.deleteAccount(accId);
        return new ResponseEntity<String>("Account was deleted", new HttpHeaders(), HttpStatus.OK);
    }
}
