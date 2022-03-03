package com.groupthree.bankapp.model.entity;

public abstract class MinimumBalanceAccount extends Account {

    protected MinimumBalanceAccount(){
        setDidPassMinimumBalance(false);
    }

    public double verifyWithinMinimum(double balance){
        // penalty should not be charged after falling below min balance once
        if (!isDidPassMinimumBalance() && balance < getMinimumBalance()) {
            balance -= getPenalty();
            setDidPassMinimumBalance(true);
        }
        return balance;
    }

    public void verifyOverMinimum() {
        if (this.balance >= getMinimumBalance()) {
            setDidPassMinimumBalance(false);
        }
    }


}
