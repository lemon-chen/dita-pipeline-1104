package io.agora.rtc2.video;

/**
 * @brief Processing properties for background images.
 */
public class SegmentationProperty {
  /**
   * 1: (Default) AI segmentation algorithm.
   */
  public static final int SEG_MODEL_AI = 1;

  /**
   * 2: Green screen segmentation algorithm.
   */
  public static final int SEG_MODEL_GREEN = 2;

  /**
   * 0: (Default) Automatically recognizes the screen color.
   */
  public static final int SCREEN_COLOR_AUTO = 0;

  /**
   * 1: Green.
   */
  public static final int SCREEN_COLOR_GREEN = 1;

  /**
   * 2: Blue.
   */
  public static final int SCREEN_COLOR_BLUE = 2;

  /**
   * The type of algorithms to user for background processing.
   * - SEG_MODEL_AI (1): (Default) Use the algorithm suitable for all scenarios.
   * - SEG_MODEL_GREEN (2): Use the algorithm designed specifically for scenarios with a green screen
   * background.
   */
  public int modelType;

  /**
   * The accuracy range for recognizing background colors in the image. The value range is [0,1], and
   * the default value is 0.5. The larger the value, the wider the range of identifiable shades of
   * pure color. When the value of this parameter is too large, the edge of the portrait and the pure
   * color in the portrait range are also detected. Agora recommends that you dynamically adjust the
   * value of this parameter according to the actual effect.
   * @note This parameter only takes effect when `modelType` is set to `SEG_MODEL_GREEN`.
   */
  public float greenCapacity;

  /**
   * The screen color:
   * - SCREEN_COLOR_AUTO (0): The SDK automatically selects the screen color.
   * - SCREEN_COLOR_GREEN (1): Green.
   * - SCREEN_COLOR_BLUE (2): Blue.
   */
  public int screenColorType;

  public SegmentationProperty(int modelType, float greenCapacity) {
    this.modelType = modelType;
    this.greenCapacity = greenCapacity;
    this.screenColorType = SCREEN_COLOR_AUTO;
  }

  public SegmentationProperty() {
    this.modelType = SEG_MODEL_AI;
    this.greenCapacity = 0.5f;
    this.screenColorType = SCREEN_COLOR_AUTO;
  }
}
