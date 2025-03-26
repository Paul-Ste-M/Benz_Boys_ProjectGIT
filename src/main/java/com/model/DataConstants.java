package com.model;

public abstract class DataConstants {
    protected static final String USER_FILE_NAME = "json/users.json";
    protected static final String USER_TEMP_FILE_NAME = "json/users_temp.json";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_USERNAME = "userName";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_ID = "userID";
    protected static final String USER_CREATED_SONGS = "createdSongs";
    protected static final String USER_IS_AUTUHOR = "isAuthor";
    protected static final String SONG_FILE_NAME = "json/songs.json";
    protected static final String SONG_TEMP_FILE_NAME = "json/songs_temp.json";
    protected static final String SONG_TITLE = "title";
    protected static final String SONG_AUTHOR = "author";
    protected static final String SONG_ID = "songID";
    protected static final String SONG_AUTHOR_ID = "authorID";
    protected static final String SONG_GENRE = "genre";
    protected static final String SONG_MEASURES = "measures";
    protected static final String MEASURE_CHORDS = "chords";
   // protected static final String MEASURE_BEAT_COUNT = "beatCount"; I don't think we need to store this. It'll recalculate when read from json.
    protected static final String CHORD_NOTES = "notes";
    protected static final String CHORD_IS_MINOR = "isMinor";
    protected static final String CHORD_TYPE = "type";
    protected static final String NOTE_TYPE = "type";
    protected static final String NOTE_PITCH = "pitch";
    protected static final String NOTE_IS_MINOR = "isMinor"; // to be deleted soon
    protected static final String NOTE_OCTAVE = "octave";
    protected static final String NOTE_FRET_NUMBER = "fretNumber";
    protected static final String NOTE_TABS_LINE = "tabsLine";
    protected static final String CHORD_LEADING_NOTE = "leadingNote";
    protected static final String CHORD_IS_SINGLE_NOTE = "isSingleNote";
    protected static final String SONG_PUBLISHED = "published";
    protected static final String SONG_COMMENTS = "comments";
    protected static final String COMMENT_COMMENTER_NAME = "commenterName";
    protected static final String COMMENT_COMMENT = "commentText";
    protected static final String COMMENT_USERNAME = "username";
    protected static final String INSTRUMENT_FILE_NAME = "json/instruments.json";
    protected static final String INSTRUMENT_TEMP_FILE_NAME = "json/instruments_temp.json";
    protected static final String INSTRUMENT_SOUND_FILE = "instrument_sound_file";
    protected static final String INSTRUMENT_INSTRUMENT_NAME = "instrumentName";
    protected static final String INSTRUMENT_CHORDS = "chords";
}
