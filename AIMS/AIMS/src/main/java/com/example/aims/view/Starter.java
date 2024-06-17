package com.example.aims.view;

import com.example.aims.utils.PathConfig;
import com.example.aims.view.media.HomeScreenController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Starter extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            // initialize the scene
            StackPane root = FXMLLoader.load(getClass().getResource(PathConfig.SPLASH_SCREEN_PATH));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image(getClass().getResource(PathConfig.LOGO_PATH).toExternalForm()));
            // Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            // Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            // After fade in, start fade out
            fadeIn.play();
            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            // After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                new HomeScreenController(scene, PathConfig.HOME_PATH,"", "", 0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void on(String[] args) {
        launch(args);
    }
}
