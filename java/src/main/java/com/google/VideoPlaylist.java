package com.google;

/** A class used to represent a Playlist */
class VideoPlaylist {

    private Video playingVideo;
    private boolean paused;

    public Video getPlayingVideo() {
        return playingVideo;
    }

    public void setPlayingVideo(Video video) {
        playingVideo = video;
        paused = false;
    }

    public boolean hasVideoPlaying() {
        return playingVideo != null;
    }

    public boolean isVideoPlaying(Video video) {
        return playingVideo == video;
    }

    public void stopVideo() {
        setPlayingVideo(null);
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
