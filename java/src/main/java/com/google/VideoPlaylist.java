package com.google;

import java.util.ArrayList;
import java.util.TreeMap;

/** A class used to represent a Playlist */
class VideoPlaylist {

    /**
     * Stores all the playlists and the Ids of their videos.
     * Has CASE_INSENSITIVE_ORDER for capitalisation-agnostic
     * storing, while retaining original key.
     */
    private final TreeMap<String, ArrayList<String>> PLAYLISTS;

    public VideoPlaylist() {
        PLAYLISTS = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * Checks if the specified playlist already exists.
     *
     * @param playlistName the name of the playlist.
     * @return true if the playlist already exists;
     * false otherwise.
     */
    boolean hasPlaylist(String playlistName) {
        return PLAYLISTS.containsKey(playlistName);
    }

    /**
     * Adds the specified playlist.
     *
     * @param playlistName the name of the playlist.
     */
    public void addPlaylist(String playlistName) {
        PLAYLISTS.put(playlistName, new ArrayList<>());
    }

    /**
     * Remove the specified playlist.
     *
     * @param playlistName the name of the playlist.
     */
    public void removePlaylist(String playlistName) {
        PLAYLISTS.remove(playlistName);
    }

    /**
     * Checks if any playlists have been created.
     *
     * @return true if playlists have been created;
     * false otherwise.
     */
    public boolean hasPlaylists() {
        return !PLAYLISTS.isEmpty();
    }

    /**
     * Adds the specified videoId to the specified playlist.
     *
     * @param playlistName the playlist to add to.
     * @param videoId      the videoId to add.
     */
    public void addVideoToPlaylist(String playlistName, String videoId) {
        PLAYLISTS.get(playlistName).add(videoId);
    }

    /**
     * Removes the specified videoId from the specified playlist.
     *
     * @param playlistName the playlist to remove from.
     * @param videoId      the videoId to remove.
     */
    public void removeVideoFromPlaylist(String playlistName, String videoId) {
        PLAYLISTS.get(playlistName).remove(videoId);
    }

    /**
     * Removes all videoIds from the specified playlist.
     *
     * @param playlistName the playlist to clear.
     */
    public void clearPlaylist(String playlistName) {
        PLAYLISTS.put(playlistName, new ArrayList<>());
    }

    /**
     * Checks if the specified playlist has the specified videoId.
     *
     * @param playlistName the playlist.
     * @param videoId      the videoId.
     * @return true if the playlist has the videoId; otherwise false.
     */
    public boolean hasVideoInPlaylist(String playlistName, String videoId) {
        return PLAYLISTS.get(playlistName).contains(videoId);
    }

    /**
     * Gets a list of all the names of the playlists.
     *
     * @return list of all the names of the playlists.
     */
    public ArrayList<String> getPlaylistNames() {
        return new ArrayList<>(PLAYLISTS.keySet());
    }

    /**
     * Gets the list of videoIds of the specified playlist.
     *
     * @param playlistName the name of the playlist.
     * @return list of all videoIds of the playlist.
     */
    public ArrayList<String> getPlaylist(String playlistName) {
        return PLAYLISTS.get(playlistName);
    }
}
