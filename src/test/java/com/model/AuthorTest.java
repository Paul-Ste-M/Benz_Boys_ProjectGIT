// @author Abdoula

package com.model;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AuthorTest {

    // Reset the UserList singleton before each test.
    @Before
    public void initUserList() {
        UserList.getInstance().getUsers().clear();
    }
    
    @Test
    public void testAuthorConstructorAndGetAuthorId() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        assertNotNull("Author ID should not be null", author.getAuthorId());
    }
    
    @Test
    public void testAddSong() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Test Song", author);
        UUID songId = song.getSongID();
        author.addSong(song);
        // Assumes that getCreatedSongs() is inherited from User
        assertTrue("Song should be added to createdSongs", author.getCreatedSongs().contains(songId));
    }
    
    @Test
    public void testAddSongByUUID() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        UUID uuid = UUID.randomUUID();
        author.addSong(uuid);
        assertTrue("UUID should be added to createdSongs", author.getCreatedSongs().contains(uuid));
    }
    
    @Test
    public void testRemoveSong() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Test Song", author);
        UUID songId = song.getSongID();
        author.addSong(song);
        assertTrue("Song should be in createdSongs before removal", author.getCreatedSongs().contains(songId));
        author.removeSong(song);
        assertFalse("Song should be removed from createdSongs", author.getCreatedSongs().contains(songId));
    }
    
    @Test
    public void testRemoveSongByUUID() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        UUID uuid = UUID.randomUUID();
        author.addSong(uuid);
        assertTrue("UUID should be in createdSongs before removal", author.getCreatedSongs().contains(uuid));
        author.removeSong(uuid);
        assertFalse("UUID should be removed from createdSongs", author.getCreatedSongs().contains(uuid));
    }
    
    @Test
    public void testCreateNewSongCloning() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        // Create an original song with dummy data.
        Song original = new Song("Original Song", author);
        original.addGenre("ROCK");
        Measure m = new Measure();
        // Add a chord to the measure so that beatCount increases.
        m.addChord(new Chord("QUARTER", true, false), 0);
        original.addMeasure(m);
        // Assume Comment has a constructor taking a String and a method getComment()
        
        Song newSong = author.createNewSong("New Song", author, original);
        assertEquals("New song title should match", "New Song", newSong.getTitle());
        // Check that genres are cloned.
        List<Genre> newSongGenres = newSong.getGenres();
        boolean containsRock = newSongGenres.stream()
                .anyMatch(g -> g.toString().equalsIgnoreCase("ROCK"));
        assertTrue("New song should clone genres", containsRock);
        // Check that measures and comments are cloned.
        assertEquals("Measures should be cloned", original.getMeasures().size(), newSong.getMeasures().size());
        assertEquals("Comments should be cloned", original.getComments().size(), newSong.getComments().size());
        
        // Verify that the newly created song is set as selected by adding a genre.
        author.addGenre("POP");
        boolean containsPop = newSong.getGenres().stream()
                .anyMatch(g -> g.toString().equalsIgnoreCase("POP"));
        assertTrue("New song should have the added genre", containsPop);
    }
    
    @Test
    public void testPublishSong() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Publish Song", author);
        assertFalse("Song should initially be unpublished", song.isPublished());
        author.publishSong(song);
        assertTrue("Song should be published", song.isPublished());
        assertTrue("Published song should be added to createdSongs", author.getCreatedSongs().contains(song.getSongID()));
    }
    
    @Test
    public void testEditSong() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Edit Song", author);
        author.editSong(song);
        // No state change to verify; just ensure the method runs without error.
        assertNotNull(song);
    }
    
    @Test
    public void testSaveSong() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Save Song", author);
        author.saveSong(song);
        // Ensure the method runs without error.
        assertNotNull(song);
    }
    
    @Test
    public void testAddMeasure() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Measure Song", author);
        author.selectSong(song);
        Measure measure = new Measure();
        author.addMeasure(measure);
        List<Measure> measures = song.getMeasures();
        // The song constructor adds an initial measure; the new measure should be at the end.
        assertEquals("Measure should be added to song", measure, measures.get(measures.size() - 1));
    }
    
    @Test
    public void testSelectSongAndAddGenre() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Selected Song", author);
        author.selectSong(song);
        author.addGenre("JAZZ");
        List<Genre> genres = song.getGenres();
        boolean found = genres.stream().anyMatch(g -> g.toString().equalsIgnoreCase("JAZZ"));
        assertTrue("Selected song should contain added genre", found);
    }
    
    @Test
    public void testSetMeasure() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        author.setMeasure(measure);
        // No direct getter for selectedMeasure; passing this test if no exception is thrown.
        assertTrue(true);
    }
    
    @Test
    public void testEditMeasure() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        int initialBeatCount = measure.getBeatCount();
        author.editMeasure(measure);
        assertEquals("Measure beat count should remain unchanged", initialBeatCount, measure.getBeatCount());
    }
    
    @Test
    public void testRemoveMeasure() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Song song = new Song("Remove Measure Song", author);
        author.selectSong(song);
        Measure measure1 = new Measure();
        Measure measure2 = new Measure();
        song.addMeasure(measure1);
        song.addMeasure(measure2);
        int initialCount = song.getMeasures().size();
        author.removeMeasure(measure1, 0);
        assertEquals("Measure should be removed", initialCount - 1, song.getMeasures().size());
    }
    
    // The following three tests verify editing a chord in the selected measure.
    @Test
    public void testEditChordType() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        // Create a chord in single note mode with initial type QUARTER and leading note C.
        Chord chord = new Chord("QUARTER", true, false);
        Note note = new Note(Pitch.C, Type.QUARTER);
        chord.setLeadingNote(note);
        measure.addChord(chord);
        author.setMeasure(measure);
        
        // Change chord type to HALF while keeping pitch C.
        author.editChord("HALF", "C", 0);
        
        Note updated = chord.getLeadingNote();
        assertEquals("Chord type should be updated to HALF", Type.HALF, updated.getType());
        assertEquals("Chord pitch should remain C", Pitch.C, updated.getPitch());
    }
    
    @Test
    public void testEditChordPitch() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        Chord chord = new Chord("QUARTER", true, false);
        Note note = new Note(Pitch.C, Type.QUARTER);
        chord.setLeadingNote(note);
        measure.addChord(chord);
        author.setMeasure(measure);
        
        // Change chord pitch to D while keeping type QUARTER.
        author.editChord("QUARTER", "D", 0);
        
        Note updated = chord.getLeadingNote();
        assertEquals("Chord type should remain QUARTER", Type.QUARTER, updated.getType());
        assertEquals("Chord pitch should be updated to D", Pitch.D, updated.getPitch());
    }
    
    @Test
    public void testEditChordBoth() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        Chord chord = new Chord("QUARTER", true, false);
        Note note = new Note(Pitch.C, Type.QUARTER);
        chord.setLeadingNote(note);
        measure.addChord(chord);
        author.setMeasure(measure);
        
        // Change type to HALF and pitch to D.
        author.editChord("HALF", "D", 0);
        
        Note updated = chord.getLeadingNote();
        assertEquals("Chord type should be updated to HALF", Type.HALF, updated.getType());
        assertEquals("Chord pitch should be updated to D", Pitch.D, updated.getPitch());
    }
    
    @Test
    public void testAddChordWithPosition() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        author.setMeasure(measure);
        // Add a chord at position 0 using the addChord overload with position.
        author.addChord(0, "QUARTER", "C", true, false, "4", "3", 1);
        assertEquals("Chord should be added at position 0", 1, measure.getChords().size());
        Chord chord = measure.getChords().get(0);
        assertEquals("Chord type should be QUARTER", Type.QUARTER, chord.getType());
        assertEquals("Chord leading note should be C", Pitch.C, chord.getLeadingNote().getPitch());
    }
    
    @Test
    public void testAddChordWithoutPosition() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        author.setMeasure(measure);
        // Add a chord without specifying position.
        author.addChord("EIGHTH", "D", true, false, "5", "2", 0);
        assertEquals("Chord should be added", 1, measure.getChords().size());
        Chord chord = measure.getChords().get(0);
        assertEquals("Chord type should be EIGHTH", Type.EIGHTH, chord.getType());
        assertEquals("Chord leading note should be D", Pitch.D, chord.getLeadingNote().getPitch());
    }
    
    @Test
    public void testMakeNoteIntoChord() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        // Create a chord in single note mode.
        Chord chord = new Chord("QUARTER", true, false);
        Note note = new Note(Pitch.C, Type.QUARTER);
        chord.setLeadingNote(note);
        measure.addChord(chord);
        author.setMeasure(measure);
        author.makeNoteIntoChord(0);
        assertFalse("Chord should no longer be single note after conversion", chord.isSingleNote());
    }
    
    @Test
    public void testMakeChordIntoNote() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        // Create a chord in full chord mode by starting with isSingleNote false.
        Chord chord = new Chord("QUARTER", false, false);
        Note note = new Note(Pitch.C, Type.QUARTER);
        chord.setLeadingNote(note);
        // Add a second note to simulate a full chord.
        chord.addNote(new Note(Pitch.E, Type.QUARTER));
        measure.addChord(chord);
        author.setMeasure(measure);
        author.makeChordIntoNote(0);
        assertTrue("Chord should become a single note after conversion", chord.isSingleNote());
    }
    
    @Test
    public void testRemoveNote() {
        Author author = new Author("Test", "User", "testuser", "password", "test@example.com");
        Measure measure = new Measure();
        Chord chord = new Chord("QUARTER", true, false);
        // Add two notes to the chord.
        Note note1 = new Note(Pitch.C, Type.QUARTER);
        Note note2 = new Note(Pitch.E, Type.QUARTER);
        chord.addNote(note1);
        chord.addNote(note2);
        measure.addChord(chord);
        author.setMeasure(measure);
        int initialSize = chord.getNotes().size();
        author.removeNote(0);
        assertEquals("Note count should decrease by one", initialSize - 1, chord.getNotes().size());
    }
}
