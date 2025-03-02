package com.model;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private List<Chord> chords;
    private int beatCount;

    public Measure() {
        this.chords = new ArrayList<>();
        this.beatCount = 0;
    }

    public boolean checkIfValid() {
        // Placeholder logic: In a real implementation, 
        // you'd check if beatCount is within an acceptable range.
        return beatCount > 0 && beatCount <= 4; 
    }

    public void addChord(String type, String pitch, int position) {
        if (position < 0 || position > chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        Chord newChord = new Chord(type, pitch, false);
        chords.add(position, newChord);
        beatCount++;
    }

    public void addChord(Chord chord, int position) {
        if (position < 0 || position > chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.add(position, chord);
        beatCount++;
    }

    public void addChord(Chord chord) {
        chords.add(chord);
        beatCount++;
    }

    public void removeChord(int position) {
        if (position < 0 || position >= chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.remove(position);
        beatCount--;
    }

    public void changeChord(String type, String pitch, int position) {
        if (position < 0 || position >= chords.size()) {
            System.out.println("Invalid position");
            return;
        }
        chords.get(position).changeNote(type, pitch);
    }

    public void playMeasure() {
        for (Chord chord : chords) {
            chord.playChord();
        }
    }
}
