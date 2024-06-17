package com.example.aims.view.payment;

import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.OrderMedia;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.List;

public class InvoiceScreenController extends BaseScreenController implements GoBack {

    @FXML
    private Text deliveryFee;

    @FXML
    private FlowPane invoiceFlowPane;

    @FXML
    private Text subtotal;

    @FXML
    private Text subtotalWithVAT;

    @FXML
    private Text totalAmount;


    private Invoice invoice;

    public <T> InvoiceScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    public InvoiceScreenController() {
    }

    @Override
    public <T> void initData(T... data) {
        this.invoice = (Invoice) data[0];

        this.deliveryFee.setText(String.valueOf(invoice.getDeliveryFee()));
        this.subtotal.setText(String.valueOf(invoice.getSubtotal()));
        this.subtotalWithVAT.setText(String.valueOf(invoice.getSubtotalWithVAT()));
        this.totalAmount.setText(String.valueOf(invoice.getTotalAmount()));
        // Flowpane here
        List<OrderMedia> orderMediaList = invoice.getMedias();
        for(OrderMedia orderMedia : orderMediaList) {

            // product image
            Image image = ImagePath.getMediaImage(orderMedia.getMedia());
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(170);
            imageView.setPreserveRatio(true);
            HBox mediaImg = new HBox(imageView);
            mediaImg.setAlignment(Pos.CENTER);

            // unit price
            HBox mediaUnitPrice = new HBox(new Text(String.valueOf(orderMedia.getMedia().getPrice())));
            mediaUnitPrice.setPrefWidth(170);
            mediaUnitPrice.setAlignment(Pos.CENTER);

            // total price
            Text totalPriceText = new Text(String.valueOf(orderMedia.getMedia().getPrice()
                    * orderMedia.getQuantity()));
            HBox mediaTotalPrice = new HBox(totalPriceText);
            mediaTotalPrice.setPrefWidth(170);
            mediaTotalPrice.setAlignment(Pos.CENTER);

            // quantity
            HBox mediaQuantity = new HBox(new Text(String.valueOf(orderMedia.getQuantity())));
            mediaQuantity.setPrefWidth(57);
            mediaQuantity.setAlignment(Pos.CENTER);

            HBox mediaBar = new HBox(mediaImg, mediaUnitPrice, mediaQuantity, mediaTotalPrice);

            invoiceFlowPane.getChildren().add(mediaBar);

        }
    }

    @FXML
    void confirm(ActionEvent event) {
        new PaymentMethodScreenController(currentScene, PathConfig.PAYMENT_METHOD_SCREEN_PATH, invoice);
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }
}
