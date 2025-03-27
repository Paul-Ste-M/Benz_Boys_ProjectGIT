package com.model;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import org.jfugue.player.Player;

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

    public Song(String title, Author author) {
        this.title = title;
        this.author = author.getFullName();
        this.authorUsername = author.getUsername();
        this.songID = author.getAuthorId();
        this.authorID = author.getUserID();
        this.genre = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.published = false;
        this.comments = new ArrayList<>();
    }

    public Song(String title, String author, String authorUsername, UUID songID, UUID authorID, boolean published) {
        this.title = title;
        this.author = author;
        this.authorUsername = authorUsername;
        this.songID = songID;
        this.authorID = authorID;
        this.published = published;
        this.genre = new ArrayList<Genre>();
        this.measures = new ArrayList<Measure>();
        this.comments = new ArrayList<Comment>();
    }

    public void addMeasure(Measure measure, int position) {
        if (position < 0 || position > measures.size()) {
            System.out.println("Invalid position");
            return;
        }
        measures.add(position, measure);
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public void addMeasures(ArrayList<Measure> newMeasures) {
        measures = newMeasures;
    }

    public void removeMeasure(Measure measure, int position) {
        if (position < 0 || position >= measures.size()) {
            System.out.println("Invalid position");
            return;
        }
        measures.remove(position);
    }

    public void addGenre(String genreName) {
        Genre newGenre = Genre.valueOf(genreName.toUpperCase()); // Assuming an Enum Genre exists
        genre.add(newGenre);
    }

    public boolean isPublished() {
        return published;
    }

    public ArrayList<Measure> getMeasures() {
        return measures;
    }

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

    // Adding the comment to the list
    public void addComment(Comment newComment2) {
        comments.add(newComment2);
    }

    
    public void setPublished(boolean published) {
        this.published = published;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Genre> getGenres() {
        ArrayList<Genre> genreNames = new ArrayList<>();
        for (Genre g : genre) {
            genreNames.add(g);
        }
        return genreNames;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public UUID getSongID() {
        return songID;
    }
    
    public UUID getAuthorID() {
        return authorID;
    }

    public void printTabsToTextFile() {
        try {
            String titleNoSpaces = title.replaceAll("\\s+", "");
            PrintWriter fileWriter = new PrintWriter(new FileOutputStream("./json/" + titleNoSpaces + ".txt"));

            fileWriter.println("\""+title+"\" by " + authorUsername+ "\n");
            fileWriter.println();

            for(int i = 1; i < 7; i++) {
                fileWriter.print(determineStringName(i) + ") |");
                for(Measure measure : measures) {
                    for(Chord chord : measure.getChords()) {
                        boolean noteExistsOnLine = false;
                        for( Note note : chord.getNotes()) {
                            if(note.getTabsLine() == i) {
                                fileWriter.print("-" + note.getFretNumber()+ "-");
                                noteExistsOnLine = true;
                            }
                        }
                        if(!noteExistsOnLine) { // a failsafe in case the chord has less than 6 notes.
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

    private String determineStringName(int tabsLine) {
        switch(tabsLine) {
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
