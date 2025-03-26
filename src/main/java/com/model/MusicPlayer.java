package com.model;


import com.calmly.Music;


public class MusicPlayer {

    public void playSong() {
        try {
            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");
            Thread.sleep(400);
            Music.playNote("D");
            Music.playNote("D");
            Music.playNote("D");
            Thread.sleep(400);
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");
            Thread.sleep(400);
            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("E");
            Music.playNote("C");
            Music.playNote("D");
            Music.playNote("D");
            Music.playNote("E");
            Music.playNote("D");
            Music.playNote("C");
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        //player.playSong();
        // Music.playNote("I[Guitar] E3"); //6th root
        // Music.playNote("I[Guitar] E4"); //5th root / 4rd string fret 2
        // Music.playNote("I[Guitar] E5"); //1st root
        // Music.playNote("I[Guitar] A3"); //6th string 5th fret / 5th root
        // Music.playNote("I[Guitar] A4"); //4th string 7th fret / 3rd string 2nd fret
        // Music.playNote("I[Guitar] A5"); // 1st string 5th fret
        //Music.playNote("I[Guitar] E3maj");
        //Music.playNote("I[Guitar] E3+G#4+B3");
        //Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5 C4+E4+G4+C5+E5 D4+A4+D5+F#5 E3+B3+E4+G#4+B4+E5");
        Music.playNote("I[Guitar] G4maj C4maj D4maj E4maj");
        Music.playNote("I[Guitar] G3+B3+D4+G4+B4+G5w");
    }
}
