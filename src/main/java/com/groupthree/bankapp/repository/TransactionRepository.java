package com.groupthree.bankapp.repository;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.Transaction;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByAccountAccountNumber(String accountNumber, Pageable pageable);
}
