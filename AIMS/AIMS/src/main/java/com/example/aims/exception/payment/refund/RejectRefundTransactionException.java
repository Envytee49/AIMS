package com.example.aims.exception.payment.refund;

public class RejectRefundTransactionException extends RefundException {
    public RejectRefundTransactionException() {
        super("ERROR: This transaction was not successful on VNPAY. VNPAY refuses to process the request");
    }
}
