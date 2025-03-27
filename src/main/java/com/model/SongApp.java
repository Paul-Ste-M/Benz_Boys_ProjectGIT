package com.model;

import java.util.ArrayList;

public class SongApp {
    private User user;
    private Song selectedSong;
    private Instrument selectedInstrument;
    private ArrayList<Song> searchResults;
    private int currentVolume = 0;

    public SongApp() {
        DataReader.readUsers();
        DataReader.readSongs();
        DataReader.readInstruments();
    }

    /**
     * User login
     */
    public User login(String username, String password) {
        return UserList.getInstance().login(username, password); 
    }

    /**
     * Sign up a new user
     */
    public User signUp(String username, String firstName, String lastName, String email, String password) {
         User potentialNewUser = UserList.getInstance().signUp(username, firstName, lastName, email, password);
         this.user = potentialNewUser;
         return potentialNewUser;
    }

    /**
     * Log out the current user
     */
    public void logout() {
        UserList.getInstance().saveUsers();
        SongLibrary.getInstance().saveSongs();
        InstrumentList.getInstance().saveInstruments();

        user = null; 
    }

    /**
     * Retrieve a song by title and author
     */
    public Song getSong(String name, String author) {
        return SongLibrary.getInstance().getSong(name, author);
    }

    /**
     * Add a song to the library
     */
    public void addSong(String name, Author author) {
        SongLibrary.getInstance().addSong(new Song(name, author));
    }

    /**
     * Save a song
     */
    public void saveSong(Song selectedSong) {
        ArrayList<Song> allSongs = SongLibrary.getInstance().getSongs();
        DataWriter.saveSongs(allSongs);
    }

    /**
     * Start a song
     */
    public Song startSong(String title, String author) {
        return SongLibrary.getInstance().getSong(title, author);
    }

    /**
     * Edit a song
     */
    public void editSong(Song selectedSong, String decision) {
        // Stub implementation
    }

    /**
     * Saves project
     */
    public void saveProject(Song selectedSong, String name, String author, String username) {
        // Stub implementation
    }

    /**
     * Publishes a song
     */
    public void publishSong(Song selectedSong, String name, String author, String username) {
        if (selectedSong != null) {
            selectedSong.setPublished(true);
        }
    }

    /**
     * Removes a song
     */
    public void removeSong(Song selectedSong) {
        SongLibrary.getInstance().removeSong(selectedSong);
        
    }

    /**
     * Play a songs
     */
    public void playSong(Song selectedSong) {
        if (selectedSong != null) {
            selectedSong.playSong(); 
        }
    }

    /**
     * Select an instrument
     */
    public Instrument selectInstrument(String instrumentName) {
        for(Instrument instrument : InstrumentList.getInstance().getInstruments()) {
            if(instrument.getInstrumentName().toString().equalsIgnoreCase(instrumentName)) {
                this.selectedInstrument = instrument;
            }
        }
        return selectedInstrument;
    }

    /**
     * Changes the volume of the music
     */
    public void changeVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.currentVolume = volume;
        }
    }

    /**
     * Export songs
     */
    public void exportSong(Song song) {
        song.printTabsToTextFile();
    }

    /**
     * Get comments for the selected song
     */
    public ArrayList<Comment> getComments() {
        if (selectedSong == null) {
            return new ArrayList<>();
        }
        return selectedSong.getComments();
    }

    /**
     * Add a comment to the currently selected song
     */
    public void addComment(String commentText, String commenterName, String username) {
        Comment newComment = new Comment(commentText, commenterName, username);
        selectedSong.addComment(newComment);
    }    

    /**
     * Show comments for the selected song
     */
    /**
     * Show comments for the selected song
     */
    public void showComments() {
        if (selectedSong != null) {
            ArrayList<Comment> comments = getComments();
            for (Comment comment : comments) {
                System.out.println(comment.getCommentText() + " by " + comment.getCommenterName());
            }
        }
    }

    /**
     * Search for a song by genre, artist, or title
     */
    public ArrayList<Song> searchByKeyboard(String genre, String artist, String title) {
        SongLibrary library = SongLibrary.getInstance();
        ArrayList<Song> results = new ArrayList<>();
    
        for (Song song : library.getSongs()) { 
            boolean matchesGenre = (genre == null || song.getGenres().stream()
            .anyMatch(g -> g.name().equalsIgnoreCase(genre))); // Checks if any genre matches
            boolean matchesArtist = (artist == null || song.getAuthor().equalsIgnoreCase(artist));
            boolean matchesTitle = (title == null || song.getTitle().equalsIgnoreCase(title));
    
            if (matchesGenre && matchesArtist && matchesTitle) {
                results.add(song);
            }
        }
    
        return results;
    }
    
    /**
     * Export to PDF
     */
    public void exportToPDF() {
        // Stub implementation
    }

    /**
     * Play ear training game, ear training game not implemented yet
     */
//    public EarTrainingGame playGame() {
//        return EarTrainingGame.getInstance(); // Assuming this method exists
//    }
}
