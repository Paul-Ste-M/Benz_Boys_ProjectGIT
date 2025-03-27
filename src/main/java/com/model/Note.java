package com.model;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Note {
    private Pitch pitch;
    private Type type;
    private boolean isRest;  // New flag for rest notes
    private String octave;
    private String fretNumber;
    private int tabsLine;

    // Constructor for normal notes
    public Note(Pitch pitch, Type type) {
        this.pitch = pitch;
        this.type = type;
        if(getNoteStringForJFugue().equals("R")) {
            this.isRest = true;    
        } else {
            this.isRest = false;
        }
    }

    // Constructor for reading in notes from json
    public Note(Pitch pitch, Type type, String octave, String fretNumber, int tabsLine) {
        this.pitch = pitch;
        this.type = type;
        if(getNoteStringForJFugue().equals("R")) {
            this.isRest = true;
        } else {
            this.isRest = false;
        }
        this.octave = octave;
        this.fretNumber = fretNumber;
        this.tabsLine = tabsLine;
    }

    // Constructor for rest notes (pauses)
   // public Note(Pitch pitch, Type type) {
      //  this.pitch = pitch; // No pitch for rests
      //  this.type = type;
        // this.isMinor = false;
       // this.isRest = true;
    //}

    // Getter for rest status
    public boolean isRest() {
        return isRest;
    }

    public void playNote() {
        Player player = new Player();  // Create a new JFugue player
        
        if (isRest) {
            System.out.println("Rest for " + type + " duration.");
            try {
                Thread.sleep(getDuration()); // Simulate pause for rest duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Playing guitar note: " + pitch + " " + type);
            // Format the pitch to JFugue format and create a pattern
            String noteString = getNoteStringForJFugue();
            Pattern pattern = new Pattern("I[Guitar] " + noteString);  // Play with Guitar instrument
            player.play(pattern);  // Play the note using JFugue player
        }
    }

    // Convert type to duration in milliseconds (for simulation)
    private long getDuration() {
        switch (type) {
            case WHOLE: return 1600;
            case HALF: return 800;
            case QUARTER: return 400;
            case EIGHTH: return 200;
            default: return 400; // Default to quarter note duration
        }
    }

    public String getDurationStringForJFugue() {
        return type.JFugueString;
    }

    public Type getType() {
        return type;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public String getOctave() {
        return octave;
    }

    public String getFretNumber() {
        return fretNumber;
    }

    public int getTabsLine() {
        return tabsLine;
    }

    public void setType(Type type) {
        if(type != null)
            this.type = type;
    }

    // Map the Pitch enum to JFugue note string representation
    public String getNoteStringForJFugue() {
        // switch (pitch) {
        //     case C: return "C";
        //     case D: return "D";
        //     case E: return "E";
        //     case F: return "F";
        //     case G: return "G";
        //     case A: return "A";
        //     case B: return "B";
        //     case R: return "R";
        //     // You can extend this mapping based on your pitch enum
        //     default: return "C";  // Default to C
        // }
        return pitch.JFugueString;
    }

    @Override
    public String toString() {
        if (isRest) {
            return "Rest(" + type + ")";
        }
        return "Note(" + pitch + ", " + type + ")";
    }
}
