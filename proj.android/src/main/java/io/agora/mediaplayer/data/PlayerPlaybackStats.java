package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The information of the media file being played.
 */
public class PlayerPlaybackStats {
  @CalledByNative
  public PlayerPlaybackStats() {
    videoFps = 0;
    videoBitrateInKbps = 0;
    audioBitrateInKbps = 0;
    totalBitrateInKbps = 0;
  }

  /**
   * The frame rate (fps) of the video.
   */
  private long videoFps;
  /**
   * The bitrate (kbps) of the video.
   */
  private long videoBitrateInKbps;
  /**
   * The bitrate (kbps) of the audio.
   */
  private long audioBitrateInKbps;
  /**
   * The total bitrate (kbps) of the media stream.
   */
  private long totalBitrateInKbps;

  public long getVideoFps() {
    return videoFps;
  }

  public long getVideoBitrate() {
    return videoBitrateInKbps;
  }

  public long getAudioBitrate() {
    return audioBitrateInKbps;
  }

  public long getTotalBitrate() {
    return totalBitrateInKbps;
  }

  @CalledByNative
  public void setVideoFps(long videoFps) {
    this.videoFps = videoFps;
  }

  @CalledByNative
  public void setVideoBitrate(long videoBitrate) {
    this.videoBitrateInKbps = videoBitrate;
  }

  @CalledByNative
  public void setAudioBitrate(long audioBitrate) {
    this.audioBitrateInKbps = audioBitrate;
  }

  @CalledByNative
  public void setTotalBitrate(long totalBitrate) {
    this.totalBitrateInKbps = totalBitrate;
  }

  @Override
  public String toString() {
    return "PlayerPlaybackStats{"
        + "videoFps=" + videoFps + ", videoBitrate=" + videoBitrateInKbps
        + ", audioBitrate=" + audioBitrateInKbps + ", totalBitrate=" + totalBitrateInKbps + '}';
  }
}
