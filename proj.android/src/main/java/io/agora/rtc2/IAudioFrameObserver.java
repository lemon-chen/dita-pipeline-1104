package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.audio.AudioParams;
import java.nio.ByteBuffer;

/**
 * The IAudioFrameObserver interface.
 */
public interface IAudioFrameObserver {
  /**
   * @brief Gets the captured audio frame.
   *
   * @details
   * To ensure that the format of the cpatured audio frame is as expected, you can choose one of the
   * following two methods to set the audio data format:
   * - Method 1: After calling `setRecordingAudioFrameParameters` to set the audio data format and
   * `registerAudioFrameObserver` to register the audio frame observer object, the SDK calculates the
   * sampling interval according to the parameters set in the methods, and triggers the
   * `onRecordAudioFrame` callback according to the sampling interval.
   * - Method 2: After calling `registerAudioFrameObserver` to register the audio frame observer
   * object, set the audio data format in the return value of the `getObservedAudioFramePosition`
   * callback. The SDK then calculates the sampling interval according to the return value of the
   * `getRecordAudioParams` callback, and triggers the `onRecordAudioFrame` callback according to the
   * sampling interval.
   *
   * @note The priority of method 1 is higher than that of method 2. If method 1 is used to set the
   * audio data format, the setting of method 2 is invalid.
   *
   * @param channelId The channel ID.
   * @param type The audio frame type.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param bytesPerSample The number of bytes per audio sample. For example, each PCM audio sample
   * usually takes up 16 bits (2 bytes).
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param buffer The audio buffer. The buffer size = samplesPerChannel x channels x bytesPerSample.
   * @param renderTimeMs The timestamp (ms) of the external audio frame. You can use this parameter
   * for the following purpose: Synchronize audio and video frames in video or audio related
   * scenarios, including where external video sources are used.
   * @param avsync_type Reserved for future use.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  public abstract boolean onRecordAudioFrame(String channelId, int type, int samplesPerChannel,
      int bytesPerSample, int channels, int samplesPerSec, ByteBuffer buffer, long renderTimeMs,
      int avsync_type);
  /**
   * @brief Gets the raw audio frame for playback.
   * 
   * @details
   * To ensure that the format of the cpatured audio frame is as expected, you can choose one of the
   * following two methods to set the audio data format:
   * - Method 1: After calling `setPlaybackAudioFrameParameters` to set the audio data format and
   * `registerAudioFrameObserver` to register the audio frame observer object, the SDK calculates the
   * sampling interval according to the parameters set in the methods, and triggers the
   * `onPlaybackAudioFrame` callback according to the sampling interval.
   * - Method 2: After calling `registerAudioFrameObserver` to register the audio frame observer
   * object, set the audio data format in the return value of the `getObservedAudioFramePosition`
   * callback. The SDK then calculates the sampling interval according to the return value of the
   * `getPlaybackAudioParams` callback, and triggers the `onPlaybackAudioFrame` callback according to the
   * sampling interval.
   *
   * @note The priority of method 1 is higher than that of method 2. If method 1 is used to set the
   * audio data format, the setting of method 2 is invalid.
   * 
   * @param channelId The channel ID.
   * @param type The audio frame type.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param bytesPerSample The number of bytes per audio sample. For example, each PCM
   * audio sample usually takes up 16 bits (2 bytes).
   * @param channels The number of channels. 
   * - 1: Mono.
   * - 2: Stereo.  If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param buffer The audio buffer. The buffer size = samplesPerChannel x channels x bytesPerSample.
   * @param renderTimeMs The timestamp (ms) of the external audio frame. You can use this parameter
   * for the following purpose: Synchronize audio and video frames in video or audio related
   * scenarios, including where external video sources are used.
   * @param avsync_type Reserved for future use.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  public abstract boolean onPlaybackAudioFrame(String channelId, int type, int samplesPerChannel,
      int bytesPerSample, int channels, int samplesPerSec, ByteBuffer buffer, long renderTimeMs,
      int avsync_type);

  /**
   * @brief Retrieves the mixed captured and playback audio frame.
   *
   * @details
   * To ensure that the data format of mixed captured and playback audio frame meets the expectations,
   * Agora recommends that you choose one of the following two ways to set the data format:
   * - Method 1: After calling `setMixedAudioFrameParameters` to set the audio data format and
   * `registerAudioFrameObserver` to register the audio frame observer object, the SDK calculates the
   * sampling interval according to the parameters set in the methods, and triggers the
   * `onMixedAudioFrame` callback according to the sampling interval.
   * - Method 2: After calling `registerAudioFrameObserver` to register the audio frame observer
   * object, set the audio data format in the return value of the `getObservedAudioFramePosition`
   * callback. The SDK then calculates the sampling interval according to the return value of the
   * `getMixedAudioParams` callback, and triggers the `onMixedAudioFrame` callback according to the
   * sampling interval.
   *
   * @note The priority of method 1 is higher than that of method 2. If method 1 is used to set the
   * audio data format, the setting of method 2 is invalid.
   *
   * @param type The audio frame type.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param bytesPerSample The number of bytes per audio sample. For example, each PCM audio sample
   * usually takes up 16 bits (2 bytes).
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param buffer The audio buffer. The buffer size = samplesPerChannel x channels x bytesPerSample.
   * @param renderTimeMs The timestamp (ms) of the external audio frame. You can use this parameter
   * for the following purpose: Synchronize audio and video frames in video or audio related
   * scenarios, including where external video sources are used.
   * @param avsync_type Reserved for future use.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  public abstract boolean onMixedAudioFrame(String channelId, int type, int samplesPerChannel,
      int bytesPerSample, int channels, int samplesPerSec, ByteBuffer buffer, long renderTimeMs,
      int avsync_type);

  /**
   * @brief Gets the in-ear monitoring audio frame.
   *
   * @details
   * To ensure that the obtained in-ear audio data meets the expectations, Agora recommends that
   * you choose one of the following two methods to set the audio data format:
   * - Method 1: After calling `setEarMonitoringAudioFrameParameters` to set the audio data format and
   * `registerAudioFrameObserver` to register the audio frame observer object, the SDK calculates the
   * sampling interval according to the parameters set in the methods, and triggers the
   * `onEarMonitoringAudioFrame` callback according to the sampling interval.
   * - Method 2: After calling `registerAudioFrameObserver` to register the audio frame observer
   * object, set the audio data format in the return value of the `getObservedAudioFramePosition`
   * callback. The SDK then calculates the sampling interval according to the return value of the
   * `getEarMonitoringAudioParams` callback, and triggers the `onEarMonitoringAudioFrame` callback according to
   * the sampling interval.
   *
   * @note The priority of method 1 is higher than that of method 2. If method 1 is used to set the
   * audio data format, the setting of method 2 is invalid.
   *
   * @param type The audio frame type.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param bytesPerSample The number of bytes per audio sample. For example, each PCM audio sample
   * usually takes up 16 bits (2 bytes).
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param buffer The audio buffer. The buffer size = samplesPerChannel x channels x bytesPerSample.
   * @param renderTimeMs The timestamp (ms) of the external audio frame. You can use this parameter
   * for the following purpose: Synchronize audio and video frames in video or audio related
   * scenarios, including where external video sources are used.
   * @param avsync_type Reserved for future use.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  public abstract boolean onEarMonitoringAudioFrame(int type, int samplesPerChannel,
      int bytesPerSample, int channels, int samplesPerSec, ByteBuffer buffer, long renderTimeMs,
      int avsync_type);

  /**
   * @brief Retrieves the audio frame before mixing of subscribed remote users.
   *
   * @param uid The ID of subscribed remote users.
   * @param type The audio frame type.
   * @param samplesPerChannel The number of samples per channel in the audio frame.
   * @param bytesPerSample The number of bytes per audio sample. For example, each PCM audio sample
   * usually takes up 16 bits (2 bytes).
   * @param channels The number of channels.
   * - 1: Mono.
   * - 2: Stereo. If the channel uses stereo, the data is interleaved.
   * @param samplesPerSec Recording sample rate (Hz).
   * @param buffer The audio buffer. The buffer size = samplesPerChannel x channels x bytesPerSample.
   * @param renderTimeMs The timestamp (ms) of the external audio frame. You can use this parameter
   * for the following purpose: Synchronize audio and video frames in video or audio related
   * scenarios, including where external video sources are used.
   * @param avsync_type Reserved for future use.
   *
   * @return
   * Without practical meaning.
   */
  @CalledByNative
  public abstract boolean onPlaybackAudioFrameBeforeMixing(String channelId, int uid, int type,
      int samplesPerChannel, int bytesPerSample, int channels, int samplesPerSec, ByteBuffer buffer,
      long renderTimeMs, int avsync_type, int rtpTimestamp, long presentationMs);

  /**
   * @brief Sets the frame position for the video observer.
   *
   * @details
   * After successfully registering the audio data observer, the SDK uses this callback for each
   * specific audio frame processing node to determine whether to trigger the following callbacks:
   * - `onRecordAudioFrame`
   * - `onPlaybackAudioFrame`
   * - `onPlaybackAudioFrameBeforeMixing`
   * - `onMixedAudioFrame`
   * - `onEarMonitoringAudioFrame`
   * You can set one or more positions you need to observe by modifying the return value of
   * `getObservedAudioFramePosition` based on your scenario requirements:
   * When the annotation observes multiple locations, the | (or operator) is required. To conserve
   * system resources, you can reduce the number of frame positions that you want to observe.
   *
   * @return
   * Returns a bitmask that sets the observation position, with the following values:
   * - POSITION_PLAYBACK (0x0001): This position can observe the playback audio mixed by all remote
   * users, corresponding to the `onPlaybackAudioFrame` callback.
   * - POSITION_RECORD (0x0002): This position can observe the collected local user's audio,
   * corresponding to the `onRecordAudioFrame` callback.
   * - POSITION_MIXED (0x0004): This position can observe the playback audio mixed by the loacl user
   * and all remote users, corresponding to the `onMixedAudioFrame` callback.
   * - POSITION_BEFORE_MIXING (0x0008): This position can observe the audio of a single remote user
   * before mixing, corresponding to the `onPlaybackAudioFrameBeforeMixing` callback.
   * - POSITION_EAR_MONITORING (0x0010): This position can observe the in-ear monitoring audio of the
   * local user, corresponding to the `onEarMonitoringAudioFrame` callback.
   */
  @CalledByNative public abstract int getObservedAudioFramePosition();

  /**
   * @brief Sets the audio format for the `onRecordAudioFrame` callback.
   *
   * @details
   * You need to register the callback when calling the `registerAudioFrameObserver` method. After you
   * successfully register the audio observer, the SDK triggers this callback, and you can set the
   * audio format in the return value of this callback.
   *
   * @note
   * The SDK triggers the `onRecordAudioFrame` callback with the `AudioParams` calculated sampling
   * interval you set in the return value. The calculation formula is Sample interval (sec) =
   * `samplePerCall` /( `sampleRate` × `channel` ).
   * Ensure that the sample interval ≥ 0.01 (s).
   *
   * @return
   * The captured audio data, see `AudioParams`.
   */
  @CalledByNative public abstract AudioParams getRecordAudioParams();

  /**
   * @brief Sets the audio format for the `onPlaybackAudioFrame` callback.
   *
   * @details
   * You need to register the callback when calling the `registerAudioFrameObserver` method. After you
   * successfully register the audio observer, the SDK triggers this callback, and you can set the
   * audio format in the return value of this callback.
   *
   * @note
   * The SDK triggers the `onPlaybackAudioFrame` callback with the `AudioParams` calculated sampling
   * interval you set in the return value. The calculation formula is Sample interval (sec) =
   * `samplePerCall` /( `sampleRate` × `channel` ).
   * Ensure that the sample interval ≥ 0.01 (s).
   *
   * @return The audio data for playback, see `AudioParams`.
   */
  @CalledByNative public abstract AudioParams getPlaybackAudioParams();

  /**
   * @brief Sets the audio format for the `onMixedAudioFrame` callback.
   *
   * @details
   * You need to register the callback when calling the `registerAudioFrameObserver` method. After you
   * successfully register the audio observer, the SDK triggers this callback, and you can set the
   * audio format in the return value of this callback.
   *
   * @note
   * The SDK triggers the `onMixedAudioFrame` callback with the `AudioParams` calculated sampling
   * interval you set in the return value. The calculation formula is Sample interval (sec) =
   * `samplePerCall` /( `sampleRate` × `channel` ).
   * Ensure that the sample interval ≥ 0.01 (s).
   *
   * @return
   * The mixed captured and playback audio data, see `AudioParams`.
   */
  @CalledByNative public abstract AudioParams getMixedAudioParams();

  /**
   * @brief Sets the audio format for the `onEarMonitoringAudioFrame` callback.
   *
   * @details
   * You need to register the callback when calling the `registerAudioFrameObserver` method. After you
   * successfully register the audio observer, the SDK triggers this callback, and you can set the
   * audio format in the return value of this callback.
   *
   * @note
   * The SDK triggers the `onEarMonitoringAudioFrame` callback with the `AudioParams` calculated
   * sampling interval you set in the return value. The calculation formula is `Sample` interval (
   * `sec` ) = `samplePerCall` /( `sampleRate` × `channel` ).
   * Ensure that the sample interval ≥ 0.01 (s).
   *
   * @return
   * The audio data of in-ear monitoring, see `AudioParams`.
   */
  @CalledByNative public abstract AudioParams getEarMonitoringAudioParams();
}
