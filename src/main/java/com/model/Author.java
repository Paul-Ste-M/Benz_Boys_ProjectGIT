package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Author extends User {
    private ArrayList<UUID> createdSongs;
    private Measure selectedMeasure;
    private Song selectedSong; // for convenience when editing a song

    public Author(String firstName, String lastName, String userName, String password, String email) {
        super(firstName, lastName, userName, password, email);
        this.createdSongs = this.getCreatedSongs();
        // Set this user to be an author
        this.setAuthorStatusToTrue();
    }

    public Author(String firstName, String lastName, String userName, String password, String email, 
                  String userID, ArrayList<UUID> createdSongs, boolean isAuthor) {
        super(firstName, lastName, userName, password, email, userID, createdSongs, isAuthor);
        this.createdSongs = this.getCreatedSongs();
        this.setAuthorStatusToTrue();

    }

    public Author(User user) {
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail());
        this.createdSongs = this.getCreatedSongs();
        // Set this user to be an author
        this.setAuthorStatusToTrue();
    }

    // Adds a song: records its ID and adds it to the central SongLibrary.
    public void addSong(Song song) {
        createdSongs.add(song.getSongID());
        SongLibrary.getInstance().addSong(song);
        System.out.println("Song added: " + song.getTitle());
    }

    public void addSong(UUID songID) {
        createdSongs.add(songID);
        System.out.println("Song ID added: " + songID.toString());
    }

    // Removes a song from both the Author's created list and the SongLibrary.
    public void removeSong(Song song) {
        createdSongs.remove(song.getSongID());
        SongLibrary.getInstance().removeSong(song);
        System.out.println("Song removed: " + song.getTitle());
    }
    
    public void removeSong(UUID songID) {
        createdSongs.remove(songID);
        System.out.println("Song ID removed: " + songID.toString());
    }

    // Creates a new song. If a non-null selectedSong is provided, its content is cloned
    // (genres, measures, and comments are copied) into the new song.
    public Song createNewSong(String title, Author author, Song selectedSong) {
        Song newSong = new Song(title, author);
        if (selectedSong != null) {
            // Clone genres
            for (Genre g : selectedSong.getGenres()) {
                newSong.addGenre(g.toString());
            }
            // Clone measures (deep copy)
            for (Measure m : selectedSong.getMeasures()) {
                Measure copiedMeasure = copyMeasure(m);
                newSong.addMeasure(copiedMeasure);
            }
            // Clone comments
            for (Comment comment : selectedSong.getComments()) {
                newSong.addComment(comment.getComment());
            }
        }
        addSong(newSong);
        // Also set the newly created song as the current selected song for further editing.
        this.selectedSong = newSong;
        return newSong;
    }

    // Helper method: deep-copies a measure by cloning each chord.
    private Measure copyMeasure(Measure measure) {
        Measure newMeasure = new Measure();
        for (Chord chord : measure.getChords()) {
            Chord newChord = copyChord(chord);
            newMeasure.addChord(newChord);
        }
        return newMeasure;
    }

    // Helper method: deep-copies a chord by cloning its leading note and each of its notes.
    private Chord copyChord(Chord chord) {
        // Create a new chord with the same type, isSingleNote, and isMinor settings.
        Chord newChord = new Chord(chord.getType().toString(), chord.isSingleNote(), chord.isMinor());
        newChord.setLeadingNote(copyNote(chord.getLeadingNote()));
        for (Note note : chord.getNotes()) {
            newChord.addNote(copyNote(note));
        }
        return newChord;
    }

    // Helper method: copies a note. (Assumes that Note has getters for pitch and type.)
    private Note copyNote(Note note) {
        return new Note(note.getPitch(), note.getType());
    }

    // Publishes a song by setting its published flag and notifying the SongLibrary.
    public void publishSong(Song song) {
        song.setPublished(true);
        SongLibrary.getInstance().publishSong(song);
        System.out.println("Song published: " + song.getTitle());
    }

    // Edits a song. (Here we simply print a message.
    // In a full application you might update the title or other attributes.)
    public void editSong(Song song) {
        System.out.println("Editing song: " + song.getTitle());
        // For example, if Song had a setTitle method:
        // song.setTitle(newTitle);
    }

    // Saves a song by writing all songs from the SongLibrary via DataWriter.
    public void saveSong(Song song) {
        DataWriter.saveSongs(SongLibrary.getInstance().getSongs());
        System.out.println("Song saved: " + song.getTitle());
    }

    // Adds a measure. The provided measure is set as the currently selected measure
    // and added to the selected song (if one is set).
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

    // Sets the currently selected song for editing.
    public void selectSong(Song song) {
        this.selectedSong = song;
        System.out.println("Selected song: " + song.getTitle());
    }

    // Adds a genre to the currently selected song.
    public void addGenre(String genre) {
        if (selectedSong != null) {
            selectedSong.addGenre(genre);
            System.out.println("Genre " + genre + " added to song " + selectedSong.getTitle());
        } else {
            System.out.println("No song selected. Cannot add genre.");
        }
    }

    public void setMeasure(Measure measure) {
        this.selectedMeasure = measure;
    }

    // Edits a measure. (A placeholder for further measure-editing functionality.)
    public void editMeasure(Measure measure) {
        System.out.println("Editing measure with beat count: " + measure.getBeatCount());
        // Further logic to change chords or beat count can be added here.
    }

    // Removes a measure from the currently selected song at a given position.
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

    // Edits a chord in the currently selected measure at the given position.
    // The chord’s leading note is updated by calling its changeNote method.
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

    // Converts a note (currently a single note) into a chord in the selected measure.
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

    // Converts a chord into a single note in the selected measure.
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

    // Removes a note from the first chord in the selected measure at the given note position.
    // (In a real application you might specify which chord to edit.)
    public void removeNote(int position) {
        if (selectedMeasure != null) {
            ArrayList<Chord> chords = (ArrayList<Chord>) selectedMeasure.getChords();
            if (!chords.isEmpty()) {
                Chord chord = chords.get(0); // for simplicity, operate on the first chord
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
