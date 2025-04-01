package com.model;

import java.util.ArrayList;

/**
 * An instrument that contains all the chord formations of the instrument.
 * @author Benz Boyz
 */
public class Instrument {

    // The type of instrument (e.g., GUITAR, UKULELE) used to identify presets
    private InstrumentType instrumentName;

    // A list of chord presets associated with this instrument
    private ArrayList<Chord> chords;

    /**
     * Constructs an Instrument with a given name and list of chord presets.
     * @param instrumentName the name/type of the instrument
     * @param chords the list of preset chords available on this instrument
     */
    public Instrument(InstrumentType instrumentName, ArrayList<Chord> chords) {
        this.instrumentName = instrumentName;
        this.chords = chords;
    }

    /**
     * Retrieves a chord from the instrument’s preset chord list based on a given leading note and minor flag.
     * Used when converting a single note into a full chord
     * @param leadingNote the root/starting note of the desired chord
     * @param isMinor whether the chord should be minor
     * @return the matching chord if found; otherwise, an "empty" placeholder chord
     */
    public Chord getChord(Note leadingNote, boolean isMinor) {
        for (Chord presetChord : chords) {
            // Check if the preset chord matches the note and minor status
            if (presetChord.getLeadingNote().getNoteStringForJFugue().equals(leadingNote.getNoteStringForJFugue())
                    && presetChord.isMinor() == isMinor) {
                return presetChord;
            }
        }

        // Return a placeholder chord if no match is found
        System.out.println("Chord not found");
        return new Chord("EMPTY", true, isMinor);
    }

    /**
     * Returns the instrument’s name/type.
     * @return the {@link InstrumentType} of the instrument
     */
    public InstrumentType getInstrumentName() {
        return instrumentName;
    }

    /**
     * Returns the list of chord presets associated with this instrument.
     * @return a list of {@link Chord} objects
     */
    public ArrayList<Chord> getChords() {
        return chords;
    }
}
