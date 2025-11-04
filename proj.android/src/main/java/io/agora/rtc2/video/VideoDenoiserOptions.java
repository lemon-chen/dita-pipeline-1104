package io.agora.rtc2.video;

/**
 * @brief Video noise reduction options.
 *
 * @since v3.6.2
 */
public class VideoDenoiserOptions {
  /**
   * `0`: (Default) Automatic mode. The SDK automatically enables or disables the video noise
   * reduction feature according to the ambient light.
   *
   */
  public static final int VIDEO_DENOISER_AUTO = 0;

  /**
   * `1`: Manual mode. Users need to enable or disable the video noise reduction feature manually.
   *
   */
  public static final int VIDEO_DENOISER_MANUAL = 1;

  /**
   * `0`: (Default) Promotes video quality during video noise reduction.
   * `HIGH_QUALITY` balances performance consumption and video noise reduction quality. The
   * performance consumption is moderate, the video noise reduction speed is moderate, and the
   * overall video quality is optimal.
   *
   */
  public static final int VIDEO_DENOISER_LEVEL_HIGH_QUALITY = 0;

  /**
   * `1`: Promotes reducing performance consumption during video noise reduction.
   * `FAST` prioritizes reducing performance consumption over video noise reduction quality. The
   * performance consumption is lower, and the video noise reduction speed is faster. To avoid a
   * noticeable shadowing effect (shadows trailing behind moving objects) in the processed video,
   * Agora recommends that you use `FAST` when the camera is fixed.
   *
   */
  public static final int VIDEO_DENOISER_LEVEL_FAST = 1;

  /**
   * Video noise reduction mode.
   * - VIDEO_DENOISER_AUTO (0): (Default) Automatic mode. The SDK automatically enables or disables
   * the video noise reduction feature according to the ambient light.
   * - VIDEO_DENOISER_MANUAL (1): Manual mode. Users need to enable or disable the video noise
   * reduction feature manually.
   */
  public int denoiserMode;

  /**
   * Video noise reduction level.
   * - VIDEO_DENOISER_LEVEL_HIGH_QUALITY (0): (Default) Promotes video quality during low-light
   * enhancement. It processes the brightness, details, and noise of the video image. The performance
   * consumption is moderate, the processing speed is moderate, and the overall video quality is
   * optimal.
   * - VIDEO_DENOISER_LEVEL_FAST (1): Promotes reducing performance consumption during video noise
   * reduction. It prioritizes reducing performance consumption over video noise reduction quality.
   * The performance consumption is lower, and the video noise reduction speed is faster. To avoid a
   * noticeable shadowing effect (shadows trailing behind moving objects) in the processed video,
   * Agora recommends that you use this setting when the camera is fixed.
   */
  public int denoiserLevel;

  public VideoDenoiserOptions() {
    denoiserMode = VIDEO_DENOISER_AUTO;
    denoiserLevel = VIDEO_DENOISER_LEVEL_HIGH_QUALITY;
  }

  public VideoDenoiserOptions(int mode, int level) {
    denoiserMode = mode;
    denoiserLevel = level;
  }
}
