package com.google;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private Video playingVideo;

    public Video getPlayingVideo() {
        return playingVideo;
    }

    public void setPlayingVideo(Video video) {
        playingVideo = video;
    }

    public boolean hasVideoPlaying() {
        return playingVideo != null;
    }

    public boolean isVideoPlaying(Video video) {
        return playingVideo == video;
    }
}
