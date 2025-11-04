package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The options for leaving a channel.
 */
public class LeaveChannelOptions {
  /**
   * Whether to stop playing and mixing the music file when a user leaves the channel.
   * - `true`: (Default) Stop playing and mixing the music file.
   * - `false`: Do not stop playing and mixing the music file.
   */
  public boolean stopAudioMixing;

  /**
   * Whether to stop playing all audio effects when a user leaves the channel.
   * - `true`: (Default) Stop playing all audio effects.
   * - `false`: Do not stop playing any audio effect.
   */
  public boolean stopAllEffect;

  /**
   * Whether to stop microphone recording when a user leaves the channel.
   * - `true`: (Default) Stop microphone recording.
   * - `false`: Do not stop microphone recording.
   */
  public boolean stopMicrophoneRecording;

  public LeaveChannelOptions() {
    stopAudioMixing = true;
    stopAllEffect = true;
    stopMicrophoneRecording = true;
  }

  @CalledByNative
  public boolean isStopAudioMixing() {
    return stopAudioMixing;
  }

  @CalledByNative
  public boolean isStopAllEffect() {
    return stopAllEffect;
  }

  @CalledByNative
  public boolean isStopMicrophoneRecording() {
    return stopMicrophoneRecording;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("stopAudioMixing=").append(stopAudioMixing);
    sb.append("stopAllEffect=").append(stopAllEffect);
    sb.append("stopMicrophoneRecording=").append(stopMicrophoneRecording);
    return sb.toString();
  }
}
