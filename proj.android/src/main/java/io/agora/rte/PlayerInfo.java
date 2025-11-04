package io.agora.rte;

import io.agora.base.internal.CalledByNative;
import io.agora.rte.Constants;

/**
 * @brief Information about the player and media stream.
 *
 * @since v4.4.0
 */
public class PlayerInfo {
  @CalledByNative
  public PlayerInfo() {
    mNativeHandle = nativeCreatePlayerInfo();
  }

  @Override
  protected void finalize() {
    destroy();
  }

  @CalledByNative
  public long getNativeHandle() {
    return mNativeHandle;
  };

  private void destroy() {
    nativeReleasePlayerInfo(mNativeHandle);
    mNativeHandle = 0;
  }

  /**
   * Current player state.
   */
  public int state() {
    return nativeState(mNativeHandle);
  }

  /**
   * @brief Gets the duration of the current media resource.
   *
   * @since v4.5.1
   *
   * @note This method is only valid when playing local media files or Media Pull.
   *
   * @return
   * If the method call succeeds, returns the duration of the current media resource in milliseconds.
   */
  public long duration() {
    return nativeDuration(mNativeHandle);
  }

  /**
   * @brief Gets the stream count in the current playback source.
   *
   * @details
   * This method is only valid when opening a non-RTE URL.
   *
   * @return
   * If the method call succeeds, returns the stream count.
   */
  public int streamCount() {
    return nativeStreamCount(mNativeHandle);
  }

  /**
   * @brief Determines whether the media resource contains an audio stream.
   *
   * @details
   * Indicates whether the URL source contains an audio stream.
   *
   * @return
   * - `true`: The URL source contains an audio stream.
   * - `false`: The URL source does not contain an audio stream.
   */
  public boolean hasAudio() {
    return nativeHasAudio(mNativeHandle);
  }

  /**
   * @brief Determines whether a video stream is included.
   *
   * @return
   * - `true`: The URL source contains a video stream.
   * - `false`: The URL source does not contain a video stream.
   */
  public boolean hasVideo() {
    return nativeHasVideo(mNativeHandle);
  }

  /**
   * @brief Determines whether the player has stopped receiving the remote audio stream.
   *
   * @since v4.4.0
   *
   * @details
   * This method determines whether the player has stopped receiving the remote audio stream.
   *
   * @return
   * - `true`: The remote audio stream has stopped being received.
   * - `false`: The remote audio stream is still being received.
   */
  public boolean isAudioMuted() {
    return nativeIsAudioMuted(mNativeHandle);
  }

  /**
   * @brief Determines whether the player has stopped receiving remote video streams.
   *
   * @since v4.4.0
   *
   * @note The `IsVideoMuted` API is only effective when an RTE URL is opened.
   *
   * @return
   * - `true`: Stops receiving remote video streams.
   * - `false`: Continues receiving remote video streams.
   */
  public boolean isVideoMuted() {
    return nativeIsVideoMuted(mNativeHandle);
  }

  /**
   * @brief Gets the height of the video resolution.
   *
   * @since v4.5.1
   *
   * @return
   * If the method call succeeds, returns the height of the video resolution in pixels.
   */
  public int videoHeight() {
    return nativeVideoHeight(mNativeHandle);
  }

  /**
   * @brief Gets the width of the video resolution.
   *
   * @since v4.5.1
   *
   * @return
   * If the method call succeeds, returns the width of the video resolution in pixels.
   */
  public int videoWidth() {
    return nativeVideoWidth(mNativeHandle);
  }

  /**
   * @brief Gets the current subscribed video layer.
   *
   * @details
   * This field is only valid when opening an RTE URL.
   *
   * @return
   * - If the method call succeeds, returns an `AbrSubscriptionLayer` object. See `AbrSubscriptionLayer` for details.
   */
  public Constants.AbrSubscriptionLayer abrSubscriptionLayer() {
    return Constants.AbrSubscriptionLayer.fromInt(nativeAbrSubscriptionLayer(mNativeHandle));
  }

  /**
   * @brief Gets the audio sample rate.
   *
   * @since v4.5.1
   *
   * @return
   * If the method call succeeds, returns the audio sample rate in Hz.
   */
  public int audioSampleRate() {
    return nativeAudioSampleRate(mNativeHandle);
  }

  /**
   * Number of audio channels.
   */
  public int audioChannels() {
    return nativeAudioChannels(mNativeHandle);
  }

  /**
   * @brief Gets the number of bits per audio sampling point.
   *
   * @since v4.5.1
   *
   * @note This field is only valid when a non-RTE URL is opened.
   *
   * @return
   * If the method call succeeds, returns the number of bits per audio sampling point, in bits.
   */
  public int audioBitsPerSample() {
    return nativeAudioBitsPerSample(mNativeHandle);
  }

  /**
   * @brief Gets the URL that is currently being played.
   *
   * @return
   * - If the method call succeeds, returns the URL that is currently being played.
   * - If the method call fails, returns an empty string.
   */
  public String currentUrl() {
    return nativeCurrentUrl(mNativeHandle);
  }

  private native long nativeCreatePlayerInfo();
  private native void nativeReleasePlayerInfo(long handle);

  private native int nativeState(long handle);
  private native long nativeDuration(long handle);
  private native int nativeStreamCount(long handle);
  private native boolean nativeHasAudio(long handle);
  private native boolean nativeHasVideo(long handle);
  private native boolean nativeIsAudioMuted(long handle);
  private native boolean nativeIsVideoMuted(long handle);
  private native int nativeVideoHeight(long handle);
  private native int nativeVideoWidth(long handle);
  private native int nativeAbrSubscriptionLayer(long handle);
  private native int nativeAudioSampleRate(long handle);
  private native int nativeAudioChannels(long handle);
  private native int nativeAudioBitsPerSample(long handle);
  private native String nativeCurrentUrl(long handle);

  private long mNativeHandle = 0;
}
