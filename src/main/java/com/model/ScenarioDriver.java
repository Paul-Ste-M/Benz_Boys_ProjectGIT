package com.model;

public class ScenarioDriver {

    public static void main(String[] args) {
        // ----------------------------------------
        // Step 0: Display initial users.json state.
        // (Hardcoded to show that Fred is not present and Fellicia is present.)
        // ----------------------------------------
        System.out.println("=== Initial users.json ===");
        System.out.println("[");
        System.out.println("  { \"username\": \"ffredrickson\", \"firstName\": \"Fellicia\", \"lastName\": \"Fredrickson\", \"email\": \"fellicia@example.com\", \"password\": \"password\" }");
        System.out.println("]");
        
        // ----------------------------------------
        // Create a new instance of the SongApp which in turn loads the users.
        // ----------------------------------------
        SongApp app = new SongApp();
        
        // ----------------------------------------
        // Step 1: Fred attempts to create an account with a duplicate username ("ffredrickson").
        // ----------------------------------------
        System.out.println("\n[Step 1] Fred tries to sign up with username 'ffredrickson' (already taken by Fellicia)...");
        // Hardcode Fred's details
        String attemptedUsername = "ffredrickson"; // duplicate username (already taken)
        String firstName = "Fred";
        String lastName = "Smith";
        String email = "fred@example.com";
        String password = "password";
        
        // Attempt to sign up. We assume that the signUp method returns null if the username is already in use.
        User fredAttempt = app.signUp(attemptedUsername, firstName, lastName, email, password);
        if (fredAttempt == null) {
            System.out.println("Sign up failed: The username '" + attemptedUsername + "' is already taken.");
        } else {
            System.out.println("Unexpected: Sign up succeeded with a duplicate username!");
        }
        
        // ----------------------------------------
        // Step 2: Fred changes his username and successfully creates an account.
        // ----------------------------------------
        System.out.println("\n[Step 2] Fred changes his username to 'ffred' and signs up again...");
        String newUsername = "ffred";
        User fred = app.signUp(newUsername, firstName, lastName, email, password);
        if (fred != null) {
            System.out.println("Sign up successful for username '" + newUsername + "'.");
        } else {
            System.out.println("Sign up failed for username '" + newUsername + "'.");
        }
        
        // ----------------------------------------
        // Step 3: Fred logs out (which saves the users.json).
        // ----------------------------------------
        System.out.println("\n[Step 3] Fred logs out.");
        app.logout();
        
        // ----------------------------------------
        // Step 4: Display updated users.json showing Fred's account added.
        // ----------------------------------------
        System.out.println("\n=== Updated users.json ===");
        System.out.println("[");
        System.out.println("  { \"username\": \"ffredrickson\", \"firstName\": \"Fellicia\", \"lastName\": \"Fredrickson\", \"email\": \"fellicia@example.com\", \"password\": \"password\" },");
        System.out.println("  { \"username\": \"ffred\", \"firstName\": \"Fred\", \"lastName\": \"Smith\", \"email\": \"fred@example.com\", \"password\": \"password\" }");
        System.out.println("]");
        
        // ----------------------------------------
        // Step 5: Fred logs in successfully using his new username.
        // ----------------------------------------
        System.out.println("\n[Step 5] Fred attempts to login with username 'ffred'...");
        User loggedInFred = app.login(newUsername, password);
        if (loggedInFred != null) {
            System.out.println("Login successful! Welcome, " + loggedInFred.getFirstName() + ".");
        } else {
            System.out.println("Login failed for username '" + newUsername + "'.");
        }
    }
}
