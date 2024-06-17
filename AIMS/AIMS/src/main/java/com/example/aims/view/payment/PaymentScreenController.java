package com.example.aims.view.payment;

import com.example.aims.controller.PayOrderController;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class PaymentScreenController extends BaseScreenController {
    @FXML
    private WebView webView;

    private WebEngine engine;
    private PayOrderController payOrderController;
    private Invoice invoice;
    private RuntimeException exception;

    public <T> PaymentScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    public PaymentScreenController() {
    }

    @Override
    public <T> void initData(T... data) {
        this.engine = webView.getEngine();
        this.invoice = (Invoice) data[0];
        payOrderController = new PayOrderController();
        // http://localhost:8080/vnpay_jsp/vnpay_return.jsp?
        // vnp_Amount=18000000&
        // vnp_BankCode=NCB&
        // vnp_BankTranNo=VNP14198263&
        // vnp_CardType=ATM&
        // vnp_OrderInfo=Thanh+toan+don+hang%3A06236597&
        // vnp_PayDate=20231124213746&
        // vnp_ResponseCode=00&vnp_TmnCode=5QT3R157&
        // vnp_TransactionNo=14198263&
        // vnp_TransactionStatus=00&
        // vnp_TxnRef=06236597&
        // vnp_SecureHash=2d2d33d975667b584a4a8ad635c43a3410c54e439f4e332031523a929d0d082113b3da217ba20741a9b70160369adc7360464f92e3226210b2414d7e2e012f81
        engine.locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("http://localhost:8080/vnpay_jsp/vnpay_return.jsp?")) {
                completePaymentTransaction(newValue);
            }
        });
        try {
            engine.load(payOrderController.generateURL(invoice.getTotalAmount(), "Payment Transaction"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void completePaymentTransaction(String newValue) {
        try {
            URI uri = new URI(newValue);
            String query = uri.getQuery();

            Map<String, String> params = Utils.parseQueryString(query);

            PaymentTransaction transaction = payOrderController.makePayment(params, invoice.getOrder());
            payOrderController.sendMail(invoice, transaction);
            showResult(transaction);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            exception = e;
            showResult(e);
            e.printStackTrace();
        }
    }

    void showResult(PaymentTransaction paymentTransaction) {
        payOrderController.emptyCart();
        new ResultScreenController(currentScene, PathConfig.RESULT_SCREEN_PATH, paymentTransaction, invoice);
    }

    void showResult(RuntimeException e) {
        new ResultScreenController(currentScene, PathConfig.RESULT_SCREEN_PATH, e);
    }
}
