package com.model;

import java.util.UUID;
import java.util.ArrayList;

public class Song {
    private String title;
    private String author;
    private UUID songID;
    private ArrayList<Genre> genre;
    private boolean published;
    private ArrayList<Comment> comments;

    public Song(String title, Author author) {
    }

    public void addMeasure(Measure measure) {
    }

    public void removeMeasure(Measure measure, int position) {
    }

    public void addGenre(String genre) {
    }

    public void playSong() {
        // Implementation for playing the song
    }

    public void addComment(String comment) {

    }

    public ArrayList<String> getComments(Song selectedSong) {
            return null;
    }
}
