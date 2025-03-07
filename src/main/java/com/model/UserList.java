package com.model;

import java.util.ArrayList;

public class UserList {
    private static UserList userList;  // Singleton instance
    private ArrayList<User> users;     // List of users

    // Private constructor to prevent instantiation from outside
    private UserList() {
        users = new ArrayList<>();
    }

    // Singleton instance getter
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    // Add a new user to the list
    public void addUser(String firstName, String lastName, String userName, String password, String email) {
        User newUser = new User(firstName, lastName, userName, password, email);
        users.add(newUser);
    }
    // Add existing user for debugging purposes
    public void addUser(User user) {
        users.add(user);
    }
    
    // Returns the list of users
    public ArrayList<User> getUsers() {
        return this.users;
    }

    // Retrieve a user based on username and password
    public User getUser(String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // User not found
    }

    // Remove a user from the list
    public void removeUser(User user) {
        users.remove(user);
    }

    // Display all users (for debugging purposes)
    public void displayUsers() {
        for (User user : users) {
            System.out.println(user.getFullName() + " (" + user.getUsername() + ")");
        }
    }

    // Save users (stub method, actual implementation depends on storage solution)
    public boolean saveUsers() {
        // Implementation for saving user data (e.g., to a file or database)
        System.out.println("Saving users...");
        return true;
    }

    public static void main(String[] args) {
        UserList userList = UserList.getInstance();

        userList.addUser("John", "Doe", "johndoe", "password", "jdoe@gmail.com");
        userList.addUser("Jane", "Smith", "janesmith", "password", "jsmith@gmail.com");

        userList.displayUsers();

    }
}
