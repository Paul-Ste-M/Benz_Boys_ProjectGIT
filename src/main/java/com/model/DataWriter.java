package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static boolean saveUsers(ArrayList<User> users) {
        // Might not even need these lines since we're passing a list in?
        // UserList userList = UserList.getInstance();
        // ArrayList<User> users = userList.getUsers();

        //ArrayList<User> users = new ArrayList<User>();

        JSONArray jsonUsers = new JSONArray();

        // Create and add all users as JSON objects
        for(int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserJSON(users.get(i)));
        }

        // Write the JSON file
        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;            
        }

        return true;
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_IS_AUTUHOR, user.isAuthor());
        
        // Storing the UUIDs of createdSongs
        JSONArray createdSongsJSON = new JSONArray();
        ArrayList<UUID> createdSongs = user.getCreatedSongs();
        for (UUID createdsong : createdSongs) {
            createdSongsJSON.add(createdsong.toString());
        }
        userDetails.put(USER_CREATED_SONGS, createdSongsJSON);

        return userDetails;
    }

    public static boolean saveSongs(ArrayList<Song> songs) {
        
        JSONArray jsonSongs = new JSONArray();

        // Create and add all songs as JSON objects
        for(int i = 0; i < songs.size(); i++) {
            jsonSongs.add(getSongJSON(songs.get(i)));
        }

        // Write the JSON file
        try (FileWriter file = new FileWriter(SONG_TEMP_FILE_NAME)) {
            file.write(jsonSongs.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static JSONObject getSongJSON(Song song) {
        JSONObject songDetails = new JSONObject();
        songDetails.put(SONG_TITLE, song.getTitle());
        songDetails.put(SONG_AUTHOR, song.getAuthor());
        songDetails.put(SONG_ID, song.getSongID().toString());
        songDetails.put(SONG_AUTHOR_ID, song.getAuthorID().toString());

        // Storing the genres of the song
        JSONArray genreListJSON = new JSONArray();
        ArrayList<Genre> genreList = song.getGenres();
        for(Genre genre : genreList) {
            genreListJSON.add(genre.toString());
        }
        songDetails.put(SONG_GENRE, genreListJSON);

        // Storing the measures of the song
        JSONArray measuresJSON = new JSONArray();
        ArrayList<Measure> measures = song.getMeasures();
        for(Measure measure : measures) {
            JSONObject measureDetails = new JSONObject();
            //measureDetails.put(MEASURE_BEAT_COUNT, measure.getBeatCount());

            // Storing the chords of the measure
            JSONArray chordsJSON = new JSONArray();
            List<Chord> chords = measure.getChords();
            for(Chord chord : chords) {
                JSONObject chordDetails = new JSONObject();
                chordDetails.put(CHORD_LEADING_NOTE, chord.getLeadingNote().getNoteStringForJFugue());
                chordDetails.put(CHORD_IS_SINGLE_NOTE, chord.isSingleNote());
                chordDetails.put(CHORD_IS_MINOR, chord.isMinor());
                chordDetails.put(CHORD_TYPE, chord.getType().toString());

                // Storing the notes of the chord
                JSONArray notesJSON = new JSONArray();
                List<Note> notes = chord.getNotes();
                for(Note note : notes) {
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

        songDetails.put(SONG_PUBLISHED, song.isPublished());

        // Storing the comments on the song
        JSONArray commentsJSON = new JSONArray();
        ArrayList<Comment> comments = song.getComments();
        for(Comment comment : comments) {
            JSONObject commentDetails = new JSONObject();
            commentDetails.put(COMMENT_COMMENTER_NAME, comment.getCommenterName());
            commentDetails.put(COMMENT_COMMENT, comment.getCommentText());
            commentDetails.put(COMMENT_USERNAME, comment.getUsername());

            commentsJSON.add(commentDetails);
        }
        songDetails.put(SONG_COMMENTS, commentsJSON);

        return songDetails;

    }

    public static boolean saveInstruments(ArrayList<Instrument> instruments) {
        JSONArray jsonInstruments = new JSONArray();

        // Create and add all songs as JSON objects
        for(int i = 0; i < instruments.size(); i++) {
            jsonInstruments.add(getInstrumentJSON(instruments.get(i)));
        }

        // Write the JSON file
        try (FileWriter file = new FileWriter(INSTRUMENT_FILE_NAME)) {
            file.write(jsonInstruments.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static JSONObject getInstrumentJSON(Instrument instrument) {
        JSONObject instrumentDetails = new JSONObject();
        instrumentDetails.put(INSTRUMENT_INSTRUMENT_NAME, instrument.getInstrumentName().toString());
        
        // Storing the chords of the instrument
        JSONArray chordsJSON = new JSONArray();
            List<Chord> chords = instrument.getChords();
            for(Chord chord : chords) {
                JSONObject chordDetails = new JSONObject();
                chordDetails.put(CHORD_LEADING_NOTE, chord.getLeadingNote().getNoteStringForJFugue());
                chordDetails.put(CHORD_IS_SINGLE_NOTE, chord.isSingleNote());
                chordDetails.put(CHORD_IS_MINOR, chord.isMinor());
                chordDetails.put(CHORD_TYPE, chord.getType().toString());

                // Storing the notes of the chord
                JSONArray notesJSON = new JSONArray();
                List<Note> notes = chord.getNotes();
                for(Note note : notes) {
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

    public static void main(String[] args) {
        // UserList userList = UserList.getInstance();
        // userList.addUser("John", "Doe", "jdoe23", "asdjihdw", "jdoe@gmail.com");
        // Author jane = new Author("Jane", "Doe", "janedoe55", "sdhfhi", "janedoe@gmail.com");
        // jane.setAuthorStatusToTrue();
        // jane.addSong(UUID.randomUUID());
        // jane.addSong(UUID.randomUUID());
        // userList.addUser(jane);
        // DataWriter.saveUsers(userList.getUsers());
        // SongLibrary songLibrary = SongLibrary.getInstance();

        // Song songTest = new Song("Test Song", jane);
        // songTest.addGenre("KIDS");
        // songTest.addGenre("POP");
        // Measure measureOne = new Measure();
        // Chord chordOne = new Chord("QUARTER", "C", true, false);
        // Chord chordTwo = new Chord("Quarter", "A", true, false);
        // Chord chordThree = new Chord("quarter", "B", true, false);
        // Chord chordFour = new Chord("quarter", "A", false, false);
        // Note bNote = new Note(Pitch.B, Type.QUARTER);
        // Note cNote = new Note(Pitch.C, Type.QUARTER);
        // chordFour.addNote(bNote);
        // chordFour.addNote(cNote);
        // measureOne.addChord(chordOne);
        // measureOne.addChord(chordTwo);
        // measureOne.addChord(chordThree);
        // measureOne.addChord(chordFour);
        // songTest.addMeasure(measureOne);
        // songTest.addComment("This is very simple, but promising!!!");
        // songTest.addComment("Stay tuned for more simple songs! :D");
        // //songTest.playSong();
        // songLibrary.addSong(songTest);
        // DataWriter.saveSongs(songLibrary.getSongs());
        DataReader.readUsers();
        DataReader.readSongs();DataReader.readInstruments();
        UserList userList = UserList.getInstance();
        SongLibrary songLibrary = SongLibrary.getInstance();
        InstrumentList instrumentList = InstrumentList.getInstance();
        songLibrary.searchByTitle("Test Song").get(0).playSong();
        songLibrary.searchByTitle("Test Song").get(0).printTabsToTextFile();
        DataWriter.saveUsers(userList.getUsers());
        DataWriter.saveSongs(songLibrary.getSongs());
        DataWriter.saveInstruments(instrumentList.getInstruments());
    }
}

