package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Information related to the media player.
 */
public class PlayerUpdatedInfo {
  /**
   * @technical preview
   */
  public String internalPlayerUuid;
  /**
   * The ID of a deivce.
   */
  public String deviceId;
  /**
   * Height (pixel) of the video.
   */
  public long videoHeight;
  /**
   * Width (pixel) of the video.
   */
  public long videoWidth;
  /**
   * Audio sample rate (Hz).
   */
  public long audioSampleRate;
  /**
   * The number of audio channels.
   */
  public long audioChannels;
  /**
   * The number of bits per audio sample point.
   */
  public long audioBitsPerSample;

  public PlayerUpdatedInfo() {
    internalPlayerUuid = null;
    deviceId = null;
  }

  @CalledByNative
  public PlayerUpdatedInfo(String internalPlayerUuid, String deviceId, long videoHeight,
      long videoWidth, long audioSampleRate, long audioChannels, long audioBitsPerSample) {
    this.internalPlayerUuid = internalPlayerUuid;
    this.deviceId = deviceId;
    this.videoHeight = videoHeight;
    this.videoWidth = videoWidth;
    this.audioSampleRate = audioSampleRate;
    this.audioChannels = audioChannels;
    this.audioBitsPerSample = audioBitsPerSample;
  }

  @CalledByNative
  public PlayerUpdatedInfo(String internalPlayerUuid, String deviceId) {
    this.internalPlayerUuid = internalPlayerUuid;
    this.deviceId = deviceId;
  }

  /**
   * Set the internal player UUID.
   *
   * @technical preview
   * @param internalPlayerUuid The internal player UUID.
   */
  @CalledByNative
  public void setInternalPlayerUuid(String internalPlayerUuid) {
    this.internalPlayerUuid = internalPlayerUuid;
  }

  /**
   * Set the device ID.
   *
   * @technical preview
   * @param deviceId The device ID.
   */
  @CalledByNative
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  /**
   * Set the video height.
   *
   * @technical preview
   * @param videoHeight The video height.
   */
  @CalledByNative
  public void setVideoHeight(long videoHeight) {
    this.videoHeight = videoHeight;
  }

  /**
   * Set the video width.
   *
   * @technical preview
   * @param videoWidth The video width.
   */
  @CalledByNative
  public void setVideoWidth(long videoWidth) {
    this.videoWidth = videoWidth;
  }

  /**
   * Set the audio sample rate.
   *
   * @technical preview
   * @param audioSampleRate The audio sample rate.
   */
  @CalledByNative
  public void setAudioSampleRate(long audioSampleRate) {
    this.audioSampleRate = audioSampleRate;
  }

  /**
   * Set the audio channels.
   *
   * @technical preview
   * @param audioChannels The audio channels.
   */
  @CalledByNative
  public void setAudioChannels(long audioChannels) {
    this.audioChannels = audioChannels;
  }

  /**
   * Set the audio bits per sample.
   *
   * @technical preview
   * @param audioBitsPerSample The audio bits per sample.
   */
  @CalledByNative
  public void setAudioBitsPerSample(long audioBitsPerSample) {
    this.audioBitsPerSample = audioBitsPerSample;
  }

  /**
   * @brief Get the internal player UUID.
   *
   * @technical preview
   * @return The internal player UUID.
   */
  @CalledByNative
  public String getInternalPlayerUuid() {
    return this.internalPlayerUuid;
  }

  /**
   * @brief Get the device ID.
   *
   * @technical preview
   * @return The device ID.
   */
  @CalledByNative
  public String getDeviceId() {
    return this.deviceId;
  }

  /**
   * @brief Get the video width.
   *
   * @technical preview
   * @return The video width.
   */
  public long getVideoWidth() {
    return videoWidth;
  }

  /**
   * @brief Get the video height.
   *
   * @technical preview
   * @return The video height.
   */
  public long getVideoHeight() {
    return videoHeight;
  }

  /**
   * @brief Get the audio sample rate.
   *
   * @technical preview
   * @return The audio sample rate.
   */
  public long getAudioSampleRate() {
    return audioSampleRate;
  }

  /**
   * @brief Get the audio channels.
   *
   * @technical preview
   * @return The audio channels.
   */
  public long getaudioChannels() {
    return audioChannels;
  }

  /**
   * @brief Get the audio bits per sample.
   *
   * @technical preview
   * @return The audio bits per sample.
   */
  public long getAudioBitsPerSample() {
    return audioBitsPerSample;
  }

  @Override
  public String toString() {
    return "PlayerUpdatedInfo{"
        + "internalPlayerUuid=" + internalPlayerUuid + ", deviceId=" + deviceId + "videoHeight="
        + videoHeight + ", videoWidth=" + videoWidth + ", audioSampleRate=" + audioSampleRate
        + "audioChannels=" + audioChannels + "audioBitsPerSample=" + audioBitsPerSample + '}';
  }
}
