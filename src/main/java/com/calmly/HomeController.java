package com.calmly;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import com.model.*;

public class HomeController implements Initializable{

    @FXML
    private Label welcomeLabel;

    private SongApp songApp;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songApp = SongApp.getInstance();
        user = songApp.getUser();

        welcomeLabel.setText("Welcome " + user.getFullName());
    }



}
