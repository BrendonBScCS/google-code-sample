package com.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    if (!videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    }

    System.out.println("Showing playlist: " + playlistName);
    ArrayList<String> videoIds = videoPlaylist.getPlaylist(playlistName);
    if (!videoIds.isEmpty()) {
      for (String videoId : videoPlaylist.getPlaylist(playlistName)) {
        Video video = videoLibrary.getVideo(videoId);
        System.out.println("  " + video.getInfo());
      }
    } else System.out.println("  No videos here yet");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    if (!videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot remove video from " + playlistName + ": Playlist does not exist");
      return;
    }

    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot remove video from " + playlistName + ": Video does not exist");
      return;
    }

    if (!videoPlaylist.hasVideoInPlaylist(playlistName, videoId)) {
      System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
      return;
    }

    videoPlaylist.removeVideoFromPlaylist(playlistName, videoId);
    System.out.println("Removed video from " + playlistName + ": " + video.getTitle());
  }

  public void clearPlaylist(String playlistName) {
    if (!videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot clear playlist " + playlistName + ": Playlist does not exist");
      return;
    }

    videoPlaylist.clearPlaylist(playlistName);
    System.out.println("Successfully removed all videos from " + playlistName);
  }

  public void deletePlaylist(String playlistName) {
    if (!videoPlaylist.hasPlaylist(playlistName)) {
      System.out.println("Cannot delete playlist " + playlistName + ": Playlist does not exist");
      return;
    }

    videoPlaylist.removePlaylist(playlistName);
    System.out.println("Deleted playlist: " + playlistName);
  }

  public void searchVideos(String searchTerm) {
    List<Video> videos = videoLibrary.getVideosSearchTitle(searchTerm);
    if (videos.isEmpty()) {
      System.out.println("No search results for " + searchTerm);
      return;
    }

    System.out.println("Here are the results for " + searchTerm + ":");
    int i = 0;
    for (Video video : videos) {
      System.out.println("  " + ++i + ") " + video.getInfo());
    }

    System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
    System.out.println("If your answer is not a valid number, we will assume it's a no.");

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    if (Pattern.matches("-?[0-9]+", input)) {
      int index = Integer.parseInt(input);
      if (index <= videos.size() && index > 0) {
        Video video = videos.get(index - 1);
        playVideo(video.getVideoId());
      }
    }
  }

  public void searchVideosWithTag(String videoTag) {
    List<Video> videos = videoLibrary.getVideosSearchTags(videoTag);
    if (videos.isEmpty()) {
      System.out.println("No search results for " + videoTag);
      return;
    }

    System.out.println("Here are the results for " + videoTag + ":");
    int i = 0;
    for (Video video : videos) {
      System.out.println("  " + ++i + ") " + video.getInfo());
    }

    System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
    System.out.println("If your answer is not a valid number, we will assume it's a no.");

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    if (Pattern.matches("-?[0-9]+", input)) {
      int index = Integer.parseInt(input);
      if (index <= videos.size() && index > 0) {
        Video video = videos.get(index - 1);
        playVideo(video.getVideoId());
      }
    }
  }

  public void flagVideo(String videoId) {
    flagVideo(videoId,"");
  }

  public void flagVideo(String videoId, String reason) {
    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot flag video: Video does not exist");
      return;
    }

    if (video.isFlagged()) {
      System.out.println("Cannot flag video: Video is already flagged");
      return;
    }

    video.setFlagged(true, reason);
    System.out.println("Successfully flagged video: " + video.getTitle() + " (reason: " + video.getFlaggedReason() + ")");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}