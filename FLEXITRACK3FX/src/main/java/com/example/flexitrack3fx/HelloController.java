package com.example.flexitrack3fx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The HelloController class is the controller for the hello-view FXML.
 * It handles the user interactions defined in the FXML file.
 */
public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button startButton;

    /**
     * This method is called when the "Hello" button is clicked.
     * It updates the welcome text and loads a new window.
     *
     * @throws Exception if there is an error loading the FXML file for the new window
     */
    @FXML
    protected void onHelloButtonClick() throws Exception {
        welcomeText.setText("Welcome to Flexitrack Application!");

        // Load the new window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chose.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 600, 400); // Adjust size as per the new window
        Stage newStage = new Stage();
        newStage.setTitle("Choose Action");
        newStage.setScene(scene);
        newStage.show();
    }
}
