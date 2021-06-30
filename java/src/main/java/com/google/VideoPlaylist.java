package com.google;

import java.util.ArrayList;
import java.util.TreeMap;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private final TreeMap<String, ArrayList<String>> PLAYLISTS;

    public VideoPlaylist() {
        PLAYLISTS = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    boolean hasPlaylist(String playlistName) {
        return PLAYLISTS.containsKey(playlistName);
    }

    public void addPlaylist(String playlistName) {
        PLAYLISTS.put(playlistName, new ArrayList<>());
    }

    public boolean hasPlaylists() {
        return !PLAYLISTS.isEmpty();
    }

    public void addVideoToPlaylist(String playlistName, String videoId) {
        PLAYLISTS.get(playlistName).add(videoId);
    }

    public boolean hasVideoInPlaylist(String playlistName, String videoId) {
        return PLAYLISTS.get(playlistName).contains(videoId);
    }

    public ArrayList<String> getPlaylistNames() {
        return new ArrayList<>(PLAYLISTS.keySet());
    }
}
