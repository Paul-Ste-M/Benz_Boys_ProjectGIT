package com.model;

import java.util.UUID;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String hashedPassword; // Store hashed passwords
    private String email;
    private UUID userID;
    private String password;

    // Constructor for creating a new user (password will be hashed)
    public User(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userID = UUID.randomUUID(); 
    }

    // Constructor for loading user from JSON (password is already hashed)
    public User(String firstName, String lastName, String userName, String password, String email, UUID userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userID = userID;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getpassword() {
        return password;
    }

public boolean Author(String firstName, String lastName, String userName, String password, String email, UUID userID){
    return true;
}
public void addSong(Song Song){
}
public void removeSong(Song Song){
}
public void playSong(Song Song){
}
public void pauseSong(Song song){
}
public void volumeIncrease(){   
}
public void volumeDescrease(){   
}
}
