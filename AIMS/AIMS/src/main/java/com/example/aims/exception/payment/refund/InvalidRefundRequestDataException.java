package com.example.aims.exception.payment.refund;

public class InvalidRefundRequestDataException extends RefundException {
    public InvalidRefundRequestDataException() {
        super("ERROR: INVALID REFUND REQUEST DATA");
    }
}
