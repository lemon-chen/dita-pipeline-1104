//
//  Agora RTC/MEDIA SDK
//
//  Created by Tongjiangyong in 2019-11.
//  Copyright (c) 2019 Agora.io. All rights reserved.
//
package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;
/**
 * @brief The detailed information of the media stream.
 */
public class MediaStreamInfo {
  /**
   * The index of the media stream.
   */
  private int streamIndex;
  /**
   * The type of the media stream.
   * - STREAM_TYPE_UNKNOWN (0): The type is unknown.
   * - STREAM_TYPE_VIDEO (1): The video stream.
   * - STREAM_TYPE_AUDIO (2): The audio stream.
   * - STREAM_TYPE_SUBTITLE (3): The subtitle stream.
   */
  private int mediaStreamType;
  /**
   * The codec of the media stream.
   */
  private String codecName;
  /**
   * The language of the media stream.
   */
  private String language;
  /**
   * This parameter only takes effect for video streams, and indicates the video frame rate (fps).
   */
  private int videoFrameRate;
  /**
   * This parameter only takes effect for video streams, and indicates the video bitrate (bps).
   */
  private int videoBitRate;
  /**
   * This parameter only takes effect for video streams, and indicates the video width (pixel).
   */
  private int videoWidth;
  /**
   * This parameter only takes effect for video streams, and indicates the video height (pixel).
   */
  private int videoHeight;
  /**
   * This parameter only takes effect for video streams, and indicates the video rotation angle.
   */
  private int videoRotation;
  /**
   * This parameter only takes effect for audio streams, and indicates the audio sample rate (Hz).
   */
  private int audioSampleRate;
  /**
   * This parameter only takes effect for audio streams, and indicates the audio channel number.
   */
  private int audioChannels;
  /**
   * This parameter only takes effect for audio streams, and indicates the bit number of each audio
   * sample.
   */
  private int audioBytesPerSample;
  /**
   * The total duration (ms) of the media stream.
   */
  private long duration;

  public MediaStreamInfo() {}

  @CalledByNative
  public MediaStreamInfo(int streamIndex, int mediaStreamType, String codecName, String language,
      int videoFrameRate, int videoBitRate, int videoWidth, int videoHeight, int videoRotation,
      int audioSampleRate, int audioChannels, long duration) {
    this.streamIndex = streamIndex;
    this.mediaStreamType = mediaStreamType;
    this.codecName = codecName;
    this.language = language;
    this.videoFrameRate = videoFrameRate;
    this.videoBitRate = videoBitRate;
    this.videoWidth = videoWidth;
    this.videoHeight = videoHeight;
    this.videoRotation = videoRotation;
    this.audioSampleRate = audioSampleRate;
    this.audioChannels = audioChannels;
    this.duration = duration;
  }

  @CalledByNative
  public int getStreamIndex() {
    return streamIndex;
  }

  @CalledByNative
  public int getMediaStreamType() {
    return mediaStreamType;
  }

  @CalledByNative
  public String getCodecName() {
    return codecName;
  }

  @CalledByNative
  public String getLanguage() {
    return language;
  }

  @CalledByNative
  public int getVideoFrameRate() {
    return videoFrameRate;
  }

  @CalledByNative
  public int getVideoWidth() {
    return videoWidth;
  }

  @CalledByNative
  public int getVideoHeight() {
    return videoHeight;
  }

  @CalledByNative
  public int getAudioSampleRate() {
    return audioSampleRate;
  }

  @CalledByNative
  public int getAudioChannels() {
    return audioChannels;
  }

  @CalledByNative
  public int getAudioBytesPerSample() {
    return audioBytesPerSample;
  }

  @CalledByNative
  public long getDuration() {
    return duration;
  }

  @CalledByNative
  public int getVideoBitRate() {
    return videoBitRate;
  }

  public void setStreamIndex(int streamIndex) {
    this.streamIndex = streamIndex;
  }

  public void setMediaStreamType(int mediaStreamType) {
    this.mediaStreamType = mediaStreamType;
  }

  public void setCodecName(String codecName) {
    this.codecName = codecName;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public void setVideoFrameRate(int videoFrameRate) {
    this.videoFrameRate = videoFrameRate;
  }

  public void setVideoBitRate(int videoBitRate) {
    this.videoBitRate = videoBitRate;
  }

  public void setVideoWidth(int videoWidth) {
    this.videoWidth = videoWidth;
  }

  public void setVideoHeight(int videoHeight) {
    this.videoHeight = videoHeight;
  }

  public void setAudioSampleRate(int audioSampleRate) {
    this.audioSampleRate = audioSampleRate;
  }

  public void setAudioChannels(int audioChannels) {
    this.audioChannels = audioChannels;
  }

  public void setAudioBytesPerSample(int audioBytesPerSample) {
    this.audioBytesPerSample = audioBytesPerSample;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public int getVideoRotation() {
    return videoRotation;
  }

  public void setVideoRotation(int videoRotation) {
    this.videoRotation = videoRotation;
  }

  @Override
  public String toString() {
    return "MediaStreamInfo{"
        + "streamIndex=" + streamIndex + ", mediaStreamType=" + mediaStreamType + ", codecName='"
        + codecName + '\'' + ", language='" + language + '\'' + ", videoFrameRate=" + videoFrameRate
        + ", videoBitRate=" + videoBitRate + ", videoWidth=" + videoWidth + ", videoHeight="
        + videoHeight + ", audioSampleRate=" + audioSampleRate + ", videoRotation=" + videoRotation
        + ", audioChannels=" + audioChannels + ", duration=" + duration + '}';
  }
}
