package com.model;

import java.util.UUID;

public class Author {
    private String name;
    private String username;
    private UUID authorID;

    public Author(String name, String username) {
        this.name = name;
        this.authorID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public UUID getAuthorID() {
        return authorID;
    }
// import java.util.ArrayList;
// import java.util.UUID;

// public class Author {

//     private ArrayList<UUID> createdSongs;
//     private Measure selectedMeasure;

//     public Author()
// public void removeSong(Song selectedSong){

// }
// public Song createNewSong(String title String author Song selectedSong){

// }
// public void publishSong(Song song){

// }
// public void  editSong(song Song){

// }
// public void saveSong(song Song){
    
// }
// public Measure addMeasure(Measure measure){

// }
// public void addGenre(String genre){

// }
// public void editMeasure(Measure measure){
    
// }
// public void removeMeasure(Measure measure, int position){

// }
// public editChord(String type, String pitch, int position){

// }
// public void makeNoteIntoChord(int position){

// }
// public void makeChordIntoNote(int position){

// }
// public void removeNote(int position){
    
// }

}
