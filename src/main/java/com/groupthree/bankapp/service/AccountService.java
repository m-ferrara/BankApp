package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.CheckingAccount;
import com.groupthree.bankapp.entity.InterestAccount;
import com.groupthree.bankapp.entity.RegularAccount;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    @Autowired
    AccountNumberGeneratorService accountNumberGeneratorService;

    public Account createAccount(String name, String accType) throws AccountTypeNotSupportedException {
        if (accType == null) {
            throw new AccountTypeNotSupportedException();
        }
        Account newAcc;
        switch (accType.toLowerCase()) {
            case "regular" : {
                newAcc = new RegularAccount();
                newAcc.setType("regular");
                break;
            }
            case "checking" : {
                newAcc = new CheckingAccount();
                newAcc.setType("checking");
                break;
            }
            case "interest" : {
                newAcc = new InterestAccount();
                newAcc.setType("interest");
                break;
            }
            default: {
                throw new AccountTypeNotSupportedException();
            }
        }
        newAcc.setName(name);
        newAcc.setAccountNumber(accountNumberGeneratorService.generate());
        repository.save(newAcc);
        return newAcc;
    }

    public Page<Account> getAllAccounts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Account getByAccountNumber(String accNumber) throws AccountNotFoundException {
        Optional<Account> account = repository.findByAccountNumberQuery(accNumber);
        if (!account.isPresent()) {
            throw new AccountNotFoundException();
        }
        return account.get();
    }

    public Account updateAccount(Account account) throws AccountNotFoundException {
        checkAccountExists(account.getAccountNumber());
        return repository.save(account);
    }

    public void deleteAccount(String accountNumber) throws AccountNotFoundException {
        Account account = this.getByAccountNumber(accountNumber);
        repository.delete(account);
    }

    private void checkAccountExists(String accNumber) throws AccountNotFoundException {
        if (!repository.findByAccountNumberQuery(accNumber).isPresent()) {
            throw new AccountNotFoundException();
        }
    }
}
