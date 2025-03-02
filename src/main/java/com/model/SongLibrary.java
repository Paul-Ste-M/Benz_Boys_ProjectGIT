package com.model;

import java.util.ArrayList;

public class SongLibrary {
    private ArrayList<Song> songs;
    private SongLibrary songLibrary;

    private SongLibrary() {
        // Stub implementation
    }
    
    public static SongLibrary getInstance() {
        // Stub implementation
        return null;
    }
    
    public Song getSong(String name, String author) {
        // Stub implementation
        return null;
    }
    
    public ArrayList<Song> searchByArtist(String artist) {
        // Stub implementation
        return null;
    }
    
    public ArrayList<Song> searchByTitle(String title) {
        // Stub implementation
        return null;
    }
    
    public ArrayList<Song> searchByGenre(String genre) {
        // Stub implementation
        return null;
    }
    
    public boolean saveSongs() {
        // Stub implementation
        return false;
    }
    
    public void publishSong(Song selectedSong) {
        // Stub implementation
    }
    
    public void removeSong(Song selectedSong) {
        // Stub implementation
    }
    
    public void addSong(String title, Author author) {
        // Stub implementation
    }
}

