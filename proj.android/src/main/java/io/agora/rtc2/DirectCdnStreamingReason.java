package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Reasons for the changes in CDN streaming status.
 *
 * @deprecated v4.6.0.
 */
@Deprecated
public enum DirectCdnStreamingReason {
  /**
   * 0: No error.
   */
  OK(0),
  /**
   * 1: A general error; no specific reason. You can try to push the media stream again.
   */
  FAILED(1),
  /**
   * 2: An error occurs when pushing audio streams. For example, the local audio capture device is not
   * working properly, is occupied by another process, or does not get the permission required.
   */
  AUDIO_PUBLICATION(2),
  /**
   * 3: An error occurs when pushing video streams. For example, the local video capture device is not
   * working properly, is occupied by another process, or does not get the permission required.
   */
  VIDEO_PUBLICATION(3),
  /**
   * 4: Fails to connect to the CDN.
   */
  NET_CONNECT(4),
  /**
   * 5: The URL is already being used. Use a new URL for streaming.
   */
  BAD_NAME(5);

  private int value;
  private DirectCdnStreamingReason(int v) {
    value = v;
  }

  public int getValue() {
    return this.value;
  }

  @CalledByNative
  public static DirectCdnStreamingReason fromInt(int v) {
    for (DirectCdnStreamingReason type : values()) {
      if (type.getValue() == v) {
        return type;
      }
    }
    return FAILED;
  }
}
