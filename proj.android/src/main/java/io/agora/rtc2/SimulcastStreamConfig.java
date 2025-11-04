package io.agora.rtc2;

import io.agora.rtc2.video.VideoEncoderConfiguration;
import io.agora.base.internal.CalledByNative;

/**
 * @brief The configuration of the low-quality video stream.
 */
public class SimulcastStreamConfig {
  /**
   * The video dimension. See `VideoDimensions`. The default value is 50% of the high-quality video
   * stream.
   */
  public VideoEncoderConfiguration.VideoDimensions dimensions;
  /**
   * Video bitrate (Kbps). The default value is -1. This parameter does not need to be set. The SDK
   * automatically matches the most suitable bitrate based on the video resolution and frame rate you
   * set.
   */
  public int bitrate;
  /**
   * The frame rate (fps) of the local video. The default value is 5.
   */
  public int framerate;

  public SimulcastStreamConfig() {
    // Currently, use default simulcast config.
    // set all field as -1 for high level api, low level sdk will determine the simulcast
    // config according to major stream
    this.dimensions = new VideoEncoderConfiguration.VideoDimensions(-1, -1);
    this.bitrate = -1;
    this.framerate = 5;
  }

  public SimulcastStreamConfig(
      VideoEncoderConfiguration.VideoDimensions dimensions, int bitrate, int framerate) {
    this.dimensions = dimensions;
    this.bitrate = bitrate;
    this.framerate = framerate;
  }

  @CalledByNative
  public int getBitrate() {
    return bitrate;
  }

  @CalledByNative
  public int getFramerate() {
    return framerate;
  }

  @CalledByNative
  public int getDimensionsWidth() {
    return dimensions.width;
  }

  @CalledByNative
  public int getDimensionsHeight() {
    return dimensions.height;
  }
}
