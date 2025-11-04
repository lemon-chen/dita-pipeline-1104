package io.agora.spatialaudio;

import io.agora.rtc2.RtcConnection;
import io.agora.spatialaudio.internal.LocalSpatialAudioImpl;

public abstract class ILocalSpatialAudioEngine extends IBaseSpatialAudioEngine {
  private static ILocalSpatialAudioEngine mInstance = null;

  /**
   * @brief Creates `ILocalSpatialAudioEngine`.
   *
   * @details
   * Call this method before `initialize`.
   *
   * @return
   * `ILocalSpatialAudioEngine`
   */
  public static synchronized ILocalSpatialAudioEngine create() {
    if (mInstance == null) {
      mInstance = new LocalSpatialAudioImpl();
    }
    return mInstance;
  }

  /**
   * @brief Destroys `ILocalSpatialAudioEngine`.
   *
   * @details
   * This method releases all resources under `ILocalSpatialAudioEngine`. When the user does not need
   * to use the spatial audio effect, you can call this method to release resources for other
   * operations.
   * After calling this method, you can no longer use any of the APIs under
   * `ILocalSpatialAudioEngine`. To use the spatial audio effect again, you need to wait until the
   * `destroy` method execution to complete before calling `initialize` to create a new
   * `ILocalSpatialAudioEngine`.
   *
   * @note Call this method before the `destroy()` method under `RtcEngine`.
   *
   */
  public static synchronized void destroy() {
    if (mInstance == null)
      return;
    mInstance.release();
    mInstance = null;
  }

  /**
   * @brief Initializes `ILocalSpatialAudioEngine`.
   *
   * @note
   * - Call this method after calling `create`.
   * - Before calling other methods of the `ILocalSpatialAudioEngine` class, you need to call this
   * method to initialize `ILocalSpatialAudioEngine`.
   * - The SDK supports creating only one `ILocalSpatialAudioEngine` instance for an app.
   *
   * @param config The configuration of `ILocalSpatialAudioEngine`. See `LocalSpatialAudioConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int initialize(LocalSpatialAudioConfig config);
  /**
   * @brief Updates the spatial position of the specified remote user.
   *
   * @details
   * After successfully calling this method, the SDK calculates the spatial audio parameters based on
   * the relative position of the local and remote user.
   *
   * @note Call this method after the `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` method.
   *
   * @param uid The user ID. This parameter must be the same as the user ID passed in when the user
   * joined the channel.
   * @param posInfo The spatial position of the remote user. See `RemoteVoicePositionInfo`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int updateRemotePosition(int uid, RemoteVoicePositionInfo posInfo);
  public abstract int updateRemotePositionEx(
      int uid, RemoteVoicePositionInfo posInfo, RtcConnection connection);
  /**
   * @brief Removes the spatial position of the specified remote user.
   *
   * @details
   * After successfully calling this method, the local user no longer hears the specified remote user.
   * After leaving the channel, to avoid wasting computing resources, call this method to delete the
   * spatial position information of the specified remote user. Otherwise, the user's spatial position
   * information will be saved continuously. When the number of remote users exceeds the number of
   * audio streams that can be received as set in `setMaxAudioRecvCount`, the system automatically
   * unsubscribes from the audio stream of the user who is furthest away based on relative distance.
   *
   * @param uid The user ID. This parameter must be the same as the user ID passed in when the user
   * joined the channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int removeRemotePosition(int uid);
  public abstract int removeRemotePositionEx(int uid, RtcConnection connection);
  public abstract int clearRemotePositionsEx(RtcConnection connection);
  /**
   * @brief Sets the sound attenuation effect for the specified user.
   *
   * @param uid The user ID. This parameter must be the same as the user ID passed in when the user
   * joined the channel.
   * @param attenuation For the user's sound attenuation coefficient, the value range is [0,1]. The
   * values are as follows:
   * - 0: Broadcast mode, where the volume and timbre are not attenuated with distance, and the volume
   * and timbre heard by local users do not change regardless of distance.
   * - (0,0.5): Weak attenuation mode, that is, the volume and timbre are only weakly attenuated
   * during the propagation process, and the sound can travel farther than the real environment.
   * - 0.5: (Default) simulates the attenuation of the volume in the real environment; the effect is
   * equivalent to not setting the `speaker_attenuation` parameter.
   * - (0.5,1]: Strong attenuation mode, that is, the volume and timbre attenuate rapidly during the
   * propagation process.
   * @param forceSet Whether to force the user's sound attenuation effect:
   * - `true`: Force `attenuation` to set the sound attenuation of the user. At this time, the
   * `attenuation` coefficient of the sound insulation area set in the `audioAttenuation` of the
   * `SpatialAudioZone` does not take effect for the user.
   * - `false`: Do not force `attenuation` to set the user's sound attenuation effect, as shown in the
   * following two cases.
   *   - If the sound source and listener are inside and outside the sound isolation area, the sound
   * attenuation effect is determined by the `audioAttenuation` in `SpatialAudioZone`.
   *   - If the sound source and the listener are in the same sound insulation area or outside the
   * same sound insulation area, the sound attenuation effect is determined by `attenuation` in this
   * method.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteAudioAttenuation(int uid, double attenuation, boolean forceSet);
}
