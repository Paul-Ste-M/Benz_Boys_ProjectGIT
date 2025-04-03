//Written by Jordan Cantave

package com.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.model.*;

public class DataWriterTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private static ArrayList<User> initialUsers = new ArrayList<User>();
    private SongLibrary songLibrary = SongLibrary.getInstance();
    private ArrayList<Song> songs = songLibrary.getSongs();
    private static ArrayList<Song> initialSongs = new ArrayList<Song>();
    private InstrumentList instrumentList = InstrumentList.getInstance();
    private ArrayList<Instrument> instruments = instrumentList.getInstruments();
    private static ArrayList<Instrument> initialInstruments = new ArrayList<Instrument>();
    
    public static ArrayList<User> getInitUsers() {
        return initialUsers;
    }

    public static ArrayList<Song> getInitSongs() {
        return initialSongs;
    }

    public static ArrayList<Instrument> getInitInstruments() {
        return initialInstruments;
    }

    // For some reason, initial JSON files would not be restored after
    // testing ended. This function manually stores the JSON files for restoring.
    @BeforeClass
    public static void storeInitialJSON() {
        DataReader.readUsers();
        for(int i = 0; i < UserList.getInstance().getUsers().size(); i++) {
            User user = UserList.getInstance().getUsers().get(i);
            getInitUsers().add(user);
        }

        DataReader.readSongs();
        for(int i = 0; i < SongLibrary.getInstance().getSongs().size(); i++) {
            Song song = SongLibrary.getInstance().getSongs().get(i);
            getInitSongs().add(song);
        }

        DataReader.readInstruments();
        for(int i = 0; i < InstrumentList.getInstance().getInstruments().size(); i++) {
            Instrument instrument = InstrumentList.getInstance().getInstruments().get(i);
            getInitInstruments().add(instrument);
        }

        UserList.getInstance().getUsers().clear();
        SongLibrary.getInstance().getSongs().clear();
        InstrumentList.getInstance().getInstruments().clear();
    }

    // Restores initial JSON files due to issue mentioned above.
    @After
    public void restoreInitialJSON() {
        DataWriter.saveUsers(initialUsers);
        DataWriter.saveSongs(initialSongs);
        DataWriter.saveInstruments(initialInstruments);

        users.clear();
        songs.clear();
        instruments.clear();
    }

    @Test
    public void testTesting() {
        assertTrue(true);
    }

    //Produced Error
    @Test
    public void testSaveNullUser() {
        DataReader.readUsers();
        users.add(new User(null, null, null, null, null, null, null, false));
        DataWriter.saveUsers(users);
        users.clear();
        DataReader.readUsers();
        assertNull(users.get(3));
    }

    //Produced Error
    @Test
    public void testSaveNullSong() {
        DataReader.readUsers();
        DataReader.readSongs();
        songLibrary.addSong(new Song(null, null, null, null, null, false));
        DataWriter.saveSongs(songs);
        songs.clear();
        DataReader.readSongs();
        assertNull(users.get(4));
    }

    //Produced Unwanted Logic
    @Test
    public void testOverwriteSongsJSONIfReadUsersFails() {
        //If readSongs() were to fail AKA if readUsers() never happens, then SongLibrary.songs would be empty.
        //If this gets saved, then the initial JSON file of songs would be overwritten when the user logs out.
        DataReader.readSongs();
        DataWriter.saveSongs(songs);
        DataReader.readSongs();
        assertTrue(songs.isEmpty());
    }

    
}
