package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

public class RejectedTransactionException extends PaymentException {
    public RejectedTransactionException() {
        super("ERROR: Refund Transaction Denied");
    }
}
