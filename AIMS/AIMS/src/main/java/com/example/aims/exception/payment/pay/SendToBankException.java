package com.example.aims.exception.payment.pay;

import com.example.aims.exception.payment.PaymentException;

;

public class SendToBankException extends PaymentException {
    public SendToBankException() {
        super("ERROR: VNPAY has sent a refund request to the Bank (Refund Transaction)");
    }
}
