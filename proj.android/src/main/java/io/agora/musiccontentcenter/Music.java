package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;
import java.util.Arrays;

/**
 * @brief Detailed information of the music resource.
 */
public class Music {
  /**
   * The identifier of a music resource, used to identify a music resource.
   */
  public long songCode;
  /**
   * Name of the music resource.
   */
  public String name;
  /**
   * Singer name.
   */
  public String singer;
  /**
   * The download URL of the music resource poster.
   */
  public String poster;
  /**
   * The release time of the music resource.
   */
  public String releaseTime;
  /**
   * Music resource types:
   * - 1: A single-track audio source with accompaniment on the left channel and original vocals on the right channel.
   * - 2: A single-track audio source with accompaniment only.
   * - 3: A single-track audio source with original vocals only.
   * - 4: A multi-track audio source.
   */
  public int type;
  /**
   * Whether the song supports the singing scoring feature:
   * - 1: The song supports the singing scoring feature.
   * - 2: The song does not support the singing scoring feature.
   */
  public int pitchType;
  /**
   * Total duration of the music resource (in seconds).
   */
  public int durationS;
  /**
   * Supported lyric types:
   * - 0: xml format.
   * - 1: lrc format.
   */
  public int[] lyricTypes;
  /**
   * The number of mv of the music
   * If this value is greater than zero, it means the current music has MV resource
   */
  public MvProperty[] mvProperties;
  /**
   * List of music climax segments. See `ClimaxSegment` for details.
   */
  public ClimaxSegment[] climaxSegments;

  public Music() {}

  @CalledByNative
  public Music(long songCode, String name, String singer, String poster, String releaseTime,
      int type, int pitchType, int durationS, int[] lyricTypes, MvProperty[] mvProperties,
      ClimaxSegment[] climaxSegments) {
    this.songCode = songCode;
    this.name = name;
    this.singer = singer;
    this.poster = poster;
    this.releaseTime = releaseTime;
    this.type = type;
    this.pitchType = pitchType;
    this.durationS = durationS;
    this.lyricTypes = lyricTypes;
    this.mvProperties = mvProperties;
    this.climaxSegments = climaxSegments;
  }

  @CalledByNative
  public long getSongCode() {
    return songCode;
  }

  @CalledByNative
  public String getName() {
    return name;
  }

  @CalledByNative
  public String getSinger() {
    return singer;
  }

  @CalledByNative
  public String getPoster() {
    return poster;
  }

  @CalledByNative
  public String getReleaseTime() {
    return releaseTime;
  }

  @CalledByNative
  public int getType() {
    return type;
  }

  @CalledByNative
  public int getPitchType() {
    return pitchType;
  }

  @CalledByNative
  public int getDurationS() {
    return durationS;
  }

  @CalledByNative
  public int[] getLyricTypes() {
    return lyricTypes;
  }

  @CalledByNative
  public MvProperty[] getMvProperties() {
    return mvProperties;
  }

  @CalledByNative
  public ClimaxSegment[] getClimaxSegments() {
    return climaxSegments;
  }

  @Override
  public String toString() {
    return "Music{"
        + "songCode=" + songCode + ", name='" + name + '\'' + ", singer='" + singer + '\''
        + ", poster='" + poster + '\'' + ", releaseTime='" + releaseTime + '\'' + ", type=" + type
        + ", pitchType=" + pitchType + ", durationS=" + durationS + ", lyricTypes="
        + Arrays.toString(lyricTypes) + ", mvProperties=" + Arrays.toString(mvProperties)
        + ", climaxSegments=" + Arrays.toString(climaxSegments) + '}';
  }
}
