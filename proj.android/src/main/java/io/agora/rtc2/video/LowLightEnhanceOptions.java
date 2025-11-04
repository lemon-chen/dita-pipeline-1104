package io.agora.rtc2.video;

/**
 * @brief The low-light enhancement options.
 *
 * @since v3.6.2
 */
public class LowLightEnhanceOptions {
  /**
   * `0`: (Default) Automatic mode. The SDK automatically enables or disables the low-light
   * enhancement feature according to the ambient light to compensate for the lighting level or
   * prevent overexposure, as necessary.
   *
   */
  public static final int LOW_LIGHT_ENHANCE_AUTO = 0;

  /**
   * `1`: Manual mode. Users need to enable or disable the low-light enhancement feature manually.
   *
   */
  public static final int LOW_LIGHT_ENHANCE_MANUAL = 1;

  /**
   * `0`: (Default) Promotes video quality during low-light enhancement.
   * It processes the brightness, details, and noise of the video image.
   * The performance consumption is moderate, the processing speed is moderate, and the overall
   * video quality is optimal.
   *
   */
  public static final int LOW_LIGHT_ENHANCE_LEVEL_HIGH_QUALITY = 0;

  /**
   * `1`: Promotes performance during low-light enhancement. It processes the brightness and details
   * of the video image. The processing speed is faster.
   */
  public static final int LOW_LIGHT_ENHANCE_LEVEL_FAST = 1;

  /**
   * The low-light enhancement mode.
   * - LOW_LIGHT_ENHANCE_AUTO (0): (Default) Automatic mode. The SDK automatically enables or disables
   * the low-light enhancement feature according to the ambient light to compensate for the lighting
   * level or prevent overexposure, as necessary.
   * - LOW_LIGHT_ENHANCE_MANUAL (1): Manual mode. Users need to enable or disable the low-light
   * enhancement feature manually.
   */
  public int lowlightEnhanceMode;

  /**
   * The low-light enhancement level.
   * - LOW_LIGHT_ENHANCE_LEVEL_HIGH_QUALITY (0): (Default) Promotes video quality during low-light
   * enhancement. It processes the brightness, details, and noise of the video image. The performance
   * consumption is moderate, the processing speed is moderate, and the overall video quality is
   * optimal.
   * - LOW_LIGHT_ENHANCE_LEVEL_FAST (1): Promotes performance during low-light enhancement. It
   * processes the brightness and details of the video image. The processing speed is faster.
   */
  public int lowlightEnhanceLevel;

  public LowLightEnhanceOptions() {
    lowlightEnhanceMode = LOW_LIGHT_ENHANCE_AUTO;
    lowlightEnhanceLevel = LOW_LIGHT_ENHANCE_LEVEL_HIGH_QUALITY;
  }

  public LowLightEnhanceOptions(int mode, int level) {
    lowlightEnhanceMode = mode;
    lowlightEnhanceLevel = level;
  }
}
