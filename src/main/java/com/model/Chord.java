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
    private Type type;



    public Chord() {
        this.notes = new ArrayList<>();
        this.isSingleNote = false;
        this.isMinor = false;

    }

    public Chord(String type, String leadingNote, boolean isSingleNote, boolean isMinor) {
        this.notes = new ArrayList<>();
        this.isSingleNote = isSingleNote;
        this.isMinor = isMinor;
        this.type = Type.valueOf(type.toUpperCase());

        // Convert leadingNote to an actual Note object (assuming Type and Pitch enums exist)
        //this.leadingNote = new Note(Pitch.valueOf(leadingNote.toUpperCase()), Type.valueOf(type.toUpperCase()),false);
        this.leadingNote = new Note(Pitch.valueOf(leadingNote.toUpperCase()), this.type);
        
        if (!isSingleNote) {
            // Generate a chord based on the leading note
            generateChord();
        } else {
            notes.add(this.leadingNote);
        }
    }

    // Constructor used by DataReader
    public Chord(String type, boolean isSingleNote, boolean isMinor) {
        this.notes = new ArrayList<Note>();
        this.type = Type.valueOf(type.toUpperCase());
        this.isSingleNote = isSingleNote;
        this.isMinor = isMinor;
    }

    public void setLeadingNote(Note leadingNote) {
        this.leadingNote = leadingNote;
    }

    public Note getLeadingNote() {
        return leadingNote;
    }

    public boolean isSingleNote() {
        return isSingleNote;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public boolean isMinor() {
        return isMinor;
    }

    public Type getType() {
        return type;
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
            // instead of playing one note at a time, gather the notes and play everything
            // add if statement for if chord is minor then add minor to note
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
        this.leadingNote = new Note(Pitch.valueOf(pitch), Type.valueOf(type));
        if (isSingleNote) {
            this.notes.clear();
            this.notes.add(leadingNote);
        } else {
            generateChord();
        }
    }
}