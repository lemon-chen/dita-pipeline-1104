package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The media setting options for the host.
 *
 * @deprecated v4.6.0.
 */
@Deprecated
public class DirectCdnStreamingMediaOptions {
  /**
   * Sets whether to publish the video captured by the camera:
   * - `true`: Publish the video captured by the camera.
   * - `false`: (Default) Do not publish the video captured by the camera.
   */
  public Boolean publishCameraTrack;
  /**
   * Sets whether to publish the audio captured by the microphone:
   * - `true`: Publish the audio captured by the microphone.
   * - `false`: (Default) Do not publish the audio captured by the microphone.
   */
  public Boolean publishMicrophoneTrack;
  /**
   * Sets whether to publish the captured audio from a custom source:
   * - `true`: Publish the captured audio from a custom source.
   * - `false`: (Default) Do not publish the captured audio from the custom source.
   */
  public Boolean publishCustomAudioTrack;
  /**
   * Sets whether to publish the captured video from a custom source:
   * - `true`: Publish the captured video from a custom source.
   * - `false`: (Default) Do not publish the captured video from the custom source.
   */
  public Boolean publishCustomVideoTrack;
  /**
   * Determines whether to publish the audio track of media player source.
   * - true: Publish the audio track of media player source.
   * - false: (Default) Do not publish the audio track of media player source.
   */
  public Boolean publishMediaPlayerAudioTrack;
  /**
   * Determines which media player source should be published.
   * This parameter get from function getMediaPlayerId() of AgoraMediaPlayer.
   */
  public Integer publishMediaPlayerId;
  /**
   * The video track ID returned by calling the `createCustomVideoTrack` method. The default value is
   * 0.
   */
  public Integer customVideoTrackId;

  public DirectCdnStreamingMediaOptions() {
    publishCameraTrack = false;
    publishMicrophoneTrack = false;
    publishCustomAudioTrack = false;
    publishCustomVideoTrack = false;
    publishMediaPlayerAudioTrack = false;
    publishMediaPlayerId = 0;
    customVideoTrackId = 0;
  }

  @CalledByNative
  public Boolean isPublishCameraTrack() {
    return publishCameraTrack;
  }

  @CalledByNative
  public Boolean isPublishMicrophoneTrack() {
    return publishMicrophoneTrack;
  }

  @CalledByNative
  public Boolean isPublishCustomAudioTrack() {
    return publishCustomAudioTrack;
  }

  @CalledByNative
  public Boolean isPublishCustomVideoTrack() {
    return publishCustomVideoTrack;
  }

  @CalledByNative
  public Boolean isPublishMediaPlayerAudioTrack() {
    return publishMediaPlayerAudioTrack;
  }

  @CalledByNative
  public Integer getPublishMediaPlayerId() {
    return publishMediaPlayerId;
  }

  @CalledByNative
  public Integer getCustomVideoTrackId() {
    return customVideoTrackId;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("publishCameraTrack=").append(publishCameraTrack);
    sb.append(" publishMicrophoneTrack=").append(publishMicrophoneTrack);
    sb.append(" publishCustomAudioTrack=").append(publishCustomAudioTrack);
    sb.append(" publishCustomVideoTrack=").append(publishCustomVideoTrack);
    sb.append(" publishMediaPlayerAudioTrack=").append(publishMediaPlayerAudioTrack);
    sb.append(" publishMediaPlayerId=").append(publishMediaPlayerId);
    sb.append(" customVideoTrackId=").append(customVideoTrackId);

    return sb.toString();
  }
}
