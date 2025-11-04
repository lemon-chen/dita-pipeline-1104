package io.agora.spatialaudio;

import io.agora.rtc2.RtcEngine;

/**
 * @brief The configuration of `ILocalSpatialAudioEngine`.
 */
public class LocalSpatialAudioConfig {
  /**
   * `RtcEngine`.
   */
  public RtcEngine mRtcEngine;
  public LocalSpatialAudioConfig() {
    mRtcEngine = null;
  }
}
