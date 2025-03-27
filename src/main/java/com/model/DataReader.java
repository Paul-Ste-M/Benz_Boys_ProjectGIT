package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Responsible for reading data from JSON files and populating
 * core application data structures including users, songs, and instruments.
 * Uses JSON.simple for parsing and constructing application objects in memory.
 */
public class DataReader extends DataConstants {

    /**
     * Reads all users from the JSON file and populates {@link UserList}.
     * Creates either {@link User} or {@link Author} objects based on the data.
     */
    public static void readUsers() {
        try {
            JSONParser parser = new JSONParser();

            /**
             * Parses the contents of the user JSON file into a JSONArray.
             */
            JSONArray usersArray = (JSONArray) parser.parse(new FileReader(USER_FILE_NAME));

            /**
             * Retrieves the singleton instance of the user list.
             */
            UserList userList = UserList.getInstance();

            /**
             * Iterates through each user object in the array to reconstruct a User or Author.
             */
            for (Object obj : usersArray) {
                JSONObject userJson = (JSONObject) obj;

                String firstName = (String) userJson.get("firstName");
                String lastName = (String) userJson.get("lastName");
                String userName = (String) userJson.get("userName");
                String password = (String) userJson.get("password");
                String email = (String) userJson.get("email");
                String userID = (String) userJson.get("userID");
                boolean isAuthor = (boolean) userJson.get("isAuthor");

                /**
                 * Converts the song ID strings from JSON into UUID objects.
                 */
                ArrayList<UUID> createdSongs = new ArrayList<>();
                JSONArray createdSongsArray = (JSONArray) userJson.get("createdSongs");
                for (Object songID : createdSongsArray) {
                    createdSongs.add(UUID.fromString((String) songID));
                }

                /**
                 * Constructs a User or Author object depending on the isAuthor flag.
                 */
                if (isAuthor) {
                    Author author = new Author(firstName, lastName, userName, password, email, userID, createdSongs, true);
                    userList.addUser(author);
                } else {
                    User user = new User(firstName, lastName, userName, password, email, userID, createdSongs, false);
                    userList.addUser(user);
                }
            }

            System.out.println("Users loaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load users from " + USER_FILE_NAME);
        }
    }

    /**
     * Reads all songs from the JSON file and populates the {@link SongLibrary}.
     * Parses full song structure including metadata, measures, chords, notes, and comments.
     */
    public static void readSongs() {
        try {
            JSONParser parser = new JSONParser();

            /**
             * Parses the songs file into a JSONArray of song objects.
             */
            JSONArray songsArray = (JSONArray) parser.parse(new FileReader(SONG_FILE_NAME));

            /**
             * Retrieves the singleton instance of the song library.
             */
            SongLibrary songLibrary = SongLibrary.getInstance();

            /**
             * Iterates through each song JSON object to reconstruct the song in memory.
             */
            for (Object obj : songsArray) {
                JSONObject songJson = (JSONObject) obj;

                String title = (String) songJson.get("title");
                String authorName = (String) songJson.get("author");
                UUID songID = UUID.fromString((String) songJson.get("songID"));
                UUID authorID = UUID.fromString((String) songJson.get("authorID"));
                boolean published = (boolean) songJson.get("published");

                /**
                 * Retrieves the User object for the song's author.
                 */
                User author = UserList.getInstance().getUser(authorID);

                /**
                 * Constructs the Song object using parsed attributes.
                 */
                Song song = new Song(title, authorName, author.getUsername(), songID, authorID, published);

                /**
                 * Parses and adds each genre to the song.
                 */
                JSONArray genresArray = (JSONArray) songJson.get("genre");
                for (Object genre : genresArray) {
                    song.addGenre((String) genre);
                }

                /**
                 * Parses each measure in the song and its chords and notes.
                 */
                JSONArray measuresArray = (JSONArray) songJson.get("measures");
                for (Object measureObj : measuresArray) {
                    JSONObject measureJson = (JSONObject) measureObj;
                    Measure measure = new Measure();

                    JSONArray chordsArray = (JSONArray) measureJson.get("chords");
                    for (Object chordObj : chordsArray) {
                        JSONObject chordJson = (JSONObject) chordObj;

                        boolean isSingleNote = (boolean) chordJson.get("isSingleNote");
                        boolean isMinor = (boolean) chordJson.get("isMinor");
                        String type = (String) chordJson.get("type");

                        Chord chord = new Chord(type, isSingleNote, isMinor);

                        JSONArray notesArray = (JSONArray) chordJson.get("notes");

                        /**
                         * Creates the leading note from the first note in the array.
                         */
                        JSONObject leadingNoteJson = (JSONObject) notesArray.get(0);
                        Note leadingNote = new Note(
                                Pitch.valueOf((String) leadingNoteJson.get("pitch")),
                                Type.valueOf((String) leadingNoteJson.get("type")),
                                (String) leadingNoteJson.get("octave"),
                                (String) leadingNoteJson.get("fretNumber"),
                                ((Long) leadingNoteJson.get("tabsLine")).intValue()
                        );
                        chord.setLeadingNote(leadingNote);

                        /**
                         * Parses and adds each note to the chord.
                         */
                        for (Object noteObj : notesArray) {
                            JSONObject noteJson = (JSONObject) noteObj;
                            Note note = new Note(
                                    Pitch.valueOf((String) noteJson.get("pitch")),
                                    Type.valueOf((String) noteJson.get("type")),
                                    (String) noteJson.get("octave"),
                                    (String) noteJson.get("fretNumber"),
                                    ((Long) noteJson.get("tabsLine")).intValue()
                            );
                            chord.addNote(note);
                        }

                        measure.addChord(chord);
                    }

                    song.addMeasure(measure);
                }

                /**
                 * Parses all comments and attaches them to the song.
                 */
                JSONArray commentsArray = (JSONArray) songJson.get("comments");
                for (Object commentObj : commentsArray) {
                    JSONObject commentJson = (JSONObject) commentObj;

                    String commenterName = (String) commentJson.get("commenterName");
                    String commentText = (String) commentJson.get("commentText");
                    String username = (String) commentJson.get("username");

                    Comment comment = new Comment(commenterName, commentText, username);
                    song.addComment(comment);
                }

                songLibrary.addSong(song);
            }

            System.out.println("Songs loaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load songs from " + SONG_FILE_NAME);
        }
    }

    /**
     * Reads instruments and their chord definitions from the JSON file
     * and adds them to the {@link InstrumentList}.
     */
    public static void readInstruments() {
        try {
            JSONParser parser = new JSONParser();

            /**
             * Parses instrument data into a JSONArray of instrument definitions.
             */
            JSONArray instrumentsArray = (JSONArray) parser.parse(new FileReader(INSTRUMENT_FILE_NAME));

            /**
             * Retrieves the singleton instance of the instrument list.
             */
            InstrumentList instrumentList = InstrumentList.getInstance();

            /**
             * Iterates through each instrument and reconstructs its chord list.
             */
            for (Object obj : instrumentsArray) {
                JSONObject instrumentJson = (JSONObject) obj;

                String instrumentName = (String) instrumentJson.get("instrumentName");

                ArrayList<Chord> chords = new ArrayList<>();
                Instrument instrument = new Instrument(InstrumentType.valueOf(instrumentName), chords);

                JSONArray chordsArray = (JSONArray) instrumentJson.get("chords");
                for (Object chordObj : chordsArray) {
                    JSONObject chordJson = (JSONObject) chordObj;

                    boolean isSingleNote = (boolean) chordJson.get("isSingleNote");
                    boolean isMinor = (boolean) chordJson.get("isMinor");
                    String type = (String) chordJson.get("type");

                    Chord chord = new Chord(type, isSingleNote, isMinor);

                    JSONArray notesArray = (JSONArray) chordJson.get("notes");

                    /**
                     * Sets the leading note from the first note object.
                     */
                    JSONObject leadingNoteJson = (JSONObject) notesArray.get(0);
                    Note leadingNote = new Note(
                            Pitch.valueOf((String) leadingNoteJson.get("pitch")),
                            Type.valueOf((String) leadingNoteJson.get("type")),
                            (String) leadingNoteJson.get("octave"),
                            (String) leadingNoteJson.get("fretNumber"),
                            ((Long) leadingNoteJson.get("tabsLine")).intValue()
                    );
                    chord.setLeadingNote(leadingNote);

                    for (Object noteObj : notesArray) {
                        JSONObject noteJson = (JSONObject) noteObj;
                        Note note = new Note(
                                Pitch.valueOf((String) noteJson.get("pitch")),
                                Type.valueOf((String) noteJson.get("type")),
                                (String) noteJson.get("octave"),
                                (String) noteJson.get("fretNumber"),
                                ((Long) noteJson.get("tabsLine")).intValue()
                        );
                        chord.addNote(note);
                    }

                    chords.add(chord);
                }

                instrumentList.addInstrument(instrument);
            }

            System.out.println("Instruments loaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load instruments from " + INSTRUMENT_FILE_NAME);
        }
    }

    /**
     * Entry point for debugging. Loads users and songs,
     * then prints them to the console.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        readUsers();
        readSongs();

        UserList.getInstance().displayUsers();
        SongLibrary.getInstance().displaySongs();
    }
}
