package io.agora.rtc2.video;
import io.agora.base.internal.CalledByNative;

/**
 * @brief MakeUp options. This structure defines options for makeup.
 * @since v4.5.0
 */
public class MakeUpOptions {
  /**
   * @brief Indicates whether makeup is enabled
   *
   * - true: Enable makeup.
   * - false: Disable makeup.
   */
  public boolean mMakeUpEnable;

  /**
   * @brief Type of eyebrow
   */
  public int mBrowType;
  /**
   * @brief Color of eyebrow
   */
  public int mBrowColor;
  /**
   * @brief Strength of eyebrow
   */
  public float mBrowStrength;

  /**
   * @brief Type of eyelash
   */
  public int mLashType;
  /**
   * @brief Color of eyelash
   */
  public int mLashColor;
  /**
   * @brief Strength of eyelash
   */
  public float mLashStrength;

  /**
   * @brief Type of eyeshadow
   */
  public int mShadowType;
  /**
   * @brief Strength of eyeshadow
   */
  public float mShadowStrength;

  /**
   * @brief Type of pupil
   */
  public int mPupilType;
  /**
   * @brief Strength of pupil
   */
  public float mPupilStrength;

  /**
   * @brief Type of face blush
   */
  public int mBlushType;
  /**
   * @brief Color of face blush
   */
  public int mBlushColor;
  /**
   * @brief Strength of face blush
   */
  public float mBlushStrength;

  /**
   * @brief Type of lip makeup
   */
  public int mLipType;
  /**
   * @brief Color of lip makeup
   */
  public int mLipColor;
  /**
   * @brief Strength of lip makeup
   */
  public float mLipStrength;

  /**
   * @brief Type of facial makeup
   * @since v4.6.0
   */
  public int mFacialType;
  /**
   * @brief Strength of facial makeup
   * @since v4.6.0
   */
  public float mFacialStrength;

  /**
   * @brief Type of Wocan makeup
   * @since v4.6.0
   */
  public int mWocanType;
  /**
   * @brief Strength of Wocan makeup
   * @since v4.6.0
   */
  public float mWocanStrength;

  @CalledByNative
  public MakeUpOptions() {
    mMakeUpEnable = false;

    mBrowType = 0;
    mBrowColor = 0;
    mBrowStrength = 0.0f;

    mLashType = 0;
    mLashColor = 0;
    mLashStrength = 0.0f;

    mShadowType = 0;
    mShadowStrength = 0.0f;

    mPupilType = 0;
    mPupilStrength = 0.0f;

    mBlushType = 0;
    mBlushColor = 0;
    mBlushStrength = 0.0f;

    mLipType = 0;
    mLipColor = 0;
    mLipStrength = 0.0f;

    mFacialType = 0;
    mFacialStrength = 0.0f;

    mWocanType = 0;
    mWocanStrength = 0.0f;
  }
}
