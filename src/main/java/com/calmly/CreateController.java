package com.calmly;

import com.model.SongApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateController {

    @FXML
    private Button createSongButton;

    @FXML
    private Label errorText;

    @FXML
    private TextField songTitleText;

    @FXML
    private void onCreateSongButtonClicked(ActionEvent event) {
        String songTitle = songTitleText.getText();

        if(songTitle.equals("")) {
            errorText.setText("You must enter a title");
            return;
        }

        SongApp songApp = SongApp.getInstance();
        songApp.startSong(songTitle);    
    }

}
