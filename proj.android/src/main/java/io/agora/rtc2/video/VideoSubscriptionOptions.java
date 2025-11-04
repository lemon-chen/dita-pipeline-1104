package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Video subscription options.
 */
public class VideoSubscriptionOptions {
  public enum REMOTE_VIDEO_STREAM_TYPE {
    REMOTE_VIDEO_STREAM_HIGH,
    REMOTE_VIDEO_STREAM_LOW,
  }

  /**
   * The video stream type that you want to subscribe to. The default value is VIDEO_STREAM_HIGH,
   * indicating that the high-quality video streams are subscribed. See `VideoStreamType`.
   */
  private REMOTE_VIDEO_STREAM_TYPE streamType;

  /**
   * Whether to subscribe to encoded video frames only:
   * - `true`: Subscribe to the encoded video data (structured data) only; the SDK does not decode or
   * render raw video data.
   * - `false`: (Default) Subscribe to both raw video data and encoded video data.
   */
  private Boolean encodedFrameOnly;

  public VideoSubscriptionOptions() {}

  public VideoSubscriptionOptions(REMOTE_VIDEO_STREAM_TYPE streamType, boolean encodedFrameOnly) {
    this.encodedFrameOnly = encodedFrameOnly;
    this.streamType = streamType;
  }

  @CalledByNative
  public int getStreamType() {
    return streamType.ordinal();
  }

  @CalledByNative
  public boolean isEncodedFrameOnly() {
    return encodedFrameOnly;
  }

  public void setEncodedFrameOnly(boolean encodedFrameOnly) {
    this.encodedFrameOnly = encodedFrameOnly;
  }

  public void setStreamType(REMOTE_VIDEO_STREAM_TYPE streamType) {
    this.streamType = streamType;
  }
}
