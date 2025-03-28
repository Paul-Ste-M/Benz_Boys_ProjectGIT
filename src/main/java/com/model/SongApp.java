package com.model;

import java.util.ArrayList;

/**
 * A facade that acts as the bridge between the UI and the backend of program.
 * @author Benz Boyz
 */

public class SongApp {

    private User user;
    private Author author;
    private Song selectedSong;
    private Instrument selectedInstrument;
    private ArrayList<Song> searchResults;
    private int currentVolume = 0;

    /**
     * Initializes a new instance of the facade
     */
    public SongApp() {
        DataReader.readUsers();
        DataReader.readSongs();
        DataReader.readInstruments();
    }

    /**
     * Logs the user in based on the given username and password
     * @param username The provided usernamew
     * @param password The provided pasword
     * @return The logged in user
     */
    public User login(String username, String password) {
        this.user = UserList.getInstance().login(username, password);

        return this.user;
    }

    /**
     * Signs up a new user based on the provided information
     * @param username The username of the new user
     * @param firstName The first name of the new user
     * @param lastName The last name of the new user
     * @param email The email of the new user
     * @param password The password of the new user
     * @return The new user
     */
    public User signUp(String username, String firstName, String lastName, String email, String password) {
        User potentialNewUser = UserList.getInstance().signUp(username, firstName, lastName, email, password);
        this.user = potentialNewUser;
        return potentialNewUser;
    }

    /**
     * Logs out the current user
     */
    public void logout() {

        if (!SongLibrary.getInstance().getSongs().isEmpty()) {
            UserList.getInstance().saveUsers();
            SongLibrary.getInstance().saveSongs();
            InstrumentList.getInstance().saveInstruments();
        } else {
            System.out.println("No songs to save, preserving existing songs.json content.");
        }

        user = null;
    }

    /**
     * Retrieve a song by title and author
     * @param name The name of the song
     * @param author The full name of the author
     * @return The retrieved song
     */
    public Song getSong(String name, String author) {
        return SongLibrary.getInstance().getSong(name, author);
    }

    /**
     * Add a song to the library
     * @param name The name of the song
     * @param author The full name of the author
     */
    public void addSong(String name, Author author) {
        SongLibrary.getInstance().addSong(new Song(name, author));
    }

    /**
     * Saves the current song being worked on
     * @param selectedSong The selected song
     */
    public void saveSong(Song selectedSong) {
        ArrayList<Song> allSongs = SongLibrary.getInstance().getSongs();
        DataWriter.saveSongs(allSongs);
    }

    /**
     * Starts a song
     * Adds the song to the SongLibrary and makes the user an Author if not already.
     * @param title The title of the new song
     * @return The newly created song
     */
    public Song startSong(String title) {
        this.author = new Author(user);
        Song newSong = new Song(title, (Author) user);
        SongLibrary.getInstance().addSong(newSong);
        this.selectedSong = newSong;
        user.addCreatedSong(newSong);
        this.author.addSong(newSong);
        this.author.selectSong(newSong);
        return newSong;
    }
    
    /**
     * Selects a measure to edit
     * @param position The position of the specific measure to select in Song's arrayList
     */
    public void selectMeasure(int position) {
        author.setMeasure(selectedSong.getMeasures().get(position));
    }

    /**
     * Adds a constructed measure to the selected song through Author
     * @param measure The measure to be added
     */
    public void addMeasure(Measure measure) {
        author.addMeasure(measure);
    }

    /**
     * Adds a chord to a specific position in the selected measure
     * @param position The position the chord will be added in
     * @param type The chord's duration Type
     * @param leadingNote The leading note's Pitch
     * @param isSingleNote Whether or not it is a chord or a single note
     * @param isMinor Whether or not the chord is minor
     * @param octave The octave of the leadingNote
     * @param fretNumber The fretNumber of the leadingNote
     * @param tabsLine The line on which the note goes on for tabs
     */
    public void addChord(int position, String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        author.addChord(position, type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
    }

    /**
     * Adds a chord to the end of the selected measure
     * @param type The chord's duration Type
     * @param leadingNote The leading note's Pitch
     * @param isSingleNote Whether or not it is a chord or a single note
     * @param isMinor Whether or not the chord is minor
     * @param octave The octave of the leadingNote
     * @param fretNumber The fretNumber of the leadingNote
     * @param tabsLine The line on which the note goes on for tabs
     */
    public void addChord(String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        author.addChord(type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
    }

    /**
     * Edits a song
     * Temporarily unimplemented
     */
    public void editSong(Song selectedSong, int position) {

    }

    /**
     * Saves project
     * Temporarily unimplemented
     * @param selectedSong The song to be saved
     */
    public void saveProject(Song selectedSong) {
        // Stub implementation
    }

    /**
     * Publishes a song
     * @param selectedSong The selected song to publish
     */
    public void publishSong(Song selectedSong) {
        if (selectedSong != null) {
            selectedSong.setPublished(true);
        }
    }

    /**
     * Removes a song
     * @param selectedSong The selected song to be removed
     */
    public void removeSong(Song selectedSong) {
        SongLibrary.getInstance().removeSong(selectedSong);

    }

    /**
     * Play a songs
     */
    public void playSong() {
        if (selectedSong != null) {
            selectedSong.playSong();
        }
    }

    /**
     * Selects an instrument
     * @param instrumentName The name of the instrument
     * @return The selected instrument
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
     * Changes the volume of the music
     * Currently not in use
     * @param volume The new volume
     */
    public void changeVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.currentVolume = volume;
        }
    }

    /**
     * Exports the song to a text file
     */
    public void exportSong() {
        if(selectedSong != null)
            selectedSong.printTabsToTextFile();
    }

    /**
     * Get comments for the selected song
     * @return The arrayList of comments
     */
    public ArrayList<Comment> getComments() {
        if (selectedSong == null) {
            return new ArrayList<>();
        }
        return selectedSong.getComments();
    }

    /**
     * Adds a comment to the currently selected song
     * @param commentText The text of the comment
     * @param commenterName The name of the commenter
     * @param username The username of the commenter
     */
    public void addComment(String commentText, String commenterName, String username) {
        Comment newComment = new Comment(commentText, commenterName, username);
        selectedSong.addComment(newComment);
    }

    /**
     * Shows comments for the selected song in the terminal
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
     * Searches the SongLibrary for songs with a matching title
     * @param title The title searched for
     * @return An arrayList containing all songs with the same title as provided
     */
    public ArrayList<Song> searchByTitle(String title) {
        ArrayList<Song> results = SongLibrary.getInstance().searchByTitle(title);
        this.searchResults = results;
        return results;
    }

    /**
     * Searches the SongLibrary for songs with a matching author
     * @param author The name of the author searched for
     * @return An arrayList containing all songs with the same author name as provided
     */
    public ArrayList<Song> searchByAuthor(String author) {
        ArrayList<Song> results = SongLibrary.getInstance().searchByArtist(author);
        this.searchResults = results;
        return results;
    }


    /**
     * Selects a song from the arrayList of search results
     * @param position The position of the song in the arrayList to be selected
     * @return The selected song
     */
    public Song selectSongFromResults(int position) {
        this.selectedSong = searchResults.get(position);
        return selectedSong;
    }

    /**
     * Search for a song by genre, artist, or title
     * @param genre The genre of the song
     * @param artist The author of the song
     * @param title The title of the song
     * @return An arraylist of songs that match the given parameters
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
     * Play ear training game, ear training game not implemented yet
     */
//    public EarTrainingGame playGame() {
//        return EarTrainingGame.getInstance(); // Assuming this method exists
//    }
}
