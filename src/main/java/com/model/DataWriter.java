package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            // return false;            
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

    public boolean saveSongs(ArrayList<Song> songs) {
            return false;
    }

    public boolean saveSounds(ArrayList<Instrument> sounds) {
        return true;
    }

    public static void main(String[] args) {
        UserList userList = UserList.getInstance();
        userList.addUser("John", "Doe", "jdoe23", "asdjihdw", "jdoe@gmail.com");
        Author jane = new Author("Jane", "Doe", "janedoe55", "sdhfhi", "janedoe@gmail.com");
        jane.setAuthorStatusToTrue();
        jane.addSong(UUID.randomUUID());
        jane.addSong(UUID.randomUUID());
        userList.addUser(jane);
        DataWriter.saveUsers(userList.getUsers());
    }
}

