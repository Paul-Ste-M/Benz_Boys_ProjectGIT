package com.calmly;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartUpController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void switchToLogIn(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToSignUp(ActionEvent event) throws IOException {
        App.setRoot("signup");
    }

}

