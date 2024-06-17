package com.example.aims.exception.payment.refund;

import com.example.aims.exception.payment.PaymentException;

;

public class ProcessingException extends PaymentException {
    public ProcessingException() {
        super("ERROR: VNPAY is processing this transaction (Refund Transaction)!");
    }
}
