package io.agora.rtc2.audio;

/**
 * @brief The advanced options for audio.
 */
public class AdvancedAudioOptions {
  /**
   * @brief The number of channels for audio preprocessing.
   *
   * @details
   * In scenarios that require enhanced realism, such as concerts, local users might need to capture
   * stereo audio and send stereo signals to remote users. For example, the singer, guitarist, and
   * drummer are standing in different positions on the stage. The audio capture device captures their
   * stereo audio and sends stereo signals to remote users. Remote users can hear the song, guitar,
   * and drum from different directions as if they were at the auditorium.
   * You can set the dual-channel processing to implement stereo audio in this class. Agora recommends
   * the following settings:1. Preprocessing: call `setAdvancedAudioOptions` and set
   * `audioProcessingChannels` to `AGORA_AUDIO_STEREO_PROCESSING` (2) in `AdvancedAudioOptions`.
   * 2. Post-processing: call `setAudioProfile(int profile)` and set `profile` to `MUSIC_STANDARD_STEREO` (3)
   * or `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note The stereo setting only takes effect when the SDK uses the media volume.
   *
   */
  public enum AudioProcessingChannelsEnum {
    /**
     * 1: (Default) Mono.
     */
    AGORA_AUDIO_MONO_PROCESSING(1),
    /**
     * 2: Stereo.
     */
    AGORA_AUDIO_STEREO_PROCESSING(2);
    private int value;
    private AudioProcessingChannelsEnum(int v) {
      value = v;
    }
    public int getValue() {
      return this.value;
    }
  }
  /**
   * The number of channels for audio preprocessing. See `AudioProcessingChannelsEnum`.
   */
  public AudioProcessingChannelsEnum audioProcessingChannels;
  /**
   * @param audioProcessingChannels the option about mono process or stereo process, the range of
   *     value is [1, 2].
   */

  public AdvancedAudioOptions(AudioProcessingChannelsEnum channels) {
    audioProcessingChannels = channels;
  }

  public AdvancedAudioOptions() {
    audioProcessingChannels = AudioProcessingChannelsEnum.AGORA_AUDIO_MONO_PROCESSING;
  }
}
