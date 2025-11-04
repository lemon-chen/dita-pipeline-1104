package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;
import java.nio.ByteBuffer;

/**
 * The IVideoEncodedFrameObserver interface.
 */
public interface IVideoEncodedFrameObserver {
  /**
   * @brief Reports that the receiver has received the to-be-decoded video frame sent by the remote
   * end.
   *
   * @details
   * If you call the `setRemoteVideoSubscriptionOptions` method and set `encodedFrameOnly` to `true`,
   * the SDK triggers this callback locally to report the received encoded video frame information.
   *
   * @since 4.6.0
   * @param channelId The channel name.
   * @param remoteUid The user id of the remote user.
   * @param buffer The encoded video image buffer.
   * @param info For the information of the encoded video frame, see `EncodedVideoFrameInfo`.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  boolean onEncodedVideoFrameReceived(String channelId, int remoteUid, ByteBuffer buffer, EncodedVideoFrameInfo info);
}
