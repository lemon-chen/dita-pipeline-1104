package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The audio device information.
 */
public class DeviceInfo {
  /**
   * Whether the audio device supports ultra-low-latency capture and playback:
   * - `true`: The device supports ultra-low-latency capture and playback.
   * - `false`: The device does not support ultra-low-latency capture and playback.
   */
  public boolean isLowLatencyAudioSupported;

  @CalledByNative
  public DeviceInfo(boolean isLowLatencyAudioSupported) {
    this.isLowLatencyAudioSupported = isLowLatencyAudioSupported;
  }
}
