package com.example.aims.view.delivery;

import com.example.aims.controller.PlaceOrderController;
import com.example.aims.controller.RushOrderController;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.delivery.*;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import com.example.aims.view.payment.InvoiceScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class RushOrderScreenController extends BaseScreenController implements Initializable, GoBack {

    @FXML
    private Button backBtn;

    @FXML
    private FlowPane rushFlowpane;

    @FXML
    private Text deliveryFeeTxt;

    @FXML
    private Text rushDeliveryFeeTxt;

    @FXML
    private TextArea deliveryInstruction;

    @FXML
    private ComboBox<String> deliveryTime;

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
    private Text subtotal;

    private List<CartMedia> cartMediaList;

    private DeliveryInfo rushDeliveryInfo;

    private int[] deliveryFee;

    private int combinedDeliveryFee;

    private RushOrderController rushOrderController;

    public RushOrderScreenController() {
    }

    public <T> RushOrderScreenController(Scene currentScene, String currentScreenPath, T ...data) {
        super(currentScene, currentScreenPath, data);
    }

    @FXML
    void requestPayOrder() {
        HashMap<String, String> rushDeliveryInfoMap = new HashMap<>();
        rushDeliveryInfoMap.put("DeliveryTime", deliveryTime.getValue());
        rushDeliveryInfoMap.put("DeliveryInstruction", deliveryInstruction.getText());

        try {
            rushOrderController.processRushDeliveryInfo(rushDeliveryInfoMap);
            rushDeliveryInfo = new RushDeliveryInfo(rushDeliveryInfo, rushDeliveryInfoMap.get("DeliveryTime"), rushDeliveryInfoMap.get("DeliveryInstruction"));
            this.combinedDeliveryFee = deliveryFee[0] + deliveryFee[1];
            Order order = new Order(cartMediaList, combinedDeliveryFee, rushDeliveryInfo);
            Invoice invoice = new Invoice(order);
            new InvoiceScreenController(currentScene, PathConfig.INVOICE_SCREEN_PATH, invoice);
        } catch (Exception e) {
            Utils.showAlert("Invalid Delivery Instruction", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deliveryTime.getItems().addAll(Arrays.asList("8.00 AM - 10.00 AM", "10.00 AM - 12.00 PM", "12.00 PM - 2.00 PM", "2.00 PM - 4.00 PM", "4.00 PM - 6.00 PM", "6.00 PM - 8.00 PM", "8.00 PM - 10.00 PM"));
        deliveryTime.getSelectionModel().selectFirst();
        rushFlowpane.setVgap(40);
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }

    @Override
    public <T> void initData(T ...data) {
        PlaceOrderController placeOrderController = (PlaceOrderController) data[0];

        this.cartMediaList = placeOrderController.getCartMediaList();

        this.rushDeliveryInfo = placeOrderController.getDeliveryInfo();

        this.rushOrderController = new RushOrderController(placeOrderController);

        this.deliveryFee = rushOrderController.calculateRushShippingFee();

        for(CartMedia cartMedia : cartMediaList) {

            // product image
            Image image = ImagePath.getMediaImage(cartMedia.getMedia());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(173);
            imageView.setPreserveRatio(true);
            HBox mediaImg = new HBox(imageView);
            mediaImg.setAlignment(Pos.CENTER);

            // unit price
            HBox mediaUnitPrice = new HBox(new Text(String.valueOf(cartMedia.getMedia().getPrice())));
            mediaUnitPrice.setPrefWidth(139);
            mediaUnitPrice.setAlignment(Pos.CENTER);

            // total price
            Text totalPriceText = new Text(String.valueOf(cartMedia.getMedia().getPrice() * cartMedia.getQuantity()));
            HBox mediaTotalPrice = new HBox(totalPriceText);
            mediaTotalPrice.setPrefWidth(139);
            mediaTotalPrice.setAlignment(Pos.CENTER);

            // quantity
            HBox mediaQuantity = new HBox(new Text(String.valueOf(cartMedia.getQuantity())));
            mediaQuantity.setPrefWidth(49);
            mediaQuantity.setAlignment(Pos.CENTER);


//            CheckBox checkBox = new CheckBox();
//            if(!cartMedia.getMedia().isRushOrderAvailable()) checkBox.setDisable(true);
//            else checkBox.setSelected(true);
//            // if clicked toggle check
//            checkBox.setOnMouseClicked(mouseEvent -> {
//                // TODO toggle then media is delivered rush else ...
//
//            });
//            HBox checkBoxContainer = new HBox(checkBox);
//            checkBoxContainer.setAlignment(Pos.CENTER);
//            checkBoxContainer.setPrefWidth(31);

            Image statusIcon;
            if(!cartMedia.getMedia().isRushOrderAvailable()) {
                statusIcon = new Image(getClass().getResourceAsStream(PathConfig.SUCCESS_ICON));
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

        nameTxt.setText(rushDeliveryInfo.getName());
        phoneTxt.setText(rushDeliveryInfo.getPhone());
        provinceTxt.setText(rushDeliveryInfo.getProvince());
        emailTxt.setText(rushDeliveryInfo.getEmail());
        addressTxt.setText(rushDeliveryInfo.getAddress());

        subtotal.setText(String.valueOf(rushOrderController.placeOrderController.calculateSubtotal()));
        deliveryFeeTxt.setText(String.valueOf(deliveryFee[0]));
        rushDeliveryFeeTxt.setText(String.valueOf(deliveryFee[1]));
    }

    @FXML
    void onDeliveryMethodChange (ActionEvent e) {
        deliveryFee = rushOrderController.calculateRushShippingFee();

        deliveryFeeTxt.setText(String.valueOf(deliveryFee[0]));
        rushDeliveryFeeTxt.setText(String.valueOf(deliveryFee[1]));
    }
}
