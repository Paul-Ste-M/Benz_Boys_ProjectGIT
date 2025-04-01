package com.model;

import java.util.ArrayList;

/**
 * Singleton class that manages a library of all available songs.
 * @author Benz Boyz
 */
public class SongLibrary {
    private static SongLibrary songLibrary;  
    private ArrayList<Song> songs;           

    /**
     * Private constructor to enforce singleton pattern.
     */
    private SongLibrary() {
        songs = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the SongLibrary.
     * @return the SongLibrary instance
     */
    public static SongLibrary getInstance() {
        if (songLibrary == null) {
            songLibrary = new SongLibrary();
        }
        return songLibrary;
    }

    /**
     * Returns the list of all songs in the library.
     * @return list of Song objects
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Searches for a song by title and author.
     * @param name   the song title
     * @param author the author’s name
     * @return the Song object if found, or null if not found
     */
    public Song getSong(String name, String author) {
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(name) && song.getAuthor().equalsIgnoreCase(author)) {
                return song;
            }
        }
        return null;
    }

    /**
     * Searches for songs by artist name.
     * @param artist the artist to search for
     * @return a list of matching songs
     */
    public ArrayList<Song> searchByArtist(String artist) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAuthor().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Searches for songs by title.
     * @param title the song title to search for
     * @return a list of matching songs
     */
    public ArrayList<Song> searchByTitle(String title) {
        ArrayList<Song> result = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Searches for songs by genre.
     * @param genre the genre to search for
     * @return a list of matching songs
     */
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

    /**
     * Saves all songs in the library to persistent storage.
     * @return true if saving was successful, false otherwise
     */
    public boolean saveSongs() {
        return DataWriter.saveSongs(songs);
    }

    /**
     * Publishes a song by setting its published status to true.
     * @param selectedSong the song to publish
     */
    public void publishSong(Song selectedSong) {
        selectedSong.setPublished(true);
        System.out.println("Song published: " + selectedSong.getTitle());
    }

    /**
     * Removes a song from the library.
     * @param selectedSong the song to remove
     */
    public void removeSong(Song selectedSong) {
        songs.remove(selectedSong);
    }

    /**
     * Creates a new song and adds it to the library.
     * @param title  the song title
     * @param author the author of the song
     */
    public void addSong(String title, Author author) {
        Song newSong = new Song(title, author);
        songs.add(newSong);
    }

    /**
     * Adds an existing song to the library.
     * @param song the Song object to add
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Displays all songs in the library by printing their titles and authors to the console.
     */
    public void displaySongs() {
        for (Song song : songs) {
            System.out.println(song.getTitle() + " by " + song.getAuthor());
        }
    }

    // /**
    //  * Test main method to demonstrate functionality.
    //  *
    //  * @param args command-line arguments (not used)
    //  */
    // public static void main(String[] args) {
    //     SongLibrary songLibrary = SongLibrary.getInstance();

    //     Author author = new Author("John", "Doe", "johndoe", "password", "jdoe@gamil.com");
    //     songLibrary.addSong("My Song", author);
    //     songLibrary.addSong("Your Song", author);
    //     songLibrary.addSong("Our Song", author);

    //     songLibrary.displaySongs();
    // }
}
