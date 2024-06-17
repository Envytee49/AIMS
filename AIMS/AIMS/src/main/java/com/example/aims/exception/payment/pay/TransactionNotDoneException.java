package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

public class TransactionNotDoneException extends PaymentException {
    public TransactionNotDoneException() {
        super("ERROR: Incomplete Transaction!");
    }
}
