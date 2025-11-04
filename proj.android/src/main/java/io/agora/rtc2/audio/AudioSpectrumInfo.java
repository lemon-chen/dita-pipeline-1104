package io.agora.rtc2.audio;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The audio spectrum data.
 */
public class AudioSpectrumInfo {
  /**
   * The audio spectrum data. Agora divides the audio frequency into 256 frequency domains, and
   * reports the energy value of each frequency domain through this parameter. The value range of each
   * energy type is [-300, 1] and the unit is dBFS.
   */
  private float[] audioSpectrumData;
  /**
   * The audio spectrum data length is 256.
   */
  private int dataLength;

  /**
   * @param audioSpectrumData which reports the audio spectrum of each remote speaker.
   * @param dataLength the length of audio spectrum data.
   */
  @CalledByNative
  public AudioSpectrumInfo(float[] audioSpectrumData, int dataLength) {
    this.audioSpectrumData = audioSpectrumData;
    this.dataLength = dataLength;
  }

  public float[] getAudioSpectrumData() {
    return audioSpectrumData;
  }

  public int getDataLength() {
    return dataLength;
  }
}
