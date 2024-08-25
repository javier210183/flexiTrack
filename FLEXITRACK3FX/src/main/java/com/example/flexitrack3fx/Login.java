package com.example.flexitrack3fx;

import com.example.flexitrack3fx.data.Config;
import com.example.flexitrack3fx.data.Users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Login class handles the login functionality for the application.
 * It verifies user credentials and loads the main menu screen upon successful login.
 */
public class Login {

    @FXML
    private TextField nameLoginField;

    @FXML
    private PasswordField passwordLoginField;

    /**
     * This method is called when the "Enter" button is clicked.
     * It retrieves the entered email and password, verifies them against stored user data,
     * and loads the menu screen if the credentials are correct.
     *
     * @param actionEvent the action event triggered by clicking the "Enter" button
     */
    public void onEnterClick(ActionEvent actionEvent) {
        String email = nameLoginField.getText().trim();
        String password = passwordLoginField.getText().trim();

        System.out.println("Attempting to log in with Email: " + email + " and Password: " + password);

        for (Users user : Config.getInstance().getUsers()) {
            System.out.println("Checking against stored User: Email: " + user.getEmail() + ", Password: " + user.getPassword());
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Credentials are correct");
                loadMenuScreen();
                return;
            }
        }
        System.out.println("Invalid credentials");
    }

    /**
     * Loads the main menu screen.
     * This method is called after successful login.
     */
    private void loadMenuScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setTitle("Menu");
            loginStage.setScene(scene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
