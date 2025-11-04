package io.agora.rtc2.video;

/**
 * @brief The custom background.
 */
public class VirtualBackgroundSource {
  /**
   * 0: Enable segementation with the captured video frame without replacing the background.
   */
  public static final int BACKGROUND_NONE = 0;
  /**
   * 1: The background is a solid color(eg: Oxffffff).
   */
  public static final int BACKGROUND_COLOR = 1;

  /**
   * 2: Image source in PNG or JPG format.
   */
  public static final int BACKGROUND_IMG = 2;
  /**
   * 3: Background blur. blur your background, not including your body.
   */
  public static final int BACKGROUND_BLUR = 3;
  /**
   * 4: Video source in MP4, AVI, MKV, FLV format.
   */
  public static final int BACKGROUND_VIDEO = 4;

  /**
   * blur degree low, have few blur effect
   */
  public static final int BLUR_DEGREE_LOW = 1;

  /**
   * blur degree medium, blur more than level 1
   */
  public static final int BLUR_DEGREE_MEDIUM = 2;

  /**
   * blur degree high, blur default, hard to find background
   */
  public static final int BLUR_DEGREE_HIGH = 3;

  /**
   * The custom background.
   * - BACKGROUND_NONE (0): Process the background as alpha data without replacement, only separating
   * the portrait and the background. After setting this value, you can call
   * `startLocalVideoTranscoder` to implement the picture-in-picture effect.
   * - BACKGROUND_COLOR (1): (Default) The background image is a solid color.
   * - BACKGROUND_IMG (2): The background image is a file in PNG or JPG format.
   * - BACKGROUND_BLUR (3): The background is a blurred version of the original background.
   * - BACKGROUND_VIDEO (4): The background is a local video in MP4, AVI, MKV, FLV, or other supported
   * formats.
   */
  public int backgroundSourceType;

  /**
   * The type of the custom background image. The color of the custom background image. The format is
   * a hexadecimal integer defined by RGB, without the # sign, such as 0xFFB6C1 for light pink. The
   * default value is 0xFFFFFF, which signifies white. The value range is [0x000000, 0xffffff]. If the
   * value is invalid, the SDK replaces the original background image with a white background image.
   * @note
   * This parameter is only applicable to custom backgrounds of the following types:
   * - BACKGROUND_COLOR: The background image is a solid-colored image of the color passed in by the
   * parameter.
   * - BACKGROUND_IMG: If the image in `source` has a transparent background, the transparent
   * background will be filled with the color passed in by the parameter.
   */
  public int color;

  /**
   * The local absolute path of the custom background image. Supports PNG, JPG, MP4, AVI, MKV, and FLV
   * formats. If the path is invalid, the SDK will use either the original background image or the
   * solid color image specified by `color`.
   * @note This parameter takes effect only when the type of the custom background image is
   * BACKGROUND_IMG or BACKGROUND_VIDEO.
   */
  public String source = null;

  /**
   * The degree of blurring applied to the custom background image.
   * - BLUR_DEGREE_LOW (1): The degree of blurring applied to the custom background image is low. The
   * user can almost see the background clearly.
   * - BLUR_DEGREE_MEDIUM (2): The degree of blurring applied to the custom background image is
   * medium. It is difficult for the user to recognize details in the background.
   * - BLUR_DEGREE_HIGH (3): The degree of blurring applied to the custom background image is high.
   * The user can barely see any distinguishing features in the background.
   * @note This parameter takes effect only when the type of the custom background image is
   * BACKGROUND_BLUR.
   */
  public int blurDegree;

  public VirtualBackgroundSource(
      int backgroundSourceType, int color, String source, int blurDegree) {
    this.backgroundSourceType = backgroundSourceType;
    this.color = color;
    this.source = source;
    this.blurDegree = blurDegree;
  }

  public VirtualBackgroundSource() {
    this.backgroundSourceType = BACKGROUND_COLOR;
    this.color = 0xffffff;
    this.source = "";
    this.blurDegree = BLUR_DEGREE_HIGH;
  }
}
