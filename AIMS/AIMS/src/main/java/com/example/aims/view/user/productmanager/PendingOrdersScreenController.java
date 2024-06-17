package com.example.aims.view.user.productmanager;

import com.example.aims.controller.CRUDUserController;
import com.example.aims.controller.PendingOrdersController;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.order.OrderMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.media.HomeScreenController;
import com.example.aims.view.user.admin.ChangeRoleController;
import com.example.aims.view.user.admin.EditUserScreenController;
import com.example.aims.view.user.admin.ResetPasswordController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;
import java.util.stream.Collectors;

public class PendingOrdersScreenController extends BaseScreenController {
    @FXML
    private TableColumn<Order, List<Button>> action;

    @FXML
    private TableColumn<Order, String> address;

    @FXML
    private TableColumn<Order, String> email;

    @FXML
    private TableColumn<Order, Integer> id;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, String> phone;

    @FXML
    private TableColumn<Order, Integer> totalAmount;

    @FXML
    private TableColumn<Order, String> productList;

    @FXML
    private TableView<Order> pendingOrdersTable;

    private int userId;

    private PendingOrdersController pendingOrdersController;
    private ObservableList<Order> orderObservableList = FXCollections.observableArrayList();

    public PendingOrdersScreenController() {
    }

    public <T> PendingOrdersScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @Override
    public <T> void initData(T... data) {
        this.userId = (Integer) data[0];

        pendingOrdersController = new PendingOrdersController();

        List<Order> orders = pendingOrdersController.getPendingOrders();

        orderObservableList.addAll(orders);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        name.setCellValueFactory(cellData -> {
            DeliveryInfo deliveryInfo = cellData.getValue().getDeliveryInfo();
            return new SimpleStringProperty(deliveryInfo != null ? deliveryInfo.getName() : "");
        });

        email.setCellValueFactory(cellData -> {
            DeliveryInfo deliveryInfo = cellData.getValue().getDeliveryInfo();
            return new SimpleStringProperty(deliveryInfo != null ? deliveryInfo.getEmail() : "");
        });

        phone.setCellValueFactory(cellData -> {
            DeliveryInfo deliveryInfo = cellData.getValue().getDeliveryInfo();
            return new SimpleStringProperty(deliveryInfo != null ? deliveryInfo.getPhone() : "");
        });

        address.setCellValueFactory(cellData -> {
            DeliveryInfo deliveryInfo = cellData.getValue().getDeliveryInfo();
            return new SimpleStringProperty(deliveryInfo != null ? deliveryInfo.getAddress() + ", " + deliveryInfo.getProvince() : "");
        });

        productList.setCellValueFactory(cellData -> {
            List<OrderMedia> orderMediaList = cellData.getValue().getLstOrderMedia();
            String productNames = orderMediaList.stream().map(om -> om.getMedia().getTitle()).collect(Collectors.joining(", "));
            return new SimpleStringProperty(productNames);
        });

        totalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        // Assuming you have a method to create action buttons for each user
        action.setCellFactory(createActionButtonCellFactory());
        // Set up the status column with a custom cell factory

        // Populate the TableView with the users list
        pendingOrdersTable.setItems(orderObservableList);

        pendingOrdersTable.setRowFactory(tableView -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Order order = row.getItem();
                    new OrderDetailScreenController(currentScene, PathConfig.ORDER_DETAIL, order, pendingOrdersController);
                }
            });
            return row;
        });
    }

    private Callback<TableColumn<Order, List<Button>>, TableCell<Order, List<Button>>> createActionButtonCellFactory() {
        return new Callback<>() {
            @Override
            public TableCell<Order, List<Button>> call(final TableColumn<Order, List<Button>> param) {
                return new TableCell<>() {
                    private final Button approveButton = new Button("Approve");
                    private final Button rejectButton = new Button("Reject");

                    {
                        approveButton.setOnAction((ActionEvent event) -> {
                            Order order = getTableView().getItems().get(getIndex());
                            pendingOrdersController.approveOrder(order);
                            refreshTableView();
                        });
                        rejectButton.setOnAction((ActionEvent event) -> {
                            Order order = getTableView().getItems().get(getIndex());
                            pendingOrdersController.rejectOrder(order);
                            refreshTableView();
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
                                    approveButton,
                                    rejectButton);
                            buttons.setSpacing(5);
                            setGraphic(buttons);
                        }
                    }
                };
            }
        };
    }

    public void refreshTableView() {
        orderObservableList.clear();
        List<Order> orders = pendingOrdersController.getPendingOrders();
        orderObservableList.addAll(orders);
        pendingOrdersTable.setItems(orderObservableList);
        pendingOrdersTable.refresh();
    }

    @FXML
    void logOut(ActionEvent event) {
        new HomeScreenController(currentScene, PathConfig.HOME_PATH, "", "", 0);
    }

    @FXML
    void manageProducts(ActionEvent event) {
        new UpdateMediaScreenController(currentScene, PathConfig.PM_MEDIA_SCREEN_PATH, userId);
    }

    @FXML
    void changePassword(ActionEvent event) {
        ResetPasswordController.showResetPasswordPopUp(new CRUDUserController(), userId);
    }
}
