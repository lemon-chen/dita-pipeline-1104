package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The climax parts of the music.
 */
public class ClimaxSegment {
  /**
   * The time (ms) when the climax part begins.
   */
  public int startTimeMs;
  /**
   * The time (ms) when the climax part ends.
   */
  public int endTimeMs;

  @CalledByNative
  public ClimaxSegment(int startTimeMs, int endTimeMs) {
    this.startTimeMs = startTimeMs;
    this.endTimeMs = endTimeMs;
  }

  @CalledByNative
  public int getStartTimeMs() {
    return startTimeMs;
  }

  @CalledByNative
  public int getEndTimeMs() {
    return endTimeMs;
  }
}
