package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Singleton class that manages all user accounts in the system.
 * Provides user registration, authentication, user lookup, and basic persistence hooks.
 */
public class UserList {

    /** Singleton instance of UserList. */
    private static UserList userList;

    /** List of all users registered in the system. */
    private ArrayList<User> users;

    /**
     * Private constructor to enforce singleton behavior.
     * Initializes an empty user list.
     */
    private UserList() {
        users = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of UserList.
     * If the instance does not exist yet, it will be created.
     *
     * @return the singleton instance of UserList
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * Registers a new user in the system if their username and email are unique.
     *
     * @param username   the desired username
     * @param firstName  the user's first name
     * @param lastName   the user's last name
     * @param email      the user's email address
     * @param password   the user's chosen password
     * @return the newly created User if successful, or null if the username or email is taken
     */
    public User signUp(String username, String firstName, String lastName, String email, String password) {
        for (User user : UserList.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                System.out.println("That username is taken!");
                return null;
            }
            if (user.getEmail().equals(email)) {
                System.out.println("That email has already been used!");
                return null;
            }
        }
        return addUser(firstName, lastName, username, password, email);
    }

    /**
     * Creates a new user with the provided information and adds them to the list.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param userName  the desired username
     * @param password  the user's password
     * @param email     the user's email address
     * @return the newly created User object
     */
    public User addUser(String firstName, String lastName, String userName, String password, String email) {
        User newUser = new User(firstName, lastName, userName, password, email);
        users.add(newUser);
        return newUser;
    }

    /**
     * Adds an existing User object to the list.
     * This is typically used when loading users from persistent storage.
     *
     * @param user the User object to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Attempts to log in a user by matching the provided username and password.
     *
     * @param username the username entered
     * @param password the password entered
     * @return the matching User if credentials are valid, or null otherwise
     */
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        System.out.println("Incorrect username or password");
        return null;
    }

    /**
     * Returns the full list of registered users.
     *
     * @return a list of all User objects
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Searches for a user by matching both username and password.
     *
     * @param userName the username to match
     * @param password the password to match
     * @return the matching User, or null if not found
     */
    public User getUser(String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Searches for a user by their UUID.
     *
     * @param userID the UUID of the user
     * @return the matching User if found, or null if no match
     */
    public User getUser(UUID userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Removes the specified user from the list.
     *
     * @param user the user to remove
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Prints all registered users to the console.
     * Each user's full name and username is displayed.
     * This method is primarily used for debugging purposes.
     */
    public void displayUsers() {
        for (User user : users) {
            System.out.println(user.getFullName() + " (" + user.getUsername() + ")");
        }
    }

    /**
     * Saves the current user list to persistent storage.
     * This method is currently a stub and should be implemented to support file or database storage.
     *
     * @return true if save operation succeeds (stubbed)
     */
    public boolean saveUsers() {
        System.out.println("Saving users..."); 
        return DataWriter.saveUsers(users);
    }

    /**
     * A test method that adds two users and prints the user list.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        UserList userList = UserList.getInstance();

        userList.addUser("John", "Doe", "johndoe", "password", "jdoe@gmail.com");
        userList.addUser("Jane", "Smith", "janesmith", "password", "jsmith@gmail.com");

        userList.displayUsers();
    }
}
