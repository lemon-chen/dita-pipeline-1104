package io.agora.rtc2.internal;

import io.agora.rtc2.Constants;

/**
 * @brief Recording configurations.
 */
public class AudioRecordingConfiguration {
  /**
   * The absolute path (including the filename extensions) of the recording file. For example:
   * `/sdcard/emulated/0/audio.aac`.
   * @note Ensure that the directory for the log files exists and is writable.
   */
  public String filePath;

  /**
   * Recording sample rate (Hz).
   * - 16000
   * - (Default) 32000
   * - 44100
   * - 48000
   * @note If you set this parameter to 44100 or 48000, Agora recommends recording WAV files, or AAC
   * files with `quality` set as AUDIO_RECORDING_QUALITY_MEDIUM or AUDIO_RECORDING_QUALITY_HIGH for
   * better recording quality.
   */
  public int sampleRate;

  /**
   * Whether to encode the audio data:
   * - `true`: Encode audio data in AAC.
   * - `false`: (Default) Do not encode audio data, but save the recorded audio data directly.
   */
  public boolean codec;

  /**
   * Recording content:
   * - AUDIO_FILE_RECORDING_MIC (1): Only records the audio of the local user.
   * - AUDIO_FILE_RECORDING_PLAYBACK (2): Only records the audio of all remote users.
   * - AUDIO_FILE_RECORDING_MIXED (3): (Default) Records the mixed audio of the local and all remote
   * users.
   */
  public int fileRecordOption;

  /**
   * Recording quality:
   * - AUDIO_RECORDING_QUALITY_LOW (0): Low quality. For example, the size of an AAC file with a
   * sample rate of 32,000 Hz and a recording duration of 10 minutes is around 1.2 MB.
   * - AUDIO_RECORDING_QUALITY_MEDIUM (1): (Default) Medium quality. For example, the size of an AAC
   * file with a sample rate of 32,000 Hz and a recording duration of 10 minutes is around 2 MB.
   * - AUDIO_RECORDING_QUALITY_HIGH (2): High quality. For example, the size of an AAC file with a
   * sample rate of 32,000 Hz and a recording duration of 10 minutes is around 3.75 MB.
   * - AUDIO_RECORDING_QUALITY_ULTRA_HIGH (3): Ultra high quality. The sample rate is 32 kHz, and the
   * file size is around 7.5 MB after 10 minutes of recording.
   * @note This parameter applies to AAC files only.
   */
  public int quality;

  /**
   * The audio channel of recording: The parameter supports the following values:
   * - 1: (Default) Mono.
   * - 2: Stereo.
   * @note
   * The actual recorded audio channel is related to the audio channel that you capture.
   * - If the captured audio is mono and `recordingChannel` is `2`, the recorded audio is the
   * dual-channel data that is copied from mono data, not stereo.
   * - If the captured audio is dual channel and `recordingChannel` is `1`, the recorded audio is the
   * mono data that is mixed by dual-channel data.
   * The integration scheme also affects the final recorded audio channel. If you need to record in
   * stereo, contact `technical support`.
   */
  public int recordingChannel;

  /**
   * The Audio file recording config.
   */
  public AudioRecordingConfiguration() {
    sampleRate = 32000;
    codec = true;
    fileRecordOption = Constants.AUDIO_FILE_RECORDING_MIXED;
    quality = Constants.AUDIO_RECORDING_QUALITY_MEDIUM;
    recordingChannel = 1;
  }
}
