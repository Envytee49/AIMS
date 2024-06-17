package com.example.aims.view.payment;

import com.example.aims.entity.order.Invoice;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class PaymentMethodScreenController extends BaseScreenController implements GoBack {
    @FXML
    RadioButton checkBox;

    @FXML
    Button submitBtn;

    private Invoice invoice;
    public <T> PaymentMethodScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    public PaymentMethodScreenController() {
    }

    @Override
    public <T> void initData(T... data) {
        this.invoice = (Invoice) data[0];
        submitBtn.setDisable(true);
        ChangeListener<Boolean> changeListener = (observable, oldValue, newValue) -> {
            if (checkBox.isSelected()) {
                submitBtn.setDisable(false);
            } else {
                submitBtn.setDisable(true);
            }
        };

        checkBox.selectedProperty().addListener(changeListener);
    }

    @FXML
    protected void Submit(ActionEvent event) throws IOException {
        new PaymentScreenController(currentScene, PathConfig.PAYMENT_SCREEN_PATH, invoice);
    }
    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }
}
