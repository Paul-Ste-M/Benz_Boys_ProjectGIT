package com.model;

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
        this.songID = UUID.randomUUID();
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

    public void addComment(String commentText) {
        Comment newComment = new Comment(author, commentText, authorUsername);
        comments.add(newComment);
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


}
