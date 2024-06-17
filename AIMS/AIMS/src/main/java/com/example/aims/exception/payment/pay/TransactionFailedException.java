package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

public class TransactionFailedException extends PaymentException {

    public TransactionFailedException() {
        super("ERROR: Transaction failed!");
    }

}
