package io.agora.rte;
import io.agora.rte.exception.RteException;
import io.agora.rte.Constants;

/**
 * @brief This class provides methods related to Media player options.
 *
 * @since v4.4.0
 */
public class PlayerConfig {
  public PlayerConfig() {
    // Implementation
    mNativeHandle = nativeCreatePlayerConfig();
  }

  @Override
  protected void finalize() {
    // Implementation
    nativeReleasePlayerConfig(mNativeHandle);
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * @brief Sets whether to enable autoplay.
   *
   * @since v4.4.0
   *
   * @details
   * You can call this method to set whether to enable autoplay before calling `openWithUrl` to open a media pull stream. If not set, autoplay is enabled by default.  
   * Call timing: This method must be called before `openWithUrl`.
   *
   * @param autoPlay Whether to enable autoplay:
   * - `true`: (Default) Enables autoplay.
   * - `false`: Disables autoplay.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setAutoPlay(boolean autoPlay) throws RteException {
    // Implementation
    nativeSetAutoPlay(mNativeHandle, autoPlay);
  }

  /**
   * @brief Gets the autoplay setting.
   *
   * @since v4.4.0
   *
   * @details
   * You can call this method to retrieve the current autoplay setting of the media player.
   * Call timing: This method must be called after `getConfigs(PlayerConfig config)`.
   *
   * @return
   * Whether the autoplay setting was retrieved successfully:
   * - `true`: Retrieved successfully.
   * - `false`: Failed to retrieve.
   */
  public boolean getAutoPlay() throws RteException {
    // Implementation
    return nativeGetAutoPlay(mNativeHandle);
  }

  /**
   * @brief Sets the playback speed parameter.
   *
   * @since v4.5.1
   *
   * @details
   * You can call this method after calling the `openWithUrl` method.
   *
   * @param speed Playback speed. Valid range is [50, 400].
   *
   */
  public void setPlaybackSpeed(int speed) throws RteException {
    // Implementation
    nativeSetPlaybackSpeed(mNativeHandle, speed);
  }

  /**
   * @brief Gets the playback speed parameter.
   *
   * @since v4.5.1
   *
   * @return
   * If the method call succeeds, returns the value of the playback speed.
   */
  public int getPlaybackSpeed() throws RteException {
    // Implementation
    return nativeGetPlaybackSpeed(mNativeHandle);
  }

  public void setPlayoutAudioTrackIdx(int idx) throws RteException {
    // Implementation
    nativeSetPlayoutAudioTrackIdx(mNativeHandle, idx);
  }

  public int getPlayoutAudioTrackIdx() throws RteException {
    // Implementation
    return nativeGetPlayoutAudioTrackIdx(mNativeHandle);
  }

  public void setPublishAudioTrackIdx(int idx) throws RteException {
    // Implementation
    nativeSetPublishAudioTrackIdx(mNativeHandle, idx);
  }

  public int getPublishAudioTrackIdx() throws RteException {
    // Implementation
    return nativeGetPublishAudioTrackIdx(mNativeHandle);
  }

  public void setAudioTrackIdx(int idx) throws RteException {
    // Implementation
    nativeSetAudioTrackIdx(mNativeHandle, idx);
  }

  public int getAudioTrackIdx() throws RteException {
    // Implementation
    return nativeGetAudioTrackIdx(mNativeHandle);
  }

  public void setSubtitleTrackIdx(int idx) throws RteException {
    // Implementation
    nativeSetSubtitleTrackIdx(mNativeHandle, idx);
  }

  public int getSubtitleTrackIdx() throws RteException {
    // Implementation
    return nativeGetSubtitleTrackIdx(mNativeHandle);
  }

  public void setExternalSubtitleTrackIdx(int idx) throws RteException {
    // Implementation
    nativeSetExternalSubtitleTrackIdx(mNativeHandle, idx);
  }

  public int getExternalSubtitleTrackIdx() throws RteException {
    // Implementation
    return nativeGetExternalSubtitleTrackIdx(mNativeHandle);
  }

  public void setAudioPitch(int audioPitch) throws RteException {
    // Implementation
    nativeSetAudioPitch(mNativeHandle, audioPitch);
  }

  public int getAudioPitch() throws RteException {
    // Implementation
    return nativeGetAudioPitch(mNativeHandle);
  }

  /**
   * @brief Sets the playback volume parameter.
   *
   * @since v4.5.1
   *
   * @details
   * You can use this method to set the playback volume. The valid range is [0, 400].
   *
   * @param volume The volume value of the player. The valid range is [0, 400].
   *
   */
  public void setPlayoutVolume(int volume) throws RteException {
    // Implementation
    nativeSetPlayoutVolume(mNativeHandle, volume);
  }

  /**
   * @brief Gets the playback volume of the media player.
   *
   * @since v4.5.1
   *
   * @return
   * If the method call succeeds, returns the volume level of the media player.
   */
  public int getPlayoutVolume() throws RteException {
    // Implementation
    return nativeGetPlayoutVolume(mNativeHandle);
  }

  public void setAudioPlaybackDelay(int delay) throws RteException {
    // Implementation
    nativeSetAudioPlaybackDelay(mNativeHandle, delay);
  }

  public int getAudioPlaybackDelay() throws RteException {
    // Implementation
    return nativeGetAudioPlaybackDelay(mNativeHandle);
  }

  public void setAudioDualMonoMode(int mode) throws RteException {
    // Implementation
    nativeSetAudioDualMonoMode(mNativeHandle, mode);
  }

  public int getAudioDualMonoMode() throws RteException {
    // Implementation
    return nativeGetAudioDualMonoMode(mNativeHandle);
  }

  public void setPublishVolume(int volume) throws RteException {
    // Implementation
    nativeSetPublishVolume(mNativeHandle, volume);
  }

  public int getPublishVolume() throws RteException {
    // Implementation
    return nativeGetPublishVolume(mNativeHandle);
  }

  /**
   * @brief Sets the number of times the media file loops during playback.
   *
   * @since v4.5.1
   *
   * @param count The number of times to loop the media file:
   * - `1`: Play once.
   * - `2`: Play twice.
   * - `-1`: Loop indefinitely until `stop` is called to stop playback.
   *
   */
  public void setLoopCount(int count) throws RteException {
    // Implementation
    nativeSetLoopCount(mNativeHandle, count);
  }

  /**
   * @brief Gets the loop count parameter of the media file.
   *
   * @since v4.5.1
   *
   * @return
   * Returns the number of times the media file loops when the method call succeeds.
   */
  public int getLoopCount() throws RteException {
    // Implementation
    return nativeGetLoopCount(mNativeHandle);
  }

  /**
   * Set player private parameters. This parameter setting can be done according to actual needs,
   * referring to the suggestions of Agora SA.
   * @since v4.4.0
   * @param jsonParameter JSON formatted string
   * @throws RteException RteException.errorCode() may return the following ErrorCode:
   *  - ErrorCode.INVALID_ARGUMENT: Indicates that the jsonParameter parameter is empty.
   * @return void
   */
  public void setJsonParameter(String jsonParameter) throws RteException {
    // Implementation
    nativeSetJsonParameter(mNativeHandle, jsonParameter);
  }

  /**
   * @brief Gets the configured SDK JSON settings.
   *
   * @since v4.4.0
   *
   * @details
   * After you call `setJsonParameter` to set the JSON configuration, you can call this method to retrieve the configured settings.
   * Call timing: This method must be called after `getConfigs(Config config)`.
   *
   * @return
   * The configured JSON settings.
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public String getJsonParameter() throws RteException {
    // Implementation
    return nativeGetJsonParameter(mNativeHandle);
  }

  /**
   * @brief Sets the quality level of the subscribed media stream.
   *
   * @since v4.4.0
   *
   * @details
   * You can switch between different video quality levels of the subscribed stream based on actual network conditions, corresponding to different resolutions.
   * By default, after calling this method, viewers can only switch between the `HIGH` and `LOW` quality levels of the video stream. If you require more advanced control over video resolution switching, you can contact technical support to enable the ABR (Adaptive Bitrate) feature. Once enabled, you can customize the resolution for each video quality level, allowing viewers to switch between all levels of the multi-layer video stream based on their needs. For detailed implementation, see the `Viewer-side URL Media Pull`.
   * Applicable scenarios: In unstable network environments, the viewer can select an appropriate video quality level based on actual network conditions to ensure smooth viewing experience.
   * Call timing: This method must be called before `setConfigs(PlayerConfig config)`.
   *
   * @note If the ABR feature is not enabled, you can only subscribe to the `HIGH` or `LOW` video quality levels.
   *
   * @param subscriptionLayer The video quality level to subscribe to. See `AbrSubscriptionLayer`.
   *                     When customizing resolutions for `LAYER1` to `LAYER6`, you can refer to the table below, or configure them based on your actual requirements:
   *                         Note: When customizing resolutions, be sure to sort the video quality levels (Layers) in descending order of resolution, and by descending frame rate if resolutions are the same.
   * | Video Quality Layer | Resolution     |
   * | ------------------- | -------------- |
   * | `LAYER1`            | 2160p (4K)     |
   * | `LAYER2`            | 1440p (2K)     |
   * | `LAYER3`            | 1080p (HD)     |
   * | `LAYER4`            | 720p (HD)      |
   * | `LAYER5`            | 540p (SD)      |
   * | `LAYER6`            | 480p (SD)      |
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with corresponding error information. You need to catch and handle the exception.
   */
  public void setAbrSubscriptionLayer(Constants.AbrSubscriptionLayer subscriptionLayer)
      throws RteException {
    // Implementation
    if (subscriptionLayer == null) {
      throw new RteException("subscriptionLayer is null",
          Constants.ErrorCode.getValue(Constants.ErrorCode.INVALID_ARGUMENT));
    }
    nativeSetAbrSubscriptionLayer(
        mNativeHandle, Constants.AbrSubscriptionLayer.getValue(subscriptionLayer));
  }

  /**
   * @brief Gets the quality layer of the subscribed video stream.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You need to call this method after `getConfigs(PlayerConfig config)`.
   *
   * @return
   * The currently set video quality layer. See `AbrSubscriptionLayer` for details.
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public Constants.AbrSubscriptionLayer getAbrSubscriptionLayer() throws RteException {
    // Implementation
    return Constants.AbrSubscriptionLayer.fromInt(nativeGetAbrSubscriptionLayer(mNativeHandle));
  }

  /**
   * @brief Sets the fallback option for subscribed remote video streams.
   *
   * @since v4.4.0
   *
   * @details
   * In suboptimal network conditions, the quality of real-time audio and video communication may degrade. After calling this method, the SDK downgrades the resolution of the video stream to the specified quality layer when network conditions worsen. Each video quality layer corresponds to a specific resolution and bitrate. At the same time, the SDK continuously monitors network conditions and restores the subscribed remote video streams to the original quality layer when the network improves.
   * By default, after calling this method, you can only choose to fall back to `LOW` or `AUDIO_ONLY`. If you require higher video quality, you can contact technical support to enable the ABR feature. Once enabled, you can fall back to any quality layer of the multi-layer video stream and customize the resolution corresponding to each layer.
   * Applicable scenarios:
   * - In general video scenarios, you can call this method and set the fallback quality layer to `LOW` or `AUDIO_ONLY`. The SDK downgrades the video stream to a lower-quality stream or receives only the audio stream when the network is poor.
   * - If you have higher requirements for the resolution of fallback video streams, you can contact technical support to enable the ABR feature. Once enabled, you can customize the resolution of each layer. The SDK dynamically adjusts the resolution within the range starting from the lowest resolution layer you specify, depending on the network conditions. For implementation details, refer to `Audience-side URL streaming`.
   * Call timing: You must call this method before `setConfigs(PlayerConfig config)`.
   *
   * @note If you have not enabled the ABR feature when calling this method, the fallback quality layer of the video stream can only be set to `LOW` or `AUDIO_ONLY`.
   *
   * @param fallbackLayer The fallback quality layer for the video stream. See `AbrFallbackLayer`. When customizing the resolution for `LAYER1` through `LAYER6`, you can refer to the table below or define them based on your actual needs:
   * | Video Quality Layer | Resolution   |
   * | ------------------- | ------------ |
   * | `LAYER1`            | 2160p (4K)   |
   * | `LAYER2`            | 1440p (2K)   |
   * | `LAYER3`            | 1080p (HD)   |
   * | `LAYER4`            | 720p (HD)    |
   * | `LAYER5`            | 540p (SD)    |
   * | `LAYER6`            | 480p (SD)    |
   * Note: When customizing resolutions, make sure to sort the video quality layers from highest to lowest resolution. If the resolutions are the same, sort them by frame rate in descending order.
   *
   * @throws RteException If this method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception accordingly.
   */
  public void setAbrFallbackLayer(Constants.AbrFallbackLayer fallbackLayer) throws RteException {
    // Implementation
    if (fallbackLayer == null) {
      throw new RteException("fallbackLayer is null",
          Constants.ErrorCode.getValue(Constants.ErrorCode.INVALID_ARGUMENT));
    }
    nativeSetAbrFallbackLayer(mNativeHandle, Constants.AbrFallbackLayer.getValue(fallbackLayer));
  }

  /**
   * @brief Gets the configured video stream fallback option.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method after `getConfigs(PlayerConfig config)`.
   *
   * @return
   * The configured video stream fallback option. See `AbrFallbackLayer` for details.
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public Constants.AbrFallbackLayer getAbrFallbackLayer() throws RteException {
    // Implementation
    return Constants.AbrFallbackLayer.fromInt(nativeGetAbrFallbackLayer(mNativeHandle));
  }

  private native long nativeCreatePlayerConfig();
  private native void nativeReleasePlayerConfig(long handle);

  private native void nativeSetAutoPlay(long handle, boolean autoPlay);
  private native boolean nativeGetAutoPlay(long handle);

  private native void nativeSetPlaybackSpeed(long handle, int speed);
  private native int nativeGetPlaybackSpeed(long handle);

  private native void nativeSetPlayoutAudioTrackIdx(long handle, int idx);
  private native int nativeGetPlayoutAudioTrackIdx(long handle);

  private native void nativeSetPublishAudioTrackIdx(long handle, int idx);
  private native int nativeGetPublishAudioTrackIdx(long handle);

  private native void nativeSetAudioTrackIdx(long handle, int idx);
  private native int nativeGetAudioTrackIdx(long handle);

  private native void nativeSetSubtitleTrackIdx(long handle, int idx);
  private native int nativeGetSubtitleTrackIdx(long handle);

  private native void nativeSetExternalSubtitleTrackIdx(long handle, int idx);
  private native int nativeGetExternalSubtitleTrackIdx(long handle);

  private native void nativeSetAudioPitch(long handle, int audioPitch);
  private native int nativeGetAudioPitch(long handle);

  private native void nativeSetPlayoutVolume(long handle, int volume);
  private native int nativeGetPlayoutVolume(long handle);

  private native void nativeSetAudioPlaybackDelay(long handle, int delay);
  private native int nativeGetAudioPlaybackDelay(long handle);

  private native void nativeSetAudioDualMonoMode(long handle, int mode);
  private native int nativeGetAudioDualMonoMode(long handle);

  private native void nativeSetPublishVolume(long handle, int volume);
  private native int nativeGetPublishVolume(long handle);

  private native void nativeSetLoopCount(long handle, int count);
  private native int nativeGetLoopCount(long handle);

  private native void nativeSetJsonParameter(long handle, String jsonParameter);
  private native String nativeGetJsonParameter(long handle);

  private native void nativeSetAbrSubscriptionLayer(long handle, int subscriptionLayer);
  private native int nativeGetAbrSubscriptionLayer(long handle);

  private native void nativeSetAbrFallbackLayer(long handle, int fallbackLayer);
  private native int nativeGetAbrFallbackLayer(long handle);

  private long mNativeHandle = 0;
}
