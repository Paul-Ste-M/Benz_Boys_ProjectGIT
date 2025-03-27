package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a user in the system.
 * A user may or may not be an author and can have associated created songs.
 * Users have personal information including name, username, email, and a UUID identifier.
 */
public class User {

    /** The user's first name. */
    private String firstName;

    /** The user's last name. */
    private String lastName;

    /** The user's unique username. */
    private String userName;

    /** The user's email address. */
    private String email;

    /** The user's unique UUID. */
    private UUID userID;

    /** The user's login password. */
    private String password;

    /** A flag indicating whether this user is an author. */
    private boolean isAuthor;

    /** A list of UUIDs representing songs this user has created. */
    protected ArrayList<UUID> createdSongs;

    /**
     * Constructs a new User when registering a new account.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param userName  the unique username
     * @param password  the user's password
     * @param email     the user's email address
     */
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

    /**
     * Constructs a User from existing data, such as when reading from persistent storage.
     *
     * @param firstName     the user's first name
     * @param lastName      the user's last name
     * @param userName      the unique username
     * @param password      the user's password
     * @param email         the user's email address
     * @param userID        the user's UUID as a string
     * @param createdSongs  a list of UUIDs for songs the user has created
     * @param isAuthor      true if the user is an author, false otherwise
     */
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

    /**
     * Placeholder method for checking or converting a user to an author.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param userName  the user's username
     * @param password  the user's password
     * @param email     the user's email
     * @return true if successful (stubbed)
     */
    public boolean Author(String firstName, String lastName, String userName, String password, String email) {
        return true;
    }

    /**
     * Placeholder method to play a song.
     *
     * @param song the song to play
     */
    public void playSong(Song song) {
        // TODO: Implement playback logic
    }

    /**
     * Placeholder method to pause a song.
     *
     * @param song the song to pause
     */
    public void pauseSong(Song song) {
        // TODO: Implement pause logic
    }

    /**
     * Placeholder method to increase playback volume.
     */
    public void volumeIncrease() {
        // TODO: Implement volume increase
    }

    /**
     * Placeholder method to decrease playback volume.
     */
    public void volumeDescrease() {
        // TODO: Implement volume decrease
    }

    /**
     * Gets the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the user's full name by combining first and last name.
     *
     * @return the full name
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.userName;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the user's email address.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the user's UUID.
     *
     * @return the UUID
     */
    public UUID getUserID() {
        return this.userID;
    }

    /**
     * Checks whether this user is an author.
     *
     * @return true if the user is an author, false otherwise
     */
    public boolean isAuthor() {
        return this.isAuthor;
    }

    /**
     * Marks this user as an author.
     */
    public void setAuthorStatusToTrue() {
        this.isAuthor = true;
    }

    /**
     * Gets the list of song UUIDs that the user has created.
     *
     * @return the list of created song UUIDs
     */
    public ArrayList<UUID> getCreatedSongs() {
        return this.createdSongs;
    }
}
