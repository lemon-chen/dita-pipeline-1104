package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;

/**
 * @brief The channel media options.
 *
 * @details
 * Agora supports publishing multiple audio streams and one video stream at the same time and in the
 * same `RtcConnection`. For example, `publishMicrophoneTrack`, `publishCustomAudioTrack`, and
 * `publishMediaPlayerAudioTrack` can be set as `true` at the same time, but only one of
 * `publishCameraTrack`, `publishScreenCaptureVideo`, `publishCustomVideoTrack`, or
 * `publishEncodedVideoTrack` can be set as `true`.
 *
 * @note Agora recommends that you set member parameter values yourself according to your business
 * scenario, otherwise the SDK will automatically assign values to member parameters.
 *
 */
public class ChannelMediaOptions {
  /**
   * Whether to publish the video captured by the camera:
   * - `true`: Publish the video captured by the camera.
   * - `false`: Do not publish the video captured by the camera.
   */
  public Boolean publishCameraTrack;
  /**
   * Determines whether to publish the video of the secondary camera track.
   * - true: Publish the video track of the secondary camera capturer.
   * - false: (Default) Do not publish the video track of the secondary camera capturer.
   */
  public Boolean publishSecondaryCameraTrack;
  /**
   * Whether to publish the video captured by the third camera:
   * - `true`: Publish the video captured by the third camera.
   * - `false`: Do not publish the video captured by the third camera.
   */
  public Boolean publishThirdCameraTrack;
  /**
   * Whether to publish the video captured by the fourth camera:
   * - `true`: Publish the video captured by the fourth camera.
   * - `false`: Do not publish the video captured by the fourth camera.
   */
  public Boolean publishFourthCameraTrack;
  /**
   * Whether to publish the audio captured by the microphone:
   * - `true`: Publish the audio captured by the microphone.
   * - `false`: Do not publish the audio captured by the microphone.
   */
  public Boolean publishMicrophoneTrack;
  /**
   * Whether to publish the video captured from the screen:
   * - `true`: Publish the video captured from the screen.
   * - `false`: Do not publish the video captured from the screen.
   * @note As of v4.0.0, the parameter name is changed from `publishScreenTrack` to
   * `publishScreenCaptureVideo`.
   */
  public Boolean publishScreenCaptureVideo;
  /**
   * Whether to publish the audio captured from the screen:
   * - `true`: Publish the audio captured from the screen.
   * - `false`: Publish the audio captured from the screen.
   */
  public Boolean publishScreenCaptureAudio;
  /**
   * Whether to publish the audio captured from a custom source:
   * - `true`: Publish the audio captured from the custom source.
   * - `false`: Do not publish the captured audio from a custom source.
   */
  public Boolean publishCustomAudioTrack;
  /**
   * The ID of the custom audio track to be published. The default value is 0. You can obtain the
   * custom audio track ID through the `createCustomAudioTrack` method.
   */
  public Integer publishCustomAudioTrackId;
  /**
   * Whether to publish the video captured from a custom source:
   * - `true`: Publish the video captured from the custom source.
   * - `false`: Do not publish the captured video from a custom source.
   */
  public Boolean publishCustomVideoTrack;
  /**
   * Whether to publish the encoded video:
   * - `true`: Publish the encoded video.
   * - `false`: Do not publish the encoded video.
   */
  public Boolean publishEncodedVideoTrack;
  /**
   * Whether to publish the audio from the media player:
   * - `true`: Publish the audio from the media player.
   * - `false`: Do not publish the audio from the media player.
   */
  public Boolean publishMediaPlayerAudioTrack;
  /**
   * Whether to publish the video from the media player:
   * - `true`: Publish the video from the media player.
   * - `false`: Do not publish the video from the media player.
   */
  public Boolean publishMediaPlayerVideoTrack;
  /**
   * Whether to publish the local transcoded video track.
   * - `true`: Publish the video track of local transcoded video track.
   * - `false`: (Default) Do not publish the local transcoded video track.
   */
  public Boolean publishTranscodedVideoTrack;
  /**
   * Whether to publish the mixed audio track:
   * - `true`: Publish the mixed audio track.
   * - `false`: Do not publish the mixed audio track.
   */
  public Boolean publishMixedAudioTrack;
  /**
   * Whether to publish the video track processed by the speech driven extension:
   * - `true`: Publish the video track processed by the speech driven extension.
   * - `false`: (Default) Do not publish the video track processed by the speech driven extension.
   */
  public Boolean publishLipSyncTrack;
  /**
   * Whether to automatically subscribe to all remote audio streams when the user joins a channel:
   * - `true`: Subscribe to all remote audio streams.
   * - `false`: Do not automatically subscribe to any remote audio streams.
   */
  public Boolean autoSubscribeAudio;
  /**
   * Whether to automatically subscribe to all remote video streams when the user joins the channel:
   * - `true`: Subscribe to all remote video streams.
   * - `false`: Do not automatically subscribe to any remote video streams.
   */
  public Boolean autoSubscribeVideo;
  /**
   * Whether to enable audio capturing or playback:
   * - `true`: Enable audio capturing or playback.
   * - `false`: Do not enable audio capturing or playback.
   * @note If you need to publish the audio streams captured by your microphone, ensure this parameter
   * is set as `true`.
   */
  public Boolean enableAudioRecordingOrPlayout;
  /**
   * The ID of the media player to be published. The default value is 0.
   */
  public Integer publishMediaPlayerId;
  /**
   * The user role:
   * - CLIENT_ROLE_BROADCASTER (1): Host.
   * - CLIENT_ROLE_AUDIENCE (2): Audience.
   * @note If you set the user role as an audience member, you cannot publish audio and video streams
   * in the channel. If you want to publish media streams in a channel during live streaming, ensure
   * you set the user role as broadcaster.
   */
  public Integer clientRoleType;
  /**
   * The latency level of an audience member in interactive live streaming.
   * - AUDIENCE_LATENCY_LEVEL_LOW_LATENCY (1): Low latency.
   * - AUDIENCE_LATENCY_LEVEL_ULTRA_LOW_LATENCY (2): (Default) Ultra low latency.
   */
  public Integer audienceLatencyLevel;
  /**
   * The default video-stream type:
   * - VIDEO_STREAM_HIGH (0): High-quality stream, that is, a high-resolution and high-bitrate video
   * stream.
   * - VIDEO_STREAM_LOW (1): Low-quality stream, that is, a low-resolution and low-bitrate video
   * stream.
   */
  public Integer defaultVideoStreamType;
  /**
   * The channel profile.
   * - CHANNEL_PROFILE_COMMUNICATION (0): Communication. Agora recommends using the live streaming
   * profile for a better audio and video experience.
   * - CHANNEL_PROFILE_LIVE_BROADCASTING (1): (Default) Live streaming.
   * - CHANNEL_PROFILE_GAME (2): Gaming.
   * Deprecated:
   * Use CHANNEL_PROFILE_LIVE_BROADCASTING instead.
   * - CHANNEL_PROFILE_CLOUD_GAMING (3): Interaction. The scenario is optimized for latency. Use this
   * profile if the use case requires frequent interactions between users.
   * Deprecated:
   * Use CHANNEL_PROFILE_LIVE_BROADCASTING instead.
   */
  public Integer channelProfile;
  /**
   * Delay (in milliseconds) for sending audio frames. You can use this parameter to set the delay of
   * the audio frames that need to be sent, to ensure audio and video synchronization.
   * To switch off the delay, set the value to 0.
   */
  public Integer audioDelayMs;
  /**
   * The delay in ms for sending media player audio frames. This is used for explicit control of A/V
   * sync. To switch off the delay, set the value to zero.
   */
  public Integer mediaPlayerAudioDelayMs;
  /**
   * (Optional) The token generated on your server for authentication.
   * @note
   * - This parameter takes effect only when calling `updateChannelMediaOptions` or
   * `updateChannelMediaOptionsEx`.
   * - Ensure that the App ID, channel name, and user name used for creating the token are the same as
   * those used by the `create(RtcEngineConfig config)` method for initializing the RTC engine, and those used by the
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` and `joinChannelEx` methods for joining the channel.
   */
  public String token;
  /**
   * Enable media packet encryption.
   * It will be ignored when calling function updateChannelMediaOptions().
   * - true:  Enable media packet encryption.
   * - false: (Default) Do not Enable media packet encryption.
   */
  public Boolean enableBuiltInMediaEncryption;
  /**
   * Whether to publish the sound of a metronome to remote users:
   * - `true`: Publish processed audio frames. Both the local user and remote users can hear the
   * metronome.
   * - `false`: Do not publish the sound of the metronome. Only the local user can hear the metronome.
   */
  public Boolean publishRhythmPlayerTrack;
  /**
   * Whether to enable interactive mode:
   * - `true`: Enable interactive mode. Once this mode is enabled and the user role is set as
   * audience, the user can receive remote video streams with low latency.
   * - `false`:Do not enable interactive mode. If this mode is disabled, the user receives the remote
   * video streams in default settings.
   * @note
   * - This parameter only applies to co-streaming scenarios. The cohosts need to call the
   * `joinChannelEx` method to join the other host's channel as an audience member, and set
   * `isInteractiveAudience` to `true`.
   * - This parameter takes effect only when the user role is `CLIENT_ROLE_AUDIENCE`.
   */
  public Boolean isInteractiveAudience;
  /**
   * The video track ID returned by calling the `createCustomVideoTrack` method. The default value is
   * 0.
   */
  public Integer customVideoTrackId;
  /**
   * Whether the audio stream being published is filtered according to the volume algorithm:
   * - `true`: The audio stream is filtered. If the audio stream filter is not enabled, this setting
   * does not takes effect.
   * - `false`: The audio stream is not filtered.
   * @note If you need to enable this function, contact `support@agora.io`.
   */
  public Boolean isAudioFilterable;
  /**
   * Determines whether to start preview when join channel if canvas have been set.
   * - true: (Default) Start preview when join channel.
   * - false: Do not start preview.
   */
  public Boolean startPreview;
  /**
   * Provides the technical preview functionalities or special customizations by configuring the
   * SDK with JSON options.
   * @technical preview
   */
  public String parameters;

  /**
   * Whether to enable multiple transmisssion paths:
   * - `true`: Enable multiple transmisssion path.
   * - `false`: Dsiable multiple transmisssion path.
   * @note Permissions and system requirements: Android: Android 7.0 or higher (API level 24 or
   * higher), and the ACCESS_NETWORK_STATE and CHANGE_NETWORK_STATE permissions are required.
   * @since 4.6.0
   */
  public Boolean enableMultipath;

  /**
   * Uplink transmission mode. See `MultipathMode`.
   * @note When using this parameter, make sure that `enableMultipath` is set to `true`.
   * @since 4.6.0
   */
  public Integer uplinkMultipathMode;

  /**
   * Downlink transmission mode. See `MultipathMode`.
   * @note When using this parameter, make sure that `enableMultipath` is set to `true`.
   * @since 4.6.0
   */
  public Integer downlinkMultipathMode;

  /**
   * Preferred type of transmission path. See `MultipathType`.
   * @note When using this parameter, make sure that `enableMultipath` is set to `true`.
   * @since 4.6.0
   */
  public Integer preferMultipathType;

  public ChannelMediaOptions() {}

  public ChannelMediaOptions(Integer clientRoleType) {
    this.clientRoleType = clientRoleType;
  }

  @CalledByNative
  public Boolean isPublishCameraTrack() {
    return publishCameraTrack;
  }

  @CalledByNative
  public Boolean isPublishSecondaryCameraTrack() {
    return publishSecondaryCameraTrack;
  }

  @CalledByNative
  public Boolean isPublishThirdCameraTrack() {
    return publishThirdCameraTrack;
  }

  @CalledByNative
  public Boolean isPublishFourthCameraTrack() {
    return publishFourthCameraTrack;
  }

  @CalledByNative
  public Boolean isPublishScreenCaptureVideo() {
    return publishScreenCaptureVideo;
  }

  @CalledByNative
  public Boolean isPublishScreenCaptureAudio() {
    return publishScreenCaptureAudio;
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
  public Boolean isPublishEncodedVideoTrack() {
    return publishEncodedVideoTrack;
  }

  @CalledByNative
  public Boolean isPublishMediaPlayerAudioTrack() {
    return publishMediaPlayerAudioTrack;
  }

  @CalledByNative
  public Boolean isPublishMediaPlayerVideoTrack() {
    return publishMediaPlayerVideoTrack;
  }

  @CalledByNative
  public Boolean isPublishTranscodedVideoTrack() {
    return publishTranscodedVideoTrack;
  }

  @CalledByNative
  public Boolean isPublishMixedAudioTrack() {
    return publishMixedAudioTrack;
  }

  @CalledByNative
  public Boolean isPublishLipSyncTrack() {
    return publishLipSyncTrack;
  }

  @CalledByNative
  public Integer getPublishMediaPlayerId() {
    return publishMediaPlayerId;
  }

  @CalledByNative
  public Boolean isPublishMicrophoneTrack() {
    return publishMicrophoneTrack;
  }

  @CalledByNative
  public Boolean isAutoSubscribeAudio() {
    return autoSubscribeAudio;
  }

  @CalledByNative
  public Boolean isAutoSubscribeVideo() {
    return autoSubscribeVideo;
  }

  @CalledByNative
  public Boolean isStartPreview() {
    return startPreview;
  }

  @CalledByNative
  public Boolean isEnableAudioRecordingOrPlayout() {
    return enableAudioRecordingOrPlayout;
  }

  @CalledByNative
  public Integer getClientRoleType() {
    return clientRoleType;
  }

  @CalledByNative
  public Integer getAudienceLatencyLevel() {
    return audienceLatencyLevel;
  }

  @CalledByNative
  public Integer getDefaultVideoStreamType() {
    return defaultVideoStreamType;
  }

  @CalledByNative
  public Integer getChannelProfile() {
    return channelProfile;
  }

  @CalledByNative
  public Integer getAudioDelayMs() {
    return audioDelayMs;
  }

  @CalledByNative
  public Integer getMediaPlayerAudioDelayMs() {
    return mediaPlayerAudioDelayMs;
  }

  @CalledByNative
  public String getToken() {
    return token;
  }

  @CalledByNative
  public Boolean isEnableBuiltInMediaEncryption() {
    return enableBuiltInMediaEncryption;
  }

  @CalledByNative
  public Boolean getPublishRhythmPlayerTrack() {
    return publishRhythmPlayerTrack;
  }

  @CalledByNative
  public Boolean getIsAudioFilterable() {
    return isAudioFilterable;
  }

  @CalledByNative
  public Integer getPublishCustomAudioTrackId() {
    return publishCustomAudioTrackId;
  }

  @CalledByNative
  public Integer getCustomVideoTrackId() {
    return customVideoTrackId;
  }

  @CalledByNative
  public Boolean isInteractiveAudience() {
    return isInteractiveAudience;
  }

  @CalledByNative
  public String getParameters() {
    return parameters;
  }

  @CalledByNative
  public Boolean isEnableMultipath() {
    return enableMultipath;
  }

  @CalledByNative
  public Integer getUplinkMultipathMode() {
    return uplinkMultipathMode;
  }

  @CalledByNative
  public Integer getDownlinkMultipathMode() {
    return downlinkMultipathMode;
  }

  @CalledByNative
  public Integer getPreferMultipathType() {
    return preferMultipathType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("publishCameraTrack=").append(publishCameraTrack);
    sb.append(" publishSecondaryCameraTrack=").append(publishSecondaryCameraTrack);
    sb.append(" publishThirdCameraTrack=").append(publishThirdCameraTrack);
    sb.append(" publishFourthCameraTrack=").append(publishFourthCameraTrack);
    sb.append(" publishScreenCaptureVideo=").append(publishScreenCaptureVideo);
    sb.append(" publishScreenCaptureAudio=").append(publishScreenCaptureAudio);
    sb.append(" publishCustomAudioTrack=").append(publishCustomAudioTrack);
    sb.append(" publishCustomAudioTrackId=").append(publishCustomAudioTrackId);
    sb.append(" publishCustomVideoTrack=").append(publishCustomVideoTrack);
    sb.append(" publishEncodedVideoTrack=").append(publishEncodedVideoTrack);
    sb.append(" publishMediaPlayerAudioTrack=").append(publishMediaPlayerAudioTrack);
    sb.append(" publishMediaPlayerVideoTrack=").append(publishMediaPlayerVideoTrack);
    sb.append(" publishMixedAudioTrack=").append(publishMixedAudioTrack);
    sb.append(" publishTranscodedVideoTrack=").append(publishTranscodedVideoTrack);
    sb.append(" publishLipSyncdVideoTrack=").append(publishLipSyncTrack);
    sb.append(" publishMediaPlayerId=").append(publishMediaPlayerId);
    sb.append(" publishMicrophoneTrack=").append(publishMicrophoneTrack);
    sb.append(" autoSubscribeAudio=").append(autoSubscribeAudio);
    sb.append(" autoSubscribeVideo=").append(autoSubscribeVideo);
    sb.append(" startPrevie=").append(startPreview);
    sb.append(" clientRoleType=").append(clientRoleType);
    sb.append(" audienceLatencyLevel=").append(audienceLatencyLevel);
    sb.append(" defaultVideoStreamType=").append(defaultVideoStreamType);
    sb.append(" channelProfile=").append(channelProfile);
    sb.append(" audioDelayMs=").append(audioDelayMs);
    sb.append(" enableBuiltInMediaEncryption=").append(enableBuiltInMediaEncryption);
    sb.append(" publishRhythmPlayerTrack=").append(publishRhythmPlayerTrack);
    sb.append(" isAudioFilterable=").append(isAudioFilterable);
    sb.append(" mediaPlayerAudioDelayMs=").append(mediaPlayerAudioDelayMs);
    sb.append(" customVideoTrackId=").append(customVideoTrackId);
    sb.append(" isInteractiveAudience=").append(isInteractiveAudience);
    sb.append(" parameters=").append(parameters);
    sb.append(" enableMultipath=").append(enableMultipath);
    sb.append(" uplinkMultipathMode=").append(uplinkMultipathMode);
    sb.append(" downlinkMultipathMode=").append(downlinkMultipathMode);
    sb.append(" preferMultipathType=").append(preferMultipathType);
    return sb.toString();
  }
}
