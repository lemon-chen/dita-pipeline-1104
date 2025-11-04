package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Filter effect options.
 *
 * @since v4.4.0
 */
public class FaceShapeAreaOptions {
  /**
   * -1: (Default) Invalid area.
   */
  public static final int FACE_SHAPE_AREA_NONE = -1;

  /**
   * 100: Head Scale, reduces the size of the head.
   * The value range is [0, 100]. The default value is 50.
   * The larger the value, the stronger the head reduction effect.
   */
  public static final int FACE_SHAPE_AREA_HEADSCALE = 100;

  /**
   * 101: Forehead, adjusts the size of the forehead.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the forehead effect.
   */
  public static final int FACE_SHAPE_AREA_FOREHEAD = 101;

  /**
   * 102: Face Contour, slims the facial contour.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the facial contour reduction effect.
   */
  public static final int FACE_SHAPE_AREA_FACECONTOUR = 102;

  /**
   * 103: Face Length, adjusts the length of the face.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the face length effect, negative values indicate
   * the opposite direction.
   */
  public static final int FACE_SHAPE_AREA_FACELENGTH = 103;

  /**
   * 104: Face Width, narrows the width of the face.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the face width reduction effect.
   */
  public static final int FACE_SHAPE_AREA_FACEWIDTH = 104;

  /**
   * 105: Cheekbone, adjusts the size of the cheekbone.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the cheekbone effect.
   */
  public static final int FACE_SHAPE_AREA_CHEEKBONE = 105;

  /**
   * 106: Cheek, adjusts the size of the cheek.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the cheek effect.
   */
  public static final int FACE_SHAPE_AREA_CHEEK = 106;

  /**
   * 107: Mandible, slims the mandible.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the mandible effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_MANDIBLE = 107;

  /**
   * 108: Chin, adjusts the length of the chin.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the chin effect, negative values indicate the
   * opposite direction.
   */
  public static final int FACE_SHAPE_AREA_CHIN = 108;

  /**
   * 200: Eye Scale, adjusts the size of the eyes.
   * The value range is [0, 100]. The default value is 50.
   * The larger the value, the stronger the eye size effect.
   */
  public static final int FACE_SHAPE_AREA_EYESCALE = 200;

  /**
   * 201: Eye Distance, adjusts the distance between the two eyes.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the eye distance effect, negative values indicate
   * the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEDISTANCE = 201;

  /**
   * 202: Eye Position, adjusts the upper and lower position of the eyes.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the eye position effect, negative values indicate
   * the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEPOSITION = 202;

  /**
   * 203: Lower Eyelid, adjusts the downward position of the eyelids.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the lower eyelid effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYELID = 203;

  /**
   * 204: Eye Pupils, adjusts the size of the eye pupils.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the eye pupils effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEPUPILS = 204;

  /**
   * 205: Eye Inner Corner, adjusts the inner corner of the eyes.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the eye inner corner effect, negative values
   * indicate the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEINNERCORNER = 205;

  /**
   * 206: Eye Outer Corner, adjusts the outer corner of the eyes.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the eye outer corner effect, negative values
   * indicate the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEOUTERCORNER = 206;

  /**
   * 300: Nose Length, adjusts the length of the nose.
   * The value range is [-100, 100]. The default value is 0.
   */
  public static final int FACE_SHAPE_AREA_NOSELENGTH = 300;

  /**
   * 301: Nose Width, adjusts the width of the nose.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the nose width effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSEWIDTH = 301;

  /**
   * 302: Nose Wing, adjusts the size of the nose wing.
   * The value range is [0, 100]. The default value is 10.
   * The larger the value, the stronger the nose wing effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSEWING = 302;

  /**
   * 303: Nose Root, adjusts the size of the nose root.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the nose root effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSEROOT = 303;

  /**
   * 304: Nose Bridge, adjusts the size of the nose bridge.
   * The value range is [0, 100]. The default value is 50.
   * The larger the value, the stronger the nose bridge effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSEBRIDGE = 304;

  /**
   * 305: Nose Tip, adjusts the size of the nose tip.
   * The value range is [0, 100]. The default value is 50.
   * The larger the value, the stronger the nose tip effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSETIP = 305;

  /**
   * 306: Nose General, adjusts the overall size of the nose.
   * The value range is [-100, 100]. The default value is 50.
   * The larger the absolute value, the stronger the nose general effect, negative values indicate
   * the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_NOSEGENERAL = 306;

  /**
   * 400: Mouth Scale, adjusts the size of the mouth.
   * The value range is [-100, 100]. The default value is 20.
   * The larger the absolute value, the stronger the mouth size effect, negative values indicate the
   * opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_MOUTHSCALE = 400;

  /**
   * 401: Mouth Position, adjusts the position of the mouth.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the mouth position effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_MOUTHPOSITION = 401;

  /**
   * 402: Mouth Smile, adjusts the smile degree of the mouth.
   * The value range is [0, 100]. The default value is 30.
   * The larger the value, the stronger the mouth smile effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_MOUTHSMILE = 402;

  /**
   * 403: Mouth Lip, adjusts the size of the lips.
   * The value range is [0, 100]. The default value is 0.
   * The larger the value, the stronger the mouth lip effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_MOUTHLIP = 403;

  /**
   * 500: Eyebrow Position, adjusts the position of the eyebrows.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the absolute value, the stronger the eyebrow position effect, negative values
   * indicate the opposite direction.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEBROWPOSITION = 500;

  /**
   * 501: Eyebrow Thickness, adjusts the thickness of the eyebrows.
   * The value range is [-100, 100]. The default value is 0.
   * The larger the value, the stronger the eyebrow thickness effect.
   * @since v4.6.0
   */
  public static final int FACE_SHAPE_AREA_EYEBROWTHICKNESS = 501;

  /**
   * Facial enhancement areas.
   * - FACE_SHAPE_AREA_NONE (-1): (Default) Invalid area, no beauty effect applied.
   * - FACE_SHAPE_AREA_HEADSCALE (100): Head, used to achieve a smaller head effect. The adjustment
   * range is [0,100]; the larger the value, the smaller the head becomes, with a preset value of 100.
   * - FACE_SHAPE_AREA_FOREHEAD (101): Forehead, used to adjust the hairline height. The adjustment
   * strength ranges from [-100,100], with positive values raising and negative values lowering the
   * hairline. The greater the absolute value, the stronger the effect. The default value is 50.
   * - FACE_SHAPE_AREA_FACECONTOUR (102): Face contour, used to achieve a slimmer face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the slimming effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_FACELENGTH (103): Face length, used to achieve a longer face effect. The
   * adjustment range is [-100,100]; positive values elongate the face, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of 0.
   * - FACE_SHAPE_AREA_FACEWIDTH (104): Face width, used to achieve a narrower face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the narrowing effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_CHEEKBONE (105): Cheekbone, used to adjust cheekbone width. The adjustment
   * range is [0,100]; the larger the value, the narrower the cheekbones, with a preset set value of
   * 43.
   * - FACE_SHAPE_AREA_CHEEK (106): Cheeks, used to adjust the cheeks width. The adjustment range is
   * [0,100]; the larger the value, the narrower the cheeks, with a preset value of 50.
   * - FACE_SHAPE_AREA_CHIN (108): Chin, used to adjust the chin length. The adjustment range is
   * [-100,100]; positive values elongate the chin, while negative values shorten it. The larger the
   * value, the stronger the enhancement effect, with a preset value of -20.
   * - FACE_SHAPE_AREA_EYESCALE (200):Eyes, used to achieve a larger eye effect. The adjustment range
   * is [0,100]; the larger the value, the larger the eye size, with a preset value of 50.
   * - FACE_SHAPE_AREA_EYEDISTANCE (201): Eye distance adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEPOSITION (202): Eye position adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_LOWEREYELID (203): Lower eyelid adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEPUPILS (204): Pupil size adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEINNERCORNER (205): Inner eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEOUTERCORNER (206): Outer eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSELENGTH (300): Nose length, used to achieve a longer nose effect. The
   * adjustment range is [-100,100]; positive values elongate the nose, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of -10.
   * - FACE_SHAPE_AREA_NOSEWIDTH (301): Nose width, used to achieve a slimmer nose effect. The
   * adjustment range is [-100,100]; positive values make the nose wider, while negative values make
   * it narrower. The larger the value, the stronger the enhancement effect, with a preset value of
   * 72.
   * - FACE_SHAPE_AREA_NOSEWING (302): Nose wing adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEROOT (303): Nose root adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEBRIDGE (304): Nose bridge adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSETIP (305): Nose tip adjustment. The range is [0, 100], with a default value
   * of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEGENERAL (306): Overall nose adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHSCALE (400): Mouth, used to achieve a larger mouth effect. The adjustment
   * strength ranges from [0,100], with larger values resulting in a larger mouth. The default value
   * is 50.
   * - FACE_SHAPE_AREA_MOUTHPOSITION (401): Mouth position adjustment. The range is [-100, 100], with
   * a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_MOUTHSMILE (402): Mouth smile adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHLIP (403): Lip shape adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEBROWPOSITION (500): Eyebrow position adjustment. The range is [-100, 100],
   * with a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEBROWTHICKNESS (501): Eyebrow thickness adjustment. The range is [0, 100],
   * with a default value of 0. The larger the value, the more noticeable the adjustment.
   */
  public int shapeArea;

  /**
   * The intensity of the enhancement. The definition of enhancement intensity varies according to the
   * different face areas, such as its orientation, range, and preset value. For details, see
   * descriptions in `shapeArea`. .
   */
  public int shapeIntensity;

  /**
   * The face shape area options.
   *
   * @param area The specific facial area to be adjusted, used with {@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#shapeArea shapeArea}: <ul> <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_NONE FACE_SHAPE_AREA_NONE(-1)}:
   * (default) invalid area. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_HEADSCALE
   * FACE_SHAPE_AREA_HEADSCALE(0)}: Head Scale. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_FOREHEAD FACE_SHAPE_AREA_FOREHEAD(1)}:
   * Forehead. <li>{@link io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_FACECONTOUR
   * FACE_SHAPE_AREA_FACECONTOUR(2)}: Face Contour. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_FACELENGTH
   * FACE_SHAPE_AREA_FACELENGTH(3)}: Face Length. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_FACEWIDTH
   * FACE_SHAPE_AREA_FACEWIDTH(4)}: Face Width. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_CHEEKBONE
   * FACE_SHAPE_AREA_CHEEKBONE(5)}: Cheekbone. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_CHEEK FACE_SHAPE_AREA_CHEEK(6)}:
   * Cheek. <li>{@link io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_CHIN
   * FACE_SHAPE_AREA_CHIN(7)}: Chin. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_EYESCALE FACE_SHAPE_AREA_EYESCALE(8)}:
   * Eye Scale. <li>{@link io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_NOSELENGTH
   * FACE_SHAPE_AREA_NOSELENGTH(9)}: Nose Length. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_NOSEWIDTH
   * FACE_SHAPE_AREA_NOSEWIDTH(10)}: Nose Width. <li>{@link
   * io.agora.rtc2.video.FaceShapeAreaOptions#FACE_SHAPE_AREA_MOUTHSCALE
   * FACE_SHAPE_AREA_MOUTHSCALE(11)}: Mouth Scale.
   * </ul>
   * @param intensity The intensity of the pinching effect applied to the specified facial area.
   * For the following area values: #FACE_SHAPE_AREA_FOREHEAD, #FACE_SHAPE_AREA_FACELENGTH,
   * #FACE_SHAPE_AREA_CHIN, #FACE_SHAPE_AREA_NOSELENGTH, #FACE_SHAPE_AREA_NOSEWIDTH,
   * #FACE_SHAPE_AREA_MOUTHSCALE, the value ranges from -100 to 100. The default value is 0. The
   * greater the absolute value, the stronger the intensity applied to the specified facial area,
   * and negative values indicate the opposite direction. For enumeration values other than the
   * above, the value ranges from 0 to 100. The default value is 0. The greater the value, the
   * stronger the intensity applied to the specified facial area.
   */
  @CalledByNative
  public FaceShapeAreaOptions(int area, int intensity) {
    this.shapeArea = area;
    this.shapeIntensity = intensity;
  }

  public FaceShapeAreaOptions() {
    this.shapeArea = FACE_SHAPE_AREA_NONE;
    this.shapeIntensity = 0;
  }
}
