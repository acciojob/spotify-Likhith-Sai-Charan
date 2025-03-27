// SpotifyService.java
package com.driver;

import java.util.*;

public class SpotifyService {
    SpotifyRepository spotifyRepository = new SpotifyRepository();

    public User createUser(String name, String mobile) {
        User user = new User(name, mobile);
        spotifyRepository.getUserMap().put(mobile, user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = spotifyRepository.getArtistMap().get(name);
        if (artist == null) {
            artist = new Artist(name);
            spotifyRepository.getArtistMap().put(name, artist);
            spotifyRepository.getArtistAlbumMap().put(artist, new ArrayList<>());
        }
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Artist artist = createArtist(artistName);
        Album album = spotifyRepository.getAlbumMap().get(title);
        if (album == null) {
            album = new Album(title, artistName);
            spotifyRepository.getAlbumMap().put(title, album);
            spotifyRepository.getArtistAlbumMap().get(artist).add(album);
            spotifyRepository.getAlbumSongMap().put(album, new ArrayList<>());
        }
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception {
        Album album = spotifyRepository.getAlbumMap().get(albumName);
        if (album == null) {
            throw new Exception("Album does not exist");
        }
        Song song = spotifyRepository.getSongMap().get(title);
        if (song == null) {
            song = new Song(title, length, albumName);
            spotifyRepository.getSongMap().put(title, song);
            spotifyRepository.getAlbumSongMap().get(album).add(song);
            spotifyRepository.getSongLikeMap().put(song, new ArrayList<>());
        }
        return song;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        User user = spotifyRepository.getUserMap().get(mobile);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        Playlist playlist = new Playlist(title, user);
        spotifyRepository.getPlaylistMap().put(title, playlist);
        spotifyRepository.getCreatorPlaylistMap().put(user, playlist);
        spotifyRepository.getUserPlaylistMap().put(user, new ArrayList<>());
        spotifyRepository.getUserPlaylistMap().get(user).add(playlist);
        spotifyRepository.getPlaylistSongMap().put(playlist, new ArrayList<>());
        spotifyRepository.getPlaylistListenerMap().put(playlist, new ArrayList<>());
        spotifyRepository.getPlaylistListenerMap().get(playlist).add(user);

        for (Song song : spotifyRepository.getSongMap().values()) {
            if (song.getLength() == length) {
                spotifyRepository.getPlaylistSongMap().get(playlist).add(song);
            }
        }
        return playlist;
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        User user = spotifyRepository.getUserMap().get(mobile);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        Playlist playlist = new Playlist(title, user);
        spotifyRepository.getPlaylistMap().put(title, playlist);
        spotifyRepository.getCreatorPlaylistMap().put(user, playlist);
        spotifyRepository.getUserPlaylistMap().put(user, new ArrayList<>());
        spotifyRepository.getUserPlaylistMap().get(user).add(playlist);
        spotifyRepository.getPlaylistSongMap().put(playlist, new ArrayList<>());
        spotifyRepository.getPlaylistListenerMap().put(playlist, new ArrayList<>());
        spotifyRepository.getPlaylistListenerMap().get(playlist).add(user);

        for (String songTitle : songTitles) {
            Song song = spotifyRepository.getSongMap().get(songTitle);
            if (song != null) {
                spotifyRepository.getPlaylistSongMap().get(playlist).add(song);
            }
        }
        return playlist;
    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        User user = spotifyRepository.getUserMap().get(mobile);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        Playlist playlist = spotifyRepository.getPlaylistMap().get(playlistTitle);
        if (playlist == null) {
            throw new Exception("Playlist does not exist");
        }
        if (!spotifyRepository.getPlaylistListenerMap().get(playlist).contains(user)) {
            spotifyRepository.getPlaylistListenerMap().get(playlist).add(user);
            if (!spotifyRepository.getUserPlaylistMap().get(user).contains(playlist)) {
                spotifyRepository.getUserPlaylistMap().get(user).add(playlist);
            }
        }
        return playlist;
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        User user = spotifyRepository.getUserMap().get(mobile);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        Song song = spotifyRepository.getSongMap().get(songTitle);
        if (song == null) {
            throw new Exception("Song does not exist");
        }
        if (!spotifyRepository.getSongLikeMap().get(song).contains(user)) {
            song.setLikes(song.getLikes() + 1);
            spotifyRepository.getSongLikeMap().get(song).add(user);
            if (!spotifyRepository.getUserLikeMap().containsKey(user)) {
                spotifyRepository.getUserLikeMap().put(user, new ArrayList<>());
            }
            spotifyRepository.getUserLikeMap().get(user).add(song);

            // Auto-like the artist
            Album album = spotifyRepository.getAlbumMap().get(song.getAlbumName());
            if (album != null) {
                Artist artist = spotifyRepository.getArtistMap().get(album.getArtistName());
                if (artist != null) {
                    artist.setLikes(artist.getLikes() + 1);
                }
            }
        }
        return song;
    }

    public String mostPopularArtist() {
        int maxLikes = -1;
        String popularArtist = "";
        for (Artist artist : spotifyRepository.getArtistMap().values()) {
            if (artist.getLikes() > maxLikes) {
                maxLikes = artist.getLikes();
                popularArtist = artist.getName();
            }
        }
        return popularArtist;
    }

    public String mostPopularSong() {
        int maxLikes = -1;
        String popularSong = "";
        for (Song song : spotifyRepository.getSongMap().values()) {
            if (song.getLikes() > maxLikes) {
                maxLikes = song.getLikes();
                popularSong = song.getTitle();
            }
        }
        return popularSong;
    }
}