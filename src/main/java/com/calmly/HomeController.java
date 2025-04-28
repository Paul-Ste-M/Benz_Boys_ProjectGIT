package com.calmly;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.model.SongApp;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeController implements Initializable {
    @FXML private Label welcomeLabel;
    @FXML private BorderPane rootPane;   // inject the BorderPane root

    private SongApp songApp;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songApp = SongApp.getInstance();
        user = songApp.getUser();
        welcomeLabel.setText("Welcome " + user.getFullName());
    }

    private void loadCenter(String fxmlFile) {
        try {
            Node view = FXMLLoader.load(HomeController.class.getResource(fxmlFile));
            rootPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void onHome(ActionEvent e) {
        try {
            App.setRoot("home");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void onSongs(ActionEvent e) {
        loadCenter("songs.fxml");
    }

    @FXML
    private void onCreate(ActionEvent e) {
        loadCenter("create.fxml");
        // try{
        //     App.setRoot("songeditor");
        // } catch (IOException ex) {
        //     ex.printStackTrace();
        // }
    }

    @FXML
    private void onAboutUs(ActionEvent e) {
        loadCenter("about.fxml");
    }

    @FXML
    private void onProfile(ActionEvent e) {
        System.out.println("Profile clicked");
    }

    @FXML
    private void onSettings(ActionEvent e) {
        loadCenter("settings.fxml");
    }
        
}
