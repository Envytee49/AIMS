package com.example.aims.view.user.productmanager;

import com.example.aims.controller.CRUDMediaController;
import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;
import com.example.aims.utils.ImageBrowser;
import com.example.aims.utils.ImageSaver;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Date;


public class CreateMediaScreenController extends BaseScreenController implements GoBack {

    @FXML
    private TextField titleTextField, valueTextField, priceTextField, quantityTextField, weightTextField;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private CheckBox rushOrderCheckBox;
    @FXML
    private VBox categorySpecificFields;

    private TextField authorTextField, coverTypeTextField, genreTextField, languageTextField, numberOfPagesTextField, publisherTextField;
    private DatePicker publishDatePicker;
    private TextField artistTextField, musicTypeTextField, recordLabelTextField;
    private DatePicker releaseDatePicker;
    private TextField directorTextField, discTypeTextField, genreDVDTextField, studioTextField, subtitlesTextField, languageDVDTextField, runtimeTextField;

    private CRUDMediaController crudMediaController;
    private FileChooser fileChooser;
    @FXML
    private ImageView mediaImage;
    @FXML
    private Button uploadButton;
    private boolean imageChanged;

    private File file;
    public CreateMediaScreenController() {
    }

    public <T> CreateMediaScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }


    @Override
    public <T> void initData(T... data) {
        this.crudMediaController = (CRUDMediaController) data[0];
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        uploadButton.setOnAction(e -> {
            this.file = ImageBrowser.uploadImage((Stage) currentScene.getWindow(), fileChooser, mediaImage);
            this.imageChanged = true;
        });
        categoryChoiceBox.getItems().addAll("Book", "CD", "DVD");
        categoryChoiceBox.setOnAction(event -> updateCategorySpecificFields());

    }

    private void updateCategorySpecificFields() {
        categorySpecificFields.getChildren().clear();
        String selectedCategory = categoryChoiceBox.getValue();

        switch (selectedCategory) {
            case "Book":
                addBookFields();
                break;
            case "CD":
                addCDFields();
                break;
            case "DVD":
                addDVDFields();
                break;
        }
    }

    private void addBookFields() {
        authorTextField = new TextField();
        coverTypeTextField = new TextField();
        genreTextField = new TextField();
        languageTextField = new TextField();
        numberOfPagesTextField = new TextField();
        publisherTextField = new TextField();
        publishDatePicker = new DatePicker();

        categorySpecificFields.getChildren().addAll(
                createFieldRow("Author", authorTextField),
                createFieldRow("Cover type", coverTypeTextField),
                createFieldRow("Genre", genreTextField),
                createFieldRow("Language", languageTextField),
                createFieldRow("Number of pages", numberOfPagesTextField),
                createFieldRow("Publisher", publisherTextField),
                createFieldRow("Publish date", publishDatePicker)
        );
    }

    private void addCDFields() {
        artistTextField = new TextField();
        musicTypeTextField = new TextField();
        recordLabelTextField = new TextField();
        releaseDatePicker = new DatePicker();

        categorySpecificFields.getChildren().addAll(
                createFieldRow("Artist", artistTextField),
                createFieldRow("Music type", musicTypeTextField),
                createFieldRow("Record label", recordLabelTextField),
                createFieldRow("Released date", releaseDatePicker)
        );
    }

    private void addDVDFields() {
        directorTextField = new TextField();
        discTypeTextField = new TextField();
        genreDVDTextField = new TextField();
        studioTextField = new TextField();
        subtitlesTextField = new TextField();
        languageDVDTextField = new TextField();
        runtimeTextField = new TextField();
        releaseDatePicker = new DatePicker();

        categorySpecificFields.getChildren().addAll(
                createFieldRow("Director", directorTextField),
                createFieldRow("Disc type", discTypeTextField),
                createFieldRow("Genre", genreDVDTextField),
                createFieldRow("Studio", studioTextField),
                createFieldRow("Subtitles", subtitlesTextField),
                createFieldRow("Language", languageDVDTextField),
                createFieldRow("Runtime", runtimeTextField),
                createFieldRow("Released date", releaseDatePicker)
        );
    }

    private HBox createFieldRow(String labelText, Control field) {
        Label label = new Label(labelText + ":");
        HBox hBox = new HBox(10, label, field);
        hBox.setAlignment(Pos.CENTER_LEFT);
        return hBox;
    }

    @FXML
    private void handleConfirmAction() {
        // Retrieve values from all fields
        String title = titleTextField.getText();
        int value = Integer.parseInt(valueTextField.getText());
        int price = Integer.parseInt(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());
        double weight = Double.parseDouble(weightTextField.getText());
        String category = categoryChoiceBox.getValue();
        boolean rushOrderAvailable = rushOrderCheckBox.isSelected();

        Media media =
                Media.builder()
                        .title(title)
                        .value(value)
                        .price(price)
                        .quantity(quantity)
                        .weight(weight)
                        .category(category)
                        .rushOrderAvailable(rushOrderAvailable)
                        .build();
        String fileName = this.file.getName();
        // Depending on the selected category, get values from specific fields
        switch (category) {
            case "Book":
                String author = authorTextField.getText();
                String coverType = coverTypeTextField.getText();
                String genre = genreTextField.getText();
                String language = languageTextField.getText();
                int numberOfPages = Integer.parseInt(numberOfPagesTextField.getText());
                String publisher = publisherTextField.getText();
                Date publishDate = Date.valueOf(publishDatePicker.getValue());
                Book book = new Book(media);
                book.setAuthor(author);
                book.setCoverType(coverType);
                book.setGenre(genre);
                book.setLanguage(language);
                book.setPublisher(publisher);
                book.setNumOfPages(numberOfPages);
                book.setPublishDate(publishDate);
                book.setImageURL("/com/example/aims/assets/book/" + fileName);
                crudMediaController.addMedia(book);
                break;
            case "CD":
                String artist = artistTextField.getText();
                String musicType = musicTypeTextField.getText();
                String recordLabel = recordLabelTextField.getText();
                Date releaseDate = Date.valueOf(releaseDatePicker.getValue());

                CD cd = new CD(media);
                cd.setArtist(artist);
                cd.setMusicType(musicType);
                cd.setRecordLabel(recordLabel);
                cd.setReleasedDate(releaseDate);
                cd.setImageURL("/com/example/aims/assets/cd/" + fileName);
                crudMediaController.addMedia(cd);
                break;
            case "DVD":
                String director = directorTextField.getText();
                String discType = discTypeTextField.getText();
                String genreDVD = genreDVDTextField.getText();
                String studio = studioTextField.getText();
                String subtitles = subtitlesTextField.getText();
                String languageDVD = languageDVDTextField.getText();
                int runtime = Integer.parseInt(runtimeTextField.getText());
                Date releasedDate = Date.valueOf(releaseDatePicker.getValue());

                DVD dvd = new DVD(media);
                dvd.setDirector(director);
                dvd.setDiscType(discType);
                dvd.setGenre(genreDVD);
                dvd.setStudio(studio);
                dvd.setLanguage(languageDVD);
                dvd.setRuntime(runtime);
                dvd.setSubtitles(subtitles);
                dvd.setReleasedDate(releasedDate);
                dvd.setImageURL("/com/example/aims/assets/dvd/"+fileName);
                crudMediaController.addMedia(dvd);
                break;
        }
        if (imageChanged) {
            ImageSaver.saveImageToResources(this.file, category);
        }

        Utils.showAlert("Create Media", "Create media successfully", Alert.AlertType.INFORMATION);
    }

    @FXML
    void goBack (ActionEvent event) {
        goBack(event, prevScene);
    }
}
