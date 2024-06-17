package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;
import com.example.aims.repository.media.MediaRepository;
import com.example.aims.repository.media.MediaRepositoryImpl;
import com.example.aims.view.BaseScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DVDScreenController extends BaseScreenController implements AddToCart {
    @FXML
    private Text category;

    @FXML
    private Text director;

    @FXML
    private Text discType;

    @FXML
    private ImageView dvdImage;

    @FXML
    private Text genre;

    @FXML
    private Text language;

    @FXML
    private Text price;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Text releaseDate;

    @FXML
    private Text runtime;

    @FXML
    private Text studio;

    @FXML
    private Text subtitles;

    @FXML
    private Text title;

    private Media media;
    private MediaRepository mediaRepository;
    private CartController cartController;
    public DVDScreenController() {
    }
    public DVDScreenController(Scene currentScene, String currentScreenPath, int id, CartController cartController) {
        super(currentScene, currentScreenPath, id, cartController);
    }
    private void initRepo() {
        mediaRepository = new MediaRepositoryImpl();
    }
    @Override
    public <T> void initData(T... data) {
        initRepo();
        this.cartController = (CartController) data[1];
        DVD dvd = mediaRepository.getDVDById((Integer) data[0]);
        this.media = dvd;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory
                        .IntegerSpinnerValueFactory(1 , 10, 1);
        quantitySpinner.setValueFactory(valueFactory);
        price.setText(dvd.getPrice() +" VND");
        category.setText(dvd.getCategory());
        director.setText(dvd.getDirector());
        discType.setText(dvd.getDiscType());
        genre.setText(dvd.getGenre());
        language.setText(dvd.getLanguage());
        runtime.setText(String.valueOf(dvd.getRuntime()));
        studio.setText(dvd.getStudio());
        subtitles.setText(dvd.getSubtitles());
        releaseDate.setText(String.valueOf(dvd.getReleasedDate()));
        title.setText(dvd.getTitle());
        dvdImage.setImage(new Image(getClass().getResourceAsStream(dvd.getImageURL())));
    }

    @FXML
    void addToCart(ActionEvent event) {
        CartMedia cartMedia = new CartMedia(media, quantitySpinner.getValue(), media.getPrice());
        addToCart(cartController, cartMedia);
    }


}
