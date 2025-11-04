package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Screen sharing configurations.
 *
 * @since v3.7.0
 */
public class ScreenCaptureParameters {
  /**
   * @brief The video configuration for the shared screen stream.
   *
   * @since v3.7.0
   *
   * @details
   * Only available for scenarios where `captureVideo` is `true`.
   *
   */
  public static class VideoCaptureParameters {
    /**
     * The video encoding bitrate (Kbps).
     */
    public int bitrate = 0;
    /**
     * The video encoding frame rate (fps). The default value is 15.
     */
    public int framerate = 15;
    /**
     * The width (px) of the video encoding resolution. The default value is 1280. If the aspect ratio
     * of width to height is different from that of the screen, the SDK adjusts the video encoding
     * resolution according to the following rules (take width × height of 1280 × 720 as an example):
     * - When the width and height of the screen are both lower than those of dimensions, the SDK uses
     * the resolution of the screen for video encoding. For example, if the screen is 640 × 360, the SDK
     * uses 640 × 360 for video encoding.
     * - When either the width or height of the screen is higher than that of dimensions, the SDK uses
     * the maximum values that do not exceed those of dimensions while maintaining the aspect ratio of
     * the screen for video encoding. For example, if the screen is 2000 × 1500, the SDK uses 960 × 720
     * for video encoding.
     * @note
     * - The billing for the screen sharing stream is based on the value of dimensions. When you do not
     * pass in a value, Agora bills you at 1280 × 720; when you pass in a value, Agora bills you at that
     * value.
     * - The value of this parameter does not indicate the orientation mode of the output video. For how
     * to set the video orientation, see `ORIENTATION_MODE`.
     * - Whether the 720p resolution or above can be supported depends on the device. If the device
     * cannot support 720p, the frame rate will be lower than the set value.
     * When setting the encoding resolution in the scenario of sharing documents (
     * SCREEN_SCENARIO_DOCUMENT ), choose one of the following two methods:
     * - If you require the best image quality, it is recommended to set the encoding resolution to be
     * the same as the capture resolution.
     * - If you wish to achieve a relative balance between image quality, bandwidth, and system
     * performance, then:
     *   - When the capture resolution is greater than 1920 × 1080, it is recommended that the encoding
     * resolution is not less than 1920 × 1080.
     *   - When the capture resolution is less than 1920 × 1080, it is recommended that the encoding
     * resolution is not less than 1280 × 720.
     */
    public int width = 1280;
    /**
     * The height (px) of the video encoding resolution. The default value is 720. If the aspect ratio
     * of width to height is different from that of the screen, the SDK adjusts the video encoding
     * resolution according to the following rules (take width × height of 1280 × 720 as an example):
     * - When the width and height of the screen are both lower than those of dimensions, the SDK uses
     * the resolution of the screen for video encoding. For example, if the screen is 640 × 360, the SDK
     * uses 640 × 360 for video encoding.
     * - When either the width or height of the screen is higher than that of dimensions, the SDK uses
     * the maximum values that do not exceed those of dimensions while maintaining the aspect ratio of
     * the screen for video encoding. For example, if the screen is 2000 × 1500, the SDK uses 960 × 720
     * for video encoding.
     * @note
     * - The billing for the screen sharing stream is based on the value of dimensions. When you do not
     * pass in a value, Agora bills you at 1280 × 720; when you pass in a value, Agora bills you at that
     * value.
     * - The value of this parameter does not indicate the orientation mode of the output video. For how
     * to set the video orientation, see `ORIENTATION_MODE`.
     * - Whether the 720p resolution or above can be supported depends on the device. If the device
     * cannot support 720p, the frame rate will be lower than the set value.
     * When setting the encoding resolution in the scenario of sharing documents (
     * SCREEN_SCENARIO_DOCUMENT ), choose one of the following two methods:
     * - If you require the best image quality, it is recommended to set the encoding resolution to be
     * the same as the capture resolution.
     * - If you wish to achieve a relative balance between image quality, bandwidth, and system
     * performance, then:
     *   - When the capture resolution is greater than 1920 × 1080, it is recommended that the encoding
     * resolution is not less than 1920 × 1080.
     *   - When the capture resolution is less than 1920 × 1080, it is recommended that the encoding
     * resolution is not less than 1280 × 720.
     */
    public int height = 720;
    /**
     * The content hint for screen sharing.
     * - SCREEN_CAPTURE_CONTENT_HINT_NONE (0): (Default) No content hint.
     * - SCREEN_CAPTURE_CONTENT_HINT_MOTION (1): Motion-intensive content. Choose this option if you
     * prefer smoothness or when you are sharing a video clip, movie, or video game.
     * - SCREEN_CAPTURE_CONTENT_HINT_DETAILS (2): Motionless content. Choose this option if you prefer
     * sharpness or when you are sharing a picture, PowerPoint slides, or texts.
     */
    public int contentHint = Constants.SCREEN_CAPTURE_CONTENT_HINT_MOTION;

    @CalledByNative("VideoCaptureParameters")
    public int getBitrate() {
      return bitrate;
    }

    @CalledByNative("VideoCaptureParameters")
    public int getFramerate() {
      return framerate;
    }

    @CalledByNative("VideoCaptureParameters")
    public int getWidth() {
      return width;
    }

    @CalledByNative("VideoCaptureParameters")
    public int getHeight() {
      return height;
    }

    @CalledByNative("VideoCaptureParameters")
    public int getContentHint() {
      return contentHint;
    }

    @Override
    public String toString() {
      return "VideoCaptureParameters{"
          + "bitrate=" + bitrate + ", framerate=" + framerate + ", width=" + width
          + ", height=" + height + ", contentHint=" + contentHint + '}';
    }
  }

  /**
   * @brief The audio configuration for the shared screen stream.
   *
   * @since v3.7.0
   *
   * @details
   * Only available where `captureAudio` is `true`.
   *
   */
  public static class AudioCaptureParameters {
    /**
     * Audio sample rate (Hz). The default value is 16000.
     */
    public int sampleRate = 16000;
    /**
     * The number of audio channels. The default value is 2, which means stereo.
     */
    public int channels = 2;
    /**
     * The volume of the captured system audio. The value range is [0, 100]. The default value is 100.
     */
    public int captureSignalVolume = 100;
    /**
     * Whether to capture audio from the current app:
     * - `true`: (Default) Caputre audio from the current app.
     * - `false`Do not capture the audio from the current app:
     * @note This parameter can only set whether to capture audio from the current app and cannot
     * control whether the captured app audio is published to the channel. You need to set it through
     * the `options` parameter when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` or `updateChannelMediaOptions`.
     */
    public boolean allowCaptureCurrentApp = true;

    @CalledByNative("AudioCaptureParameters")
    public int getSampleRate() {
      return sampleRate;
    }

    @CalledByNative("AudioCaptureParameters")
    public int getChannels() {
      return channels;
    }

    @CalledByNative("AudioCaptureParameters")
    public int getCaptureSignalVolume() {
      return captureSignalVolume;
    }

    @Override
    public String toString() {
      return "AudioCaptureParameters{"
          + "sampleRate=" + sampleRate + ", channels=" + channels + ", captureSignalVolume="
          + captureSignalVolume + ", allowCaptureCurrentApp=" + allowCaptureCurrentApp + '}';
    }
  }
  /**
   * Determines whether to capture system audio during screen sharing:
   * - `true`: Capture system audio.
   * - `false`: (Default) Do not capture system audio.
   * @note
   * - Due to system limitations, capturing system audio is only applicable to Android API level 29
   * and later (that is, Android 10 and later).
   * - To improve the success rate of capturing system audio during screen sharing, ensure that you
   * have called the `setAudioScenario` method and set the audio scenario to
   * `AUDIO_SCENARIO_GAME_STREAMING`.
   */
  public boolean captureAudio = false;
  /**
   * The video configuration for the shared screen stream. See {@link VideoCaptureParameters
   * VideoCaptureParameters}.
   */
  public VideoCaptureParameters videoCaptureParameters = new VideoCaptureParameters();
  /**
   * Whether to capture the screen when screen sharing:
   * - `true`: (Default) Capture the screen.
   * - `false`: Do not capture the screen.
   * @note Due to system limitations, the capture screen is only applicable to Android API level 21
   * and above, that is, Android 5 and above.
   */
  public boolean captureVideo = true;
  /**
   * The audio configuration for the shared screen stream. See {@link AudioCaptureParameters
   * AudioCaptureParameters}.
   */
  public AudioCaptureParameters audioCaptureParameters = new AudioCaptureParameters();

  @CalledByNative
  public boolean isCaptureAudio() {
    return captureAudio;
  }

  @CalledByNative
  public VideoCaptureParameters getVideoCaptureParameters() {
    return videoCaptureParameters;
  }

  @CalledByNative
  public boolean isCaptureVideo() {
    return captureVideo;
  }

  @CalledByNative
  public AudioCaptureParameters getAudioCaptureParameters() {
    return audioCaptureParameters;
  }

  @Override
  public String toString() {
    return "ScreenCaptureParameters{"
        + "captureAudio=" + captureAudio + ", videoCaptureParameters=" + videoCaptureParameters
        + ", captureVideo=" + captureVideo + ", audioCaptureParameters=" + audioCaptureParameters
        + '}';
  }
}
