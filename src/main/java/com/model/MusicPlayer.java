package com.model;

import com.calmly.Music;

/**
 * A basic utility class used to play predefined musical sequences or test instrument-based note playback
 * using the Calmly {@code Music} library.
 * This class can be used as a standalone test or integrated into a broader system for dynamic playback.
 */
public class MusicPlayer {

    /**
     * Plays a hardcoded melody by sequentially calling {@code Music.playNote()} on individual note strings.
     * Includes pauses between sections using {@code Thread.sleep()} to simulate rhythm.
     * This is a sample melody intended for demonstration purposes only.
     * 
     * If an exception occurs during playback or delay, the error is printed to the console.
     */
    public void playSong() {
        try {
            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");
            Thread.sleep(400);

            Music.playNote("D");
            Music.playNote("D");
            Music.playNote("D");
            Thread.sleep(400);

            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");
            Thread.sleep(400);

            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");

            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Main method to demonstrate usage of {@code Music.playNote()} with instrument-specific and chord-formatted strings.
     * <p>
     * Most playback lines are commented out as examples. To test individual notes or chords,
     * uncomment the relevant lines.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();

        // player.playSong(); // Uncomment to test the melody in playSong()

        // Test cases and format examples for Calmly Music:
        // Music.playNote("I[Guitar] E3");           // Low E string
        // Music.playNote("I[Guitar] A4");           // Higher pitch A
        // Music.playNote("I[Guitar] E3maj");        // E major chord
        // Music.playNote("I[Guitar] G3+B3+D4");     // G major chord (explicit notation)
        // Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5w"); // G chord with long duration

        // Example: Play four major chords in sequence
        Music.playNote("I[Guitar] G4maj C4maj D4maj E4maj");

        // Example: Play a full G major chord with all notes and long sustain
        Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5w");
    }
}
