package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Author extends User {
    private ArrayList<UUID> createdSongs;
    private Measure selectedMeasure;
    
    // We might not even need this constructor
    public Author(String firstName, String lastName, String userName, String password, String email) {
        super(firstName, lastName, userName, password, email);
        
        this.createdSongs = this.getCreatedSongs();
    }

    public Author(String firstName, String lastName, String userName, String password, String email, String userID, ArrayList<UUID> createdSongs, boolean isAuthor) {
        super(firstName, lastName, userName, password, email, userID, createdSongs, isAuthor);
    }

    public void addSong(Song song) {
        // Implementation to add a song
        createdSongs.add(song.getSongID());
    }

    public void addSong(UUID songID) {
        createdSongs.add(songID);
    }

    public void removeSong(Song song) {
        // Implementation to remove a song
        createdSongs.remove(song.getSongID());
    }
    
    public void removeSong(UUID songID) {
        // Implementation to remove a song
        createdSongs.remove(songID);
    }

    public Song createNewSong(String title, Author author, Song selectedSong) {
        // Implementation to create a new song
        return new Song(title, author);}

    public void publishSong(Song song) {
        // Implementation to publish a song
        song.isPublished();
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
