package com.model;

import java.util.UUID;

public class User {
    
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private UUID userID;

public User(String firstName, String lastName, String userName,  String password, String email, UUID userID){
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.email = email;
}

public String getfirstName() {
    return firstName;
}
public String getlastName() {
    return lastName;
}
public String getUsername() {
    return userName;
}
public String getpassword() {
    return password;
}
public String getemail(){
    return email; 
}
public UUID userID(){
    return userID;
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
