package com.example.flexitrack3fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HelloApplication class is the main entry point for the JavaFX application.
 * It extends the {@link javafx.application.Application} class and overrides the start method.
 */
public class HelloApplication extends Application {

    /**
     * The start method is called after the application is launched.
     * It loads the FXML file, sets the scene, and displays the primary stage.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 380, 450);
        stage.setTitle("WELCOME FELXITRACK");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
