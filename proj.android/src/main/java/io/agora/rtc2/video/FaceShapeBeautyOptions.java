package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The facial enhancement style options.
 *
 * @since v4.4.0
 */
public class FaceShapeBeautyOptions {
  /**
   * 0: (Default) Female face shape style.
   */
  public static final int FACE_SHAPE_BEAUTY_STYLE_FEMALE = 0;

  /**
   * 1: Male face shape style.
   */
  public static final int FACE_SHAPE_BEAUTY_STYLE_MALE = 1;

  /**
   * 2: Natural face shape style.
   *
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_BEAUTY_STYLE_NATURAL = 2;

  /**
   * Facial enhancement style options:
   * - FACE_SHAPE_BEAUTY_STYLE_FEMALE 0: (Default) Female.
   * - FACE_SHAPE_BEAUTY_STYLE_MALE 1: Male.
   * - FACE_SHAPE_BEAUTY_STYLE_NATURAL (2): The natural style beauty effect only makes minimal
   * adjustments to facial features.
   */
  public int shapeStyle;

  /**
   * The intensity of the facial enhancement style, with a value range oof [0.0,1.0]. The default
   * value is 0.0, which means no face enhancement effect. The higher the value, the more obvious the
   * facial enhancement effect.
   */
  public int styleIntensity;

  /**
   * Face shape beauty options.
   *
   * @param style The face shape style, used with the {@link
   * io.agora.rtc2.video.FaceShapeBeautyOptions#shapeStyle shapeStyle}: <ul> <li>{@link
   * io.agora.rtc2.video.FaceShapeBeautyOptions#FACE_SHAPE_BEAUTY_STYLE_FEMALE
   * FACE_SHAPE_BEAUTY_STYLE_FEMALE(0)}: (default) Female face shape style. <li>{@link
   * io.agora.rtc2.video.FaceShapeBeautyOptions#FACE_SHAPE_BEAUTY_STYLE_MALE
   * FACE_SHAPE_BEAUTY_STYLE_MALE(1)}: Male face shape style.
   * </ul>
   * @param intensity The intensity of the pinching effect applied to the specified facial style.
   *     The value ranges from 0 (original) to 100. The default value is 0.
   * The greater the value, the stronger the intensity applied to face pinching.
   */
  @CalledByNative
  public FaceShapeBeautyOptions(int style, int intensity) {
    this.shapeStyle = style;
    this.styleIntensity = intensity;
  }

  public FaceShapeBeautyOptions() {
    this.shapeStyle = FACE_SHAPE_BEAUTY_STYLE_FEMALE;
    this.styleIntensity = 50;
  }
}
