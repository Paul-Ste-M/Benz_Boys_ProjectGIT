package com.calmly;

import com.model.SongApp;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class SettingsController {


    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;

    @FXML
    public void initialize() {
        SongApp songApp = SongApp.getInstance();
        User user = songApp.getUser();

        usernameLabel.setText("Username: " + user.getUsername());
        emailLabel.setText("Email: " + user.getEmail());
        firstNameLabel.setText("First name: " + user.getFirstName());
        lastNameLabel.setText("Last name: " + user.getLastName());
    }

    @FXML
    private void onSave() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Settings Saved");
        alert.setHeaderText(null);
        alert.setContentText("Your preferences have been saved.");
        alert.showAndWait();
    }

    @FXML
    private void onBack() {
        try {
            App.setRoot("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
