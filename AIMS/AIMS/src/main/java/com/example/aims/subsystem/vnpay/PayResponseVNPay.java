package com.example.aims.subsystem.vnpay;



import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.exception.*;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.payment.pay.*;
import com.example.aims.exception.payment.refund.ProcessingException;
import com.example.aims.utils.Utils;

import java.util.Map;

class PayResponseVNPay {
    private Map<String, String> response;
    public PayResponseVNPay(Map<String, String> response) {
        this.response = response;
    }

    public PaymentTransaction getPaymentTransaction() throws PaymentException, UnrecognizedException{
        if (response == null) {
            return null;
        }

        switch (response.get("vnp_TransactionStatus")) {
            case "00": {// If code == 00 then create order and save transaction
                break;
            }
            case "01":
                throw new TransactionNotDoneException();
            case "02":
                throw new TransactionFailedException();
            case "04":
                throw new TransactionReverseException();
            case "05":
                throw new ProcessingException();
            case "09":
                throw new RejectedTransactionException();
            case "06":
                throw new SendToBankException();
            case "07":
                throw new AnonymousTransactionException();
            default:
                throw new UnrecognizedException();
        }

        // Create Payment transaction
        String transactionId = response.get("vnp_TransactionNo");
        String transactionContent = response.get("vnp_OrderInfo");
        int amount = Integer.parseInt(response.get("vnp_Amount")) / 100;
        String createdAt = response.get("vnp_PayDate");
        String vnpTxnRef = response.get("vnp_TxnRef");
        PaymentTransaction trans = new PaymentTransaction(
                transactionId,
                transactionContent,
                amount,
                Utils.convertPaymentTimeFormat(createdAt),
                vnpTxnRef);

        return trans;
    }
}
