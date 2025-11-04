package io.agora.rtc2.audio;

import io.agora.rtc2.audio.AudioSpectrumInfo;
import io.agora.base.internal.CalledByNative;
/**
 * The IAudioSpectrumObserver interface.
 */
public interface IAudioSpectrumObserver {
  /**
   * @brief Gets the statistics of a local audio spectrum.
   *
   * @details
   * After successfully calling `registerAudioSpectrumObserver` to implement the
   * `onLocalAudioSpectrum` callback in `IAudioSpectrumObserver` and calling
   * `enableAudioSpectrumMonitor` to enable audio spectrum monitoring, the SDK triggers this callback
   * as the time interval you set to report the received remote audio data spectrum before encoding.
   *
   * @param data The audio spectrum data of the local user. See `AudioSpectrumInfo`.
   *
   * @return
   * Whether the spectrum data is received:
   * - `true`: Spectrum data is received.
   * - `false`: No spectrum data is received.
   */
  @CalledByNative boolean onLocalAudioSpectrum(AudioSpectrumInfo data);

  /**
   * @brief Gets the remote audio spectrum.
   *
   * @details
   * After successfully calling `registerAudioSpectrumObserver` to implement the
   * `onRemoteAudioSpectrum` callback in the `IAudioSpectrumObserver` and calling
   * `enableAudioSpectrumMonitor` to enable audio spectrum monitoring, the SDK will trigger the
   * callback as the time interval you set to report the received remote audio data spectrum.
   *
   * @param userAudioSpectrumInfos The audio spectrum information of the remote user. See
   * `UserAudioSpectrumInfo`. The number of arrays is the number of remote users monitored by the SDK.
   * If the array is null, it means that no audio spectrum of remote users is detected.
   * @param spectrumNumber The number of remote users.
   *
   * @return
   * Whether the spectrum data is received:
   * - `true`: Spectrum data is received.
   * - `false`: No spectrum data is received.
   */
  @CalledByNative
  boolean onRemoteAudioSpectrum(UserAudioSpectrumInfo[] userAudioSpectrumInfos, int spectrumNumber);
}
