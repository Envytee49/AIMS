package com.example.aims.view.cart;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.delivery.DeliveryFormScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class CartScreenController extends BaseScreenController {
    @FXML
    private FlowPane cartFlowpane;

    @FXML
    private Text totalAmountText;

    private CartController cartController;
    private Cart cart;
    public CartScreenController() {
    }

    public <T> CartScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @FXML
    protected void requestToPlaceOrder(ActionEvent event) throws IOException {
        if(cartController.getListCartMedia().size() == 0) {
            Utils.showAlert("Cart", "Cart is empty", Alert.AlertType.WARNING);
            return;
        }
        List<CartMedia> cartMediaList = cartController.requestToPlaceOder();
        if(cartMediaList != null) {
            new DeliveryFormScreenController(currentScene, PathConfig.DELIVERY_FORM_SCREEN_PATH, cartMediaList);
        }else {
            Utils.showAlert("Cart", "Please choose 1 media", Alert.AlertType.WARNING);
        }
    }


    @Override
    public <T> void initData(T... data) {
        // To do
        cartFlowpane.setVgap(10);
        cartController = new CartController();
        this.cart = cartController.getCart();
        List<CartMedia> cartMedia = cart.getListCartMedia();
        for(CartMedia cartMediaItem : cartMedia) {

            // product image
            Image image = ImagePath.getMediaImage(cartMediaItem.getMedia());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(191);
            imageView.setPreserveRatio(true);
            HBox mediaImg = new HBox(imageView);
            mediaImg.setAlignment(Pos.CENTER);

            // unit price
            HBox mediaUnitPrice = new HBox(new Text(String.valueOf(cartMediaItem.getMedia().getPrice())));
            mediaUnitPrice.setPrefWidth(156);
            mediaUnitPrice.setAlignment(Pos.CENTER);

            // total price
            Text totalPriceText = new Text(String.valueOf(cartMediaItem.getMedia().getPrice()
                    * cartMediaItem.getQuantity()));
            HBox mediaTotalPrice = new HBox(totalPriceText);
            mediaTotalPrice.setPrefWidth(156);
            mediaTotalPrice.setAlignment(Pos.CENTER);

            // spinner - quantity
            Spinner<Integer> spinner = new Spinner<>();
            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
                            cartMediaItem.getMedia().getQuantity(),
                            cartMediaItem.getQuantity());
            spinner.setValueFactory(valueFactory);
            spinner.setPrefWidth(80);
            spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                totalPriceText.setText(String.valueOf(cartMediaItem.getMedia().getPrice()
                        * newValue));
                cartController.updateQuantityOfMedia(cartMediaItem, newValue);
                totalAmountText.setText(cartController.getAmount() + " VND");
            });
            HBox mediaQuantity = new HBox(spinner);
            mediaQuantity.setPrefWidth(103);
            mediaQuantity.setAlignment(Pos.CENTER);


            // delete button
            Button deleteButton = new Button("X");
            HBox deleteBtnContainer = new HBox(deleteButton);
            deleteBtnContainer.setAlignment(Pos.CENTER);
            deleteBtnContainer.setPrefWidth(51);

            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(true);
            // if clicked toggle check
            checkBox.setOnMouseClicked(mouseEvent -> {
                cartController.updateMediaCheck(cartMediaItem);
                totalAmountText.setText(cartController.getAmount() + " VND");
            });
            HBox checkBoxContainer = new HBox(checkBox);
            checkBoxContainer.setAlignment(Pos.CENTER);
            checkBoxContainer.setPrefWidth(23);

            HBox mediaBar = new HBox(checkBoxContainer, mediaImg, mediaUnitPrice, mediaQuantity, mediaTotalPrice, deleteBtnContainer);

            // if delete - remove from cart -> refresh money -> remove from pane
            deleteButton.setOnMouseClicked(mouseEvent -> {
                cartController.removeMediaFromCart(cartMediaItem);
                totalAmountText.setText(cartController.getAmount() + " VND");
                cartFlowpane.getChildren().remove(mediaBar);
            });
            cartFlowpane.getChildren().add(mediaBar);

        }

        totalAmountText.setText(String.valueOf(cartController.getAmount()) + " VND");
    }
}
