package com.example.aims.view;

import com.example.aims.utils.PathConfig;
import com.example.aims.view.cart.CartScreenController;
import com.example.aims.view.media.HomeScreenController;
import com.example.aims.view.media.PriceFilterController;
import com.example.aims.view.user.LogInScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.util.Optional;

public class NavigationController {
    @FXML
    private TextField searchBox;
    public void switchScene(ActionEvent event, String path, Optional<String> query) {
//        try {
            Scene currentScene = ((Node) event.getSource()).getScene();
            if(path.equals(PathConfig.HOME_PATH)) {
                new HomeScreenController(currentScene, PathConfig.HOME_PATH, query.get(), "", 0);
            }else if(path.equals(PathConfig.CART_PATH)) {
                new CartScreenController(currentScene, PathConfig.CART_PATH);
            }else if(path.equals(PathConfig.LOGIN_PATH)) {
                new LogInScreenController(currentScene, PathConfig.LOGIN_PATH);
            }
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
//            Parent root = loader.load();
//            BaseScreenController baseScreenController = loader.getController();
//            baseScreenController.setPrevScene(((Node) event.getSource()).getScene());
//            if(query.isPresent()) {
//                baseScreenController.initData(query.get(), "", 0);
//            }
//            else baseScreenController.initData();
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            baseScreenController.setCurrentScene(scene);
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
//            stage.centerOnScreen();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void switchToCart(ActionEvent event) {
        switchScene(event, PathConfig.CART_PATH, Optional.empty());
    }

    @FXML
    void switchToHome(ActionEvent event) {
        switchScene(event, PathConfig.HOME_PATH, Optional.of(""));
    }
    @FXML
    void searchQuery(ActionEvent event) {
        PriceFilterController.searchQuery = searchBox.getText();
        switchScene(event,PathConfig.HOME_PATH, Optional.of(searchBox.getText()));
    }
    @FXML
    void switchToLogIn(ActionEvent event) {
        switchScene(event, PathConfig.LOGIN_PATH, Optional.empty());
    }
}
