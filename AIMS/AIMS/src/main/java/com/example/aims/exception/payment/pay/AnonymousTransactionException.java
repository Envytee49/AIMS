package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

;

public class AnonymousTransactionException extends PaymentException {
    public AnonymousTransactionException() {
        super("ERROR: Transaction is suspicious");
    }
}
