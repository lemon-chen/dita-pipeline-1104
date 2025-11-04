package io.agora.rtc2;

/**
 * @brief Information about the audio and video streams to be recorded.
 */
public class RecorderStreamInfo {
  /**
   * The user ID of the remote user to be recorded.
   */
  public int uid;

  /**
   * The name of the channel to be recorded.
   */
  public String channelId;
  /**
   * Type of video stream to record:
   * - 0: Remote video streams within the channel.
   * - 1: Local preview video stream before joining the channel.
   */
  public int recorderStreamType;

  public RecorderStreamInfo() {
    uid = 0;
    channelId = null;
    recorderStreamType = 0;
  }

  public RecorderStreamInfo(String channelId, int uid, int recorderStreamType) {
    this.uid = uid;
    this.channelId = channelId;
    this.recorderStreamType = recorderStreamType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("channelId=").append(channelId);
    return sb.toString();
  }
}
