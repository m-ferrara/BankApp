package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.*;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.exception.TransactionTypeNotSupportedException;
import com.groupthree.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository repository;

    @Autowired
    TransactionService transactionService;

    public Account processTransaction(String transactionType, String acctId, double amount)
            throws TransactionTypeNotSupportedException, AccountNotFoundException, AccountTypeNotSupportedException {
        Account account = accountService.getByAccountNumber(acctId);
        double balance = account.getBalance();
        switch (transactionType) {
            case "deposit" : {
                account.setBalance((balance + amount));
                break;
            }
            case "withdrawal" : {
                account.setBalance((balance - amount));
                break;
            }
            default: {
                throw new TransactionTypeNotSupportedException();
            }
        }
        Account newAcc = accountService.updateAccount(account);
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(transactionType);
        transaction.setAmount(amount);
        transaction = repository.saveAndFlush(transaction);
        return account;
    }

    public Transaction updateTransaction(Transaction transaction) {
        return repository.saveAndFlush(transaction);
    }

    public void deleteTransaction(Long transactionId){
        repository.deleteById(transactionId);
    }

    public Page<Transaction> getAllTransactionsByAccount(String accountNumber, Pageable pageable) {
        Page<Transaction> transactions = repository.findAllByAccountAccountNumber(accountNumber, pageable);
        return transactions;
    }

    public Optional<Transaction> getTransactionById(Long transactionId) {
        return repository.findById(transactionId);
    }
}
