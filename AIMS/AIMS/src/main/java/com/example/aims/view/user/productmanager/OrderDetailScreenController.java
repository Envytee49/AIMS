package com.example.aims.view.user.productmanager;

import com.example.aims.controller.PendingOrdersController;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.order.OrderMedia;
import com.example.aims.entity.delivery.DeliveryInfo;
import com.example.aims.entity.delivery.RushDeliveryInfo;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


import java.util.List;

public class OrderDetailScreenController extends BaseScreenController implements GoBack {

    @FXML
    private Button backBtn;

    @FXML
    private FlowPane rushFlowpane;

    @FXML
    private Text deliveryFeeTxt;

    @FXML
    private Text totalAmountTxt;

//    @FXML
//    private Text deliveryInstruction;
//
//    @FXML
//    private Text deliveryTime;

    @FXML
    private Text addressTxt;

    @FXML
    private Text emailTxt;

    @FXML
    private Text nameTxt;

    @FXML
    private Text phoneTxt;

    @FXML
    private Text provinceTxt;

    @FXML
    private HBox deliveryTimeLabel;

    @FXML
    private HBox deliveryInstructionLabel;

    @FXML
    private HBox deliveryTimeBox;

    @FXML
    private HBox deliveryInstructionBox;


    private Order order;

    private PendingOrdersController pendingOrdersController;

    public OrderDetailScreenController() {
    }

    public <T> OrderDetailScreenController(Scene currentScene, String currentScreenPath, T ...data) {
        super(currentScene, currentScreenPath, data);
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }

    @Override
    public <T> void initData(T ...data) {
        this.order = (Order) data[0];
        this.pendingOrdersController = (PendingOrdersController) data[1];

        List<OrderMedia> orderMediaList = order.getLstOrderMedia();
        DeliveryInfo deliveryInfo = order.getDeliveryInfo();
        
        for(OrderMedia orderMedia : orderMediaList) {

            // product image
            Image image = new Image(getClass().getResourceAsStream(orderMedia.getMedia().getImageURL()));
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(173);
            imageView.setPreserveRatio(true);
            HBox mediaImg = new HBox(imageView);
            mediaImg.setAlignment(Pos.CENTER);

            // unit price
            HBox mediaUnitPrice = new HBox(new Text(String.valueOf(orderMedia.getMedia().getPrice())));
            mediaUnitPrice.setPrefWidth(139);
            mediaUnitPrice.setAlignment(Pos.CENTER);

            // total price
            Text totalPriceText = new Text(String.valueOf(orderMedia.getMedia().getPrice() * orderMedia.getQuantity()));
            HBox mediaTotalPrice = new HBox(totalPriceText);
            mediaTotalPrice.setPrefWidth(139);
            mediaTotalPrice.setAlignment(Pos.CENTER);

            // quantity
            HBox mediaQuantity = new HBox(new Text(String.valueOf(orderMedia.getQuantity())));
            mediaQuantity.setPrefWidth(49);
            mediaQuantity.setAlignment(Pos.CENTER);

            Image statusIcon;
            if (deliveryInfo instanceof RushDeliveryInfo) {
                if(!orderMedia.getMedia().isRushOrderAvailable()) {
                    statusIcon = new Image(getClass().getResourceAsStream(PathConfig.SUCCESS_ICON));
                } else {
                    statusIcon = new Image(getClass().getResourceAsStream(PathConfig.FAILURE_ICON));
                }
            } else {
                statusIcon = new Image(getClass().getResourceAsStream(PathConfig.FAILURE_ICON));
            }

            ImageView statusIconView = new ImageView();
            statusIconView.setImage(statusIcon);
            statusIconView.setFitWidth(30);
            statusIconView.setPreserveRatio(true);

            HBox iconContainer = new HBox(statusIconView);
            iconContainer.setPrefWidth(97);
            iconContainer.setAlignment(Pos.CENTER);

            HBox mediaBar = new HBox(mediaImg, mediaUnitPrice, mediaQuantity, mediaTotalPrice, iconContainer);

            rushFlowpane.getChildren().add(mediaBar);
        }
        // This need future work with Hibernate

        nameTxt.setText(deliveryInfo.getName());
        phoneTxt.setText(deliveryInfo.getPhone());
        provinceTxt.setText(deliveryInfo.getProvince());
        emailTxt.setText(deliveryInfo.getEmail());
        addressTxt.setText(deliveryInfo.getAddress());

        if (deliveryInfo instanceof RushDeliveryInfo) {
            Text timeLabel = new Text("Delivery Time");
            Text instructionLabel = new Text("Delivery Instruction");

            deliveryTimeLabel.getChildren().add(timeLabel);
            deliveryTimeLabel.setAlignment(Pos.CENTER_LEFT);

            deliveryInstructionLabel.getChildren().add(instructionLabel);
            deliveryInstructionLabel.setAlignment(Pos.CENTER_LEFT);

            Text time = new Text(((RushDeliveryInfo) deliveryInfo).getDeliveryTime());
            Text instruction = new Text(((RushDeliveryInfo) deliveryInfo).getDeliveryInstruction());

            deliveryTimeBox.getChildren().add(time);
            deliveryTimeBox.setAlignment(Pos.CENTER_RIGHT);

            deliveryInstructionBox.getChildren().add(instruction);
            deliveryInstructionBox.setAlignment(Pos.CENTER_RIGHT);
        }

        deliveryFeeTxt.setText(String.valueOf(order.getShippingFee()));
        totalAmountTxt.setText(String.valueOf(order.getTotalAmount()));
    }

    @FXML
    public void approveOrder (ActionEvent e) {
        pendingOrdersController.approveOrder(order);

        new PendingOrdersScreenController(currentScene, PathConfig.PENDING_ORDERS);
    }

    @FXML
    public void rejectOrder (ActionEvent e) {
        pendingOrdersController.rejectOrder(order);

        new PendingOrdersScreenController(currentScene, PathConfig.PENDING_ORDERS);
    }
}
