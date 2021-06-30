package com.google;

import java.util.ArrayList;
import java.util.HashMap;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private final HashMap<String, ArrayList<String>> PLAYLISTS;

    public VideoPlaylist() {
        PLAYLISTS = new HashMap<>();
    }

    boolean hasPlaylist(String playlistName) {
        return PLAYLISTS.containsKey(clean(playlistName));
    }

    public void addPlaylist(String playlistName) {
        PLAYLISTS.put(clean(playlistName), new ArrayList<>());
    }

    public void addVideoToPlaylist(String playlistName, String videoId) {
        PLAYLISTS.get(clean(playlistName)).add(videoId);
    }

    public boolean hasVideoInPlaylist(String playlistName, String videoId) {
        return PLAYLISTS.get(clean(playlistName)).contains(videoId);
    }

    private String clean(String playlistName) {
        return playlistName.toLowerCase();
    }
}
