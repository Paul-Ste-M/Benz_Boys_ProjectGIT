// package com.model;

// /**
//  * A test class that builds and plays the song "Mary Had a Little Lamb"
//  * using Note, Chord, Measure, and Song classes.
//  * Demonstrates the full process of song creation and playback
//  * Was created as an initial assignment.
//  */
// public class SongTest {

//     /**
//      * The main method executes the test.
//      * It constructs notes, chords, measures, and adds them to a song.
//      * Finally, it plays the song using the playSong() method.
//      *
//      * @param args command-line arguments (unused)
//      */
//     public static void main(String[] args) {

//         /**
//          * Creates an Author object with full name, username, password, and email.
//          * This author will be associated with the song.
//          */
//         Author author = new Author("John", "Doe", "johndoe", "password", "jdoe@gmail.com");

//         /**
//          * Instantiates a new Song object titled "Mary Had a Little Lamb",
//          * and assigns it to the created author.
//          */
//         Song maryHadALittleLamb = new Song("Mary Had a Little Lamb", author);

//         /**
//          * Defines individual musical notes to be reused in chords.
//          * Each note has a pitch and a rhythmic type (quarter note).
//          */
//         Note e = new Note(Pitch.E, Type.QUARTER);
//         Note d = new Note(Pitch.D, Type.QUARTER);
//         Note c = new Note(Pitch.C, Type.QUARTER);
//         Note g = new Note(Pitch.G, Type.QUARTER);

//         /**
//          * Defines a rest note with a quarter duration.
//          * This simulates a pause or silence in the music.
//          */
//         Note restQuarter = new Note(Pitch.REST, Type.QUARTER);

//         /**
//          * Initializes seven Measure objects to represent sections of the song.
//          * Each measure will contain one or more chords.
//          */
//         Measure measure1 = new Measure();
//         Measure measure2 = new Measure();
//         Measure measure3 = new Measure();
//         Measure measure4 = new Measure();
//         Measure measure5 = new Measure();
//         Measure measure6 = new Measure();
//         Measure measure7 = new Measure();

//         /**
//          * Creates and populates Chord objects with the desired notes.
//          * These chords form the melody of the song.
//          */

//         Chord chord1 = new Chord();
//         chord1.addNote(e);
//         chord1.addNote(d);
//         chord1.addNote(c);
//         chord1.addNote(d);

//         Chord chord2 = new Chord();
//         chord2.addNote(e);
//         chord2.addNote(e);
//         chord2.addNote(e);
//         chord2.addNote(restQuarter);

//         Chord chord3 = new Chord();
//         chord3.addNote(d);
//         chord3.addNote(d);
//         chord3.addNote(d);
//         chord3.addNote(restQuarter);

//         Chord chord4 = new Chord();
//         chord4.addNote(e);
//         chord4.addNote(g);
//         chord4.addNote(g);
//         chord4.addNote(restQuarter);

//         Chord chord5 = new Chord();
//         chord5.addNote(e);
//         chord5.addNote(d);
//         chord5.addNote(c);
//         chord5.addNote(d);

//         Chord chord6 = new Chord();
//         chord6.addNote(e);
//         chord6.addNote(e);
//         chord6.addNote(e);
//         chord6.addNote(e);
//         chord6.addNote(restQuarter);

//         Chord chord7 = new Chord();
//         chord7.addNote(d);
//         chord7.addNote(d);
//         chord7.addNote(e);
//         chord7.addNote(d);
//         chord7.addNote(c);
//         chord7.addNote(restQuarter);

//         /**
//          * Adds each Chord to its corresponding Measure.
//          */
//         measure1.addChord(chord1);
//         measure2.addChord(chord2);
//         measure3.addChord(chord3);
//         measure4.addChord(chord4);
//         measure5.addChord(chord5);
//         measure6.addChord(chord6);
//         measure7.addChord(chord7);

//         /**
//          * Appends the completed Measures to the Song object in order.
//          */
//         maryHadALittleLamb.addMeasure(measure1);
//         maryHadALittleLamb.addMeasure(measure2);
//         maryHadALittleLamb.addMeasure(measure3);
//         maryHadALittleLamb.addMeasure(measure4);
//         maryHadALittleLamb.addMeasure(measure5);
//         maryHadALittleLamb.addMeasure(measure6);
//         maryHadALittleLamb.addMeasure(measure7);

//         /**
//          * Assigns a musical genre to the song using its string name.
//          */
//         maryHadALittleLamb.addGenre("KIDS");

//         /**
//          * Outputs a message indicating playback has started.
//          * Then calls playSong() to perform the music using JFugue.
//          */
//         System.out.println("Now playing: Mary Had a Little Lamb 🎵");
//         maryHadALittleLamb.playSong();
//     }
// }
