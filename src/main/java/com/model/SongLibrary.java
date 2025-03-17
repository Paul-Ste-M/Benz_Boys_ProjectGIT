package com.model;

import java.util.ArrayList;

public class SongLibrary {
    private static SongLibrary songLibrary;  // Singleton instance
    private ArrayList<Song> songs;           // List of songs

    // Private constructor to prevent external instantiation
    private SongLibrary() {
        songs = new ArrayList<>();
    }

    // Singleton instance getter
    public static SongLibrary getInstance() {
        if (songLibrary == null) {
            songLibrary = new SongLibrary();
        }
        return songLibrary;
    }
    // Returns the list of songs for DataWriter
    public ArrayList<Song> getSongs() {
        return songs;
    }

    // Get a song by name and author
    public Song getSong(String name, String author) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(name) && song.getAuthor().equalsIgnoreCase(author)) {
                return song;
            }
        }
        return null; // Song not found
    }

    // Search songs by artist
    public ArrayList<Song> searchByArtist(String artist) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAuthor().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
    }

    // Search songs by title
    public ArrayList<Song> searchByTitle(String title) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                result.add(song);
            }
        }
        return result;
    }

    // Search songs by genre
    public ArrayList<Song> searchByGenre(String genre) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            for (Genre g : song.getGenres()) {
                if (g.name().equalsIgnoreCase(genre)) {
                    result.add(song);
                }
            }
        }
        return result;
    }

    // Save songs (stub method, implementation depends on storage)
    public boolean saveSongs() {
        System.out.println("Saving songs...");
        return true;
    }

    // Publish a song
    public void publishSong(Song selectedSong) {
        selectedSong.setPublished(true);
        System.out.println("Song published: " + selectedSong.getTitle());
    }

    // Remove a song from the library
    public void removeSong(Song selectedSong) {
        songs.remove(selectedSong);
    }

    // Add a new song to the library
    public void addSong(String title, Author author) {
        Song newSong = new Song(title, author);
        songs.add(newSong);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void displaySongs() {
        for (Song song : songs) {
            System.out.println(song.getTitle() + " by " + song.getAuthor());
        }
    }

    public static void main(String[] args) {
        SongLibrary songLibrary = SongLibrary.getInstance();

        Author author = new Author("John", "Doe", "johndoe", "password", "jdoe@gamil.com");
        songLibrary.addSong("My Song", author);
        songLibrary.addSong("Your Song", author);
        songLibrary.addSong("Our Song", author);

        songLibrary.displaySongs();

        
    }

}
