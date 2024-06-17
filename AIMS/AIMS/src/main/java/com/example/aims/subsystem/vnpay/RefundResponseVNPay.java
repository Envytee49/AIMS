package com.example.aims.subsystem.vnpay;


import com.example.aims.entity.payment.RefundTransaction;
import com.example.aims.exception.*;
import com.example.aims.exception.payment.refund.*;

import java.util.Map;

class RefundResponseVNPay {
    Map<String, String> response;

    public RefundResponseVNPay(Map<String, String> response){
        this.response = response;
    }

    public RefundTransaction getRefundTransaction(){
        if (response == null) {
            return null;
        }
        // Create Payment transaction
        String errorCode = response.get("vnp_ResponseCode");
        String id = response.get("vnp_ResponseId");
        String message = response.get("vnp_Message");
        int amount = Integer.parseInt(response.get("vnp_Amount")) / 100;
        String content = response.get("vnp_OrderInfo");

        RefundTransaction refundTransaction = new RefundTransaction(id, message, errorCode, amount, content);

        switch (refundTransaction.getErrorCode()) {
            case "00":
                break;
            case "02":
                throw new InvalidIdentifierException();
            case "03":
                throw new InvalidRefundRequestDataException();
            case "91":
                throw new NotFoundTransactionException();
            case "94":
                throw new ProcessingRefundException();
            case "95":
                throw new RejectRefundTransactionException();
            case "97":
                throw new InvalidCheckSumException();
            default:
                throw new UnrecognizedException();
        }
        return refundTransaction;
    }

}
