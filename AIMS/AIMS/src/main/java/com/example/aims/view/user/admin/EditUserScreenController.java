package com.example.aims.view.user.admin;

import com.example.aims.constant.UserRole;
import com.example.aims.constant.UserStatus;
import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.user.User;
import com.example.aims.utils.Utils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.aims.view.user.admin.CreateNode.*;

public class EditUserScreenController {
    public static void showUserDetailsPopup(
            CRUDUserController crudUserController,
            User user) {
        // Create a new stage for the pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("User Details");

        // Create text fields for other properties
        TextField nameField = createTextField(user.getName(), 400);
        TextField emailField = createTextField(user.getEmail(), 400);
        TextField addressField = createTextField(user.getAddress(), 400);
        TextField phoneField = createTextField(user.getPhone(), 400);

        // Create ChoiceBox for role selection
        ChoiceBox<String> roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll("ADMIN", "PRODUCT MANAGER");
        roleChoiceBox.setValue(user.getRole().toString());

        // Create ChoiceBox for status selection
        ChoiceBox<String> statusChoiceBox = new ChoiceBox<>();
        statusChoiceBox.getItems().addAll("BLOCKED", "UNBLOCKED");
        statusChoiceBox.setValue(user.getUserStatus().toString());

        // Create labels with bigger font size and bold titles
        Label nameLabel = createStyledLabel("Name");
        Label emailLabel = createStyledLabel("Email");
        Label addressLabel = createStyledLabel("Address");
        Label phoneLabel = createStyledLabel("Phone");
        Label roleLabel = createStyledLabel("Role");
        Label statusLabel = createStyledLabel("Status");
        Label createdAt = createStyledLabel("Created At: " + user.getCreatedAt());
        Label updatedAt = createStyledLabel("Updated At: " + user.getUpdateAt());

        // Create Confirm button
        Button confirmButton = new Button("CONFIRM EDIT");
        confirmButton.setStyle("-fx-background-color: #21b5dd; -fx-text-fill: #fff;-fx-font-weight: bold");
        // Confirm button action
        confirmButton.setOnAction(event -> {
            // Save the edited details
            User updatedUser = User.builder()
                    .name(nameField.getText())
                    .phone(phoneField.getText())
                    .userStatus(Objects.equals(statusChoiceBox.getValue(), "BLOCKED")
                            ? UserStatus.BLOCKED : UserStatus.UNBLOCKED)
                    .role(roleChoiceBox.getValue().equals("ADMIN") ? UserRole.ADMIN : UserRole.PRODUCT_MANAGER)
                    .address(addressField.getText())
                    .email(emailField.getText())
                    .build();
            try {
                crudUserController.updateUser(updatedUser, user.getId());
                popupStage.close();
            }catch (RuntimeException e){
                Utils.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Arrange labels, text fields, choice boxes, and button in a vertical box
        VBox vbox = new VBox(
                createHBoxWithSpacing(nameLabel, nameField),
                createHBoxWithSpacing(emailLabel, emailField),
                createHBoxWithSpacing(addressLabel, addressField),
                createHBoxWithSpacing(phoneLabel, phoneField),
                createHBoxWithSpacing(roleLabel, roleChoiceBox),
                createHBoxWithSpacing(statusLabel, statusChoiceBox),
                createdAt, updatedAt,
                confirmButton
        );
        vbox.setSpacing(20); // Vertical spacing between components
        vbox.setPadding(new Insets(50));
        vbox.setStyle("-fx-background-color: #fff");
        vbox.setPrefHeight(400);
        vbox.setPrefWidth(600);

        // Set the scene and show the pop-up stage
        Scene scene = new Scene(vbox);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}
