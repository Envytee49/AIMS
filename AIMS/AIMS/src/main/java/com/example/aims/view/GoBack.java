package com.example.aims.view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface GoBack {
    default void goBack(ActionEvent event, Scene prevScene) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(prevScene);
        stage.show();
        stage.centerOnScreen();
    }
}
