package com.example.flexitrack3fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Principal class provides methods to open different windows of the application,
 * such as the register window and the login window.
 */
public class Principal {

    /**
     * Opens the register window.
     * This method loads the Register.fxml file and displays the register stage.
     */
    public void openRegisterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml")); // Ensure the FXML file name is correct
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the login window.
     * This method loads the Login.fxml file and displays the login stage.
     */
    public void openLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml")); // Ensure the FXML file name is correct
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(scene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the application.
     * This method is called when the user chooses to exit the application.
     */
    public void exitApplication() {
        System.exit(0);
    }
}
