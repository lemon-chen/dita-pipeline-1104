package io.agora.rtc2;

import io.agora.base.VideoFrame;
import io.agora.rtc2.SpatialAudioParams;
import io.agora.rtc2.UserInfo;
import io.agora.rtc2.internal.EncryptionConfig;
import io.agora.rtc2.live.LiveTranscoding;
import io.agora.rtc2.video.AgoraVideoFrame;
import io.agora.rtc2.video.ChannelMediaRelayConfiguration;
import io.agora.rtc2.video.ContentInspectConfig;
import io.agora.rtc2.video.SnapshotConfig;
import io.agora.rtc2.video.EncodedVideoFrameInfo;
import io.agora.rtc2.video.VideoCanvas;
import io.agora.rtc2.video.VideoEncoderConfiguration;
import io.agora.rtc2.video.VideoSubscriptionOptions;
import io.agora.rtc2.video.WatermarkConfig;
import io.agora.rtc2.video.WatermarkOptions;
import java.nio.ByteBuffer;

/**
 * @brief The `RtcEngineEx` interface,.
 */
public abstract class RtcEngineEx extends RtcEngine {
  /**
   * @brief Stops or resumes publishing the local audio stream.
   *
   * @details
   * A successful call of this method triggers the `onUserMuteAudio` and `onRemoteAudioStateChanged`
   * callbacks on the remote client.
   *
   * @note This method does not affect any ongoing audio recording, because it does not disable the
   * audio capture device.
   *
   * @param muted Whether to stop publishing the local audio stream:
   * - `true`: Stops publishing the local audio stream.
   * - `false`: (Default) Resumes publishing the local audio stream.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteLocalAudioStreamEx(boolean muted, RtcConnection connection);
  /**
   * @brief Stops or resumes publishing the local video stream.
   *
   * @details
   * A successful call of this method triggers the `onUserMuteVideo` callback on the remote client.
   *
   * @note This method does not affect any ongoing video recording, because it does not disable the
   * camera.
   *
   * @param muted Whether to stop publishing the local video stream.
   * - `true`: Stop publishing the local video stream.
   * - `false`: (Default) Publish the local video stream.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteLocalVideoStreamEx(boolean muted, RtcConnection connection);
  /**
   * @brief Stops or resumes subscribing to the audio streams of all remote users.
   *
   * @details
   * After successfully calling this method, the local user stops or resumes subscribing to the audio
   * streams of all remote users, including the ones join the channel subsequent to this call.
   *
   * @note
   * - Call this method after joining a channel.
   * - If you do not want to subscribe the audio streams of remote users before joining a channel, you
   * can set `autoSubscribeAudio` as `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`.
   *
   * @param muted Whether to stop subscribing to the audio streams of all remote users:
   * - `true`: Stops subscribing to the audio streams of all remote users.
   * - `false`: (Default) Subscribes to the audio streams of all remote users by default.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteAllRemoteAudioStreamsEx(boolean muted, RtcConnection connection);
  /**
   * @brief Stops or resumes subscribing to the video streams of all remote users.
   *
   * @details
   * After successfully calling this method, the local user stops or resumes subscribing to the video
   * streams of all remote users, including all subsequent users.
   *
   * @param muted Whether to stop subscribing to the video streams of all remote users.
   * - `true`: Stop subscribing to the video streams of all remote users.
   * - `false`: (Default) Subscribe to the video streams of all remote users by default.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteAllRemoteVideoStreamsEx(boolean muted, RtcConnection connection);
  /**
   * @brief Stops or resumes receiving the audio stream of a specified user.
   *
   * @details
   *             This method is used to stops or resumes receiving the audio stream of a specified
   * user. You can call this method before or after joining a channel. If a user leaves a channel, the
   * settings in this method become invalid.
   *
   * @param uid The ID of the specified user.
   * @param muted Whether to stop receiving the audio stream of the specified user:
   * - `true`: Stop receiving the audio stream of the specified user.
   * - `false`: (Default) Resume receiving the audio stream of the specified user.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteRemoteAudioStreamEx(int uid, boolean muted, RtcConnection connection);

  /**
   * @brief Stops or resumes receiving the video stream of a specified user.
   *
   * @details
   * This method is used to stop or resume receiving the video stream of a specified user. You can
   * call this method before or after joining a channel. If a user leaves a channel, the settings in
   * this method become invalid.
   *
   * @param uid The user ID of the remote user.
   * @param muted Whether to stop receiving the video stream of the specified user:
   * - `true`: Stop receiving the video stream of the specified user.
   * - `false`: (Default) Resume receiving the video stream of the specified user.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteRemoteVideoStreamEx(int uid, boolean muted, RtcConnection connection);

  /**
   * @brief Sets the video stream type to subscribe to.
   *
   * @details
   * Depending on the default behavior of the sender and the specific settings when calling
   * `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)`, the scenarios for the receiver calling this method are as follows:
   * - The SDK enables low-quality video stream adaptive mode ( `AUTO_SIMULCAST_STREAM` ) on the
   * sender side by default, meaning only the high-quality video stream is transmitted. Only the
   * receiver with the role of the **host**can call this method to initiate a low-quality video stream
   * request. Once the sender receives the request, it starts automatically sending the low-quality
   * video stream. At this point, all users in the channel can call this method to switch to
   * low-quality video stream subscription mode.
   * - If the sender calls `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` and sets `mode` to `DISABLE_SIMULCAST_STREAM`
   * (never send low-quality video stream), then calling this method will have no effect.
   * - If the sender calls `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` and sets `mode` to `ENABLE_SIMULCAST_STREAM`
   * (always send low-quality video stream), both the host and audience receivers can call this method
   * to switch to low-quality video stream subscription mode.
   * The SDK will dynamically adjust the size of the corresponding video stream based on the size of
   * the video window to save bandwidth and computing resources. The default aspect ratio of the
   * low-quality video stream is the same as that of the high-quality video stream. According to the
   * current aspect ratio of the high-quality video stream, the system will automatically allocate the
   * resolution, frame rate, and bitrate of the low-quality video stream.
   *
   * @note If the publisher has already called `setDualStreamModeEx` and set `mode` to
   * `DISABLE_SIMULCAST_STREAM` (never send low-quality video stream), calling this method will not
   * take effect, you should call `setDualStreamModeEx` again on the sending end and adjust the
   * settings.
   *
   * @param uid The user ID.
   * @param streamType The video stream type, see `VideoStreamType`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVideoStreamTypeEx(
      int uid, Constants.VideoStreamType streamType, RtcConnection connection);

  
 /**
   * @brief Sets the video stream type to subscribe to.
   *
   * @details
   * Depending on the default behavior of the sender and the specific settings when calling
   * `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)`, the scenarios for the receiver calling this method are as follows:
   * - The SDK enables low-quality video stream adaptive mode ( `AUTO_SIMULCAST_STREAM` ) on the
   * sender side by default, meaning only the high-quality video stream is transmitted. Only the
   * receiver with the role of the **host**can call this method to initiate a low-quality video stream
   * request. Once the sender receives the request, it starts automatically sending the low-quality
   * video stream. At this point, all users in the channel can call this method to switch to
   * low-quality video stream subscription mode.
   * - If the sender calls `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` and sets `mode` to `DISABLE_SIMULCAST_STREAM`
   * (never send low-quality video stream), then calling this method will have no effect.
   * - If the sender calls `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` and sets `mode` to `ENABLE_SIMULCAST_STREAM`
   * (always send low-quality video stream), both the host and audience receivers can call this method
   * to switch to low-quality video stream subscription mode.
   * The SDK will dynamically adjust the size of the corresponding video stream based on the size of
   * the video window to save bandwidth and computing resources. The default aspect ratio of the
   * low-quality video stream is the same as that of the high-quality video stream. According to the
   * current aspect ratio of the high-quality video stream, the system will automatically allocate the
   * resolution, frame rate, and bitrate of the low-quality video stream.
   *
   * @note If the publisher has already called `setDualStreamModeEx` and set `mode` to
   * `DISABLE_SIMULCAST_STREAM` (never send low-quality video stream), calling this method will not
   * take effect, you should call `setDualStreamModeEx` again on the sending end and adjust the
   * settings.
   *
   * @param uid The user ID.
   * @param streamType The video stream type:
   * - 0: High-quality video stream.
   * - 1: Low-quality video stream.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int setRemoteVideoStreamTypeEx(int uid, int streamType, RtcConnection connection);

  /**
   * Sets the high priority user list and related fallback option for the remotely subscribed video
   * stream based on the network conditions in NASA2.
   *
   * @param uidList The id list of high priority users.
   * @param option The remote subscribe fallback option of high priority users.
   * @param connection {@link RtcConnection} is used to control different connection instances.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setHighPriorityUserListEx(
      int[] uidList, int option, RtcConnection connection);

  /**
   * @brief Sets the blocklist of subscriptions for audio streams.
   *
   * @details
   * You can call this method to specify the audio streams of a user that you do not want to subscribe
   * to.
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - The blocklist is not affected by the setting in `muteRemoteAudioStream`,
   * `muteAllRemoteAudioStreams`, and `autoSubscribeAudio` in `ChannelMediaOptions`.
   * - Once the blocklist of subscriptions is set, it is effective even if you leave the current
   * channel and rejoin the channel.
   * - If a user is added in the allowlist and blocklist at the same time, only the blocklist takes
   * effect.
   *
   * @param uidList The user ID list of users that you do not want to subscribe to.
   * If you want to specify the audio streams of a user that you do not want to subscribe to, add the
   * user ID in this list. If you want to remove a user from the blocklist, you need to call the
   * `setSubscribeAudioBlocklist` method to update the user ID list; this means you only add the `uid`
   * of users that you do not want to subscribe to in the new user ID list.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeAudioBlocklistEx(int[] uidList, RtcConnection connection);

  /**
   * @brief Sets the allowlist of subscriptions for audio streams.
   *
   * @details
   * You can call this method to specify the audio streams of a user that you want to subscribe to.
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - The allowlist is not affected by the setting in `muteRemoteAudioStream`,
   * `muteAllRemoteAudioStreams` and `autoSubscribeAudio` in `ChannelMediaOptions`.
   * - Once the allowlist of subscriptions is set, it is effective even if you leave the current
   * channel and rejoin the channel.
   * - If a user is added in the allowlist and blocklist at the same time, only the blocklist takes
   * effect.
   *
   * @param uidList The user ID list of users that you want to subscribe to.
   * If you want to specify the audio streams of a user for subscription, add the user ID in this
   * list. If you want to remove a user from the allowlist, you need to call the
   * `setSubscribeAudioAllowlist` method to update the user ID list; this means you only add the `uid`
   * of users that you want to subscribe to in the new user ID list.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeAudioAllowlistEx(int[] uidList, RtcConnection connection);

  /**
   * @brief Sets the blocklist of subscriptions for video streams.
   *
   * @details
   * You can call this method to specify the video streams of a user that you do not want to subscribe
   * to.
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - The blocklist is not affected by the setting in `muteRemoteVideoStream`,
   * `muteAllRemoteVideoStreams` and `autoSubscribeAudio` in `ChannelMediaOptions`.
   * - Once the blocklist of subscriptions is set, it is effective even if you leave the current
   * channel and rejoin the channel.
   * - If a user is added in the allowlist and blocklist at the same time, only the blocklist takes
   * effect.
   *
   * @param uidList The user ID list of users that you do not want to subscribe to.
   * If you want to specify the video streams of a user that you do not want to subscribe to, add the
   * user ID of that user in this list. If you want to remove a user from the blocklist, you need to
   * call the `setSubscribeVideoBlocklist` method to update the user ID list; this means you only add
   * the `uid` of users that you do not want to subscribe to in the new user ID list.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeVideoBlocklistEx(int[] uidList, RtcConnection connection);

  /**
   * @brief Sets the allowlist of subscriptions for video streams.
   *
   * @details
   * You can call this method to specify the video streams of a user that you want to subscribe to.
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - The allowlist is not affected by the setting in `muteRemoteVideoStream`,
   * `muteAllRemoteVideoStreams` and `autoSubscribeAudio` in `ChannelMediaOptions`.
   * - Once the allowlist of subscriptions is set, it is effective even if you leave the current
   * channel and rejoin the channel.
   * - If a user is added in the allowlist and blocklist at the same time, only the blocklist takes
   * effect.
   *
   * @param uidList The user ID list of users that you want to subscribe to.
   * If you want to specify the video streams of a user for subscription, add the user ID of that user
   * in this list. If you want to remove a user from the allowlist, you need to call the
   * `setSubscribeVideoAllowlist` method to update the user ID list; this means you only add the `uid`
   * of users that you want to subscribe to in the new user ID list.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeVideoAllowlistEx(int[] uidList, RtcConnection connection);

  /**
   * @brief Sets the video display mode of a specified remote user.
   *
   * @details
   * After initializing the video view of a remote user, you can call this method to update its
   * rendering and mirror modes. This method affects only the video view that the local user sees.
   *
   * @note
   * - Call this method after initializing the remote view by calling the `setupRemoteVideo` method.
   * - During a call, you can call this method as many times as necessary to update the display mode
   * of the video view of a remote user.
   *
   * @param uid The user ID of the remote user.
   * @param renderMode The video display mode of the remote user:
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   * @param mirrorMode The mirror mode of the remote user view:
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. If you use a
   * front camera, the SDK enables the mirror mode by default; if you use a rear camera, the SDK
   * disables the mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enable the mirroring mode of the local view.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disable the mirroring mode of the local view.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - < 0: Failure.
   */
  public abstract int setRemoteRenderModeEx(
      int uid, int renderMode, int mirrorMode, RtcConnection connection);

  /**
   * @brief Initializes the video view of a remote user.
   *
   * @details
   * This method initializes the video view of a remote stream on the local device. It affects only
   * the video view that the local user sees. Call this method to bind the remote video stream to a
   * video view and to set the rendering and mirror modes of the video view.
   * The application specifies the uid of the remote video in the `VideoCanvas` method before the
   * remote user joins the channel.
   * If the remote uid is unknown to the application, set it after the application receives the
   * `onUserJoined` callback. If the Video Recording function is enabled, the Video Recording Service
   * joins the channel as a dummy client, causing other clients to also receive the `onUserJoined`
   * callback. Do not bind the dummy client to the application view because the dummy client does not
   * send any video streams.
   * To unbind the remote user from the view, set the `view` parameter to NULL.
   * Once the remote user leaves the channel, the SDK unbinds the remote user.
   *
   * @note
   * - Call this method after `joinChannelEx`.
   * - To update the rendering or mirror mode of the remote video view during a call, use the
   * `setRemoteRenderModeEx` method.
   *
   * @param remote The remote video view settings. See `VideoCanvas`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setupRemoteVideoEx(VideoCanvas remote, RtcConnection connection);

  /**
   * @brief Sets the video encoder configuration.
   *
   * @details
   * Sets the encoder configuration for the local video. Each configuration profile corresponds to a
   * set of video parameters, including the resolution, frame rate, and bitrate.
   * Call timing: Call this method after `joinChannelEx`.
   *
   * @note The `config` specified in this method is the maximum value under ideal network conditions.
   * If the video engine cannot render the video using the specified `config` due to unreliable
   * network conditions, the parameters further down the list are considered until a successful
   * configuration is found.
   *
   * @param config Video profile. See `VideoEncoderConfiguration`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVideoEncoderConfigurationEx(
      VideoEncoderConfiguration config, RtcConnection connection);

  /**
   * @brief Updates the channel media options after joining the channel.
   *
   * @param options The channel media options. See `ChannelMediaOptions`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The value of a member in `ChannelMediaOptions` is invalid. For example, the token or the
   * user ID is invalid. You need to fill in a valid parameter.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - -8: The internal state of the `RtcEngine` object is wrong. The possible reason is that the
   * user is not in the channel. Agora recommends that you use the `onConnectionStateChanged` callback
   * to see whether the user is in the channel. If you receive the `CONNECTION_STATE_DISCONNECTED` (1)
   * or `CONNECTION_STATE_FAILED` (5) state, the user is not in the channel. You need to call
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join a channel before calling this method.
   */
  public abstract int updateChannelMediaOptionsEx(
      ChannelMediaOptions options, RtcConnection connection);

  /**
   * @brief Joins a channel.
   *
   * @details
   * You can call this method multiple times to join more than one channel. If you want to join the
   * same channel from different devices, ensure that the user IDs are different for all devices.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   * Call timing: Call this method after `create(RtcEngineConfig config)`.
   * In a multi-camera capture scenario, you need to call the `startPreview(Constants.VideoSourceType sourceType)` method after
   * calling this method to set the `sourceType` to `VIDEO_SOURCE_CAMERA_SECONDARY`, to ensure that
   * the second camera captures normally.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onJoinChannelSuccess` and `onConnectionStateChanged` callbacks.
   * - The remote client: The `onUserJoined` callback, if a user joining the channel in the
   * COMMUNICATION profile, or a host joining a channel in the LIVE_BROADCASTING profile.
   * When the connection between the local client and Agora's server is interrupted due to poor
   * network conditions, the SDK tries reconnecting to the server. When the local client successfully
   * rejoins the channel, the SDK triggers the `onRejoinChannelSuccess` callback on the local client.
   *
   * @note
   * If you are already in a channel, you cannot rejoin the channel with the same user ID.
   * Before joining a channel, ensure that the App ID you use to generate a token is the same as that
   * you pass in the `create(RtcEngineConfig config)` method; otherwise, you may fail to join the channel with the
   * token.
   *
   * @param token The token generated on your server for authentication.Note:
   * - (Recommended) If your project has enabled the security mode (using APP ID and Token for
   * authentication), this parameter is required.
   * - If you have only enabled the testing mode (using APP ID for authentication), this parameter is
   * optional. You will automatically exit the channel 24 hours after successfully joining in.
   * - If you need to join different channels at the same time or switch between channels, Agora
   * recommends using a wildcard token so that you don't need to apply for a new token every time
   * joining a channel. See `Secure authentication with tokens`.
   * @param connection The connection information. See `RtcConnection`.
   * @param options The channel media options. See `ChannelMediaOptions`.
   * @param eventHandler The callback class of `RtcEngineEx`. See `IRtcEngineEventHandler`. You can
   * get the callback events of multiple channels through the `eventHandler` object passed in this
   * parameter.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid. For example, the token is invalid, the `uid` parameter is not
   * set to an integer, or the value of a member in `ChannelMediaOptions` is invalid. You need to pass
   * in a valid parameter and join the channel again.
   *   - -3: Fails to initialize the `RtcEngine` object. You need to reinitialize the `RtcEngine`
   * object.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - -8: The internal state of the `RtcEngine` object is wrong. The typical cause is that after
   * calling `startEchoTest` to start a call loop test, you call this method to join the channel
   * without calling `stopEchoTest` to stop the test. You need to call `stopEchoTest` before calling
   * this method.
   *   - -17: The request to join the channel is rejected. The typical cause is that the user is
   * already in the channel. Agora recommends that you use the `onConnectionStateChanged` callback to
   * see whether the user is in the channel. Do not call this method to join the channel unless you
   * receive the `CONNECTION_STATE_DISCONNECTED` (1) state.
   *   - -102: The channel name is invalid. You need to pass in a valid channel name in `channelId` to
   * rejoin the channel.
   *   - -121: The user ID is invalid. You need to pass in a valid user ID in `uid` to rejoin the
   * channel.
   */
  public abstract int joinChannelEx(String token, RtcConnection connection,
      ChannelMediaOptions options, IRtcEngineEventHandler eventHandler);

  /**
   * @brief Leaves a channel.
   *
   * @details
   * After calling this method, the SDK terminates the audio and video interaction, leaves the current
   * channel, and releases all resources related to the session.
   * After calling `joinChannelEx` to join a channel, you must call this method or `leaveChannelEx(RtcConnection connection, LeaveChannelOptions options)`
   * to end the call, otherwise, the next call cannot be started.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onLeaveChannel` callback will be triggered.
   * - The remote client: The `onUserOffline` callback will be triggered after the remote host leaves
   * the channel.
   *
   * @note
   * If you call `destroy()` immediately after calling this method, the SDK does not trigger the
   * `onLeaveChannel` callback.
   * - This method call is asynchronous. When this method returns, it does not necessarily mean that
   * the user has left the channel.
   * - If you call `leaveChannel()` or `leaveChannel(LeaveChannelOptions options)`, you will leave all the channels you
   * have joined by calling `joinChannel(String token, String channelId, String optionalInfo, int uid)`, `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`, or `joinChannelEx`.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int leaveChannelEx(RtcConnection connection);

  /**
   * @brief Sets channel options and leaves the channel.
   *
   * @details
   * After calling this method, the SDK terminates the audio and video interaction, leaves the current
   * channel, and releases all resources related to the session.
   * After calling `joinChannelEx` to join a channel, you must call this method or `leaveChannelEx(RtcConnection connection)`
   * to end the call, otherwise, the next call cannot be started.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onLeaveChannel` callback will be triggered.
   * - The remote client: The `onUserOffline` callback will be triggered after the remote host leaves
   * the channel.
   *
   * @note
   * If you call `destroy()` immediately after calling this method, the SDK does not trigger the
   * `onLeaveChannel` callback.
   * - This method call is asynchronous. When this method returns, it does not necessarily mean that
   * the user has left the channel.
   * - If you call `leaveChannel()` or `leaveChannel(LeaveChannelOptions options)`, you will leave all the channels you
   * have joined by calling `joinChannel(String token, String channelId, String optionalInfo, int uid)`, `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`, or `joinChannelEx`.
   *
   * @param connection The connection information. See `RtcConnection`.
   * @param options Since
   * v4.1.0
   * The options for leaving the channel. See `LeaveChannelOptions`.
   * Note: This parameter only supports the `stopMicrophoneRecording` member in the
   * `LeaveChannelOptions` settings; setting other members does not take effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int leaveChannelEx(RtcConnection connection, LeaveChannelOptions options);

  /* Leaves a channel with the channel ID and user account.
   *
   * @param channelId The channel name. The maximum length of this parameter is 64 bytes. Supported
   *     character scopes are:
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - The space character.
   * - Punctuation characters and other symbols, including: "!", "#", "$", "%", "&", "(", ")", "+",
   * "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]", "^", "_", " {", "}", "|", "~", ",".
   * @param userAccount The user account. The maximum length of this parameter is 255 bytes. Ensure
   *     that you set this parameter and do not set it as null. Supported character scopes are:
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - The space character.
   * - Punctuation characters and other symbols, including: "!", "#", "$", "%", "&", "(", ")", "+",
   * "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]", "^", "_", " {", "}", "|", "~", ",".
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int leaveChannelWithUserAccountEx(String channelId, String userAccount);

  /**
   * Leaves a channel with the channel ID and user account and sets the options for leaving.
   *
   * @param channelId The channel name. The maximum length of this parameter is 64 bytes. Supported
   *     character scopes are:
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - The space character.
   * - Punctuation characters and other symbols, including: "!", "#", "$", "%", "&", "(", ")", "+",
   * "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]", "^", "_", " {", "}", "|", "~", ",".
   * @param userAccount The user account. The maximum length of this parameter is 255 bytes. Ensure
   *     that you set this parameter and do not set it as null. Supported character scopes are:
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - The space character.
   * - Punctuation characters and other symbols, including: "!", "#", "$", "%", "&", "(", ")", "+",
   * "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]", "^", "_", " {", "}", "|", "~", ",".
   * @param options The options for leaving the channel. See {@link
   *     io.agora.rtc2.LeaveChannelOptions LeaveChannelOptions}.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int leaveChannelWithUserAccountEx(
      String channelId, String userAccount, LeaveChannelOptions options);

  /**
   * @brief Enables or disables dual-stream mode on the sender side.
   *
   * @deprecated v4.2.0. This method is deprecated. Use setDualStreamModeEx instead.
   *
   * @details
   * You can call this method to enable or disable the dual-stream mode on the publisher side. Dual
   * streams are a pairing of a high-quality video stream and a low-quality video stream:
   * - High-quality video stream: High bitrate, high resolution.
   * - Low-quality video stream: Low bitrate, low resolution.
   * After you enable dual-stream mode, you can call `setRemoteVideoStreamType(int uid, int streamType)` to choose to
   * receive either the high-quality video stream or the low-quality video stream on the subscriber
   * side.
   *
   * @note This method is applicable to all types of streams from the sender, including but not
   * limited to video streams collected from cameras, screen sharing streams, and custom-collected
   * video streams.
   *
   * @param enabled Whether to enable dual-stream mode:
   * - `true`: Enable dual-stream mode.
   * - `false`: (Default) Disable dual-stream mode.
   * @param streamConfig The configuration of the low-quality video stream. See
   * `SimulcastStreamConfig`.Note: When setting `mode` to `DISABLE_SIMULCAST_STREAM`, setting
   * `streamConfig` will not take effect.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int enableDualStreamModeEx(
      boolean enabled, SimulcastStreamConfig streamConfig, RtcConnection connection);

  /**
   * @brief Sets the dual-stream mode on the sender side.
   *
   * @details
   * The SDK defaults to enabling low-quality video stream adaptive mode ( `AUTO_SIMULCAST_STREAM` )
   * on the sender side, which means the sender does not actively send low-quality video stream. The
   * receiving end with the role of the **host** can initiate a low-quality video stream request by
   * calling `setRemoteVideoStreamTypeEx(int uid, int streamType, RtcConnection connection)`, and upon receiving the request, the sending end
   * automatically starts sending low-quality stream.
   * - If you want to modify this behavior, you can call this method and set `mode` to
   * `DISABLE_SIMULCAST_STREAM` (never send low-quality video streams) or `ENABLE_SIMULCAST_STREAM`
   * (always send low-quality video streams).
   * - If you want to restore the default behavior after making changes, you can call this method
   * again with `mode` set to `AUTO_SIMULCAST_STREAM`.
   *
   * @note
   * The difference and connection between this method and `enableDualStreamModeEx` is as follows:
   * - When calling this method and setting `mode` to DISABLE_SIMULCAST_STREAM, it has the same effect
   * as `enableDualStreamModeEx` `(false)`.
   * - When calling this method and setting `mode` to ENABLE_SIMULCAST_STREAM, it has the same effect
   * as `enableDualStreamModeEx` `(true)`.
   * - Both methods can be called before and after joining a channel. If both methods are used, the
   * settings in the method called later takes precedence.
   *
   * @param mode The mode in which the video stream is sent. See `SimulcastStreamMode`.
   * @param streamConfig The configuration of the low-quality video stream. See
   * `SimulcastStreamConfig`.Note: When setting `mode` to `DISABLE_SIMULCAST_STREAM`, setting
   * `streamConfig` will not take effect.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setDualStreamModeEx(Constants.SimulcastStreamMode mode,
      SimulcastStreamConfig streamConfig, RtcConnection connection);

  /**
   * @brief Sets the simulcast video stream configuration.
   *
   * @technical preview
   *
   * @details
   * This method can be called in scenarios involving multiple channels. You can call the
   * `setSimulcastConfig` method to set video streams with different resolutions for the same video
   * source. The subscribers can call to select which stream layer to receive. The broadcaster can
   * publish up to four layers of video streams: one main stream (highest resolution) and three
   * additional streams of different quality levels. `setRemoteVideoStreamType(int uid, int streamType)`
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   *
   * @param simulcastConfig This configuration includes seven layers, from STREAM_LAYER_1 to
   * STREAM_LOW, with a maximum of three layers enabled simultaneously. See `SimulcastConfig`.
   * @param connection Connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSimulcastConfigEx(
      SimulcastConfig simulcastConfig, RtcConnection connection);

  /**
   * @brief Gets the current connection state of the SDK.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * The current connection state.
   */
  public abstract int getConnectionStateEx(RtcConnection connection);

  /**
   * @brief Agora supports reporting and analyzing customized messages.
   *
   * @details
   * Agora supports reporting and analyzing customized messages. This function is in the beta stage
   * with a free trial. The ability provided in its beta test version is reporting a maximum of 10
   * message pieces within 6 seconds, with each message piece not exceeding 256 bytes and each string
   * not exceeding 100 bytes. To try out this function, contact `support@agora.io` and discuss the
   * format of customized messages with us.
   *
   */
  public abstract int sendCustomReportMessageEx(
      String id, String category, String event, String label, int value, RtcConnection connection);

  /**
   * @brief Sends data stream messages.
   *
   * @details
   * After calling `createDataStreamEx(DataStreamConfig config, RtcConnection connection)`, you can call this method to send data stream messages
   * to all users in the channel.
   * The SDK has the following restrictions on this method:
   * - Each client within the channel can have up to 5 data channels simultaneously, with a total
   * shared packet bitrate limit of 30 KB/s for all data channels.
   * - Each data channel can send up to 60 packets per second, with each packet being a maximum of 1
   * KB.
   * A successful method call triggers the `onStreamMessage` callback on the remote client, from which
   * the remote user gets the stream message. A failed method call triggers the `onStreamMessageError`
   * callback on the remote client.
   *
   * @note
   * - If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   * - Call this method after `joinChannelEx`.
   * - Ensure that you call `createDataStreamEx(DataStreamConfig config, RtcConnection connection)` to create a data channel before calling this
   * method.
   *
   * @param streamId The data stream ID. You can get the data stream ID by calling `createDataStreamEx(DataStreamConfig config, RtcConnection connection)`
   * .
   * @param message The message to be sent.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendStreamMessageEx(int streamId, byte[] message, RtcConnection connection);

  /**
   * @brief Send Reliable message to remote uid in channel.
   *
   * @technical preview
   *
   * @param uid remote user id.
   * @param type Reliable Data Transmission tunnel message type.
   * @param message The sent data.
   * @param connection Connection ID.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendRdtMessageEx(int uid, int type, byte[] message, RtcConnection connection);

  /**
   * @brief Send media control message
   *
   * @technical preview
   *
   * @param uid remote user id. In particular, if the uid is set to 0, it means broadcasting the
   *     message to the entire channel.
   * @param message The sent data, max 1024 Bytes.
   * @param connection Connection ID.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendMediaControlMessageEx(int uid, byte[] message, RtcConnection connection);

  /**
   * @brief Creates a data stream.
   *
   * @details
   * You can call this method to create a data stream and improve the reliability and ordering of data
   * transmission.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: After setting `reliable` to `true`, if the recipient does not receive the data
   * within five seconds, the SDK triggers the `onStreamMessageError` callback and returns an error
   * code.
   *
   * @note Each user can create up to five data streams during the lifecycle of `RtcEngine`. The data
   * stream will be destroyed when leaving the channel, and the data stream needs to be recreated if
   * needed.
   *
   * @param reliable Sets whether the recipients are guaranteed to receive the data stream within five
   * seconds:
   * - `true`: The recipients receive the data from the sender within five seconds. If the recipient
   * does not receive the data within five seconds, the SDK triggers the `onStreamMessageError`
   * callback and returns an error code.
   * - `false`: There is no guarantee that the recipients receive the data stream within five seconds
   * and no error message is reported for any delay or missing data stream.
   * Attention: Please ensure that `reliable` and `ordered` are either both set to`true` or both set
   * to `false`.
   * @param ordered Sets whether the recipients receive the data stream in the sent order:
   * - `true`: The recipients receive the data in the sent order.
   * - `false`: The recipients do not receive the data in the sent order.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - ID of the created data stream, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int createDataStreamEx(
      boolean reliable, boolean ordered, RtcConnection connection);
  /**
   * @brief Creates a data stream.
   *
   * @details
   * Compared to `createDataStreamEx(boolean reliable, boolean ordered, RtcConnection connection)`, this method does not guarantee the reliability of data
   * transmission. If a data packet is not received five seconds after it was sent, the SDK directly
   * discards the data.
   * Call timing: Call this method after `joinChannelEx`.
   *
   * @note
   * Each user can create up to five data streams during the lifecycle of `RtcEngine`. The data stream
   * will be destroyed when leaving the channel, and the data stream needs to be recreated if needed.
   * If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   *
   * @param config The configurations for the data stream. See `DataStreamConfig`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - ID of the created data stream, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int createDataStreamEx(DataStreamConfig config, RtcConnection connection);

  /**
   * @brief Join a channel using a user account and token, and set the media options.
   *
   * @details
   * Before calling this method, if you have not called `registerLocalUserAccount` to register a user
   * account, when you call this method to join a channel, the SDK automatically creates a user
   * account for you. Calling the `registerLocalUserAccount` method to register a user account, and
   * then calling this method to join a channel can shorten the time it takes to enter the channel.
   * Once a user joins the channel, the user subscribes to the audio and video streams of all the
   * other users in the channel by default, giving rise to usage and **billings**. If you want to stop
   * subscribing to the media stream of other users, you can set the `options` parameter or call the
   * corresponding `mute` method.
   * Call timing: Call this method after `create(RtcEngineConfig config)`.
   * Related callbacks: After the user successfully joins the channel, the SDK triggers the following
   * callbacks:
   * - The local client: `onLocalUserRegistered`, `onJoinChannelSuccess` and
   * `onConnectionStateChanged` callbacks.
   * - The remote client: The `onUserJoined` and `onUserInfoUpdated` callbacks if a user joins the
   * channel in the COMMUNICATION profile, or if a host joins the channel in the LIVE_BROADCASTING
   * profile.
   *
   * @note
   * - This method only supports users joining one channel at a time.
   * - Users with different App IDs cannot call each other.
   * - Before joining a channel, ensure that the App ID you use to generate a token is the same as
   * that you pass in the `create(RtcEngineConfig config)` method; otherwise, you may fail to join the channel with the
   * token.
   * To ensure smooth communication, use the same parameter type to identify the user. For example, if
   * a user joins the channel with a UID, then ensure all the other users use the UID too. The same
   * applies to the user account. If a user joins the channel with the Agora Web SDK, ensure that the
   * ID of the user is set to the same parameter type.
   *
   * @param token The token generated on your server for authentication.Note:
   * - (Recommended) If your project has enabled the security mode (using APP ID and Token for
   * authentication), this parameter is required.
   * - If you have only enabled the testing mode (using APP ID for authentication), this parameter is
   * optional. You will automatically exit the channel 24 hours after successfully joining in.
   * - If you need to join different channels at the same time or switch between channels, Agora
   * recommends using a wildcard token so that you don't need to apply for a new token every time
   * joining a channel. See `Secure authentication with tokens`.
   * @param channelId The channel name. This parameter signifies the channel in which users engage in
   * real-time audio and video interaction. Under the premise of the same App ID, users who fill in
   * the same channel ID enter the same channel for audio and video interaction. The string length
   * must be less than 64 bytes. Supported characters (89 characters in total):
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   * @param userAccount The user account. This parameter is used to identify the user in the channel
   * for real-time audio and video engagement. You need to set and manage user accounts yourself and
   * ensure that each user account in the same channel is unique. The maximum length of this parameter
   * is 255 bytes. Ensure that you set this parameter and do not set it as NULL. Supported characters
   * are as follows(89 in total):
   * - The 26 lowercase English letters: a to z.
   * - The 26 uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - Space
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   * @param options The channel media options. See `ChannelMediaOptions`.
   * @param eventHandler The callback class of `RtcEngineEx`. See `IRtcEngineEventHandler`. You can
   * get the callback events of multiple channels through the `eventHandler` object passed in this
   * parameter.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid. For example, the token is invalid, the `uid` parameter is not
   * set to an integer, or the value of a member in `ChannelMediaOptions` is invalid. You need to pass
   * in a valid parameter and join the channel again.
   *   - -3: Fails to initialize the `RtcEngine` object. You need to reinitialize the `RtcEngine`
   * object.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - -8: The internal state of the `RtcEngine` object is wrong. The typical cause is that after
   * calling `startEchoTest` to start a call loop test, you call this method to join the channel
   * without calling `stopEchoTest` to stop the test. You need to call `stopEchoTest` before calling
   * this method.
   *   - -17: The request to join the channel is rejected. The typical cause is that the user is
   * already in the channel. Agora recommends that you use the `onConnectionStateChanged` callback to
   * see whether the user is in the channel. Do not call this method to join the channel unless you
   * receive the `CONNECTION_STATE_DISCONNECTED` (1) state.
   *   - -102: The channel name is invalid. You need to pass in a valid channel name in `channelId` to
   * rejoin the channel.
   *   - -121: The user ID is invalid. You need to pass in a valid user ID in `uid` to rejoin the
   * channel.
   */
  public abstract int joinChannelWithUserAccountEx(String token, String channelId,
      String userAccount, ChannelMediaOptions options, IRtcEngineEventHandler eventHandler);

  /**
   * @brief Gets the user information by passing in the user account.
   *
   * @details
   * After a remote user joins the channel, the SDK gets the UID and user account of the remote user,
   * caches them in a mapping table object, and triggers the `onUserInfoUpdated` callback on the local
   * client. After receiving the callback, you can call this method and pass in the UID to get the
   * user account of the specified user from the `UserInfo` object.
   *
   * @param userAccount The user account.
   * @param userInfo The `UserInfo` object that identifies the user information.
   * - Input value: A `UserInfo` object.
   * - Output: A `UserInfo` object that contains the user account and user ID of the user.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - A `UserInfo` object, if the method call succeeds.
   * - NULL, if the method call fails.
   */
  public abstract int getUserInfoByUserAccountEx(
      String userAccount, UserInfo userInfo, RtcConnection connection);

  /**
   * @brief Gets the user information by passing in the user ID.
   *
   * @details
   * After a remote user joins the channel, the SDK gets the UID and user account of the remote user,
   * caches them in a mapping table object, and triggers the `onUserInfoUpdated` callback on the local
   * client. After receiving the callback, you can call this method and pass in the UID to get the
   * user account of the specified user from the `UserInfo` object.
   *
   * @param uid The user ID.
   * @param userInfo The `UserInfo` object that identifies the user information.
   * - Input value: A `UserInfo` object.
   * - Output: A `UserInfo` object that contains the user account and user ID of the user.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - A `UserInfo` object, if the method call succeeds.
   * - NULL, if the method call fails.
   */
  public abstract int getUserInfoByUidEx(int uid, UserInfo userInfo, RtcConnection connection);

  /**
   * Adjusts the recording volume.
   *
   * @param volume The recording volume, which ranges from 0 to 400:
   * - 0  : Mute the recording volume.
   * - 100: The original volume.
   * - 400: (Maximum) Four times the original volume with signal clipping protection.
   *
   * @param connection The RtcConnection object.
   *
   * @return
   * - 0  : Success.
   * - < 0: Failure.
   */
  public abstract int adjustRecordingSignalVolumeEx(int volume, RtcConnection connection);

  /**
   * Mute or resume recording signal volume.
   *
   * @param muted Determines whether to mute or resume the recording signal volume.
   * -  true: Mute the recording signal volume.
   * - false: (Default) Resume the recording signal volume.
   *
   * @param connection The RtcConnection object.
   *
   * @return
   * - 0  : Success.
   * - < 0: Failure.
   */
  public abstract int muteRecordingSignalEx(boolean muted, RtcConnection connection);

  /**
   * @brief Adjusts the playback signal volume of a specified remote user.
   *
   * @details
   * You can call this method to adjust the playback volume of a specified remote user. To adjust the
   * playback volume of different remote users, call the method as many times, once for each remote
   * user.
   * Call timing: Call this method after `joinChannelEx`.
   *
   * @param uid The user ID of the remote user.
   * @param volume The volume of the user. The value range is [0,400].
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustUserPlaybackSignalVolumeEx(
      int uid, int volume, RtcConnection connection);

  /**
   * @brief Sets the 2D position (the position on the horizontal plane) of the remote user's voice.
   *
   * @details
   * This method sets the voice position and volume of a remote user.
   * When the local user calls this method to set the voice position of a remote user, the voice
   * difference between the left and right channels allows the local user to track the real-time
   * position of the remote user, creating a sense of space. This method applies to massive
   * multiplayer online games, such as Battle Royale games.
   *
   * @note
   * - For the best voice positioning, Agora recommends using a wired headset.
   * - Call this method after joining a channel.
   *
   * @param uid The user ID of the remote user.
   * @param pan The voice position of the remote user. The value ranges from -1.0 to 1.0:
   * - -1.0: The remote voice comes from the left.
   * - 0.0: (Default) The remote voice comes from the front.
   * - 1.0: The remote voice comes from the right.
   * @param gain The volume of the remote user. The value ranges from 0.0 to 100.0. The default value
   * is 100.0 (the original volume of the remote user). The smaller the value, the lower the volume.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVoicePositionEx(
      int uid, double pan, double gain, RtcConnection connection);

  /**
   * @brief Sets options for subscribing to remote video streams.
   *
   * @details
   * When a remote user has enabled dual-stream mode, you can call this method to choose the option
   * for subscribing to the video streams sent by the remote user.
   *
   * @param uid The user ID of the remote user.
   * @param options The video subscription options. See `VideoSubscriptionOptions`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVideoSubscriptionOptionsEx(
      int uid, VideoSubscriptionOptions options, RtcConnection rtcConnection);

  /**
   * Sets the 3D sound position of a remote user.
   * @note
   * - For this method to work, enable stereo panning for remote users by calling the \ref
   * agora::rtc::IRtcEngine::enableSoundPositionIndication "enableSoundPositionIndication" method
   * before joining a channel.
   * - This method requires hardware support. For the best sound positioning, we recommend using a
   * wired headset.
   * - Ensure that you call this method after joining a channel.
   * @param uid The ID of the remote user.
   * @param azimuth
   * @param elevation
   * @param distance
   * @param connection {@link RtcConnection} is used to control different connection instances.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteUserSpatialAudioParamsEx(
      int uid, SpatialAudioParams params, RtcConnection connection);

  /**
   * @brief Adds a watermark image to the local video.
   *
   * @since v2.9.1
   * @deprecated From v4.6.0. We recommend using the {@link
   * RtcEngine#addVideoWatermark(WatermarkConfig) addVideoWatermark}2 method instead.
   *
   * @details
   * This method adds a PNG watermark image to the local video in the live streaming. Once the
   * watermark image is added, all the audience in the channel (CDN audience included), and the
   * capturing device can see and capture it. The Agora SDK supports adding only one watermark image
   * onto a live video stream. The newly added watermark image replaces the previous one.
   * The watermark coordinates are dependent on the settings in the `setVideoEncoderConfigurationEx`
   * method:
   * - If the orientation mode of the encoding video ( `ORIENTATION_MODE` ) is fixed landscape mode or
   * the adaptive landscape mode, the watermark uses the landscape orientation.
   * - If the orientation mode of the encoding video ( `ORIENTATION_MODE` ) is fixed portrait mode or
   * the adaptive portrait mode, the watermark uses the portrait orientation.
   * - When setting the watermark position, the region must be less than the dimensions set in the
   * `setVideoEncoderConfigurationEx` method; otherwise, the watermark image will be cropped.
   *
   * @note
   * - Ensure that you have called `enableVideo` before calling this method.
   * - This method supports adding a watermark image in the PNG file format only. Supported pixel
   * formats of the PNG image are RGBA, RGB, Palette, Gray, and Alpha_gray.
   * - If the dimensions of the PNG image differ from your settings in this method, the image will be
   * cropped or zoomed to conform to your settings.
   * - If you have enabled the local video preview by calling the `startPreview(Constants.VideoSourceType sourceType)` method, you can
   * use the `visibleInPreview` member to set whether or not the watermark is visible in the preview.
   * - If you have enabled the mirror mode for the local video, the watermark on the local video is
   * also mirrored. To avoid mirroring the watermark, Agora recommends that you do not use the mirror
   * and watermark functions for the local video at the same time. You can implement the watermark
   * function in your application layer.
   *
   * @param watermarkUrl The local file path of the watermark image to be added. This method supports
   * adding a watermark image from the local absolute or relative file path.
   * @param options The options of the watermark image to be added. See `WatermarkOptions`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int addVideoWatermarkEx(
      String watermarkUrl, WatermarkOptions options, RtcConnection connection);

  /**
   * @brief Adds a watermark image to the local video.
   *
   * @since v4.6.0
   *
   * @details
   * Applicable scenarios: This method applies to multi-channel scenarios.
   *
   * @param config Watermark configuration. See `WatermarkConfig`.
   * @param connection `RtcConnection` object. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int addVideoWatermarkEx(WatermarkConfig config, RtcConnection connection);

  /**
   * @brief Removes the specified watermark image from the local or remote video stream.
   *
   * @since v4.6.0
   *
   * @details
   * Applicable scenarios: This method applies to multi-channel scenarios.
   *
   * @param id ID of the watermark image to be removed.
   * @param connection Parameters used to control different connection instances. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int removeVideoWatermarkEx(String id, RtcConnection connection);

  /**
   * @brief Removes the watermark image from the video stream.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int clearVideoWatermarkEx(RtcConnection connection);

  /**
   * @brief Enables the reporting of users' volume indication.
   *
   * @details
   * This method enables the SDK to regularly report the volume information to the app of the local
   * user who sends a stream and remote users (three users at most) whose instantaneous volumes are
   * the highest.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: The SDK triggers the `onAudioVolumeIndication` callback according to the
   * interval you set if this method is successfully called and there are users publishing streams in
   * the channel.
   *
   * @param interval Sets the time interval between two consecutive volume indications:
   * - ≤ 0: Disables the volume indication.
   * - > 0: Time interval (ms) between two consecutive volume indications. Ensure this parameter is
   * set to a value greater than 10, otherwise you will not receive the `onAudioVolumeIndication`
   * callback. Agora recommends that this value is set as greater than 100.
   * @param smooth The smoothing factor that sets the sensitivity of the audio volume indicator. The
   * value ranges between 0 and 10. The recommended value is 3. The greater the value, the more
   * sensitive the indicator.
   * @param reportVad - `true`: Enables the voice activity detection of the local user. Once it is
   * enabled, the `vad` parameter of the `onAudioVolumeIndication` callback reports the voice activity
   * status of the local user.
   * - `false`: (Default) Disables the voice activity detection of the local user. Once it is
   * disabled, the `vad` parameter of the `onAudioVolumeIndication` callback does not report the voice
   * activity status of the local user, except for the scenario where the engine automatically detects
   * the voice activity of the local user.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableAudioVolumeIndicationEx(
      int interval, int smooth, boolean reportVad, RtcConnection connection);

  /**
   * @brief Takes a snapshot of a video stream using connection ID.
   *
   * @details
   * This method takes a snapshot of a video stream from the specified user, generates a JPG image,
   * and saves it to the specified path.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: After a successful call of this method, the SDK triggers the `onSnapshotTaken`
   * callback to report whether the snapshot is successfully taken, as well as the details for that
   * snapshot.
   *
   * @note
   * - The method is asynchronous, and the SDK has not taken the snapshot when the method call
   * returns.
   * - When used for local video snapshots, this method takes a snapshot for the video streams
   * specified in `ChannelMediaOptions`.
   * - If the user's video has been preprocessed, for example, watermarked or beautified, the
   * resulting snapshot includes the pre-processing effect.
   *
   * @param connection The connection information. See `RtcConnection`.
   * @param uid The user ID. Set uid as 0 if you want to take a snapshot of the local user's video.
   * @param filePath The local path (including filename extensions) of the snapshot. For example:
   * - Android: `/storage/emulated/0/Android/data/<package name>/files/example.jpg`
   * Attention: Ensure that the path you specify exists and is writable.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int takeSnapshotEx(RtcConnection connection, int uid, String filePath);

  /**
   * @brief Gets a video screenshot of the specified observation point using the connection ID.
   *
   * @details
   * This method takes a snapshot of a video stream from the specified user, generates a JPG image,
   * and saves it to the specified path.
   * Call timing: Call this method after `joinChannelEx`.
   * Related callbacks: After a successful call of this method, the SDK triggers the `onSnapshotTaken`
   * callback to report whether the snapshot is successfully taken, as well as the details for that
   * snapshot.
   *
   * @note
   * - The method is asynchronous, and the SDK has not taken the snapshot when the method call
   * returns.
   * - When used for local video snapshots, this method takes a snapshot for the video streams
   * specified in `ChannelMediaOptions`.
   * - If the user's video has been preprocessed, for example, watermarked or beautified, the
   * resulting snapshot includes the pre-processing effect.
   *
   * @param connection The connection information. See `RtcConnection`.
   * @param uid The user ID. Set uid as 0 if you want to take a snapshot of the local user's video.
   * @param config The configuration of the snaptshot. See `SnapshotConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int takeSnapshotEx(RtcConnection connection, int uid, SnapshotConfig config);

  /**
   * @brief Enables or disables video screenshot and upload.
   *
   * @details
   * This method can take screenshots for multiple video streams and upload them. When video
   * screenshot and upload function is enabled, the SDK takes screenshots and uploads videos sent by
   * local users based on the type and frequency of the module you set in `ContentInspectConfig`.
   * After video screenshot and upload, the Agora server sends the callback notification to your app
   * server in HTTPS requests and sends all screenshots to the third-party cloud storage service.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @note Before calling this method, ensure that you have contacted `technical support` to activate
   * the video screenshot upload service.
   *
   * @param enabled Whether to enalbe video screenshot and upload:
   * - `true`: Enables video screenshot and upload.
   * - `false`: Disables video screenshot and upload.
   * @param config Screenshot and upload configuration. See `ContentInspectConfig`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableContentInspectEx(
      boolean enabled, ContentInspectConfig config, RtcConnection connection);
  /**
   * @brief Starts pushing media streams to a CDN without transcoding.
   *
   * @details
   * Agora recommends that you use the server-side Media Push function. For details, see `Use RESTful
   * API`.
   * You can call this method to push an audio or video stream to the specified CDN address. This
   * method can push media streams to only one CDN address at a time, so if you need to push streams
   * to multiple addresses, call this method multiple times.
   * After you call this method, the SDK triggers the `onRtmpStreamingStateChanged` callback on the
   * local client to report the state of the streaming.
   *
   * @note
   * - Call this method after joining a channel.
   * - Only hosts in the LIVE_BROADCASTING profile can call this method.
   * - If you want to retry pushing streams after a failed push, make sure to call `stopRtmpStream`
   * first, then call this method to retry pushing streams; otherwise, the SDK returns the same error
   * code as the last failed push.
   *
   * @param url The address of Media Push. The format is RTMP or RTMPS. The character length cannot
   * exceed 1024 bytes. Special characters such as Chinese characters are not supported.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The URL or configuration of transcoding is invalid; check your URL and transcoding
   * configurations.
   *   - -7: The SDK is not initialized before calling this method.
   *   - -19: The Media Push URL is already in use; use another URL instead.
   */
  public abstract int startRtmpStreamWithoutTranscodingEx(String url, RtcConnection connection);
  /**
   * @brief Starts Media Push and sets the transcoding configuration.
   *
   * @details
   * Agora recommends that you use the server-side Media Push function. For details, see `Use RESTful
   * API`.
   * You can call this method to push a live audio-and-video stream to the specified CDN address and
   * set the transcoding configuration. This method can push media streams to only one CDN address at
   * a time, so if you need to push streams to multiple addresses, call this method multiple times.
   * After you call this method, the SDK triggers the `onRtmpStreamingStateChanged` callback on the
   * local client to report the state of the streaming.
   *
   * @note
   * - Ensure that you enable the Media Push service before using this function.
   * - Call this method after joining a channel.
   * - Only hosts in the LIVE_BROADCASTING profile can call this method.
   * - If you want to retry pushing streams after a failed push, make sure to call `stopRtmpStreamEx`
   * first, then call this method to retry pushing streams; otherwise, the SDK returns the same error
   * code as the last failed push.
   *
   * @param url The address of Media Push. The format is RTMP or RTMPS. The character length cannot
   * exceed 1024 bytes. Special characters such as Chinese characters are not supported.
   * @param transcoding The transcoding configuration for Media Push. See `LiveTranscoding`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The URL or configuration of transcoding is invalid; check your URL and transcoding
   * configurations.
   *   - -7: The SDK is not initialized before calling this method.
   *   - -19: The Media Push URL is already in use; use another URL instead.
   */
  public abstract int startRtmpStreamWithTranscodingEx(
      String url, LiveTranscoding transcoding, RtcConnection connection);
  /**
   * @brief Updates the transcoding configuration.
   *
   * @details
   * Agora recommends that you use the server-side Media Push function. For details, see `Use RESTful
   * API`.
   * After you start pushing media streams to CDN with transcoding, you can dynamically update the
   * transcoding configuration according to the scenario. The SDK triggers the `onTranscodingUpdated`
   * callback after the transcoding configuration is updated.
   *
   * @param transcoding The transcoding configuration for Media Push. See `LiveTranscoding`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int updateRtmpTranscodingEx(
      LiveTranscoding transcoding, RtcConnection connection);
  /**
   * @brief Stops pushing media streams to a CDN.
   *
   * @details
   * Agora recommends that you use the server-side Media Push function. For details, see `Use RESTful
   * API`.
   * You can call this method to stop the live stream on the specified CDN address. This method can
   * stop pushing media streams to only one CDN address at a time, so if you need to stop pushing
   * streams to multiple addresses, call this method multiple times.
   * After you call this method, the SDK triggers the `onRtmpStreamingStateChanged` callback on the
   * local client to report the state of the streaming.
   *
   * @param url The address of Media Push. The format is RTMP or RTMPS. The character length cannot
   * exceed 1024 bytes. Special characters such as Chinese characters are not supported.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopRtmpStreamEx(String url, RtcConnection connection);
  /**
   * @brief Starts relaying media streams across channels or updates channels for media relay.
   *
   * @since v4.2.0
   *
   * @details
   * The first successful call to this method starts relaying media streams from the source channel to
   * the destination channels. To relay the media stream to other channels, or exit one of the current
   * media relays, you can call this method again to update the destination channels. This feature
   * supports relaying media streams to a maximum of six destination channels.
   * After a successful method call, the SDK triggers the `onChannelMediaRelayStateChanged` callback,
   * and this callback returns the state of the media stream relay. Common states are as follows:
   * - If the `onChannelMediaRelayStateChanged` callback returns `RELAY_STATE_RUNNING` (2) and
   * `RELAY_OK` (0), it means that the SDK starts relaying media streams from the source channel to
   * the destination channel.
   * - If the `onChannelMediaRelayStateChanged` callback returns `RELAY_STATE_FAILURE` (3), an
   * exception occurs during the media stream relay.
   *
   * @note
   * - Call this method after joining the channel.
   * - This method takes effect only when you are a host in a live streaming channel.
   * - The relaying media streams across channels function needs to be enabled by contacting
   * `technical support`.
   * - Agora does not support string user accounts in this API.
   *
   * @param channelMediaRelayConfiguration The configuration of the media stream relay. See
   * `ChannelMediaRelayConfiguration`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -8: Internal state error. Probably because the user is not a broadcaster.
   */
  public abstract int startOrUpdateChannelMediaRelayEx(
      ChannelMediaRelayConfiguration channelMediaRelayConfiguration, RtcConnection connection);
  /**
   * @brief Stops the media stream relay. Once the relay stops, the host quits all the target
   * channels.
   *
   * @details
   * After a successful method call, the SDK triggers the `onChannelMediaRelayStateChanged` callback.
   * If the callback reports `RELAY_STATE_IDLE` (0) and `RELAY_OK` (0), the host successfully stops
   * the relay.
   *
   * @note If the method call fails, the SDK triggers the `onChannelMediaRelayStateChanged` callback
   * with the `RELAY_ERROR_SERVER_NO_RESPONSE` (2) or `RELAY_ERROR_SERVER_CONNECTION_LOST` (8) status
   * code. You can call the `leaveChannel(LeaveChannelOptions options)` method to leave the channel, and the media stream
   * relay automatically stops.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no ongoing channel media relay.
   */
  public abstract int stopChannelMediaRelayEx(RtcConnection connection);
  /**
   * @brief Pauses the media stream relay to all target channels.
   *
   * @deprecated This method is deprecated from v4.2.0. Use `startOrUpdateChannelMediaRelayEx`
   * instead.
   *
   * @details
   * After the cross-channel media stream relay starts, you can call this method to pause relaying
   * media streams to all target channels; after the pause, if you want to resume the relay, call
   * `resumeAllChannelMediaRelay`.
   *
   * @note Call this method after `startOrUpdateChannelMediaRelayEx`.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no ongoing channel media relay.
   */
  public abstract int pauseAllChannelMediaRelayEx(RtcConnection connection);
  /**
   * @brief Resumes the media stream relay to all target channels.
   *
   * @details
   * After calling the `pauseAllChannelMediaRelayEx` method, you can call this method to resume
   * relaying media streams to all destination channels.
   *
   * @note Call this method after `pauseAllChannelMediaRelayEx`.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no paused channel media relay.
   */
  public abstract int resumeAllChannelMediaRelayEx(RtcConnection connection);

  /**
   * @brief Enables tracing the video frame rendering process.
   *
   * @since v4.1.1
   *
   * @details
   * The SDK starts tracing the rendering status of the video frames in the channel from the moment
   * this method is successfully called and reports information about the event through the
   * `onVideoRenderingTracingResult` callback.
   * Applicable scenarios: Agora recommends that you use this method in conjunction with the UI
   * settings (such as buttons and sliders) in your app to improve the user experience. For example,
   * call this method when the user clicks the Join Channel button, and then get the time spent during
   * the video frame rendering process through the `onVideoRenderingTracingResult` callback, so as to
   * optimize the indicators accordingly.
   *
   * @note
   * - If you have not called this method, the SDK tracks the rendering events of the video frames
   * from the moment you call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. You can call this method at an
   * appropriate time according to the actual application scenario to set the starting position for
   * tracking video rendering events.
   * - After the local user leaves the current channel, the SDK automatically tracks the video
   * rendering events from the moment you join a channel.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startMediaRenderingTracingEx(RtcConnection connection);

  /**
   * <p>
   * Provides technical preview functionalities or special customizations by
   * configuring the SDK with JSON options.
   *
   * <p>
   * The JSON options are not public by default. Agora is working on making
   * commonly used JSON options public in a standard way.
   *
   * @param parameters Parameter to be set as a JSON string in the specified
   *                   format.
   * @param connection {@link RtcConnection} is used to control different connection instances.
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  public abstract int setParametersEx(String parameters, RtcConnection connection);

  /**
   * @brief Gets the call ID with the connection ID.
   *
   * @details
   * When a user joins a channel on a client, a `callId` is generated to identify the call from the
   * client. You can call this method to get `callId`, and pass it in when calling methods such as
   * `rate` and `complain`.
   * Call timing: Call this method after joining a channel.
   *
   * @param connection The connection information. See `RtcConnection`.
   *
   */
  public abstract String getCallIdEx(RtcConnection connection);

  /**
   * Adds the IRtcEngineEventHandler.
   *
   * @param handler The IRtcEngineEventHandler instance.
   * @param connection {@link RtcConnection} is used to control different connection instances.
   */
  public abstract void addHandlerEx(IRtcEngineEventHandler handler, RtcConnection connection);

  /**
   * Removes the IRtcEngineEventHandler.
   *
   * @param handler The IRtcEngineEventHandler instance.
   * @param connection {@link RtcConnection} is used to control different connection instances.
   */
  public abstract void removeHandlerEx(IRtcEngineEventHandler handler, RtcConnection connection);

  /**
   * @brief Enables or disables the built-in encryption.
   *
   * @details
   * After the user leaves the channel, the SDK automatically disables the built-in encryption. To
   * enable the built-in encryption, call this method before the user joins the channel again.
   * Applicable scenarios: Scenarios with higher security requirements.
   * Call timing: Call this method before joining a channel.
   *
   * @note
   * - All users within the same channel must set the same encryption configurations when calling this
   * method.
   * - If you enable the built-in encryption, you cannot use the Media Push function.
   *
   * @param enabled Whether to enable built-in encryption:
   * - true: Enable the built-in encryption.
   * - false: (Default) Disable the built-in encryption.
   * @param config Built-in encryption configurations. See `EncryptionConfig`.
   * @param connection The connection information. See `RtcConnection`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableEncryptionEx(
      boolean enabled, EncryptionConfig config, RtcConnection connection);

  /**
   * Send audio metadata.
   * @since v4.3.1
   * @param metadata Audio Metadata.
   * @param connection {@link RtcConnection} is used to control different connection instances.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   * @technical preview
   */
  public abstract int sendAudioMetadataEx(byte[] metadata, RtcConnection connection);

  /**
   * @brief Preloads a specified sound effect to the channel.
   *
   * @since v4.6.0
   *
   * @details
   * Each time you call this method, you can only preload one sound effect file into memory. If you
   * need to preload multiple sound files, please call this method multiple times. After preloading is
   * complete, you can call `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)` to play the preloaded sound effects, or call
   * `playAllEffects` to play all preloaded sound effects.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   *
   * @note
   * - To ensure a smooth experience, the size of sound effect files should not exceed the limit.
   * - Agora recommends that you call this method before joining a channel.
   *
   * @param connection Connection information. See `RtcConnection`.
   * @param soundId The audio effect ID.
   * @param filePath The absolute path of the local file or the URL of the online file. Supported
   * audio formats include: mp3, mp4, m4a, aac, 3gp, mkv and wav.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int preloadEffectEx(RtcConnection connection, int soundId, String filePath);

  /**
   * @brief Preloads a specified sound effect to a channel.
   *
   * @since v4.6.0
   *
   * @details
   * Each time you call this method, you can only preload one sound effect file into memory. If you
   * need to preload multiple sound files, please call this method multiple times. After preloading is
   * complete, you can call `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)` to play the preloaded sound effects, or call
   * `playAllEffects` to play all preloaded sound effects.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   *
   * @note
   * - To ensure a smooth experience, the size of sound effect files should not exceed the limit.
   * - Agora recommends that you call this method before joining a channel.
   *
   * @param connection Connection information. See `RtcConnection`.
   * @param soundId The audio effect ID.
   * @param filePath The absolute path of the local file or the URL of the online file. Supported
   * audio formats include: mp3, mp4, m4a, aac, 3gp, mkv and wav.
   * @param startPos The playback position (ms) of the audio effect file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int preloadEffectEx(
      RtcConnection connection, int soundId, String filePath, int startPos);

  /**
   * @brief Plays a specified sound effect in the channel.
   *
   * @since v4.6.0
   *
   * @details
   * This method allows you to set whether to publish sound effects in a channel. In order to play
   * multiple sound files simultaneously, simply call the method multiple times with different
   * `soundId` and `filePath` parameters. After calling `preloadEffect` to preload the sound effect,
   * you can call this method to play the specified sound effect to all users in the channel. Each
   * call to this method can only play one sound effect. To play multiple sound effects
   * simultaneously, please call this method multiple times.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   *
   * @note
   * - Agora recommends not playing more than three sound effects at the same time.
   * - The sound effect ID and file path in this method must be consistent with those in the
   * `preloadEffect` method.
   *
   * @param connection Connection information. See `RtcConnection`.
   * @param soundId The audio effect ID.
   * @param filePath The absolute path of the local file or the URL of the online file. Supported
   * audio formats: mp3, mp4, m4a, aac, 3gp, mkv and wav.
   * @param loopCount Number of times the sound effect to be looped:
   * - `-1`: Loop infinitely until calling `stopEffect` or `stopAllEffects`.
   * - `0`: Play once.
   * - `1`: Play twice.
   * @param pitch The pitch of the audio effect. The range is from 0.5 to 2.0, with a default value of
   * 1.0 (original pitch). The lower the value, the lower the pitch.
   * @param pan The spatial position of the audio effect. The range of values is from -1.0 to 1.0:
   * - `-1.0`: The audio effect is heard on the left of the user.
   * - `0.0`: The audio effect is heard in front of the user.
   * - `1.0`: The audio effect is heard on the right of the user.
   * @param gain The volume of the audio effect. The value range is from 0.0 to 100.0, with a default
   * value of 100 (original volume). The smaller the value, the lower the volume.
   * @param publish Whether to publish the audio effect in the channel:
   * - `true`: Publish the audio effect in the channel.
   * - `false`: Do not publish the audio effect in the channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int playEffectEx(RtcConnection connection, int soundId, String filePath,
      int loopCount, double pitch, double pan, double gain, boolean publish);

  /**
   * @brief Plays a specified sound effect in a channel.
   *
   * @since v4.6.0
   *
   * @details
   * You can call this method to play a specified sound effect to all users in the channel. Each call
   * to this method can only play one sound effect. To play multiple sound effects simultaneously,
   * please call this method multiple times. This method allows you to set whether to publish sound
   * effects in a channel. In order to play multiple sound files simultaneously, simply call the
   * method multiple times with different `soundId` and `filePath` parameters.
   * Applicable scenarios: This method can be called in scenarios involving multiple channels.
   *
   * @note
   * - Agora recommends not playing more than three sound effects at the same time.
   * - The sound effect ID and file path in this method must be consistent with those in the
   * `preloadEffect` method.
   *
   * @param connection Connection information. See `RtcConnection`.
   * @param soundId The audio effect ID.
   * @param filePath The absolute path of the local file or the URL of the online file. Supported
   * audio formats include: mp3, mp4, m4a, aac, 3gp, mkv and wav.
   * @param loopCount Number of times the sound effect to be looped:
   * - `-1`: Loop infinitely until calling `stopEffect` or `stopAllEffects`.
   * - `0`: Play once.
   * - `1`: Play twice.
   * @param pitch The pitch of the audio effect. The range is from 0.5 to 2.0, with a default value of
   * 1.0 (original pitch). The lower the value, the lower the pitch.
   * @param pan The spatial position of the audio effect. The range of values is from -1.0 to 1.0:
   * - `-1.0`: The audio effect is heard on the left of the user.
   * - `0.0`: The audio effect is heard in front of the user.
   * - `1.0`: The audio effect is heard on the right of the user.
   * @param gain The volume of the audio effect. The value range is from 0.0 to 100.0, with a default
   * value of 100 (original volume). The smaller the value, the lower the volume.
   * @param publish Whether to publish the audio effect in the channel:
   * - `true`: Publish the audio effect in the channel.
   * - `false`: Do not publish the audio effect in the channel.
   * @param startPos The playback position (ms) of the audio effect file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int playEffectEx(RtcConnection connection, int soundId, String filePath,
      int loopCount, double pitch, double pan, double gain, boolean publish, int startPos);
}
