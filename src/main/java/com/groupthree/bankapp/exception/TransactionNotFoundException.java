package com.groupthree.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException() {
        super("Transaction not found.");
    }

    public TransactionNotFoundException(Throwable t) {
        super("Transaction not found.", t);
    }

}
