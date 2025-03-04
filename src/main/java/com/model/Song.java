package com.model;

import java.util.ArrayList;
import java.util.UUID;

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
        this.author = author.getName();
        this.authorUsername = author.getUsername();
        this.songID = UUID.randomUUID();
        this.authorID = author.getAuthorID();
        this.genre = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.published = false;
        this.comments = new ArrayList<>();
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

    public void playSong() {
        for (Measure measure : measures) {
            measure.playMeasure();
        }
    }

    public void addComment(String commentText) {
        Comment newComment = new Comment(author, commentText, authorUsername);
        comments.add(newComment);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }


}
