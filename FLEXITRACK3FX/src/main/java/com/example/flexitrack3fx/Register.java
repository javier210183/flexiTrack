package com.example.flexitrack3fx;

import com.example.flexitrack3fx.data.Config;
import com.example.flexitrack3fx.data.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Register implements Initializable {
    @FXML
    PasswordField passwordField;

    @FXML
    ListView<Users> registerListView;

    @FXML
    TextField namefield;

    @FXML
    TextField emailfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerListView.setItems(FXCollections.observableArrayList(Config.getInstance().getUsers()));
        registerListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUsers, newUsers) -> updateContactData(newUsers)
        );
    }


    public void onAddClick(ActionEvent actionEvent) {
        Users user = new Users(this.namefield.getText(), this.emailfield.getText(), this.passwordField.getText());
        registerListView.getItems().add(user);
        Config.getInstance().updateUser(user);
    }

    public void onRemoveClick(ActionEvent actionEvent) {
        int index = registerListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Users user = registerListView.getItems().remove(index);
        }
    }

    public void onSaveClick(ActionEvent actionEvent) {
        int selectedIndex = registerListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Users selectedUser = registerListView.getItems().get(selectedIndex);

            selectedUser.setName(namefield.getText());
            selectedUser.setEmail(emailfield.getText());
            selectedUser.setPassword(passwordField.getText());

            registerListView.getItems().set(selectedIndex, selectedUser);
            Config.getInstance().updateUser(selectedUser);

            clearFields();
        }
    }

    private void updateContactData(Users newUsers) {
        this.namefield.setText(newUsers.getName());
        this.emailfield.setText(newUsers.getEmail());
        this.passwordField.setText(newUsers.getPassword());
    }

    private void clearFields() {
        namefield.setText("");
        emailfield.setText("");
        passwordField.setText("");
    }
}
