package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import java.nio.ByteBuffer;

public interface IAudioEncodedFrameObserver {
  /**
   * @brief Gets the encoded audio data of the local user.
   *
   * @details
   * After calling `registerAudioEncodedFrameObserver` and setting the encoded audio as
   * AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIC, you can get the encoded audio data of the local user
   * from this callback.
   *
   * @param buffer The audio buffer.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param codecType Audio encoding type:
   * - AUDIO_CODEC_OPUS (1): OPUS.
   * - AUDIO_CODEC_AACLC (8): LC-AAC.
   * - AUDIO_CODEC_HEAAC (9): HE-AAC.
   * - AUDIO_CODEC_HEAAC2 (11): HE-AAC v2.
   *
   */
  @CalledByNative
  public abstract void onRecordAudioEncodedFrame(
      ByteBuffer buffer, int samplesPerChannel, int channels, int samplesPerSec, int codecType);
  /**
   * @brief Gets the encoded audio data of all remote users.
   *
   * @details
   * After calling `registerAudioEncodedFrameObserver` and setting the encoded audio as
   * AUDIO_ENCODED_FRAME_OBSERVER_POSITION_PLAYBACK, you can get encoded audio data of all remote
   * users through this callback.
   *
   * @param buffer The audio buffer.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param codecType Audio encoding type:
   * - AUDIO_CODEC_OPUS (1): OPUS.
   * - AUDIO_CODEC_AACLC (8): LC-AAC.
   * - AUDIO_CODEC_HEAAC (9): HE-AAC.
   * - AUDIO_CODEC_HEAAC2 (11): HE-AAC v2.
   *
   */
  @CalledByNative
  public abstract void onPlaybackAudioEncodedFrame(
      ByteBuffer buffer, int samplesPerChannel, int channels, int samplesPerSec, int codecType);
  /**
   * @brief Gets the mixed and encoded audio data of the local and all remote users.
   *
   * @details
   * After calling `registerAudioEncodedFrameObserver` and setting the audio profile as
   * AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIXED, you can get the mixed and encoded audio data of the
   * local and all remote users through this callback.
   *
   * @param buffer The audio buffer.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param codecType Audio encoding type:
   * - AUDIO_CODEC_OPUS (1): OPUS.
   * - AUDIO_CODEC_AACLC (8): LC-AAC.
   * - AUDIO_CODEC_HEAAC (9): HE-AAC.
   * - AUDIO_CODEC_HEAAC2 (11): HE-AAC v2.
   *
   */
  @CalledByNative
  public abstract void onMixedAudioEncodedFrame(
      ByteBuffer buffer, int samplesPerChannel, int channels, int samplesPerSec, int codecType);
}
