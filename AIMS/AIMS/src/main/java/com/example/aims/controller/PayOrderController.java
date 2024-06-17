package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;
import com.example.aims.mail.IMailer;
import com.example.aims.mail.MailBody;
import com.example.aims.mail.MailInfo;
import com.example.aims.mail.gmail.GMailer;
import com.example.aims.repository.order.OrderRepository;
import com.example.aims.repository.order.OrderRepositoryImpl;
import com.example.aims.repository.payment.PaymentRepository;
import com.example.aims.repository.payment.PaymentRepositoryImpl;
import com.example.aims.subsystem.IPaymentSubsystem;
import com.example.aims.subsystem.PaymentSubsystem;
import com.example.aims.subsystem.vnpay.VNPaySubsystemController;

import java.io.IOException;
import java.util.Map;

public class PayOrderController {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private IPaymentSubsystem vnPay;
    private IMailer mailer;
    public PayOrderController(){
        this.orderRepository = new OrderRepositoryImpl();
        this.paymentRepository = new PaymentRepositoryImpl();
        this.vnPay = new PaymentSubsystem(new VNPaySubsystemController());
        this.mailer = new GMailer();
    }

    /**
     * save order and payment
     * @param res response from vnpay
     * @param order order of payment
     * @return PaymentTransaction
     * @throws PaymentException
     * @throws UnrecognizedException
     */
    public PaymentTransaction makePayment(Map<String, String> res, Order order)
            throws PaymentException, UnrecognizedException {

        PaymentTransaction transaction = vnPay.getPaymentTransaction(res);
        // if payment is successful then create order then set order to transaction
        Order savedOrder = createOrder(order);
        transaction.setOrder(savedOrder);
        paymentRepository.saveTransaction(transaction);

        return transaction;
    }

    public String generateURL(int amount, String content) throws IOException {
        return vnPay.generateURL(amount, content);
    }

    public void emptyCart(){
        Cart.getCart().getListCartMedia().clear();
    }

    private Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public void sendMail(Invoice invoice, PaymentTransaction transaction) {
        String body = MailBody.createMailBody(invoice, transaction);
        MailInfo mailBody = new MailInfo(invoice.getOrder().getDeliveryInfo().getEmail(), body);
        mailer.sendMail(mailBody);
    }
}
