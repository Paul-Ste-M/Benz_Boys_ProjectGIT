package com.model;

import java.util.ArrayList;
import java.util.List;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Chord {
    private List<Note> notes;
    private Note leadingNote;
    private boolean isSingleNote;
    private boolean isMinor;



    public Chord() {
        this.notes = new ArrayList<>();
        this.isSingleNote = false;
    }

    public Chord(String type, String leadingNote, boolean isSingleNote) {
        this.notes = new ArrayList<>();
        this.isSingleNote = isSingleNote;
        
        // Convert leadingNote to an actual Note object (assuming Type and Pitch enums exist)
        this.leadingNote = new Note(Pitch.valueOf(leadingNote), Type.valueOf(type),false);
        
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
//        for (Note note : notes) {
//            note.playNote();
//        }
        if(isSingleNote) {
            for (Note note : notes) {
                note.playNote();
            }
        } else {
            Player player = new Player();
            String musicString = "";
            for(int i = 0; i < notes.size(); i++) {
                musicString = musicString + "V" + i + " I[Guitar] " + notes.get(i).getNoteStringForJFugue() + " "; 
            }
            Pattern finalMusicString = new Pattern(musicString);
            player.play(finalMusicString);
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
        this.leadingNote = new Note(Pitch.valueOf(pitch), Type.valueOf(type), false);
        if (isSingleNote) {
            this.notes.clear();
            this.notes.add(leadingNote);
        } else {
            generateChord();
        }
    }
}
