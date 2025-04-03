//Written by Jordan Cantave

package com.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Assert;
import com.model.*;


public class DataReaderTest {
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

    @Test
    public void testGetUserListSize() {
        DataReader.readUsers();
        assertEquals(3, users.size());
    }

    @Test
    public void testGetUserListSizeZero() {
        DataReader.readUsers();
        users.clear();
        DataWriter.saveUsers(users);
        DataReader.readUsers();
        assertEquals(0, users.size());
    }

    @Test
    public void testGetFirstNameFirstUser() {
        DataReader.readUsers();
        assertEquals("Fellicia", users.get(0).getFirstName());
    }

    @Test
    public void testGetFirstNameLastUser() {
        DataReader.readUsers();
        assertEquals("Fred", users.get(2).getFirstName());
    }

    @Test
    public void testAddedFourthUserNullFirstName() {
        DataReader.readUsers();
        userList.addUser(null, "Jenkins", "nulljenkins", "asde", "null@jenkins");
        DataWriter.saveUsers(users);
        users.clear();
        DataReader.readUsers();
        assertNull(users.get(3).getFirstName());
        
    }

    @Test
    public void testGetSongLibrarySize() {
        DataReader.readUsers();
        DataReader.readSongs();
        assertEquals(4, songs.size());
    }

    @Test
    public void testGetSongLibrarySizeZero() {
        DataReader.readUsers();
        DataReader.readSongs();
        songs.clear();
        DataWriter.saveSongs(songs);
        DataReader.readSongs();
        assertEquals(0, songs.size());
    }

    @Test
    public void testReadSongsIfReadUsersFailed() {
        DataReader.readSongs();
        assertTrue(songs.isEmpty());
    }

    @Test
    public void testGetInstrumentsSize() {
        DataReader.readInstruments();
        assertEquals(1, instruments.size());
    }

    
}
