package com.example.aims.view.user.admin;


import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.user.User;
import com.example.aims.exception.RuntimeException;
import com.example.aims.exception.user.create.UsernameExistException;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CreateUserScreenController extends BaseScreenController implements GoBack {
    private CRUDUserController crudUserController;

    @FXML
    TextField nameText;
    @FXML
    TextField emailText;
    @FXML
    TextField passwordText;
    @FXML
    TextField addressText;
    @FXML
    TextField usernameText;
    @FXML
    TextField phoneText;
    @FXML
    ChoiceBox<String> roleCB;
    @FXML
    ChoiceBox<String> blockCB;

    public CreateUserScreenController() {

    }

    public <T> CreateUserScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }

    @FXML
    void saveUser(ActionEvent event) {
        try {
            crudUserController
                    .createUser(nameText.getText(),
                            emailText.getText(),
                            addressText.getText(),
                            phoneText.getText(),
                            usernameText.getText(),
                            passwordText.getText(),
                            roleCB.getValue(),
                            blockCB.getValue());
            Utils.showAlert("Create User",
                    "Create user successful",
                    Alert.AlertType.INFORMATION);
        } catch (RuntimeException e) {
            Utils.showAlert("Create User", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public <T> void initData(T... data) {
        crudUserController = new CRUDUserController();
        roleCB.getItems().addAll("ADMIN", "PRODUCT_MANAGER");
        blockCB.getItems().addAll("UNBLOCKED", "BLOCKED");
        roleCB.setValue("ADMIN");
        blockCB.setValue("UNBLOCKED");


    }
}
