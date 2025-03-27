// Album.java
package com.driver;

public class Album {
    private String title;
    private String artistName;

    public Album() {
    }

    public Album(String title, String artistName) {
        this.title = title;
        this.artistName = artistName;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}