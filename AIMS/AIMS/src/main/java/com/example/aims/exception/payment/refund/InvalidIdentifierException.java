package com.example.aims.exception.payment.refund;

public class InvalidIdentifierException extends RefundException {
    public InvalidIdentifierException() {
        super("ERROR: INVALID IDENTIFIER");
    }
}
