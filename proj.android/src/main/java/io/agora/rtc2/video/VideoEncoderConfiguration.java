package io.agora.rtc2.video;
/**
 * @brief Video encoder configurations.
 */
public class VideoEncoderConfiguration {
  /**
   * @brief The video dimension.
   */
  static public class VideoDimensions {
    /**
     * The width (pixels) of the video.
     */
    public int width;
    /**
     * The height (pixels) of the video.
     */
    public int height;

    public VideoDimensions(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public VideoDimensions() {
      this.width = 0;
      this.height = 0;
    }
  }
  /**
   * VD_120x120: The video resolution is 120 &times; 120.
   */
  public final static VideoDimensions VD_120x120 = new VideoDimensions(120, 120);
  /**
   * VD_160x120: The video resolution is 160 &times; 120.
   */
  public final static VideoDimensions VD_160x120 = new VideoDimensions(160, 120);
  /**
   * VD_180x180: The video resolution is 180 &times; 180.
   */
  public final static VideoDimensions VD_180x180 = new VideoDimensions(180, 180);
  /**
   * VD_240x180: The video resolution is 240 &times; 180.
   */
  public final static VideoDimensions VD_240x180 = new VideoDimensions(240, 180);
  /**
   * VD_320x180: The video resolution is 320 &times; 180.
   */
  public final static VideoDimensions VD_320x180 = new VideoDimensions(320, 180);
  /**
   * VD_240x240: The video resolution is 240 &times; 240.
   */
  public final static VideoDimensions VD_240x240 = new VideoDimensions(240, 240);
  /**
   * VD_320x240: The video resolution is 320 &times; 240.
   */
  public final static VideoDimensions VD_320x240 = new VideoDimensions(320, 240);
  /**
   * VD_424x240: The video resolution is 424 &times; 240.
   */
  public final static VideoDimensions VD_424x240 = new VideoDimensions(424, 240);
  /**
   * VD_360x360: The video resolution is 360 &times; 360.
   */
  public final static VideoDimensions VD_360x360 = new VideoDimensions(360, 360);
  /**
   * VD_480x360: The video resolution is 480 &times; 360.
   */
  public final static VideoDimensions VD_480x360 = new VideoDimensions(480, 360);
  /**
   * VD_640x360: The video resolution is 640 &times; 360.
   */
  public final static VideoDimensions VD_640x360 = new VideoDimensions(640, 360);
  /**
   * VD_480x480: The video resolution is 480 &times; 480.
   */
  public final static VideoDimensions VD_480x480 = new VideoDimensions(480, 480);
  /**
   * VD_640x480: The video resolution is 640 &times; 480.
   */
  public final static VideoDimensions VD_640x480 = new VideoDimensions(640, 480);
  /**
   * VD_840x480: The video resolution is 840 &times; 480.
   */
  public final static VideoDimensions VD_840x480 = new VideoDimensions(840, 480);
  /**
   * VD_960x540: The video resolution is 960 &times; 540.
   */
  public final static VideoDimensions VD_960x540 = new VideoDimensions(960, 540);
  /**
   * VD_960x720: The video resolution is 640 &times; 360.
   */
  public final static VideoDimensions VD_960x720 = new VideoDimensions(960, 720);
  /**
   * VD_1280x720: The video resolution is 640 &times; 360.
   */
  public final static VideoDimensions VD_1280x720 = new VideoDimensions(1280, 720);
  /**
   * Not supported.
   */
  public final static VideoDimensions VD_1920x1080 = new VideoDimensions(1920, 1080);
  /**
   * Not supported.
   */
  public final static VideoDimensions VD_2540x1440 = new VideoDimensions(2540, 1440);
  /**
   * Not supported.
   */
  public final static VideoDimensions VD_3840x2160 = new VideoDimensions(3840, 2160);

  /**
   * @brief The video frame rate.
   */
  public enum FRAME_RATE {
    /**
     * 1: 1 fps.
     */
    FRAME_RATE_FPS_1(1),
    /**
     * 7: 7 fps.
     */
    FRAME_RATE_FPS_7(7),
    /**
     * 10: 10 fps.
     */
    FRAME_RATE_FPS_10(10),
    /**
     * 15: 15 fps.
     */
    FRAME_RATE_FPS_15(15),
    /**
     * 24: 24 fps.
     */
    FRAME_RATE_FPS_24(24),
    /**
     * 30: 30 fps.
     */
    FRAME_RATE_FPS_30(30),
    /**
     * Not supported.
     */
    FRAME_RATE_FPS_60(60);

    private int value;
    private FRAME_RATE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }
  /**
   * @brief Video output orientation mode.
   */
  public enum ORIENTATION_MODE {
    /**
     * 0: (Default) The output video always follows the orientation of the captured video. The receiver
     * takes the rotational information passed on from the video encoder. This mode applies to scenarios
     * where video orientation can be adjusted on the receiver.
     * - If the captured video is in landscape mode, the output video is in landscape mode.
     * - If the captured video is in portrait mode, the output video is in portrait mode.
     */
    ORIENTATION_MODE_ADAPTIVE(0),
    /**
     * 1: In this mode, the SDK always outputs videos in landscape (horizontal) mode. If the captured
     * video is in portrait mode, the video encoder crops it to fit the output. Applies to situations
     * where the receiving end cannot process the rotational information. For example, CDN live
     * streaming.
     */
    ORIENTATION_MODE_FIXED_LANDSCAPE(1),
    /**
     * 2: In this mode, the SDK always outputs video in portrait (portrait) mode. If the captured video
     * is in landscape mode, the video encoder crops it to fit the output. Applies to situations where
     * the receiving end cannot process the rotational information. For example, CDN live streaming.
     */
    ORIENTATION_MODE_FIXED_PORTRAIT(2);

    private int value;
    private ORIENTATION_MODE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * @brief Video encoder preference.
   */
  public enum ENCODING_PREFERENCE {
    /**
     * -1: Adaptive preference. The SDK automatically selects the optimal encoding type for encoding
     * based on factors such as platform and device type.
     */
    PREFER_AUTO(-1),
    /**
     * 0: Software coding preference. The SDK prefers software encoders for video encoding.
     */
    PREFER_SOFTWARE(0),
    /**
     * 1: Hardware encoding preference. The SDK prefers a hardware encoder for video encoding. When the
     * device does not support hardware encoding, the SDK automatically uses software encoding and
     * reports the currently used video encoder type through `hwEncoderAccelerating` in the
     * `onLocalVideoStats` callback.
     */
    PREFER_HARDWARE(1);

    private int value;

    private ENCODING_PREFERENCE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * @brief Compression preference for video encoding.
   */
  public enum COMPRESSION_PREFERENCE {
    /**
     * -1: (Default) Automatic mode. The SDK will automatically select PREFER_LOW_LATENCY or
     * PREFER_QUALITY based on the video scenario you set to achieve the best user experience.
     */
    PREFER_COMPRESSION_AUTO(-1),
    /**
     * 0: Low latency preference. The SDK compresses video frames to reduce latency. This preference is
     * suitable for scenarios where smoothness is prioritized and reduced video quality is acceptable.
     */
    PREFER_LOW_LATENCY(0),
    /**
     * 1: High quality preference. The SDK compresses video frames while maintaining video quality. This
     * preference is suitable for scenarios where video quality is prioritized.
     */
    PREFER_QUALITY(1);

    private int value;

    private COMPRESSION_PREFERENCE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * @brief Advanced options for video encoding.
   */
  static public class AdvanceOptions {
    /**
     * Video encoder preference. See `ENCODING_PREFERENCE`.
     */
    public ENCODING_PREFERENCE encodingPreference;

    /**
     * Compression preference for video encoding. See `COMPRESSION_PREFERENCE`.
     */
    public COMPRESSION_PREFERENCE compressionPreference;

    /**
     * Whether to encode and send the Alpha data present in the video frame to the remote end:
     * - `true`: Encode and send Alpha data.
     * - `false`: (Default) Do not encode and send Alpha data.
     */
    public boolean encodeAlpha;

    public AdvanceOptions(ENCODING_PREFERENCE encodingPreference,
        COMPRESSION_PREFERENCE compressionPreference, boolean encodeAlpha) {
      this.encodingPreference = encodingPreference;
      this.compressionPreference = compressionPreference;
      this.encodeAlpha = encodeAlpha;
    }

    public AdvanceOptions() {
      this.encodingPreference = ENCODING_PREFERENCE.PREFER_AUTO;
      this.compressionPreference = COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO;
      this.encodeAlpha = false;
    }
  }

  /**
   * @brief Video degradation preferences when the bandwidth is a constraint.
   */
  public enum DEGRADATION_PREFERENCE {
    /**
     * -1: (Default) Automatic mode. The SDK will automatically select MAINTAIN_FRAMERATE,
     * MAINTAIN_BALANCED or MAINTAIN_RESOLUTION based on the video scenario you set, in order to achieve
     * the best overall quality of experience (QoE).
     */
    MAINTAIN_AUTO(-1),
    /**
     * 0: Prefers to reduce the video frame rate while maintaining video resolution during video
     * encoding under limited bandwidth. This degradation preference is suitable for scenarios where
     * video quality is prioritized.
     */
    MAINTAIN_QUALITY(0),
    /**
     * 1: Degrade resolution in order to maintain framerate.
     */
    MAINTAIN_FRAMERATE(1),
    /**
     * 2: Reduces the video frame rate and video resolution simultaneously during video encoding under
     * limited bandwidth. The MAINTAIN_BALANCED has a lower reduction than MAINTAIN_QUALITY and
     * MAINTAIN_FRAMERATE, and this preference is suitable for scenarios where both smoothness and video
     * quality are a priority.
     * @note The resolution of the video sent may change, so remote users need to handle this issue. See
     * `onVideoSizeChanged`.
     */
    MAINTAIN_BALANCED(2),
    /**
     * 3: Reduces the video frame rate while maintaining the video resolution during video encoding
     * under limited bandwidth. This degradation preference is suitable for scenarios where video
     * quality is prioritized.
     */
    MAINTAIN_RESOLUTION(3),
    /**
     * 4: Disabled VQC adjustion.
     */
    DISABLED(100);

    private int value;

    private DEGRADATION_PREFERENCE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * Supported codec type bit mask.
   */
  public enum CODEC_CAP_MASK {
    /**
     * codec cap mask
     */
    CODEC_CAP_MASK_NONE(0),
    /**
     * bit 1: Hardware decoder support flag
     */
    CODEC_CAP_MASK_HW_DEC(1),
    /**
     * bit 2: Hardware encoder support flag
     */
    CODEC_CAP_MASK_HW_ENC(2),
    /**
     * bit 3: Software decoder support flag
     */
    CODEC_CAP_MASK_SW_DEC(4),
    /**
     * bit 4: Software encoder support flag
     */
    CODEC_CAP_MASK_SW_ENC(8);

    private int value;

    private CODEC_CAP_MASK(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }
  ;

  /**
   * Video mirror mode types.
   */
  public enum MIRROR_MODE_TYPE {
    /**
     * (Default) 0: The mirror mode determined by the SDK.
     */
    MIRROR_MODE_AUTO(0),
    /**
     * 1: Enable the mirror mode.
     */
    MIRROR_MODE_ENABLED(1),
    /**
     * 2: Disable the mirror mode.
     */
    MIRROR_MODE_DISABLED(2);

    private int value;

    private MIRROR_MODE_TYPE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * @brief Video codec types.
   */
  public enum VIDEO_CODEC_TYPE {
    /**
     * 0: (Default) Unspecified codec format. The SDK automatically matches the appropriate codec format
     * based on the current video stream's resolution and device performance.
     */
    VIDEO_CODEC_NONE(0),
    /**
     * 1: Standard VP8.
     */
    VIDEO_CODEC_VP8(1),
    /**
     * 2: Standard H.264.
     */
    VIDEO_CODEC_H264(2),
    /**
     * 3: Standard H.265.
     */
    VIDEO_CODEC_H265(3),
    /**
     * 6: Generic. This type is used for transmitting raw video data, such as encrypted video frames.
     * The SDK returns this type of video frames in callbacks, and you need to decode and render the
     * frames yourself.
     */
    VIDEO_CODEC_GENERIC(6),
    /**
     * 12: AV1.
     * @technical preview
     */
    VIDEO_CODEC_AV1(12),
    /**
     * 13: VP9.
     */
    VIDEO_CODEC_VP9(13),
    /**
     * 20: Generic JPEG. This type consumes minimum computing resources and applies to IoT devices.
     */
    VIDEO_CODEC_GENERIC_JPEG(20);

    private int value;

    private VIDEO_CODEC_TYPE(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }
  /**
   * 0: The standard bitrate mode. In this mode, the bitrates under the Live Broadcast and
   * Communication profiles differ:
   * - In the Communication profile, the video bitrate is the same as the base bitrate.
   * - In the Live Broadcast profile, the video bitrate is twice the base bitrate.
   */
  public static final int STANDARD_BITRATE = 0;
  /**
   * -1: The compatible bitrate mode. In this mode, the bitrate stays the same regardless of the
   * profile. If you choose this mode for the Live Broadcast profile, the video frame rate may be
   * lower than the set value.
   */
  public static final int COMPATIBLE_BITRATE = -1;

  /**
   * (For future use) Use the default minimum bitrate.
   */
  public static final int DEFAULT_MIN_BITRATE = -1;

  /**
   * (For future use) The default minimum frame rate.
   */
  public static final int DEFAULT_MIN_FRAMERATE = -1;

  /**
   * -2: (For future use) Set minimum bitrate the same as target bitrate.
   */
  public static final int DEFAULT_MIN_BITRATE_EQUAL_TO_TARGET_BITRATE = -2;

  /**
   * Users can set the resolution by themselves, or directly select the desired resolution from the
   * following list:
   * - VD_120x120: The video resolution is 120 × 120.
   * - VD_160x120: The video resolution is 160 × 120.
   * - VD_180x180: The video resolution is 180 × 180.
   * - VD_240x180: The video resolution is 240 × 180.
   * - VD_320x180: The video resolution is 320 × 180.
   * - VD_240x240: The video resolution is 240 × 240.
   * - VD_320x240: The video resolution is 320 × 240.
   * - VD_424x240: The video resolution is 424 × 240.
   * - VD_360x360: The video resolution is 360 × 360.
   * - VD_480x360: The video resolution is 480 × 360.
   * - VD_640x360: The video resolution is 640 × 360.
   * - VD_480x480: The video resolution is 480 × 480.
   * - VD_640x480: The video resolution is 640 × 480.
   * - VD_840x480: The video resolution is 840 × 480.
   * - VD_960x540: The video resolution is 960 × 540.
   * - VD_960x720: The video resolution is 960 × 720.
   * - VD_1280x720: The video resolution is 1280 × 720.
   * - VD_1920x1080: The video resolution is 1920 × 1080.
   * - VD_2540x1440: The video resolution is 2540 × 1440.
   * - VD_3840x2160: The video resolution is 3840 × 2160.
   * @note
   * - Whether the 720p resolution or above can be supported depends on the device. If the device
   * cannot support 720p, the frame rate will be lower than the set value.
   * - The default value is 960 × 540.
   */
  public VideoDimensions dimensions;
  /**
   * The frame rate (fps) of the encoding video frame. The default value is 15. See `FRAME_RATE`.
   */
  public int frameRate;

  /**
   * (For future use) The minimum video encoder frame rate (fps). The default value is {@link
   * VideoEncoderConfiguration#DEFAULT_MIN_FRAMERATE DEFAULT_MIN_FRAMERATE(-1)}
   * (the SDK uses the lowest encoder frame rate).
   *
   */
  public int minFrameRate;
  /**
   * The encoding bitrate (Kbps) of the video. This parameter does not need to be set; keeping the
   * default value `STANDARD_BITRATE` is sufficient. The SDK automatically matches the most suitable
   * bitrate based on the video resolution and frame rate you have set. For the correspondence between
   * video resolution and frame rate, see `Video profile`.
   * - STANDARD_BITRATE (0): (Recommended) Standard bitrate mode.
   * - COMPATIBLE_BITRATE (-1): Adaptive bitrate mode. In general, Agora suggests that you do not use
   * this value.
   */
  public int bitrate;

  /**
   * The minimum encoding bitrate (Kbps) of the video.
   * The SDK automatically adjusts the encoding bitrate to adapt to the network conditions. Using a
   * value greater than the default value forces the video encoder to output high-quality images but
   * may cause more packet loss and sacrifice the smoothness of the video transmission. Unless you
   * have special requirements for image quality, Agora does not recommend changing this value.
   * @note This parameter only applies to the interactive streaming profile.
   */
  public int minBitrate;
  /**
   * The orientation mode of the encoded video. See `ORIENTATION_MODE`.
   */
  public ORIENTATION_MODE orientationMode;

  /**
   * Video degradation preference under limited bandwidth. See `DEGRADATION_PREFERENCE`.
   * @note When this parameter is set to MAINTAIN_FRAMERATE (1) or MAINTAIN_BALANCED (2),
   * `orientationMode` needs to be set to ORIENTATION_MODE_ADAPTIVE (0) at the same time, otherwise
   * the setting will not take effect.
   */
  public DEGRADATION_PREFERENCE degradationPrefer;

  /**
   * Sets the mirror mode of the published local video stream. It only affects the video that the
   * remote user sees.
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. The SDK
   * disables mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enables the mirror mode for remote users.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disables the mirror mode for remote users.
   * @note By default, the video is not mirrored.
   */
  public MIRROR_MODE_TYPE mirrorMode;

  /**
   * Advanced options for video encoding. See `AdvanceOptions`.
   */
  public AdvanceOptions advanceOptions;

  /**
   * The codec type of the local video stream. See `VIDEO_CODEC_TYPE`.
   */
  public VIDEO_CODEC_TYPE codecType;

  public VideoEncoderConfiguration() {
    this.dimensions = new VideoDimensions(960, 540);
    this.frameRate = FRAME_RATE.FRAME_RATE_FPS_15.getValue();
    this.minFrameRate = DEFAULT_MIN_FRAMERATE;
    this.bitrate = STANDARD_BITRATE;
    this.minBitrate = DEFAULT_MIN_BITRATE;
    this.orientationMode = ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE;
    this.degradationPrefer = DEGRADATION_PREFERENCE.MAINTAIN_AUTO;
    this.mirrorMode = MIRROR_MODE_TYPE.MIRROR_MODE_DISABLED;
    this.advanceOptions = new AdvanceOptions(
        ENCODING_PREFERENCE.PREFER_AUTO, COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO, false);
    this.codecType = VIDEO_CODEC_TYPE.VIDEO_CODEC_NONE;
  }

  public VideoEncoderConfiguration(VideoDimensions dimensions, FRAME_RATE frameRate, int bitrate,
      ORIENTATION_MODE orientationMode) {
    this.dimensions = dimensions;
    this.frameRate = frameRate.getValue();
    this.minFrameRate = DEFAULT_MIN_FRAMERATE;
    this.bitrate = bitrate;
    this.minBitrate = DEFAULT_MIN_BITRATE;
    this.orientationMode = orientationMode;
    this.degradationPrefer = DEGRADATION_PREFERENCE.MAINTAIN_AUTO;
    this.mirrorMode = MIRROR_MODE_TYPE.MIRROR_MODE_DISABLED;
    this.advanceOptions = new AdvanceOptions(
        ENCODING_PREFERENCE.PREFER_AUTO, COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO, false);
    this.codecType = VIDEO_CODEC_TYPE.VIDEO_CODEC_NONE;
  }

  public VideoEncoderConfiguration(VideoDimensions dimensions, FRAME_RATE frameRate, int bitrate,
      ORIENTATION_MODE orientationMode, MIRROR_MODE_TYPE mirrorMode) {
    this.dimensions = dimensions;
    this.frameRate = frameRate.getValue();
    this.minFrameRate = DEFAULT_MIN_FRAMERATE;
    this.bitrate = bitrate;
    this.minBitrate = DEFAULT_MIN_BITRATE;
    this.orientationMode = orientationMode;
    this.degradationPrefer = DEGRADATION_PREFERENCE.MAINTAIN_AUTO;
    this.mirrorMode = mirrorMode;
    this.advanceOptions = new AdvanceOptions(
        ENCODING_PREFERENCE.PREFER_AUTO, COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO, false);
    this.codecType = VIDEO_CODEC_TYPE.VIDEO_CODEC_NONE;
  }

  public VideoEncoderConfiguration(
      int width, int height, FRAME_RATE frameRate, int bitrate, ORIENTATION_MODE orientationMode) {
    this.dimensions = new VideoDimensions(width, height);
    this.frameRate = frameRate.getValue();
    this.minFrameRate = DEFAULT_MIN_FRAMERATE;
    this.bitrate = bitrate;
    this.minBitrate = DEFAULT_MIN_BITRATE;
    this.orientationMode = orientationMode;
    this.degradationPrefer = DEGRADATION_PREFERENCE.MAINTAIN_AUTO;
    this.mirrorMode = MIRROR_MODE_TYPE.MIRROR_MODE_DISABLED;
    this.advanceOptions = new AdvanceOptions(
        ENCODING_PREFERENCE.PREFER_AUTO, COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO, false);
    this.codecType = VIDEO_CODEC_TYPE.VIDEO_CODEC_NONE;
  }

  public VideoEncoderConfiguration(int width, int height, FRAME_RATE frameRate, int bitrate,
      ORIENTATION_MODE orientationMode, MIRROR_MODE_TYPE mirrorMode) {
    this.dimensions = new VideoDimensions(width, height);
    this.frameRate = frameRate.getValue();
    this.minFrameRate = DEFAULT_MIN_FRAMERATE;
    this.bitrate = bitrate;
    this.minBitrate = DEFAULT_MIN_BITRATE;
    this.orientationMode = orientationMode;
    this.degradationPrefer = DEGRADATION_PREFERENCE.MAINTAIN_AUTO;
    this.mirrorMode = mirrorMode;
    this.advanceOptions = new AdvanceOptions(
        ENCODING_PREFERENCE.PREFER_AUTO, COMPRESSION_PREFERENCE.PREFER_COMPRESSION_AUTO, false);
    this.codecType = VIDEO_CODEC_TYPE.VIDEO_CODEC_NONE;
  }
}
