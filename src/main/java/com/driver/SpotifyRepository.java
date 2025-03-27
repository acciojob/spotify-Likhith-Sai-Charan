// SpotifyRepository.java
package com.driver;

import java.util.*;

public class SpotifyRepository {
    private HashMap<Artist, List<Album>> artistAlbumMap;
    private HashMap<Album, List<Song>> albumSongMap;
    private HashMap<Playlist, List<Song>> playlistSongMap;
    private HashMap<Playlist, List<User>> playlistListenerMap;
    private HashMap<User, Playlist> creatorPlaylistMap;
    private HashMap<User, List<Playlist>> userPlaylistMap;
    private HashMap<Song, List<User>> songLikeMap;
    private HashMap<User, List<Song>> userLikeMap;
    private HashMap<String, User> userMap;
    private HashMap<String, Artist> artistMap;
    private HashMap<String, Album> albumMap;
    private HashMap<String, Song> songMap;
    private HashMap<String, Playlist> playlistMap;

    public SpotifyRepository(){
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();
        userLikeMap = new HashMap<>();
        userMap = new HashMap<>();
        artistMap = new HashMap<>();
        albumMap = new HashMap<>();
        songMap = new HashMap<>();
        playlistMap = new HashMap<>();
    }

    // Getters for all maps
    public HashMap<Artist, List<Album>> getArtistAlbumMap() {
        return artistAlbumMap;
    }

    public HashMap<Album, List<Song>> getAlbumSongMap() {
        return albumSongMap;
    }

    public HashMap<Playlist, List<Song>> getPlaylistSongMap() {
        return playlistSongMap;
    }

    public HashMap<Playlist, List<User>> getPlaylistListenerMap() {
        return playlistListenerMap;
    }

    public HashMap<User, Playlist> getCreatorPlaylistMap() {
        return creatorPlaylistMap;
    }

    public HashMap<User, List<Playlist>> getUserPlaylistMap() {
        return userPlaylistMap;
    }

    public HashMap<Song, List<User>> getSongLikeMap() {
        return songLikeMap;
    }

    public HashMap<User, List<Song>> getUserLikeMap() {
        return userLikeMap;
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public HashMap<String, Artist> getArtistMap() {
        return artistMap;
    }

    public HashMap<String, Album> getAlbumMap() {
        return albumMap;
    }

    public HashMap<String, Song> getSongMap() {
        return songMap;
    }

    public HashMap<String, Playlist> getPlaylistMap() {
        return playlistMap;
    }
}