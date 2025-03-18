package com.model;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private UUID userID;
    private String password;
    private boolean isAuthor;

    public User(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userID = UUID.randomUUID();
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

    public void setAuthor(boolean isAuthor) {
        this.isAuthor = isAuthor;
    }

}
