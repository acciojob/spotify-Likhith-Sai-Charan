// Playlist.java
package com.driver;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String title;
    private List<Song> songs;
    private List<User> listeners;
    private User creator;

    public Playlist() {
        this.songs = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    public Playlist(String title, User creator) {
        this.title = title;
        this.creator = creator;
        this.songs = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.listeners.add(creator);
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}