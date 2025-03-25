package com.model;

public enum Pitch {
    C("C"),
    C_SHARP("C#"),
    D("D"),
    D_SHARP("D#"),
    E("E"),
    F("F"),
    F_SHARP("F#"),
    G("G"),
    G_SHARP("G#"),
    A("A"),
    A_SHARP("A#"),
    B("B"),
    REST("R");

    public final String JFugueString;

    private Pitch(String JFugueString) {
        this.JFugueString = JFugueString;
    }
}
