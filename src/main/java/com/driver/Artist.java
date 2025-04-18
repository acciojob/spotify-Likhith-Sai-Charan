// Artist.java
package com.driver;

public class Artist {
    private String name;
    private int likes;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
        this.likes = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}