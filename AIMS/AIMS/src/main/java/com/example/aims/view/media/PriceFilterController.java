package com.example.aims.view.media;

import com.example.aims.utils.PathConfig;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PriceFilterController implements Initializable {
    public static String searchQuery ="";
    @FXML
    private ComboBox<String> dropdown;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropdown.getItems().addAll("Price", "High to Low", "Low to High");
        dropdown.getSelectionModel().selectFirst();
        dropdown.setOnAction(event -> {
            String priceFilter = dropdown.getValue();
            Scene currentScene = ((Node) event.getSource()).getScene();
            new HomeScreenController(currentScene, PathConfig.HOME_PATH, searchQuery, priceFilter);
            dropdown.setValue(priceFilter);
        });
    }
}
