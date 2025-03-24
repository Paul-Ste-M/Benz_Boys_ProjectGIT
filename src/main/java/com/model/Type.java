package com.model;

public enum Type {
    WHOLE("w"),
    HALF("h"),
    QUARTER("q"),
    EIGHTH("i"),
    EMPTY("q"); //Might not need this last one

    public final String JFugueString;

    private Type(String JFugueString) {
        this.JFugueString = JFugueString;
    }
}
