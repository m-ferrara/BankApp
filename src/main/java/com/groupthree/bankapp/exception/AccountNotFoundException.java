package com.groupthree.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException() {
        super("Account was not found");
    }

    public AccountNotFoundException(Throwable t) {
        super("Account was not found", t);
    }

}
