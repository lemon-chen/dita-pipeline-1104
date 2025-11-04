package io.agora.rtc2;

/**
 * @brief The configurations for the data stream.
 *
 * @details
 * The following table shows the SDK behaviors under different parameter settings:
 * | `syncWithAudio` | `ordered` | SDK behaviors                                                     |
 * | --------------- | --------- | ----------------------------------------------------------------- |
 * | `false`         | `false`   | The SDK triggers the `onStreamMessage` callback immediately after the receiver receives a data packet.                                                                                                                                                                                      |
 * | `true`          | `false`   | If the data packet delay is within the audio delay, the SDK triggers the onStreamMessage callback when the synchronized audio packet is played out. If the data packet delay exceeds the audio delay, the SDK triggers the onStreamMessage callback as soon as the data packet is received. |
 * | `false`         | `true`    | If the delay of a data packet is less than five seconds, the SDK corrects the order of the data packet. If the delay of a data packet exceeds five seconds, the SDK discards the data packet.                                                                                               |
 * | `true`          | `true`    | If the delay of the data packet is within the range of the audio delay, the SDK corrects the order of the data packet. If the delay of a data packet exceeds the audio delay, the SDK discards this data packet.                                                                            |
 *
 */
public class DataStreamConfig {
  /**
   * Whether to synchronize the data packet with the published audio packet.
   * - `true`: Synchronize the data packet with the audio packet. This setting is suitable for special
   * scenarios such as lyrics synchronization.
   * - `false`: Do not synchronize the data packet with the audio packet. This setting is suitable for
   * scenarios where data packets need to arrive at the receiving end immediately.
   * When you set the data packet to synchronize with the audio, then if the data packet delay is
   * within the audio delay, the SDK triggers the `onStreamMessage` callback when the synchronized
   * audio packet is played out.
   */
  public boolean syncWithAudio = false;
  /**
   * Whether the SDK guarantees that the receiver receives the data in the sent order.
   * - `true`: Guarantee that the receiver receives the data in the sent order.
   * - `false`: Do not guarantee that the receiver receives the data in the sent order.
   * Do not set this parameter as `true` if you need the receiver to receive the data packet
   * immediately.
   */
  public boolean ordered = false;
}
