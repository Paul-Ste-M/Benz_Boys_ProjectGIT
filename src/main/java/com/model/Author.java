package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Author extends User {
    private ArrayList<UUID> createdSongs;
    private Measure selectedMeasure;

    public Author(String firstName, String lastName, String userName, String password, String email) {
        super(firstName, lastName, userName, password, email);
        this.createdSongs = new ArrayList<>();
    }

    public Author(User user) {
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail());
        this.createdSongs = new ArrayList<>();
        user.setAuthor(true);
        DataReader DataReader = new DataReader();
    }
    public void addSong(Song song) {
        // Implementation to add a song
    }

    public void removeSong(Song song) {
        // Implementation to remove a song
    }

    public Song createNewSong(String title, Author author, Song selectedSong) {
        // Implementation to create a new song
        return new Song(title, author);}

    public void publishSong(Song song) {
        // Implementation to publish a song
    }

    public void editSong(Song song) {
        // Implementation to edit a song
    }

    public void saveSong(Song song) {
        // Implementation to save a song
    }

    public Measure addMeasure(Measure measure) {
        this.selectedMeasure = measure;
        return measure;
    }

    public void addGenre(String genre) {
        // Implementation to add a genre
    }

    public void editMeasure(Measure measure) {
        // Implementation to edit a measure
    }

    public void removeMeasure(Measure measure, int position) {
        // Implementation to remove a measure at a specific position
    }

    public void editChord(String type, String pitch, int position) {
        // Implementation to edit a chord
    }

    public void makeNoteIntoChord(int position) {
        // Implementation to make a note into a chord
    }

    public void makeChordIntoNote(int position) {
        // Implementation to make a chord into a note
    }

    public void removeNote(int position) {
        // Implementation to remove a note
    }


}
