package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Represents a musical note, including its pitch, duration (type), octave, and tablature information.
 * This class also handles both normal notes and rest (pause) notes. Notes can be played using the JFugue library.
 */
public class Note {
    private Pitch pitch;
    private Type type;
    private boolean isRest;
    private String octave;
    private String fretNumber;
    private int tabsLine;

    /**
     * Constructs a note with the given pitch and type.
     * Automatically marks the note as a rest if its pitch maps to "R" in JFugue.
     *
     * @param pitch the musical pitch (C, D, E, etc. or R for rest)
     * @param type  the duration/type of the note (e.g. QUARTER, WHOLE)
     */
    public Note(Pitch pitch, Type type) {
        this.pitch = pitch;
        this.type = type;
        this.isRest = getNoteStringForJFugue().equals("R");
    }

    /**
     * Constructs a note with full data, typically used when loading from JSON.
     * Includes fret and tablature position in addition to pitch/type.
     *
     * @param pitch       the pitch of the note
     * @param type        the note duration/type
     * @param octave      the octave string (e.g., "3", "4")
     * @param fretNumber  string indicating the fret number
     * @param tabsLine    line number on the tablature
     */
    public Note(Pitch pitch, Type type, String octave, String fretNumber, int tabsLine) {
        this.pitch = pitch;
        this.type = type;
        this.isRest = getNoteStringForJFugue().equals("R");
        this.octave = octave;
        this.fretNumber = fretNumber;
        this.tabsLine = tabsLine;
    }

    /**
     * Returns whether this note is a rest (pause) rather than a sound.
     *
     * @return true if this is a rest, false otherwise
     */
    public boolean isRest() {
        return isRest;
    }

    /**
     * Plays this note using the JFugue library.
     * If it is a rest, the method waits for the appropriate duration using {@code Thread.sleep()}.
     * Otherwise, it plays the note on the "Guitar" instrument.
     */
    public void playNote() {
        Player player = new Player();

        if (isRest) {
            System.out.println("Rest for " + type + " duration.");
            try {
                Thread.sleep(getDuration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Playing guitar note: " + pitch + " " + type);
            String noteString = getNoteStringForJFugue();
            Pattern pattern = new Pattern("I[Guitar] " + noteString);
            player.play(pattern);
        }
    }

    /**
     * Returns the playback duration in milliseconds for this note,
     * based on its type.
     *
     * @return the duration in milliseconds
     */
    private long getDuration() {
        switch (type) {
            case WHOLE:
                return 1600;
            case HALF:
                return 800;
            case QUARTER:
                return 400;
            case EIGHTH:
                return 200;
            default:
                return 400;
        }
    }

    /**
     * Returns the JFugue-compatible duration string for this note.
     *
     * @return a string like "w", "h", "q", "i" based on the note type
     */
    public String getDurationStringForJFugue() {
        return type.JFugueString;
    }

    /**
     * Returns the type (duration) of the note.
     *
     * @return the note's type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the pitch of the note.
     *
     * @return the note's pitch
     */
    public Pitch getPitch() {
        return pitch;
    }

    /**
     * Returns the string representation of the octave (used in tabs).
     *
     * @return the octave string
     */
    public String getOctave() {
        return octave;
    }

    /**
     * Returns the fret number this note is associated with.
     *
     * @return fret number as a string
     */
    public String getFretNumber() {
        return fretNumber;
    }

    /**
     * Returns the tablature line number (e.g., string on guitar).
     *
     * @return the tablature line index
     */
    public int getTabsLine() {
        return tabsLine;
    }

    /**
     * Sets the type of this note. Ignores null input.
     *
     * @param type the new note type
     */
    public void setType(Type type) {
        if (type != null)
            this.type = type;
    }

    /**
     * Converts the pitch of this note to a JFugue-compatible note string.
     * If the pitch is "R" (rest), returns "R".
     *
     * @return the note string for JFugue playback (e.g., "C", "G#", "R")
     */
    public String getNoteStringForJFugue() {
        return pitch.JFugueString;
    }

    /**
     * Returns a string representation of this note.
     * For rest notes, shows "Rest(type)", otherwise shows "Note(pitch, type)".
     *
     * @return formatted string representation of the note
     */
    @Override
    public String toString() {
        if (isRest) {
            return "Rest(" + type + ")";
        }
        return "Note(" + pitch + ", " + type + ")";
    }
}

