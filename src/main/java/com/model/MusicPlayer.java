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
        player.playSong();
    }
}
