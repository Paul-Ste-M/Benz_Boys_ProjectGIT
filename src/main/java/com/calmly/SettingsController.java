package com.calmly;

import java.util.List;
import java.util.UUID;

import com.model.Song;
import com.model.SongApp;
import com.model.SongLibrary;
import com.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SettingsController {

    @FXML private Label usernameLabel;
    @FXML private Label emailLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private ListView<String> createdSongsList;
    @FXML private VBox songDetailsBox;
    @FXML private Label selectedSongLabel;
    @FXML private Button playButton;
    
    private Song selectedSong;
    private SongApp songApp;

    @FXML
    public void initialize() {
        songApp = SongApp.getInstance();
        User user = songApp.getUser();

        // populate basic user info
        usernameLabel.setText("Username: " + user.getUsername());
        emailLabel.setText("Email: " + user.getEmail());
        firstNameLabel.setText("First name: " + user.getFirstName());
        lastNameLabel.setText("Last name: " + user.getLastName());

        // populate the ListView of created songs
        List<UUID> songIds = user.getCreatedSongs();
        ObservableList<String> titles = FXCollections.observableArrayList();

        for (UUID id : songIds) {
            Song song = SongLibrary.getInstance()
                          .getSongs()
                          .stream()
                          .filter(s -> s.getSongID().equals(id))
                          .findFirst()
                          .orElse(null);
            if (song != null) {
                titles.add(song.getTitle() + " - " + song.getAuthor());
            }
        }
        createdSongsList.setItems(titles);
        
        // Add selection listener
        createdSongsList.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> onSongSelected(newVal));
    }

    private void onSongSelected(String songString) {
        if (songString != null) {
            String[] parts = songString.split(" - ");
            if (parts.length == 2) {
                String title = parts[0].trim();
                String author = parts[1].trim();
                
                selectedSong = SongLibrary.getInstance().getSongs().stream()
                    .filter(s -> s.getTitle().equals(title) && s.getAuthor().equals(author))
                    .findFirst()
                    .orElse(null);
                
                if (selectedSong != null) {
                    selectedSongLabel.setText("Selected: " + songString);
                    songDetailsBox.setVisible(true);
                }
            }
        }
    }

    @FXML
    private void onPlaySong() {
        if (selectedSong != null) {
            songApp.selectSongFromLibrary(selectedSong.getTitle(), selectedSong.getAuthor());
            songApp.playSong();
            
            // Visual feedback
            playButton.setText("Playing...");
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        javafx.application.Platform.runLater(() -> 
                            playButton.setText("Play Song"));
                    }
                },
                2000
            );
        }
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

    @FXML
    private void onLogout() {
        try {
            songApp.logout();
            App.setRoot("startup");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}