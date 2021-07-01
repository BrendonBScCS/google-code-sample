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

  /**
   * Returns the title of the video.
   */
  String getTitle() {
    return title;
  }

  /**
   * Returns the video id of the video.
   */
  String getVideoId() {
    return videoId;
  }

  /**
   * Returns a readonly collection of the tags of the video.
   */
  List<String> getTags() {
    return tags;
  }

  /**
   * Gets the video info in the form of:
   * <code>title (video id) [tags]</code>
   *
   * @return the video info.
   */
  String getInfo() {
    return getTitle() + " (" + getVideoId() + ") " + getTags().toString().replaceAll(",", "");
  }

  /**
   * Gets the video info including flagged info in the form of:
   * <code>title (video id) [tags] - FLAGGED (reason: flag reason)</code>
   *
   * @return the video info including flagged info.
   */
  String getInfoWithFlagged() {
    StringBuilder info = new StringBuilder(getInfo());
    if (isFlagged()) info.append(" - FLAGGED (reason: ").append(getFlaggedReason()).append(")");
    return info.toString();
  }

  /**
   * Checks if the video has been flagged.
   *
   * @return true if the video has been flagged;
   * false otherwise.
   */
  public boolean isFlagged() {
    return flagged;
  }

  /**
   * Gets the flagged reason, or {@link #DEFAULT_FLAGGED_REASON} if
   * one is not specified.
   *
   * @return video flagged reason or default flagged reason if one
   * is not specified.
   * @see #isFlagged()
   */
  public String getFlaggedReason() {
    return flaggedReason.isEmpty() ? DEFAULT_FLAGGED_REASON : flaggedReason;
  }

  /**
   * Sets flagged to the specified value, and clears the flagged reason.
   *
   * @param flagged the value of if the video is flagged.
   */
  public void setFlagged(boolean flagged) {
    this.flagged = flagged;
    this.flaggedReason = "";
  }

  /**
   * Sets flagged to the specified value, and flagged reason to the
   * specified value.
   *
   * @param flagged       the value of if the video is flagged.
   * @param flaggedReason the reason of why the video is flagged.
   */
  public void setFlagged(boolean flagged, String flaggedReason) {
    this.flagged = flagged;
    this.flaggedReason = flaggedReason;
  }
}
