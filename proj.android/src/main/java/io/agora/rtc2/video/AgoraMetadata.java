package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;
import java.util.Arrays;

/**
 * @brief Media metadata.
 */
public class AgoraMetadata {
  /**
   * The channel name.
   */
  public String channelId;
  /**
   * The user ID.
   * - For the recipient: The ID of the remote user who sent the `AgoraMetadata`.
   * - For the sender: Ignore it.
   */
  public int uid;
  /**
   * The buffer address of the received `AgoraMetadata`.
   */
  public byte[] data;
  /**
   * The timestamp (ms) of when the `AgoraMetadata` is sent.
   */
  public long timeStampMs;

  public AgoraMetadata() {
    channelId = null;
    uid = 0;
    data = null;
    timeStampMs = 0;
  }

  @CalledByNative
  public AgoraMetadata(String channelId, int uid, byte[] data, long timeStampMs) {
    this.channelId = channelId;
    this.uid = uid;
    this.data = data;
    this.timeStampMs = timeStampMs;
  }
}
