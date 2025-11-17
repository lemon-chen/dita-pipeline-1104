package io.agora.rtc2;

import io.agora.rtc2.video.VideoEncoderConfiguration;
import io.agora.base.internal.CalledByNative;

/**
 * @brief Configure video streams of different quality levels.
 *
 * @technical preview
 */
public class SimulcastConfig {
  /**
   * @brief Index of video streams of different quality levels.
   * @technical preview
   */
  public static enum StreamLayerIndex {
    /**
     * 0: Video stream layer_1, with lower resolution and bitrate than VIDEO_STREAM_HIGH.
     */
    STREAM_LAYER_1(0),
    /**
     * 1: Video stream layer_2, with lower resolution and bitrate than VIDEO_STREAM_LAYER_1.
     */
    STREAM_LAYER_2(1),
    /**
     * 2: Video stream layer_3, with lower resolution and bitrate than VIDEO_STREAM_LAYER_2.
     */
    STREAM_LAYER_3(2),
    /**
     * 3: Video stream layer_4, with lower resolution and bitrate than VIDEO_STREAM_LAYER_3.
     */
    STREAM_LAYER_4(3),
    /**
     * 4: Video stream layer_5, with lower resolution and bitrate than VIDEO_STREAM_LAYER_4.
     */
    STREAM_LAYER_5(4),
    /**
     * 5: Video stream layer_6, with lower resolution and bitrate than VIDEO_STREAM_LAYER_5.
     */
    STREAM_LAYER_6(5),
    /**
     * 6: Low-quality video stream, with the lowest resolution and bitrate.
     */
    STREAM_LOW(6),
    /**
     * 7: Maximum number of video stream layers.
     */
    STREAM_LAYER_COUNT_MAX(7);

    private int value;
    private StreamLayerIndex(int v) {
      this.value = v;
    }

    public int getValue() {
      return this.value;
    }
  }
  /**
   * @brief Configures the parameters of a specific layer in multi-quality video streams.
   *
   * @technical preview
   *
   * @details
   * Used to configure the resolution, frame rate, and enable status of a specific layer in
   * multi-quality video streams.
   *
   */
  public class StreamLayerConfig {
    /**
     * Video frame size. See `VideoEncoderConfiguration.VideoDimensions`.
     */
    public VideoEncoderConfiguration.VideoDimensions dimensions;

    /**
     * Frame rate (fps) of the local video capture. Default is 0.
     */
    public int framerate;

    /**
     * Whether to enable the video stream for the corresponding layer. Default is `false`.
     * - `true`: Enables the video stream for the corresponding layer.
     * - `false`: (Default) Disables the video stream for the corresponding layer.
     */
    public boolean enable;

    public StreamLayerConfig() {
      this.dimensions = new VideoEncoderConfiguration.VideoDimensions(0, 0);
      this.framerate = 0;
      this.enable = false;
    }

    public StreamLayerConfig(
        VideoEncoderConfiguration.VideoDimensions dimensions, int framerate, boolean enable) {
      this.dimensions = dimensions;
      this.framerate = framerate;
      this.enable = enable;
    }

    @CalledByNative("StreamLayerConfig")
    public int getFramerate() {
      return framerate;
    }

    @CalledByNative("StreamLayerConfig")
    public int getDimensionsWidth() {
      return dimensions.width;
    }

    @CalledByNative("StreamLayerConfig")
    public int getDimensionsHeight() {
      return dimensions.height;
    }

    @CalledByNative("StreamLayerConfig")
    public boolean isEnabled() {
      return enable;
    }
  }

  /**
   * Configurations for multi-layer streaming: `StreamLayerConfig`.
   */
  final public StreamLayerConfig[] configs =
      new StreamLayerConfig[StreamLayerIndex.STREAM_LAYER_COUNT_MAX.getValue()];
  public SimulcastConfig() {
    for (int i = 0; i < StreamLayerIndex.STREAM_LAYER_COUNT_MAX.getValue(); i++) {
      configs[i] = new StreamLayerConfig();
    }
    this.publishFallbackEnable = false;
  }

  /**
   * Whether to enable fallback publishing:
   * - `true`: Enable fallback publishing. When the device performance or network is poor at the
   * publishing end, the SDK will dynamically disable multiple video streams of different quality
   * levels, from layer1 to layer6. At least the video streams of the highest and lowest quality are
   * retained to maintain basic video continuity.
   * - `false`: (Default) Disable fallback publishing.
   */
  public boolean publishFallbackEnable;
}
