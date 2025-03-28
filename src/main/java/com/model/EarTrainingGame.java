// package com.model;

// import java.util.ArrayList;
// import java.util.List;

// import org.jfugue.pattern.PatternProducer;
// import org.jfugue.player.Player;
// import org.jfugue.theory.ChordProgression;

// public class EarTrainingGame {
//     private Chord currentCorrectChord;
//     private ArrayList<Chord> options;
//     private static final int NUM_ROUNDS = 5;

//     public EarTrainingGame() {
//         int round;


        
//         // Constructor: Initialize any necessary values
//     }

//     public void playChord(String chord) {
//             Player player = new Player();
//             ChordProgression cp = new ChordProgression(chord);
//             PatternProducer[] chords = cp.setKey("C").getChords();
//             player.play(chords[0]);
//             // Logic to play a chord
//     }

//     public void displayOptions(List<String> options) {
//         System.out.println("What chord is this?");
//         for (int i = 0; i < options.size(); i++) {
//             System.out.println((i + 1) + ". " + options.get(i));
//         }
//         // Logic to display possible chord options to the user
//     }

//     public void generateOptions() {
        
//         // Logic to generate possible answer choices
//     }

//     public void chooseNewChord() {
//         // Logic to randomly select a new chord
//     }

//     public void nextRound() {
//         // Logic to move to the next round
//     }

//     public boolean checkAnswer(String userDecision) {
//         // Logic to verify if the user's answer is correct
//         return false; // Placeholder return value
//     }
// }

