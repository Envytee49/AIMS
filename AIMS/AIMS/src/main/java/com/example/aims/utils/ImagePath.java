package com.example.aims.utils;

import com.example.aims.entity.media.Media;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImagePath {
    public static Image getMediaImage(Media media) {
        try {
            File initialFile = new File("AIMS/src/main/resources" + media.getImageURL());
            InputStream targetStream;
            targetStream = new FileInputStream(initialFile);
            return new Image(targetStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
