package com.model;

import java.util.ArrayList;

public class Chord {
    private ArrayList<Note> notes;
    private Note leadingNote;
    private boolean isSingleNote;

    public Chord(String type, String leadingNote, boolean isSingleNote) {
    }
    
    public void playNotes() {
        // Implementation for playing the notes
    }
    
    public void makeChordIntoNote() {
        this.isSingleNote = true;
    }
    
    public void makeNoteIntoChord() {
        this.isSingleNote = false;
    }
    
    public void changeNote(String type, String pitch) {
    }
}

