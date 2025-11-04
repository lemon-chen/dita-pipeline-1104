package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Filter effect options.
 */
public class FilterEffectOptions {
  /**
   * The absolute path to the local cube map texture file, which can be used to customize the filter
   * effect. The specified .cude file should strictly follow the Cube LUT Format Specification;
   * otherwise, the filter options do not take effect. The following is a sample of the .cude file:
   * 
   * ```
   * LUT_3D_SIZE 32
   * 0.0039215689 0 0.0039215682
   * 0.0086021447 0.0037950677 0
   * ...
   * 0.0728652592 0.0039215689 0
   * ```
   * @note
   * - The identifier `LUT_3D_SIZE` on the first line of the cube map file represents the size of the
   * three-dimensional lookup table. The LUT size for filter effect can only be set to 32.
   * - The SDK provides a built-in `built_in_whiten_filter.cube` file. You can pass the absolute path
   * of this file to get the whitening filter effect.
   */
  public String path = null;

  /**
   * The intensity of the filter effect, with a range value of [0.0,1.0], in which 0.0 represents no
   * filter effect. The default value is 0.5. The higher the value, the stronger the filter effect.
   */
  public float strength;

  @CalledByNative
  public FilterEffectOptions(String path, float strength) {
    this.path = path;
    this.strength = strength;
  }

  public FilterEffectOptions() {
    this.path = "";
    this.strength = 0.5f;
  }
}
