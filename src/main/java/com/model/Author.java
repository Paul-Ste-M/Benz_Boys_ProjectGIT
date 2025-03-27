package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an Author who can create, edit, and manage songs.
 * Inherits from the User class and adds capabilities specific to song creation and manipulation.
 */
public class Author extends User {
    private ArrayList<UUID> createdSongs;
    private Measure selectedMeasure;
    private Song selectedSong; // for convenience when editing a song

    /**
     * Constructs an Author with basic user details.
     *
     * @param firstName The author's first name.
     * @param lastName The author's last name.
     * @param userName The author's username.
     * @param password The author's password.
     * @param email The author's email.
     */
    public Author(String firstName, String lastName, String userName, String password, String email) {
        super(firstName, lastName, userName, password, email);
        this.createdSongs = this.getCreatedSongs();
        this.setAuthorStatusToTrue();
    }

    /**
     * Constructs an Author with all attributes, including userID, created songs, and author status.
     *
     * @param firstName The author's first name.
     * @param lastName The author's last name.
     * @param userName The author's username.
     * @param password The author's password.
     * @param email The author's email.
     * @param userID The user's unique ID.
     * @param createdSongs List of song UUIDs created by the author.
     * @param isAuthor Whether the user is an author.
     */
    public Author(String firstName, String lastName, String userName, String password, String email, 
                  String userID, ArrayList<UUID> createdSongs, boolean isAuthor) {
        super(firstName, lastName, userName, password, email, userID, createdSongs, isAuthor);
        this.createdSongs = this.getCreatedSongs();
    }

    /**
     * Adds a song to the author's list and the SongLibrary.
     *
     * @param song The song to be added.
     */
    public void addSong(Song song) {
        createdSongs.add(song.getSongID());
        SongLibrary.getInstance().addSong(song);
        System.out.println("Song added: " + song.getTitle());
    }

    /**
     * Adds a song by its UUID to the author's created songs list.
     *
     * @param songID The UUID of the song.
     */
    public void addSong(UUID songID) {
        createdSongs.add(songID);
        System.out.println("Song ID added: " + songID.toString());
    }

    /**
     * Removes a song from the author's list and from the SongLibrary.
     *
     * @param song The song to be removed.
     */
    public void removeSong(Song song) {
        createdSongs.remove(song.getSongID());
        SongLibrary.getInstance().removeSong(song);
        System.out.println("Song removed: " + song.getTitle());
    }

    /**
     * Removes a song by its UUID from the author's list.
     *
     * @param songID The UUID of the song.
     */
    public void removeSong(UUID songID) {
        createdSongs.remove(songID);
        System.out.println("Song ID removed: " + songID.toString());
    }

    /**
     * Creates a new song and optionally clones the content of an existing one.
     *
     * @param title The title of the new song.
     * @param author The author creating the song.
     * @param selectedSong An optional song to clone.
     * @return The newly created song.
     */
    public Song createNewSong(String title, Author author, Song selectedSong) {
        Song newSong = new Song(title, author);
        if (selectedSong != null) {
            for (Genre g : selectedSong.getGenres()) {
                newSong.addGenre(g.toString());
            }
            for (Measure m : selectedSong.getMeasures()) {
                Measure copiedMeasure = copyMeasure(m);
                newSong.addMeasure(copiedMeasure);
            }
            for (Comment comment : selectedSong.getComments()) {
                newSong.addComment(comment.getComment());
            }
        }
        addSong(newSong);
        this.selectedSong = newSong;
        return newSong;
    }

    /**
     * Deep copies a measure by cloning all its chords.
     *
     * @param measure The measure to copy.
     * @return A new copied Measure instance.
     */
    private Measure copyMeasure(Measure measure) {
        Measure newMeasure = new Measure();
        for (Chord chord : measure.getChords()) {
            Chord newChord = copyChord(chord);
            newMeasure.addChord(newChord);
        }
        return newMeasure;
    }

    /**
     * Deep copies a chord by cloning its notes and leading note.
     *
     * @param chord The chord to copy.
     * @return A new copied Chord instance.
     */
    private Chord copyChord(Chord chord) {
        Chord newChord = new Chord(chord.getType().toString(), chord.isSingleNote(), chord.isMinor());
        newChord.setLeadingNote(copyNote(chord.getLeadingNote()));
        for (Note note : chord.getNotes()) {
            newChord.addNote(copyNote(note));
        }
        return newChord;
    }

    /**
     * Copies a note by creating a new instance with the same properties.
     *
     * @param note The note to copy.
     * @return A new copied Note instance.
     */
    private Note copyNote(Note note) {
        return new Note(note.getPitch(), note.getType());
    }

    /**
     * Publishes a song and updates the SongLibrary.
     *
     * @param song The song to publish.
     */
    public void publishSong(Song song) {
        song.setPublished(true);
        SongLibrary.getInstance().publishSong(song);
        this.createdSongs.add(song.getSongID());
        System.out.println("Song published: " + song.getTitle());
    }

    /**
     * Placeholder method for editing a song.
     *
     * @param song The song to edit.
     */
    public void editSong(Song song) {
        System.out.println("Editing song: " + song.getTitle());
    }

    /**
     * Saves all songs via the DataWriter.
     *
     * @param song The song to save.
     */
    public void saveSong(Song song) {
        DataWriter.saveSongs(SongLibrary.getInstance().getSongs());
        System.out.println("Song saved: " + song.getTitle());
    }

    /**
     * Adds a measure to the currently selected song or stores it for later use.
     *
     * @param measure The measure to add.
     * @return The added measure.
     */
    public Measure addMeasure(Measure measure) {
        this.selectedMeasure = measure;
        if (selectedSong != null) {
            selectedSong.addMeasure(measure);
            System.out.println("Measure added to song: " + selectedSong.getTitle());
        } else {
            System.out.println("No song selected. Measure stored for later use.");
        }
        return measure;
    }

    /**
     * Sets the selected song for future editing.
     *
     * @param song The song to select.
     */
    public void selectSong(Song song) {
        this.selectedSong = song;
        System.out.println("Selected song: " + song.getTitle());
    }

    /**
     * Adds a genre to the currently selected song.
     *
     * @param genre The genre to add.
     */
    public void addGenre(String genre) {
        if (selectedSong != null) {
            selectedSong.addGenre(genre);
            System.out.println("Genre " + genre + " added to song " + selectedSong.getTitle());
        } else {
            System.out.println("No song selected. Cannot add genre.");
        }
    }

    /**
     * Placeholder method for editing a measure.
     *
     * @param measure The measure to edit.
     */
    public void editMeasure(Measure measure) {
        System.out.println("Editing measure with beat count: " + measure.getBeatCount());
    }

    /**
     * Removes a measure from the selected song at a specific position.
     *
     * @param measure The measure to remove.
     * @param position The index of the measure in the song.
     */
    public void removeMeasure(Measure measure, int position) {
        if (selectedSong != null) {
            ArrayList<Measure> measures = selectedSong.getMeasures();
            if (position >= 0 && position < measures.size()) {
                measures.remove(position);
                System.out.println("Removed measure at position " + position + " from song " + selectedSong.getTitle());
            } else {
                System.out.println("Invalid measure position.");
            }
        } else {
            System.out.println("No song selected. Cannot remove measure.");
        }
    }

    /**
     * Edits the chord in the currently selected measure at a specific position.
     *
     * @param type New chord type.
     * @param pitch New pitch for the leading note.
     * @param position Index of the chord to edit.
     */
    public void editChord(String type, String pitch, int position) {
        if (selectedMeasure != null) {
            ArrayList<Chord> chords = (ArrayList<Chord>) selectedMeasure.getChords();
            if (position >= 0 && position < chords.size()) {
                chords.get(position).changeNote(type, pitch);
                System.out.println("Edited chord at position " + position + " with new type " + type + " and pitch " + pitch);
            } else {
                System.out.println("Invalid chord position.");
            }
        } else {
            System.out.println("No measure selected. Cannot edit chord.");
        }
    }

    /**
     * Converts a note into a chord in the selected measure at a given position.
     *
     * @param position The position of the chord to convert.
     */
    public void makeNoteIntoChord(int position) {
        if (selectedMeasure != null) {
            ArrayList<Chord> chords = (ArrayList<Chord>) selectedMeasure.getChords();
            if (position >= 0 && position < chords.size()) {
                Chord chord = chords.get(position);
                chord.makeNoteIntoChord();
                System.out.println("Converted note to chord at position " + position);
            } else {
                System.out.println("Invalid chord position.");
            }
        } else {
            System.out.println("No measure selected. Cannot convert note to chord.");
        }
    }

    /**
     * Converts a chord into a single note in the selected measure at a given position.
     *
     * @param position The position of the chord to convert.
     */
    public void makeChordIntoNote(int position) {
        if (selectedMeasure != null) {
            ArrayList<Chord> chords = (ArrayList<Chord>) selectedMeasure.getChords();
            if (position >= 0 && position < chords.size()) {
                Chord chord = chords.get(position);
                chord.makeChordIntoNote();
                System.out.println("Converted chord to note at position " + position);
            } else {
                System.out.println("Invalid chord position.");
            }
        } else {
            System.out.println("No measure selected. Cannot convert chord to note.");
        }
    }

    /**
     * Removes a note from the first chord in the selected measure at a given position.
     *
     * @param position The index of the note to remove.
     */
    public void removeNote(int position) {
        if (selectedMeasure != null) {
            ArrayList<Chord> chords = (ArrayList<Chord>) selectedMeasure.getChords();
            if (!chords.isEmpty()) {
                Chord chord = chords.get(0); // for simplicity
                ArrayList<Note> notes = (ArrayList<Note>) chord.getNotes();
                if (position >= 0 && position < notes.size()) {
                    notes.remove(position);
                    System.out.println("Removed note at position " + position + " from the first chord in the selected measure.");
                } else {
                    System.out.println("Invalid note position.");
                }
            } else {
                System.out.println("No chords in the selected measure.");
            }
        } else {
            System.out.println("No measure selected. Cannot remove note.");
        }
    }
}
