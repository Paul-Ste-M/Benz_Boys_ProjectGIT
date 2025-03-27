package com.model;

/**
 * Represents the set of musical pitches that a {@link Note} can have.
 * Each pitch is mapped to its corresponding JFugue string for playback using the JFugue music library.
 * This includes all standard chromatic pitches as well as a {@code REST} symbol ("R") to indicate silence.
 */
public enum Pitch {

    /** Natural C pitch. */
    C("C"),

    /** C sharp pitch. */
    C_SHARP("C#"),

    /** Natural D pitch. */
    D("D"),

    /** D sharp pitch. */
    D_SHARP("D#"),

    /** Natural E pitch. */
    E("E"),

    /** Natural F pitch. */
    F("F"),

    /** F sharp pitch. */
    F_SHARP("F#"),

    /** Natural G pitch. */
    G("G"),

    /** G sharp pitch. */
    G_SHARP("G#"),

    /** Natural A pitch. */
    A("A"),

    /** A sharp pitch. */
    A_SHARP("A#"),

    /** Natural B pitch. */
    B("B"),

    /** Represents a rest or pause in the music (no sound). */
    REST("R");

    /**
     * The JFugue-compatible string representation of the pitch.
     * This is used when building music strings for playback.
     */
    public final String JFugueString;

    /**
     * Constructs a Pitch enum with its JFugue string value.
     *
     * @param JFugueString the string used by JFugue to represent this pitch
     */
    private Pitch(String JFugueString) {
        this.JFugueString = JFugueString;
    }
}
