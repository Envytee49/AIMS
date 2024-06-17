package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.media.Book;
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

public class BookScreenController extends BaseScreenController implements AddToCart {
    @FXML
    private Text author;

    @FXML
    private Text category;

    @FXML
    private Text coverType;

    @FXML
    private Text genre;

    @FXML
    private Text language;

    @FXML
    private Text numOfPages;

    @FXML
    private Text price;

    @FXML
    private Text publishDate;

    @FXML
    private Text publisher;

    @FXML
    private ImageView bookImage;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Text title;
    private Media media;
    private Cart cart = Cart.getCart();
    private MediaRepository mediaRepository;
    private CartController cartController;
    public BookScreenController() {

    }

    public BookScreenController(Scene currentScene, String currentScreenPath, int id, CartController cartController) {
        super(currentScene, currentScreenPath, id, cartController);
    }

    private void init() {
        mediaRepository = new MediaRepositoryImpl();
    }

    @Override
    public <T> void initData(T... data) {
        // init repo
        init();
        this.cartController = (CartController) data[1];
        Book book = mediaRepository.getBookById((Integer) data[0]);
        this.media = book;
        author.setText(book.getAuthor());
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory
                        .IntegerSpinnerValueFactory(1 , 10, 1);
        quantitySpinner.setValueFactory(valueFactory);
        category.setText(book.getCategory());
        coverType.setText(book.getCoverType());
        genre.setText(book.getGenre());
        language.setText(book.getLanguage());
        title.setText(book.getTitle());
        numOfPages.setText(String.valueOf(book.getNumOfPages()));
        price.setText(book.getPrice() + " VND");
        publishDate.setText(String.valueOf(book.getPublishDate()));
        publisher.setText(book.getPublisher());
        bookImage.setImage(new Image(getClass().getResourceAsStream(book.getImageURL())));
    }

    @FXML
    void addToCart(ActionEvent event) {
        CartMedia cartMedia = new CartMedia(media, quantitySpinner.getValue(), media.getPrice());
        addToCart(cartController, cartMedia);
    }


}
