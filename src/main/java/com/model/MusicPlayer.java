// package com.model;

// import com.calmly.Music;

// /**
//  * A used at the beginning of the project's implementation used to test JFugue.
//  */
// public class MusicPlayer {

//     public void playSong() {
//         try {
//             Music.playNote("E");
//             Music.playNote("D");
//             Music.playNote("C");
//             Music.playNote("D");
//             Music.playNote("E");
//             Music.playNote("E");
//             Music.playNote("E");
//             Thread.sleep(400);

//             Music.playNote("D");
//             Music.playNote("D");
//             Music.playNote("D");
//             Thread.sleep(400);

//             Music.playNote("E");
//             Music.playNote("E");
//             Music.playNote("E");
//             Thread.sleep(400);

//             Music.playNote("E");
//             Music.playNote("D");
//             Music.playNote("C");
//             Music.playNote("D");
//             Music.playNote("E");
//             Music.playNote("E");
//             Music.playNote("E");

//             Music.playNote("C");
//             Music.playNote("D");
//             Music.playNote("D");
//             Music.playNote("E");
//             Music.playNote("D");
//             Music.playNote("C");
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//     }

//     /**
//      * Main method to demonstrate usage of {@code Music.playNote()} with instrument-specific and chord-formatted strings.
//      * <p>
//      * Most playback lines are commented out as examples. To test individual notes or chords,
//      * uncomment the relevant lines.
//      * 
//      * @param args command-line arguments (not used)
//      */
//     public static void main(String[] args) {
//         MusicPlayer player = new MusicPlayer();

//         // player.playSong(); // Uncomment to test the melody in playSong()

//         // Test cases and format examples for Calmly Music:
//         // Music.playNote("I[Guitar] E3");           // Low E string
//         // Music.playNote("I[Guitar] A4");           // Higher pitch A
//         // Music.playNote("I[Guitar] E3maj");        // E major chord
//         // Music.playNote("I[Guitar] G3+B3+D4");     // G major chord (explicit notation)
//         // Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5w"); // G chord with long duration

//         // Example: Play four major chords in sequence
//         Music.playNote("I[Guitar] G4maj C4maj D4maj E4maj");

//         // Example: Play a full G major chord with all notes and long sustain
//         Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5w");
//     }
// }
