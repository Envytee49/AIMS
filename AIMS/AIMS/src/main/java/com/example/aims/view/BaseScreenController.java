package com.example.aims.view;

import com.example.aims.repository.AIMSDB;
import com.example.aims.utils.PathConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BaseScreenController{
    protected Scene currentScene;
    protected Scene prevScene;
    public BaseScreenController(){}
    public <T> BaseScreenController(Scene currentScene, String currentScreenPath, T ...data) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(currentScreenPath));
        Parent root;
        try {
            root = loader.load();
            BaseScreenController baseScreenController = loader.getController();
            Stage stage = (Stage) currentScene.getWindow();
            Scene scene = new Scene(root);
            baseScreenController.setCurrentScene(scene);
            baseScreenController.setPrevScene(currentScene);
            baseScreenController.initData(data);
            stage.setScene(scene);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResource(PathConfig.LOGO_PATH).toExternalForm()));
            stage.setResizable(false);
            stage.setOnCloseRequest(windowEvent -> AIMSDB.shutdown());
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }

    public Scene getPrevScene() {
        return prevScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public abstract <T> void initData(T ...data);


}
