package com.google;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private final VideoStage videoStage;
  private final VideoPlaylist videoPlaylist;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.videoStage = new VideoStage();
    this.videoPlaylist = new VideoPlaylist();
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
    if (videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
      return;
    }
    videoPlaylist.addPlaylist(playlistName);
    System.out.println("Successfully created new playlist: " + playlistName);
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if (!videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
      return;
    }

    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      return;
    }

    if (videoPlaylist.hasVideoInPlaylist(playlistName, videoId)) {
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
      return;
    }

    videoPlaylist.addVideoToPlaylist(playlistName, videoId);
    System.out.println("Added video to " + playlistName + ": " + video.getTitle());
  }

  public void showAllPlaylists() {
    if (!videoPlaylist.hasPlaylists()) {
      System.out.println("No playlists exist yet");
      return;
    }

    System.out.println("Showing all playlists:");
    for (String playlistName : videoPlaylist.getPlaylistNames()) {
      System.out.println("  " + playlistName);
    }
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