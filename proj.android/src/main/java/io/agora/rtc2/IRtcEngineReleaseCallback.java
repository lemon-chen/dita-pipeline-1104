package io.agora.rtc2;
import io.agora.base.internal.CalledByNative;
// Media Recorder Callback Methods
/**
 * @brief The class used for asynchronously destroying the engine.
 *
 * @since v4.6.0
 */
public interface IRtcEngineReleaseCallback {
  /**
   * @brief Callback triggered when `RtcEngine` is released.
   *
   * @details
   * This callback is triggered when the `destroy()` method is called to asynchronously release
   * the `RtcEngine` object.
   * Call timing: This callback is triggered when the `destroy()` method is called to
   * asynchronously release the `RtcEngine` object.
   *
   */
  @CalledByNative void onEngineReleased();
}
