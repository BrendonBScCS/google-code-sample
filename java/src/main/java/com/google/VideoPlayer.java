package com.google;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private final VideoStage videoStage;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.videoStage = new VideoStage();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    for (Video video : videoLibrary.getVideosLexicographically()) {
      System.out.println("  " + video.getInfo());
    }
  }

  public void playVideo(String videoId) {
    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }

    if (videoStage.hasVideoPlaying())
      System.out.println("Stopping video: " + videoStage.getPlayingVideo().getTitle());

    System.out.println("Playing video: " + video.getTitle());
    videoStage.setPlayingVideo(video);
  }

  public void stopVideo() {
    if (!videoStage.hasVideoPlaying()) {
      System.out.println("Cannot stop video: No video is currently playing");
      return;
    }
    Video video = videoStage.getPlayingVideo();
    System.out.println("Stopping video: " + video.getTitle());
    videoStage.stopVideo();
  }

  public void playRandomVideo() {
    Video video = videoLibrary.getRandomVideo();
    if (video == null) {
      System.out.println("No videos available");
      return;
    }

    if (videoStage.hasVideoPlaying())
      System.out.println("Stopping video: " + videoStage.getPlayingVideo().getTitle());

    System.out.println("Playing video: " + video.getTitle());
    videoStage.setPlayingVideo(video);
  }

  public void pauseVideo() {
    if (videoStage.hasVideoPlaying()) {
      Video video = videoStage.getPlayingVideo();
      if (videoStage.isPaused()) {
        System.out.println("Video already paused: " + video.getTitle());
        return;
      }

      System.out.println("Pausing video: " + video.getTitle());
      videoStage.setPaused(true);
    } else System.out.println("Cannot pause video: No video is currently playing");
  }

  public void continueVideo() {
    if (videoStage.hasVideoPlaying()) {
      Video video = videoStage.getPlayingVideo();
      if (videoStage.isPaused()) {
        System.out.println("Continuing video: " + video.getTitle());
        videoStage.setPaused(false);
      } else System.out.println("Cannot continue video: Video is not paused");
    } else System.out.println("Cannot continue video: No video is currently playing");
  }

  public void showPlaying() {
    if (videoStage.hasVideoPlaying()) {
      Video video = videoStage.getPlayingVideo();
      StringBuilder playing = new StringBuilder(video.getInfo());
      if (videoStage.isPaused())
        playing.append(" - ").append("PAUSED");

      System.out.println("Currently playing: " + playing.toString());
    } else System.out.println("No video is currently playing");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}