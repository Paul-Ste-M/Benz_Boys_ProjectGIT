package com.model;

public class SongTest {
    public static void main(String[] args) {
        // Create an author
        Author author = new Author("John", "Doe", "johndoe", "password", "jdoe@gmail.com");

        // Create a song
        Song maryHadALittleLamb = new Song("Mary Had a Little Lamb", author);

        // Define the melody using notes
        Note e = new Note(Pitch.E, Type.QUARTER,false);
        Note d = new Note(Pitch.D, Type.QUARTER,false);
        Note c = new Note(Pitch.C, Type.QUARTER,false);
        Note g = new Note(Pitch.G, Type.QUARTER,false);
        Note restQuarter = new Note(Type.QUARTER); // Rest for a quarter note duration


        // Create measures
        Measure measure1 = new Measure();
        Measure measure2 = new Measure();
        Measure measure3 = new Measure();
        Measure measure4 = new Measure();
        Measure measure5 = new Measure();
        Measure measure6 = new Measure();
        Measure measure7 = new Measure();

        // Create chords for melody (Mary Had a Little Lamb)
        Chord chord1 = new Chord();  // E D C D
        chord1.addNote(e);
        chord1.addNote(d);
        chord1.addNote(c);
        chord1.addNote(d);
        
        Chord chord2 = new Chord();  // E E E
        chord2.addNote(e);
        chord2.addNote(e);
        chord2.addNote(e);
        chord2.addNote(restQuarter);

        

        Chord chord3 = new Chord();  // D D D
        chord3.addNote(d);
        chord3.addNote(d);
        chord3.addNote(d);
        chord3.addNote(restQuarter);


        Chord chord4 = new Chord();  // E G G
        chord4.addNote(e);
        chord4.addNote(g);
        chord4.addNote(g);
        chord4.addNote(restQuarter);

        Chord chord5 = new Chord();  // E D C D
        chord5.addNote(e);
        chord5.addNote(d);
        chord5.addNote(c);
        chord5.addNote(d);

        Chord chord6 = new Chord();  // E E E E
        chord6.addNote(e);
        chord6.addNote(e);
        chord6.addNote(e);
        chord6.addNote(e);
        chord6.addNote(restQuarter);

        Chord chord7 = new Chord();  // D D E D C
        chord7.addNote(d);
        chord7.addNote(d);
        chord7.addNote(e);
        chord7.addNote(d);
        chord7.addNote(c);
        chord7.addNote(restQuarter);

        // Add chords to measures
        measure1.addChord(chord1);
        measure2.addChord(chord2);
        measure3.addChord(chord3);
        measure4.addChord(chord4);
        measure5.addChord(chord5);
        measure6.addChord(chord6);
        measure7.addChord(chord7);

        // Add measures to the song
        maryHadALittleLamb.addMeasure(measure1);
        maryHadALittleLamb.addMeasure(measure2);
        maryHadALittleLamb.addMeasure(measure3);
        maryHadALittleLamb.addMeasure(measure4);
        maryHadALittleLamb.addMeasure(measure5);
        maryHadALittleLamb.addMeasure(measure6);
        maryHadALittleLamb.addMeasure(measure7);

        // Add genre
        maryHadALittleLamb.addGenre("KIDS");

        // Add a comment
        maryHadALittleLamb.addComment("A childhood classic!");

        // Play the song
        System.out.println("Now playing: Mary Had a Little Lamb 🎵");
        maryHadALittleLamb.playSong();

        // Display comments
        System.out.println("\nComments:");
        for (Comment h : maryHadALittleLamb.getComments()) {
            System.out.println("- " + h.getComment());
        }
    }
}
