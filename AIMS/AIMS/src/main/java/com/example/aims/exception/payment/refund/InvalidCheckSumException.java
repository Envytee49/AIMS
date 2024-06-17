package com.example.aims.exception.payment.refund;

public class InvalidCheckSumException extends RefundException {
    public InvalidCheckSumException() {
        super("ERROR: INVALID CHECK SUM");
    }
}
