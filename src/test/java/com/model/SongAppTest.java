
package com.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;
/** SongAppTest.java
 * Originally the tests worked with the SongApp class, but
 * reworked the SongApp, but due to the time constraints I wasn't able to implement the newer SongApp class.
 * @author Paul Stevens
 */

public class SongAppTest {

    private SongApp songApp;
    private User testUser;
    private Author testAuthor;
    private Song selectedSong; // Declare selectedSong as a field

    @Test
    public void testLogin() {
        // Simulate login
        User loggedInUser = songApp.login("testUser", "password");
        
        // Check that the login returns a valid user
        assertNotNull(loggedInUser);
        assertEquals("testUser", loggedInUser.getUsername());
    }
    

    @Test
    public void testSignUp() {
        // Simulate signup
        User newUser = songApp.signUp("newUser", "Alice", "Smith", "alice.smith@example.com", "newPassword");

        // Check that the signup returns a valid user
        assertNotNull(newUser);
        assertEquals("newUser", newUser.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSong_EmptySongName() {
        // Try to add a song with an empty name
        songApp.addSong("", testAuthor);  // Should throw IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSong_NullSongName() {
        // Try to add a song with a null name
        songApp.addSong(null, testAuthor);  // Should throw IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSong_NullAuthor() {
        // Try to add a song with a null author
        songApp.addSong("Test Song", null);  // Should throw IllegalArgumentException
    }

    @Test
    public void testStartSong() {
        // Start a new song with a title
        Song newSong = songApp.startSong("New Song");

        // Verify the new song was created successfully
        assertNotNull(newSong);
        assertEquals("New Song", newSong.getTitle());
        assertEquals(testAuthor, newSong.getAuthor());  // Check that the author is set correctly
    }
    @Test
public void testAddChord() {
    // Arrange: create a sample song and select it
    Song newSong = songApp.startSong("New Song");
    selectedSong = newSong;
    
    // Act: Add a chord to the song
    songApp.addChord("Major", "C", true, false, "4", "5", 1);
    
    // Assert: Check if the chord was added correctly
    assertEquals(1, selectedSong.getMeasures().get(0).getChords().size());
}

    @Test
    public void testSearchByTitle() {
        // Add some songs to the library (assuming this is done through addSong)
        songApp.addSong("Song One", testAuthor);
        songApp.addSong("Song Two", testAuthor);
        
        // Search by title
        ArrayList<Song> searchResults = songApp.searchByTitle("Song One");

        // Check that we have the correct search results
        assertNotNull(searchResults);
        assertEquals(1, searchResults.size());
        assertEquals("Song One", searchResults.get(0).getTitle());
    }

    @Test
    public void testSearchByAuthor() {
        // Add songs to the library
        songApp.addSong("Song One", testAuthor);
        songApp.addSong("Song Two", testAuthor);

        // Search by author
        ArrayList<Song> searchResults = songApp.searchByAuthor("John Doe");

        // Check that the search returns the correct results
        assertNotNull(searchResults);
        assertEquals(2, searchResults.size());
    }

    @Test
    public void testPublishSong() {
        // Add and start a song
        Song song = songApp.startSong("Publish Test Song");

        // Publish the song
        songApp.publishSong(song);

        // Assert the song is published
        assertTrue(song.isPublished());
    }

    @Test
    public void testSelectSongFromResults() {
        // Add some songs to the library
        songApp.addSong("Song One", testAuthor);
        songApp.addSong("Song Two", testAuthor);

        // Search for songs
        ArrayList<Song> searchResults = songApp.searchByTitle("Song One");

        // Select a song from the search results
        Optional<Song> selectedSong = songApp.selectSongFromResults(0);

        // Check that a song was selected
        assertTrue(selectedSong.isPresent());
        assertEquals("Song One", selectedSong.get().getTitle());
    }

    @Test
    public void testChangeVolume() {
        // Change the volume
        songApp.changeVolume(50);
        
        // Since volume is stored as an integer, we'll check that the volume is updated properly
        // Assuming there's a getter for current volume in the SongApp class.
        assertEquals(50, songApp.getVolume());
    }

    @Test
    public void testShowComments() {
        // Start a new song
        Song newSong = songApp.startSong("Song with Comments");

        // Add a comment to the song
        songApp.addComment("Great song!", "Alice", "aliceUser");

        // Check that comments are properly displayed
        songApp.showComments();
        // Manually verify printed output in your test run
    }

    @Test
    public void testSelectInstrumentGuitar() {
        // Select an instrument from the list
        Instrument selectedInstrument = songApp.selectInstrument("Guitar");

        // Check if the instrument is selected
        assertNotNull(selectedInstrument);
        assertEquals("Guitar", selectedInstrument.getInstrumentName().toString());
    }
    @Test
    public void testSelectInstrumentPiano() {
        // Select an instrument from the list
        Instrument selectedInstrument = songApp.selectInstrument("Piano");

        // Check if the instrument is selected
        assertNotNull(selectedInstrument);
        assertEquals("Piano", selectedInstrument.getInstrumentName().toString());
    }
    @Test
public void testRemoveSong() {
    // Arrange: create and add a new song
    Song newSong = songApp.startSong("New Song");
    
    // Act: Remove the song from the library
    songApp.removeSong(newSong);
    
    // Assert: Check that the song was removed
    assertFalse(SongLibrary.getInstance().getSongs().contains(newSong));
}
}


