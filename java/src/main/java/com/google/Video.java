package com.google;

import java.util.Collections;
import java.util.List;

/** A class used to represent a video. */
class Video {

  private final String title;
  private final String videoId;
  private final List<String> tags;
  private boolean flagged;
  private String flaggedReason;
  private static final String DEFAULT_FLAGGED_REASON = "Not supplied";

  Video(String title, String videoId, List<String> tags) {
    this.title = title;
    this.videoId = videoId;
    this.tags = Collections.unmodifiableList(tags);
    this.flagged = false;
    this.flaggedReason = "";
  }

  /** Returns the title of the video. */
  String getTitle() {
    return title;
  }

  /** Returns the video id of the video. */
  String getVideoId() {
    return videoId;
  }

  /** Returns a readonly collection of the tags of the video. */
  List<String> getTags() {
    return tags;
  }

  String getInfo() {
    return getTitle() + " (" + getVideoId() + ") " + getTags().toString().replaceAll(",", "");
  }

  public boolean isFlagged() {
    return flagged;
  }

  public String getFlaggedReason() {
    return flaggedReason.isEmpty() ? DEFAULT_FLAGGED_REASON : flaggedReason;
  }

  public void setFlagged(boolean flagged) {
    this.flagged = flagged;
  }

  public void setFlagged(boolean flagged, String flaggedReason) {
    this.flagged = flagged;
    this.flaggedReason = flaggedReason;
  }
}
