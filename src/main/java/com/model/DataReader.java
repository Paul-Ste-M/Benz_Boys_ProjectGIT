package com.model;

import java.io.FileReader;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {
    private static final String USERS_FILE_PATH = "json/users.json"; // Users file
    private static final String SONGS_FILE_PATH = "json/songs.json"; // Songs file

    public static void readUsers() {
        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONArray usersArray = (JSONArray) parser.parse(new FileReader(USERS_FILE_PATH));

            // Get the singleton instance of UserList
            UserList userList = UserList.getInstance();

            // Iterate through the JSON array and populate the UserList
            for (Object obj : usersArray) {
                JSONObject userJson = (JSONObject) obj;

                String firstName = (String) userJson.get("firstName");
                String lastName = (String) userJson.get("lastName");
                String userName = (String) userJson.get("userName");
                String password = (String) userJson.get("password");
                String email = (String) userJson.get("email");

                userList.addUser(firstName, lastName, userName, password, email);
            }

            System.out.println("Users loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load users from " + USERS_FILE_PATH);
        }
    }

    public static void readSongs() {
        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONArray songsArray = (JSONArray) parser.parse(new FileReader(SONGS_FILE_PATH));

            // Get the singleton instance of SongLibrary
            SongLibrary songLibrary = SongLibrary.getInstance();

            // Iterate through the JSON array and populate SongLibrary
            for (Object obj : songsArray) {
                JSONObject songJson = (JSONObject) obj;

                String title = (String) songJson.get("title");
                String authorName = (String) songJson.get("author");
                UUID songID = UUID.fromString((String) songJson.get("songID"));
                UUID authorID = UUID.fromString((String) songJson.get("authorID"));
                boolean published = (boolean) songJson.get("published");

                // Create an Author object (assuming you have an Author class)
                Author author = new Author(authorName, "", "", "", "");

                // Create a new song
                Song song = new Song(title, author);
                song.setPublished(published);

                // Parse genres
                JSONArray genresArray = (JSONArray) songJson.get("genre");
                for (Object genre : genresArray) {
                    song.addGenre((String) genre);
                }

                // Parse measures
                JSONArray measuresArray = (JSONArray) songJson.get("measures");
                for (Object measureObj : measuresArray) {
                    JSONObject measureJson = (JSONObject) measureObj;
                    Measure measure = new Measure();

                    JSONArray chordsArray = (JSONArray) measureJson.get("chords");
                    for (Object chordObj : chordsArray) {
                        JSONObject chordJson = (JSONObject) chordObj;
                        String leadingNote = (String) chordJson.get("leadingNote");
                        boolean isSingleNote = (boolean) chordJson.get("isSingleNote");

                        Chord chord = new Chord("QUARTER", leadingNote, isSingleNote);

                        // Parse notes in the chord
                        JSONArray notesArray = (JSONArray) chordJson.get("notes");
                        for (Object noteObj : notesArray) {
                            JSONObject noteJson = (JSONObject) noteObj;
                            String noteType = (String) noteJson.get("type");
                            String pitch = (String) noteJson.get("pitch");
                            boolean isMinor = (boolean) noteJson.get("isMinor");

                            Note note = new Note(Pitch.valueOf(pitch), Type.valueOf(noteType), isMinor);
                            chord.addNote(note);
                        }

                        measure.addChord(chord);
                    }

                    song.addMeasure(measure);
                }

                // Parse comments
                JSONArray commentsArray = (JSONArray) songJson.get("comments");
                for (Object commentObj : commentsArray) {
                    JSONObject commentJson = (JSONObject) commentObj;
                    String commentText = (String) commentJson.get("comment");

                    song.addComment(commentText);
                }

                // Add song to library
                songLibrary.addSong(song);
            }

            System.out.println("Songs loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load songs from " + SONGS_FILE_PATH);
        }
    }

    public static void main(String[] args) {
        // Read both users and songs
        readUsers();
        readSongs();

        // Display all users and songs for verification
        UserList.getInstance().displayUsers();
        SongLibrary.getInstance().displaySongs();
    }
}
