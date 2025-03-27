package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
        User user=new User(name, mobile);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Artist artist = artists.stream().filter(a->a.getName().equals(artistName)).findFirst().orElseGet(()->{
            Artist newArtist = new Artist(artistName);
            artists.add(newArtist);
            return newArtist;
        });
        Album album = new Album(title);
        albums.add(album);
        artistAlbumMap.computeIfAbsent(artist, k-> new ArrayList<>()).add(album);
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        Album album = albums.stream().filter(a -> a.getTitle().equals(albumName)).findFirst().orElse(null);
        if (album == null) throw new Exception("Album not found");
        Song song = new Song(title, length);
        songs.add(song);
        albumSongMap.computeIfAbsent(album, k -> new ArrayList<>()).add(song);
        return song;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        User user = users.stream().filter(u -> u.getMobile().equals(mobile)).findFirst().orElse(null);
        if (user == null) throw new Exception("User not found");
        Playlist playlist = new Playlist(title);
        for (Song song : songs) {
            if (song.getLength() == length) {
                playlist.addSong(song);
            }
        }
        playlists.add(playlist);
        creatorPlaylistMap.put(user, playlist);
        playlistListenerMap.put(playlist, new ArrayList<>(Collections.singletonList(user)));
        return playlist;
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        User user = users.stream().filter(u -> u.getMobile().equals(mobile)).findFirst().orElse(null);
        if (user == null) throw new Exception("User not found");
        Playlist playlist = new Playlist(title);
        for (String songTitle : songTitles) {
            songs.stream().filter(s -> s.getTitle().equals(songTitle)).forEach(playlist::addSong);
        }
        playlists.add(playlist);
        creatorPlaylistMap.put(user, playlist);
        playlistListenerMap.put(playlist, new ArrayList<>(Collections.singletonList(user)));
        return playlist;
    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        User user = users.stream().filter(u -> u.getMobile().equals(mobile)).findFirst().orElse(null);
        if (user == null) throw new Exception("User not found");
        Playlist playlist = playlists.stream().filter(p -> p.getTitle().equals(playlistTitle)).findFirst().orElse(null);
        if (playlist == null) throw new Exception("Playlist not found");
        playlistListenerMap.computeIfAbsent(playlist, k -> new ArrayList<>()).add(user);
        return playlist;
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        User user = users.stream().filter(u -> u.getMobile().equals(mobile)).findFirst().orElse(null);
        if (user == null) throw new Exception("User not found");
        Song song = songs.stream().filter(s -> s.getTitle().equals(songTitle)).findFirst().orElse(null);
        if (song == null) throw new Exception("Song not found");
        songLikeMap.computeIfAbsent(song, k -> new ArrayList<>()).add(user);
        song.setLikes(song.getLikes() + 1);
        return song;
    }

    public String mostPopularArtist() {
        return artists.stream().max(Comparator.comparingInt(Artist::getLikes)).map(Artist::getName).orElse(null);
    }

    public String mostPopularSong() {
        return songs.stream().max(Comparator.comparingInt(Song::getLikes)).map(Song::getTitle).orElse(null);
    }
}
