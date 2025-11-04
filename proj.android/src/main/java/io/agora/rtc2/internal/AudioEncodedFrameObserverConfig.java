package io.agora.rtc2.internal;

import io.agora.rtc2.Constants;

/**
 * @brief Observer settings for the encoded audio.
 */
public class AudioEncodedFrameObserverConfig {
  /**
   * Audio observer position:
   * - AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIC (1): Only encode the audio of the local user.
   * - AUDIO_ENCODED_FRAME_OBSERVER_POSITION_PLAYBACK (2): Only encode the audio of all remote users.
   * - AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIXED (3): Records the mixed audio of the local and all
   * remote users.
   */
  public int postionType;
  /**
   * Audio encoding type:
   * - AUDIO_ENCODING_TYPE_AAC_16000_LOW: AAC encoding format, 16000 Hz sampling rate, low quality. A
   * file with an audio duration of 10 minutes is approximately 1.2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_16000_MEDIUM: AAC encoding format, 16000 Hz sampling rate, medium
   * quality. A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_32000_LOW: AAC encoding format, 32000 Hz sampling rate, low quality. A
   * file with an audio duration of 10 minutes is approximately 1.2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_32000_MEDIUM: AAC encoding format, 32000 Hz sampling rate, medium
   * quality. A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_32000_HIGH: AAC encoding format, 32000 Hz sampling rate, high quality.
   * A file with an audio duration of 10 minutes is approximately 3.5 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_48000_MEDIUM: AAC encoding format, 48000 Hz sampling rate, medium
   * quality. A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_AAC_48000_HIGH: AAC encoding format, 48000 Hz sampling rate, high quality.
   * A file with an audio duration of 10 minutes is approximately 3.5 MB after encoding.
   * - AUDIO_ENCODING_TYPE_OPUS_16000_LOW: OPUS encoding format, 16000 Hz sampling rate, low quality.
   * A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_OPUS_16000_MEDIUM: OPUS encoding format, 16000 Hz sampling rate, medium
   * quality. A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_OPUS_48000_MEDIUM: OPUS encoding format, 48000 Hz sampling rate, medium
   * quality. A file with an audio duration of 10 minutes is approximately 2 MB after encoding.
   * - AUDIO_ENCODING_TYPE_OPUS_48000_HIGH: OPUS encoding format, 48000 Hz sampling rate, high
   * quality. A file with an audio duration of 10 minutes is approximately 3.5 MB after encoding.
   */
  public int encodingType;

  public AudioEncodedFrameObserverConfig() {
    postionType = Constants.AUDIO_FILE_RECORDING_PLAYBACK;
    encodingType = Constants.AUDIO_ENCODING_TYPE_OPUS_48000_MEDIUM;
  }
}
