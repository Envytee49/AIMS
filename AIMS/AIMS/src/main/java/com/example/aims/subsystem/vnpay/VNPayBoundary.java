package com.example.aims.subsystem.vnpay;

import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.entity.payment.RefundTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

class VNPayBoundary {

	public String generateURL(int amount, String content) throws UnsupportedEncodingException {
		PayRequestVNPay payRequestVNPay = new PayRequestVNPay(amount, content);
		return payRequestVNPay.generateURL();
	}

	public RefundTransaction refund(PaymentTransaction paymentTransaction) throws IOException {
		RefundRequestVNPay refundRequestVNPay = new RefundRequestVNPay( paymentTransaction);
		String response = refundRequestVNPay.refund();
		Gson gson = new Gson();
		Type type = new com.google.gson.reflect.TypeToken<HashMap<String, String>>() {}.getType();
		HashMap<String, String> resultHashmap = gson.fromJson(response, type);
		RefundResponseVNPay refundResponseVNPay = new RefundResponseVNPay(resultHashmap);
		return refundResponseVNPay.getRefundTransaction();
	}

	public PaymentTransaction getPaymentTransaction(Map<String,String> response) throws PaymentException, UnrecognizedException {
		PayResponseVNPay payResponseVNPay = new PayResponseVNPay(response);
		return payResponseVNPay.getPaymentTransaction();
	}

}
