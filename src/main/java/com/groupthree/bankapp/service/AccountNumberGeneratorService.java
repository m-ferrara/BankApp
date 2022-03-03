package com.groupthree.bankapp.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountNumberGeneratorService {
    private Random random;

    public AccountNumberGeneratorService() {
        this.random = new Random();
    }

    public String generate() {
        StringBuilder accountNumber = new StringBuilder();
        int accountNumberDigits = 16;
        for (int i=0; i<accountNumberDigits; i++) {
            int randInt = this.random.nextInt(9);
            accountNumber.append(String.valueOf(randInt));
        }
        return accountNumber.toString();
    }
}
