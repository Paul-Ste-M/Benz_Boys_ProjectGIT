package com.model;

/**
 * Enum representing the supported types of instruments in the application.
 * This is used primarily to label and differentiate {@link Instrument} instances
 * (e.g., to associate a chord library or sound bank with a specific instrument type).
 * Instrument types also serve as identifiers when reading and writing instrument
 * data using {@link DataReader} and {@link DataWriter}.
 */
public enum InstrumentType {

    /**
     * Represents a guitar.
     */
    GUITAR,

    /**
     * Represents a piano.
     */
    PIANO
}
