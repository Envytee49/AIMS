package com.example.aims.view.user;

import com.example.aims.constant.UserRole;
import com.example.aims.controller.LogInController;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.login.EmptyInputException;
import com.example.aims.exception.user.login.NonExistUserException;
import com.example.aims.exception.user.login.UserIsBlockedException;
import com.example.aims.exception.user.login.WrongPasswordException;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import com.example.aims.view.user.admin.UpdateUserScreenController;
import com.example.aims.view.user.productmanager.PendingOrdersScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInScreenController extends BaseScreenController implements GoBack {
    @FXML
    TextField userText;
    @FXML
    private PasswordField passwordText;
    private LogInController logInController;
    public LogInScreenController() {
    }

    public <T> LogInScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }
    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }
    @FXML
    void logIn(ActionEvent event) {
        try {
            User user = logInController.logIn(userText.getText(), passwordText.getText());
            if (user.getRole() == UserRole.ADMIN) {
                new UpdateUserScreenController(currentScene, PathConfig.ADMIN_USER_SCREEN_PATH, user.getId());
            }else if(user.getRole() == UserRole.PRODUCT_MANAGER){
                new PendingOrdersScreenController(currentScene, PathConfig.PENDING_ORDERS, user.getId());
            }
        } catch (NonExistUserException | WrongPasswordException | UserIsBlockedException | EmptyInputException  e ) {
            Utils.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @Override
    public <T> void initData(T... data) {
        logInController = new LogInController();
    }
}
