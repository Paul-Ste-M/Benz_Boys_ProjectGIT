package com.calmly;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import com.model.*;

public class LogInController {

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    private void onLoginButtonClicked(ActionEvent event) throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();

        SongApp songApp = SongApp.getInstance();

        if (songApp.login(username, password) == null) {
            errorMessage.setText("The username or password you have entered is incorrect");
            return;
        }

        App.setRoot("home");
    }

}

