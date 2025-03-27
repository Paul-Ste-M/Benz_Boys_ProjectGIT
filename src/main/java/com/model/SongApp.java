package com.model;

import java.util.ArrayList;

/**
 * The main application controller for managing users, songs, instruments, and overall session state.
 * Provides methods for logging in/out, managing songs, selecting instruments, saving data, and interacting with comments.
 */
public class SongApp {
    private User user;
    private Song selectedSong;
    private Instrument selectedInstrument;
    private ArrayList<Song> searchResults;
    private int currentVolume = 0;

    /**
     * Constructs the SongApp and loads all users, songs, and instruments from disk.
     */
    public SongApp() {
        DataReader.readUsers();
        DataReader.readSongs();
        DataReader.readInstruments();
    }

    /**
     * Attempts to log in a user with the given credentials.
     *
     * @param username the username to authenticate
     * @param password the password to check
     * @return the logged-in User if credentials are valid, or null otherwise
     */
    public User login(String username, String password) {
        return UserList.getInstance().login(username, password); 
    }

    /**
     * Registers a new user and sets them as the current user.
     *
     * @param username  the desired username
     * @param firstName first name
     * @param lastName  last name
     * @param email     user’s email
     * @param password  desired password
     * @return the newly created User object
     */
    public User signUp(String username, String firstName, String lastName, String email, String password) {
         User potentialNewUser = UserList.getInstance().signUp(username, firstName, lastName, email, password);
         this.user = potentialNewUser;
         return potentialNewUser;
    }

    /**
     * Logs out the current user and saves all relevant data to disk.
     */
    public void logout() {
        UserList.getInstance().saveUsers();
        SongLibrary.getInstance().saveSongs();
        InstrumentList.getInstance().saveInstruments();
        user = null; 
    }

    /**
     * Retrieves a song from the library by title and author.
     *
     * @param name   the title of the song
     * @param author the author’s name
     * @return the Song object if found, null otherwise
     */
    public Song getSong(String name, String author) {
        return SongLibrary.getInstance().getSong(name, author);
    }

    /**
     * Adds a new song to the song library.
     *
     * @param name   the title of the song
     * @param author the Author creating the song
     */
    public void addSong(String name, Author author) {
        SongLibrary.getInstance().addSong(new Song(name, author));
    }

    /**
     * Saves the current list of songs to disk.
     *
     * @param selectedSong the song to save (not directly used)
     */
    public void saveSong(Song selectedSong) {
        ArrayList<Song> allSongs = SongLibrary.getInstance().getSongs();
        DataWriter.saveSongs(allSongs);
    }

    /**
     * Retrieves and returns a song from the library to begin editing or playback.
     *
     * @param title  the song title
     * @param author the song author
     * @return the retrieved Song object
     */
    public Song startSong(String title, String author) {
        return SongLibrary.getInstance().getSong(title, author);
    }

    /**
     * Placeholder method for editing a song (not yet implemented).
     *
     * @param selectedSong the song to edit
     * @param decision     user’s input or edit action
     */
    public void editSong(Song selectedSong, String decision) {
        // Stub implementation
    }

    /**
     * Placeholder for saving a song project with metadata.
     *
     * @param selectedSong the song to save
     * @param name         title of the song
     * @param author       name of the author
     * @param username     author's username
     */
    public void saveProject(Song selectedSong, String name, String author, String username) {
        // Stub implementation
    }

    /**
     * Publishes the selected song, marking it as publicly available.
     *
     * @param selectedSong the song to publish
     * @param name         title of the song
     * @param author       name of the author
     * @param username     author's username
     */
    public void publishSong(Song selectedSong, String name, String author, String username) {
        if (selectedSong != null) {
            selectedSong.setPublished(true);
        }
    }

    /**
     * Removes the selected song from the library.
     *
     * @param selectedSong the song to remove
     */
    public void removeSong(Song selectedSong) {
        SongLibrary.getInstance().removeSong(selectedSong);
    }

    /**
     * Plays the selected song using JFugue playback.
     *
     * @param selectedSong the song to play
     */
    public void playSong(Song selectedSong) {
        if (selectedSong != null) {
            selectedSong.playSong(); 
        }
    }

    /**
     * Selects an instrument by name from the loaded list.
     *
     * @param instrumentName the name of the instrument to select
     * @return the selected Instrument, or null if not found
     */
    public Instrument selectInstrument(String instrumentName) {
        for (Instrument instrument : InstrumentList.getInstance().getInstruments()) {
            if (instrument.getInstrumentName().toString().equalsIgnoreCase(instrumentName)) {
                this.selectedInstrument = instrument;
            }
        }
        return selectedInstrument;
    }

    /**
     * Sets the application's playback volume.
     *
     * @param volume value between 0 and 100
     */
    public void changeVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.currentVolume = volume;
        }
    }

    /**
     * Exports the selected song as a text-based tablature file.
     *
     * @param song the song to export
     */
    public void exportSong(Song song) {
        song.printTabsToTextFile();
    }

    /**
     * Retrieves the comments associated with the selected song.
     *
     * @return a list of Comment objects, or empty list if no song is selected
     */
    public ArrayList<Comment> getComments() {
        if (selectedSong == null) {
            return new ArrayList<>();
        }
        return selectedSong.getComments();
    }

    /**
     * Adds a comment to the currently selected song.
     *
     * @param commentText   the content of the comment
     * @param commenterName the name of the person commenting
     * @param username      the commenter's username
     */
    public void addComment(String commentText, String commenterName, String username) {
        Comment newComment = new Comment(commentText, commenterName, username);
        selectedSong.addComment(newComment);
    }

    /**
     * Prints all comments for the selected song to the console.
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
     * Searches the song library using optional genre, artist, or title filters.
     * Any null parameter is treated as a wildcard.
     *
     * @param genre  the genre to match (can be null)
     * @param artist the author name to match (can be null)
     * @param title  the title to match (can be null)
     * @return a list of matching songs
     */
    public ArrayList<Song> searchByKeyboard(String genre, String artist, String title) {
        SongLibrary library = SongLibrary.getInstance();
        ArrayList<Song> results = new ArrayList<>();

        for (Song song : library.getSongs()) {
            boolean matchesGenre = (genre == null || song.getGenres().stream()
                .anyMatch(g -> g.name().equalsIgnoreCase(genre)));
            boolean matchesArtist = (artist == null || song.getAuthor().equalsIgnoreCase(artist));
            boolean matchesTitle = (title == null || song.getTitle().equalsIgnoreCase(title));

            if (matchesGenre && matchesArtist && matchesTitle) {
                results.add(song);
            }
        }

        return results;
    }

    /**
     * Stub method to export the song project to a PDF file.
     * Currently not implemented.
     */
    public void exportToPDF() {
        // Stub implementation
    }
}
