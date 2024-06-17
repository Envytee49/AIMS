package com.example.aims.view.user.admin;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CreateNode {
    public static HBox createHBoxWithSpacing(Label label, Node node) {
        HBox hbox = new HBox(30); // Horizontal spacing between label and node
        hbox.getChildren().addAll(label, node);
        return hbox;
    }

    public static TextField createTextField(String initialValue, double prefWidth) {
        TextField textField = new TextField(initialValue);
        textField.setPrefWidth(prefWidth);
        return textField;
    }
    public static PasswordField createPasswordField(double prefWidth) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(prefWidth);
        return passwordField;
    }
    public static Label createStyledLabel(String title) {
        Label label = new Label(title + ": ");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return label;
    }

}
