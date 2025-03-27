package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Handles writing Users, Songs, and Instruments to JSON files.
 * Converts Java objects into JSON format and saves them to disk.
 */
public class DataWriter extends DataConstants {

    /**
     * Saves a list of users to the temporary users JSON file.
     * Each user is converted to a JSON object using getUserJSON and
     * added to a JSON array, which is then written to disk.
     *
     * @param users the list of users to save
     * @return true if the file is successfully written, false otherwise
     */
    public static boolean saveUsers(ArrayList<User> users) {
        JSONArray jsonUsers = new JSONArray();

        /**
         * Convert each user object to a JSON representation.
         */
        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserJSON(users.get(i)));
        }

        /**
         * Write the JSON array to the designated file.
         */
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Converts a User object to its JSON representation, including their
     * personal information and the list of created song IDs.
     *
     * @param user the user to convert
     * @return a JSONObject representing the user
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_IS_AUTUHOR, user.isAuthor());

        /**
         * Convert all createdSongs UUIDs to a JSON array of strings.
         */
        JSONArray createdSongsJSON = new JSONArray();
        ArrayList<UUID> createdSongs = user.getCreatedSongs();
        for (UUID createdsong : createdSongs) {
            createdSongsJSON.add(createdsong.toString());
        }

        userDetails.put(USER_CREATED_SONGS, createdSongsJSON);

        return userDetails;
    }

    /**
     * Saves a list of songs to the temporary songs JSON file.
     * Converts each Song into a JSON object using getSongJSON and
     * stores them in a JSON array for file output.
     *
     * @param songs the list of songs to save
     * @return true if the file is written successfully, false otherwise
     */
    public static boolean saveSongs(ArrayList<Song> songs) {
        JSONArray jsonSongs = new JSONArray();

        /**
         * Convert each song to a JSON object and add to array.
         */
        for (int i = 0; i < songs.size(); i++) {
            jsonSongs.add(getSongJSON(songs.get(i)));
        }

        /**
         * Write the JSON array to file.
         */
        try (FileWriter file = new FileWriter(SONG_FILE_NAME)) {
            file.write(jsonSongs.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Converts a Song object to a JSONObject.
     * Includes metadata (title, author info), genres, measures (with chords and notes),
     * published status, and comments.
     *
     * @param song the song to convert
     * @return a JSONObject representing the song
     */
    public static JSONObject getSongJSON(Song song) {
        JSONObject songDetails = new JSONObject();

        songDetails.put(SONG_TITLE, song.getTitle());
        songDetails.put(SONG_AUTHOR, song.getAuthor());
        songDetails.put(SONG_ID, song.getSongID().toString());
        songDetails.put(SONG_AUTHOR_ID, song.getAuthorID().toString());

        /**
         * Add genres.
         */
        JSONArray genreListJSON = new JSONArray();
        ArrayList<Genre> genreList = song.getGenres();
        for (Genre genre : genreList) {
            genreListJSON.add(genre.toString());
        }
        songDetails.put(SONG_GENRE, genreListJSON);

        /**
         * Add measures.
         */
        JSONArray measuresJSON = new JSONArray();
        ArrayList<Measure> measures = song.getMeasures();
        for (Measure measure : measures) {
            JSONObject measureDetails = new JSONObject();

            /**
             * Add chords to measure.
             */
            JSONArray chordsJSON = new JSONArray();
            List<Chord> chords = measure.getChords();
            for (Chord chord : chords) {
                JSONObject chordDetails = new JSONObject();

                chordDetails.put(CHORD_LEADING_NOTE, chord.getLeadingNote().getNoteStringForJFugue());
                chordDetails.put(CHORD_IS_SINGLE_NOTE, chord.isSingleNote());
                chordDetails.put(CHORD_IS_MINOR, chord.isMinor());
                chordDetails.put(CHORD_TYPE, chord.getType().toString());

                /**
                 * Add notes to chord.
                 */
                JSONArray notesJSON = new JSONArray();
                List<Note> notes = chord.getNotes();
                for (Note note : notes) {
                    JSONObject noteDetails = new JSONObject();
                    noteDetails.put(NOTE_TYPE, note.getType().toString());
                    noteDetails.put(NOTE_PITCH, note.getPitch().toString());
                    noteDetails.put(NOTE_OCTAVE, note.getOctave());
                    noteDetails.put(NOTE_FRET_NUMBER, note.getFretNumber());
                    noteDetails.put(NOTE_TABS_LINE, note.getTabsLine());
                    notesJSON.add(noteDetails);
                }

                chordDetails.put(CHORD_NOTES, notesJSON);
                chordsJSON.add(chordDetails);
            }

            measureDetails.put(MEASURE_CHORDS, chordsJSON);
            measuresJSON.add(measureDetails);
        }

        songDetails.put(SONG_MEASURES, measuresJSON);

        /**
         * Add published status.
         */
        songDetails.put(SONG_PUBLISHED, song.isPublished());

        /**
         * Add comments.
         */
        JSONArray commentsJSON = new JSONArray();
        ArrayList<Comment> comments = song.getComments();
        for (Comment comment : comments) {
            JSONObject commentDetails = new JSONObject();
            commentDetails.put(COMMENT_COMMENTER_NAME, comment.getCommenterName());
            commentDetails.put(COMMENT_COMMENT, comment.getCommentText());
            commentDetails.put(COMMENT_USERNAME, comment.getUsername());
            commentsJSON.add(commentDetails);
        }
        songDetails.put(SONG_COMMENTS, commentsJSON);

        return songDetails;
    }

    /**
     * Saves a list of instruments to the instruments JSON file.
     * Each Instrument is serialized to JSON using getInstrumentJSON().
     *
     * @param instruments the list of instruments to save
     * @return true if writing succeeds, false otherwise
     */
    public static boolean saveInstruments(ArrayList<Instrument> instruments) {
        JSONArray jsonInstruments = new JSONArray();

        /**
         * Convert each instrument to JSON and add to array.
         */
        for (int i = 0; i < instruments.size(); i++) {
            jsonInstruments.add(getInstrumentJSON(instruments.get(i)));
        }

        /**
         * Write the array to the file.
         */
        try (FileWriter file = new FileWriter(INSTRUMENT_FILE_NAME)) {
            file.write(jsonInstruments.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Converts an Instrument object to a JSONObject.
     * Includes instrument name, chords, and all notes in each chord.
     *
     * @param instrument the instrument to convert
     * @return a JSONObject representing the instrument
     */
    public static JSONObject getInstrumentJSON(Instrument instrument) {
        JSONObject instrumentDetails = new JSONObject();
        instrumentDetails.put(INSTRUMENT_INSTRUMENT_NAME, instrument.getInstrumentName().toString());

        JSONArray chordsJSON = new JSONArray();
        List<Chord> chords = instrument.getChords();

        for (Chord chord : chords) {
            JSONObject chordDetails = new JSONObject();

            chordDetails.put(CHORD_LEADING_NOTE, chord.getLeadingNote().getNoteStringForJFugue());
            chordDetails.put(CHORD_IS_SINGLE_NOTE, chord.isSingleNote());
            chordDetails.put(CHORD_IS_MINOR, chord.isMinor());
            chordDetails.put(CHORD_TYPE, chord.getType().toString());

            JSONArray notesJSON = new JSONArray();
            List<Note> notes = chord.getNotes();
            for (Note note : notes) {
                JSONObject noteDetails = new JSONObject();
                noteDetails.put(NOTE_TYPE, note.getType().toString());
                noteDetails.put(NOTE_PITCH, note.getPitch().toString());
                noteDetails.put(NOTE_OCTAVE, note.getOctave());
                noteDetails.put(NOTE_FRET_NUMBER, note.getFretNumber());
                noteDetails.put(NOTE_TABS_LINE, note.getTabsLine());
                notesJSON.add(noteDetails);
            }

            chordDetails.put(CHORD_NOTES, notesJSON);
            chordsJSON.add(chordDetails);
        }

        instrumentDetails.put(INSTRUMENT_CHORDS, chordsJSON);
        return instrumentDetails;
    }

    /**
     * A test driver for the DataWriter class.
     * Loads users, songs, and instruments from disk, plays a sample song,
     * prints its tablature to a file, and saves everything back.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        /**
         * Load data from disk.
         */
        DataReader.readUsers();
        DataReader.readSongs();
        DataReader.readInstruments();

        /**
         * Retrieve data instances.
         */
        UserList userList = UserList.getInstance();
        SongLibrary songLibrary = SongLibrary.getInstance();
        InstrumentList instrumentList = InstrumentList.getInstance();

        /**
         * Play and print a test song.
         */
        songLibrary.searchByTitle("Test Song").get(0).playSong();
        songLibrary.searchByTitle("Test Song").get(0).printTabsToTextFile();

        /**
         * Save updated data back to JSON.
         */
        DataWriter.saveUsers(userList.getUsers());
        DataWriter.saveSongs(songLibrary.getSongs());
        DataWriter.saveInstruments(instrumentList.getInstruments());
    }
}
