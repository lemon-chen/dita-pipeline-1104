package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.video.AgoraMetadata;

/**
 * The definition of IMetadataObserver.
 *
 * @note Implement all the callbacks in this class in the critical thread. We recommend avoiding any
 * time-consuming operation in the critical thread.
 */
public interface IMetadataObserver {
  /**
   * (Not supported) The metadata type is unknown.
   */
  public final static int UNKNOWN_METADATA = -1;
  /**
   * The metadata type is video.
   */
  public final static int VIDEO_METADATA = 0;

  /**
   * @brief Occurs when the SDK requests the maximum size of the metadata.
   *
   * @details
   * After successfully complete the registration by calling `registerMediaMetadataObserver`, the SDK
   * triggers this callback once every video frame is sent. You need to specify the maximum size of
   * the metadata in the return value of this callback.
   *
   * @return
   * The maximum size of the `buffer` of the metadata that you want to use. The highest value is 1024
   * bytes. Ensure that you set the return value.
   */
  @CalledByNative public abstract int getMaxMetadataSize();

  /**
   * @brief Occurs when the SDK is ready to send metadata.
   *
   * @details
   * This callback is triggered when the SDK is ready to send metadata.
   *
   * @note Ensure that the size of the metadata does not exceed the value set in the
   * `getMaxMetadataSize` callback.
   *
   * @param timeStampMs The timestamp.
   * @param sourceType Video data type. See `VideoSourceType`.
   *
   * @return
   * The metadata that you want to send in the format of byte. Ensure that you set the return value.
   */
  @CalledByNative public abstract byte[] onReadyToSendMetadata(long timeStampMs, int sourceType);

  /**
   * @brief Occurs when the local user receives the metadata.
   *
   * @param metadata The metadata received. See `AgoraMetadata`.
   *
   */
  @CalledByNative public abstract void onMetadataReceived(AgoraMetadata metadata);
}
