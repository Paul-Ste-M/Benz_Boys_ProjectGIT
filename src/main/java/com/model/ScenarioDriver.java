
package com.model;

import java.util.ArrayList;

public class ScenarioDriver {

    public static void main(String[] args) {

        // Create a new instance of the SongApp which in turn loads the users.
        SongApp app = new SongApp();

        // Run the separate scenarios
        runUserManagementScenario(app);
        runSongPlayingScenario(app);
        runSongCreationScenario(app);
        runPlayCreatedSongScenario(app);

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
        // Fred logs in.
        // Fred searches for all songs by "Tom Petty", sees the list, selects "Free Fallin",
        // plays the song, and exports the sheet music to a text file.
        // ----------------------------------------
            
        app.login("ffred", "password");

        System.out.println("\n[Step 6] Fred searches for all songs by 'Tom Petty'...");

        ArrayList<Song> tomPettySongs = app.searchByAuthor("Tom Petty");

        if (tomPettySongs != null && !tomPettySongs.isEmpty()) {
            System.out.println("Songs by Tom Petty:");
            for (int i = 0; i < tomPettySongs.size(); i++) {
                Song song = tomPettySongs.get(i);
                System.out.println((i + 1) + ". " + song.getTitle());
            }
        //     // Fred picks "Free Fallin" from the list.
             Song selectedSong = app.selectSongFromResults(2);

            if (selectedSong != null) {
                System.out.println("\nFred selects '" + selectedSong.getTitle() + "' and plays it...");

                app.playSong();

                System.out.println("Now playing: " + selectedSong.getTitle());

                // Fred then exports the sheet music to a text file.
                System.out.println("Exporting the sheet music for '" + selectedSong.getTitle() + "' to a text file...");

                app.exportSong();

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
        Song newSong = app.startSong("A horses journey");
        System.out.println("New song 'A horses journey' created by " + fellicia.getFirstName() + ".");



        // Add two measures with notes to the song.
        System.out.println(newSong);

        app.selectMeasure(0);

        app.addChord(0, "QUARTER", "C", true, false, "4", "3", 5);
        app.addChord("QUARTER", "D", true, false, "4", "0", 4);
        app.addChord("QUARTER", "G", false, true, "3", "3", 6);
        app.addChord("QUARTER", "G", false, false, "3", "3", 6);

        app.addMeasure(new Measure());
        app.selectMeasure(1);
        app.addChord("QUARTER", "C", true, false, "4", "3", 5);
        app.addChord("EIGHTH", "D", true, false, "4", "0", 4);
        app.addChord("EIGHTH", "D", true, false, "4", "0", 4);
        app.addChord("QUARTER", "E", false, false, "3", "0", 6);
        app.addChord("QUARTER", "E", false, true, "3", "0", 6);
        

        app.publishSong(newSong);
        // Fellicia plays her new song
        System.out.println("Fellicia plays the song 'A horses journey'.");
        app.playSong();

        // Fellicia logs out (updates users.json and songs.json are saved)
        app.logout();
        System.out.println("Fellicia logged out.");

        // Show updated JSON files (simulation)
    }
private static void runPlayCreatedSongScenario(SongApp app) {
        // Now, log in as Fredrick (Fred) to search for and play the new song.
        System.out.println("\n[Third Scenario] Fred logs in to search and play the new song.");
        User fred = app.login("ffred", "password");
        if (fred != null) {
            System.out.println("Fredrick logged in successfully.");
            
            // Fred searches for song by title
            ArrayList<Song> results = app.searchByTitle("A horses journey");

            // Fred selects "A horses journey"
            Song horseJourney = app.selectSongFromResults(0);
    
            if (results != null && !results.isEmpty()) {
                System.out.println("Fredrick found the song: " + horseJourney.getTitle());
                System.out.println("Fredrick plays the song.");
                app.playSong();
                app.exportSong();
            } else {
                System.out.println("Song 'A horses journey' not found in search results.");
            }
        } else {
            System.out.println("Fredrick login failed.");
        }
    }

}
