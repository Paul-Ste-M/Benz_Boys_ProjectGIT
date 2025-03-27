package com.model;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import org.jfugue.player.Player;

/**
 * Represents a musical song composed by an author.
 * A song contains a title, author info, a unique ID, genres, measures (music structure),
 * published status, and user comments.
 * Songs can be played back using JFugue and exported as tablature to a text file.
 */
public class Song {
    private String title;
    private String author;
    private String authorUsername;
    private UUID songID;
    private UUID authorID;
    private ArrayList<Genre> genre;
    private ArrayList<Measure> measures;
    private boolean published;
    private ArrayList<Comment> comments;

    /**
     * Constructs a new Song from an Author object.
     * Generates a new UUID and initializes empty genres, measures, and comments.
     *
     * @param title  the song title
     * @param author the Author who created the song
     */
    public Song(String title, Author author) {
        this.title = title;
        this.author = author.getFullName();
        this.authorUsername = author.getUsername();
        this.songID = UUID.randomUUID();
        this.authorID = author.getUserID();
        this.genre = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.published = false;
        this.comments = new ArrayList<>();
    }

    /**
     * Constructs a Song with all core fields, typically used when loading from saved data.
     *
     * @param title          the song title
     * @param author         the author's full name
     * @param authorUsername the author's username
     * @param songID         the song's UUID
     * @param authorID       the author's UUID
     * @param published      whether the song is published
     */
    public Song(String title, String author, String authorUsername, UUID songID, UUID authorID, boolean published) {
        this.title = title;
        this.author = author;
        this.authorUsername = authorUsername;
        this.songID = songID;
        this.authorID = authorID;
        this.published = published;
        this.genre = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    /**
     * Adds a measure to a specific index in the song.
     *
     * @param measure  the measure to insert
     * @param position the position to insert it at
     */
    public void addMeasure(Measure measure, int position) {
        if (position < 0 || position > measures.size()) {
            System.out.println("Invalid position");
            return;
        }
        measures.add(position, measure);
    }

    /**
     * Adds a measure to the end of the song.
     *
     * @param measure the measure to add
     */
    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    /**
     * Replaces the song’s current measures with a new list.
     *
     * @param newMeasures the new set of measures
     */
    public void addMeasures(ArrayList<Measure> newMeasures) {
        measures = newMeasures;
    }

    /**
     * Removes the measure at the given index, if the position is valid.
     *
     * @param measure  unused parameter (can be removed)
     * @param position the index of the measure to remove
     */
    public void removeMeasure(Measure measure, int position) {
        if (position < 0 || position >= measures.size()) {
            System.out.println("Invalid position");
            return;
        }
        measures.remove(position);
    }

    /**
     * Adds a genre to the song based on the provided genre name string.
     *
     * @param genreName the name of the genre (must match the Genre enum)
     */
    public void addGenre(String genreName) {
        Genre newGenre = Genre.valueOf(genreName.toUpperCase());
        genre.add(newGenre);
    }

    /**
     * Returns whether the song is published.
     *
     * @return true if published, false otherwise
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Returns the list of measures in the song.
     *
     * @return a list of Measure objects
     */
    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    /**
     * Plays the song using JFugue by compiling all chords in all measures into a single string
     * and sending it to a JFugue Player.
     */
    public void playSong() {
        String musicString = "";
        for (Measure measure : measures) {
            musicString = measure.playMeasure(musicString);
        }
        musicString = "| " + musicString;
        System.out.println(musicString);
        Player player = new Player();
        player.play("I[Guitar] " + musicString);
    }

    /**
     * Adds a new comment to the song.
     *
     * @param newComment2 the comment to add
     */
    public void addComment(Comment newComment2) {
        comments.add(newComment2);
    }

    /**
     * Sets the published status of the song.
     *
     * @param published true if the song should be marked as published
     */
    public void setPublished(boolean published) {
        this.published = published;
    }

    /**
     * Returns the list of comments on the song.
     *
     * @return a list of Comment objects
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Returns the list of genres associated with the song.
     *
     * @return a list of Genre enums
     */
    public ArrayList<Genre> getGenres() {
        ArrayList<Genre> genreNames = new ArrayList<>();
        for (Genre g : genre) {
            genreNames.add(g);
        }
        return genreNames;
    }

    /**
     * Returns the title of the song.
     *
     * @return the song title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the full name of the author.
     *
     * @return the author's name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the unique identifier of the song.
     *
     * @return the song's UUID
     */
    public UUID getSongID() {
        return songID;
    }
<<<<<<< HEAD
    

=======

    /**
     * Returns the UUID of the song’s author.
     *
     * @return the author’s UUID
     */
>>>>>>> d19f1b4de2034961027bc81329c6f71e3eb43692
    public UUID getAuthorID() {
        return authorID;
    }

    /**
     * Exports the song as tablature (guitar tab) to a text file in the /json directory.
     * The file is named after the song title with all spaces removed.
     * Each string of the guitar is printed on its own line.
     * For each chord, the note's fret number is shown if it belongs to that string.
     * If no note is played on that string, "---" is printed as a placeholder.
     */
    public void printTabsToTextFile() {
        try {
            String titleNoSpaces = title.replaceAll("\\s+", "");
            PrintWriter fileWriter = new PrintWriter(new FileOutputStream("./json/" + titleNoSpaces + ".txt"));

            fileWriter.println("\"" + title + "\" by " + authorUsername + "\n");
            fileWriter.println();

            for (int i = 1; i < 7; i++) {
                fileWriter.print(determineStringName(i) + ") |");
                for (Measure measure : measures) {
                    for (Chord chord : measure.getChords()) {
                        boolean noteExistsOnLine = false;
                        for (Note note : chord.getNotes()) {
                            if (note.getTabsLine() == i) {
                                fileWriter.print("-" + note.getFretNumber() + "-");
                                noteExistsOnLine = true;
                            }
                        }
                        if (!noteExistsOnLine) {
                            fileWriter.print("---");
                        }
                    }
                    fileWriter.print("|");
                }
                fileWriter.println();
            }

            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the string name of a guitar string based on tablature line number.
     *
     * @param tabsLine the tablature line number (1 to 6)
     * @return the string name (E, B, G, D, A, or E)
     */
    private String determineStringName(int tabsLine) {
        switch (tabsLine) {
            case 1: return "E";
            case 2: return "B";
            case 3: return "G";
            case 4: return "D";
            case 5: return "A";
            case 6: return "E";
            default: return "E";
        }
    }
}
