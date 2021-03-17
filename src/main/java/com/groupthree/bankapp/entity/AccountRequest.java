package com.groupthree.bankapp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AccountRequest {
    private Long id;

    private String name;

    @Column(name="account_number")
    private String accountNumber;

    protected double balance;

    @Column(name="minimum_balance")
    private double minimumBalance;

    private boolean didPassMinimumBalance;

    private double penalty;

    @Column(name="transaction_charge")
    private double transactionCharge;

    @Column(name="interest_charge")
    private double interestCharge;

    private double amount;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    protected double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    protected boolean isDidPassMinimumBalance() {
        return didPassMinimumBalance;
    }

    protected void setDidPassMinimumBalance(boolean didPassMinimumBalance) {
        this.didPassMinimumBalance = didPassMinimumBalance;
    }
    protected double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    protected double getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    protected double getInterestCharge() {
        return interestCharge;
    }

    public void setInterestCharge(double interestCharge) {
        this.interestCharge = interestCharge;
    }

    protected double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
