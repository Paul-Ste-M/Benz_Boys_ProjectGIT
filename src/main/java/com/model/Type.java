package com.model;

/**
 * Represents the rhythmic duration of a musical note or chord.
 * Each enum value maps to a shorthand string used by the JFugue music library.
 * @author Benz Boyz
 */
public enum Type {

    /**
     * A whole note duration.
     * JFugue notation: "w"
     */
    WHOLE("w"),

    /**
     * A half note duration.
     * JFugue notation: "h"
     */
    HALF("h"),

    /**
     * A quarter note duration.
     * JFugue notation: "q"
     */
    QUARTER("q"),

    /**
     * An eighth note duration.
     * JFugue notation: "i"
     */
    EIGHTH("i"),

    /**
     * A default or fallback type.
     * May be used to fill gaps or when duration is unspecified.
     * Currently defaults to a quarter note.
     */
    EMPTY("q");

    /**
     * The string representation used by the JFugue library for this note type.
     */
    public final String JFugueString;

    /**
     * Constructs a Type enum value with the corresponding JFugue string.
     *
     * @param JFugueString the shorthand string used by JFugue for this duration
     */
    private Type(String JFugueString) {
        this.JFugueString = JFugueString;
    }
}
