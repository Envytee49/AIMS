package com.example.aims.utils;

import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.Media;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageSaver {
    private static String getPathByMedia(String category, String resourcesPath) {
        if (category.equals("Book")) {
            return resourcesPath + "book/" ;
        } if (category.equals("CD")) {
            return resourcesPath + "cd/" ;
        }
        if (category.equals("DVD")){
            return resourcesPath + "dvd/";
        }
        return "";
    }
    public static void saveImageToResources(File file, String category) {
        String path;
        URL res = ImageSaver.class.getClassLoader().getResource("foo.txt");
        File destinationFile = null;
        String resourcesPath;
        try {
            if (res != null) {
                destinationFile = Paths.get(res.toURI()).toFile();
                int idx = destinationFile.getAbsolutePath().indexOf("AIMS") + "AIMS".length();
                resourcesPath = destinationFile.getAbsolutePath().substring(0, idx) +
                        "\\src\\main\\resources\\com\\example\\aims\\assets\\";
            } else {
                throw new RuntimeException("Resource not found: foo.txt");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        path = getPathByMedia(category, resourcesPath);
        Path resourceDirectory = Path.of(path);
        try {
            Files.createDirectories(resourceDirectory);
            Path destinationPath = resourceDirectory.resolve(file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Destination path: " + destinationPath);
            Files.copy(new FileInputStream(file), destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
