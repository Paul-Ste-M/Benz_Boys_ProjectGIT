package com.model;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {
    private static final String FILE_PATH = "json/users.json"; // Path to the JSON file

    public static void readUsers() {
        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONArray usersArray = (JSONArray) parser.parse(new FileReader(FILE_PATH));

            // Get the singleton instance of UserList
            UserList userList = UserList.getInstance();

            // Iterate through the JSON array and populate the UserList
            for (Object obj : usersArray) {
                JSONObject userJson = (JSONObject) obj;

                String firstName = (String) userJson.get("firstName");
                String lastName = (String) userJson.get("lastName");
                String userName = (String) userJson.get("userName");
                String password = (String) userJson.get("password");
                String email = (String) userJson.get("email");

                userList.addUser(firstName, lastName, userName, password, email);
            }

            System.out.println("Users loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load users from " + FILE_PATH);
        }
    }

    public static void main(String[] args) {
        // Call the readUsers method to populate the UserList
        readUsers();

        // Display all users for verification
        UserList.getInstance().displayUsers();
    }
}
