package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A class used to represent a Video Library.
 */
class VideoLibrary {

  private final HashMap<String, Video> videos;

  VideoLibrary() {
    this.videos = new HashMap<>();
    try {
      File file = new File(this.getClass().getResource("/videos.txt").getFile());

      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] split = line.split("\\|");
        String title = split[0].strip();
        String id = split[1].strip();
        List<String> tags;
        if (split.length > 2) {
          tags = Arrays.stream(split[2].split(",")).map(String::strip).collect(
                  Collectors.toList());
        } else {
          tags = new ArrayList<>();
        }
        this.videos.put(id, new Video(title, id, tags));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find videos.txt");
      e.printStackTrace();
    }
  }

  /**
   * Gets all the videos.
   *
   * @return a list of all the saved videos.
   */
  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  /**
   * Gets all the videos, ordered lexicographically on the title.
   *
   * @return a list of all the lexicographically-ordered videos.
   */
  List<Video> getVideosLexicographically() {
    ArrayList<Video> array = new ArrayList<>(this.videos.values());
    array.sort(Comparator.comparing(Video::getTitle));
    return array;
  }

  /**
   * Gets a random non-flagged video.
   *
   * @return a non-flagged video chosen at random.
   * @see #getNonFlaggedVideos()
   */
  Video getRandomVideo() {
    List<Video> array = getNonFlaggedVideos();
    return array.isEmpty() ? null : array.get(new Random().nextInt(array.size()));
  }

  /**
   * Gets all the videos that aren't flagged.
   *
   * @return all non-flagged videos.
   */
  List<Video> getNonFlaggedVideos() {
    return new ArrayList<>(this.videos.values()).stream().filter(video -> !video.isFlagged()).collect(Collectors.toList());
  }

  /**
   * Get a video by id. Returns null if the video is not found.
   */
  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }

  /**
   * Searches through all non-flagged videos for all that contain the
   * specified term in the video title, returned in lexicographical order.
   *
   * @param term the term to search for in the title.
   * @return lexicographically-ordered list of all non-flagged videos
   * containing the term in the title.
   * @see #getNonFlaggedVideos()
   */
  List<Video> getVideosSearchTitle(String term) {
    List<Video> array = getNonFlaggedVideos();
    return array.stream().filter(video -> video.getTitle().toLowerCase().contains(term)).sorted(Comparator.comparing(Video::getTitle)).collect(Collectors.toList());
  }

  /**
   * Searches through all non-flagged videos for all that contain the
   * specified tag in the video tags, returned in lexicographical order.
   *
   * @param videoTag the tag to search for in the tags.
   * @return lexicographically-ordered list of all non-flagged videos
   * containing the tag in the tags.
   * @see #getNonFlaggedVideos()
   */
  List<Video> getVideosSearchTags(String videoTag) {
    List<Video> array = getNonFlaggedVideos();
    return array.stream().filter(video -> video.getTags().contains(videoTag.toLowerCase())).sorted(Comparator.comparing(Video::getTitle)).collect(Collectors.toList());
  }
}
