package io.agora.rtc2;

import java.util.Locale;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The statistics of the current CDN streaming.
 *
 * @deprecated v4.6.0.
 */
@Deprecated
public class DirectCdnStreamingStats {
  /**
   * The width (px) of the video frame.
   */
  public int videoWidth;

  /**
   * The height (px) of the video frame.
   */
  public int videoHeight;

  /**
   * The frame rate (fps) of the current video frame.
   */
  public int fps;

  /**
   * The bitrate (bps) of the current video frame.
   */
  public int videoBitrate;

  /**
   * The bitrate (bps) of the current audio frame.
   */
  public int audioBitrate;

  public DirectCdnStreamingStats() {
    videoWidth = 0;
    videoHeight = 0;
    fps = 0;
    videoBitrate = 0;
    audioBitrate = 0;
  }

  @CalledByNative
  public DirectCdnStreamingStats(
      int videoWidth, int videoHeight, int fps, int videoBitrate, int audioBitrate) {
    this.videoWidth = videoWidth;
    this.videoHeight = videoHeight;
    this.fps = fps;
    this.videoBitrate = videoBitrate;
    this.audioBitrate = audioBitrate;
  }

  @Override
  public String toString() {
    return String.format(Locale.getDefault(),
        "videoWidth=%d videoHeight=%d fps=%d videoBitrate=%d audioBitrate=%d", videoWidth,
        videoHeight, fps, videoBitrate, audioBitrate);
  }
}
