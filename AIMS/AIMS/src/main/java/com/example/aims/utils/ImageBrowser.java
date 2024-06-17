package com.example.aims.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageBrowser {
    public static File uploadImage (Stage primaryStage, FileChooser fileChooser, ImageView mediaImage) {
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            try {
                FileInputStream f = new FileInputStream(file);
                Image image = new Image(f);
                mediaImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
