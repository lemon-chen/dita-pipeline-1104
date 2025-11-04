package io.agora.rtc2.internal;

/**
 * @brief Configurations of the last-mile network test.
 */
public class LastmileProbeConfig {
  /**
   * Sets whether to test the uplink network. Some users, for example, the audience members in a
   * LIVE_BROADCASTING channel, do not need such a test.
   * - `true`: Test the uplink network.
   * - `false`: Do not test the uplink network.
   */
  public boolean probeUplink;
  /**
   * Sets whether to test the downlink network:
   * - `true`: Test the downlink network.
   * - `false`: Do not test the downlink network.
   */
  public boolean probeDownlink;
  /**
   * The expected maximum uplink bitrate (bps) of the local user. The value range is [100000,
   * 5000000]. Agora recommends referring to `setVideoEncoderConfiguration` to set the value.
   */
  public int expectedUplinkBitrate;
  /**
   * The expected maximum downlink bitrate (bps) of the local user. The value range is
   * [100000,5000000].
   */
  public int expectedDownlinkBitrate;

  public LastmileProbeConfig() {}
}
