package com.example.aims.view.delivery;

import com.example.aims.controller.PlaceOrderController;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.Order;
import com.example.aims.exception.RuntimeException;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.GoBack;
import javafx.scene.control.Alert;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.payment.InvoiceScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public class DeliveryFormScreenController extends BaseScreenController implements GoBack {

    @FXML
    private TextField addressInput;

    @FXML
    private Button backBtn;

    @FXML
    private FlowPane deliveryFlowpane;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private ChoiceBox<String> provinceInput;

    @FXML
    private Text subtotal;

    @FXML
    private Text deliveryFeeTxt;

    @FXML
    private CheckBox rushOrderCheckbox;

    private List<CartMedia> cartMediaList;

    private int deliveryFee;

    private PlaceOrderController placeOrderController;

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }

    public DeliveryFormScreenController() {
    }

    public <T> DeliveryFormScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @FXML
    void confirm(ActionEvent event) {
        String name = nameInput.getText();
        String phone = phoneInput.getText();
        String address = addressInput.getText();
        String email = emailInput.getText();
        String province = provinceInput.getValue();

        HashMap<String, String> info = new HashMap();
        info.put("name", name);
        info.put("phone", phone);
        info.put("address", address);
        info.put("email", email);
        info.put("province", province);

        try {
            placeOrderController.processDeliveryInfo(info);
            if (rushOrderCheckbox.isSelected()) {
                if (placeOrderController.checkRushOrderAddress()) {
                    new RushOrderScreenController(currentScene, PathConfig.RUSH_ORDER_SCREEN_PATH, placeOrderController);
                } else {
                    Utils.showAlert("Invalid province/city", "Rush Order only exist in Hanoi", Alert.AlertType.ERROR);
                }
            } else {
                Order order = new Order(cartMediaList, deliveryFee, placeOrderController.getDeliveryInfo());
                Invoice invoice = new Invoice(order);
                new InvoiceScreenController(currentScene, PathConfig.INVOICE_SCREEN_PATH, invoice);
            }
        } catch (RuntimeException e) {
            Utils.showAlert("Invalid Information", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public <T> void initData(T... data) {
        cartMediaList = (List<CartMedia>) data[0];

        this.placeOrderController = new PlaceOrderController(cartMediaList);

        provinceInput.getItems().addAll(
                "An Giang", "Bà Rịa-Vũng Tàu", "Bạc Liêu", "Bắc Kạn", "Bắc Giang", "Bắc Ninh",
                "Bến Tre", "Bình Dương", "Bình Định", "Bình Phước", "Bình Thuận", "Cà Mau",
                "Cao Bằng", "Cần Thơ (TP)", "Đà Nẵng (TP)", "Đắk Lắk", "Đắk Nông", "Điện Biên",
                "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội (TP)",
                "Hà Tây", "Hà Tĩnh", "Hải Dương", "Hải Phòng (TP)", "Hòa Bình", "Hồ Chí Minh (TP)",
                "Hậu Giang", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu",
                "Lào Cai", "Lạng Sơn", "Lâm Đồng", "Long An", "Nam Định", "Nghệ An",
                "Ninh Bình", "Ninh Thuận", "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam",
                "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh",
                "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên - Huế", "Tiền Giang",
                "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
        );

        // Optionally set a default value
        provinceInput.setValue("Hà Nội (TP)");

        this.deliveryFee = placeOrderController.calculateShippingFee(provinceInput.getValue());

        for (CartMedia cartMediaItem : cartMediaList) {
            // product image
            Image image = ImagePath.getMediaImage(cartMediaItem.getMedia());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(167);
            imageView.setPreserveRatio(true);
            HBox mediaImg = new HBox(imageView);
            mediaImg.setAlignment(Pos.CENTER);

            // unit price
            HBox mediaUnitPrice = new HBox(new Text(String.valueOf(cartMediaItem.getMedia().getPrice())));
            mediaUnitPrice.setPrefWidth(167);
            mediaUnitPrice.setAlignment(Pos.CENTER);

            // quantity
            HBox mediaQuantity = new HBox(new Text(String.valueOf(cartMediaItem.getQuantity())));
            mediaQuantity.setPrefWidth(69);
            mediaQuantity.setAlignment(Pos.CENTER);

            // total price
            Text totalPriceText = new Text(String.valueOf(cartMediaItem.getMedia().getPrice() * cartMediaItem.getQuantity()));
            HBox mediaTotalPrice = new HBox(totalPriceText);
            mediaTotalPrice.setPrefWidth(167);
            mediaTotalPrice.setAlignment(Pos.CENTER);

            HBox mediaBar = new HBox(mediaImg, mediaUnitPrice, mediaQuantity, mediaTotalPrice);

            deliveryFlowpane.getChildren().add(mediaBar);

            subtotal.setText(String.valueOf(placeOrderController.calculateSubtotal()));
            deliveryFeeTxt.setText(String.valueOf(deliveryFee));
        }
    }

    @FXML
    void onProvinceChange (ActionEvent e) {
        this.deliveryFee = placeOrderController.calculateShippingFee(provinceInput.getValue());

        deliveryFeeTxt.setText(String.valueOf(deliveryFee));
    }
}
