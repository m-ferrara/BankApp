package com.groupthree.bankapp.controller;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.Transaction;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.exception.TransactionNotFoundException;
import com.groupthree.bankapp.exception.TransactionTypeNotSupportedException;
import com.groupthree.bankapp.service.TransactionService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/accounts/{accId}/transactions")
public class TransactionController {
    @Autowired
    TransactionService service;

    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(@PathVariable String accId, Pageable pageable) {
        Page<Transaction> transactions = service.getAllTransactionsByAccount(accId, pageable);
        return new ResponseEntity<>(transactions, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> processTransaction(@PathVariable String accId, @RequestBody Transaction transaction) throws TransactionTypeNotSupportedException, AccountNotFoundException, AccountTypeNotSupportedException {
        Account account = service.processTransaction(transaction.getType(), accId, transaction.getAmount());
        return new ResponseEntity<Account>(account, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long transactionId, @RequestBody Transaction transaction) throws TransactionNotFoundException, AccountNotFoundException {
        transactionExists(transactionId);
        Transaction updatedTransaction = service.updateTransaction(transaction);
        return new ResponseEntity<>(updatedTransaction, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) throws TransactionNotFoundException {
        transactionExists(transactionId);
        service.deleteTransaction(transactionId);
        return new ResponseEntity<>("Delete transaction completed successfully", new HttpHeaders(), HttpStatus.OK);
    }

    private void transactionExists(Long transactionId) throws TransactionNotFoundException {
        Optional<Transaction> transactionFromDb = service.getTransactionById(transactionId);
        if (!transactionFromDb.isPresent()) {
            throw new TransactionNotFoundException();
        }
    }
}
