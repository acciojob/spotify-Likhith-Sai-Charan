package com.driver;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spotify")
public class SpotifyController {

    SpotifyService spotifyService = new SpotifyService();

    @PostMapping("/add-user")
    public String createUser(@RequestParam String name, @RequestParam String mobile) {
        try {
            User user = spotifyService.createUser(name, mobile);
            return "User created successfully with mobile: " + mobile;
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    @PostMapping("/add-artist")
    public String createArtist(@RequestParam String name) {
        Artist artist = spotifyService.createArtist(name);
        return "Artist '" + artist.getName() + "' created successfully";
    }

    @PostMapping("/add-album")
    public String createAlbum(@RequestParam String title, @RequestParam String artistName) {
        Album album = spotifyService.createAlbum(title, artistName);
        return "Album '" + album.getTitle() + "' by artist '" + artistName + "' created successfully";
    }

    @PostMapping("/add-song")
    public String createSong(@RequestParam String title,
                             @RequestParam String albumName,
                             @RequestParam int length) {
        try {
            Song song = spotifyService.createSong(title, albumName, length);
            return "Song '" + song.getTitle() + "' added to album '" + albumName + "' successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/add-playlist-on-length")
    public String createPlaylistOnLength(@RequestParam String mobile,
                                         @RequestParam String title,
                                         @RequestParam int length) {
        try {
            Playlist playlist = spotifyService.createPlaylistOnLength(mobile, title, length);
            return "Playlist '" + playlist.getTitle() + "' created with " +
                    playlist.getSongs().size() + " songs of length " + length + " seconds";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/add-playlist-on-name")
    public String createPlaylistOnName(@RequestParam String mobile,
                                       @RequestParam String title,
                                       @RequestBody List<String> songTitles) {
        try {
            Playlist playlist = spotifyService.createPlaylistOnName(mobile, title, songTitles);
            return "Playlist '" + playlist.getTitle() + "' created with " +
                    playlist.getSongs().size() + " songs";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping("/find-playlist")
    public String findPlaylist(@RequestParam String mobile,
                               @RequestParam String playlistTitle) {
        try {
            Playlist playlist = spotifyService.findPlaylist(mobile, playlistTitle);
            return "User with mobile " + mobile + " is now listening to playlist '" +
                    playlist.getTitle() + "'";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping("/like-song")
    public String likeSong(@RequestParam String mobile,
                           @RequestParam String songTitle) {
        try {
            Song song = spotifyService.likeSong(mobile, songTitle);
            return "User with mobile " + mobile + " liked song '" +
                    song.getTitle() + "' (Total likes: " + song.getLikes() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/popular-artist")
    public String mostPopularArtist() {
        String artist = spotifyService.mostPopularArtist();
        if (artist.isEmpty()) {
            return "No artists found";
        }
        return "Most popular artist is '" + artist + "'";
    }

    @GetMapping("/popular-song")
    public String mostPopularSong() {
        String song = spotifyService.mostPopularSong();
        if (song.isEmpty()) {
            return "No songs found";
        }
        return "Most popular song is '" + song + "'";
    }
}