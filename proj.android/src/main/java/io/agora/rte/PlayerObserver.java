package io.agora.rte;
import io.agora.base.internal.CalledByNative;
import io.agora.rte.Error;
import io.agora.rte.PlayerInfo;

/**
 * Player Observer, an interface for receiving player event callbacks.
 * @since v4.4.0
 */
public class PlayerObserver {
  public PlayerObserver() {
    // Implementation
    mNativeHandle = nativeCreatePlayerObserver();
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
   * @brief Callback for player state changes.
   *
   * @since v4.4.0
   *
   * @details
   * If you need to monitor changes in the player state, you must first call `registerObserver` to register the player observer object.
   * Call timing: This callback is triggered by the SDK when the player state changes, reporting the current and previous states.
   *
   * @param old_state The player state before the change. See `PlayerState` for details.
   * @param new_state The current player state. See `PlayerState` for details. If the current state is `FAILED`, you can get detailed error information through the `error` parameter.
   * @param error The state or error information. See `Error` for details.
   *
   */
  @CalledByNative
  public void onStateChanged(int old_state, int new_state, Error error) {
    // Implementation
  }

  /**
   * @brief Reports the current playback progress of the media resource.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: This callback is triggered once every second during the playback of the media resource.
   *
   * @param curr_time The current playback progress in milliseconds.
   * @param utc_time The current NTP (Network Time Protocol) time in milliseconds.
   *
   */
  @CalledByNative
  public void onPositionChanged(long curr_time, long utc_time) {
    // Implementation
  }

  /**
   * @brief Callback for video resolution change.
   *
   * @since v4.4.0
   *
   * @details
   * If you need to monitor changes in the video resolution of the stream played by the player, you must first call `registerObserver` to register a player observer object.  
   * Call timing: This callback is triggered by the SDK when the resolution of the video stream changes, reporting the current width and height of the video.
   *
   * @param width Width of the video frame (px).
   * @param height Height of the video frame (px).
   *
   */
  @CalledByNative
  public void onResolutionChanged(int width, int height) {
    // Implementation
  }

  /**
   * @brief Player event callback.
   *
   * @since v4.4.0
   *
   * @details
   * If you need to monitor player events, you must first call `registerObserver` to register the player observer object.
   * Call timing: The SDK triggers this callback when a player event changes.
   *
   * @param event Player event. See `PlayerEvent` for details.
   *
   */
  @CalledByNative
  public void onEvent(int event) {
    // Implementation
  }

  /**
   * @brief Callback for received media auxiliary information.
   *
   * @since v4.4.0
   *
   * @details
   * If you need to obtain auxiliary information from the media stream, you must first call `registerObserver` to register a player observer object.
   * Call timing: This callback is triggered after the SDK parses the auxiliary information of the media stream being played by the player, reporting the data type and its specific content.
   *
   * @param type Type of auxiliary information:
   * - 0: SEI (Supplemental Enhancement Information) type.
   * @param data Parsed auxiliary information.
   *
   */
  @CalledByNative
  public void onMetadata(int type, byte[] data) {
    // Implementation
  }

  /**
   * @brief Callback triggered when the player and media stream information changes.
   *
   * @since v4.4.0
   *
   * @details
   * If you need to obtain information about the player and media stream, you must first call `registerObserver` to register a player observer object. You can also retrieve the information directly using the `getInfo` method.
   * Call timing: This callback is triggered by the SDK when the player and media stream information changes.
   *
   * @param info Information about the player and media stream. See `PlayerInfo` for details.
   *
   */
  @CalledByNative
  public void onPlayerInfoUpdated(PlayerInfo info) {
    // Implementation
  }

  /**
   * @brief Player volume indication callback.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: The SDK triggers this callback when the player's volume changes.
   *
   * @param volume The current volume of the player, with a value range of [0,225].
   *
   */
  @CalledByNative
  public void onAudioVolumeIndication(int volume) {
    // Implementation
  }

  private void destroy() {
    nativeReleasePlayerObserver(mNativeHandle);
    mNativeHandle = 0;
  }

  private native long nativeCreatePlayerObserver();
  private native void nativeReleasePlayerObserver(long handle);

  private long mNativeHandle = 0;
}
