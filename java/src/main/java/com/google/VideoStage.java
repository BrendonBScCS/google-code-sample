package com.google;

/** A class used to represent a stage for videos to play */
class VideoStage {

    private Video playingVideo;
    private boolean paused;

    /**
     * Gets the video that's currently playing.
     *
     * @return the current video that's playing.
     */
    public Video getPlayingVideo() {
        return playingVideo;
    }

    /**
     * Sets the current playing video to the specified video.
     * Video is automatically set to be play and not be paused.
     *
     * @param video the video to play.
     */
    public void setPlayingVideo(Video video) {
        playingVideo = video;
        paused = false;
    }

    /**
     * Checks if a video has been selected to play.
     *
     * @return true if a video is selected;
     * false otherwise.
     */
    public boolean hasVideoPlaying() {
        return playingVideo != null;
    }

    /**
     * Checks if the currently selected playing video is the
     * one specified.
     *
     * @param video the video to check.
     * @return true if the video is selected; false otherwise.
     */
    public boolean isVideoPlaying(Video video) {
        return playingVideo == video;
    }

    /**
     * Stops the currently selected video by removing the selected video
     * and automatically unpausing.
     */
    public void stopVideo() {
        setPlayingVideo(null);
        paused = false;
    }

    /**
     * Checks if the selected video is paused.
     *
     * @return true if paused; false otherwise.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the paused state of the selected video.
     *
     * @param paused the pause state of the video.
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
