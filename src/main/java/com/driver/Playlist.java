package com.driver;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String title;
    private List<Song> songs;
    private List<User> listeners;

    public Playlist(){
        this.songs=new ArrayList<>();
        this.listeners=new ArrayList<>();
    }

    public Playlist(String title){
        this.title = title;
        this.songs=new ArrayList<>();
        this.listeners=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs(){
        return songs;
    }

    public void addSong(Song song){
        this.songs.add(song);
    }

    public List<User> getListeners(){
        return listeners;
    }

    public void addListener(User user){
        this.listeners.add(user);
    }
}
