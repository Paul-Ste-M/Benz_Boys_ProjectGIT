package com.model;

import java.util.ArrayList;
import java.util.List;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Represents a musical chord, which can be either a single note or a group of notes
 * based on a leading note, a chord type, and whether it is minor or not.
 */
public class Chord {
    private List<Note> notes;
    private Note leadingNote;
    private boolean isSingleNote;
    private boolean isMinor;
    private Type type;

    /**
     * Default constructor. Initializes an empty chord.
     */
    public Chord() {
        this.notes = new ArrayList<>();
        this.isSingleNote = false;
        this.isMinor = false;
    }

    /**
     * Constructs a chord with full parameters including fretboard information.
     *
     * @param type The chord type (e.g., MAJOR, MINOR).
     * @param leadingNote The pitch of the leading note (e.g., C, D, E).
     * @param isSingleNote Whether this is just a single note.
     * @param isMinor Whether the chord is minor.
     * @param octave The octave of the leading note.
     * @param fretNumber The fret number where the note is played.
     * @param tabsLine The tab line index.
     */
    public Chord(String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        this.notes = new ArrayList<>();
        this.isSingleNote = isSingleNote;
        this.isMinor = isMinor;
        this.type = Type.valueOf(type.toUpperCase());
        this.leadingNote = new Note(Pitch.valueOf(leadingNote.toUpperCase()), this.type, octave, fretNumber, tabsLine);

        if (!isSingleNote) {
            generateChord(getInstrument());
        } else {
            notes.add(this.leadingNote);
        }
    }

    /**
     * Constructor used by DataReader when loading chords.
     *
     * @param type The chord type.
     * @param isSingleNote Whether it's a single note.
     * @param isMinor Whether the chord is minor.
     */
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

    /**
     * Generates the chord notes from the leading note and minor flag
     * using a predefined instrument's chord definition.
     *
     * @param instrument The instrument used to generate the chord.
     */
    private void generateChord(Instrument instrument) {
        Chord presetChord = instrument.getChord(leadingNote, isMinor);
        if (presetChord.getLeadingNote().getType().equals(Type.EMPTY))
            return;

        this.notes.clear();
        for (int i = 0; i < presetChord.getNotes().size(); i++) {
            Note newNote = presetChord.getNotes().get(i);
            newNote.setType(type);
            notes.add(newNote);
        }
    }

    /**
     * Returns a default instrument to use for chord generation.
     *
     * @return The default instrument.
     */
    public Instrument getInstrument() {
        InstrumentList instrumentList = InstrumentList.getInstance();
        Instrument instrument = instrumentList.getInstruments().get(0); // placeholder logic
        return instrument;
    }

    /**
     * Adds a note at a specific position in the chord.
     *
     * @param newNote The note to add.
     * @param position The position to insert the note at.
     */
    public void addNote(Note newNote, int position) {
        notes.add(position, newNote);
    }

    /**
     * Adds a note to the end of the chord.
     *
     * @param newNote The note to add.
     */
    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    /**
     * Replaces the current notes with a new list of notes.
     *
     * @param newNotes The new list of notes.
     */
    public void addNotes(ArrayList<Note> newNotes) {
        notes = newNotes;
    }

    /**
     * Generates the JFugue music string for this chord, whether it's a single note or polyphonic.
     *
     * @param musicString A base string to append to.
     * @return The resulting JFugue music string.
     */
    public String playChord(String musicString) {
        if (isSingleNote) {
            for (Note note : notes) {
                musicString = musicString + note.getNoteStringForJFugue() + note.getOctave()
                        + note.getDurationStringForJFugue() + " ";
            }
        } else {
            for (int i = 0; i < notes.size(); i++) {
                if (i == notes.size() - 1)
                    musicString = musicString + notes.get(i).getNoteStringForJFugue() + notes.get(i).getOctave()
                            + notes.get(i).getDurationStringForJFugue() + " ";
                else
                    musicString = musicString + notes.get(i).getNoteStringForJFugue() + notes.get(i).getOctave()
                            + notes.get(i).getDurationStringForJFugue() + "+";
            }
        }
        return musicString;
    }

    /**
     * Converts a chord into a single note by keeping only the leading note.
     */
    public void makeChordIntoNote() {
        if (!notes.isEmpty()) {
            this.leadingNote = notes.get(0);
            this.isSingleNote = true;
            this.notes.clear();
            this.notes.add(leadingNote);
        }
    }

    /**
     * Converts a single note into a full chord by generating additional notes.
     */
    public void makeNoteIntoChord() {
        if (isSingleNote) {
            isSingleNote = false;
            generateChord(getInstrument());
        }
    }

    /**
     * Changes the leading note and updates the chord accordingly.
     *
     * @param type The type of the new chord.
     * @param pitch The pitch of the new leading note.
     */
    public void changeNote(String type, String pitch) {
        this.leadingNote = new Note(Pitch.valueOf(pitch), Type.valueOf(type));
        if (isSingleNote) {
            this.notes.clear();
            this.notes.add(leadingNote);
        } else {
            this.notes.clear();
            this.notes.add(leadingNote);
            generateChord(getInstrument());
        }
    }
}
