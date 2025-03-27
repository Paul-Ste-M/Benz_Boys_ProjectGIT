package com.model;

/**
 * A utility abstract class that holds constant values for JSON data field names
 * and file paths used throughout the application for reading and writing user,
 * song, chord, note, and instrument data.
 * This class is not meant to be instantiated.
 */
public abstract class DataConstants {

    /** Path to the main user data JSON file. */
    protected static final String USER_FILE_NAME = "json/users.json";

    /** Path to the temporary user data JSON file (used during writes). */
    protected static final String USER_TEMP_FILE_NAME = "json/users_temp.json";

    /** JSON field for a user's first name. */
    protected static final String USER_FIRST_NAME = "firstName";

    /** JSON field for a user's last name. */
    protected static final String USER_LAST_NAME = "lastName";

    /** JSON field for a user's username. */
    protected static final String USER_USERNAME = "userName";

    /** JSON field for a user's password. */
    protected static final String USER_PASSWORD = "password";

    /** JSON field for a user's email. */
    protected static final String USER_EMAIL = "email";

    /** JSON field for a user's unique ID. */
    protected static final String USER_ID = "userID";

    /** JSON field for a list of songs created by the user. */
    protected static final String USER_CREATED_SONGS = "createdSongs";

    /** JSON field for checking whether a user is an author. */
    protected static final String USER_IS_AUTUHOR = "isAuthor";

    /** Path to the main song data JSON file. */
    protected static final String SONG_FILE_NAME = "json/songs.json";

    /** Path to the temporary song data JSON file. */
    protected static final String SONG_TEMP_FILE_NAME = "json/songs_temp.json";

    /** JSON field for a song's title. */
    protected static final String SONG_TITLE = "title";

    /** JSON field for the author object of the song. */
    protected static final String SONG_AUTHOR = "author";

    /** JSON field for a song's unique ID. */
    protected static final String SONG_ID = "songID";

    /** JSON field for the author's user ID. */
    protected static final String SONG_AUTHOR_ID = "authorID";

    /** JSON field for the list of genres associated with a song. */
    protected static final String SONG_GENRE = "genre";

    /** JSON field for a list of measures in a song. */
    protected static final String SONG_MEASURES = "measures";

    /** JSON field for the list of chords in a measure. */
    protected static final String MEASURE_CHORDS = "chords";

    /** JSON field for the notes within a chord. */
    protected static final String CHORD_NOTES = "notes";

    /** JSON field for indicating whether a chord is minor. */
    protected static final String CHORD_IS_MINOR = "isMinor";

    /** JSON field for the type of the chord. */
    protected static final String CHORD_TYPE = "type";

    /** JSON field for the type of the note. */
    protected static final String NOTE_TYPE = "type";

    /** JSON field for the pitch of the note. */
    protected static final String NOTE_PITCH = "pitch";

    /** (Deprecated) JSON field indicating if a note is minor. */
    protected static final String NOTE_IS_MINOR = "isMinor"; // to be deleted soon

    /** JSON field for the octave of a note. */
    protected static final String NOTE_OCTAVE = "octave";

    /** JSON field for the fret number of a note (guitar context). */
    protected static final String NOTE_FRET_NUMBER = "fretNumber";

    /** JSON field for the line in tablature where the note appears. */
    protected static final String NOTE_TABS_LINE = "tabsLine";

    /** JSON field for the leading note in a chord. */
    protected static final String CHORD_LEADING_NOTE = "leadingNote";

    /** JSON field indicating whether the chord is a single note. */
    protected static final String CHORD_IS_SINGLE_NOTE = "isSingleNote";

    /** JSON field to indicate whether a song is published. */
    protected static final String SONG_PUBLISHED = "published";

    /** JSON field for a list of comments associated with a song. */
    protected static final String SONG_COMMENTS = "comments";

    /** JSON field for the name of the person who made the comment. */
    protected static final String COMMENT_COMMENTER_NAME = "commenterName";

    /** JSON field for the comment text. */
    protected static final String COMMENT_COMMENT = "commentText";

    /** JSON field for the username of the commenter. */
    protected static final String COMMENT_USERNAME = "username";

    /** Path to the instrument data JSON file. */
    protected static final String INSTRUMENT_FILE_NAME = "json/instruments.json";

    /** Path to the temporary instrument data JSON file. */
    protected static final String INSTRUMENT_TEMP_FILE_NAME = "json/instruments_temp.json";

    /** JSON field for the sound file of an instrument. */
    protected static final String INSTRUMENT_SOUND_FILE = "instrument_sound_file";

    /** JSON field for the name of an instrument. */
    protected static final String INSTRUMENT_INSTRUMENT_NAME = "instrumentName";

    /** JSON field for a list of chords associated with an instrument. */
    protected static final String INSTRUMENT_CHORDS = "chords";
}
