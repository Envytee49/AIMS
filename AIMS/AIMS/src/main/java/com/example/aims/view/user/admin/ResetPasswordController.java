package com.example.aims.view.user.admin;

import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.IncorrectOldPasswordException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.aims.view.user.admin.CreateNode.*;

public class ResetPasswordController {
    public static void showResetPasswordPopUp(CRUDUserController crudUserController, int userId) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("User password change");

        // Create text fields for other properties
        PasswordField oldPasswordField = createPasswordField( 300);
        PasswordField newPasswordField = createPasswordField( 300);

        // Create labels with bigger font size and bold titles
        Label oldPassword = createStyledLabel("Old Password");
        Label newPassword = createStyledLabel("New Password");

        // Create Confirm button
        Button confirmButton = new Button("CONFIRM CHANGE PASSWORD");
        confirmButton.setStyle("-fx-background-color: #21b5dd; -fx-text-fill: #fff;-fx-font-weight: bold");
        // Confirm button action

        // Arrange labels, text fields, choice boxes, and button in a vertical box
        VBox vbox = new VBox(
                createHBoxWithSpacing(oldPassword, oldPasswordField),
                createHBoxWithSpacing(newPassword, newPasswordField),
                confirmButton
        );
        vbox.setSpacing(20); // Vertical spacing between components
        vbox.setPadding(new Insets(50));
        vbox.setStyle("-fx-background-color: #fff");
        vbox.setPrefHeight(200);
        vbox.setPrefWidth(600);

        confirmButton.setOnAction(event -> {
            try {
                crudUserController.changePassword(userId, oldPasswordField.getText(), newPasswordField.getText());
                popupStage.close();
            } catch (IncorrectOldPasswordException e) {
                Label incorrectOldPassword = new Label("Incorrect Old Password");
                incorrectOldPassword.setStyle("-fx-text-fill: red");
                vbox.getChildren().add(incorrectOldPassword);
                e.printStackTrace();
            }

        });
        // Set the scene and show the pop-up stage
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}
