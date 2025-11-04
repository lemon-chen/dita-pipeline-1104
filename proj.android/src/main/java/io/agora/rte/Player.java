package io.agora.rte;
import io.agora.rte.Rte;
import io.agora.rte.PlayerInitialConfig;
import io.agora.rte.PlayerConfig;
import io.agora.rte.PlayerObserver;
import io.agora.rte.callback.AsyncCallback;
import io.agora.rte.callback.PlayerGetStatsCallback;
import io.agora.rte.Stream;
import io.agora.rte.PlayerCustomSourceProvider;
import io.agora.rte.PlayerInfo;
import io.agora.rte.PlayerStats;
import io.agora.rte.exception.RteException;

/**
 * The Player class can be used to play URL resources.
 * @since v4.4.0
 */
public class Player {
  /**
   * @brief Constructs a player object.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: This method must be called after `initMediaEngine`.
   *
   * @param rte An `Rte` object.
   * @param initialConfig Initialization settings for the player object. You can pass a null pointer.
   *
   */
  public Player(Rte rte, PlayerInitialConfig initialConfig) {
    // Implementation
    mNativeHandle = nativeCreatePlayer(rte != null ? rte.getNativeHandle() : 0,
        initialConfig != null ? initialConfig.getNativeHandle() : 0);
  }

  @Override
  protected void finalize() {
    // Implementation
    destroy();
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * @brief Preloads a URL resource.
   *
   * @since v4.4.0
   *
   * @details
   * Successfully preloading a resource accelerates the speed of opening the URL resource with `openWithUrl`. When you need to use these resources, they can be accessed more quickly, reducing wait time.
   * Applicable scenarios: Preloading media resources before opening them can reduce user wait time and provide a smoother audiovisual experience.
   * Call timing: You must call this method before `openWithUrl`.
   *
   * @note This method currently only works for URLs with the `rte://` prefix. You can preload up to 20 URLs. If the limit is exceeded, newly preloaded URLs will replace the earliest ones.
   *
   * @param url A URL with the `rte://` prefix. For details on each field, refer to `Media Pull` on the audience side.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public static void preloadWithUrl(String url) throws RteException {
    // Implementation
    nativePreloadWithUrl(url);
  }

  /**
   * @brief Opens a URL resource.
   *
   * @since v4.4.0
   *
   * @details
   * This method supports opening real-time streaming media via a URL. If you want to speed up the process of opening a URL resource, you can call `preloadWithUrl` to preload the resource before invoking this method.
   * If the resource fails to open, you will receive an `onStateChanged` callback reporting the player state as `FAILED`. In this case, you need to call `stop` first and then call `openWithUrl` again to reopen the URL resource. If you have disabled autoplay, you can call `play` to start playback after the resource is opened.
   * Call timing: This method must be called after `Player`.
   * Related callbacks: After calling this method, the `onStateChanged` callback is triggered, reporting the player state as `OPENING`, indicating that the URL is being opened. Once successfully opened, the player state is reported as `OPEN_COMPLETED`.
   *
   * @note This method currently supports only URLs that start with `rte://`, direct CDN streaming URLs, and local media files.
   *
   * @param url The URL to open. It can be a direct CDN streaming URL, a local media file, or a URL prefixed with `rte://`. For detailed explanations of each field in the RTE URL, see "Audience-side URL playback".
   * @param startTime The start playback position in milliseconds.
   * @param callback An asynchronous callback function used to notify the result of opening the URL resource. If an error occurs during the process, you can get the specific error information through the `onResult` callback under `AsyncCallback`.
   *
   */
  public void openWithUrl(String url, long startTime, AsyncCallback callback) {
    // Implementation
    nativeOpenWithUrl(mNativeHandle, url != null ? url : "", startTime, callback);
  }

  public void openWithCustomSourceProvider(
      PlayerCustomSourceProvider provider, long startTime, AsyncCallback callback) {
    // Implementation
    nativeOpenWithCustomSourceProvider(
        mNativeHandle, provider != null ? provider.getNativeHandle() : 0, startTime, callback);
  }

  public void openWithStream(Stream stream, AsyncCallback callback) {
    // Implementation
    nativeOpenWithStream(mNativeHandle, stream != null ? stream.getNativeHandle() : 0, callback);
  }

  /**
   * @brief Switches to a new URL during playback.
   *
   * @since v4.5.1
   *
   * @details
   * You must call this method when the SDK returns the player state as `OPEN_COMPLETED`.
   *
   * @note This method is only effective when the player opens a non-RTE URL.
   *
   * @param url The URL resource to switch to.
   * @param syncPts - `true`: Synchronizes the playback position.  
   * - `false`: (Default) Does not synchronize the playback position.
   * @param callback Asynchronous callback used to notify the result of the switch operation. See `AsyncCallback` for details.
   *
   */
  public void switchWithUrl(String url, boolean syncPts, AsyncCallback callback) {
    // Implementation
    nativeSwitchWithUrl(mNativeHandle, url != null ? url : "", syncPts, callback);
  }

  /**
   * @brief Retrieves statistics of the currently playing media resource in the player.
   *
   * @since v4.4.0
   *
   * @details
   * This method retrieves player statistics, including decoding and rendering frame rates, audio and video bitrate, etc., and returns the result asynchronously through a callback function.
   * Call timing: This method must be called after `openWithUrl`.
   *
   * @param callback Callback interface used to asynchronously receive statistics and possible error information. See the `onResult` callback under the `PlayerGetStatsCallback` interface class.
   *
   */
  public void getStats(PlayerGetStatsCallback callback) {
    // Implementation
    nativeGetStats(mNativeHandle, callback);
  }

  /**
   * @brief Sets the view window to display video.
   *
   * @since v4.4.0
   *
   * @details
   * This method specifies a `Canvas` object to display video. Once the video stream plays successfully, the video image appears on the specified `Canvas`.
   * Call timing: This method must be called after `Player`.
   *
   * @param canvas The `Canvas` object used to render video frames. See `Canvas` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setCanvas(Canvas canvas) throws RteException {
    // Implementation
    nativeSetCanvas(mNativeHandle, canvas != null ? canvas.getNativeHandle() : 0);
  }

  /**
   * @brief Plays a URL resource.
   *
   * @since v4.4.0
   *
   * @details
   * If you have disabled autoplay, after successfully opening a Media Pull stream using `openWithUrl`, you can call this method to start playback.
   * Call timing: Call this method only after receiving the `onStateChanged` callback with the state `OPEN_COMPLETED`.
   * Related callbacks: After this method is successfully called, the `onStateChanged` callback will be triggered to report the player state.
   *
   * @note This method currently only supports URLs with the `rte://` prefix.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void play() throws RteException {
    // Implementation
    nativePlay(mNativeHandle);
  }

  /**
   * @brief Stops playing the media resource.
   *
   * @since v4.4.0
   *
   * @details
   * After you successfully open a URL stream using `openWithUrl` and start playback with `play`, you can call this method to stop playback. If you want to pause playback instead, call `pause`.
   * If opening the URL stream with `openWithUrl` fails, you need to call this method first before calling `openWithUrl` again to reopen the URL.
   * Call timing: This method must be called after `openWithUrl`.
   * Related callbacks: Upon successful execution, this method triggers the `onStateChanged` callback and reports the player state as `STOPPED`.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void stop() throws RteException {
    // Implementation
    nativeStop(mNativeHandle);
  }

  /**
   * @brief Pauses playback.
   *
   * @since v4.4.0
   *
   * @details
   * After you call `play` to play a URL resource, you can call this method if you want to pause the playback. If you want to stop the playback, call `stop` instead.  
   * Call timing: This method must be called after `play`.
   * Related callbacks: After this method is successfully called, the `onStateChanged` callback is triggered to report the player state as `PAUSED`.
   *
   * @throws RteException If this method call fails, the SDK throws an `RteException` with the corresponding error message. You need to catch and handle the exception.
   */
  public void pause() throws RteException {
    // Implementation
    nativePause(mNativeHandle);
  }

  /**
   * @brief Seeks to a specified playback position.
   *
   * @since v4.5.1
   *
   * @param newTime The playback position to seek to, in milliseconds.
   * 
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.

   *
   */
  public void seek(long newTime) throws RteException {
    // Implementation
    nativeSeek(mNativeHandle, newTime);
  }

  /**
   * @brief  Starts or stops receiving the audio stream.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method after `openWithUrl`.
   * Related callbacks: After this method is successfully called, the `onPlayerInfoUpdated(PlayerInfo info)` callback is triggered to report the latest player and Media Pull information.
   *
   * @param mute Whether to receive the audio stream:
   * - `true`: Do not receive the audio stream.
   * - `false`: Receive the audio stream.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void muteAudio(boolean mute) throws RteException {
    // Implementation
    nativeMuteAudio(mNativeHandle, mute);
  }

  /**
   * @brief Starts or stops receiving remote video streams.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method after `openWithUrl`.
   * Related callbacks: After this method is successfully called, the `onPlayerInfoUpdated(PlayerInfo info)` callback is triggered to report the latest player and media stream information.
   *
   * @param mute Whether to receive remote video streams:
   * - `true`: Do not receive remote video streams.
   * - `false`: Receive remote video streams.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void muteVideo(boolean mute) throws RteException {
    // Implementation
    nativeMuteVideo(mNativeHandle, mute);
  }

  /**
   * @brief Gets the current playback position.
   *
   * @since v4.5.1
   *
   * @details
   * This method retrieves the current playback time position in milliseconds.
   *
   * @return
   * If the method call succeeds, returns the current playback position in milliseconds.
   * 
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public long getPosition() throws RteException {
    // Implementation
    return nativeGetPosition(mNativeHandle);
  }

  /**
   * @brief Retrieves information related to the player and media stream.
   *
   * @since v4.4.0
   *
   * @details
   * You can use this method to obtain information about the player and the media stream, such as audio sample rate and video frame dimensions.
   * Call timing: This method must be called after `Player`.
   *
   * @param info Information related to the player and media stream. See `PlayerInfo` for details.
   *
   * @throws RteException If this method call fails, the SDK throws an `RteException` with the corresponding error message. You need to catch and handle the exception.
   */
  public void getInfo(PlayerInfo info) throws RteException {
    // Implementation
    nativeGetInfo(mNativeHandle, info != null ? info.getNativeHandle() : 0);
  }

  /**
   * @brief Gets the current media player options.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: This method must be called after `Player`.
   *
   * @param config The media player options object. See `PlayerConfig` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void getConfigs(PlayerConfig config) throws RteException {
    // Implementation
    nativeGetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  /**
   * @brief Sets the player configurations.
   *
   * @since v4.4.0
   *
   * @details
   * You can call this method to configure the player, such as enabling autoplay and subscribing to video streams of different resolutions and bitrate.
   * Call timing: This method must be called after `Player`.
   *
   * @param config The player configuration object. See `PlayerConfig` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setConfigs(PlayerConfig config) throws RteException {
    // Implementation
    nativeSetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  /**
   * @brief Registers an observer object for the player.
   *
   * @since v4.4.0
   *
   * @details
   * Before calling this method, you need to implement an interface class that inherits from `PlayerObserver`.
   * Call timing: This method must be called after `Player`.
   *
   * @param observer An instance of the interface object. See `PlayerObserver` for details.
   *
   * @throws RteException If the method call fails, the SDK throws a `RteException` with the corresponding error message. You need to catch and handle the exception.
   */
  public void registerObserver(PlayerObserver observer) throws RteException {
    // Implementation
    nativeRegisterObserver(mNativeHandle, observer != null ? observer.getNativeHandle() : 0);
  }

  /**
   * @brief Unregisters the media player event observer object.
   *
   * @since v4.4.0
   *
   * @details
   * After calling `registerObserver` to register a media player event observer, if you need to unregister it, call this method.
   * Call timing: This method must be called after `registerObserver`.
   *
   * @param observer The interface object instance. See `PlayerObserver` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error message. You need to catch and handle the exception.
   */
  public void unregisterObserver(PlayerObserver observer) throws RteException {
    // Implementation
    nativeUnregisterObserver(mNativeHandle, observer != null ? observer.getNativeHandle() : 0);
  }

  private void destroy() {
    nativeReleasePlayer(mNativeHandle);
    mNativeHandle = 0;
  }

  private native long nativeCreatePlayer(long rteHandle, long initialConfigHandle);
  private native void nativeReleasePlayer(long handle);

  private static native void nativePreloadWithUrl(String url);
  private native void nativeOpenWithUrl(
      long handle, String url, long startTime, AsyncCallback callback);
  private native void nativeOpenWithCustomSourceProvider(
      long handle, long providerHandle, long startTime, AsyncCallback callback);
  private native void nativeOpenWithStream(long handle, long streamHandle, AsyncCallback callback);
  private native void nativeSwitchWithUrl(
      long handle, String url, boolean syncPts, AsyncCallback callback);
  private native void nativeGetStats(long handle, PlayerGetStatsCallback callback);
  private native void nativeSetCanvas(long handle, long canvasHandle);
  private native void nativePlay(long handle);
  private native void nativeStop(long handle);
  private native void nativePause(long handle);
  private native void nativeSeek(long handle, long newTime);
  private native void nativeMuteAudio(long handle, boolean mute);
  private native void nativeMuteVideo(long handle, boolean mute);
  private native long nativeGetPosition(long handle);
  private native void nativeGetInfo(long handle, long infoHandle);
  private native void nativeGetConfigs(long handle, long configHandle);
  private native void nativeSetConfigs(long handle, long configHandle);
  private native void nativeRegisterObserver(long handle, long observerHandle);
  private native void nativeUnregisterObserver(long handle, long observerHandle);

  private long mNativeHandle = 0;
}
