package io.agora.rtc2.video;
/**
 * @brief The color enhancement options.
 *
 * @since v3.6.2
 */
public class ColorEnhanceOptions {
  /**
   * The level of color enhancement. The value range is [0.0, 1.0]. `0.0` is the default value, which
   * means no color enhancement is applied to the video. The higher the value, the higher the level of
   * color enhancement. The default value is `0.5`.
   */
  public float strengthLevel;

  /**
   * The level of skin tone protection. The value range is [0.0, 1.0]. `0.0` means no skin tone
   * protection. The higher the value, the higher the level of skin tone protection. The default value
   * is `1.0`.
   * - When the level of color enhancement is higher, the portrait skin tone can be significantly
   * distorted, so you need to set the level of skin tone protection.
   * - When the level of skin tone protection is higher, the color enhancement effect can be slightly
   * reduced.
   * Therefore, to get the best color enhancement effect, Agora recommends that you adjust
   * `strengthLevel` and `skinProtectLevel` to get the most appropriate values.
   */
  public float skinProtectLevel;

  public ColorEnhanceOptions() {
    strengthLevel = 0.5f;
    skinProtectLevel = 1f;
  }

  public ColorEnhanceOptions(float strength, float skinProtect) {
    strengthLevel = strength;
    skinProtectLevel = skinProtect;
  }
}
