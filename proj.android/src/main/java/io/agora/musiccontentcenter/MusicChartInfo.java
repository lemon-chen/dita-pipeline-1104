package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Detailed information about the music chart.
 */
public class MusicChartInfo {
  /**
   * Name of the leaderboard.
   */
  public String name;
  /**
   * The ID of the music chart.
   */
  public int type;

  public MusicChartInfo() {}

  @CalledByNative
  public MusicChartInfo(String name, int type) {
    this.name = name;
    this.type = type;
  }

  @CalledByNative
  public String getName() {
    return name;
  }

  @CalledByNative
  public int getType() {
    return type;
  }

  @Override
  public String toString() {
    return "MusicChartInfo{"
        + "name='" + name + '\'' + ", type=" + type + '}';
  }
}
