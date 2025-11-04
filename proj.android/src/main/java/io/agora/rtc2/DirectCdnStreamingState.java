package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The current CDN streaming state.
 *
 * @deprecated v4.6.0.
 */
@Deprecated
public enum DirectCdnStreamingState {
  /**
   * 0: The initial state before the CDN streaming starts.
   */
  IDLE(0),
  /**
   * 1: Streams are being pushed to the CDN. The SDK returns this value when you call the
   * `startDirectCdnStreaming` method to push streams to the CDN.
   */
  RUNNING(1),
  /**
   * 2: Stops pushing streams to the CDN. The SDK returns this value when you call the
   * `stopDirectCdnStreaming` method to stop pushing streams to the CDN.
   */
  STOPPED(2),
  /**
   * 3: Fails to push streams to the CDN. You can troubleshoot the issue with the information reported
   * by the `onDirectCdnStreamingStateChanged` callback, and then push streams to the CDN again.
   */
  FAILED(3),
  /**
   * 4: Tries to reconnect the Agora server to the CDN. The SDK attempts to reconnect a maximum of 10
   * times; if the connection is not restored, the streaming state becomes FAILED.
   */
  RECOVERING(4);

  private int value;
  private DirectCdnStreamingState(int v) {
    value = v;
  }

  public int getValue() {
    return this.value;
  }

  @CalledByNative
  public static DirectCdnStreamingState fromInt(int v) {
    for (DirectCdnStreamingState type : values()) {
      if (type.getValue() == v) {
        return type;
      }
    }
    return FAILED;
  }
}
