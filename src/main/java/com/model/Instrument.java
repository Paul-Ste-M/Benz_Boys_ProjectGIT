package com.model;

import java.util.ArrayList;

public class Instrument {
    //private static final String INSTTUMENT_SOUND_FILE = "";
    private InstrumentType instrumentName;
    private ArrayList<Chord> chords;

    public Instrument(InstrumentType instrumentName, ArrayList<Chord> chords) {
        this.instrumentName = instrumentName;
        this.chords = chords;
    }

    public Chord getChord(Note leadingNote, boolean isMinor) {
        for(Chord presetChord : chords) {
            if(presetChord.getLeadingNote().getNoteStringForJFugue().equals(leadingNote.getNoteStringForJFugue())
               && presetChord.isMinor() == isMinor) {
                return presetChord;
               }
        }
        // If chord is not found, just return an "empty" chord so that logic will be applied elsewhere
        System.out.println("Chord not found");
        Chord chord = new Chord("EMPTY", true, isMinor);
        return chord;
    }

    public InstrumentType getInstrumentName() {
        return instrumentName;
    }

    public ArrayList<Chord> getChords() {
        return chords;
    }

    public void playSound(String pitch){
    }

    public void loadinSounds(){
        
    }
}
