package com.model;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A facade that acts as the bridge between the UI and the backend of the program.
 * Simplified and improved for better readability and maintainability.
 * @author Benz Boyz
 */
public class SongApp {

    private User user;
    private Author author;
    private Song selectedSong;
    private Instrument selectedInstrument;
    private ArrayList<Song> searchResults;
    private int currentVolume = 0;
    private static SongApp songApp;

    /**
     * Initializes a new instance of the facade
     */
    private SongApp() {
        // Initialize the data by reading users, songs, and instruments
        DataReader.readUsers();
        DataReader.readSongs();
        DataReader.readInstruments();
    }

    public static SongApp getInstance() {
        if(songApp == null) {
            songApp = new SongApp();
        }
        return songApp;
    }

    /**
     * Logs the user in based on the given username and password.
     * @param username The provided username
     * @param password The provided password
     * @return The logged-in user
     */
    public User login(String username, String password) {
        this.user = UserList.getInstance().login(username, password);
        return this.user;
    }

    /**
     * Signs up a new user with the provided information.
     * @param username The username of the new user
     * @param firstName The first name of the new user
     * @param lastName The last name of the new user
     * @param email The email of the new user
     * @param password The password of the new user
     * @return The new user
     */
    public User signUp(String username, String firstName, String lastName, String email, String password) {
        User newUser = UserList.getInstance().signUp(username, firstName, lastName, email, password);
        this.user = newUser;
        return newUser;
    }

    /**
     * Logs out the current user and saves the session data.
     */
    public void logout() {
        if (user != null) {
            DataWriter.saveUsers(UserList.getInstance().getUsers());
            SongLibrary.getInstance().saveSongs();
            InstrumentList.getInstance().saveInstruments();
        }
        user = null;
    }

    /**
     * Retrieves a song by title and author.
     * @param name The name of the song
     * @param author The full name of the author
     * @return The retrieved song
     */
    public Song getSong(String name, String author) {
        return SongLibrary.getInstance().getSong(name, author);
    }

    public User getUser() {
        return user;
    }

    /**
     * Adds a song to the library.
     * @param name The name of the song
     * @param author The author of the song
     */
    public void addSong(String name, Author author) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Song name cannot be null or empty");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        SongLibrary.getInstance().addSong(new Song(name, author));
    }

    /**
     * Saves the current song being worked on.
     */
    public void saveSong() {
        ArrayList<Song> allSongs = SongLibrary.getInstance().getSongs();
        DataWriter.saveSongs(allSongs);
    }

    /**
     * Starts a new song and makes the user an Author if not already.
     * @param title The title of the new song
     * @return The newly created song
     */
    public Song startSong(String title) {
        this.author = new Author(user); // Ensure the user is the author
        Song newSong = new Song(title, (Author) user);
        SongLibrary.getInstance().addSong(newSong);
        this.selectedSong = newSong;
        user.addCreatedSong(newSong);
        this.author.addSong(newSong);
        this.author.selectSong(newSong);
        return newSong;
    }

    /**
     * Selects a measure from the selected song.
     * @param position The position of the measure in the song
     */
    public void selectMeasure(int position) {
        author.setMeasure(selectedSong.getMeasures().get(position));
    }

    /**
     * Adds a constructed measure to the selected song.
     * @param measure The measure to be added
     */
    public void addMeasure(Measure measure) {
        author.addMeasure(measure);
    }

    /**
     * Adds a chord to a specific position in the selected measure.
     * @param position The position to insert the chord
     * @param type The chord's type
     * @param leadingNote The leading note's pitch
     * @param isSingleNote Whether the chord is a single note
     * @param isMinor Whether the chord is minor
     * @param octave The octave of the note
     * @param fretNumber The fret number
     * @param tabsLine The line on the tab
     */
    public void addChord(int position, String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        author.addChord(position, type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
    }

    /**
     * Adds a chord to the end of the selected measure.
     * @param type The chord's type
     * @param leadingNote The leading note's pitch
     * @param isSingleNote Whether the chord is a single note
     * @param isMinor Whether the chord is minor
     * @param octave The octave of the note
     * @param fretNumber The fret number
     * @param tabsLine The line on the tab
     */
    public void addChord(String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        author.addChord(type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
    }

    /**
     * Publishes the song to make it public.
     * @param selectedSong The selected song to publish
     */
    public void publishSong(Song selectedSong) {
        if (selectedSong != null) {
            selectedSong.setPublished(true);
        }
    }

    /**
     * Removes a song from the library.
     * @param selectedSong The selected song to be removed
     */
    public void removeSong(Song selectedSong) {
        SongLibrary.getInstance().removeSong(selectedSong);
    }

    /**
     * Plays the currently selected song.
     */
    public void playSong() {
        if (selectedSong != null) {
            selectedSong.playSong();
        }
    }

    /**
     * Selects an instrument by name.
     * @param instrumentName The name of the instrument
     * @return The selected instrument
     */
    public Instrument selectInstrument(String instrumentName) {
        for (Instrument instrument : InstrumentList.getInstance().getInstruments()) {
            if (instrument.getInstrumentName().toString().equalsIgnoreCase(instrumentName)) {
                this.selectedInstrument = instrument;
                return instrument;
            }
        }
        return null; // Return null if no instrument was found
    }

    /**
     * Changes the volume of the music.
     * @param volume The new volume level (0 to 100)
     */
    public void changeVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.currentVolume = volume;
        }
    }
    /**
     * Gets the current volume level.
     * @return currentVolume The current volume level (0 to 100)
     */
    public int getVolume() {
        return currentVolume;
    }

    /**
     * Exports the song to a text file.
     */
    public void exportSong() {
        if (selectedSong != null) {
            selectedSong.printTabsToTextFile();
        }
    }

    /**
     * Retrieves comments for the selected song.
     * @return The list of comments
     */
    public ArrayList<Comment> getComments() {
        return selectedSong == null ? new ArrayList<>() : selectedSong.getComments();
    }

    /**
     * Adds a comment to the currently selected song.
     * @param commentText The comment text
     * @param commenterName The commenter's name
     * @param username The username of the commenter
     */
    public void addComment(String commentText, String commenterName, String username) {
        if (selectedSong != null) {
            Comment comment = new Comment(commentText, commenterName, username);
            selectedSong.addComment(comment);
        }
    }

    /**
     * Displays the comments for the selected song.
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
     * Searches for songs by title.
     * @param title The title to search for
     * @return The list of matching songs
     */
    public ArrayList<Song> searchByTitle(String title) {
        ArrayList<Song> results = SongLibrary.getInstance().searchByTitle(title);
        this.searchResults = results;
        return results;
    }

    /**
     * Searches for songs by author.
     * @param author The author to search for
     * @return The list of matching songs
     */
    public ArrayList<Song> searchByAuthor(String author) {
        ArrayList<Song> results = SongLibrary.getInstance().searchByArtist(author);
        this.searchResults = results;
        return results;
    }

    /**
     * Selects a song from the search results.
     * @param position The position of the song in the search results
     * @return The selected song
     */
    public Optional<Song> selectSongFromResults(int position) {
    if (position < 0 || position >= searchResults.size()) {
        return Optional.empty(); 
    }
    this.selectedSong = searchResults.get(position);
    return Optional.of(selectedSong);
}

    /**
     * Search for a song by genre, artist, or title.
     * @param genre The genre of the song
     * @param artist The artist of the song
     * @param title The title of the song
     * @return The list of matching songs
     */
    public ArrayList<Song> searchByKeyboard(String genre, String artist, String title) {
        SongLibrary library = SongLibrary.getInstance();
        ArrayList<Song> results = new ArrayList<>();

        for (Song song : library.getSongs()) {
            boolean matchesGenre = (genre == null || song.getGenres().stream().anyMatch(g -> g.name().equalsIgnoreCase(genre)));
            boolean matchesArtist = (artist == null || song.getAuthor().equalsIgnoreCase(artist));
            boolean matchesTitle = (title == null || song.getTitle().equalsIgnoreCase(title));

            if (matchesGenre && matchesArtist && matchesTitle) {
                results.add(song);
            }
        }

        return results;
    }
}
