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

  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  List<Video> getVideosLexicographically() {
    ArrayList<Video> array = new ArrayList<>(this.videos.values());
    array.sort(Comparator.comparing(Video::getTitle));
    return array;
  }

  Video getRandomVideo() {
    return this.videos.isEmpty() ? null : (Video) this.videos.values().toArray()[new Random().nextInt(this.videos.values().size())];
  }

  /**
   * Get a video by id. Returns null if the video is not found.
   */
  Video getVideo(String videoId) {
    return this.videos.get(videoId);
  }

  List<Video> getVideosSearchTitle(String term) {
    ArrayList<Video> array = new ArrayList<>(this.videos.values());
    return array.stream().filter(video -> video.getTitle().toLowerCase().contains(term)).sorted(Comparator.comparing(Video::getTitle)).collect(Collectors.toList());
  }

  List<Video> getVideosSearchTags(String term) {
    ArrayList<Video> array = new ArrayList<>(this.videos.values());
    return array.stream().filter(video -> video.getTags().contains(term.toLowerCase())).sorted(Comparator.comparing(Video::getTitle)).collect(Collectors.toList());
  }
}
