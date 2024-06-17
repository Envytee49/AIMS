package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.media.Media;
import com.example.aims.repository.media.MediaRepository;
import com.example.aims.repository.media.MediaRepositoryImpl;
import com.example.aims.response.MediaResponse;
import com.example.aims.utils.ImagePath;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class HomeScreenController extends BaseScreenController implements AddToCart {
    @FXML
    private StackPane homeStackpane;
    @FXML
    private ScrollPane homeScrollpane;
    private CartController cartController;
    private Cart cart;
    private MediaRepository mediaRepository;

    public HomeScreenController() {
    }

    public <T> HomeScreenController(Scene currentScene, String currentPath, T... data) {
        super(currentScene, currentPath, data);
    }

    private void setmediaOnMouseClick(ImageView imageView, Media media) {
        if (media.getCategory().equals("Book")) {
            imageView.setOnMouseClicked(event -> new BookScreenController(currentScene, PathConfig.BOOK_PATH, media.getId(), cartController));
        } else if (media.getCategory().equals("CD")) {
            imageView.setOnMouseClicked(event -> new CDScreenController(currentScene, PathConfig.CD_PATH, media.getId(), cartController));
        } else {
            imageView.setOnMouseClicked(event -> new DVDScreenController(currentScene, PathConfig.DVD_PATH, media.getId(), cartController));
        }
    }

    private void init() {
        mediaRepository = new MediaRepositoryImpl();
        cartController = new CartController();
        cart = cartController.getCart();
    }
    private void smoothScroll(ScrollPane scrollPane) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(scrollPane.vvalueProperty(), 0.0, Interpolator.EASE_BOTH)
                )
        );
        timeline.play();
    }
    @Override
    public <T> void initData(T... data) {
        // To init repo
        init();

        // parameters
        String query = (String) data[0];
        String priceFilter = (String) data[1];
        // set paginate
        Pagination pagination = new Pagination();
        pagination.setStyle("-fx-background-color: #fff");


        pagination.setPageFactory(integer -> {
        pagination.setMinWidth(800);

            smoothScroll(homeScrollpane);

            FlowPane homeFlowpane = new FlowPane();
            homeFlowpane.setStyle("-fx-background-color: #fff");
            homeFlowpane.setPrefWrapLength(800);
            homeFlowpane.setHgap(20);
            homeFlowpane.setVgap(20);
            homeFlowpane.setAlignment(Pos.TOP_CENTER);
            homeFlowpane.setRowValignment(VPos.BASELINE);
            homeFlowpane.setPadding(new Insets(50,0,50,0));
            MediaResponse mediaResponse = mediaRepository.getMediaListByFilter(query, priceFilter, integer);
            int mediaCount = mediaResponse.getMediaCount();
            pagination.setPageCount((int) Math.ceil(mediaCount / 20.0));

            List<Media> mediaList = mediaResponse.getMediaList();

            // create screen
            for (int i = 0; i < mediaList.size(); i++) {
                Media media = mediaList.get(i);
                ImageView mediaImage = new ImageView(ImagePath.getMediaImage(media));
                mediaImage.setPreserveRatio(true);
                mediaImage.setFitWidth(200);
                mediaImage.setId("media-image");

                Button addToCartBtn = new Button("Add To Cart".toUpperCase());
                addToCartBtn.setId("addToCartBtn");

                // Add to cart

                Spinner<Integer> spinner = new Spinner<>();
                SpinnerValueFactory<Integer> valueFactory =
                        new SpinnerValueFactory
                                .IntegerSpinnerValueFactory(1, 10, 1);
                spinner.setValueFactory(valueFactory);
                spinner.setId("spinner");

                addToCartBtn.setOnMouseClicked(mouseEvent -> {
                    // TO DO
                    CartMedia cartMedia = new CartMedia(media, spinner.getValue(), media.getPrice());
                    addToCart(cartController, cartMedia);
                });
                Label title = new Label(media.getTitle());
                title.setId("title");
                title.setWrapText(true);

                Label price = new Label(media.getPrice() + " VND");
                price.setId("price");
                VBox vBox = new VBox();
                vBox.setMaxHeight(300);
                vBox.getChildren().addAll(mediaImage, title, price, spinner, addToCartBtn);
                setmediaOnMouseClick(mediaImage, media);

                // Set flow-pane
                homeFlowpane.getChildren().add(vBox);
            }

            return homeFlowpane;
        });
        // Button to scroll on top
        Button scrollToTopBtn = new Button("↑".toUpperCase());
        scrollToTopBtn.setId("scroll-to-top-btn");
        scrollToTopBtn.setVisible(false);
        scrollToTopBtn.setOnMouseClicked(mouseEvent -> {
            smoothScroll(homeScrollpane);
        });

        homeStackpane.getChildren().add(scrollToTopBtn);
        StackPane.setAlignment(scrollToTopBtn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(scrollToTopBtn, new Insets(40));

        // If current vertical length is > half of scroll pane
        homeScrollpane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            // Adjust threshold as needed
            scrollToTopBtn.setVisible(newValue.doubleValue() > 0.5);
        });

        AnchorPane anchorPane = new AnchorPane(pagination);

        homeScrollpane.setContent(anchorPane);
    }


}



