// @author Abdoula

package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class SongLibraryTest {

    private SongLibrary songLibrary;
    private UserList userList;

    @Before
    public void setUp() {
        // Reset the SongLibrary singleton.
        songLibrary = SongLibrary.getInstance();
        songLibrary.getSongs().clear();
        // Reset the UserList singleton.
        userList = UserList.getInstance();
        userList.getUsers().clear();
    }

    @Test
    public void testSingletonInstance() {
        assertNotNull("SongLibrary instance should not be null", SongLibrary.getInstance());
        SongLibrary anotherInstance = SongLibrary.getInstance();
        assertEquals("SongLibrary should be singleton", songLibrary, anotherInstance);
    }

    @Test
    public void testAddSongByTitleAndAuthor() {
        Author author = new Author("John", "Doe", "johndoe", "password", "john@example.com");
        userList.addUser(author);
        // Use the SongLibrary method that creates and adds a new song.
        songLibrary.addSong("My Song", author);
        ArrayList<Song> songs = songLibrary.getSongs();
        assertEquals("There should be one song in the library", 1, songs.size());
        Song addedSong = songs.get(0);
        assertEquals("Song title should be 'My Song'", "My Song", addedSong.getTitle());
        assertEquals("Song author should match the author's full name", author.getFullName(), addedSong.getAuthor());
    }

    @Test
    public void testAddSongBySongObject() {
        Author author = new Author("Jane", "Smith", "janesmith", "password", "jane@example.com");
        userList.addUser(author);
        Song song = new Song("Another Song", author);
        songLibrary.addSong(song);
        ArrayList<Song> songs = songLibrary.getSongs();
        assertTrue("Library should contain the added song", songs.contains(song));
    }

    @Test
    public void testGetSong() {
        Author author = new Author("John", "Doe", "johndoe", "password", "john@example.com");
        userList.addUser(author);
        Song song1 = new Song("Song One", author);
        Song song2 = new Song("Song Two", author);
        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        Song found = songLibrary.getSong("Song Two", author.getFullName());
        assertNotNull("getSong should find the song", found);
        assertEquals("Found song should have title 'Song Two'", "Song Two", found.getTitle());
        assertEquals("Found song's author should be correct", author.getFullName(), found.getAuthor());
    }

    @Test
    public void testSearchByArtist() {
        Author author1 = new Author("Alice", "Wonderland", "alicew", "password", "alice@example.com");
        Author author2 = new Author("Bob", "Builder", "bobbuilder", "password", "bob@example.com");
        userList.addUser(author1);
        userList.addUser(author2);
        Song song1 = new Song("Song A", author1);
        Song song2 = new Song("Song B", author1);
        Song song3 = new Song("Song C", author2);
        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        songLibrary.addSong(song3);
        ArrayList<Song> result = songLibrary.searchByArtist(author1.getFullName());
        assertEquals("Should find 2 songs by the artist", 2, result.size());
        for (Song song : result) {
            assertEquals("Song author should match", author1.getFullName(), song.getAuthor());
        }
    }

    @Test
    public void testSearchByTitle() {
        Author author = new Author("Charles", "Darwin", "cdarwin", "password", "charles@example.com");
        userList.addUser(author);
        Song song1 = new Song("Evolution", author);
        Song song2 = new Song("Evolution", author);
        Song song3 = new Song("Origin", author);
        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        songLibrary.addSong(song3);
        ArrayList<Song> result = songLibrary.searchByTitle("Evolution");
        assertEquals("Should find 2 songs with the title 'Evolution'", 2, result.size());
        for (Song song : result) {
            assertEquals("Song title should be 'Evolution'", "Evolution", song.getTitle());
        }
    }

    @Test
    public void testSearchByGenre() {
        Author author = new Author("David", "Bowie", "dbowie", "password", "david@example.com");
        userList.addUser(author);
        Song song1 = new Song("Starman", author);
        Song song2 = new Song("Heroes", author);
        // Add genres using the addGenre method; note that the Genre enum expects uppercase values.
        song1.addGenre("ROCK");
        song2.addGenre("POP");
        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        ArrayList<Song> rockSongs = songLibrary.searchByGenre("Rock");
        assertEquals("Should find 1 rock song", 1, rockSongs.size());
        assertEquals("The rock song should be 'Starman'", "Starman", rockSongs.get(0).getTitle());
        ArrayList<Song> popSongs = songLibrary.searchByGenre("pop");
        assertEquals("Should find 1 pop song", 1, popSongs.size());
        assertEquals("The pop song should be 'Heroes'", "Heroes", popSongs.get(0).getTitle());
    }

    @Test
    public void testPublishSong() {
        Author author = new Author("Ella", "Fitzgerald", "ella", "password", "ella@example.com");
        userList.addUser(author);
        Song song = new Song("Dream a Little Dream", author);
        songLibrary.addSong(song);
        songLibrary.publishSong(song);
        assertTrue("Song should be published", song.isPublished());
    }

    @Test
    public void testRemoveSong() {
        Author author = new Author("Freddie", "Mercury", "fmercury", "password", "freddie@example.com");
        userList.addUser(author);
        Song song = new Song("Bohemian Rhapsody", author);
        songLibrary.addSong(song);
        assertTrue("Song should be in the library", songLibrary.getSongs().contains(song));
        songLibrary.removeSong(song);
        assertFalse("Song should be removed", songLibrary.getSongs().contains(song));
    }

    @Test
    public void testSaveSongs() {
        // This test assumes that DataWriter.saveSongs(songs) returns true.
        Author author = new Author("George", "Harrison", "gharrison", "password", "george@example.com");
        userList.addUser(author);
        Song song = new Song("Something", author);
        songLibrary.addSong(song);
        boolean result = songLibrary.saveSongs();
        assertTrue("saveSongs should return true", result);
    }

    @Test
    public void testDisplaySongs() {
        // This test ensures that displaySongs() runs without throwing an exception.
        Author author = new Author("Henry", "Rollins", "hrollins", "password", "henry@example.com");
        userList.addUser(author);
        Song song1 = new Song("Song One", author);
        Song song2 = new Song("Song Two", author);
        songLibrary.addSong(song1);
        songLibrary.addSong(song2);
        try {
            songLibrary.displaySongs();
        } catch (Exception e) {
            fail("displaySongs() should not throw an exception.");
        }
    }
}
