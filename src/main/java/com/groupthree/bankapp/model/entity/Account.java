package com.groupthree.bankapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularAccount.class, name = "regular"),
        @JsonSubTypes.Type(value = CheckingAccount.class, name = "checking"),
        @JsonSubTypes.Type(value = InterestAccount.class, name = "interest")
})
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String acctNumber;

    protected double balance;

    private double minimumBalance;

    private boolean didPassMinimumBalance;

    private double penalty;

    private double transactionCharge;

    private double interestCharge;

    private double amount;

    private LocalDate lastDateSinceTransactionCharged;

    /**
     * Factory method to create new instances of the Account child classes
     * based on the type provided
     *
     * @param accType The account type to create. Supports: regular, checking, interest
     * @param name The name to register to the account under
     * @param accountNumber The account number to register the account under
     * @return The created account
     * @throws AccountTypeNotSupportedException If the account type provided is not supported
     */
    public static Account of(String accType, String name, String accountNumber) throws AccountTypeNotSupportedException {
        Account newAcc;
        switch (accType) {
            case "regular" : {
                newAcc = new RegularAccount();
                break;
            }
            case "checking" : {
                newAcc = new CheckingAccount();
                break;
            }
            case "interest" : {
                newAcc = new InterestAccount();
                break;
            }
            default: {
                throw new AccountTypeNotSupportedException();
            }
        }

        newAcc.setName(name);
        newAcc.setAcctNumber(accountNumber);

        return newAcc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
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

    @JsonIgnore
    public LocalDate getLastDateSinceTransactionCharged() {
        return this.lastDateSinceTransactionCharged;
    }

    public void setLastDateSinceTransactionCharged(LocalDate lastDateSinceTransactionCharged) {
        this.lastDateSinceTransactionCharged = lastDateSinceTransactionCharged;
    }

}
