package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;

/**
 * @brief The spatial audio parameters.
 */
public class SpatialAudioParams {
  /**
   * The azimuth angle of the remote user or media player relative to the local user. The value range
   * is [0,360], and the unit is degrees, The values are as follows:
   * - 0: (Default) 0 degrees, which means directly in front on the horizontal plane.
   * - 90: 90 degrees, which means directly to the left on the horizontal plane.
   * - 180: 180 degrees, which means directly behind on the horizontal plane.
   * - 270: 270 degrees, which means directly to the right on the horizontal plane.
   * - 360: 360 degrees, which means directly in front on the horizontal plane.
   */
  public Double speaker_azimuth;
  /**
   * The elevation angle of the remote user or media player relative to the local user. The value
   * range is [-90,90], and the unit is degrees, The values are as follows:
   * - 0: (Default) 0 degrees, which means that the horizontal plane is not rotated.
   * - -90: -90 degrees, which means that the horizontal plane is rotated 90 degrees downwards.
   * - 90: 90 degrees, which means that the horizontal plane is rotated 90 degrees upwards.
   */
  public Double speaker_elevation;
  /**
   * The distance of the remote user or media player relative to the local user. The value range is
   * [1,50], and the unit is meters. The default value is 1 meter.
   */
  public Double speaker_distance;
  /**
   * The orientation of the remote user or media player relative to the local user. The value range is
   * [0,180], and the unit is degrees, The values are as follows:
   * - 0: (Default) 0 degrees, which means that the sound source and listener face the same direction.
   * - 180: 180 degrees, which means that the sound source and listener face each other.
   */
  public Integer speaker_orientation;
  /**
   * Whether to enable audio blurring:
   * - `true`: Enable audio blurring.
   * - `false`: (Default) Disable audio blurring.
   */
  public Boolean enable_blur;
  /**
   * Whether to enable air absorption, that is, to simulate the sound attenuation effect of sound
   * transmitting in the air; under a certain transmission distance, the attenuation speed of
   * high-frequency sound is fast, and the attenuation speed of low-frequency sound is slow.
   * - `true`: (Default) Enable air absorption. Make sure that the value of `speaker_attenuation` is
   * not `0`; otherwise, this setting does not take effect.
   * - `false`: Disable air absorption.
   */
  public Boolean enable_air_absorb;
  /**
   * The sound attenuation coefficient of the remote user or media player. The value range is [0,1].
   * The values are as follows:
   * - 0: Broadcast mode, where the volume and timbre are not attenuated with distance, and the volume
   * and timbre heard by local users do not change regardless of distance.
   * - (0,0.5): Weak attenuation mode, where the volume and timbre only have a weak attenuation during
   * the propagation, and the sound can travel farther than that in a real environment.
   * `enable_air_absorb` needs to be enabled at the same time.
   * - 0.5: (Default) Simulates the attenuation of the volume in the real environment; the effect is
   * equivalent to not setting the `speaker_attenuation` parameter.
   * - (0.5,1]: Strong attenuation mode, where volume and timbre attenuate rapidly during the
   * propagation. `enable_air_absorb` needs to be enabled at the same time.
   */
  public Double speaker_attenuation;
  /**
   * Whether to enable the Doppler effect: When there is a relative displacement between the sound
   * source and the receiver of the sound source, the tone heard by the receiver changes.
   * - `true`: Enable the Doppler effect.
   * - `false`: (Default) Disable the Doppler effect.
   * @note
   * - This parameter is suitable for scenarios where the sound source is moving at high speed (for
   * example, racing games). It is not recommended for common audio and video interactive scenarios
   * (for example, voice chat, co-streaming, or online KTV).
   * - When this parameter is enabled, Agora recommends that you set a regular period (such as 30 ms),
   * and then call the `updatePlayerPositionInfo`, `updateSelfPosition`, and `updateRemotePosition`
   * methods to continuously update the relative distance between the sound source and the receiver.
   * The following factors can cause the Doppler effect to be unpredictable or the sound to be
   * jittery: the period of updating the distance is too long, the updating period is irregular, or
   * the distance information is lost due to network packet loss or delay.
   */
  public Boolean enable_doppler;

  /**
   * get speaker azimuth
   */
  @CalledByNative
  public Double getSpeakerAzimuth() {
    return speaker_azimuth;
  }

  /**
   * get speaker elevation
   */
  @CalledByNative
  public Double getSpeakerElevation() {
    return speaker_elevation;
  }

  /**
   * get speaker distance
   */
  @CalledByNative
  public Double getSpeakerDistance() {
    return speaker_distance;
  }

  /**
   * get speaker orientation
   */
  @CalledByNative
  public Integer getSpeakerOrientation() {
    return speaker_orientation;
  }

  /**
   * get blur enabled flag
   */
  @CalledByNative
  public Boolean getBlurFlag() {
    return enable_blur;
  }

  /**
   * get air absorb enabled flag
   */
  @CalledByNative
  public Boolean getAirAbsorbFlag() {
    return enable_air_absorb;
  }

  /**
   * get speaker attenuation
   */
  @CalledByNative
  public Double getSpeakerAttenuation() {
    return speaker_attenuation;
  }

  @CalledByNative
  public Boolean getDopplerFlag() {
    return enable_doppler;
  }
}
