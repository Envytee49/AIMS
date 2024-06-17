package com.example.aims.view.user.admin;

import com.example.aims.constant.UserRole;
import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.user.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.aims.view.user.admin.CreateNode.createHBoxWithSpacing;
import static com.example.aims.view.user.admin.CreateNode.createStyledLabel;

public class ChangeRoleController {
    public static void showRolePopUp(CRUDUserController crudUserController, User user) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("User roles");

        // Create ChoiceBox for role selection
        ChoiceBox<String> roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll("ADMIN", "PRODUCT MANAGER");
        roleChoiceBox.setValue(user.getRole().toString());

        Label roleLabel = createStyledLabel("Role");

        // Create Confirm button
        Button confirmButton = new Button("CONFIRM UPDATE ROLE");
        confirmButton.setStyle("-fx-background-color: #21b5dd; -fx-text-fill: #fff;-fx-font-weight: bold");
        // Confirm button action
        confirmButton.setOnAction(event -> {
            // Save the edited details
            if (roleChoiceBox.getValue().equals("ADMIN")) {
                crudUserController.updateUserRole(user.getId(), UserRole.ADMIN);
            } else if (roleChoiceBox.getValue().equals("PRODUCT MANAGER")) {
                crudUserController.updateUserRole(user.getId(), UserRole.PRODUCT_MANAGER);
            }
            popupStage.close();
        });

        // Arrange labels, text fields, choice boxes, and button in a vertical box
        VBox vbox = new VBox(
                createHBoxWithSpacing(roleLabel, roleChoiceBox),
                confirmButton
        );
        vbox.setSpacing(20); // Vertical spacing between components
        vbox.setPadding(new Insets(50));
        vbox.setStyle("-fx-background-color: #fff");
        vbox.setPrefHeight(200);
        vbox.setPrefWidth(400);

        // Set the scene and show the pop-up stage
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }


}
