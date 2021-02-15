package com.groupthree.bankapp.model.entity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class InterestAccount extends Account {

    public InterestAccount() {
        this(0.03);
        this.setLastDateSinceTransactionCharged(null);
    }

    public InterestAccount(double interestRate) {
        super();
        setInterestCharge(interestRate);
    }

    public void applyInterestRate() {
        double interestRate = this.getBalance() * this.getInterestCharge();
        this.setBalance(this.getBalance() + interestRate);
    }
}
