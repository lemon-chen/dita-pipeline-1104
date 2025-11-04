package io.agora.rtc2;

/**
 * @brief Contains connection information.
 */
public class RtcConnection {
  /**
   * Connection state types.
   */
  public enum CONNECTION_STATE_TYPE {
    /**
     * 0: The SDK has not been initialized.
     */
    CONNECTION_STATE_NOT_INITIALIZED(0),
    /**
     * 1: The SDK is disconnected from Agora's edge server.
     */
    CONNECTION_STATE_DISCONNECTED(1),
    /**
     * 2: The SDK is connecting to Agora's edge server.
     */
    CONNECTION_STATE_CONNECTING(2),
    /**
     * 3: The SDK is connected to Agora's edge server and has joined a channel.
     *  You can now publish or subscribe to a media stream in the channel.
     */
    CONNECTION_STATE_CONNECTED(3),
    /**
     * 4: The SDK keeps rejoining the channel after being disconnected from a
     *  joined channel because of network issues.
     */
    CONNECTION_STATE_RECONNECTING(4),
    /**
     * 5: The SDK fails to connect to Agora's edge server or join the channel.
     */
    CONNECTION_STATE_FAILED(5);

    private int value;
    private CONNECTION_STATE_TYPE(int v) {
      value = v;
    }

    public static int getValue(CONNECTION_STATE_TYPE type) {
      return type.value;
    }
  }

  /**
   * The ID of the local user.
   */
  public int localUid;

  /**
   * The channel name.
   */
  public String channelId;

  public RtcConnection() {
    channelId = "";
    localUid = Constants.DEFAULT_CONNECTION_ID;
  }

  public RtcConnection(String channelId, int uid) {
    this.channelId = channelId;
    this.localUid = uid;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("channelId=").append(channelId);
    sb.append("localUid=").append(localUid);
    return sb.toString();
  }
}
