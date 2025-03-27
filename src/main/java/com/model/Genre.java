package com.model;

/**
 * Represents the different musical genres that a song can belong to.
 * <p>
 * This enum is used in the {@link Song} class to categorize and tag songs
 * with one or more genres. It helps with searching, filtering, and displaying
 * songs by musical style.
 * <p>
 * These values are also serialized into and deserialized from JSON when reading
 * and writing songs using {@link DataReader} and {@link DataWriter}.
 */
public enum Genre {

    /**
     * Represents Soul music.
     */
    SOUL,

    /**
     * Represents Jazz music.
     */
    JAZZ,

    /**
     * Represents Rock music.
     */
    ROCK,

    /**
     * Represents Pop music.
     */
    POP,

    /**
     * Represents music made for children.
     */
    KIDS
}
