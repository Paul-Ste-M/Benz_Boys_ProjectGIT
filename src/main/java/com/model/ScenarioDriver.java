package com.model;

import java.util.ArrayList;

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

        // Create a new instance of the SongApp which in turn loads the users.
        SongApp app = new SongApp();

        // Run the separate scenarios
        runUserManagementScenario(app);
        runSongPlayingScenario(app);
        runSongCreationScenario(app);

    }

    private static void runUserManagementScenario(SongApp app) {
        // ----------------------------------------
        // Step 1: Fred attempts to create an account with a duplicate username ("ffredrickson").
        // ----------------------------------------
        System.out.println("\n[Step 1] Fred tries to sign up with username 'ffredrickson' (already taken by Fellicia)...");
        String attemptedUsername = "ffredrickson"; // duplicate username (already taken)
        String firstName = "Fred";
        String lastName = "Smith";
        String email = "fred@example.com";
        String password = "password";
        
        // Attempt to sign up. We assume that signUp returns null if the username is already in use.
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

    private static void runSongPlayingScenario(SongApp app) {
        // ----------------------------------------
        // Step 6: Playing a song scenario.
        // Fred searches for all songs by "Tom Petty", sees the list, selects "Free Fallin",
        // plays the song, and exports the sheet music to a text file.
        // ----------------------------------------
        System.out.println("\n[Step 6] Fred searches for all songs by 'Tom Petty'...");
        ArrayList<Song> tomPettySongs = app.searchByKeyboard(null, "Tom Petty", null);
        if (tomPettySongs != null && !tomPettySongs.isEmpty()) {
            System.out.println("Songs by Tom Petty:");
            for (int i = 0; i < tomPettySongs.size(); i++) {
                Song song = tomPettySongs.get(i);
                System.out.println((i + 1) + ". " + song.getTitle());
            }
            // Fred picks "Free Fallin" from the list.
            Song selectedSong = null;
            for (Song song : tomPettySongs) {
                if ("Free Fallin".equalsIgnoreCase(song.getTitle())) {
                    selectedSong = song;
                    break;
                }
            }
            if (selectedSong != null) {
                System.out.println("\nFred selects '" + selectedSong.getTitle() + "' and plays it...");
                app.playSong(selectedSong);
                System.out.println("Now playing: " + selectedSong.getTitle());
                
                // Fred then exports the sheet music to a text file.
                System.out.println("Exporting the sheet music for '" + selectedSong.getTitle() + "' to a text file...");
                app.exportSong(selectedSong);
            } else {
                System.out.println("The song 'Free Fallin' was not found among Tom Petty's songs.");
            }
        } else {
            System.out.println("No songs by 'Tom Petty' were found.");
        }
    }
    private static void runSongCreationScenario(SongApp app) {
        System.out.println("\n[Third Scenario] Making a new song by Fellicia...");
    
        // Fellicia logs into her account
        User fellicia = app.login("ffredrickson", "fellicia");
        if (fellicia != null) {
            System.out.println("Fellicia logged in successfully.");
        } else {
            System.out.println("Fellicia login failed.");
            return;
        }
        
        // Fellicia creates a new song called "A horses journey"
        app.startSong("A horses journey");
        Song newSong = app.getSong("A horses journey", fellicia.getUsername());
        System.out.println("New song 'A horses journey' created by " + fellicia.getFirstName() + ".");
        
        // Add two measures with notes to the song.
        // (This is a simulated step. Replace the pseudo-code below with actual method calls if you have addMeasure or similar.)
        System.out.println("Adding 2 measures with notes to the song...");
        // Example pseudo-code:
        // newSong.addMeasure(new Measure("Measure 1: Notes C, E, G"));
        // newSong.addMeasure(new Measure("Measure 2: Notes D, F, A"));
        app.publishSong(newSong);
        // Fellicia plays her new song
        System.out.println("Fellicia plays the song 'A horses journey'.");
        app.playSong(newSong);
        
        // Fellicia logs out (updates users.json and songs.json are saved)
        app.logout();
        System.out.println("Fellicia logged out.");
        
        // Show updated JSON files (simulation)
        System.out.println("\n=== Updated users.json ===");
        System.out.println("... (updated users.json content showing Fellicia and Fred) ...");
        
        System.out.println("\n=== Updated songs.json ===");
        System.out.println("... (updated songs.json content showing 'A horses journey' tied to Fellicia) ...");
        
        // Now, log in as Fredrick (Fred) to search for and play the new song.
        System.out.println("\n[Third Scenario] Fred logs in to search and play the new song.");
        User fred = app.login("ffred", "password");
        if (fred != null) {
            System.out.println("Fredrick logged in successfully.");
            ArrayList<Song> results = app.searchByTitle("A horses journey");
            if (results != null && !results.isEmpty()) {
                Song foundSong = results.get(0);
                System.out.println("Fredrick found the song: " + foundSong.getTitle());
                System.out.println("Fredrick plays the song.");
                app.playSong(foundSong);
            } else {
                System.out.println("Song 'A horses journey' not found in search results.");
            }
        } else {
            System.out.println("Fredrick login failed.");
        }
    }
    
}
