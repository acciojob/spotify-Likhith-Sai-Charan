// Song.java
package com.driver;

public class Song {
    private String title;
    private int length;
    private int likes;
    private String albumName;

    public Song() {
    }

    public Song(String title, int length, String albumName) {
        this.title = title;
        this.length = length;
        this.albumName = albumName;
        this.likes = 0;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}