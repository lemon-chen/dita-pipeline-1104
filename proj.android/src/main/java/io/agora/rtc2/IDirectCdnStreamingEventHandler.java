package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @deprecated v4.6.0.
 */

@Deprecated
public interface IDirectCdnStreamingEventHandler {
  /**
   * @brief Occurs when the CDN streaming state changes.
   *
   * @details
   * When the host directly pushes streams to the CDN, if the streaming state changes, the SDK
   * triggers this callback to report the changed streaming state, error codes, and other information.
   * You can troubleshoot issues by referring to this callback.
   *
   * @param state The current CDN streaming state. See `DirectCdnStreamingState`.
   * @param reason Reasons for changes in the status of CDN streaming. See `DirectCdnStreamingReason`.
   * @param message The information about the changed streaming state.
   *
   */
  @CalledByNative
  void onDirectCdnStreamingStateChanged(
      DirectCdnStreamingState state, DirectCdnStreamingReason reason, String message);

  /**
   * @brief Reports the CDN streaming statistics.
   *
   * @details
   * When the host directly pushes media streams to the CDN, the SDK triggers this callback every one
   * second.
   *
   * @param stats The statistics of the current CDN streaming. See `DirectCdnStreamingStats`.
   *
   */
  @CalledByNative void onDirectCdnStreamingStats(DirectCdnStreamingStats stats);
}
