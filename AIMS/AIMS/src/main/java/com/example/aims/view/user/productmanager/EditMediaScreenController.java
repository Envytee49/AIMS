package com.example.aims.view.user.productmanager;

import com.example.aims.controller.CRUDMediaController;
import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;
import com.example.aims.utils.ImageBrowser;
import com.example.aims.utils.ImageSaver;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;

public class EditMediaScreenController extends BaseScreenController implements GoBack {

    @FXML
    private TextField titleTextField, valueTextField, priceTextField, quantityTextField, weightTextField;
    @FXML
    private Text categoryTxt;
    @FXML
    private CheckBox rushOrderCheckBox;
    @FXML
    private VBox categorySpecificFields;

    @FXML
    private ImageView mediaImage;

    @FXML
    private Button uploadButton;

    private TextField authorTextField, coverTypeTextField,
            genreTextField, languageTextField,
            numberOfPagesTextField, publisherTextField;
    private DatePicker publishDatePicker;
    private TextField artistTextField, musicTypeTextField, recordLabelTextField;
    private DatePicker releaseDatePicker;
    private TextField directorTextField, discTypeTextField, genreDVDTextField,
            studioTextField, subtitlesTextField, languageDVDTextField, runtimeTextField;

    private CRUDMediaController crudMediaController;
    private UpdateMediaScreenController updateMediaScreenController;
    private Media media;

    private FileChooser fileChooser;


    private boolean imageChanged;

    private File file;

    public EditMediaScreenController() {
    }

    public <T> EditMediaScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    private Image getMediaImage() {
        try {
            File initialFile = new File("AIMS/src/main/resources" + this.media.getImageURL());
            InputStream targetStream;
            targetStream = new FileInputStream(initialFile);
            return new Image(targetStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public <T> void initData(T... data) {
        this.media = (Media) data[0];
        this.crudMediaController = (CRUDMediaController) data[1];
        this.updateMediaScreenController = (UpdateMediaScreenController) data[2];

        mediaImage.setImage(getMediaImage());

        if (media instanceof Book) {
            addBookFields();
        } else if (media instanceof CD) {
            addCDFields();
        } else {
            addDVDFields();
        }
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        uploadButton.setOnAction(e -> {
            this.file = ImageBrowser.uploadImage((Stage) currentScene.getWindow(), fileChooser, mediaImage);
            this.imageChanged = true;
        });
        ;

        titleTextField.setText(media.getTitle());
        priceTextField.setText(String.valueOf(media.getPrice()));
        valueTextField.setText(String.valueOf(media.getValue()));
        quantityTextField.setText(String.valueOf(media.getQuantity()));
        weightTextField.setText(String.valueOf(media.getWeight()));
        categoryTxt.setText(media.getCategory());
        rushOrderCheckBox.setSelected(media.isRushOrderAvailable());
    }

    private void addBookFields() {
        Book book = (Book) media;

        authorTextField = new TextField(book.getAuthor());
        coverTypeTextField = new TextField(book.getCoverType());
        genreTextField = new TextField(book.getGenre());
        languageTextField = new TextField(book.getLanguage());
        numberOfPagesTextField = new TextField(String.valueOf(book.getNumOfPages()));
        publisherTextField = new TextField(book.getPublisher());
        publishDatePicker = new DatePicker(book.getPublishDate().toLocalDate());

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
        CD cd = (CD) media;

        artistTextField = new TextField(cd.getArtist());
        musicTypeTextField = new TextField(cd.getMusicType());
        recordLabelTextField = new TextField(cd.getRecordLabel());
        releaseDatePicker = new DatePicker(cd.getReleasedDate().toLocalDate());

        categorySpecificFields.getChildren().addAll(
                createFieldRow("Artist", artistTextField),
                createFieldRow("Music type", musicTypeTextField),
                createFieldRow("Record label", recordLabelTextField),
                createFieldRow("Released date", releaseDatePicker)
        );
    }

    private void addDVDFields() {
        DVD dvd = (DVD) media;

        directorTextField = new TextField(dvd.getDirector());
        discTypeTextField = new TextField(dvd.getDiscType());
        genreDVDTextField = new TextField(dvd.getGenre());
        studioTextField = new TextField(dvd.getStudio());
        subtitlesTextField = new TextField(dvd.getSubtitles());
        languageDVDTextField = new TextField(dvd.getLanguage());
        runtimeTextField = new TextField(String.valueOf(dvd.getRuntime()));
        releaseDatePicker = new DatePicker(dvd.getReleasedDate().toLocalDate());

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
    private void confirmEdit() {
        // Retrieve values from all fields
        String title = titleTextField.getText();
        int value = Integer.parseInt(valueTextField.getText());
        int price = Integer.parseInt(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());
        double weight = Double.parseDouble(weightTextField.getText());
        String category = categoryTxt.getText();
        boolean rushOrderAvailable = rushOrderCheckBox.isSelected();

        Media media =
                Media.builder()
                        .id(this.media.getId())
                        .title(title)
                        .value(value)
                        .price(price)
                        .quantity(quantity)
                        .weight(weight)
                        .category(category)
                        .rushOrderAvailable(rushOrderAvailable)
                        .build();
        String fileName = this.file == null ? this.media.getId() + ".jpg" : this.file.getName();
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
                crudMediaController.updateMedia(book);
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
                crudMediaController.updateMedia(cd);
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
                dvd.setImageURL("/com/example/aims/assets/dvd/" + fileName);
                crudMediaController.updateMedia(dvd);
                break;
        }
        if (imageChanged) {
            ImageSaver.saveImageToResources(this.file, category);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        goBack(event, prevScene);
    }

}

