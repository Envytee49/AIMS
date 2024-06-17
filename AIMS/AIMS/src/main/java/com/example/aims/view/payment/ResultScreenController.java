package com.example.aims.view.payment;

import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.cart.CartScreenController;
import com.example.aims.view.media.HomeScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ResultScreenController extends BaseScreenController {

    @FXML
    private Text amountText;

    @FXML
    private AnchorPane failedScreen;

    @FXML
    private Text name;

    @FXML
    private Text phone;

    @FXML
    private Text province;

    @FXML
    private Text shippingAddress;

    @FXML
    private Button submitBtn;

    @FXML
    private AnchorPane successScreen;

    @FXML
    private Text transErrorMessage;

    @FXML
    private Text transactionContent;

    @FXML
    private Text transactionDatetime;

    @FXML
    private Text transactionID;

    private Invoice invoice;

    private PaymentTransaction paymentTransaction;

    public <T> ResultScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    public ResultScreenController() {
    }

    @FXML
    protected void Submit(ActionEvent event) throws IOException {
        if(paymentTransaction != null) {
            new HomeScreenController(currentScene, PathConfig.HOME_PATH,"", "", 0);
        }
        else {
           new CartScreenController(currentScene, PathConfig.CART_PATH);
        }
    }

    @Override
    public <T> void initData(T... data) {
        if(data.length == 2) {
            this.paymentTransaction = (PaymentTransaction) data[0];
            this.invoice = (Invoice) data[1];
            this.failedScreen.setVisible(false);
            this.successScreen.setVisible(true);
            submitBtn.setText("Back to Homepage");
            transactionID.setText(paymentTransaction.getTransactionId());
            amountText.setText(String.valueOf(paymentTransaction.getAmount()));
            transactionContent.setText(paymentTransaction.getTransactionContent());
            transactionDatetime.setText(paymentTransaction.getCreatedAt());
            name.setText(invoice.getOrder().getDeliveryInfo().getName());
            phone.setText(invoice.getOrder().getDeliveryInfo().getPhone());
            province.setText(invoice.getOrder().getDeliveryInfo().getProvince());
            shippingAddress.setText(invoice.getOrder().getDeliveryInfo().getAddress());
            amountText.setText(String.valueOf(paymentTransaction.getAmount()));
        }else {
            this.failedScreen.setVisible(true);
            this.successScreen.setVisible(false);
            PaymentException paymentException = (PaymentException) data[0];
            transErrorMessage.setText(paymentException.getMessage());
            submitBtn.setText("Back to Cart");
        }
    }

}
