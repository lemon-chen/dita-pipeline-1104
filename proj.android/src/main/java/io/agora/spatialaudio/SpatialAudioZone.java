package io.agora.spatialaudio;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Sound insulation area settings.
 */
public class SpatialAudioZone {
  /**
   * The ID of the sound insulation area.
   */
  public int zoneSetId;
  /**
   * The spatial center point of the sound insulation area. This parameter is an array of length 3,
   * and the three values represent the front, right, and top coordinates in turn.
   */
  public float[] position;
  /**
   * Starting at `position`, the forward unit vector. This parameter is an array of length 3, and the
   * three values represent the front, right, and top coordinates in turn.
   */
  public float[] forward;
  /**
   * Starting at `position`, the right unit vector. This parameter is an array of length 3, and the
   * three values represent the front, right, and top coordinates in turn.
   */
  public float[] right;
  /**
   * Starting at `position`, the up unit vector. This parameter is an array of length 3, and the three
   * values represent the front, right, and top coordinates in turn.
   */
  public float[] up;
  /**
   * The entire sound insulation area is regarded as a cube; this represents the length of the forward
   * side in the unit length of the game engine.
   */
  public float forwardLength;
  /**
   * The entire sound insulation area is regarded as a cube; this represents the length of the right
   * side in the unit length of the game engine.
   */
  public float rightLength;
  /**
   * The entire sound insulation area is regarded as a cube; this represents the length of the up side
   * in the unit length of the game engine.
   */
  public float upLength;
  /**
   * The sound attenuation coefficient when users within the sound insulation area communicate with
   * external users. The value range is [0,1]. The values are as follows:
   * - 0: Broadcast mode, where the volume and timbre are not attenuated with distance, and the volume
   * and timbre heard by local users do not change regardless of distance.
   * - (0,0.5): Weak attenuation mode, that is, the volume and timbre are only weakly attenuated
   * during the propagation process, and the sound can travel farther than the real environment.
   * - 0.5: (Default) simulates the attenuation of the volume in the real environment; the effect is
   * equivalent to not setting the `audioAttenuation` parameter.
   * - (0.5,1]: Strong attenuation mode (default value is 1), that is, the volume and timbre attenuate
   * rapidly during propagation.
   */
  public float audioAttenuation;

  public SpatialAudioZone() {
    zoneSetId = -1;
    position = new float[] {0.0f, 0.0f, 0.0f};
    forward = new float[] {0.0f, 0.0f, 0.0f};
    right = new float[] {0.0f, 0.0f, 0.0f};
    up = new float[] {0.0f, 0.0f, 0.0f};
    forwardLength = 0.0f;
    rightLength = 0.0f;
    upLength = 0.0f;
    audioAttenuation = 0.0f;
  }

  @CalledByNative
  public float[] getPosition() {
    return position;
  }

  @CalledByNative
  public float[] getForward() {
    return forward;
  }

  @CalledByNative
  public float[] getRight() {
    return right;
  }

  @CalledByNative
  public float[] getUp() {
    return up;
  }

  @CalledByNative
  public int getZoneSetId() {
    return zoneSetId;
  }

  @CalledByNative
  public float getForwardLength() {
    return forwardLength;
  }

  @CalledByNative
  public float getRightLength() {
    return rightLength;
  }

  @CalledByNative
  public float getUpLength() {
    return upLength;
  }

  @CalledByNative
  public float getAudioAttenuation() {
    return audioAttenuation;
  }
}
