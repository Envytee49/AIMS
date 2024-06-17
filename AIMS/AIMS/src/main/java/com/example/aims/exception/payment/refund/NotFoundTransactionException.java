package com.example.aims.exception.payment.refund;

public class NotFoundTransactionException extends RefundException {
    public NotFoundTransactionException() {
        super("ERROR: NOT FOUND TRANSACTION");
    }
}
