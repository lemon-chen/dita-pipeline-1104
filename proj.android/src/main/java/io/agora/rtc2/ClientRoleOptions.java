package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
/**
 * @brief Setting of user role properties.
 *
 * @since v3.4.200.
 */
public class ClientRoleOptions {
  /**
   * The latency level of an audience member in interactive live streaming.
   * - AUDIENCE_LATENCY_LEVEL_LOW_LATENCY (1): Low latency.
   * - AUDIENCE_LATENCY_LEVEL_ULTRA_LOW_LATENCY (2): (Default) Ultra low latency.
   */
  public int audienceLatencyLevel;

  @CalledByNative
  public int getAudienceLatencyLevel() {
    return audienceLatencyLevel;
  }
}
