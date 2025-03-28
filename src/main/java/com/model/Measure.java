package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single measure in a song.
 * A measure consists of a list of chords and tracks the total beat count.
 * @author Benz Boyz
 */
public class Measure {

    /** The list of chords in this measure. */
    private List<Chord> chords;

    /** The number of beats in this measure (i.e., number of chords added). */
    private int beatCount;

    /**
     * Constructs an empty measure with no chords and a beat count of zero.
     */
    public Measure() {
        this.chords = new ArrayList<Chord>();
        this.beatCount = 0;
    }

    /**
     * Validates whether the measure has a beat count within the expected range.
     * @return true if beatCount is between 1 and 4 (inclusive), false otherwise
     */
    public boolean checkIfValid() {
        /**
         * Placeholder logic: In a real implementation, you'd check if beatCount 
         * is within an acceptable range based on time signature (default assumed: 4).
         */
        return beatCount > 0 && beatCount <= 4;
    }

    /**
     * Retrieves the number of beats in the measure.
     * @return the total beat count
     */
    public int getBeatCount() {
        return beatCount;
    }

    /**
     * Returns the list of chords contained in this measure.
     * @return a list of Chord objects
     */
    public List<Chord> getChords() {
        return chords;
    }

    /**
     * Adds a new chord to a specific position in the measure using individual note parameters.
     * If the position is invalid, the chord is not added.
     * @param type        the note type (e.g., "QUARTER", "HALF")
     * @param leadingNote the pitch of the leading note (e.g., "C", "D")
     * @param isSingleNote whether the chord is a single note
     * @param isMinor     whether the chord is in a minor key
     * @param octave      the octave of the note (as a string)
     * @param fretNumber  the fret number on the instrument
     * @param tabsLine    the line number used for tab notation
     * @param position    the position at which to insert the chord in the measure
     */
    public void addChord(String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine, int position) {
        if (position < 0 || position > chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        Chord newChord = new Chord(type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
        chords.add(position, newChord);
        beatCount++;
    }

    public void addChord(String type, String leadingNote, boolean isSingleNote, boolean isMinor, String octave, String fretNumber, int tabsLine) {
        Chord newChord = new Chord(type, leadingNote, isSingleNote, isMinor, octave, fretNumber, tabsLine);
        chords.add(newChord);
        beatCount++;
    }

    /**
     * Adds a Chord object to a specific position in the measure.
     * If the position is invalid, the chord is not added.
     * @param chord    the chord object to insert
     * @param position the position in the list of chords to insert at
     */
    public void addChord(Chord chord, int position) {
        if (position < 0 || position > chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.add(position, chord);
        beatCount++;
    }

    /**
     * Adds a chord to the end of the measure.
     * @param chord the Chord object to append
     */
    public void addChord(Chord chord) {
        chords.add(chord);
        beatCount++;
    }

    /**
     * Removes the chord at a specific position in the measure.
     * If the position is invalid, no chord is removed.
     * @param position the index of the chord to remove
     */
    public void removeChord(int position) {
        if (position < 0 || position >= chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.remove(position);
        beatCount--;
    }

    /**
     * Changes the chord at the specified position by updating its type and pitch.
     * If the position is invalid, no changes are made.
     * @param type     the new note type (e.g., "QUARTER")
     * @param pitch    the new pitch (e.g., "C", "D")
     * @param position the index of the chord to change
     */
    public void changeChord(String type, String pitch, int position) {
        if (position < 0 || position >= chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.get(position).changeNote(type, pitch);
    }

    /**
     * Converts all chords in this measure to a JFugue-compatible music string
     * and appends the result to the provided string.
     * @param musicString the current music string being built
     * @return the updated music string including this measure's content
     */
    public String playMeasure(String musicString) {
        for (Chord chord : chords) {
            musicString = chord.playChord(musicString);
        }
        return musicString + "| ";
    }
}
