package com.model;

import java.util.ArrayList;
import java.util.List;

public class Chord {
    private List<Note> notes;
    private Note leadingNote;
    private boolean isSingleNote;

    public Chord() {
        this.notes = new ArrayList<>();
        this.isSingleNote = false;
    }

    public Chord(String type, String leadingNote, boolean isSingleNote) {
        this.notes = new ArrayList<>();
        this.isSingleNote = isSingleNote;
        
        // Convert leadingNote to an actual Note object (assuming Type and Pitch enums exist)
        this.leadingNote = new Note(Pitch.valueOf(leadingNote), Type.valueOf(type), 1, false, false);
        
        if (!isSingleNote) {
            // Generate a chord based on the leading note
            generateChord();
        } else {
            notes.add(this.leadingNote);
        }
    }

    private void generateChord() {
        notes.add(leadingNote);
    }

    public void addNote(Note newNote, int position) {
        notes.add(position, newNote);
    }
    
    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    public void addNotes(ArrayList <Note> newNotes) {
        notes = (newNotes);
    }

    public void playChord() {
        for (Note note : notes) {
            note.playNote();
        }
    }

    public void makeChordIntoNote() {
        if (!notes.isEmpty()) {
            this.leadingNote = notes.get(0);
            this.isSingleNote = true;
            this.notes.clear();
            this.notes.add(leadingNote);
        }
    }

    public void makeNoteIntoChord() {
        if (isSingleNote) {
            isSingleNote = false;
            generateChord();
        }
    }

    public void changeNote(String type, String pitch) {
        this.leadingNote = new Note(Pitch.valueOf(pitch), Type.valueOf(type), 1, false, false);
        if (isSingleNote) {
            this.notes.clear();
            this.notes.add(leadingNote);
        } else {
            generateChord();
        }
    }
}
