package com.groupthree.bankapp.service;

import com.groupthree.bankapp.model.entity.Account;
import com.groupthree.bankapp.model.entity.CheckingAccount;
import com.groupthree.bankapp.model.entity.InterestAccount;
import com.groupthree.bankapp.model.entity.RegularAccount;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    @Autowired
    AccountNumberGeneratorService accountNumberGeneratorService;

    public Account createAccount(String name, String accType) throws AccountTypeNotSupportedException {
        Account newAcc = Account.of(accType, name, accountNumberGeneratorService.generate());
        repository.save(newAcc);
        return newAcc;
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountByAccNumber(String accNumber) throws AccountNotFoundException {
        return repository.findByAccountNumber(accNumber);
    }

    public Account updateAccount(Account account) {
        return repository.save(account);
    }

    public void deleteAccount(String accId) throws AccountNotFoundException {
        Account account = this.getAccountByAccNumber(accId);
        repository.delete(account);
    }
}
