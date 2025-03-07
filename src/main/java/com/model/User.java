package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private UUID userID;
    private String password;
    private boolean isAuthor;
    protected ArrayList<UUID> createdSongs;

    // Constructor when creating a newly registered user
    public User(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userID = UUID.randomUUID();
        this.createdSongs = new ArrayList<>();
        this.isAuthor = false;
    }

    // Constructor when reading in an existing user from users.json
    public User(String firstName, String lastName, String userName, String password, String email, String userID, ArrayList<UUID> createdSongs, boolean isAuthor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userID = UUID.fromString(userID);
        this.createdSongs = createdSongs;
        this.isAuthor = isAuthor;
    }

    public boolean Author(String firstName, String lastName, String userName, String password, String email) {
        // Implementation for author validation or conversion
        return true;
    }

    public void playSong(Song song) {
        // Implementation to play a song
    }

    public void pauseSong(Song song) {
        // Implementation to pause a song
    }

    public void volumeIncrease() {
        // Implementation to increase volume
    }

    public void volumeDescrease() {
        // Implementation to decrease volume
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public boolean isAuthor() {
        return this.isAuthor;
    }

    // Will probably be fleshed out more soon?
    public void setAuthorStatusToTrue() {
        this.isAuthor = true;
    }

    public ArrayList<UUID> getCreatedSongs() {
        return this.createdSongs;
    }
    
}
