package com.example.challenger.springs;

/**
 * Created by CHALLENGER on 13/09/2016.
 */
public class Song {
    private int id;
    private String song_title;
    private String song_body;

    public Song(int id, String song_title, String song_body) {
        this.id = id;
        this.song_title = song_title;
        this.song_body = song_body;
    }

    public int getId() {
        return id;
    }

    public String getSong_title() {
        return song_title;
    }

    public String getSong_body() {
        return song_body;
    }
}
