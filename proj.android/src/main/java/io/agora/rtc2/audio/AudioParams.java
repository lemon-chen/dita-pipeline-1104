package io.agora.rtc2.audio;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;
import io.agora.rtc2.IAudioFrameObserver;

/**
 * @brief Audio data format.
 *
 * @details
 * You can pass the `AudioParams` object in the following APIs to set the audio data format for the
 * corresponding callback:
 * - `getRecordAudioParams`: Sets the audio data format for the `onRecordAudioFrame` callback.
 * - `getPlaybackAudioParams`: Sets the audio data format for the `onPlaybackAudioFrame` callback.
 * - `getMixedAudioParams`: Sets the audio data format for the `onMixedAudioFrame` callback.
 * - `getEarMonitoringAudioParams`: Sets the audio data format for the `onEarMonitoringAudioFrame`
 * callback.
 *
 * @note
 * - The SDK calculates the sampling interval through the `samplesPerCall`, `sampleRate`, and
 * `channel` parameters in `AudioParams`, and triggers the `onRecordAudioFrame`,
 * `onPlaybackAudioFrame`, `onMixedAudioFrame`, and `onEarMonitoringAudioFrame` callbacks according
 * to the sampling interval.
 * - Sample interval (sec) = `samplePerCall` /( `sampleRate` × `channel` ).
 * - Ensure that the sample interval ≥ 0.01 (s).
 *
 */
public class AudioParams {
  /**
   * The audio sample rate (Hz), which can be set as one of the following values:
   * - 8000.
   * - (Default) 16000.
   * - 32000.
   * - 44100
   * - 48000
   */
  public int sampleRate = 0;
  /**
   * The number of audio channels, which can be set as either of the following values:
   * - 1: (Default) Mono.
   * - 2: Stereo.
   */
  public int channel = 0;
  /**
   * The use mode of the audio data, which can be set as either of the following values:
   * - RAW_AUDIO_FRAME_OP_MODE_READ_ONLY (0): Read-only mode, For example, when users acquire the data
   * with the Agora SDK, then start the media push.
   * - RAW_AUDIO_FRAME_OP_MODE_READ_WRITE (2): Read and write mode, For example, when users have their
   * own audio-effect processing module and perform some voice pre-processing, such as a voice change.
   */
  public int mode = Constants.RAW_AUDIO_FRAME_OP_MODE_READ_ONLY;
  /**
   * The number of samples, such as 1024 for the media push.
   */
  public int samplesPerCall = 0;

  @CalledByNative
  public AudioParams(int sampleRate, int channelCnt, int mode, int samplesPerCall) {
    this.sampleRate = sampleRate;
    this.channel = channelCnt;
    this.mode = mode;
    this.samplesPerCall = samplesPerCall;
  }

  @CalledByNative
  public int getSampleRate() {
    return this.sampleRate;
  }

  @CalledByNative
  public int getChannel() {
    return this.channel;
  }

  @CalledByNative
  public int getMode() {
    return this.mode;
  }

  @CalledByNative
  public int getSamplesPerCall() {
    return this.samplesPerCall;
  }

  @Override
  public String toString() {
    return "AudioParams{"
        + "sampleRate=" + sampleRate + ", channel=" + channel + ", mode=" + mode
        + ", samplesPerCall=" + samplesPerCall + '}';
  }
}
