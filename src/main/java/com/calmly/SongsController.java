package com.calmly;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.model.Song;
import com.model.SongApp;
import com.model.SongLibrary;
import com.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SongsController {

    @FXML private ListView<String> allSongsList;
    @FXML private ListView<String> userSongsList;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeComboBox;
    @FXML private VBox songDetailsBox;
    @FXML private Label selectedSongLabel;
    @FXML private Button playButton;
    
    private ObservableList<String> allSongsData;
    private ObservableList<String> userSongsData;
    private Song selectedSong;
    private SongApp songApp;

    // Search type options
    private enum SearchType {
        AUTHOR("Author"),
        TITLE("Title");

        private final String displayName;

        SearchType(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    @FXML
    public void initialize() {
        songApp = SongApp.getInstance();
        
        // Initialize observable lists
        allSongsData = FXCollections.observableArrayList();
        userSongsData = FXCollections.observableArrayList();
        
        // Set the items to ListViews
        allSongsList.setItems(allSongsData);
        userSongsList.setItems(userSongsData);
        
        // Initialize search type combo box
        searchTypeComboBox.setItems(FXCollections.observableArrayList(
            SearchType.AUTHOR.toString(),
            SearchType.TITLE.toString()
        ));
        searchTypeComboBox.getSelectionModel().selectFirst();
        
        // Set up song selection listeners
        setupSelectionListeners();
        
        // Initially hide the song details panel
        songDetailsBox.setVisible(false);
        
        // Load songs
        loadAllSongs();
        loadUserSongs();
    }

    private void setupSelectionListeners() {
        // Listener for all songs list
        allSongsList.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> handleSongSelection(newVal));
            
        // Listener for user songs list
        userSongsList.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> handleSongSelection(newVal));
    }

    private void handleSongSelection(String songString) {
        if (songString != null) {
            // Extract title and author from the formatted string
            String[] parts = songString.split(" - ");
            if (parts.length == 2) {
                String title = parts[0].trim();
                String author = parts[1].trim();
                
                // Find the song in the library
                selectedSong = SongLibrary.getInstance().getSongs().stream()
                    .filter(s -> s.getTitle().equals(title) && s.getAuthor().equals(author))
                    .findFirst()
                    .orElse(null);
                
                if (selectedSong != null) {
                    updateSelectedSongUI(selectedSong);
                }
            }
        }
    }

    private void updateSelectedSongUI(Song song) {
        selectedSongLabel.setText(song.getTitle() + " by " + song.getAuthor());
        songDetailsBox.setVisible(true);
        
        // Clear selection from the other list
        if (allSongsList.getSelectionModel().getSelectedItem() != null && 
            !allSongsList.getSelectionModel().getSelectedItem().contains(song.getTitle())) {
            allSongsList.getSelectionModel().clearSelection();
        }
        
        if (userSongsList.getSelectionModel().getSelectedItem() != null && 
            !userSongsList.getSelectionModel().getSelectedItem().contains(song.getTitle())) {
            userSongsList.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void onPlaySong() {
        if (selectedSong != null) {
            songApp.selectSongFromLibrary(selectedSong.getTitle(), selectedSong.getAuthor());
            songApp.playSong();
            
            // Update button text temporarily
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

    private void loadAllSongs() {
        SongLibrary songLibrary = SongLibrary.getInstance();
        List<Song> songs = songLibrary.getSongs();
        
        allSongsData.setAll(
            songs.stream()
                .map(song -> String.format("%s - %s", song.getTitle(), song.getAuthor()))
                .collect(Collectors.toList())
        );
    }

    private void loadUserSongs() {
        User user = songApp.getUser();
        List<UUID> songIds = user.getCreatedSongs();
        
        List<String> userSongs = songIds.stream()
            .map(id -> SongLibrary.getInstance().getSongs().stream()
                .filter(s -> s.getSongID().equals(id))
                .findFirst()
                .map(song -> String.format("%s - %s", song.getTitle(), song.getAuthor()))
                .orElse(null))
            .filter(title -> title != null)
            .collect(Collectors.toList());
        
        userSongsData.setAll(userSongs);
    }

    @FXML
    private void onSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        String selectedSearchType = searchTypeComboBox.getValue();
        
        if (searchTerm.isEmpty()) {
            loadAllSongs();
            return;
        }
        
        SongLibrary songLibrary = SongLibrary.getInstance();
        List<Song> filteredSongs = songLibrary.getSongs().stream()
            .filter(song -> {
                if (selectedSearchType.equals(SearchType.AUTHOR.toString())) {
                    return song.getAuthor().toLowerCase().contains(searchTerm);
                } else {
                    return song.getTitle().toLowerCase().contains(searchTerm);
                }
            })
            .collect(Collectors.toList());
        
        allSongsData.setAll(
            filteredSongs.stream()
                .map(song -> String.format("%s - %s", song.getTitle(), song.getAuthor()))
                .collect(Collectors.toList())
        );
    }
}