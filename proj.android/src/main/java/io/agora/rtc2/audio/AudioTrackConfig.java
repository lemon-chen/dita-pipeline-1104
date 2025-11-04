package io.agora.rtc2.audio;

/**
 * @brief The configuration of custom audio tracks.
 */
public class AudioTrackConfig {
  /**
   * Whether to enable the local audio-playback device:
   * - `true`: (Default) Enable the local audio-playback device.
   * - `false`: Do not enable the local audio-playback device.
   */
  public boolean enableLocalPlayback;
  /**
   * Whether to enable audio processing module:
   * - `true`: Enable the audio processing module to apply the Automatic Echo Cancellation (AEC),
   * Automatic Noise Suppression (ANS), and Automatic Gain Control (AGC) effects.
   * - `false`: (Default) Do not enable the audio processing module.
   * @note This parameter only takes effect on AUDIO_TRACK_DIRECT in custom audio capturing.
   */
  public boolean enableAudioProcessing;

  public AudioTrackConfig() {
    this.enableLocalPlayback = true;
    this.enableAudioProcessing = false;
  }

  @Override
  public String toString() {
    return "AudioTrackConfig{"
        + "enableLocalPlayback=" + enableLocalPlayback + "enableAudioProcessing"
        + enableAudioProcessing + '}';
  }
}
