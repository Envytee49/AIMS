package com.example.aims.view.user.productmanager;

import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;
import com.example.aims.view.BaseScreenController;
import com.example.aims.view.GoBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MediaDetailScreenController extends BaseScreenController implements GoBack {

    @FXML
    private Text category;

    @FXML
    private Text price;

    @FXML
    private ImageView mediaImage;

    @FXML
    private Text quantity;

    @FXML
    private Text title;

    @FXML
    private VBox infoBox;

    private Media media;

    public MediaDetailScreenController() {

    }

    public <T> MediaDetailScreenController (Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    @Override
    public <T> void initData(T... data) {
        this.media = (Media) data[0];

        HashMap<String, String> mediaData = new HashMap();

        if (media instanceof Book) {
            Book book = (Book) media;
            mediaData.put("Author: ", book.getAuthor());
            mediaData.put("Cover type: ", book.getCoverType());
            mediaData.put("Genre: ", book.getGenre());
            mediaData.put("Language: ", book.getLanguage());
            mediaData.put("Number of pages: ", String.valueOf(book.getNumOfPages()));
            mediaData.put("Publish date: ", String.valueOf(book.getPublishDate()));
            mediaData.put("Publisher: ", book.getPublisher());
        } else if (media instanceof CD) {
            CD cd = (CD) media;
            mediaData.put("Artist: ", cd.getArtist());
            mediaData.put("Music type: ", cd.getMusicType());
            mediaData.put("Record Label: ", cd.getRecordLabel());
            mediaData.put("Released date: ", String.valueOf(cd.getReleasedDate()));
        } else {
            DVD dvd = (DVD) media;
            mediaData.put("Director: ", dvd.getDirector());
            mediaData.put("Disc type: ", dvd.getDiscType());
            mediaData.put("Genre: ", dvd.getGenre());
            mediaData.put("Studio: ", dvd.getStudio());
            mediaData.put("Subtitles: ", dvd.getSubtitles());
            mediaData.put("Language: ", dvd.getLanguage());
            mediaData.put("Runtime: ", String.valueOf(dvd.getRuntime()));
            mediaData.put("Released date: ", String.valueOf(dvd.getReleasedDate()));
        }

        HBox gap = new HBox();
        gap.setPrefHeight(42);
        gap.setPrefWidth(24);

        for (Map.Entry<String, String> entry : mediaData.entrySet()) {
            Text label = new Text(entry.getKey());
            Text info = new Text(entry.getValue());

            HBox temp = new HBox(label, gap, info);
            infoBox.getChildren().add(temp);
        }

        mediaImage.setImage(getMediaImage());
        title.setText(media.getTitle());
        price.setText(String.valueOf(media.getPrice()));
        category.setText(media.getCategory());
        quantity.setText(String.valueOf(media.getQuantity()));
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
    @FXML
    void goBack (ActionEvent event) {
        goBack(event, prevScene);
    }
}
