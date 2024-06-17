package com.example.aims.exception.payment.refund;

public class ProcessingRefundException extends RefundException {
    public ProcessingRefundException() {
        super("ERROR: The transaction has previously been submitted for a refund. This request is being processed by VNPAY");
    }
}
