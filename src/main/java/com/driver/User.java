// User.java
package com.driver;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String mobile;
    private List<Playlist> playlists;
    private List<Song> likedSongs;

    public User() {
        this.playlists = new ArrayList<>();
        this.likedSongs = new ArrayList<>();
    }

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
        this.playlists = new ArrayList<>();
        this.likedSongs = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Song> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(List<Song> likedSongs) {
        this.likedSongs = likedSongs;
    }
}