package com.calmly;

import java.io.IOException;

import com.model.SongApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField emailText;

    @FXML
    private Label errorText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField userNameText;

    @FXML
    private void onCreateAccountButtonClicked(ActionEvent event) throws IOException {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String userName = userNameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();

        // Check for empty fields
        if(firstName.equals(" ") || lastName.equals("") || userName.equals("") || email.equals("")
                || password.equals("")) {
            errorText.setText("You cannot leave any fields blank");
            return;
        }

        SongApp songApp = SongApp.getInstance();

        if(songApp.signUp(userName, firstName, lastName, email, password) == null) {
            errorText.setText("The username or email you have selected is already in use");
            return;
        }

        App.setRoot("home");
    }

}