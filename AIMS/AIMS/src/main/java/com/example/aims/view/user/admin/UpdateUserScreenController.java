package com.example.aims.view.user.admin;

import com.example.aims.controller.CRUDUserController;
import com.example.aims.entity.user.User;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.media.HomeScreenController;
import com.example.aims.view.user.LogInScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.Date;
import java.util.List;

public class UpdateUserScreenController extends BaseScreenController {
    @FXML
    private TableColumn<User, List<Button>> action;

    @FXML
    private TableColumn<User, String> address;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> phone;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private TableColumn<User, String> status;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> username;

    private CRUDUserController crudUserController;
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private Integer userId;
    public UpdateUserScreenController() {
    }

    public <T> UpdateUserScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @Override
    public <T> void initData(T... data) {
        crudUserController = new CRUDUserController();
        this.userId = (Integer) data[0];
        List<User> users = crudUserController.getAllUsers(userId);
        userList.addAll(users);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        status.setCellValueFactory(new PropertyValueFactory<>("userStatus"));

        // Assuming you have a method to create action buttons for each user
        action.setCellFactory(createActionButtonCellFactory());
        // Set up the status column with a custom cell factory

        // Populate the TableView with the users list
        userTable.setItems(userList);

        userTable.setRowFactory(tableView -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User user = row.getItem();
                    EditUserScreenController
                            .showUserDetailsPopup( crudUserController, user);
                    refreshTableView();
                }
            });
            return row;
        });
    }

    private Callback<TableColumn<User, List<Button>>, TableCell<User, List<Button>>> createActionButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<User, List<Button>> call(final TableColumn<User, List<Button>> param) {
                return new TableCell<>() {
                    private final Button resetPasswordButton = new Button("Reset Password");
                    private final Button deleteButton = new Button("Delete");
                    private final Button blockButton = new Button("Block");
                    private final Button unblockButton = new Button("Unblock");
                    private final Button changeRoleButton = new Button("Change Role");

                    {
                        resetPasswordButton.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleResetPassword(user);
                        });
                        deleteButton.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleDelete(user);
                        });
                        blockButton.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleBlock(user);
                        });
                        unblockButton.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleUnblock(user);
                        });
                        changeRoleButton.setOnAction((ActionEvent event) -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleChangeRole(user);
                        });
                    }

                    @Override
                    protected void updateItem(List<Button> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Create a layout to hold all buttons
                            HBox buttons = new HBox(
                                    resetPasswordButton,
                                    deleteButton,
                                    blockButton,
                                    unblockButton,
                                    changeRoleButton);
                            buttons.setSpacing(7);
                            setGraphic(buttons);
                        }
                    }
                };
            }
        };
    }

    private void handleResetPassword(User user) {
        ResetPasswordController.showResetPasswordPopUp(crudUserController, user.getId());
    }

    private void handleDelete(User user) {
        // Implement delete logic
        crudUserController.removeUser(user);
        refreshTableView();
    }

    private void handleBlock(User user) {
        // Implement block logic
        crudUserController.blockUser(user.getId());
        refreshTableView();
    }

    private void handleUnblock(User user) {
        // Implement unblock logic
        crudUserController.unblockUser(user.getId());
        refreshTableView();
    }

    private void handleChangeRole(User user) {
        // pop up
        ChangeRoleController.showRolePopUp(crudUserController, user);
        refreshTableView();
    }

    public void refreshTableView() {
        userList.clear();
        List<User> users = crudUserController.getAllUsers(userId);
        userList.addAll(users);
        userTable.setItems(userList);
        userTable.refresh();
    }

    @FXML
    void logOut(ActionEvent event) {
        new HomeScreenController(currentScene, PathConfig.HOME_PATH, "", "", 0);
    }

    @FXML
    void refresh(ActionEvent event) {
        refreshTableView();
    }
    @FXML
    void changePassword(ActionEvent event) {
        ResetPasswordController.showResetPasswordPopUp(crudUserController, this.userId);
    }
    @FXML
    void addUser(ActionEvent event) {
        new CreateUserScreenController(currentScene, PathConfig.CREATE_USER_PATH);


    }
}