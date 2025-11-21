package io.agora.rtc2;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.view.SurfaceView;
import android.view.TextureView;

import androidx.annotation.Nullable;

import io.agora.base.VideoFrame;
import io.agora.mediaplayer.IMediaPlayer;
import io.agora.mediaplayer.IMediaPlayerCacheManager;
// import io.agora.base.internal.video.VideoEncoderUtils;
import io.agora.rtc2.ClientRoleOptions;
import io.agora.rtc2.EncodedVideoTrackOptions;
import io.agora.rtc2.SpatialAudioParams;
import io.agora.rtc2.audio.AdvancedAudioOptions;
import io.agora.rtc2.audio.AgoraRhythmPlayerConfig;
import io.agora.rtc2.audio.AudioTrackConfig;
import io.agora.rtc2.audio.IAudioSpectrumObserver;
import io.agora.rtc2.internal.AudioEncodedFrameObserverConfig;
import io.agora.rtc2.internal.AudioRecordingConfiguration;
import io.agora.rtc2.internal.EncryptionConfig;
import io.agora.rtc2.internal.LastmileProbeConfig;
import io.agora.rtc2.internal.RtcEngineImpl;
import io.agora.rtc2.live.LiveInjectStreamConfig;
import io.agora.rtc2.live.LiveTranscoding;
import io.agora.rtc2.proxy.LocalAccessPointConfiguration;
import io.agora.rtc2.video.AgoraFocalLengthInfo;
import io.agora.rtc2.video.AgoraImage;
import io.agora.rtc2.video.AgoraVideoFrame;
import io.agora.rtc2.video.BeautyOptions;
import io.agora.rtc2.video.FaceShapeBeautyOptions;
import io.agora.rtc2.video.FaceShapeAreaOptions;
import io.agora.rtc2.video.FilterEffectOptions;
import io.agora.rtc2.video.CameraCapturerConfiguration;
import io.agora.rtc2.video.ChannelMediaRelayConfiguration;
import io.agora.rtc2.video.CodecCapInfo;
import io.agora.rtc2.video.ColorEnhanceOptions;
import io.agora.rtc2.video.ContentInspectConfig;
import io.agora.rtc2.video.SnapshotConfig;
import io.agora.rtc2.video.EncodedVideoFrameInfo;
import io.agora.rtc2.video.IFaceInfoObserver;
import io.agora.rtc2.video.IVideoEncodedFrameObserver;
import io.agora.rtc2.video.IVideoFrameObserver;
import io.agora.rtc2.video.ImageTrackOptions;
import io.agora.rtc2.video.LowLightEnhanceOptions;
import io.agora.rtc2.video.SegmentationProperty;
import io.agora.rtc2.video.VideoCanvas;
import io.agora.rtc2.video.VideoCompositingLayout;
import io.agora.rtc2.video.VideoDenoiserOptions;
import io.agora.rtc2.video.VideoEncoderConfiguration;
import io.agora.rtc2.video.VideoSubscriptionOptions;
import io.agora.rtc2.video.VirtualBackgroundSource;
import io.agora.rtc2.video.WatermarkConfig;
import io.agora.rtc2.video.WatermarkOptions;
import io.agora.rtc2.IVideoEffectObject;
import io.agora.utils2.internal.DeviceUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Main interface class of the Agora Native SDK.
 *
 * Call the methods of this class to use all the functionalities of the SDK.
 * Agora recommends calling the {@link RtcEngine} API methods in the same thread instead
 * of in multiple threads. In previous versions, this class was named
 * AgoraAudio, and was renamed to {@link RtcEngine} from version 1.0.
 *
 */
public abstract class RtcEngine {
  protected static RtcEngineImpl mInstance = null;

  /**
   * @brief Creates and initializes `RtcEngine`.
   *
   * @details
   * All called methods provided by the `RtcEngine` class are executed asynchronously. Agora
   * recommends calling these methods in the same thread.
   *
   * @note
   * - Before calling other APIs, you must call this method to create the `RtcEngine` object.
   * - You can create the `RtcEngine` instance either by calling this method or by calling `create(RtcEngineConfig config)`
   * . The difference between `create(RtcEngineConfig config)` and this method is that `create(RtcEngineConfig config)` supports
   * more configurations when creating the `RtcEngine` instance, for example, specifying the region
   * for connection and setting the log files.
   * - The SDK supports creating only one `RtcEngine` instance for an app.
   *
   * @param context The context of Android Activity.
   * @param appId The App ID issued by Agora for your project. Only users in apps with the same App ID
   * can join the same channel and communicate with each other. An App ID can only be used to create
   * one `RtcEngine` instance. To change your App ID, call `destroy()` to destroy the current
   * `RtcEngine` instance, and then create a new one.
   * @param handler The event handler for `RtcEngine`. See `IRtcEngineEventHandler`.
   *
   * @return
   * - Returns an initialized `RtcEngine` object if the method call succeeds.
   * - The method call fails and an exception is thrown, you need to catch the exception and handle
   * it.
   */
  public static synchronized RtcEngine create(
      Context context, String appId, IRtcEngineEventHandler handler) throws Exception {
    long startTs = System.nanoTime();
    if (context == null || !RtcEngineImpl.initializeNativeLibs())
      return null;

    RtcEngineConfig config = new RtcEngineConfig();
    config.mContext = context;
    config.mAppId = appId;
    config.mEventHandler = handler;

    if (mInstance == null) {
      mInstance = new RtcEngineImpl(config);
      mInstance.recordCreateEngineTimeStamp(startTs, System.nanoTime());
    } else {
      mInstance.reinitialize(config);
    }
    return mInstance;
  }

  /**
   * @brief Creates and initializes `RtcEngine`.
   *
   * @details
   * You can create the `RtcEngine` instance either by calling this method or by calling `create(Context context, String appId, IRtcEngineEventHandler handler)`
   * . The difference between `create(Context context, String appId, IRtcEngineEventHandler handler)` and this method is that this method supports more
   * configurations when creating the `RtcEngine` instance, for example, specifying the region for
   * connection and setting the log files.
   * Call timing: Before calling other APIs, you must call this method to create the `RtcEngine`
   * object.
   *
   * @note
   * The SDK supports creating only one `RtcEngine` instance for an app.
   * All called methods provided by the `RtcEngine` class are executed asynchronously. Agora
   * recommends calling these methods in the same thread.
   *
   * @param config Configurations for the `RtcEngine` instance. See `RtcEngineConfig`.
   *
   * @return
   * - Returns an initialized `RtcEngine` object if the method call succeeds.
   * - The method call fails and an exception is thrown, you need to catch the exception and handle
   * it.
   */
  public static synchronized RtcEngine create(RtcEngineConfig config) throws Exception {
    long startTs = System.nanoTime();
    if (config == null || config.mContext == null
        || !RtcEngineImpl.initializeNativeLibs(config.mNativeLibPath))
      return null;
    if (mInstance == null) {
      mInstance = new RtcEngineImpl(config);
      mInstance.recordCreateEngineTimeStamp(startTs, System.nanoTime());
    } else {
      mInstance.reinitialize(config);
    }
    return mInstance;
  }

  /**
   * @brief Releases the `RtcEngine` instance.
   *
   * @details
   * This method releases all resources used by the Agora SDK. Use this method for apps in which users
   * occasionally make voice or video calls. When users do not make calls, you can free up resources
   * for other operations.
   * After a successful method call, you can no longer use any method or callback in the SDK anymore.
   * If you want to use the real-time communication functions again, you must call `create(RtcEngineConfig config)` to
   * create a new `RtcEngine` instance.
   *
   * @note Agora does not recommend you calling `destroy()` in any callback of the SDK.
   * Otherwise, the SDK cannot release the resources until the callbacks return results, which may
   * result in a deadlock.
   *
   */
  public static synchronized void destroy() {
    destroy(null);
  }

  /**
   * @brief Destroys the `RtcEngine` instance and releases related resources.
   *
   * @since v4.6.0
   *
   * @details
   * When you no longer need real-time communication, call this method to release the `RtcEngine`
   * instance and its related resources, so that the released resources can be used for other
   * operations. It is recommended for scenarios where users make voice or video interactions.
   *
   * @param callback To configure the synchronous or asynchronous destruction of the engine:
   * - Non-`NULL`：Asynchronous destruction. The method returns immediately, while the engine resources
   * might not be fully released. The `onEngineReleased` callback is triggered after the engine is
   * destroyed.
   * - `NULL`：Synchronous destruction. The method returns only after the engine resources are fully
   * released. See `IRtcEngineReleaseCallback`.
   *
   */
  public static synchronized void destroy(@Nullable IRtcEngineReleaseCallback callback) {
    if (mInstance == null)
      return;
    mInstance.doDestroy(callback);
    mInstance = null;
    System.gc();
  }

  /**
   * @brief Sets the channel profile.
   *
   * @details
   * You can call this method to set the channel profile. The SDK adopts different optimization
   * strategies for different channel profiles. For example, in a live streaming scenario, the SDK
   * prioritizes video quality. After initializing the SDK, the default channel profile is the live
   * streaming profile.
   * Call timing: Call this method before joining a channel.
   *
   * @note
   * To ensure the quality of real-time communication, Agora recommends that all users in a channel
   * use the same channel profile.
   * In different channel scenarios, the default audio routing of the SDK is different. See
   * `setDefaultAudioRouteToSpeakerphone`.
   *
   * @param profile The channel profile.
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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid.
   *   - -7: The SDK is not initialized.
   */
  public abstract int setChannelProfile(int profile);

  /**
   * @brief Sets the client role.
   *
   * @details
   * By default,the SDK sets the user role as audience. You can call this method to set the user role
   * as host. The user role ( `roles` ) determines the users' permissions at the SDK level, including
   * whether they can publish audio and video streams in a channel.
   * Call timing: You can call this method either before or after joining a channel.
   * If you call this method to set the user role as the host before joining the channel and set the
   * local video property through the `setupLocalVideo` method, the local video preview is
   * automatically enabled when the user joins the channel.
   * If you call this method to set the user role after joining a channel, the SDK will automatically
   * call the `muteLocalAudioStream` and `muteLocalVideoStream` method to change the state for
   * publishing audio and video streams.
   * Related callbacks: If you call this method to switch the user role after joining the channel, the
   * SDK triggers the following callbacks:
   * - Triggers `onClientRoleChanged` on the local client.Note: Calling this method before joining a
   * channel and set the `role` to `AUDIENCE` will trigger this callback as well.
   * - Triggers `onUserJoined` or `onUserOffline` on the remote client.
   * If you call this method to set the user role after joining a channel but encounter a failure, the
   * SDK trigger the `onClientRoleChangeFailed` callback to report the reason for the failure and the
   * current user role.
   *
   * @note
   * When calling this method before joining a channel and setting the user role to `BROADCASTER`, the
   * `onClientRoleChanged` callback will not be triggered on the local client.
   * Calling this method before joining a channel and set the `role` to `AUDIENCE` will trigger this
   * callback as well.
   *
   * @param role The user role:
   * - CLIENT_ROLE_BROADCASTER (1): Host.
   * - CLIENT_ROLE_AUDIENCE (2): Audience.
   * Note: If you set the user role as an audience member, you cannot publish audio and video streams
   * in the channel. If you want to publish media streams in a channel during live streaming, ensure
   * you set the user role as broadcaster.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -7: The SDK is not initialized.
   */
  public abstract int setClientRole(int role);

  /**
   * @brief Sets the user role and the audience latency level in a live streaming scenario.
   *
   * @details
   * By default,the SDK sets the user role as audience. You can call this method to set the user role
   * as host. The user role ( `roles` ) determines the users' permissions at the SDK level, including
   * whether they can publish audio and video streams in a channel.
   * The difference between this method and `setClientRole(int role)` is that, this method supports
   * setting the `audienceLatencyLevel`. `audienceLatencyLevel` needs to be used together with `role`
   * to determine the level of service that users can enjoy within their permissions. For example, an
   * audience member can choose to receive remote streams with low latency or ultra-low latency.
   * Call timing: You can call this method either before or after joining a channel.
   * If you call this method to set the user role as the host before joining the channel and set the
   * local video property through the `setupLocalVideo` method, the local video preview is
   * automatically enabled when the user joins the channel.
   * If you call this method to set the user role after joining a channel, the SDK will automatically
   * call the `muteLocalAudioStream` and `muteLocalVideoStream` method to change the state for
   * publishing audio and video streams.
   * Related callbacks: If you call this method to switch the user role after joining the channel, the
   * SDK triggers the following callbacks:
   * - Triggers `onClientRoleChanged` on the local client.Note: Calling this method before joining a
   * channel and set the `role` to `AUDIENCE` will trigger this callback as well.
   * - Triggers `onUserJoined` or `onUserOffline` on the remote client.
   * If you call this method to set the user role after joining a channel but encounter a failure, the
   * SDK trigger the `onClientRoleChangeFailed` callback to report the reason for the failure and the
   * current user role.
   *
   * @note
   * When the user role is set to host, the audience latency level can only be set to
   * AUDIENCE_LATENCY_LEVEL_ULTRA_LOW_LATENCY.
   * When calling this method before joining a channel and setting the `role` to `BROADCASTER`, the
   * `onClientRoleChanged` callback will not be triggered on the local client.
   * Calling this method before joining a channel and set the `role` to `AUDIENCE` will trigger this
   * callback as well.
   *
   * @param role The user role.
   * - CLIENT_ROLE_BROADCASTER (1): Host. A host can both send and receive streams.
   * - CLIENT_ROLE_AUDIENCE (2): (Default) Audience. An audience member can only receive streams.
   * Note: If you set the user role as an audience member, you cannot publish audio and video streams
   * in the channel. If you want to publish media streams in a channel during live streaming, ensure
   * you set the user role as broadcaster.
   * @param options The detailed options of a user, including the user level. See `ClientRoleOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -5: The request is rejected.
   *   - -7: The SDK is not initialized.
   */
  public abstract int setClientRole(int role, ClientRoleOptions options);

  /**
   * @brief Reports customized messages.
   *
   * @details
   * Agora supports reporting and analyzing customized messages. This function is in the beta stage
   * with a free trial. The ability provided in its beta test version is reporting a maximum of 10
   * message pieces within 6 seconds, with each message piece not exceeding 256 bytes and each string
   * not exceeding 100 bytes. To try out this function, contact `support@agora.io` and discuss the
   * format of customized messages with us.
   *
   */
  public abstract int sendCustomReportMessage(
      String id, String category, String event, String label, int value);

  /**
   * @brief Preloads a channel with `token`, `channelName`, and `optionalUid`.
   *
   * @details
   * When audience members need to switch between different channels frequently, calling the method
   * can help shortening the time of joining a channel, thus reducing the time it takes for audience
   * members to hear and see the host.
   * If you join a preloaded channel, leave it and want to rejoin the same channel, you do not need to
   * call this method unless the token for preloading the channel expires.
   * Call timing: To improve the user experience of preloading channels, Agora recommends that before
   * joining the channel, calling this method as early as possible once confirming the channel name
   * and user information.
   *
   * @note
   * - When calling this method, ensure you set the user role as audience and do not set the audio
   * scenario as `AUDIO_SCENARIO_CHORUS`, otherwise, this method does not take effect.
   * - You also need to make sure that the channel name, user ID and token passed in for preloading
   * are the same as the values passed in when joinning the channel, otherwise, this method does not
   * take effect.
   * - One `RtcEngine` instance supports preloading 20 channels at most. When exceeding this limit,
   * the latest 20 preloaded channels take effect.
   * Failing to preload a channel does not mean that you can't join a channel, nor will it increase
   * the time of joining a channel.
   *
   * @param token The token generated on your server for authentication. See .When the token for
   * preloading channels expires, you can update the token based on the number of channels you
   * preload.
   * - When preloading one channel, calling this method to pass in the new token.
   * - When preloading more than one channels:
   *   - If you use a wildcard token for all preloaded channels, call `updatePreloadChannelToken` to
   * update the token.Note: When generating a wildcard token, ensure the user ID is not set as 0. See
   * `Secure authentication with tokens`.
   *   - If you use different tokens to preload different channels, call this method to pass in your
   * user ID, channel name and the new token.
   * @param channelName The channel name that you want to preload. This parameter signifies the
   * channel in which users engage in real-time audio and video interaction. Under the premise of the
   * same App ID, users who fill in the same channel ID enter the same channel for audio and video
   * interaction. The string length must be less than 64 bytes. Supported characters (89 characters in
   * total):
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   * @param optionalUid The user ID. This parameter is used to identify the user in the channel for
   * real-time audio and video interaction. You need to set and manage user IDs yourself, and ensure
   * that each user ID in the same channel is unique. This parameter is a 32-bit signed integer. The
   * value range is from -2^31 to 2^31-1. If the user ID is not assigned (or set to 0), the SDK assigns
   * a random user ID and `onJoinChannelSuccess` returns it in the callback. Your application must
   * record and maintain the returned user ID, because the SDK does not do so.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - -102: The channel name is invalid. You need to pass in a valid channel name and join the
   * channel again.
   */
  public abstract int preloadChannel(String token, String channelName, int optionalUid);

  /**
   * @brief Preloads a channel with `token`, `channelName`, and `userAccount`.
   *
   * @details
   * When audience members need to switch between different channels frequently, calling the method
   * can help shortening the time of joining a channel, thus reducing the time it takes for audience
   * members to hear and see the host.
   * If you join a preloaded channel, leave it and want to rejoin the same channel, you do not need to
   * call this method unless the token for preloading the channel expires.
   * Call timing: To improve the user experience of preloading channels, Agora recommends that before
   * joining the channel, calling this method as early as possible once confirming the channel name
   * and user information.
   *
   * @note
   * - When calling this method, ensure you set the user role as audience and do not set the audio
   * scenario as `AUDIO_SCENARIO_CHORUS`, otherwise, this method does not take effect.
   * - You also need to make sure that the User Account, channel ID and token passed in for preloading
   * are the same as the values passed in when joining the channel, otherwise, this method does not
   * take effect.
   * - One `RtcEngine` instance supports preloading 20 channels at most. When exceeding this limit,
   * the latest 20 preloaded channels take effect.
   * Failing to preload a channel does not mean that you can't join a channel, nor will it increase
   * the time of joining a channel.
   *
   * @param token The token generated on your server for authentication. See .When the token for
   * preloading channels expires, you can update the token based on the number of channels you
   * preload.
   * - When preloading one channel, calling this method to pass in the new token.
   * - When preloading more than one channels:
   *   - If you use a wildcard token for all preloaded channels, call `updatePreloadChannelToken` to
   * update the token.Note: When generating a wildcard token, ensure the user ID is not set as 0. See
   * `Secure authentication with tokens`.
   *   - If you use different tokens to preload different channels, call this method to pass in your
   * user ID, channel name and the new token.
   * @param channelName The channel name that you want to preload. This parameter signifies the
   * channel in which users engage in real-time audio and video interaction. Under the premise of the
   * same App ID, users who fill in the same channel ID enter the same channel for audio and video
   * interaction. The string length must be less than 64 bytes. Supported characters (89 characters in
   * total):
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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid. For example, the User Account is empty. You need to pass in a
   * valid parameter and join the channel again.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - -102: The channel name is invalid. You need to pass in a valid channel name and join the
   * channel again.
   */
  public abstract int preloadChannelWithUserAccount(
      String token, String channelName, String userAccount);

  /**
   * @brief Updates the wildcard token for preloading channels.
   *
   * @details
   * You need to maintain the life cycle of the wildcard token by yourself. When the token expires,
   * you need to generate a new wildcard token and then call this method to pass in the new token.
   * Applicable scenarios: In scenarios involving multiple channels, such as switching between
   * different channels, using a wildcard token means users do not need to apply for a new token every
   * time joinning a new channel, which can save users time for switching channels and reduce the
   * pressure on your token server.
   *
   * @param token The new token.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid. For example, the token is invalid. You need to pass in a valid
   * parameter and join the channel again.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   */
  public abstract int updatePreloadChannelToken(String token);

  /**
   * @brief Joins a channel.
   *
   * @details
   * By default, the user subscribes to the audio and video streams of all the other users in the
   * channel, giving rise to usage and **billings**. To stop subscribing to a specified stream or all
   * remote streams, call the corresponding `mute` methods.
   * Call timing: Call this method after `create(RtcEngineConfig config)`.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onJoinChannelSuccess` and `onConnectionStateChanged` callbacks.
   * - The remote client: The `onUserJoined` callback, if a user joining the channel in the
   * COMMUNICATION profile, or a host joining a channel in the LIVE_BROADCASTING profile.
   * When the connection between the local client and Agora's server is interrupted due to poor
   * network conditions, the SDK tries reconnecting to the server. When the local client successfully
   * rejoins the channel, the SDK triggers the `onRejoinChannelSuccess` callback on the local client.
   *
   * @note
   * - This method only supports users joining one channel at a time.
   * - Users with different App IDs cannot call each other.
   * - Before joining a channel, ensure that the App ID you use to generate a token is the same as
   * that you pass in the `create(RtcEngineConfig config)` method; otherwise, you may fail to join the channel with the
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
   * @param channelId The channel name. This parameter signifies the channel in which users engage in
   * real-time audio and video interaction. Under the premise of the same App ID, users who fill in
   * the same channel ID enter the same channel for audio and video interaction. The string length
   * must be less than 64 bytes. Supported characters (89 characters in total):
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   * @param optionalInfo (Optional) Reserved for future use.
   * @param uid The user ID. This parameter is used to identify the user in the channel for real-time
   * audio and video interaction. You need to set and manage user IDs yourself, and ensure that each
   * user ID in the same channel is unique. This parameter is a 32-bit signed integer. The value range
   * is from -2^31 to 2^31-1. If the user ID is not assigned (or set to 0), the SDK assigns a random
   * user ID and `onJoinChannelSuccess` returns it in the callback. Your application must record and
   * maintain the returned user ID, because the SDK does not do so.
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
  public abstract int joinChannel(String token, String channelId, String optionalInfo, int uid);

  /**
   * @brief Joins a channel with media options.
   *
   * @details
   * Compared to `joinChannel(String token, String channelId, String optionalInfo, int uid)`, this method has the `options` parameter which is used to set
   * media options, such as whether to publish audio and video streams within a channel, or whether to
   * automatically subscribe to the audio and video streams of all remote users when joining a
   * channel. By default, the user subscribes to the audio and video streams of all the other users in
   * the channel, giving rise to usage and **billings**. To stop subscribing to other streams, set the
   * `options` parameter or call the corresponding `mute` methods.
   * Call timing: Call this method after `create(RtcEngineConfig config)`.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onJoinChannelSuccess` and `onConnectionStateChanged` callbacks.
   * - The remote client: The `onUserJoined` callback, if a user joining the channel in the
   * COMMUNICATION profile, or a host joining a channel in the LIVE_BROADCASTING profile.
   * When the connection between the local client and Agora's server is interrupted due to poor
   * network conditions, the SDK tries reconnecting to the server. When the local client successfully
   * rejoins the channel, the SDK triggers the `onRejoinChannelSuccess` callback on the local client.
   *
   * @note
   * - This method only supports users joining one channel at a time.
   * - Users with different App IDs cannot call each other.
   * - Before joining a channel, ensure that the App ID you use to generate a token is the same as
   * that you pass in the `create(RtcEngineConfig config)` method; otherwise, you may fail to join the channel with the
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
   * @param channelId The channel name. This parameter signifies the channel in which users engage in
   * real-time audio and video interaction. Under the premise of the same App ID, users who fill in
   * the same channel ID enter the same channel for audio and video interaction. The string length
   * must be less than 64 bytes. Supported characters (89 characters in total):
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   * @param uid The user ID. This parameter is used to identify the user in the channel for real-time
   * audio and video interaction. You need to set and manage user IDs yourself, and ensure that each
   * user ID in the same channel is unique. This parameter is a 32-bit signed integer. The value range
   * is from -231 to 231-1. If the user ID is not assigned (or set to 0), the SDK assigns a random
   * user ID and `onJoinChannelSuccess` returns it in the callback. Your application must record and
   * maintain the returned user ID, because the SDK does not do so.
   * @param options The channel media options. See `ChannelMediaOptions`.
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
  public abstract int joinChannel(
      String token, String channelId, int uid, ChannelMediaOptions options);

  /**
   * @brief Registers a user account.
   *
   * @details
   * Once registered, the user account can be used to identify the local user when the user joins the
   * channel. After the registration is successful, the user account can identify the identity of the
   * local user, and the user can use it to join the channel.
   * This method is optional. If you want to join a channel using a user account, you can choose one
   * of the following methods:
   * - Call the `registerLocalUserAccount` method to register a user account, and then call the
   * `joinChannelWithUserAccount(String token, String channelName, String userAccount)` or `joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options)` method to join a
   * channel, which can shorten the time it takes to enter the channel.
   * - Call the `joinChannelWithUserAccount(String token, String channelName, String userAccount)` or `joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options)` method to
   * join a channel.
   * Related callbacks: After successfully calling this method, the `onLocalUserRegistered` callback
   * will be triggered on the local client to report the local user's UID and user account.
   *
   * @note
   * - Starting from v4.6.0, the SDK will no longer automatically map Int UID to the String
   * `userAccount` used when registering a User Account. If you want to join a channel with the
   * original String `userAccount` used during registration, call the `joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options)`
   * method to join the channel, instead of calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` and pass in the Int UID
   * obtained through this method
   * - Ensure that the `userAccount` is unique in the channel.
   * - To ensure smooth communication, use the same parameter type to identify the user. For example,
   * if a user joins the channel with a UID, then ensure all the other users use the UID too. The same
   * applies to the user account. If a user joins the channel with the Agora Web SDK, ensure that the
   * ID of the user is set to the same parameter type.
   *
   * @param appId The App ID of your project on Agora Console.
   * @param userAccount The user account. This parameter is used to identify the user in the channel
   * for real-time audio and video engagement. You need to set and manage user accounts yourself and
   * ensure that each user account in the same channel is unique. The maximum length of this parameter
   * is 255 bytes. Ensure that you set this parameter and do not set it as NULL. Supported characters
   * are as follow(89 in total):
   * - The 26 lowercase English letters: a to z.
   * - The 26 uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - Space
   * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
   * "^", "_", "{", "}", "|", "~", ","
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerLocalUserAccount(String appId, String userAccount);

  /**
   * @brief Joins a channel with a User Account and Token.
   *
   * @details
   * Before calling this method, if you have not called `registerLocalUserAccount` to register a user
   * account, when you call this method to join a channel, the SDK automatically creates a user
   * account for you. Calling the `registerLocalUserAccount` method to register a user account, and
   * then calling this method to join a channel can shorten the time it takes to enter the channel.
   * Once a user joins the channel, the user subscribes to the audio and video streams of all the
   * other users in the channel by default, giving rise to usage and billings. To stop subscribing to
   * a specified stream or all remote streams, call the corresponding `mute` methods.
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
   * @param channelName The channel name. This parameter signifies the channel in which users engage
   * in real-time audio and video interaction. Under the premise of the same App ID, users who fill in
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
  public abstract int joinChannelWithUserAccount(
      String token, String channelName, String userAccount);

  /**
   * @brief Join a channel using a user account and token, and set the media options.
   *
   * @since v3.3.0.
   *
   * @details
   * Before calling this method, if you have not called `registerLocalUserAccount` to register a user
   * account, when you call this method to join a channel, the SDK automatically creates a user
   * account for you. Calling the `registerLocalUserAccount` method to register a user account, and
   * then calling this method to join a channel can shorten the time it takes to enter the channel.
   * Compared to `joinChannelWithUserAccount(String token, String channelName, String userAccount)`, this method has the `options` parameter which is
   * used to set media options, such as whether to publish audio and video streams within a channel.
   * By default, the user subscribes to the audio and video streams of all the other users in the
   * channel, giving rise to usage and **billings**. To stop subscribing to other streams, set the
   * `options` parameter or call the corresponding `mute` methods.
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
   * @param channelName The channel name. This parameter signifies the channel in which users engage
   * in real-time audio and video interaction. Under the premise of the same App ID, users who fill in
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
  public abstract int joinChannelWithUserAccount(
      String token, String channelName, String userAccount, ChannelMediaOptions options);

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
   *
   */
  public abstract int getUserInfoByUserAccount(String userAccount, UserInfo userInfo);

  /**
   * @brief Gets the user information by passing in the user ID.
   *
   * @details
   * After a remote user joins the channel, the SDK gets the UID and user account of the remote user,
   * caches them in a mapping table object, and triggers the `onUserInfoUpdated` callback on the local
   * client. After receiving the callback, you can call this method and pass in the UID to get the
   * user account of the specified user from the `UserInfo` object.
   * Call timing: Call this method after receiving the `onUserInfoUpdated` callback.
   * Related callbacks: `onUserInfoUpdated`
   *
   * @param uid The user ID.
   * @param userInfo Input and output parameter. The `UserInfo` object that identifies the user
   * information.
   * - Input value: A `UserInfo` object.
   * - Output: A `UserInfo` object that contains both the user account and UID.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int getUserInfoByUid(int uid, UserInfo userInfo);

  /**
   * @brief Leaves a channel.
   *
   * @details
   * After calling this method, the SDK terminates the audio and video interaction, leaves the current
   * channel, and releases all resources related to the session.
   * After joining the channel, you must call this method to end the call; otherwise, the next call
   * cannot be started.
   * Call timing: Call this method after joining a channel.
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
   * - If you have called `joinChannelEx` to join multiple channels, calling this method will leave
   * all the channels you joined.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -7: The SDK is not initialized.
   */
  public abstract int leaveChannel();

  /**
   * @brief Sets channel options and leaves the channel.
   *
   * @details
   * After calling this method, the SDK terminates the audio and video interaction, leaves the current
   * channel, and releases all resources related to the session.
   * After joining a channel, you must call this method or `leaveChannel()` to end the call,
   * otherwise, the next call cannot be started. If you have called `joinChannelEx` to join multiple
   * channels, calling this method will leave all the channels you joined.
   * Call timing: Call this method after joining a channel.
   * Related callbacks: A successful call of this method triggers the following callbacks:
   * - The local client: The `onLeaveChannel` callback will be triggered.
   * - The remote client: The `onUserOffline` callback will be triggered after the remote host leaves
   * the channel.
   *
   * @note
   * If you call `destroy()` immediately after calling this method, the SDK does not trigger the
   * `onLeaveChannel` callback.
   * This method call is asynchronous. When this method returns, it does not necessarily mean that the
   * user has left the channel.
   *
   * @param options The options for leaving the channel. See `LeaveChannelOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int leaveChannel(LeaveChannelOptions options);

  /**
   * @brief Renews the token.
   *
   * @details
   * This method is used to update the token. After successfully calling this method, the SDK will
   * trigger the `onRenewTokenResult` callback. A token will expire after a certain period of time, at
   * which point the SDK will be unable to establish a connection with the server.
   * Call timing: In any of the following cases, Agora recommends that you generate a new token on
   * your server and then call this method to renew your token:
   * - Receiving the `onTokenPrivilegeWillExpire` callback reporting the token is about to expire.
   * - Receiving the `onRequestToken` callback reporting the token has expired.
   * - Receiving the `onConnectionStateChanged` callback reporting `CONNECTION_CHANGED_TOKEN_EXPIRED`
   * (9).
   *
   * @param token The new token.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid. For example, the token is empty.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   *   - 110: Invalid token. Ensure the following:
   *     - The user ID specified when generating the token is consistent with the user ID used when
   * joining the channel.
   *     - The generated token is the same as the token passed in to join the channel.
   */
  public abstract int renewToken(String token);

  /**
   * @brief Gets the audio device information.
   *
   * @details
   * After calling this method, you can get whether the audio device supports ultra-low-latency
   * capture and playback.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @return
   * The `DeviceInfo` object that identifies the audio device information.
   * - Not null: Success.
   * - Null: Failure.
   */
  public abstract DeviceInfo getAudioDeviceInfo();

  /**
   * @brief Enables interoperability with the Agora Web SDK (applicable only in the live streaming
   * scenarios).
   *
   * @details
   * You can call this method to enable or disable interoperability with the Agora Web SDK. If a
   * channel has Web SDK users, ensure that you call this method, or the video of the Native user will
   * be a black screen for the Web user.
   * This method is only applicable in live streaming scenarios, and interoperability is enabled by
   * default in communication scenarios.
   *
   * @param enabled Whether to enable interoperability:
   * - `true`: Enable interoperability.
   * - `false`: (Default) Disable interoperability.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableWebSdkInteroperability(boolean enabled);

  /**
   * @brief Gets the current connection state of the SDK.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @return
   * The current connection state.
   */
  public abstract int getConnectionState();

  /**
   * @brief Enables the audio module.
   *
   * @details
   * The audio module is enabled by default After calling `disableAudio` to disable the audio module,
   * you can call this method to re-enable it.
   * Call timing: This method can be called either before or after joining the channel. It is still
   * valid after one leaves channel.
   *
   * @note
   * - Calling this method will reset the entire engine, resulting in a slow response time. You can
   * use the following methods to independently control a specific function of the audio module based
   * on your actual needs:
   *   - `enableLocalAudio`: Whether to enable the microphone to create the local audio stream.
   *   - `muteLocalAudioStream`: Whether to publish the local audio stream.
   *   - `muteRemoteAudioStream`: Whether to subscribe and play the remote audio stream.
   *   - `muteAllRemoteAudioStreams`: Whether to subscribe to and play all remote audio streams.
   * - A successful call of this method resets `enableLocalAudio`, `muteRemoteAudioStream`, and
   * `muteAllRemoteAudioStreams`. Proceed it with caution.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableAudio();

  /**
   * @brief Disables the audio module.
   *
   * @details
   * The audio module is enabled by default, and you can call this method to disable the audio module.
   * Call timing: This method can be called either before or after joining the channel. It is still
   * valid after one leaves channel.
   *
   * @note
   * This method resets the internal engine and takes some time to take effect. Agora recommends using
   * the following API methods to control the audio modules separately:
   * - `enableLocalAudio`: Whether to enable the microphone to create the local audio stream.
   * - `muteLocalAudioStream`: Whether to publish the local audio stream.
   * - `muteRemoteAudioStream`: Whether to subscribe and play the remote audio stream.
   * - `muteAllRemoteAudioStreams`: Whether to subscribe to and play all remote audio streams.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int disableAudio();

  /**
   * Disables the audio function in the channel.
   * <p>
   * Note: This method controls the underlying states of the Engine. It is still
   * valid after one leaves channel.
   *
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  public abstract int pauseAudio();

  /**
   * Enables the audio function in the channel.
   *
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  public abstract int resumeAudio();

  /**
   * @brief Sets the audio profile.
   *
   * @details
   * If you need to set the audio scenario, you can either call `setAudioScenario`, or `create(RtcEngineConfig config)`
   * and set the `mAudioScenario` in `RtcEngineConfig`.
   * Applicable scenarios: This method is suitable for various audio scenarios. You can choose as
   * needed. For example, in scenarios with high audio quality requirements such as music teaching, it
   * is recommended to set `profile` to `MUSIC_HIGH_QUALITY`(4).
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param profile The audio profile, including the sampling rate, bitrate, encoding mode, and the
   * number of channels.
   * - `DEFAULT`(0): The default value.
   *   - For the interactive streaming profile: A sample rate of 48 kHz, music encoding, mono, and a
   * bitrate of up to 64 Kbps.
   *   - For the communication profile: A sample rate of 32 kHz, audio encoding, mono, and a bitrate
   * of up to 18 Kbps.
   * - `SPEECH_STANDARD`(1): A sampling rate of 32 kHz, audio encoding, mono, and a bitrate of up to
   * 18 Kbps.
   * - `MUSIC_STANDARD`(2): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up to 64
   * Kbps.
   * - `MUSIC_STANDARD_STEREO`(3): A sampling rate of 48 kHz, music encoding, stereo, and a bitrate of
   * up to 80 Kbps.
   * - `MUSIC_HIGH_QUALITY`(4): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up
   * to 96 Kbps.
   * - `MUSIC_HIGH_QUALITY_STEREO`(5): A sampling rate of 48 kHz, music encoding, stereo, and a
   * bitrate of up to 128 Kbps.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioProfile(int profile);

  /**
   * @brief Sets the audio profile and audio scenario.
   *
   * @details
   * Applicable scenarios: This method is suitable for various audio scenarios. You can choose as
   * needed. For example, in scenarios with high audio quality requirements such as music teaching, it
   * is recommended to set `profile` to `MUSIC_HIGH_QUALITY`(4) and `scenario` to
   * `AUDIO_SCENARIO_GAME_STREAMING`(3).
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param profile The audio profile, including the sampling rate, bitrate, encoding mode, and the
   * number of channels.
   * - `DEFAULT`(0): The default value.
   *   - For the interactive streaming profile: A sample rate of 48 kHz, music encoding, mono, and a
   * bitrate of up to 64 Kbps.
   *   - For the communication profile: A sample rate of 32 kHz, audio encoding, mono, and a bitrate
   * of up to 18 Kbps.
   * - `SPEECH_STANDARD`(1): A sampling rate of 32 kHz, audio encoding, mono, and a bitrate of up to
   * 18 Kbps.
   * - `MUSIC_STANDARD`(2): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up to 64
   * Kbps.
   * - `MUSIC_STANDARD_STEREO`(3): A sampling rate of 48 kHz, music encoding, stereo, and a bitrate of
   * up to 80 Kbps.
   * - `MUSIC_HIGH_QUALITY`(4): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up
   * to 96 Kbps.
   * - `MUSIC_HIGH_QUALITY_STEREO`(5): A sampling rate of 48 kHz, music encoding, stereo, and a
   * bitrate of up to 128 Kbps.
   * @param scenario The audio scenarios. Under different audio scenarios, the device uses different
   * volume types.
   * - `AUDIO_SCENARIO_DEFAULT`(0): (Default) Automatic scenario, where the SDK chooses the
   * appropriate audio quality according to the user role and audio route.
   * - `AUDIO_SCENARIO_GAME_STREAMING`(3): High-quality audio scenario, where users mainly play music.
   * - `AUDIO_SCENARIO_CHATROOM`(5): Chatroom scenario, where users need to frequently switch the user
   * role or mute and unmute the microphone.
   * - `AUDIO_SCENARIO_CHORUS`(7): Real-time chorus scenario, where users have good network conditions
   * and require ultra-low latency.
   *                         Attention: Before using this enumeration, you need to call
   * `getAudioDeviceInfo` to see whether the audio device supports ultra-low-latency capture and
   * playback. To experience ultra-low latency, you need to ensure that your audio device supports
   * ultra-low latency ( `isLowLatencyAudioSupported` = `true`).
   * - `AUDIO_SCENARIO_MEETING`(8): Meeting scenario that mainly involves the human voice.
   * - `AUDIO_SCENARIO_AI_CLIENT`(10): AI conversation scenario, which is only applicable to scenarios
   * where the user interacts with the conversational AI agent created by `Conversational AI Engine`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioProfile(int profile, int scenario);

  /**
   * @brief Sets the audio scenario.
   *
   * @details
   * Applicable scenarios: This method is suitable for various audio scenarios. You can choose as
   * needed. For example, in scenarios such as music teaching that require high sound quality, it is
   * recommended to set `scenario` to `AUDIO_SCENARIO_GAME_STREAMING`(3).
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param scenario The audio scenarios. Under different audio scenarios, the device uses different
   * volume types.
   * - `AUDIO_SCENARIO_DEFAULT`(0): (Default) Automatic scenario, where the SDK chooses the
   * appropriate audio quality according to the user role and audio route.
   * - `AUDIO_SCENARIO_GAME_STREAMING`(3): High-quality audio scenario, where users mainly play music.
   * - `AUDIO_SCENARIO_CHATROOM`(5): Chatroom scenario, where users need to frequently switch the user
   * role or mute and unmute the microphone.
   * - `AUDIO_SCENARIO_CHORUS`(7): Real-time chorus scenario, where users have good network conditions
   * and require ultra-low latency.
   *                         Attention: Before using this enumeration, you need to call
   * `getAudioDeviceInfo` to see whether the audio device supports ultra-low-latency capture and
   * playback. To experience ultra-low latency, you need to ensure that your audio device supports
   * ultra-low latency ( `isLowLatencyAudioSupported` = `true`).
   * - `AUDIO_SCENARIO_MEETING`(8): Meeting scenario that mainly involves the human voice.
   * - `AUDIO_SCENARIO_AI_CLIENT`(10): AI conversation scenario, which is only applicable to scenarios
   * where the user interacts with the conversational AI agent created by `Conversational AI Engine`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioScenario(int scenario);

  /**
   * <p>
   * Sets high-quality audio preferences.
   *
   * <p>
   * Call this method and set all the modes before joining a channel. Do NOT call
   * this method again after joining a channel.
   *
   * <p>
   * Note: Agora does not recommend using this method. If you want to set the
   * audio profile, see {@link setAudioProfile(int profile, int scenario)
   * setAudioProfile}.
   *
   * @param fullband    Full-band codec (48 kHz sampling rate), not compatible
   *                    with versions before v1.7.4.
   *                    <ul>
   *                    <li>True: Enable full-band codec.
   *                    <li>False: Disable full-band codec.
   *                    </ul>
   * @param stereo      Stereo codec, not compatible with versions before v1.7.4.
   *                    <ul>
   *                    <li>True: Enable stereo codec.
   *                    <li>False: Disable stereo codec.
   *                    </ul>
   * @param fullBitrate High bitrate. Recommended in voice-only mode.
   *                    <ul>
   *                    <li>True: Enable high bitrate mode.
   *                    <li>False: Disable high bitrate mode.
   *                    </ul>
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   *
   */
  public abstract int setHighQualityAudioParameters(
      boolean fullband, boolean stereo, boolean fullBitrate);

  /**
   * @brief Adjusts the capturing signal volume.
   *
   * @details
   * If you only need to mute the audio signal, Agora recommends that you use `muteRecordingSignal`
   * instead.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param volume The volume of the user. The value range is [0,400].
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustRecordingSignalVolume(int volume);

  /**
   * @brief Adjusts the playback signal volume of all remote users.
   *
   * @details
   * This method is used to adjust the signal volume of all remote users mixed and played locally. If
   * you need to adjust the signal volume of a specified remote user played locally, it is recommended
   * that you call `adjustUserPlaybackSignalVolume` instead.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param volume The volume of the user. The value range is [0,400].
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustPlaybackSignalVolume(int volume);

  /**
   * @brief Enables the reporting of users' volume indication.
   *
   * @details
   * This method enables the SDK to regularly report the volume information to the app of the local
   * user who sends a stream and remote users (three users at most) whose instantaneous volumes are
   * the highest.
   * Call timing: This method can be called either before or after joining the channel.
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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableAudioVolumeIndication(int interval, int smooth, boolean reportVad);

  /**
   * @brief Enables or disables the local audio capture.
   *
   * @details
   * The audio function is enabled by default when users joining a channel. This method disables or
   * re-enables the local audio function to stop or restart local audio capturing.
   * The difference between this method and `muteLocalAudioStream` are as follows:
   * - `enableLocalAudio`: Disables or re-enables the local audio capturing and processing. If you
   * disable or re-enable local audio capturing using the `enableLocalAudio` method, the local user
   * might hear a pause in the remote audio playback.
   * - `muteLocalAudioStream`: Sends or stops sending the local audio streams without affecting the
   * audio capture status.
   * Applicable scenarios: This method does not affect receiving the remote audio streams.
   * `enableLocalAudio` `(false)` is suitable for scenarios where the user wants to receive remote
   * audio streams without sending locally captured audio.
   * Call timing: You can call this method either before or after joining a channel. Calling it before
   * joining a channel only sets the device state, and it takes effect immediately after you join the
   * channel.
   * Related callbacks: Once the local audio function is disabled or re-enabled, the SDK triggers the
   * `onLocalAudioStateChanged` callback, which reports `LOCAL_AUDIO_STREAM_STATE_STOPPED` (0) or
   * `LOCAL_AUDIO_STREAM_STATE_RECORDING` (1).
   *
   * @param enabled - `true`: (Default) Re-enable the local audio function, that is, to start the
   * local audio capturing device (for example, the microphone).
   * - `false`: Disable the local audio function, that is, to stop local audio capturing.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableLocalAudio(boolean enabled);

  /**
   * @brief Stops or resumes publishing the local audio stream.
   *
   * @details
   * This method is used to control whether to publish the locally captured audio stream. If you call
   * this method to stop publishing locally captured audio streams, the audio capturing device will
   * still work and won't be affected.
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After successfully calling this method, the local end triggers callback
   * `onAudioPublishStateChanged`; the remote end triggers `onUserMuteAudio` and
   * `onRemoteAudioStateChanged` callbacks.
   *
   * @param muted Whether to stop publishing the local audio stream:
   * - `true`: Stops publishing the local audio stream.
   * - `false`: (Default) Resumes publishing the local audio stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteLocalAudioStream(boolean muted);

  /**
   * @brief Stops or resumes subscribing to the audio stream of a specified user.
   *
   * @details
   * Call timing: Call this method after joining a channel.
   * Related callbacks: After a successful method call, the SDK triggers the
   * `onAudioSubscribeStateChanged` callback.
   *
   * @param uid The user ID of the specified user.
   * @param muted Whether to subscribe to the specified remote user's audio stream.
   * - `true`: Stop subscribing to the audio stream of the specified user.
   * - `false`: (Default) Subscribe to the audio stream of the specified user.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteRemoteAudioStream(int uid, boolean muted);

  /**
   * @brief Adjusts the playback signal volume of a specified remote user.
   *
   * @details
   * You can call this method to adjust the playback volume of a specified remote user. To adjust the
   * playback volume of different remote users, call the method as many times, once for each remote
   * user.
   * Call timing: Call this method after joining a channel.
   *
   * @param uid The user ID of the remote user.
   * @param volume The volume of the user. The value range is [0,400].
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustUserPlaybackSignalVolume(int uid, int volume);

  /**
   * @brief Stops or resumes subscribing to the audio streams of all remote users.
   *
   * @details
   * After successfully calling this method, the local user stops or resumes subscribing to the audio
   * streams of all remote users, including all subsequent users.
   * Call timing: Call this method after joining a channel.
   *
   * @note
   * If you call this method and then call `enableAudio` or `disableAudio`, the latest call will
   * prevail.
   * By default, the SDK subscribes to the audio streams of all remote users when joining a channel.
   * To modify this behavior, you can set `autoSubscribeAudio` to `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * to join the channel, which will cancel the subscription to the audio streams of all users
   * upon joining the channel.
   *
   * @param muted Whether to stop subscribing to the audio streams of all remote users:
   * - `true`: Stops subscribing to the audio streams of all remote users.
   * - `false`: (Default) Subscribes to the audio streams of all remote users by default.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteAllRemoteAudioStreams(boolean muted);

  /**
   * @brief Enables the video module.
   *
   * @details
   * The video module is disabled by default, call this method to enable it. If you need to disable
   * the video module later, you need to call `disableVideo`.
   * Call timing: This method can be called either before joining a channel or while in the channel:
   * - If called before joining a channel, it enables the video module.
   * - If called during an audio-only call, the audio call automatically switches to a video call.
   * Related callbacks: A successful call of this method triggers the `onRemoteVideoStateChanged`
   * callback on the remote client.
   *
   * @note
   * - This method enables the internal engine and is valid after leaving the channel.
   * - Calling this method will reset the entire engine, resulting in a slow response time. You can
   * use the following methods to independently control a specific function of the video module based
   * on your actual needs:
   *   - `enableLocalVideo`: Whether to enable the camera to create the local video stream.
   *   - `muteLocalVideoStream`: Whether to publish the local video stream.
   *   - `muteRemoteVideoStream`: Whether to subscribe to and play the remote video stream.
   *   - `muteAllRemoteVideoStreams`: Whether to subscribe to and play all remote video streams.
   * - A successful call of this method resets `enableLocalVideo`, `muteRemoteVideoStream`, and
   * `muteAllRemoteVideoStreams`. Proceed it with caution.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableVideo();

  /**
   * @brief Disables the video module.
   *
   * @details
   * This method is used to disable the video module.
   * Call timing: This method can be called either before or after joining the channel.
   * - If it is called before joining the channel, the audio-only mode is enabled.
   * - If it is called after joining the channel, it switches from video mode to audio-only mode.
   * Then, calling `enableVideo` can swithch to video mode again.
   * Related callbacks: A successful call of this method triggers the `onUserEnableVideo` (`false`)
   * callback on the remote client.
   *
   * @note
   * - This method affects the internal engine and can be called after leaving the channel.
   * - Calling this method will reset the entire engine, resulting in a slow response time. You can
   * use the following methods to independently control a specific function of the video module based
   * on your actual needs:
   *   - `enableLocalVideo`: Whether to enable the camera to create the local video stream.
   *   - `muteLocalVideoStream`: Whether to publish the local video stream.
   *   - `muteRemoteVideoStream`: Whether to subscribe to and play the remote video stream.
   *   - `muteAllRemoteVideoStreams`: Whether to subscribe to and play all remote video streams.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int disableVideo();

  /**
   * @brief Sets the video encoder configuration.
   *
   * @details
   * Sets the encoder configuration for the local video. Each configuration profile corresponds to a
   * set of video parameters, including the resolution, frame rate, and bitrate.
   * Call timing: You can call this method either before or after joining a channel. If the user does
   * not need to reset the video encoding properties after joining the channel, Agora recommends
   * calling this method before `enableVideo` to reduce the time to render the first video frame.
   *
   * @note
   * - Both this method and the `getMirrorApplied` method support setting the mirroring effect. Agora
   * recommends that you only choose one method to set it up. Using both methods at the same time
   * causes the mirroring effect to overlap, and the mirroring settings fail.
   * - The `config` specified in this method is the maximum value under ideal network conditions. If
   * the video engine cannot render the video using the specified `config` due to unreliable network
   * conditions, the parameters further down the list are considered until a successful configuration
   * is found.
   *
   * @param config Video profile. See `VideoEncoderConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVideoEncoderConfiguration(VideoEncoderConfiguration config);

  /**
   * @brief Queries the video codec capabilities of the SDK.
   *
   * @return
   * - One `CodecCapInfo` array indicating the video encoding capability of the device, if the method
   * call succeeds.
   * - If the call timeouts, please modify the call logic and do not invoke the method in the main
   * thread.
   */
  public abstract CodecCapInfo[] queryCodecCapability();

  /**
   * @brief Queries device score.
   *
   * @details
   * Applicable scenarios: In high-definition or ultra-high-definition video scenarios, you can first
   * call this method to query the device's score. If the returned score is low (for example, below
   * 60), you need to lower the video resolution to avoid affecting the video experience. The minimum
   * device score required for different business scenarios is varied. For specific score
   * recommendations, please `technical support`.
   *
   * @return
   * - > 0: The method call succeeeds, the value is the current device's score, the range is [0,100],
   * the larger the value, the stronger the device capability. Most devices are rated between 60 and
   * 100.
   * - < 0: Failure.
   */
  public abstract int queryDeviceScore();

  /**
   * @brief Queries the focal length capability supported by the camera.
   *
   * @details
   * If you want to enable the wide-angle or ultra-wide-angle mode for camera capture, it is
   * recommended to start by calling this method to check whether the device supports the required
   * focal length capability. Then, adjust the camera's focal length configuration based on the query
   * result by calling `setCameraCapturerConfiguration`, ensuring the best camera capture performance.
   *
   * @return
   * Returns an array of `AgoraFocalLengthInfo` objects, which contain the camera's orientation and
   * focal length type.
   */
  public abstract AgoraFocalLengthInfo[] queryCameraFocalLengthCapability();

  /**
   * @brief Sets the camera capture configuration.
   *
   * @details
   * Call timing: Call this method before enabling local camera capture, such as before calling
   * `startPreview(Constants.VideoSourceType sourceType)` and `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`.
   *
   * @note
   * To adjust the camera focal length configuration, It is recommended to call
   * `queryCameraFocalLengthCapability` first to check the device's focal length capabilities, and
   * then configure based on the query results.
   * Due to limitations on some Android devices, even if you set the focal length type according to
   * the results returned in `queryCameraFocalLengthCapability`, the settings may not take effect.
   *
   * @param config The camera capture configuration. See `CameraCapturerConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setCameraCapturerConfiguration(CameraCapturerConfiguration config);

  /**
   * @brief Initializes the local video view.
   *
   * @details
   * This method initializes the video view of a local stream on the local device. It only affects the
   * video seen by the local user and does not impact the publishing of the local video. Call this
   * method to bind the local video stream to a video view ( `view` ) and to set the rendering and
   * mirror modes of the video view.
   * The binding remains valid after leaving the channel. To stop rendering or unbind the local video
   * from the view, set `view` as NULL.
   * Applicable scenarios: After initialization, call this method to set the local video and then join
   * the channel.
   * In real-time interactive scenarios, if you need to simultaneously view multiple preview frames in
   * the local video preview, and each frame is at a different observation position along the video
   * link, you can repeatedly call this method to set different `view` s and set different observation
   * positions for each `view. ` For example, by setting the video source to the camera and then
   * configuring two `view` s with `position` setting to VIDEO_MODULE_POSITION_POST_CAPTURER_ORIGIN
   * and VIDEO_MODULE_POSITION_POST_CAPTURER, you can simultaneously preview the raw, unprocessed
   * video frame and the video frame that has undergone preprocessing (image enhancement effects,
   * virtual background, watermark) in the local video preview.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note To update only the rendering or mirror mode of the local video view during a call, call
   * `setLocalRenderMode(int renderMode, int mirrorMode)` instead.
   *
   * @param local The local video view and settings. See `VideoCanvas`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setupLocalVideo(VideoCanvas local);

  /**
   * @brief Initializes the video view of a remote user.
   *
   * @details
   * This method initializes the video view of a remote stream on the local device. It affects only
   * the video view that the local user sees. Call this method to bind the remote video stream to a
   * video view and to set the rendering and mirror modes of the video view.
   * You need to specify the ID of the remote user in this method. If the remote user ID is unknown to
   * the application, set it after the app receives the `onUserJoined` callback.
   * To unbind the remote user from the view, set the `view` parameter to NULL.
   * Once the remote user leaves the channel, the SDK unbinds the remote user.
   * In the scenarios of custom layout for mixed videos on the mobile end, you can call this method
   * and set a separate `view` for rendering each sub-video stream of the mixed video stream.
   *
   * @note
   * - To update the rendering or mirror mode of the remote video view during a call, use the
   * `setRemoteRenderMode` method.
   * - When using the recording service, the app does not need to bind a view, as it does not send a
   * video stream. If your app does not recognize the recording service, bind the remote user to the
   * view when the SDK triggers the `onFirstRemoteVideoDecoded` callback.
   *
   * @param remote The remote video view and settings. See `VideoCanvas`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setupRemoteVideo(VideoCanvas remote);

  /**
   * Updates the display mode of the video view of a remote user.
   *
   * After initializing the video view of a remote user, you can call this method to update its
   * rendering and mirror modes. This method affects only the video view that the local user sees.
   *
   * @note
   * - Ensure that you have called {@link setupRemoteVideo setupRemoteVideo} to initialize the
   * remote video view before calling this method.
   * - During a call, you can call this method as many times as necessary to update the display mode
   * of the video view of a remote user.
   *
   * @param uid ID of the remote user.
   * @param renderMode Sets the remote display mode:
   * - `RENDER_MODE_HIDDEN`(1): Uniformly scale the video until it fills the visible boundaries
   * (cropped). One dimension of the video may have clipped contents.
   * - `RENDER_MODE_FIT`(2): Uniformly scale the video until one of its dimension fits the boundary
   * (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio will be
   * filled with black.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setRemoteRenderMode(int uid, int renderMode);

  /**
   * @brief Sets the local video display mode.
   *
   * @details
   * Call this method to set the local video display mode. This method can be called multiple times
   * during a call to change the display mode.
   *
   * @param renderMode The local video display mode.
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setLocalRenderMode(int renderMode);

  /**
   * @brief Updates the display mode of the video view of a remote user.
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
   * @param renderMode The rendering mode of the remote user view.
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   * @param mirrorMode The mirror mode of the remote user view.
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. The SDK
   * disables mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enables the mirror mode for remote user's view.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disables the mirror mode for remote user's view.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteRenderMode(int uid, int renderMode, int mirrorMode);

  /**
   * @brief Updates the display mode of the local video view.
   *
   * @details
   * After initializing the local video view, you can call this method to update its rendering and
   * mirror modes. It affects only the video view that the local user sees and does not impact the
   * publishing of the local video.
   * Call timing: - Ensure that you have called the `setupLocalVideo` method to initialize the local
   * video view before calling this method.
   * - During a call, you can call this method as many times as necessary to update the display mode
   * of the local video view.
   *
   * @param renderMode The local video display mode.
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   * @param mirrorMode For the local user:
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. If you use a
   * front camera, the SDK enables the mirror mode by default; if you use a rear camera, the SDK
   * disables the mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enable the mirroring mode of the local view.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disable the mirroring mode of the local view.
   * Attention: If you use a front camera, the SDK enables the mirror mode by default; if you use a
   * rear camera, the SDK disables the mirror mode by default.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalRenderMode(int renderMode, int mirrorMode);

  /**
   * @brief Enables the local video preview.
   *
   * @details
   * You can call this method to enable local video preview.
   * Call timing: This method must be called after `enableVideo` and `setupLocalVideo`.
   *
   * @note
   * - The local preview enables the mirror mode by default.
   * - After leaving the channel, local preview remains enabled. You need to call `stopPreview()`
   * to disable local preview.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startPreview();

  /**
   * @brief Enables the local video preview and specifies the video source for the preview.
   *
   * @details
   * This method is used to start local video preview and specify the video source that appears in the
   * preview screen.
   * Call timing: This method must be called after `enableVideo` and `setupLocalVideo`.
   *
   * @note
   * - The local preview enables the mirror mode by default.
   * - After leaving the channel, local preview remains enabled. You need to call `stopPreview()`
   * to disable local preview.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startPreview(Constants.VideoSourceType sourceType);

  /**
   * @brief Stops the local video preview.
   *
   * @details
   * Applicable scenarios: After calling `startPreview()` to start the preview, if you want to
   * stop the local video preview, call this method.
   * Call timing: Call this method before joining a channel or after leaving a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopPreview();

  /**
   * @brief Stops the local video preview.
   *
   * @details
   * Applicable scenarios: After calling `startPreview(Constants.VideoSourceType sourceType)` to start the preview, if you want to
   * stop the local video preview, call this method.
   * Call timing: Call this method before joining a channel or after leaving a channel.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopPreview(Constants.VideoSourceType sourceType);

  /**
   * @brief Enables/Disables the local video capture.
   *
   * @details
   * This method disables or re-enables the local video capture, and does not affect receiving the
   * remote video stream.
   * After calling `enableVideo`, the local video capture is enabled by default.
   * If you call `enableLocalVideo` (`false`) to disable local video capture within the channel, it
   * also simultaneously stops publishing the video stream within the channel. If you want to restart
   * video catpure, you can call `enableLocalVideo` (`true`) and then call `updateChannelMediaOptions`
   * to set the `options` parameter to publish the locally captured video stream in the channel.
   * After the local video capturer is successfully disabled or re-enabled, the SDK triggers the
   * `onRemoteVideoStateChanged` callback on the remote client.
   *
   * @note
   * - You can call this method either before or after joining a channel. However, if you call it
   * before joining, the settings will only take effect once you have joined the channel.
   * - This method enables the internal engine and is valid after leaving the channel.
   *
   * @param enabled Whether to enable the local video capture.
   * - `true`: (Default) Enable the local video capture.
   * - `false`: Disable the local video capture. Once the local video is disabled, the remote users
   * cannot receive the video stream of the local user, while the local user can still receive the
   * video streams of remote users. When set to `false`, this method does not require a local camera.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableLocalVideo(boolean enabled);

  /**
   * @brief Starts camera capture.
   *
   * @details
   * You can call this method to start capturing video from one or more cameras by specifying
   * `sourceType`.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   * Note:
   * - On Android devices, you can capture video from up to 4 cameras, provided the device has
   * multiple cameras or supports external cameras.
   * @param config The configuration of the video capture. See `CameraCapturerConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startCameraCapture(
      Constants.VideoSourceType sourceType, CameraCapturerConfiguration config);

  /**
   * @brief Stops camera capture.
   *
   * @details
   * After calling `startCameraCapture` to start capturing video through one or more cameras, you can
   * call this method and set the `sourceType` parameter to stop the capture from the specified
   * cameras.
   *
   * @note If you are using the local video mixing function, calling this method can cause the local
   * video mixing to be interrupted.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopCameraCapture(Constants.VideoSourceType sourceType);

  /**
   * @brief Starts the local video mixing.
   *
   * @details
   * After calling this method, you can merge multiple video streams into one video stream locally.
   * For example, you can merge the video streams captured by the camera, screen sharing, media
   * player, remote video, video files, images, etc. into one video stream, and then publish the mixed
   * video stream to the channel.
   * Applicable scenarios: You can enable the local video mixing function in scenarios such as remote
   * conferences, live streaming, and online education, which allows users to view and manage multiple
   * videos more conveniently, and supports portrait-in-picture effect and other functions.
   * The following is a typical use case for implementing the portrait-in-picture effect:1. Call
   * `enableVirtualBackground(boolean enabled, VirtualBackgroundSource backgroundSource, SegmentationProperty segproperty)`, and set the custom background image to `BACKGROUND_NONE`, that
   * is, separate the portrait and the background in the video captured by the camera.
   * 2. Call `startScreenCapture` to start capturing the screen sharing video stream.
   * 3. Call this method and set the video source for capturing portraits as one of the video sources
   * participating in the local video mixing, picture-in-picture of the portrait can be achived in the
   * mixed video.
   * Call timing: - If you need to mix the locally collected video streams, you need to call this
   * method after `startCameraCapture` or `startScreenCapture`.
   * - If you want to publish the mixed video stream to the channel, you need to set
   * `publishTranscodedVideoTrack` in `ChannelMediaOptions` to `true` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * or `updateChannelMediaOptions`.
   * Related callbacks: When you fail to call this method, the SDK triggers the
   * `onLocalVideoTranscoderError` callback to report the reason.
   *
   * @note
   * - Local video mixing requires more CPU resources. Therefore, Agora recommends enabling this
   * function on devices with higher performance.
   * - If you need to mix locally captured video streams, the SDK supports the following capture
   * combinations:
   *   - On the Android platform, it supports up to 2 video streams captured by cameras (the device
   * itself needs to support dual cameras or support external cameras) + 1 screen sharing stream.
   * - When configuring the local video mixing, it is necessary to ensure that the layer number of the
   * video stream capturing the portrait is greater than the layer number of the screen sharing
   * stream. Otherwise, the portrait will be covered by the screen sharing and will not be displayed
   * in the final mixed video stream.
   *
   * @param config Configuration of the local video mixing, see
   * `LocalTranscoderConfiguration`.Attention:
   * - The maximum resolution of each video stream participating in the local video mixing is 4096 ×
   * 2160. If this limit is exceeded, video mixing does not take effect.
   * - The maximum resolution of the mixed video stream is 4096 × 2160.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startLocalVideoTranscoder(LocalTranscoderConfiguration config);

  /**
   * @brief Stops the local video mixing.
   *
   * @details
   * After calling `startLocalVideoTranscoder`, call this method if you want to stop the local video
   * mixing.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopLocalVideoTranscoder();

  /**
   * @brief Updates the local video mixing configuration.
   *
   * @details
   * After calling `startLocalVideoTranscoder`, call this method if you want to update the local video
   * mixing configuration.
   *
   * @note If you want to update the video source type used for local video mixing, such as adding a
   * second camera or screen to capture video, you need to call this method after `startCameraCapture`
   * or `startScreenCapture`.
   *
   * @param config Configuration of the local video mixing, see `LocalTranscoderConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int updateLocalTranscoderConfiguration(LocalTranscoderConfiguration config);

  /**
   * @brief Starts local audio mixing.
   *
   * @details
   * This method supports merging multiple audio streams into one audio stream locally. For example,
   * merging the audio streams captured from the local microphone, and that from the media player, the
   * sound card, and the remote users into one audio stream, and then publish the merged audio stream
   * to the channel.
   * - If you want to mix the locally captured audio streams, you can set publishMixedAudioTrack in
   * `ChannelMediaOptions` to `true`, and then publish the mixed audio stream to the channel.
   * - If you want to mix the remote audio stream, ensure that the remote audio stream has been
   * published in the channel and you have subcribed to the audio stream that you need to mix.
   * Applicable scenarios: You can enable this function in the following scenarios:
   * - By utilizing the local video mixing feature, the associated audio streams of the mixed video
   * streams can be simultaneously captured and published.
   * - In live streaming scenarios, users can receive audio streams within the channel, mix multiple
   * audio streams locally, and then forward the mixed audio stream to other channels.
   * - In online classes, teachers can mix the audio from interactions with students locally and then
   * forward the mixed audio stream to other channels.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note To ensure audio quality, it is recommended that the number of audio streams to be mixed
   * does not exceed 10.
   *
   * @param config The configurations for mixing the lcoal audio. See `LocalAudioMixerConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   */
  public abstract int startLocalAudioMixer(LocalAudioMixerConfiguration config);

  /**
   * @brief Updates the configurations for mixing audio streams locally.
   *
   * @details
   * After calling `startLocalAudioMixer`, call this method if you want to update the local audio
   * mixing configuration.
   * Call timing: Call this method after `startLocalAudioMixer`.
   *
   * @note To ensure audio quality, it is recommended that the number of audio streams to be mixed
   * does not exceed 10.
   *
   * @param config The configurations for mixing the lcoal audio. See `LocalAudioMixerConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   */
  public abstract int updateLocalAudioMixerConfiguration(LocalAudioMixerConfiguration config);

  /**
   * @brief Stops the local audio mixing.
   *
   * @details
   * After calling `startLocalAudioMixer`, call this method if you want to stop the local audio
   * mixing.
   * Call timing: Call this method after `startLocalAudioMixer`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   */
  public abstract int stopLocalAudioMixer();

  /**
   * @brief Stops or resumes publishing the local video stream.
   *
   * @details
   * This method is used to control whether to publish the locally captured video stream. If you call
   * this method to stop publishing locally captured video streams, the video capturing device will
   * still work and won't be affected.
   * Compared to `enableLocalVideo` (`false`), which can also cancel the publishing of local video
   * stream by turning off the local video stream capture, this method responds faster.
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After successfully calling this method, the local end triggers callback
   * `onVideoPublishStateChanged`; the remote end triggers `onUserMuteVideo` and
   * `onRemoteVideoStateChanged` callbacks.
   *
   * @param muted Whether to stop publishing the local video stream.
   * - `true`: Stop publishing the local video stream.
   * - `false`: (Default) Publish the local video stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteLocalVideoStream(boolean muted);

  /**
   * @brief Stops or resumes subscribing to the video stream of a specified user.
   *
   * @details
   * Call timing: Call this method after joining a channel.
   * Related callbacks: After a successful method call, the SDK triggers the
   * `onVideoSubscribeStateChanged` callback.
   *
   * @param uid The user ID of the specified user.
   * @param muted Whether to subscribe to the specified remote user's video stream.
   * - `true`: Stop subscribing to the video streams of the specified user.
   * - `false`: (Default) Subscribe to the video stream of the specified user.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteRemoteVideoStream(int uid, boolean muted);

  /**
   * @brief Stops or resumes subscribing to the video streams of all remote users.
   *
   * @details
   * After successfully calling this method, the local user stops or resumes subscribing to the video
   * streams of all remote users, including all subsequent users.
   * Call timing: Call this method after joining a channel.
   *
   * @note
   * If you call this method and then call `enableVideo` or `disableVideo`, the latest call will
   * prevail.
   * By default, the SDK subscribes to the video streams of all remote users when joining a channel.
   * To modify this behavior, you can set `autoSubscribeVideo` to`false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * to join the channel, which will cancel the subscription to the video streams of all users
   * upon joining the channel.
   *
   * @param muted Whether to stop subscribing to the video streams of all remote users.
   * - `true`: Stop subscribing to the video streams of all remote users.
   * - `false`: (Default) Subscribe to the video streams of all remote users by default.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteAllRemoteVideoStreams(boolean muted);

  /**
   * @brief Sets the image enhancement options.
   *
   * @since v2.4.0.
   *
   * @details
   * Enables or disables image enhancement, and sets the options.
   * Call timing: Call this method after calling `enableVideo` or `startPreview(Constants.VideoSourceType sourceType)`.
   *
   * @note
   * - This method only applies to Android 5.0 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param enabled Whether to enable the image enhancement function:
   * - `true`: Enable the image enhancement function.
   * - `false`: (Default) Disable the image enhancement function.
   * @param options The image enhancement options. See `BeautyOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 5.0 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setBeautyEffectOptions(boolean enabled, BeautyOptions options);

  /**
   * @brief Sets the image enhancement options and specifies the media source.
   *
   * @since v2.4.0.
   *
   * @details
   * Enables or disables image enhancement, and sets the options.
   * Both this method and `setBeautyEffectOptions(boolean enabled, BeautyOptions options)` set image enhancement options, but this
   * method allows you to specify the media source to which the image enhancement is applied.
   * Call timing: Call this method after calling `enableVideo` or `startPreview(Constants.VideoSourceType sourceType)`.
   *
   * @param enabled Whether to enable the image enhancement function:
   * - `true`: Enable the image enhancement function.
   * - `false`: (Default) Disable the image enhancement function.
   * @param options The image enhancement options. See `BeautyOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - The default value is `PRIMARY_CAMERA_SOURCE`.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 5.0 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setBeautyEffectOptions(
      boolean enabled, BeautyOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the face shape beauty options.
   *
   *
   * @details
   * Calling this method allows for adjusting specific parts of the face, achieving effects such as
   * slimming the face, enlarging the eyes, and slimming the nose through minor cosmetic procedures.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method only applies to Android 4.4 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param enabled Whether to enable the face shape effect:
   * - `true`: Enable the face shape effect.
   * - `false`: (Default) Disable the face shape effect.
   * @param options Face shaping style options, see `FaceShapeBeautyOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 4.4 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options);

  /**
   * @brief Sets the face shape options and specifies the media source.
   *
   *
   * @details
   * Calling this method allows for modifying various parts of the face, achieving slimming, enlarging
   * eyes, slimming nose, and other minor cosmetic effects all at once using preset parameters,
   * supporting fine-tuning the overall modification intensity.
   * Both this methods and `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options)` can be used to set beauty effects
   * options, the difference is that this method supports specifying the media source to apply the
   * beauty effects.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method only applies to Android 4.4 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param enabled Whether to enable the face shape effect:
   * - `true`: Enable the face shape effect.
   * - `false`: (Default) Disable the face shape effect.
   * @param options Face shaping style options, see `FaceShapeBeautyOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - Use the default value `PRIMARY_CAMERA_SOURCE` if you use camera to capture local video.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 4.4 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setFaceShapeBeautyOptions(
      boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Gets the beauty effect options.
   *
   *
   * @details
   * Calling this method can retrieve the current settings of the beauty effect.
   * Applicable scenarios: When the user opens the beauty style and style intensity menu in the app,
   * you can call this method to get the current beauty effect options, then refresh the menu in the
   * user interface according to the results, and update the UI.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @return
   * - A pointer to the `FaceShapeBeautyOptions` instance, if the method call succeeds.
   * - NULL is returned, if the method call fails.
   */
  public abstract FaceShapeBeautyOptions getFaceShapeBeautyOptions();

  /**
   * @brief Gets the beauty effect options.
   *
   *
   * @details
   * Calling this method can retrieve the current settings of the beauty effect.
   * Applicable scenarios: When the user opens the beauty style and style intensity menu in the app,
   * you can call this method to get the current beauty effect options, then refresh the menu in the
   * user interface according to the results, and update the UI.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - Use the default value `PRIMARY_CAMERA_SOURCE` if you use camera to capture local video.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - A pointer to the `FaceShapeBeautyOptions` instance, if the method call succeeds.
   * - NULL is returned, if the method call fails.
   */
  public abstract FaceShapeBeautyOptions getFaceShapeBeautyOptions(
      Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the options for beauty enhancement facial areas.
   *
   *
   * @details
   * If the preset beauty effects implemented in the `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType)` method do not
   * meet expectations, you can use this method to set beauty area options, individually fine-tune
   * each part of the face, and achieve a more refined beauty effect.
   * Call timing: Call this method after calling `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType)` or
   * `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType)`.
   *
   * @note
   * - This method only applies to Android 4.4 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param options Facial enhancement areas, see `FaceShapeAreaOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 4.4 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setFaceShapeAreaOptions(FaceShapeAreaOptions options);

  /**
   * @brief Sets the image enhancement options for facial areas and specifies the media source.
   *
   *
   * @details
   * If the preset beauty effects implemented in the `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType)` method do not
   * meet expectations, you can use this method to set beauty area options, individually fine-tune
   * each part of the face, and achieve a more refined beauty effect.
   * Both this methods and `setFaceShapeAreaOptions(FaceShapeAreaOptions options)` can be used to set beauty effects options,
   * the difference is that this method supports specifying the media source to apply the beauty
   * effects.
   * Call timing: Call this method after calling `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options, Constants.MediaSourceType sourceType)` or
   * `setFaceShapeBeautyOptions(boolean enabled, FaceShapeBeautyOptions options)`.
   *
   * @note
   * - This method only applies to Android 4.4 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param options Facial enhancement areas, see `FaceShapeAreaOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - Use the default value `PRIMARY_CAMERA_SOURCE` if you use camera to capture local video.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The current device does not support this feature. Possible reasons include:
   *     - The current device capabilities do not meet the requirements for image enhancement. Agora
   * recommends you replace it with a high-performance device.
   *     - The current device version is lower than Android 4.4 and does not support this feature.
   * Agora recommends you replace the device or upgrade the operating system.
   */
  public abstract int setFaceShapeAreaOptions(
      FaceShapeAreaOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Gets the facial beauty area options.
   *
   * @details
   * Calling this method can retrieve the current settings of the beauty effect.
   * Applicable scenarios: When the user opens the facial beauty area and shaping intensity menu in
   * the app, you can call this method to get the current beauty effect options, then refresh the menu
   * in the user interface according to the results, and update the UI.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @param shapeArea Facial enhancement areas.
   * - FACE_SHAPE_AREA_NONE (-1): (Default) Invalid area, no beauty effect applied.
   * - FACE_SHAPE_AREA_HEADSCALE (100): Head, used to achieve a smaller head effect. The adjustment
   * range is [0,100]; the larger the value, the smaller the head becomes, with a preset value of 100.
   * - FACE_SHAPE_AREA_FOREHEAD (101): Forehead, used to adjust the hairline height. The adjustment
   * strength ranges from [-100,100], with positive values raising and negative values lowering the
   * hairline. The greater the absolute value, the stronger the effect. The default value is 50.
   * - FACE_SHAPE_AREA_FACECONTOUR (102): Face contour, used to achieve a slimmer face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the slimming effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_FACELENGTH (103): Face length, used to achieve a longer face effect. The
   * adjustment range is [-100,100]; positive values elongate the face, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of 0.
   * - FACE_SHAPE_AREA_FACEWIDTH (104): Face width, used to achieve a narrower face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the narrowing effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_CHEEKBONE (105): Cheekbone, used to adjust cheekbone width. The adjustment
   * range is [0,100]; the larger the value, the narrower the cheekbones, with a preset set value of
   * 43.
   * - FACE_SHAPE_AREA_CHEEK (106): Cheeks, used to adjust the cheeks width. The adjustment range is
   * [0,100]; the larger the value, the narrower the cheeks, with a preset value of 50.
   * - FACE_SHAPE_AREA_CHIN (108): Chin, used to adjust the chin length. The adjustment range is
   * [-100,100]; positive values elongate the chin, while negative values shorten it. The larger the
   * value, the stronger the enhancement effect, with a preset value of -20.
   * - FACE_SHAPE_AREA_EYESCALE (200):Eyes, used to achieve a larger eye effect. The adjustment range
   * is [0,100]; the larger the value, the larger the eye size, with a preset value of 50.
   * - FACE_SHAPE_AREA_EYEDISTANCE (201): Eye distance adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEPOSITION (202): Eye position adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_LOWEREYELID (203): Lower eyelid adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEPUPILS (204): Pupil size adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEINNERCORNER (205): Inner eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEOUTERCORNER (206): Outer eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSELENGTH (300): Nose length, used to achieve a longer nose effect. The
   * adjustment range is [-100,100]; positive values elongate the nose, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of -10.
   * - FACE_SHAPE_AREA_NOSEWIDTH (301): Nose width, used to achieve a slimmer nose effect. The
   * adjustment range is [-100,100]; positive values make the nose wider, while negative values make
   * it narrower. The larger the value, the stronger the enhancement effect, with a preset value of
   * 72.
   * - FACE_SHAPE_AREA_NOSEWING (302): Nose wing adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEROOT (303): Nose root adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEBRIDGE (304): Nose bridge adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSETIP (305): Nose tip adjustment. The range is [0, 100], with a default value
   * of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEGENERAL (306): Overall nose adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHSCALE (400): Mouth, used to achieve a larger mouth effect. The adjustment
   * strength ranges from [0,100], with larger values resulting in a larger mouth. The default value
   * is 50.
   * - FACE_SHAPE_AREA_MOUTHPOSITION (401): Mouth position adjustment. The range is [-100, 100], with
   * a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_MOUTHSMILE (402): Mouth smile adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHLIP (403): Lip shape adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEBROWPOSITION (500): Eyebrow position adjustment. The range is [-100, 100],
   * with a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEBROWTHICKNESS (501): Eyebrow thickness adjustment. The range is [0, 100],
   * with a default value of 0. The larger the value, the more noticeable the adjustment.
   *
   * @return
   * - A pointer to the `FaceShapeAreaOptions` instance, if the method call succeeds.
   * - NULL is returned, if the method call fails.
   */
  public abstract FaceShapeAreaOptions getFaceShapeAreaOptions(int shapeArea);

  /**
   * @brief Gets the facial beauty area options.
   *
   * @details
   * Calling this method can retrieve the current settings of the beauty effect.
   * Applicable scenarios: When the user opens the facial beauty area and shaping intensity menu in
   * the app, you can call this method to get the current beauty effect options, then refresh the menu
   * in the user interface according to the results, and update the UI.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @param shapeArea Facial enhancement areas.
   * - FACE_SHAPE_AREA_NONE (-1): (Default) Invalid area, no beauty effect applied.
   * - FACE_SHAPE_AREA_HEADSCALE (100): Head, used to achieve a smaller head effect. The adjustment
   * range is [0,100]; the larger the value, the smaller the head becomes, with a preset value of 100.
   * - FACE_SHAPE_AREA_FOREHEAD (101): Forehead, used to adjust the hairline height. The adjustment
   * strength ranges from [-100,100], with positive values raising and negative values lowering the
   * hairline. The greater the absolute value, the stronger the effect. The default value is 50.
   * - FACE_SHAPE_AREA_FACECONTOUR (102): Face contour, used to achieve a slimmer face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the slimming effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_FACELENGTH (103): Face length, used to achieve a longer face effect. The
   * adjustment range is [-100,100]; positive values elongate the face, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of 0.
   * - FACE_SHAPE_AREA_FACEWIDTH (104): Face width, used to achieve a narrower face effect. The
   * adjustment range is [0,100]; the larger the value, the stronger the narrowing effect, with a
   * preset value of 10.
   * - FACE_SHAPE_AREA_CHEEKBONE (105): Cheekbone, used to adjust cheekbone width. The adjustment
   * range is [0,100]; the larger the value, the narrower the cheekbones, with a preset set value of
   * 43.
   * - FACE_SHAPE_AREA_CHEEK (106): Cheeks, used to adjust the cheeks width. The adjustment range is
   * [0,100]; the larger the value, the narrower the cheeks, with a preset value of 50.
   * - FACE_SHAPE_AREA_CHIN (108): Chin, used to adjust the chin length. The adjustment range is
   * [-100,100]; positive values elongate the chin, while negative values shorten it. The larger the
   * value, the stronger the enhancement effect, with a preset value of -20.
   * - FACE_SHAPE_AREA_EYESCALE (200):Eyes, used to achieve a larger eye effect. The adjustment range
   * is [0,100]; the larger the value, the larger the eye size, with a preset value of 50.
   * - FACE_SHAPE_AREA_EYEDISTANCE (201): Eye distance adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEPOSITION (202): Eye position adjustment. The range is [-100, 100], with a
   * default value of 0. The greater the absolute value, the more noticeable the adjustment. Negative
   * values indicate the opposite direction.
   * - FACE_SHAPE_AREA_LOWEREYELID (203): Lower eyelid adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEPUPILS (204): Pupil size adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEINNERCORNER (205): Inner eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEOUTERCORNER (206): Outer eye corner adjustment. The range is [0, 100], with
   * a default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSELENGTH (300): Nose length, used to achieve a longer nose effect. The
   * adjustment range is [-100,100]; positive values elongate the nose, while negative values shorten
   * it. The larger the value, the stronger the enhancement effect, with a preset value of -10.
   * - FACE_SHAPE_AREA_NOSEWIDTH (301): Nose width, used to achieve a slimmer nose effect. The
   * adjustment range is [-100,100]; positive values make the nose wider, while negative values make
   * it narrower. The larger the value, the stronger the enhancement effect, with a preset value of
   * 72.
   * - FACE_SHAPE_AREA_NOSEWING (302): Nose wing adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEROOT (303): Nose root adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEBRIDGE (304): Nose bridge adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSETIP (305): Nose tip adjustment. The range is [0, 100], with a default value
   * of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_NOSEGENERAL (306): Overall nose adjustment. The range is [0, 100], with a
   * default value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHSCALE (400): Mouth, used to achieve a larger mouth effect. The adjustment
   * strength ranges from [0,100], with larger values resulting in a larger mouth. The default value
   * is 50.
   * - FACE_SHAPE_AREA_MOUTHPOSITION (401): Mouth position adjustment. The range is [-100, 100], with
   * a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_MOUTHSMILE (402): Mouth smile adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_MOUTHLIP (403): Lip shape adjustment. The range is [0, 100], with a default
   * value of 0. The larger the value, the more noticeable the adjustment.
   * - FACE_SHAPE_AREA_EYEBROWPOSITION (500): Eyebrow position adjustment. The range is [-100, 100],
   * with a default value of 0. The greater the absolute value, the more noticeable the adjustment.
   * Negative values indicate the opposite direction.
   * - FACE_SHAPE_AREA_EYEBROWTHICKNESS (501): Eyebrow thickness adjustment. The range is [0, 100],
   * with a default value of 0. The larger the value, the more noticeable the adjustment.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - Use the default value `PRIMARY_CAMERA_SOURCE` if you use camera to capture local video.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - A pointer to the `FaceShapeAreaOptions` instance, if the method call succeeds.
   * - NULL is returned, if the method call fails.
   */
  public abstract FaceShapeAreaOptions getFaceShapeAreaOptions(
      int shapeArea, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the filter effect options.
   *
   * @since v4.4.1
   *
   * @details
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method only applies to Android 5.0 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param enabled Whether to enable the filter effect:
   * - `true`: Yes.
   * - `false`: (Default) No.
   * @param options The filter effect options. See `FilterEffectOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setFilterEffectOptions(boolean enabled, FilterEffectOptions options);

  /**
   * @brief Sets the filter effect options and specifies the media source.
   *
   * @since v4.4.1.
   *
   * @details
   * Both this method and `setBeautyEffectOptions(boolean enabled, BeautyOptions options, Constants.MediaSourceType sourceType)` set filter effect options. The difference is
   * that this method allows you to specify the media source to which the filter effect option is
   * applied.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method only applies to Android 5.0 or later.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device.
   *
   * @param enabled Whether to enable the filter effect:
   * - `true`: Yes.
   * - `false`: (Default) No.
   * @param options The filter effect options. See `FilterEffectOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - Use the default value `PRIMARY_CAMERA_SOURCE` if you use camera to capture local video.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setFilterEffectOptions(
      boolean enabled, FilterEffectOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets low-light enhancement.
   *
   * @since v3.6.2
   *
   * @details
   * You can call this method to enable the color enhancement feature and set the options of the color
   * enhancement effect.
   * Applicable scenarios: The low-light enhancement feature can adaptively adjust the brightness
   * value of the video captured in situations with low or uneven lighting, such as backlit, cloudy,
   * or dark scenes. It restores or highlights the image details and improves the overall visual
   * effect of the video.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - Dark light enhancement has certain requirements for equipment performance. The low-light
   * enhancement feature has certain performance requirements on devices. If your device overheats
   * after you enable low-light enhancement, Agora recommends modifying the low-light enhancement
   * options to a less performance-consuming level or disabling low-light enhancement entirely.
   * - If you want to prioritize image quality ( LOW_LIGHT_ENHANCE_LEVEL_HIGH_QUALITY ) when using the
   * low-light enhancement function, you need to first call `setVideoDenoiserOptions(boolean enabled, VideoDenoiserOptions options)` to achieve
   * video noise reduction, the specific corresponding relationship is as follows:
   *   - When low light enhancement is set to automatic mode ( LOW_LIGHT_ENHANCE_AUTO ), video noise
   * reduction needs to be set to prioritize image quality ( VIDEO_DENOISER_LEVEL_HIGH_QUALITY ) and
   * automatic mode ( VIDEO_DENOISER_AUTO ).
   *   - When low-light enhancement is set to manual mode ( LOW_LIGHT_ENHANCE_MANUAL ), video noise
   * reduction needs to be set to prioritize image quality ( VIDEO_DENOISER_LEVEL_HIGH_QUALITY ) and
   * manual mode ( VIDEO_DENOISER_MANUAL ).
   *
   * @param enabled Whether to enable low-light enhancement:
   * - `true`: Enable low-light enhancement.
   * - `false`: (Default) Disable low-light enhancement.
   * @param options The low-light enhancement options. See `LowLightEnhanceOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLowlightEnhanceOptions(boolean enabled, LowLightEnhanceOptions options);

  /**
   * @brief Sets low light enhance options and specifies the media source.
   *
   * @since v3.6.2
   *
   * @details
   * This method and `setLowlightEnhanceOptions(boolean enabled, LowLightEnhanceOptions options)` both set low light enhance options, but this
   * method allows you to specify the media source to which the low light enhance options are applied.
   * Applicable scenarios: dark environments and low-end video capture devices can cause video images
   * to contain significant noise, which affects video quality. In real-time interactive scenarios,
   * video noise also consumes bitstream resources and reduces encoding efficiency during encoding.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - Video noise reduction has certain requirements for equipment performance. If your device
   * overheats after you enable video noise reduction, Agora recommends modifying the video noise
   * reduction options to a less performance-consuming level or disabling video noise reduction
   * entirely.
   *
   * @param enabled Whether to enable low-light enhancement:
   * - `true`: Enable low-light enhancement.
   * - `false`: (Default) Disable low-light enhancement.
   * @param options The low-light enhancement options. See `LowLightEnhanceOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - The default value is `PRIMARY_CAMERA_SOURCE`.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLowlightEnhanceOptions(
      boolean enabled, LowLightEnhanceOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets video noise reduction.
   *
   * @since v3.6.2
   *
   * @details
   * You can call this method to enable the video noise reduction feature and set the options of the
   * video noise reduction effect.
   * Applicable scenarios: dark environments and low-end video capture devices can cause video images
   * to contain significant noise, which affects video quality. In real-time interactive scenarios,
   * video noise also consumes bitstream resources and reduces encoding efficiency during encoding.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - Video noise reduction has certain requirements for equipment performance. If your device
   * overheats after you enable video noise reduction, Agora recommends modifying the video noise
   * reduction options to a less performance-consuming level or disabling video noise reduction
   * entirely.
   * If the noise reduction implemented by this method does not meet your needs, Agora recommends that
   * you call the `setBeautyEffectOptions(boolean enabled, BeautyOptions options)` method to enable the beauty and skin smoothing
   * function to achieve better video noise reduction effects. The recommended `BeautyOptions`
   * settings for intense noise reduction effect are as follows:
   * - `lighteningContrastLevel` LIGHTENING_CONTRAST_NORMAL
   * - `lighteningLevel`: 0.0
   * - `smoothnessLevel`: 0.5
   * - `rednessLevel`: 0.0
   * - `sharpnessLevel`: 0.1
   *
   * @param enabled Whether to enable video noise reduction:
   * - `true`: Enable video noise reduction.
   * - `false`: (Default) Disable video noise reduction.
   * @param options The video noise reduction options. See `VideoDenoiserOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVideoDenoiserOptions(boolean enabled, VideoDenoiserOptions options);

  /**
   * @brief Sets video noise reduction and specifies the media source.
   *
   * @since v3.6.2
   *
   * @details
   * You can call this method to enable the video noise reduction feature and set the options of the
   * video noise reduction effect.
   * Both this method and `setVideoDenoiserOptions(boolean enabled, VideoDenoiserOptions options)` set video noise reduction, but this method
   * allows you to specify the media source to which the noise reduction is applied.
   * Applicable scenarios: dark environments and low-end video capture devices can cause video images
   * to contain significant noise, which affects video quality. In real-time interactive scenarios,
   * video noise also consumes bitstream resources and reduces encoding efficiency during encoding.
   * Call timing: Call this method after calling `enableVideo`.
   *
   * @note
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   * - Video noise reduction has certain requirements for equipment performance. If your device
   * overheats after you enable video noise reduction, Agora recommends modifying the video noise
   * reduction options to a less performance-consuming level or disabling video noise reduction
   * entirely.
   * If the noise reduction implemented by this method does not meet your needs, Agora recommends that
   * you call the `setBeautyEffectOptions(boolean enabled, BeautyOptions options)` method to enable the beauty and skin smoothing
   * function to achieve better video noise reduction effects. The recommended `BeautyOptions`
   * settings for intense noise reduction effect are as follows:
   * - `lighteningContrastLevel` LIGHTENING_CONTRAST_NORMAL
   * - `lighteningLevel`: 0.0
   * - `smoothnessLevel`: 0.5
   * - `rednessLevel`: 0.0
   * - `sharpnessLevel`: 0.1
   *
   * @param enabled Whether to enable video noise reduction:
   * - `true`: Enable video noise reduction.
   * - `false`: (Default) Disable video noise reduction.
   * @param options The video noise reduction options. See `VideoDenoiserOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - The default value is `PRIMARY_CAMERA_SOURCE`.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVideoDenoiserOptions(
      boolean enabled, VideoDenoiserOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets color enhancement.
   *
   * @since v3.6.2
   *
   * @details
   * The video images captured by the camera can have color distortion. The color enhancement feature
   * intelligently adjusts video characteristics such as saturation and contrast to enhance the video
   * color richness and color reproduction, making the video more vivid.
   * You can call this method to enable the color enhancement feature and set the options of the color
   * enhancement effect.
   *
   * @note
   * - Call this method after calling `enableVideo`.
   * - The color enhancement feature has certain performance requirements on devices. With color
   * enhancement turned on, Agora recommends that you change the color enhancement level to one that
   * consumes less performance or turn off color enhancement if your device is experiencing severe
   * heat problems.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param enabled Whether to enable color enhancement:
   * - `true` Enable color enhancement.
   * - `false`: (Default) Disable color enhancement.
   * @param options The color enhancement options. See `ColorEnhanceOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setColorEnhanceOptions(boolean enabled, ColorEnhanceOptions options);

  /**
   * @brief Sets color enhance options and specifies the media source.
   *
   * @since v3.6.2
   *
   * @details
   * The video images captured by the camera can have color distortion. The color enhancement feature
   * intelligently adjusts video characteristics such as saturation and contrast to enhance the video
   * color richness and color reproduction, making the video more vivid.
   * Both this method and `setColorEnhanceOptions(boolean enabled, ColorEnhanceOptions options)` set color enhancement options, but this
   * method allows you to specify the media source to which the color enhance options are applied.
   *
   * @note
   * - Call this method after calling `enableVideo`.
   * - The color enhancement feature has certain performance requirements on devices. With color
   * enhancement turned on, Agora recommends that you change the color enhancement level to one that
   * consumes less performance or turn off color enhancement if your device is experiencing severe
   * heat problems.
   * - This method relies on the image enhancement dynamic library
   * `libagora_clear_vision_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param enabled Whether to enable color enhancement:
   * - `true` Enable color enhancement.
   * - `false`: (Default) Disable color enhancement.
   * @param options The color enhancement options. See `ColorEnhanceOptions`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - The default value is `PRIMARY_CAMERA_SOURCE`.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setColorEnhanceOptions(
      boolean enabled, ColorEnhanceOptions options, Constants.MediaSourceType sourceType);

  /**
   * @brief Creates a video effect object.
   *
   * @since v4.6.0
   *
   * @details
   * Creates an `IVideoEffectObject` video effect object and returns its pointer.
   *
   * @note You must call the `enableVideo` method before calling this method. This method applies to
   * Android 4.4 and later.
   *
   * @param bundlePath The path to the video effect resource package.
   * @param sourceType The media source type, such as PRIMARY_CAMERA_SOURCE. See `MediaSourceType`.
   *
   * @return
   * - The `IVideoEffectObject` object, if the method call succeeds.
   * - `null`, if the method call fails.
   */
  public abstract IVideoEffectObject createVideoEffectObject(
      String bundlePath, Constants.MediaSourceType sourceType);

  /**
   * @brief Destroys a video effect object.
   *
   * @since v4.6.0
   *
   * @note You must call the `enableVideo` method before using this method. This method is applicable
   * to Android 4.4 and later.
   *
   * @param videoEffectObject The video effect object to be destroyed. See `IVideoEffectObject`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int destroyVideoEffectObject(IVideoEffectObject videoEffectObject);

  /**
   * @brief Enables/Disables the virtual background.
   *
   * @details
   * The virtual background feature enables the local user to replace their original background with a
   * static image, dynamic video, blurred background, or portrait-background segmentation to achieve
   * picture-in-picture effect. Once the virtual background feature is enabled, all users in the
   * channel can see the custom background.
   * Call this method after calling `enableVideo` or `startPreview(Constants.VideoSourceType sourceType)`.
   *
   * @note
   * - Using a video as a your virtual background will lead to continuous increase in memory usage,
   * which may cause issues such as app crashes. Therefore,it is recommended to reduce the resolution
   * and frame rate of the video when using it.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device. Agora recommends you use virtual
   * background on devices with the following processors:
   *   - Snapdragon 700 series 750G and later
   *   - Snapdragon 800 series 835 and later
   *   - Dimensity 700 series 720 and later
   *   - Kirin 800 series 810 and later
   *   - Kirin 900 series 980 and later
   * - Agora recommends that you use this feature in scenarios that meet the following conditions:
   *   - A high-definition camera device is used, and the environment is uniformly lit.
   *   - There are few objects in the captured video. Portraits are half-length and unobstructed.
   * Ensure that the background is a solid color that is different from the color of the user's
   * clothing.
   * - This method relies on the virtual background dynamic library
   * `libagora_segmentation_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param enabled Whether to enable virtual background:
   * - `true`: Enable virtual background.
   * - `false`: Disable virtual background.
   * @param backgroundSource The custom background. See `VirtualBackgroundSource`. To adapt the
   * resolution of the custom background image to that of the video captured by the SDK, the SDK
   * scales and crops the custom background image while ensuring that the content of the custom
   * background image is not distorted.
   * @param segproperty Processing properties for background images. See `SegmentationProperty`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The device capabilities do not meet the requirements for the virtual background feature.
   * Agora recommends you try it on devices with higher performance.
   */
  public abstract int enableVirtualBackground(
      boolean enabled, VirtualBackgroundSource backgroundSource, SegmentationProperty segproperty);

  /**
   * @brief Enables virtual background and specify the media source, or disables virtual background.
   *
   * @details
   * The virtual background feature enables the local user to replace their original background with a
   * static image, dynamic video, blurred background, or portrait-background segmentation to achieve
   * picture-in-picture effect. Once the virtual background feature is enabled, all users in the
   * channel can see the custom background.
   * Both this method and `enableVirtualBackground(boolean enabled, VirtualBackgroundSource backgroundSource, SegmentationProperty segproperty)` enable/disable virtual background, but this
   * method allows you to specify the media source to which the virtual background is applied.
   * Call this method after calling `enableVideo` or `startPreview(Constants.VideoSourceType sourceType)`.
   *
   * @note
   * - Using a video as a your virtual background will lead to continuous increase in memory usage,
   * which may cause issues such as app crashes. Therefore,it is recommended to reduce the resolution
   * and frame rate of the video when using it.
   * - This feature has high requirements on device performance. When calling this method, the SDK
   * automatically checks the capabilities of the current device. Agora recommends you use virtual
   * background on devices with the following processors:
   *   - Snapdragon 700 series 750G and later
   *   - Snapdragon 800 series 835 and later
   *   - Dimensity 700 series 720 and later
   *   - Kirin 800 series 810 and later
   *   - Kirin 900 series 980 and later
   * - Agora recommends that you use this feature in scenarios that meet the following conditions:
   *   - A high-definition camera device is used, and the environment is uniformly lit.
   *   - There are few objects in the captured video. Portraits are half-length and unobstructed.
   * Ensure that the background is a solid color that is different from the color of the user's
   * clothing.
   * - This method relies on the virtual background dynamic library
   * `libagora_segmentation_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param enabled Whether to enable virtual background:
   * - `true`: Enable virtual background.
   * - `false`: Disable virtual background.
   * @param backgroundSource The custom background. See `VirtualBackgroundSource`. To adapt the
   * resolution of the custom background image to that of the video captured by the SDK, the SDK
   * scales and crops the custom background image while ensuring that the content of the custom
   * background image is not distorted.
   * @param segproperty Processing properties for background images. See `SegmentationProperty`.
   * @param sourceType The type of the media source to which the filter effect is applied. See
   * `MediaSourceType`.Attention: In this method, this parameter supports only the following two
   * settings:
   * - The default value is `PRIMARY_CAMERA_SOURCE`.
   * - Set this parameter to `CUSTOM_VIDEO_SOURCE` if you use custom video source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -4: The device capabilities do not meet the requirements for the virtual background feature.
   * Agora recommends you try it on devices with higher performance.
   */
  public abstract int enableVirtualBackground(boolean enabled,
      VirtualBackgroundSource backgroundSource, SegmentationProperty segproperty,
      Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the default audio playback route.
   *
   * @details
   * Most mobile phones have two audio routes: an earpiece at the top, and a speakerphone at the
   * bottom. The earpiece plays at a lower volume, and the speakerphone at a higher volume. When
   * setting the default audio route, you determine whether audio playback comes through the earpiece
   * or speakerphone when no external audio device is connected.
   * In different scenarios, the default audio routing of the system is also different. See the
   * following:
   * - Voice call: Earpiece.
   * - Audio broadcast: Speakerphone.
   * - Video call: Speakerphone.
   * - Video broadcast: Speakerphone.
   * You can call this method to change the default audio route.
   * Call timing: Call this method before joining a channel. If you need to change the audio route
   * after joining a channel, call `setEnableSpeakerphone`.
   * Related callbacks: After successfully calling this method, the SDK triggers the
   * `onAudioRouteChanged` callback to report the current audio route.
   *
   * @note After calling this method to set the default audio route, the actual audio route of the
   * system will change with the connection of external audio devices (wired headphones or Bluetooth
   * headphones). See .
   *
   * @param defaultToSpeaker Whether to set the speakerphone as the default audio route:
   * - `true`: Set the speakerphone as the default audio route.
   * - `false`: Set the earpiece as the default audio route.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setDefaultAudioRoutetoSpeakerphone(boolean defaultToSpeaker);

  /**
   * @brief Enables/Disables the audio route to the speakerphone.
   *
   * @details
   * Applicable scenarios: If the default audio route of the SDK or the setting in
   * `setDefaultAudioRouteToSpeakerphone` cannot meet your requirements, you can call this method to
   * switch the current audio route.
   * Call timing: Call this method after joining a channel.
   * Related callbacks: After successfully calling this method, the SDK triggers the
   * `onAudioRouteChanged` callback to report the current audio route.
   *
   * @note
   * - This method only sets the audio route in the current channel and does not influence the default
   * audio route. If the user leaves the current channel and joins another channel, the default audio
   * route is used.
   * - If the user uses an external audio playback device such as a Bluetooth or wired headset, this
   * method does not take effect, and the SDK plays audio through the external device. When the user
   * uses multiple external devices, the SDK plays audio through the last connected device.
   *
   * @param enabled Sets whether to enable the speakerphone or earpiece:
   * - `true`: Enable device state monitoring. The audio route is the speakerphone.
   * - `false`: Disable device state monitoring. The audio route is the earpiece.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setEnableSpeakerphone(boolean enabled);

  /**
   * @brief Selects the audio playback route in communication audio mode.
   *
   * @details
   * This method is used to switch the audio route from Bluetooth headphones to earpiece, wired
   * headphones or speakers in communication audio mode ( `MODE_IN_COMMUNICATION` ).
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After successfully calling this method, the SDK triggers the
   * `onAudioRouteChanged` callback to report the current audio route.
   *
   * @note Using this method and the `setEnableSpeakerphone` method at the same time may cause
   * conflicts. Agora recommends that you use the `setRouteInCommunicationMode` method alone.
   *
   * @param route The audio playback route you want to use:
   * - -1: The default audio route.
   * - 0: Headphones with microphone.
   * - 1: Handset.
   * - 2: Headphones without microphone.
   * - 3: Device's built-in speaker.
   * - 4: (Not supported yet) External speakers.
   * - 5: Bluetooth headphones.
   * - 6: USB device.
   *
   * @return
   * Without practical meaning.
   */
  public abstract int setRouteInCommunicationMode(int route);

  /**
   * @brief Checks whether the speakerphone is enabled.
   *
   * @details
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @return
   * - `true`: The speakerphone is enabled, and the audio plays from the speakerphone.
   * - `false`: The speakerphone is not enabled, and the audio plays from devices other than the
   * speakerphone. For example, the headset or earpiece.
   */
  public abstract boolean isSpeakerphoneEnabled();

  /**
   * @brief Enables in-ear monitoring.
   *
   * @details
   * This method enables or disables in-ear monitoring.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @note Users must use earphones (wired or Bluetooth) to hear the in-ear monitoring effect.
   *
   * @param enabled Enables or disables in-ear monitoring.
   * - `true`: Enables in-ear monitoring.
   * - `false`: (Default) Disables in-ear monitoring.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - - 8: Make sure the current audio routing is Bluetooth or headset.
   */
  public abstract int enableInEarMonitoring(boolean enabled);

  /**
   * @brief Enables in-ear monitoring.
   *
   * @details
   * This method enables or disables in-ear monitoring.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @note Users must use earphones (wired or Bluetooth) to hear the in-ear monitoring effect.
   *
   * @param enabled Enables or disables in-ear monitoring.
   * - `true`: Enables in-ear monitoring.
   * - `false`: (Default) Disables in-ear monitoring.
   * @param includeAudioFilters The audio filter types of in-ear monitoring:
   * - EAR_MONITORING_FILTER_NONE (1 << 0): No audio filter added to in-ear monitoring.
   * - EAR_MONITORING_FILTER_BUILT_IN_AUDIO_FILTERS (1 << 1): Add vocal effects audio filter to in-ear
   * monitoring. If you implement functions such as voice beautifier and audio effect, users can hear
   * the voice after adding these effects. This enumerator supports combination using the bitwise OR
   * operator (|).
   * - EAR_MONITORING_FILTER_NOISE_SUPPRESSION (1 << 2): Add noise suppression audio filter to in-ear
   * monitoring. This enumerator supports combination using the bitwise OR operator (|).
   * - EAR_MONITORING_FILTER_REUSE_POST_PROCESSING_FILTER (1 <<15)： Reuse the audio filter that has
   * been processed on the sending end for in-ear monitoring. This enumerator reduces CPU usage while
   * increasing in-ear monitoring latency, which is suitable for latency-tolerant scenarios requiring
   * low CPU consumption. This enumerator is only supported for standalone use. Once selected, other
   * audio filter configurations will be automatically disabled.
   * Attention: This parameter only takes effect when `enabled` is set as `true`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - - 8: Make sure the current audio routing is Bluetooth or headset.
   */
  public abstract int enableInEarMonitoring(boolean enabled, int includeAudioFilters);

  /**
   * @brief Sets the volume of the in-ear monitor.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param volume The volume of the user. The value range is [0,400].
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: Invalid parameter settings, such as in-ear monitoring volume exceeding the valid range (<
   * 0 or > 400).
   */
  public abstract int setInEarMonitoringVolume(int volume);

  /**
   * @brief Changes the voice pitch of the local speaker.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param pitch The local voice pitch. The value range is [0.5,2.0]. The lower the value, the lower
   * the pitch. The default value is 1.0 (no change to the pitch).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalVoicePitch(double pitch);

  /**
   * @brief Sets the formant ratio to change the timbre of human voice.
   *
   * @details
   * Formant ratio affects the timbre of voice. The smaller the value, the deeper the sound will be,
   * and the larger, the sharper. After you set the formant ratio, all users in the channel can hear
   * the changed voice. If you want to change the timbre and pitch of voice at the same time, Agora
   * recommends using this method together with `setLocalVoicePitch`.
   * Applicable scenarios: You can call this method to set the formant ratio of local audio to change
   * the timbre of human voice.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param formantRatio The formant ratio. The value range is [-1.0, 1.0]. The default value is 0.0,
   * which means do not change the timbre of the voice.Note: Agora recommends setting this value
   * within the range of [-0.4, 0.6]. Otherwise, the voice may be seriously distorted.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalVoiceFormant(double formantRatio);

  /**
   * @brief Sets the local voice equalization effect.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param bandFrequency The band frequency. The value ranges between 0 and 9; representing the
   * respective 10-band center frequencies of the voice effects, including 31, 62, 125, 250, 500, 1k,
   * 2k, 4k, 8k, and 16k Hz. See `AUDIO_EQUALIZATION_BAND_FREQUENCY`.
   * @param bandGain The gain of each band in dB. The value ranges between -15 and 15. The default
   * value is 0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalVoiceEqualization(
      Constants.AUDIO_EQUALIZATION_BAND_FREQUENCY bandFrequency, int bandGain);

  /**
   * @brief Sets the local voice reverberation.
   *
   * @details
   * The SDK provides an easier-to-use method, `setAudioEffectPreset`, to directly implement preset
   * reverb effects for such as pop, R&B, and KTV.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @param reverbKey The reverberation key. Agora provides five reverberation keys, see
   * `AUDIO_REVERB_TYPE`.
   * @param value The value of the reverberation key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalVoiceReverb(Constants.AUDIO_REVERB_TYPE reverbKey, int value);
  /**
   * @brief Sets the preset headphone equalization effect.
   *
   * @details
   * This method is mainly used in spatial audio effect scenarios. You can select the preset headphone
   * equalizer to listen to the audio to achieve the expected audio experience.
   *
   * @note If the headphones you use already have a good equalization effect, you may not get a
   * significant improvement when you call this method, and could even diminish the experience.
   *
   * @param preset The preset headphone equalization effect:
   * - HEADPHONE_EQUALIZER_OFF: The headphone equalizer is disabled, and the original audio is heard.
   * - HEADPHONE_EQUALIZER_OVEREAR: An equalizer is used for headphones.
   * - HEADPHONE_EQUALIZER_INEAR: An equalizer is used for in-ear headphones.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   */
  public abstract int setHeadphoneEQPreset(int preset);
  /**
   * @brief Sets the low- and high-frequency parameters of the headphone equalizer.
   *
   * @details
   * In a spatial audio effect scenario, if the preset headphone equalization effect is not achieved
   * after calling the `setHeadphoneEQPreset` method, you can further adjust the headphone
   * equalization effect by calling this method.
   *
   * @param lowGain The low-frequency parameters of the headphone equalizer. The value range is
   * [-10,10]. The larger the value, the deeper the sound.
   * @param highGain The high-frequency parameters of the headphone equalizer. The value range is
   * [-10,10]. The larger the value, the sharper the sound.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   */
  public abstract int setHeadphoneEQParameters(int lowGain, int highGain);
  /**
   * @brief Sets an SDK preset audio effect.
   *
   * @since v3.2.0
   *
   * @details
   * Call this method to set an SDK preset audio effect for the local user who sends an audio stream.
   * This audio effect does not change the gender characteristics of the original voice. After setting
   * an audio effect, all users in the channel can hear the effect.
   * Call timing: This method can be called either before or after joining the channel.
   * To achieve better vocal effects, it is recommended that you call the following APIs before
   * calling this method:
   * - Call `setAudioScenario` to set the audio scenario to high-quality audio scenario, namely
   * `AUDIO_SCENARIO_GAME_STREAMING` (3).
   * - Call `setAudioProfile(int profile)` to set the `profile` parameter to `MUSIC_HIGH_QUALITY` (4) or
   * `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note
   * - Do not set the `profile` parameter in `setAudioProfile(int profile)` to `SPEECH_STANDARD` (1), or the
   * method does not take effect.
   * - If you call `setAudioEffectPreset` and set enumerators except for `ROOM_ACOUSTICS_3D_VOICE` or
   * `PITCH_CORRECTION`, do not call `setAudioEffectParameters`; otherwise, `setAudioEffectPreset` is
   * overridden.
   * - After calling `setAudioEffectPreset`, Agora does not recommend you to call the following
   * methods, otherwise the effect set by `setAudioEffectPreset` will be overwritten:
   *   - `setVoiceBeautifierPreset`
   *   - `setLocalVoicePitch`
   *   - `setLocalVoiceEqualization`
   *   - `setLocalVoiceReverb`
   *   - `setVoiceBeautifierParameters`
   *   - `setVoiceConversionPreset`
   * - This method relies on the voice beautifier dynamic library
   * `libagora_audio_beauty_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param preset Preset audio effects.
   * - AUDIO_EFFECT_OFF: Turn off audio effects and use the original voice.
   * - ROOM_ACOUSTICS_KTV: The reverberation style typical of a KTV venue.
   * - ROOM_ACOUSTICS_VOCAL_CONCERT: The reverberation style typical of a concert hall.
   * - ROOM_ACOUSTICS_STUDIO: The reverberation style typical of a recording studio.
   * - ROOM_ACOUSTICS_PHONOGRAPH: The reverberation style typical of the vintage phonograph.
   * - ROOM_ACOUSTICS_VIRTUAL_STEREO: A virtual stereo effect that renders monophonic audio as stereo
   * audio. Before using this preset, set the `profile` parameter of `setAudioProfile(int profile)` to
   * MUSIC_HIGH_QUALITY (4) or MUSIC_HIGH_QUALITY_STEREO (5) ; otherwise, the preset setting is
   * invalid.
   * - ROOM_ACOUSTICS_SPACIAL: A more spatial audio effect.
   * - ROOM_ACOUSTICS_ETHEREAL: A more ethereal audio effect.
   * - ROOM_ACOUSTICS_VIRTUAL_SURROUND_SOUND: Virtual surround sound, that is, the SDK generates a
   * simulated surround sound field on the basis of stereo channels, thereby creating a surround sound
   * effect. Attention: If the virtual surround sound is enabled, users need to use stereo audio
   * playback devices to hear the anticipated audio effect.
   * - ROOM_ACOUSTICS_CHORUS: A chorus audio effect. Agora recommends using this effect in chorus
   * scenarios to enhance the sense of depth and dimension in the vocals.
   * - ROOM_ACOUSTICS_3D_VOICE: A 3D voice effect that makes the voice appear to be moving around the
   * user. The default cycle period is 10 seconds. After setting this effect, you can call
   * `setAudioEffectParameters` to modify the movement period.
   *   - Before using this preset, set the `profile` parameter of `setAudioProfile(int profile)` to
   * MUSIC_STANDARD_STEREO (3) or MUSIC_HIGH_QUALITY_STEREO (5); otherwise, the preset setting is
   * invalid.
   *   - If the 3D voice effect is enabled, users need to use stereo audio playback devices to hear
   * the anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_UNCLE: The reverberation style typical of an uncle's voice. Agora
   * recommends using this preset to process a male-sounding voice; otherwise, you may not hear the
   * anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_OLDMAN: The voice of an old man. Agora recommends using this preset to
   * process a male-sounding voice; otherwise, you may not hear the anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_BOY: The voice of a boy. Agora recommends using this preset to process a
   * male-sounding voice; otherwise, you may not hear the anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_SISTER: The voice of a young woman. Agora recommends using this preset to
   * process a female-sounding voice; otherwise, you may not hear the anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_GIRL: The voice of a girl. Agora recommends using this preset to process a
   * female-sounding voice; otherwise, you may not hear the anticipated voice effect.
   * - VOICE_CHANGER_EFFECT_PIGKING: The voice of Pig King, a character in Journey to the West who has
   * a voice like a growling bear.
   * - VOICE_CHANGER_EFFECT_HULK: The voice of the Hulk.
   * - STYLE_TRANSFORMATION_RNB: The reverberation style typical of R&B music. Before using this
   * preset, set the `profile` parameter of `setAudioProfile(int profile)` to MUSIC_HIGH_QUALITY (4) or
   * MUSIC_HIGH_QUALITY_STEREO (5) ; otherwise, the preset setting is invalid.
   * - STYLE_TRANSFORMATION_POPULAR: The reverberation style typical of popular music. Before using
   * this preset, set the `profile` parameter of `setAudioProfile(int profile)` to MUSIC_HIGH_QUALITY (4) or
   * MUSIC_HIGH_QUALITY_STEREO (5) ; otherwise, the preset setting is invalid.
   * - PITCH_CORRECTION: A pitch correction effect that corrects the user's pitch based on the pitch
   * of the natural C major scale. After setting this voice effect, you can call
   * `setAudioEffectParameters` to adjust the basic mode of tuning and the pitch of the main tone.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioEffectPreset(int preset);

  /**
   * @brief Sets a preset voice beautifier effect.
   *
   * @since v3.2.0
   *
   * @details
   * Call this method to set a preset voice beautifier effect for the local user who sends an audio
   * stream. After setting a voice beautifier effect, all users in the channel can hear the effect.
   * You can set different voice beautifier effects for different scenarios.
   * Call timing: This method can be called either before or after joining the channel.
   * To achieve better vocal effects, it is recommended that you call the following APIs before
   * calling this method:
   * - Call `setAudioScenario` to set the audio scenario to high-quality audio scenario, namely
   * `AUDIO_SCENARIO_GAME_STREAMING` (3).
   * - Call `setAudioProfile(int profile)` to set the `profile` parameter to `MUSIC_HIGH_QUALITY` (4) or
   * `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note
   * - Do not set the `profile` parameter in `setAudioProfile(int profile)` to `SPEECH_STANDARD` (1), or the
   * method does not take effect.
   * - This method has the best effect on human voice processing, and Agora does not recommend calling
   * this method to process audio data containing music.
   * - After calling `setVoiceBeautifierPreset`, Agora does not recommend calling the following
   * methods, otherwise the effect set by `setVoiceBeautifierPreset` will be overwritten:
   *   - `setAudioEffectPreset`
   *   - `setAudioEffectParameters`
   *   - `setLocalVoicePitch`
   *   - `setLocalVoiceEqualization`
   *   - `setLocalVoiceReverb`
   *   - `setVoiceBeautifierParameters`
   *   - `setVoiceConversionPreset`
   * - This method relies on the voice beautifier dynamic library
   * `libagora_audio_beauty_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param preset The preset voice beautifier effect options.
   * - VOICE_BEAUTIFIER_OFF: Turn off voice beautifier effects and use the original voice.
   * - CHAT_BEAUTIFIER_MAGNETIC: A more magnetic voice (Male only).
   * - CHAT_BEAUTIFIER_FRESH: A fresher voice (Female only).
   * - CHAT_BEAUTIFIER_VITALITY: A more vital voice (Female only).
   * - SINGING_BEAUTIFIER: The singing beautifier effect.
   *   - If you call `setVoiceBeautifierPreset` ( SINGING_BEAUTIFIER ), you can beautify a
   * male-sounding voice and add a reverberation effect that sounds like singing in a small room.
   * Agora recommends using this enumerator to process a male-sounding voice; otherwise, you might
   * experience vocal distortion.
   *   - If you call `setVoiceBeautifierParameters` ( SINGING_BEAUTIFIER, param1, param2), you can
   * beautify a male- or female-sounding voice and add a reverberation effect.
   * - TIMBRE_TRANSFORMATION_VIGOROUS: A more rigorous vice.
   * - TIMBRE_TRANSFORMATION_DEEP: A deep voice.
   * - TIMBRE_TRANSFORMATION_MELLOW: A mellower voice.
   * - TIMBRE_TRANSFORMATION_FALSETTO: Falsetto.
   * - TIMBRE_TRANSFORMATION_FULL: A fuller voice.
   * - TIMBRE_TRANSFORMATION_CLEAR: A clearer voice.
   * - TIMBRE_TRANSFORMATION_RESOUNDING: A resounding voice.
   * - TIMBRE_TRANSFORMATION_RINGING: A more ringing voice.
   * - ULTRA_HIGH_QUALITY_VOICE: Ultra-high quality voice, which makes the audio clearer and restores
   * more details.
   *   - To achieve better audio effect quality, Agora recommends that you call `setAudioProfile(int profile)`
   * and set the `profile` to `MUSIC_HIGH_QUALITY` (4) or `MUSIC_HIGH_QUALITY_STEREO` (5) and
   * `scenario ` to `AUDIO_SCENARIO_GAME_STREAMING` (3) before calling this method.
   *   - If you have an audio capturing device that can already restore audio details to a high
   * degree, Agora recommends that you do not enable ultra-high quality; otherwise, the SDK may
   * over-restore audio details, and you may not hear the anticipated voice effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVoiceBeautifierPreset(int preset);
  /**
   * @brief Sets a preset voice beautifier effect.
   *
   * @since v3.3.1
   *
   * @details
   * Call this method to set a preset voice changing effect for the local user who publishes an audio
   * stream in a channel. After setting the voice changing effect, all users in the channel can hear
   * the effect. You can set different voice changing effects for the user depending on different
   * scenarios.
   * Call timing: This method can be called either before or after joining the channel.
   * To achieve better vocal effects, it is recommended that you call the following APIs before
   * calling this method:
   * - Call `setAudioScenario` to set the audio scenario to high-quality audio scenario, namely
   * `AUDIO_SCENARIO_GAME_STREAMING` (3).
   * - Call `setAudioProfile(int profile)` to set the `profile` parameter to `MUSIC_HIGH_QUALITY` (4) or
   * `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note
   * - Do not set the `profile` parameter in `setAudioProfile(int profile)` to `SPEECH_STANDARD` (1), or the
   * method does not take effect.
   * - This method has the best effect on human voice processing, and Agora does not recommend calling
   * this method to process audio data containing music.
   * - After calling `setVoiceConversionPreset`, Agora does not recommend you to call the following
   * methods, otherwise the effect set by `setVoiceConversionPreset` will be overwritten:
   *   - `setAudioEffectPreset`
   *   - `setAudioEffectParameters`
   *   - `setVoiceBeautifierPreset`
   *   - `setVoiceBeautifierParameters`
   *   - `setLocalVoicePitch`
   *   - `setLocalVoiceFormant`
   *   - `setLocalVoiceEqualization`
   *   - `setLocalVoiceReverb`
   * - This method relies on the voice beautifier dynamic library
   * `libagora_audio_beauty_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param preset The options for SDK preset voice conversion effects.
   * - VOICE_CONVERSION_OFF: Turn off voice conversion effects and use the original voice.
   * - VOICE_CHANGER_NEUTRAL: A gender-neutral voice. To avoid audio distortion, ensure that you use
   * this enumerator to process a female-sounding voice.
   * - VOICE_CHANGER_SWEET: A sweet voice. To avoid audio distortion, ensure that you use this
   * enumerator to process a female-sounding voice.
   * - VOICE_CHANGER_SOLID: A steady voice. To avoid audio distortion, ensure that you use this
   * enumerator to process a male-sounding voice.
   * - VOICE_CHANGER_BASS: A deep voice. To avoid audio distortion, ensure that you use this
   * enumerator to process a male-sounding voice.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVoiceConversionPreset(int preset);
  /**
   * @brief Sets parameters for SDK preset audio effects.
   *
   * @since v3.2.0
   *
   * @details
   * Call this method to set the following parameters for the local user who sends an audio stream:
   * - 3D voice effect: Sets the cycle period of the 3D voice effect.
   * - Pitch correction effect: Sets the basic mode and tonic pitch of the pitch correction effect.
   * Different songs have different modes and tonic pitches. Agora recommends bounding this method
   * with interface elements to enable users to adjust the pitch correction interactively.
   * After setting the audio parameters, all users in the channel can hear the effect.
   * To achieve better vocal effects, it is recommended that you call the following APIs before
   * calling this method:
   * - Call `setAudioScenario` to set the audio scenario to high-quality audio scenario, namely
   * `AUDIO_SCENARIO_GAME_STREAMING` (3).
   * - Call `setAudioProfile(int profile)` to set the `profile` parameter to `MUSIC_HIGH_QUALITY` (4) or
   * `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - Do not set the `profile` parameter in `setAudioProfile(int profile)` to `SPEECH_STANDARD` (1), or the
   * method does not take effect.
   * - This method has the best effect on human voice processing, and Agora does not recommend calling
   * this method to process audio data containing music.
   * - After calling `setAudioEffectParameters`, Agora does not recommend you to call the following
   * methods, otherwise the effect set by `setAudioEffectParameters` will be overwritten:
   *   - `setAudioEffectPreset`
   *   - `setVoiceBeautifierPreset`
   *   - `setLocalVoicePitch`
   *   - `setLocalVoiceEqualization`
   *   - `setLocalVoiceReverb`
   *   - `setVoiceBeautifierParameters`
   *   - `setVoiceConversionPreset`
   * - This method relies on the voice beautifier dynamic library
   * `libagora_audio_beauty_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param preset The options for SDK preset audio effects:
   * - `ROOM_ACOUSTICS_3D_VOICE`, 3D voice effect:
   *   - You need to set the `profile` parameter in `setAudioProfile(int profile)` to `MUSIC_STANDARD_STEREO`
   * (3) or `MUSIC_HIGH_QUALITY_STEREO` (5) before setting this enumerator; otherwise, the enumerator
   * setting does not take effect.
   *   - If the 3D voice effect is enabled, users need to use stereo audio playback devices to hear
   * the anticipated voice effect.
   * - `PITCH_CORRECTION`, Pitch correction effect:
   * @param param1 - If you set `preset` to `ROOM_ACOUSTICS_3D_VOICE`, `param1` sets the cycle period
   * of the 3D voice effect. The value range is [1,60] and the unit is seconds. The default value is
   * 10, indicating that the voice moves around you every 10 seconds.
   * - If you set `preset` to `PITCH_CORRECTION`, `param1` indicates the basic mode of the pitch
   * correction effect:
   *   - `1`: (Default) Natural major scale.
   *   - `2`: Natural minor scale.
   *   - `3`: Japanese pentatonic scale.
   * @param param2 - If you set `preset` to `ROOM_ACOUSTICS_3D_VOICE` , you need to set `param2` to
   * `0`.
   * - If you set `preset` to `PITCH_CORRECTION`, `param2` indicates the tonic pitch of the pitch
   * correction effect:
   *   - `1`: A
   *   - `2`: A#
   *   - `3`: B
   *   - `4`: (Default) C
   *   - `5`: C#
   *   - `6`: D
   *   - `7`: D#
   *   - `8`: E
   *   - `9`: F
   *   - `10`: F#
   *   - `11`: G
   *   - `12`: G#
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioEffectParameters(int preset, int param1, int param2);

  /**
   * @brief Sets parameters for the preset voice beautifier effects.
   *
   * @since 3.3.0.
   *
   * @details
   * Call this method to set a gender characteristic and a reverberation effect for the singing
   * beautifier effect. This method sets parameters for the local user who sends an audio stream.
   * After setting the audio parameters, all users in the channel can hear the effect.
   * To achieve better vocal effects, it is recommended that you call the following APIs before
   * calling this method:
   * - Call `setAudioScenario` to set the audio scenario to high-quality audio scenario, namely
   * `AUDIO_SCENARIO_GAME_STREAMING` (3).
   * - Call `setAudioProfile(int profile)` to set the `profile` parameter to `MUSIC_HIGH_QUALITY` (4) or
   * `MUSIC_HIGH_QUALITY_STEREO` (5).
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - Do not set the `profile` parameter in `setAudioProfile(int profile)` to `SPEECH_STANDARD` (1), or the
   * method does not take effect.
   * - This method has the best effect on human voice processing, and Agora does not recommend calling
   * this method to process audio data containing music.
   * - After calling `setVoiceBeautifierParameters`, Agora does not recommend calling the following
   * methods, otherwise the effect set by `setVoiceBeautifierParameters` will be overwritten:
   *   - `setAudioEffectPreset`
   *   - `setAudioEffectParameters`
   *   - `setVoiceBeautifierPreset`
   *   - `setLocalVoicePitch`
   *   - `setLocalVoiceEqualization`
   *   - `setLocalVoiceReverb`
   *   - `setVoiceConversionPreset`
   * - This method relies on the voice beautifier dynamic library
   * `libagora_audio_beauty_extension.so`. If the dynamic library is deleted, the function cannot be
   * enabled normally.
   *
   * @param preset The option for the preset audio effect:
   * - `SINGING_BEAUTIFIER`: The singing beautifier effect.
   * @param param1 The gender characteristics options for the singing voice:
   * - `1`: A male-sounding voice.
   * - `2`: A female-sounding voice.
   * @param param2 The reverberation effect options for the singing voice:
   * - `1`: The reverberation effect sounds like singing in a small room.
   * - `2`: The reverberation effect sounds like singing in a large room.
   * - `3`: The reverberation effect sounds like singing in a hall.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVoiceBeautifierParameters(int preset, int param1, int param2);

  /**
   * Set parameters for SDK preset voice conversion.
   *
   * @note
   * - reserved interface
   *
   * @param preset The options for SDK preset audio effects. See #VOICE_CONVERSION_PRESET.
   * @param param1 reserved.
   * @param param2 reserved.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVoiceConversionParameters(int preset, int param1, int param2);

  /**
   * @brief Enables or disables stereo panning for remote users.
   *
   * @details
   * Ensure that you call this method before joining a channel to enable stereo panning for remote
   * users so that the local user can track the position of a remote user by calling
   * `setRemoteVoicePosition`.
   *
   * @param enabled Whether to enable stereo panning for remote users:
   * - `true`: Enable stereo panning.
   * - `false`: Disable stereo panning.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableSoundPositionIndication(boolean enabled);

  /**
   * @brief Sets the 2D position (the position on the horizontal plane) of the remote user's voice.
   *
   * @details
   * This method sets the 2D position and volume of a remote user, so that the local user can easily
   * hear and identify the remote user's position.
   * When the local user calls this method to set the voice position of a remote user, the voice
   * difference between the left and right channels allows the local user to track the real-time
   * position of the remote user, creating a sense of space. This method applies to massive
   * multiplayer online games, such as Battle Royale games.
   *
   * @note
   * - For this method to work, enable stereo panning for remote users by calling the
   * `enableSoundPositionIndication` method before joining a channel.
   * - For the best voice positioning, Agora recommends using a wired headset.
   * - Call this method after joining a channel.
   *
   * @param uid The user ID of the remote user.
   * @param pan The voice position of the remote user. The value ranges from -1.0 to 1.0:
   * - 0.0: (Default) The remote voice comes from the front.
   * - -1.0: The remote voice comes from the left.
   * - 1.0: The remote voice comes from the right.
   * @param gain The volume of the remote user. The value ranges from 0.0 to 100.0. The default value
   * is 100.0 (the original volume of the remote user). The smaller the value, the lower the volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVoicePosition(int uid, double pan, double gain);

  /**
   * @brief Enables or disables the voice AI tuner.
   *
   * @details
   * The voice AI tuner supports enhancing sound quality and adjusting tone style.
   * Applicable scenarios: Social entertainment scenes including online KTV, online podcast and live
   * streaming in showrooms, where high sound quality is required.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param enabled Whether to enable the voice AI tuner:
   * - `true`: Enables the voice AI tuner.
   * - `false`: (Default) Disable the voice AI tuner.
   * @param type Voice AI tuner sound types, see `VOICE_AI_TUNER_TYPE`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableVoiceAITuner(boolean enabled, Constants.VOICE_AI_TUNER_TYPE type);

  /**
   * @brief Starts playing the music file.
   *
   * @deprecated This method is deprecated. Use `startAudioMixing(String filePath, boolean loopback, int cycle)` instead.
   * @details
   * This method supports playing URI files starting with `content://`. For the audio file formats
   * supported by this method, see ` What formats of audio files does the Agora RTC SDK support`. If
   * the local music file does not exist, the SDK does not support the file format, or the the SDK
   * cannot access the music file URL, the SDK reports AUDIO_MIXING_REASON_CAN_NOT_OPEN.
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: A successful method call triggers the `onAudioMixingStateChanged`
   * (`AUDIO_MIXING_STATE_PLAYING`) callback. When the audio mixing file playback finishes, the SDK
   * triggers the `onAudioMixingStateChanged` (`AUDIO_MIXING_STATE_STOPPED`) callback on the local
   * client.
   *
   * @note
   * - If you call this method to play short sound effect files, you may encounter playback failure.
   * Agora recommends using `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)` instead to play such files.
   * - If you need to call this method multiple times, ensure that the time interval between calling
   * this method is more than 500 ms.
   * - On Android, there are following considerations:
   *   - To use this method, ensure that the Android device is v4.2 or later, and the API version is
   * v16 or later.
   *   - If you need to play an online music file, Agora does not recommend using the redirected URL
   * address. Some Android devices may fail to open a redirected URL address.
   *   - If you call this method on an emulator, ensure that the music file is in the `/sdcard/`
   * directory and the format is MP3.
   *
   * @param filePath The file path. The SDK supports URI addresses starting with `content://`, paths
   * starting with `/assets/`, URLs and absolute paths of local files. The absolute path needs to be
   * accurate to the file name and extension. Supported audio formats include MP3, AAC, M4A, MP4, WAV,
   * and 3GP. See [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * Attention: If you have preloaded an audio effect into memory by calling `preloadEffect`, ensure
   * that the value of this parameter is the same as that of `filePath` in `preloadEffect`.
   * @param loopback Whether to only play music files on the local client:
   * - `true`: Only play music files on the local client so that only the local user can hear the
   * music.
   * - `false`: Publish music files to remote clients so that both the local user and remote users can
   * hear the music.
   * @param cycle The number of times the music file plays.
   * - > 0: The number of times for playback. For example, 1 represents playing 1 time.
   * - -1: Play the audio file in an infinite loop.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -3: The SDK is not ready.
   *     - The audio module is disabled.
   *     - The program is not complete.
   *     - The initialization of `RtcEngine` fails. Reinitialize the `RtcEngine`.
   */
  public abstract int startAudioMixing(String filePath, boolean loopback, int cycle);

  /**
   * @brief Enables or disables the spatial audio effect.
   *
   * @details
   * After enabling the spatial audio effect, you can call `setRemoteUserSpatialAudioParams` to set
   * the spatial audio effect parameters of the remote user.
   *
   * @note
   * - You can call this method either before or after joining a channel.
   * - This method relies on the spatial audio dynamic library `libagora_spatial_audio_extension.so`.
   * If the dynamic library is deleted, the function cannot be enabled normally.
   *
   * @param enabled Whether to enable the spatial audio effect:
   * - `true`: Enable the spatial audio effect.
   * - `false`: Disable the spatial audio effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableSpatialAudio(boolean enabled);

  /**
   * @brief Sets the spatial audio effect parameters of the remote user.
   *
   * @details
   * Call this method after `enableSpatialAudio`. After successfully setting the spatial audio effect
   * parameters of the remote user, the local user can hear the remote user with a sense of space.
   *
   * @param uid The user ID. This parameter must be the same as the user ID passed in when the user
   * joined the channel.
   * @param params The spatial audio parameters. See `SpatialAudioParams`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteUserSpatialAudioParams(int uid, SpatialAudioParams params);

  /**
   * @brief Options for subscribing to remote video streams.
   *
   * @details
   * When a remote user has enabled dual-stream mode, you can call this method to choose the option
   * for subscribing to the video streams sent by the remote user. The default subscription behavior
   * of the SDK for remote video streams depends on the type of registered video observer:
   * - If the `IVideoFrameObserver` observer is registered, the default is to subscribe to both raw
   * data and encoded data.
   * - If the `IVideoEncodedFrameObserver` observer is registered, the default is to subscribe only to
   * the encoded data.
   * - If both types of observers are registered, the default behavior follows the last registered
   * video observer. For example, if the last registered observer is the `IVideoFrameObserver`
   * observer, the default is to subscribe to both raw data and encoded data.
   * If you want to modify the default behavior, or set different subscription options for different
   * `uids`, you can call this method to set it.
   *
   * @param uid The user ID of the remote user.
   * @param options The video subscription options. See `VideoSubscriptionOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVideoSubscriptionOptions(int uid, VideoSubscriptionOptions options);

  /**
   * @brief Sets whether to enable the AI ​​noise suppression function and set the noise suppression
   * mode.
   *
   * @details
   * You can call this method to enable AI noise suppression function. Once enabled, the SDK
   * automatically detects and reduces stationary and non-stationary noise from your audio on the
   * premise of ensuring the quality of human voice. Stationary noise refers to noise signal with
   * constant average statistical properties and negligibly small fluctuations of level within the
   * period of observation. Common sources of stationary noises are:
   * - Television;
   * - Air conditioner;
   * - Machinery, etc.
   * Non-stationary noise refers to noise signal with huge fluctuations of level within the period of
   * observation; common sources of non-stationary noises are:
   * - Thunder;
   * - Explosion;
   * - Cracking, etc.
   * Applicable scenarios: In scenarios such as co-streaming, online education and video meeting, this
   * function can detect and reduce background noises to improve experience.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note
   * - This method relies on the AI noise suppression dynamic library
   * `libagora_ai_noise_suppression_extension.so`. If the dynamic library is deleted, the function
   * cannot be enabled.
   * - Agora does not recommend enabling this function on devices running Android 6.0 and below.
   *
   * @param enabled Whether to enable the AI noise suppression function:
   * - `true`: Enable the AI noise suppression.
   * - `false`: (Default) Disable the AI noise suppression.
   * @param mode The AI noise suppression modes:
   * - 0: (Default) Balance mode. This mode allows for a balanced performance on noice suppression and
   * time delay.
   * - 1: Aggressive mode. In scenarios where high performance on noise suppression is required, such
   * as live streaming outdoor events, this mode reduces nosies more dramatically, but sometimes may
   * affect the original character of the audio.
   * - 2: Aggressive mode with low latency. The noise suppression delay of this mode is about only
   * half of that of the balance and aggressive modes. It is suitable for scenarios that have high
   * requirements on noise suppression with low latency, such as sing together online in real time.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAINSMode(boolean enabled, int mode);

  /**
   * @brief Starts playing the music file.
   *
   * @details
   * This method supports playing URI files starting with `content://`. For the audio file formats
   * supported by this method, see ` What formats of audio files does the Agora RTC SDK support`. If
   * the local music file does not exist, the SDK does not support the file format, or the the SDK
   * cannot access the music file URL, the SDK reports AUDIO_MIXING_REASON_CAN_NOT_OPEN.
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: A successful method call triggers the `onAudioMixingStateChanged`
   * (`AUDIO_MIXING_STATE_PLAYING`) callback. When the audio mixing file playback finishes, the SDK
   * triggers the `onAudioMixingStateChanged` (`AUDIO_MIXING_STATE_STOPPED`) callback on the local
   * client.
   *
   * @note
   * - If you call this method to play short sound effect files, you may encounter playback failure.
   * Agora recommends using `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)` instead to play such files.
   * - If you need to call this method multiple times, ensure that the time interval between calling
   * this method is more than 500 ms.
   * - On Android, there are following considerations:
   *   - To use this method, ensure that the Android device is v4.2 or later, and the API version is
   * v16 or later.
   *   - If you need to play an online music file, Agora does not recommend using the redirected URL
   * address. Some Android devices may fail to open a redirected URL address.
   *   - If you call this method on an emulator, ensure that the music file is in the `/sdcard/`
   * directory and the format is MP3.
   *
   * @param filePath File path:
   * - Android: The file path, which needs to be accurate to the file name and suffix. Agora supports
   * URL addresses, absolute paths, or file paths that start with `/assets/`. You might encounter
   * permission issues if you use an absolute path to access a local file, so Agora recommends using a
   * URI address instead. For example:
   * `content://com.android.providers.media.documents/document/audio%3A14441`
   * @param loopback Whether to only play music files on the local client:
   * - `true`: Only play music files on the local client so that only the local user can hear the
   * music.
   * - `false`: Publish music files to remote clients so that both the local user and remote users can
   * hear the music.
   * @param cycle The number of times the music file plays.
   * - > 0: The number of times for playback. For example, 1 represents playing 1 time.
   * - -1: Play the audio file in an infinite loop.
   * @param startPos The playback position (ms) of the music file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -3: The SDK is not ready.
   *     - The audio module is disabled.
   *     - The program is not complete.
   *     - The initialization of `RtcEngine` fails. Reinitialize the `RtcEngine`.
   */
  public abstract int startAudioMixing(String filePath, boolean loopback, int cycle, int startPos);

  /**
   * @brief Stops playing the music file.
   *
   * @details
   * After calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` to play a music file, you can call this method to stop the
   * playing. If you only need to pause the playback, call `pauseAudioMixing`.
   * Call timing: Call this method after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopAudioMixing();

  /**
   * @brief Pauses playing and mixing the music file.
   *
   * @details
   * After calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` to play a music file, you can call this method to pause
   * the playing. If you need to stop the playback, call `stopAudioMixing`.
   * Call timing: Call this method after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pauseAudioMixing();

  /**
   * @brief Resumes playing and mixing the music file.
   *
   * @details
   * After calling `pauseAudioMixing` to pause the playback, you can call this method to resume the
   * playback.
   * Call timing: Call this method after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int resumeAudioMixing();

  /**
   * @brief Adjusts the volume during audio mixing.
   *
   * @details
   * This method adjusts the audio mixing volume on both the local client and remote clients.
   * Call timing: Call this method after `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)`.
   *
   * @note This method does not affect the volume of the audio file set in the `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`
   * method.
   *
   * @param volume Audio mixing volume. The value ranges between 0 and 100. The default value is 100,
   * which means the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustAudioMixingVolume(int volume);

  /**
   * @brief Adjusts the volume of audio mixing for local playback.
   *
   * @details
   * Call timing: You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving
   * the `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @param volume The volume of audio mixing for local playback. The value ranges between 0 and 100
   * (default). 100 represents the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustAudioMixingPlayoutVolume(int volume);

  /**
   * @brief Adjusts the volume of audio mixing for publishing.
   *
   * @details
   * This method adjusts the volume of audio mixing for publishing (sending to other users).
   * Call timing: Call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @param volume The volume of audio mixing for local playback. The value ranges between 0 and 100
   * (default). 100 represents the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustAudioMixingPublishVolume(int volume);

  /**
   * @brief Retrieves the audio mixing volume for local playback.
   *
   * @details
   * You can call this method to get the local playback volume of the mixed audio file, which helps in
   * troubleshooting volume‑related issues.
   * Call timing: Call this method after `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @return
   * - ≥ 0: The audio mixing volume, if this method call succeeds. The value range is [0,100].
   * - < 0: Failure.
   */
  public abstract int getAudioMixingPlayoutVolume();

  /**
   * @brief Retrieves the audio mixing volume for publishing.
   *
   * @details
   * This method helps troubleshoot audio volume‑related issues.
   *
   * @note You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @return
   * - ≥ 0: The audio mixing volume, if this method call succeeds. The value range is [0,100].
   * - < 0: Failure.
   */
  public abstract int getAudioMixingPublishVolume();

  /**
   * @brief Retrieves the duration (ms) of the music file.
   *
   * @details
   * Retrieves the total duration (ms) of the audio.
   * Call timing: Call this method after `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @return
   * - ≥ 0: The audio mixing duration, if this method call succeeds.
   * - < 0: Failure.
   */
  public abstract int getAudioMixingDuration();

  /**
   * @brief Retrieves the playback position (ms) of the music file.
   *
   * @details
   * Retrieves the playback position (ms) of the audio.
   *
   * @note
   * - You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   * - If you need to call `getAudioMixingCurrentPosition` multiple times, ensure that the time
   * interval between calling this method is more than 500 ms.
   *
   * @return
   * - ≥ 0: The current playback position (ms) of the audio mixing, if this method call succeeds. 0
   * represents that the current music file does not start playing.
   * - < 0: Failure.
   */
  public abstract int getAudioMixingCurrentPosition();

  /**
   * @brief Sets the audio mixing position.
   *
   * @details
   * Call this method to set the playback position of the music file to a different starting position
   * (the default plays from the beginning).
   * Call timing: Call this method after `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @param pos Integer. The playback position (ms).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioMixingPosition(int pos);

  /**
   * @brief Sets the channel mode of the current audio file.
   *
   * @details
   * In a stereo music file, the left and right channels can store different audio data. According to
   * your needs, you can set the channel mode to original mode, left channel mode, right channel mode,
   * or mixed channel mode.
   * Applicable scenarios: For example, in the KTV scenario, the left channel of the music file stores
   * the musical accompaniment, and the right channel stores the original singer's vocals. You can set
   * according to actual needs:
   * - If you only want to hear the accompaniment, use this method to set the audio file's channel
   * mode to left channel mode.
   * - If you need to hear both the accompaniment and the original vocals simultaneously, call this
   * method to set the channel mode to mixed mode.
   * Call timing: Call this method after `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @note This method only applies to stereo audio files.
   *
   * @param mode The channel mode. See `AudioMixingDualMonoMode`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioMixingDualMonoMode(Constants.AudioMixingDualMonoMode mode);

  /**
   * @brief Sets the pitch of the local music file.
   *
   * @details
   * When a local music file is mixed with a local human voice, call this method to set the pitch of
   * the local music file only.
   * Call timing: You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving
   * the `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @param pitch Sets the pitch of the local music file by the chromatic scale. The default value is
   * 0, which means keeping the original pitch. The value ranges from -12 to 12, and the pitch value
   * between consecutive values is a chromatic value. The greater the absolute value of this
   * parameter, the higher or lower the pitch of the local music file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioMixingPitch(int pitch);

  /**
   * @brief Sets the playback speed of the current audio file.
   *
   * @details
   * Ensure you call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` receiving the
   * `onAudioMixingStateChanged` callback reporting the state as `AUDIO_MIXING_STATE_PLAYING`.
   *
   * @param speed The playback speed. Agora recommends that you set this to a value between 50 and
   * 400, defined as follows:
   * - 50: Half the original speed.
   * - 100: The original speed.
   * - 400: 4 times the original speed.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAudioMixingPlaybackSpeed(int speed);

  /**
   * @brief Selects the audio track used during playback.
   *
   * @details
   * After getting the track index of the audio file, you can call this method to specify any track to
   * play. For example, if different tracks of a multi-track file store songs in different languages,
   * you can call this method to set the playback language.
   *
   * @note
   * - For the supported formats of audio files, see
   * `https://docs.agora.io/en/help/general-product-inquiry/audio_format#extended-audio-file-formats`.
   * - You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @param audioIndex The audio track you want to specify. The value should be greater than 0 and
   * less than that of returned by `getAudioTrackCount`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int selectAudioTrack(int audioIndex);

  /**
   * @brief Gets the index of audio tracks of the current music file.
   *
   * @note You need to call this method after calling `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` and receiving the
   * `onAudioMixingStateChanged` `(AUDIO_MIXING_STATE_PLAYING)` callback.
   *
   * @return
   * - The SDK returns the index of the audio tracks if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int getAudioTrackCount();

  /**
   * @brief Gets the `IAudioEffectManager` class to manage the audio effect files.
   *
   * @return
   * `IAudioEffectManager`
   */
  public abstract IAudioEffectManager getAudioEffectManager();

  /**
   * @brief Starts client-side recording.
   *
   * @details
   * The SDK supports recording on the client side during a call. This method records the audio of all users in the channel and generates a recording file that contains all users' voices. The recording file only supports the following formats:
   * - `.wav`: Large file size with high audio fidelity.
   * - `.aac`: Smaller file size with some loss of audio fidelity.
   * 
   * Make sure that the specified directory exists and is writable. You must call this API after `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`. If you call `leaveChannel(LeaveChannelOptions options)` while recording is in progress, the recording stops automatically.
   *
   * @note When you call this method, the default recording sample rate is 32 kHz and cannot be changed.
   *
   * @param filePath The absolute path where the recording file is saved locally, including the file name and extension. For example: `/sdcard/emulated/0/audio.aac`.
   *                     Note:
   * Make sure that the specified path exists and is writable.
   * @param quality Recording quality.
   * - 0: Low quality. Sample rate is 32 kHz. The file size for 10 minutes of recording is approximately 1.2 MB.
   * - 1: Medium quality. Sample rate is 32 kHz. The file size for 10 minutes of recording is approximately 2 MB.
   * - 2: High quality. Sample rate is 32 kHz. The file size for 10 minutes of recording is approximately 3.75 MB.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting.
   */
  public abstract int startAudioRecording(String filePath, int quality);

  /**
   * @brief Starts client-side recording and applies recording configurations.
   *
   * @details
   * The SDK supports recording on the client side during a voice calling, voice-only interaction. After calling this method, you can record the audio of users in the channel and obtain a recording file. The recording file supports the following formats only:
   * - WAV: Higher audio fidelity with larger file size. For example, with a sampling rate of 32000 Hz, a 10-minute recording results in a file of approximately 73 MB.
   * - AAC: Lower audio fidelity with smaller file size. For example, with a sampling rate of 32000 Hz and a recording quality of `AUDIO_RECORDING_QUALITY_MEDIUM`, a 10-minute recording results in a file of approximately 2 MB.
   * Recording automatically stops when the user leaves the channel.
   * Call timing: You must call this method after joining the channel.
   *
   * @param config Recording configuration. See `AudioRecordingConfiguration` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Code` for details and troubleshooting tips.
   */
  public abstract int startAudioRecording(AudioRecordingConfiguration config);

  /**
   * @brief Stops client-side recording.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int stopAudioRecording();

  /**
   * @brief Starts an audio device loopback test.
   *
   * @details
   * To test whether the user's local sending and receiving streams are normal, you can call this
   * method to perform an audio and video call loop test, which tests whether the audio and video
   * devices and the user's upstream and downstream networks are working properly.
   * After starting the test, the user needs to make a sound or face the camera. The audio or video is
   * output after about two seconds. If the audio playback is normal, the audio device and the user's
   * upstream and downstream networks are working properly; if the video playback is normal, the video
   * device and the user's upstream and downstream networks are working properly.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note
   * - When calling in a channel, make sure that no audio or video stream is being published.
   * - After calling this method, call `stopEchoTest` to end the test; otherwise, the user cannot
   * perform the next audio and video call loop test and cannot join the channel.
   * - In live streaming scenarios, this method only applies to hosts.
   *
   * @param config The configuration of the audio and video call loop test. See
   * `EchoTestConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startEchoTest(EchoTestConfiguration config);

  /**
   * @brief Stops the audio call test.
   *
   * @details
   * After calling `startEchoTest`, you must call this method to end the test; otherwise, the user
   * cannot perform the next audio and video call loop test and cannot join the channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5(ERR_REFUSED): Failed to stop the echo test. The echo test may not be running.
   */
  public abstract int stopEchoTest();

  /**
   * @brief Starts the last mile network probe test.
   *
   * @details
   * This method starts the last-mile network probe test before joining a channel to get the uplink
   * and downlink last mile network statistics, including the bandwidth, packet loss, jitter, and
   * round-trip time (RTT).
   * Call timing: Do not call other methods before receiving the `onLastmileQuality` and
   * `onLastmileProbeResult` callbacks. Otherwise, the callbacks may be interrupted.
   * Related callbacks: After successfully calling this method, the SDK sequentially triggers the
   * following 2 callbacks:
   * - `onLastmileQuality`: The SDK triggers this callback within two seconds depending on the network
   * conditions. This callback rates the network conditions and is more closely linked to the user
   * experience.
   * - `onLastmileProbeResult`: The SDK triggers this callback within 30 seconds depending on the
   * network conditions. This callback returns the real-time statistics of the network conditions and
   * is more objective.
   *
   * @param config The configurations of the last-mile network probe test. See `LastmileProbeConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startLastmileProbeTest(LastmileProbeConfig config);

  /**
   * @brief Stops the last mile network probe test.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopLastmileProbeTest();

  /**
   * @brief Sets the external audio source.
   *
   * @deprecated This method is deprecated. Use createCustomAudioTrack(Constants.AudioTrackType
   * trackType, AudioTrackConfig config) instead.
   *
   * @details
   * Call this method before calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` and `startPreview()`.
   *
   * @param enabled - `true`: Enable the external audio source.
   * - `false`: (Default) Disable the external audio source.
   * @param sampleRate The sample rate (Hz) of the external audio source, which can be set as 8000,
   * 16000, 32000, 44100, or 48000.
   * @param channels The number of audio channels of the external audio source:
   * - 1: Mono.
   * - 2: Stereo.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int setExternalAudioSource(boolean enabled, int sampleRate, int channels);

  /**
   * @brief Sets the external audio sink.
   *
   * @details
   * After enabling the external audio sink, you can call `pullPlaybackAudioFrame(byte[] data, int lengthInByte)` to pull
   * remote audio frames. The app can process the remote audio and play it with the audio effects that
   * you want.
   * Applicable scenarios: This method applies to scenarios where you want to use external audio data
   * for playback.
   * Call timing: Call this method before joining a channel.
   *
   * @note Once you enable the external audio sink, the app will not retrieve any audio data from the
   * `onPlaybackAudioFrame` callback.
   *
   * @param enabled Whether to enable or disable the external audio sink:
   * - `true`: Enables the external audio sink.
   * - `false`: (Default) Disables the external audio sink.
   * @param sampleRate The sample rate (Hz) of the external audio sink, which can be set as 16000,
   * 32000, 44100, or 48000.
   * @param channels The number of audio channels of the external audio sink:
   * - 1: Mono.
   * - 2: Stereo.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExternalAudioSink(boolean enabled, int sampleRate, int channels);

  /**
   * @brief Sets the EGL context for rendering remote video streams.
   *
   * @details
   * This method can replace the default remote EGL context within the SDK, making it easier to manage
   * the EGL context.
   * When the engine is destroyed, the SDK will automatically release the EGL context.
   * Applicable scenarios: This method is suitable for using a custom video rendering method instead
   * of the default SDK rendering method to render remote video frames in Texture format.
   * Call timing: Call this method before joining a channel.
   *
   * @param eglContext The EGL context for rendering remote video streams.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExternalRemoteEglContext(Object eglContext);

  /**
   * @brief Pulls the remote audio data.
   *
   * @details
   * After a successful call of this method, the app pulls the decoded and mixed audio data for
   * playback.
   * Call timing: Call this method after joining a channel.
   * Before calling this method, call `setExternalAudioSink` `(enabled: true)` to notify the app to
   * enable and set the external audio rendering.
   *
   * @note
   * Both this method and the `onPlaybackAudioFrame` callback can be used to get audio data after
   * remote mixing. After calling `setExternalAudioSink` to enable external audio rendering, the app
   * will no longer be able to obtain data from the `onPlaybackAudioFrame` callback. Therefore, you
   * should choose between this method and the `onPlaybackAudioFrame` callback based on your actual
   * business requirements. The specific distinctions between them are as follows:
   * - After calling this method, the app automatically pulls the audio data from the SDK. By setting
   * the audio data parameters, the SDK adjusts the frame buffer to help the app handle latency,
   * effectively avoiding audio playback jitter.
   * - After registering the `onPlaybackAudioFrame` callback, the SDK sends the audio data to the app
   * through the callback. Any delay in processing the audio frames may result in audio jitter.
   * This method is only used for retrieving audio data after remote mixing. If you need to get audio
   * data from different audio processing stages such as capture and playback, you can register the
   * corresponding callbacks by calling `registerAudioFrameObserver`.
   *
   * @param data The remote audio data to be pulled. The data type is `byte[]`.
   * @param lengthInByte The data length (byte). The value of this parameter is related to the audio
   * duration, and the values of the `sampleRate` and `channels` parameters that you set in
   * `setExternalAudioSink`. `lengthInByte` = `sampleRate`/1000 × 2 × `channels` × audio duration
   * (ms).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pullPlaybackAudioFrame(byte[] data, int lengthInByte);

  /**
   * @brief Pulls the remote audio data.
   *
   * @details
   * Before calling this method, call the `setExternalAudioSink(enabled: true)` method to notify the
   * app to enable and set the external audio sink.
   * After a successful method call, the app pulls the decoded and mixed audio data for playback.
   *
   * @note
   * - Call this method after joining a channel.
   * - The difference between this method and the `onPlaybackAudioFrame` callback is as follows:
   *   - `onPlaybackAudioFrame`: The SDK sends the audio data to the app through this callback. Any
   * delay in processing the audio frames may result in audio jitter.
   *   - `pullPlaybackAudioFrame(byte[] data, int lengthInByte)`: The app pulls the remote audio data. After setting the audio
   * data parameters, the SDK adjusts the frame buffer and avoids problems caused by jitter in the
   * external audio playback.
   *
   * @param data The remote audio data to be pulled. The data type is `ByteBuffer`.
   * @param lengthInByte The length (in bytes) of the remote audio data. The value of this parameter
   * is related to the audio duration,and the values of the `sampleRate` and `channels` parameters
   * that you set in `setExternalAudioSink`. `lengthInByte` = `sampleRate` /1000 × 2 × `channels` ×
   * audio duration (ms).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pullPlaybackAudioFrame(ByteBuffer data, int lengthInByte);

  /**
   * @brief Starts the audio capturing device test.
   *
   * @details
   * This method tests whether the audio capturing device works properly. After calling this method,
   * the SDK triggers the `onAudioVolumeIndication` callback at the time interval set in this method,
   * which reports `uid` = 0 and the volume information of the capturing device.
   * The difference between this method and the `startEchoTest` method is that the former checks if
   * the local audio capturing device is working properly, while the latter can check the audio and
   * video devices and network conditions.
   *
   * @note Call this method before joining a channel. After the test is completed, call
   * `stopRecordingDeviceTest` to stop the test before joining a channel.
   *
   * @param indicationInterval The interval (ms) for triggering the `onAudioVolumeIndication`
   * callback. This value should be set to greater than 10, otherwise, you will not receive the
   * `onAudioVolumeIndication` callback and the SDK returns the error code `-2`. Agora recommends that
   * you set this value to 100.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: Invalid parameters. Check your parameter settings.
   */
  public abstract int startRecordingDeviceTest(int indicationInterval);

  /**
   * @brief Stops the audio capturing device test.
   *
   * @details
   * This method stops the audio capturing device test. You must call this method to stop the test
   * after calling the `startRecordingDeviceTest` method.
   *
   * @note Call this method before joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopRecordingDeviceTest();

  /**
   * @brief Starts the audio playback device test.
   *
   * @details
   * This method tests whether the audio device for local playback works properly. Once a user starts
   * the test, the SDK plays an audio file specified by the user. If the user can hear the audio, the
   * playback device works properly.
   * After calling this method, the SDK triggers the `onAudioVolumeIndication` callback every 100 ms,
   * reporting `uid` = 1 and the volume information of the playback device.
   * The difference between this method and the `startEchoTest` method is that the former checks if
   * the local audio playback device is working properly, while the latter can check the audio and
   * video devices and network conditions.
   *
   * @note Call this method before joining a channel. After the test is completed, call
   * `stopPlaybackDeviceTest` to stop the test before joining a channel.
   *
   * @param audioFileName The path of the audio file. The data format is string in UTF-8.
   * - Supported file formats: wav, mp3, m4a, and aac.
   * - Supported file sample rates: 8000, 16000, 32000, 44100, and 48000 Hz.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int startPlaybackDeviceTest(String audioFileName);

  /**
   * @brief Stops the audio playback device test.
   *
   * @details
   * This method stops the audio playback device test. You must call this method to stop the test
   * after calling the `startPlaybackDeviceTest` method.
   *
   * @note Call this method before joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopPlaybackDeviceTest();

  /**
   * @brief Creates a custom audio track.
   *
   * @details
   * To publish a custom audio source, see the following steps:1. Call this method to create a custom
   * audio track and get the audio track ID.
   * 2. Call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. In `ChannelMediaOptions`, set
   * `publishCustomAudioTrackId` to the audio track ID that you want to publish, and set
   * `publishCustomAudioTrack` to `true`.
   * 3. Call `pushExternalAudioFrame` and specify `trackId` as the audio track ID set in step 2. You
   * can then publish the corresponding custom audio source in the channel.
   *
   * @note Call this method before joining a channel.
   *
   * @param trackType The type of the custom audio track. See `AudioTrackType`.Attention: If
   * `AUDIO_TRACK_DIRECT` is specified for this parameter, you must set `publishMicrophoneTrack` to
   * `false` in `ChannelMediaOptions` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel; otherwise,
   * joining the channel fails and returns the error code -2.
   * @param config The configuration of the custom audio track. See `AudioTrackConfig`.
   *
   * @return
   * - If the method call is successful, the audio track ID is returned as the unique identifier of
   * the audio track.
   * - If the method call fails, 0xffffffff is returned.
   */
  public abstract int createCustomAudioTrack(
      Constants.AudioTrackType trackType, AudioTrackConfig config);

  /**
   * @brief Destroys the specified audio track.
   *
   * @param trackId The custom audio track ID returned in `createCustomAudioTrack`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int destroyCustomAudioTrack(int trackId);

  /**
   * @brief Sets the external audio source parameters.
   *
   * @deprecated This method is deprecated. Use createCustomAudioTrack(Constants.AudioTrackType
   * trackType, AudioTrackConfig config) instead.
   *
   * @details
   * Call timing: Call this method before joining a channel.
   *
   * @param enabled Whether to enable the external audio source:
   * - `true`: Enable the external audio source.
   * - `false`: (Default) Disable the external audio source.
   * @param sampleRate The sample rate (Hz) of the external audio source which can be set as `8000`,
   * `16000`, `32000`, `44100`, or `48000`.
   * @param channels The number of channels of the external audio source, which can be set as `1`
   * (Mono) or `2` (Stereo).
   * @param localPlayback Whether to play the external audio source:
   * - `true`: Play the external audio source.
   * - `false`: (Default) Do not play the external source.
   * @param publish Whether to publish audio to the remote users:
   * - `true`: (Default) Publish audio to the remote users.
   * - `false`: Do not publish audio to the remote users.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int setExternalAudioSource(
      boolean enabled, int sampleRate, int channels, boolean localPlayback, boolean publish);

  /**
   * Pushes the external audio data to the app.
   * @deprecated This method is deprecated. Use pushExternalAudioFrame(byte[] data, long timestamp,
   *     int sampleRate, int channels, Constants.BytesPerSample bytesPerSample, int trackId)
   *     instead.
   *
   * @param data The audio buffer data.
   * @param timestamp The timestamp of the audio data.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int pushExternalAudioFrame(byte[] data, long timestamp);

  /**
   * Pushes the external audio data to the app.
   * @deprecated This method is deprecated. Use pushExternalAudioFrame(ByteBuffer[] data, long
   *     timestamp, int sampleRate, int channels, Constants.BytesPerSample bytesPerSample, int
   *     trackId) instead.
   *
   * @param data The audio buffer data.
   * @param timestamp The timestamp of the audio data.
   * @param trackId The audio track ID.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int pushExternalAudioFrame(ByteBuffer data, long timestamp, int trackId);

  /**
   * @brief Pushes the external audio frame to the SDK.
   *
   * @details
   * Call this method to push external audio frames through the audio track.
   * Call timing: Before calling this method to push external audio data, perform the following
   * steps:1. Call `createCustomAudioTrack` to create a custom audio track and get the audio track ID.
   * 2. Call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. In `ChannelMediaOptions`, set
   * `publishCustomAudioTrackId` to the audio track ID that you want to publish, and set
   * `publishCustomAudioTrack` to `true`.
   *
   * @param data The external audio data.
   * @param timestamp The timestamp (ms) of the external audio frame. This parameter is required. You
   * can use it to restore the order of the captured audio frames, or synchronize audio and video
   * frames in video-related scenarios (including scenarios where external video sources are used).
   * @param sampleRate The sample rate (Hz) of the external audio source which can be set as `8000`,
   * `16000`, `32000`, `44100`, or `48000`.
   * @param channels The number of channels of the external audio source, which can be set as `1`
   * (Mono) or `2` (Stereo).
   * @param bytesPerSample The number of bytes per sample. For PCM, this parameter is generally set to
   * 16 bits (2 bytes).
   * @param trackId The audio track ID. Set this parameter to the custom audio track ID returned in
   * `createCustomAudioTrack`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pushExternalAudioFrame(byte[] data, long timestamp, int sampleRate,
      int channels, Constants.BytesPerSample bytesPerSample, int trackId);

  /**
   * Pushes the external audio data to the app.
   *
   * @param data The audio buffer data.
   * @param timestamp The timestamp of the audio data.
   * @param sampleRate Sets the sample rate, which can be set as 8000, 16000, 32000, 44100, or 48000
   *     Hz.
   * @param channel Sets the number of audio channels
   * - 1: Mono.
   * - 2: Stereo.
   * @param bytesPerSample bytes per sample
   * @param trackId The audio track ID.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pushExternalAudioFrame(ByteBuffer data, long timestamp, int sampleRate,
      int channels, Constants.BytesPerSample bytesPerSample, int trackId);

  /**
   * @brief Configures the external video source.
   *
   * @details
   * After calling this method to enable an external video source, you can call
   * `pushExternalVideoFrameById(AgoraVideoFrame frame, int videoTrackId)` to push external video data to the SDK.
   * Call timing: Call this method before joining a channel.
   *
   * @note Dynamic switching of video sources is not supported within the channel. To switch from an
   * external video source to an internal video source, you must first leave the channel, call this
   * method to disable the external video source, and then rejoin the channel.
   *
   * @param enable Whether to use the external video source:
   * - `true`: Use the external video source. The SDK prepares to accept the external video frame.
   * - `false`: (Default) Do not use the external video source.
   * @param useTexture Whether to use the external video frame in the Texture format.
   * - `true`: Use the external video frame in the Texture format.
   * - `false`: (Default) Do not use the external video frame in the Texture format.
   * @param sourceType Whether the external video frame is encoded. See `ExternalVideoSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExternalVideoSource(
      boolean enable, boolean useTexture, Constants.ExternalVideoSourceType sourceType);

  /**
   * Sets the external video source.
   *
   * Once the external video source is enabled, the SDK prepares to accept the external video frame.
   *
   * @param enable Determines whether to enable the external video source.
   * - true: Enable the external video source. Once set, the SDK creates the external source and
   * prepares video data from `pushExternalVideoFrame` or `pushExternalEncodedVideoFrame`.
   * - false: Disable the external video source.
   * @param useTexture Determines whether to use textured video data.
   * - true: Use texture, which is not supported now.
   * - False: Do not use texture.
   * @param sourceType Determines whether the external video source is encoded.
   * - {@link Constants#ExternalVideoSourceType VIDEO_FRAME(0)}: The external video source is not
   * encoded.
   * - {@link Constants#ExternalVideoSourceType ENCODED_VIDEO_FRAME(1)}: The external video source
   * is encoded.
   * @param encodedOpt Determine encoded video track options including codec type, cc mode and
   * target bitrate.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExternalVideoSource(boolean enable, boolean useTexture,
      Constants.ExternalVideoSourceType sourceType, EncodedVideoTrackOptions encodedOpt);

  /**
   * @brief Pushes the external raw video frame to the SDK.
   *
   * @deprecated This method is deprecated.
   *
   * @details
   * After calling the `setExternalVideoSource` method and setting the `enabled` parameter to `true`,
   * and the `encodedFrame` parameter to `false`, you can use this method to push the raw external
   * video frame to the SDK.
   * You can push the video frame either by calling this method or by calling `pushExternalVideoFrame(AgoraVideoFrame frame)`
   * . The difference is that this method supports video data in the texture format.
   *
   * @param frame Video frame to be pushed. See `VideoFrame`.
   *
   * @return
   * - `true`: Success.
   * - `false`: Failure.
   */
  @Deprecated public abstract boolean pushExternalVideoFrame(VideoFrame frame);

  /**
   * @brief Pushes the external raw video frame to the SDK through video tracks.
   *
   * @details
   * To publish a custom video source, see the following steps:1. Call `createCustomVideoTrack` to
   * create a video track and get the video track ID.
   * 2. Call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. In `ChannelMediaOptions`, set
   * `customVideoTrackId` to the video track ID that you want to publish, and set
   * `publishCustomVideoTrack` to `true`.
   * 3. Call this method and specify `videoTrackId` as the video track ID set in step 2. You can then
   * publish the corresponding custom video source in the channel.
   * You can push the video frame either by calling this method or by calling
   * `pushExternalVideoFrameById(AgoraVideoFrame frame, int videoTrackId)`. The difference is that this method supports video data in the
   * texture format.
   *
   * @note
   * If you only need to push one custom video source to the channel, you can directly call the
   * `setExternalVideoSource` method and the SDK will automatically create a video track with the
   * `videoTrackId` set to 0.
   * DANGER: After calling this method, even if you stop pushing external video frames to the SDK, the
   * custom video stream will still be counted as the video duration usage and incur charges. Agora
   * recommends that you take appropriate measures based on the actual situation to avoid such video
   * billing.
   * - If you no longer need to capture external video data, you can call `destroyCustomVideoTrack` to
   * destroy the custom video track.
   * - If you only want to use the external video data for local preview and not publish it in the
   * channel, you can call `muteLocalVideoStream` to cancel sending video stream or call
   * `updateChannelMediaOptions` to set `publishCustomVideoTrack` to `false`.
   *
   * @param frame Video frame to be pushed. See `VideoFrame`.
   * @param videoTrackId The video track ID returned by calling the `createCustomVideoTrack`
   * method.Note: If you only need to push one custom video source, set `videoTrackId` to 0.
   *
   * @return
   * - 0: Pushes the external encoded video frame to the SDK successfully.
   * - < 0: Fails to push external encoded video frames to the SDK.
   */
  public abstract int pushExternalVideoFrameById(VideoFrame frame, int videoTrackId);

  /**
   * @brief Pushes the external raw video frame to the SDK through video tracks.
   *
   * @details
   * To publish a custom video source, see the following steps:1. Call `createCustomVideoTrack` to
   * create a video track and get the video track ID.
   * 2. Call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. In `ChannelMediaOptions`, set
   * `customVideoTrackId` to the video track ID that you want to publish, and set
   * `publishCustomVideoTrack` to `true`.
   * 3. Call this method and specify `videoTrackId` as the video track ID set in step 2. You can then
   * publish the corresponding custom video source in the channel.
   * You can push the video frame either by calling this method or by calling
   * `pushExternalVideoFrameById(VideoFrame frame, int videoTrackId)`. The difference is that this method does not support video
   * data in Texture format.
   *
   * @note
   * If you only need to push one custom video source to the channel, you can directly call the
   * `setExternalVideoSource` method and the SDK will automatically create a video track with the
   * `videoTrackId` set to 0.
   * DANGER: After calling this method, even if you stop pushing external video frames to the SDK, the
   * custom video stream will still be counted as the video duration usage and incur charges. Agora
   * recommends that you take appropriate measures based on the actual situation to avoid such video
   * billing.
   * - If you no longer need to capture external video data, you can call `destroyCustomVideoTrack` to
   * destroy the custom video track.
   * - If you only want to use the external video data for local preview and not publish it in the
   * channel, you can call `muteLocalVideoStream` to cancel sending video stream or call
   * `updateChannelMediaOptions` to set `publishCustomVideoTrack` to `false`.
   *
   * @param frame The external raw video frame to be pushed. See `AgoraVideoFrame`.
   * @param videoTrackId The video track ID returned by calling the `createCustomVideoTrack`
   * method.Note: If you only need to push one custom video source, set `videoTrackId` to 0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pushExternalVideoFrameById(AgoraVideoFrame frame, int videoTrackId);

  /**
   * Pushes the encoded external video frame to Agora SDK.
   * @deprecated This method is deprecated.
   *
   * @note
   * Ensure that you call {@link setExternalVideoSource setExternalVideoSource} before
   * calling this method.
   *
   * @param data The encoded external video data, which must be the direct buffer.
   * @param frameInfo The encoded external video frame info: {@link
   *     io.agora.rtc2.video.EncodedVideoFrameInfo
   * EncodedVideoFrameInfo}.
   * @return
   * - 0: Success, which means that the encoded external video frame is pushed successfully.
   * - < 0: Failure, which means that the encoded external video frame fails to be pushed.
   * @technical preview
   */
  @Deprecated
  public abstract int pushExternalEncodedVideoFrame(
      ByteBuffer data, EncodedVideoFrameInfo frameInfo);

  /**
   * Pushes the encoded external video frame to the app with specified connection.
   *
   * @note
   * Ensure that you call {@link setExternalVideoSource setExternalVideoSource} before
   * calling this method.
   *
   * @param data The encoded external video data, which must be the direct buffer.
   * @param frameInfo The encoded external video frame info: {@link
   *     io.agora.rtc2.video.EncodedVideoFrameInfo EncodedVideoFrameInfo}.
   * @param videoTrackId  The id of the video track.
   * @return
   * - 0: Success, which means that the encoded external video frame is pushed successfully.
   * - < 0: Failure, which means that the encoded external video frame fails to be pushed.
   * @technical preview
   */
  public abstract int pushExternalEncodedVideoFrameById(
      ByteBuffer data, EncodedVideoFrameInfo frameInfo, int videoTrackId);

  /**
   * @brief Pushes the external raw video frame to the SDK.
   *
   * @details
   * After calling the `setExternalVideoSource` method and setting the `enabled` parameter to `true`,
   * and the `encodedFrame` parameter to `false`, you can use this method to push the raw external
   * video frame to the SDK.
   * You can push the video frame either by calling this method or by calling `pushExternalVideoFrame(VideoFrame frame)`
   * . The difference is that this method does not support video data in Texture format.
   *
   * @param frame The external raw video frame to be pushed. See `AgoraVideoFrame`.
   *
   * @return
   * - `true`: Success.
   * - `false`: Failure.
   */
  @Deprecated public abstract boolean pushExternalVideoFrame(AgoraVideoFrame frame);

  /**
   * @brief Check whether the video supports the Texture encoding.
   *
   * @return
   * - `true`: Supports the Texture encoding.
   * - `false`: Does not support the Texture encoding.
   */
  public abstract boolean isTextureEncodeSupported();

  /**
   * @brief Registers an audio frame observer object.
   *
   * @details
   * Call this method to register an audio frame observer object (register a callback). When you need
   * the SDK to trigger the `onMixedAudioFrame`, `onRecordAudioFrame`, `onPlaybackAudioFrame`,
   * `onPlaybackAudioFrameBeforeMixing` or `onEarMonitoringAudioFrame` callback, you need to use this
   * method to register the callbacks.
   * Call timing: Call this method before joining a channel.
   *
   * @param observer The observer instance. See `IAudioFrameObserver`. Set the value as NULL to
   * release the instance. Agora recommends calling this method after receiving `onLeaveChannel` to
   * release the audio observer object.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerAudioFrameObserver(IAudioFrameObserver observer);

  /**
   * @brief Registers an encoded audio observer.
   *
   * @note
   * - Call this method after joining a channel.
   * - You can call this method or `startAudioRecording [2/2]` to set the recording type and quality
   * of audio files, but Agora does not recommend using this method and `startAudioRecording [2/2]` at
   * the same time. Only the method called later will take effect.
   *
   * @param config Observer settings for the encoded audio. See `AudioEncodedFrameObserverConfig`.
   * @param observer The encoded audio observer. See `IAudioEncodedFrameObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerAudioEncodedFrameObserver(
      AudioEncodedFrameObserverConfig config, IAudioEncodedFrameObserver observer);

  /**
   * @brief Sets the format of the captured raw audio data.
   *
   * @details
   * The SDK calculates the sampling interval based on the `samplesPerCall`, `sampleRate` and
   * `channel` parameters set in this method.Sample interval (sec) = `samplePerCall` /( `sampleRate` ×
   * `channel` ). Ensure that the sample interval ≥ 0.01 (s). The SDK triggers the
   * `onRecordAudioFrame` callback according to the sampling interval.
   * Call timing: Call this method before joining a channel.
   *
   * @param sampleRate The sample rate returned in the callback, which can be set as 8000, 16000,
   * 32000, 44100, or 48000 Hz.
   * @param channel The number of audio channels. You can set the value as 1 or 2.
   * - 1: Mono.
   * - 2: Stereo.
   * @param mode The use mode of the audio frame:
   * - RAW_AUDIO_FRAME_OP_MODE_READ_ONLY (0): (Default) Read only mode. For example, when users
   * acquire the data with the Agora SDK, then push the RTMP or RTMPS streams.
   * - RAW_AUDIO_FRAME_OP_MODE_READ_WRITE (2): Read and write mode: Users read the data from
   * AudioFrame, modify it, and then play it. For example, when users have their own audio-effect
   * processing module and perform some voice pre-processing, such as a voice change.
   * @param samplesPerCall The number of data samples, such as 1024 for the Media Push.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRecordingAudioFrameParameters(
      int sampleRate, int channel, int mode, int samplesPerCall);

  /**
   * @brief Sets the format of the raw audio playback data.
   *
   * @details
   * The SDK calculates the sampling interval based on the `samplesPerCall`, `sampleRate` and
   * `channel` parameters set in this method.Sample interval (sec) = `samplePerCall` /( `sampleRate` ×
   * `channel` ). Ensure that the sample interval ≥ 0.01 (s). The SDK triggers the
   * `onPlaybackAudioFrame` callback according to the sampling interval.
   * Call timing: Call this method before joining a channel.
   *
   * @param sampleRate The sample rate returned in the callback, which can be set as 8000, 16000,
   * 32000, 44100, or 48000 Hz.
   * @param channel The number of audio channels. You can set the value as 1 or 2.
   * - 1: Mono.
   * - 2: Stereo.
   * @param mode The use mode of the audio frame:
   * - RAW_AUDIO_FRAME_OP_MODE_READ_ONLY (0): (Default) Read only mode. For example, when users
   * acquire the data with the Agora SDK, then push the RTMP or RTMPS streams.
   * - RAW_AUDIO_FRAME_OP_MODE_READ_WRITE (2): Read and write mode: Users read the data from
   * AudioFrame, modify it, and then play it. For example, when users have their own audio-effect
   * processing module and perform some voice pre-processing, such as a voice change.
   * @param samplesPerCall The number of data samples, such as 1024 for the Media Push.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setPlaybackAudioFrameParameters(
      int sampleRate, int channel, int mode, int samplesPerCall);

  /**
   * @brief Sets the format of the raw audio data after mixing for audio capture and playback.
   *
   * @details
   * The SDK calculates the sampling interval based on the `samplesPerCall`, `sampleRate` and
   * `channel` parameters set in this method.Sample interval (sec) = `samplePerCall` /( `sampleRate` ×
   * `channel` ). Ensure that the sample interval ≥ 0.01 (s). The SDK triggers the `onMixedAudioFrame`
   * callback according to the sampling interval.
   * Call timing: Call this method before joining a channel.
   *
   * @param sampleRate The sample rate returned in the callback, which can be set as 8000, 16000,
   * 32000, 44100, or 48000 Hz.
   * @param channel The number of audio channels. You can set the value as 1 or 2.
   * - 1: Mono.
   * - 2: Stereo.
   * @param samplesPerCall The number of data samples, such as 1024 for the Media Push.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setMixedAudioFrameParameters(int sampleRate, int channel, int samplesPerCall);

  /**
   * @brief Sets the format of the in-ear monitoring raw audio data.
   *
   * @details
   * This method is used to set the in-ear monitoring audio data format reported by the
   * `onEarMonitoringAudioFrame` callback.
   *
   * @note
   * - Before calling this method, you need to call `enableInEarMonitoring(boolean enabled, int includeAudioFilters)`, and set
   * `includeAudioFilters` to `EAR_MONITORING_FILTER_BUILT_IN_AUDIO_FILTERS` or
   * `EAR_MONITORING_FILTER_NOISE_SUPPRESSION`.
   * - The SDK calculates the sampling interval based on the `samplesPerCall`, `sampleRate` and
   * `channel` parameters set in this method.Sample interval (sec) = `samplePerCall` /( `sampleRate` ×
   * `channel` ). Ensure that the sample interval ≥ 0.01 (s). The SDK triggers the
   * `onEarMonitoringAudioFrame` callback according to the sampling interval.
   *
   * @param sampleRate The sample rate of the audio data reported in the `onEarMonitoringAudioFrame`
   * callback, which can be set as 8,000, 16,000, 32,000, 44,100, or 48,000 Hz.
   * @param channel The number of audio channels reported in the `onEarMonitoringAudioFrame` callback.
   * - 1: Mono.
   * - 2: Stereo.
   * @param mode The use mode of the audio frame:
   * - RAW_AUDIO_FRAME_OP_MODE_READ_ONLY (0): (Default) Read only mode. For example, when users
   * acquire the data with the Agora SDK, then push the RTMP or RTMPS streams.
   * - RAW_AUDIO_FRAME_OP_MODE_READ_WRITE (2): Read and write mode: Users read the data from
   * AudioFrame, modify it, and then play it. For example, when users have their own audio-effect
   * processing module and perform some voice pre-processing, such as a voice change.
   * @param samplesPerCall The number of data samples reported in the `onEarMonitoringAudioFrame`
   * callback, such as 1,024 for the Media Push.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setEarMonitoringAudioFrameParameters(
      int sampleRate, int channel, int mode, int samplesPerCall);

  /**
   * @brief Adds a watermark image to the local video.
   *
   * @deprecated From v2.9.1. We recommend using the {@link RtcEngine#addVideoWatermark(String,
   * WatermarkOptions) addVideoWatermark}2 method instead.
   *
   * @details
   * This method adds a PNG watermark image to the local video stream in a live streaming session.
   * Once the watermark image is added, all the users in the channel (CDN audience included) and the
   * video capturing device can see and capture it. If you only want to add a watermark to the CDN
   * live streaming, see `startRtmpStreamWithTranscoding`.
   *
   * @note
   * - The URL descriptions are different for the local video and CDN live streaming: In a local video
   * stream, URL refers to the absolute path of the added watermark image file in the local video
   * stream. In a CDN live stream, URL refers to the URL address of the added watermark image in the
   * CDN live streaming.
   * - The source file of the watermark image must be in the PNG file format. If the width and height
   * of the PNG file differ from your settings in this method, the PNG file will be cropped to conform
   * to your settings.
   * - The Agora SDK supports adding only one watermark image onto a local video or CDN live stream.
   * The newly added watermark image replaces the previous one.
   *
   * @param watermark The watermark image to be added to the local live streaming: `AgoraImage`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int addVideoWatermark(AgoraImage watermark);

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
   * The watermark coordinates are dependent on the settings in the `setVideoEncoderConfiguration`
   * method:
   * - If the orientation mode of the encoding video ( `ORIENTATION_MODE` ) is fixed landscape mode or
   * the adaptive landscape mode, the watermark uses the landscape orientation.
   * - If the orientation mode of the encoding video ( `ORIENTATION_MODE` ) is fixed portrait mode or
   * the adaptive portrait mode, the watermark uses the portrait orientation.
   * - When setting the watermark position, the region must be less than the dimensions set in the
   * `setVideoEncoderConfiguration` method; otherwise, the watermark image will be cropped.
   * You can control the visibility of the watermark during preview by setting the `visibleInPreview`
   * parameter when calling this method. However, whether it ultimately takes effect also depends on
   * the position parameter you set when calling `setupLocalVideo` (the ` position` of the video frame
   * in the video link). Refer to the table below for details.
   * | Observation position                            | visibleInPreview value | Watermark visibility |
   * | ----------------------------------------------- | ---------------------- | -------------------- |
   * | (Default) `VIDEO_MODULE_POSITION_POST_CAPTURER` | `true`                 | Yes                  |
   * |                                                 | `false`                | No                   |
   * | `VIDEO_MODULE_POSITION_PRE_ENCODER`             | `true`                 | Yes                  |
   * |                                                 | `false`                | Yes                  |
   *
   * @note
   * - Ensure that calling this method after `enableVideo`.
   * - If you only want to add a watermark to the media push, you can call this method or the
   * `startRtmpStreamWithTranscoding` method.
   * - This method supports adding a watermark image in the PNG file format only. Supported pixel
   * formats of the PNG image are RGBA, RGB, Palette, Gray, and Alpha_gray.
   * - If the dimensions of the PNG image differ from your settings in this method, the image will be
   * cropped or zoomed to conform to your settings.
   * - If you have enabled the mirror mode for the local video, the watermark on the local video is
   * also mirrored. To avoid mirroring the watermark, Agora recommends that you do not use the mirror
   * and watermark functions for the local video at the same time. You can implement the watermark
   * function in your application layer.
   *
   * @param watermarkUrl The local file path of the watermark image to be added. This method supports
   * adding a watermark image from the local absolute or relative file path.
   * @param options The options of the watermark image to be added. See `WatermarkOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int addVideoWatermark(String watermarkUrl, WatermarkOptions options);

  /**
   * @brief Adds a watermark image to the local video.
   *
   * @since v4.6.0
   *
   * @details
   * You can use this method to overlay a watermark image on the local video stream, and configure the
   * watermark's position, size, and visibility in the preview using `WatermarkConfig`.
   *
   * @param config Watermark configuration. See `WatermarkConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int addVideoWatermark(WatermarkConfig config);

  /**
   * @brief Removes the watermark image from the local video.
   *
   * @since v4.6.0
   *
   * @details
   * This method removes a previously added watermark image from the local video stream using the
   * specified unique ID.
   *
   * @param id The ID of the watermark image to be removed.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int removeVideoWatermark(String id);

  /**
   * @brief Removes the watermark image from the video stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int clearVideoWatermarks();

  /**
   * Sets the priority of a remote user's media stream.
   *
   * @since v2.4.0.
   *        <p>
   *        Use this method with the
   *        {@link RtcEngine#setRemoteSubscribeFallbackOption
   *        setRemoteSubscribeFallbackOption} method. If the fallback function is
   *        enabled for a subscribed stream, the SDK ensures the high-priority
   *        user gets the best possible stream quality.
   *
   * @note The SDK supports setting userPriority as high for one user only.
   *
   * @param uid          The ID of the remote user.
   * @param userPriority The priority of the remote user:
   *                     <ul>
   *                     <li>{@link Constants#USER_PRIORITY_HIGH
   *                     USER_PRIORITY_HIGH(50)}: the user's priority is high.
   *                     <li>{@link Constants#USER_PRIORITY_NORANL
   *                     USER_PRIORITY_NORANL(100)}: (default) the user's priority
   *                     is normal.
   *                     </ul>
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  public abstract int setRemoteUserPriority(int uid, int userPriority);

/**
 * @brief Sets the fallback option for the subscribed video stream based on the network conditions.
 *
 * @details
 * An unstable network affects the audio and video quality in a video call or interactive live video
 * streaming. If `option` is set as `STREAM_FALLBACK_OPTION_VIDEO_STREAM_LOW` or
 * `STREAM_FALLBACK_OPTION_AUDIO_ONLY`, the SDK automatically switches the video from a high-quality
 * stream to a low-quality stream or disables the video when the downlink network conditions cannot
 * support both audio and video to guarantee the quality of the audio. Meanwhile, the SDK
 * continuously monitors network quality and resumes subscribing to audio and video streams when the
 * network quality improves.
 * When the subscribed video stream falls back to an audio-only stream, or recovers from an
 * audio-only stream to an audio-video stream, the SDK triggers the
 * `onRemoteSubscribeFallbackToAudioOnly` callback.
 *
 * @param option Fallback options for the subscribed stream. See `StreamFallbackOptions`.
 *
 * @return
 * - 0: Success.
 * - < 0: Failure.
 */

  public abstract int setRemoteSubscribeFallbackOption(Constants.StreamFallbackOptions option);
  /**
   * @brief Sets the fallback option for the subscribed video stream based on the network conditions.
   *
   * @details
   * An unstable network affects the audio and video quality in a video call or interactive live video
   * streaming. If `option` is set as `STREAM_FALLBACK_OPTION_VIDEO_STREAM_LOW` or
   * `STREAM_FALLBACK_OPTION_AUDIO_ONLY`, the SDK automatically switches the video from a high-quality
   * stream to a low-quality stream or disables the video when the downlink network conditions cannot
   * support both audio and video to guarantee the quality of the audio. Meanwhile, the SDK
   * continuously monitors network quality and resumes subscribing to audio and video streams when the
   * network quality improves.
   * When the subscribed video stream falls back to an audio-only stream, or recovers from an
   * audio-only stream to an audio-video stream, the SDK triggers the
   * `onRemoteSubscribeFallbackToAudioOnly` callback.
   *
   * @param option Fallback options for the subscribed stream.
   * - STREAM_FALLBACK_OPTION_DISABLED (0): No fallback processing is performed on audio and video
   * streams, and the quality of the audio and video streams cannot be guaranteed.
   * - STREAM_FALLBACK_OPTION_VIDEO_STREAM_LOW (1): (Default) Under poor downlink network conditions,
   * the remote video stream, to which you subscribe, falls back to the low-quality (low resolution
   * and low bitrate) video stream.
   * - STREAM_FALLBACK_OPTION_AUDIO_ONLY (2): When the network conditions are poor, try to receive the
   * low-quality video stream first. If the video cannot be displayed due to extremely poor network
   * environment, then fall back to receiving audio-only stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setRemoteSubscribeFallbackOption(int option);

  /**
   * Sets the high priority user list and related fallback option for the remotely subscribed video
   * stream based on the network conditions in NASA2.
   *
   * @param uidList The id list of high priority users.
   * @param option The remote subscribe fallback option of high priority users.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setHighPriorityUserList(int[] uidList, int option);

  /**
   * @brief Enables or disables dual-stream mode on the sender side.
   *
   * @deprecated v4.2.0. This method is deprecated. Use setDualStreamMode instead.
   *
   * @details
   * Dual streams are a pairing of a high-quality video stream and a low-quality video stream:
   * - High-quality video stream: High bitrate, high resolution.
   * - Low-quality video stream: Low bitrate, low resolution.
   * After you enable dual-stream mode, you can call `setRemoteVideoStreamType(int uid, int streamType)` to choose to
   * receive either the high-quality video stream or the low-quality video stream on the subscriber
   * side.
   *
   * @note
   * - This method is applicable to all types of streams from the sender, including but not limited to
   * video streams collected from cameras, screen sharing streams, and custom-collected video streams.
   * - If you need to enable dual video streams in a multi-channel scenario, you can call the
   * `enableDualStreamModeEx` method.
   * - You can call this method either before or after joining a channel.
   *
   * @param enabled Whether to enable dual-stream mode:
   * - `true`: Enable dual-stream mode.
   * - `false`: (Default) Disable dual-stream mode.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int enableDualStreamMode(boolean enabled);

  /**
   * @brief Sets the dual-stream mode on the sender side and the low-quality video stream.
   *
   * @deprecated v4.2.0. This method is deprecated. Use setDualStreamMode instead.
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
   * @note
   * - This method is applicable to all types of streams from the sender, including but not limited to
   * video streams collected from cameras, screen sharing streams, and custom-collected video streams.
   * - If you need to enable dual video streams in a multi-channel scenario, you can call the
   * `enableDualStreamModeEx` method.
   * - You can call this method either before or after joining a channel.
   *
   * @param enabled Whether to enable dual-stream mode:
   * - `true`: Enable dual-stream mode.
   * - `false`: (Default) Disable dual-stream mode.
   * @param streamConfig The configuration of the low-quality video stream. See
   * `SimulcastStreamConfig`.Note: When setting `mode` to `DISABLE_SIMULCAST_STREAM`, setting
   * `streamConfig` will not take effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int enableDualStreamMode(boolean enabled, SimulcastStreamConfig streamConfig);

  /**
   * @brief Sets the dual-stream mode on the sender side.
   *
   * @details
   * The SDK defaults to enabling low-quality video stream adaptive mode ( `AUTO_SIMULCAST_STREAM` )
   * on the sender side, which means the sender does not actively send low-quality video stream. The
   * receiving end with the role of the **host** can initiate a low-quality video stream request by
   * calling `setRemoteVideoStreamType(int uid, int streamType)`, and upon receiving the request, the sending end
   * automatically starts sending low-quality stream.
   * - If you want to modify this behavior, you can call this method and set `mode` to
   * `DISABLE_SIMULCAST_STREAM` (never send low-quality video streams) or `ENABLE_SIMULCAST_STREAM`
   * (always send low-quality video streams).
   * - If you want to restore the default behavior after making changes, you can call this method
   * again with `mode` set to `AUTO_SIMULCAST_STREAM`.
   *
   * @note
   * The difference and connection between this method and `enableDualStreamMode(boolean enabled)` is as follows:
   * - When calling this method and setting `mode` to `DISABLE_SIMULCAST_STREAM`, it has the same
   * effect as `enableDualStreamMode(boolean enabled)` `(false)`.
   * - When calling this method and setting `mode` to `ENABLE_SIMULCAST_STREAM`, it has the same
   * effect as `enableDualStreamMode(boolean enabled)` `(true)`.
   * - Both methods can be called before and after joining a channel. If both methods are used, the
   * settings in the method called later takes precedence.
   *
   * @param mode The mode in which the video stream is sent. See `SimulcastStreamMode`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setDualStreamMode(Constants.SimulcastStreamMode mode);

  /**
   * @brief Sets dual-stream mode configuration on the sender side.
   *
   * @details
   * The SDK defaults to enabling low-quality video stream adaptive mode ( `AUTO_SIMULCAST_STREAM` )
   * on the sender side, which means the sender does not actively send low-quality video stream. The
   * receiving end with the role of the **host** can initiate a low-quality video stream request by
   * calling `setRemoteVideoStreamType(int uid, int streamType)`, and upon receiving the request, the sending end
   * automatically starts sending low-quality stream.
   * - If you want to modify this behavior, you can call this method and set `mode` to
   * `DISABLE_SIMULCAST_STREAM` (never send low-quality video streams) or `ENABLE_SIMULCAST_STREAM`
   * (always send low-quality video streams).
   * - If you want to restore the default behavior after making changes, you can call this method
   * again with `mode` set to `AUTO_SIMULCAST_STREAM`.
   * The difference between this method and `setDualStreamMode(Constants.SimulcastStreamMode mode)` is that this method can also
   * configure the low-quality video stream, and the SDK sends the stream according to the
   * configuration in `streamConfig`.
   *
   * @note
   * The difference and connection between this method and `enableDualStreamMode(boolean enabled, SimulcastStreamConfig streamConfig)` is as follows:
   * - When calling this method and setting `mode` to `DISABLE_SIMULCAST_STREAM`, it has the same
   * effect as calling `enableDualStreamMode(boolean enabled, SimulcastStreamConfig streamConfig)` and setting `enabled` to `false`.
   * - When calling this method and setting `mode` to `ENABLE_SIMULCAST_STREAM`, it has the same
   * effect as calling `enableDualStreamMode(boolean enabled, SimulcastStreamConfig streamConfig)` and setting `enabled` to `true`.
   * - Both methods can be called before and after joining a channel. If both methods are used, the
   * settings in the method called later takes precedence.
   *
   * @param mode The mode in which the video stream is sent. See `SimulcastStreamMode`.
   * @param streamConfig The configuration of the low-quality video stream. See
   * `SimulcastStreamConfig`.Note: When setting `mode` to `DISABLE_SIMULCAST_STREAM`, setting
   * `streamConfig` will not take effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setDualStreamMode(
      Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig);

  /**
   * @brief Sets the simulcast video stream configuration.
   *
   * @technical preview
   *
   * @details
   * You can call this method to set video streams with different resolutions for the same video
   * source. The subscribers can call `setRemoteVideoStreamType(int uid, int streamType)` to select which stream layer to
   * receive. The broadcaster can publish up to four layers of video streams: one main stream (highest
   * resolution) and three additional streams of different quality levels.
   *
   * @param simulcastConfig Configuration for different video steam layers. See `SimulcastConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSimulcastConfig(SimulcastConfig simulcastConfig);

  /**
   * @brief Sets the maximum frame rate for rendering local video.
   *
   * @details
   * Applicable scenarios: In scenarios where the requirements for video rendering frame rate are not
   * high (such as screen sharing or online education), you can call this method to set the maximum
   * frame rate for local video rendering. The SDK will attempt to keep the actual frame rate of local
   * rendering close to this value, which helps to reduce CPU consumption and improving system
   * performance.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   * @param targetFps The capture frame rate (fps) of the local video. Sopported values are: 1, 7, 10,
   * 15, 24, 30, 60.CAUTION: Set this parameter to a value lower than the actual video frame rate;
   * otherwise, the settings do not take effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLocalRenderTargetFps(Constants.VideoSourceType sourceType, int targetFps);
  /**
   * @brief Sets the maximum frame rate for rendering remote video.
   *
   * @details
   * Applicable scenarios: In scenarios where the video rendering frame rate is not critical (e.g.,
   * screen sharing, online education) or when the remote users are using mid-to-low-end devices, you
   * can call this method to set the maximum frame rate for video rendering on the remote client. The
   * SDK will attempt to render the actual frame rate as close as possible to this value, which helps
   * to reduce CPU consumption and improve system performance.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param targetFps The capture frame rate (fps) of the local video. Sopported values are: 1, 7, 10,
   * 15, 24, 30, 60.CAUTION: Set this parameter to a value lower than the actual video frame rate;
   * otherwise, the settings do not take effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteRenderTargetFps(int targetFps);
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
   * @note
   * - You can call this method either before or after joining a channel.
   * - If you call both this method and `setRemoteDefaultVideoStreamType(int streamType)`, the setting of this
   * method takes effect.
   *
   * @param uid The user ID.
   * @param streamType The video stream type, see `VideoStreamType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteVideoStreamType(int uid, Constants.VideoStreamType streamType);

  /**
   * @brief Sets the video stream type to subscribe to.
   *
   * @deprecated This method is deprecated. Use `setRemoteVideoStreamType(int uid, Constants.VideoStreamType streamType)` instead.
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
   * @note
   * - You can call this method either before or after joining a channel.
   * - If you call both this method and `setRemoteDefaultVideoStreamType(int streamType)`, the setting of this
   * method takes effect.
   *
   * @param uid The user ID.
   * @param streamType The video stream type:
   * - 0: High-quality video stream.
   * - 1: Low-quality video stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setRemoteVideoStreamType(int uid, int streamType);

  /**
   * @brief Sets the default video stream type to subscribe to.
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
   * Call timing: Call this method before joining a channel. The SDK does not support changing the
   * default subscribed video stream type after joining a channel.
   *
   * @note If you call both this method and `setRemoteVideoStreamType(int uid, int streamType)`, the setting of
   * `setRemoteVideoStreamType(int uid, int streamType)` takes effect.
   *
   * @param streamType The video stream type, see `VideoStreamType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setRemoteDefaultVideoStreamType(Constants.VideoStreamType streamType);

  /**
   * @brief Sets the default video stream type to subscribe to.
   * @deprecated This method is deprecated. Use `setRemoteDefaultVideoStreamType(Constants.VideoStreamType streamType)` instead.
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
   * Call timing: Call this method before joining a channel. The SDK does not support changing the
   * default subscribed video stream type after joining a channel.
   *
   * @note If you call both this method and `setRemoteVideoStreamType(int uid, int streamType)`, the setting of
   * `setRemoteVideoStreamType(int uid, int streamType)` takes effect.
   *
   * @param streamType The default video-stream type:
   * - VIDEO_STREAM_HIGH (0): High-quality stream, that is, a high-resolution and high-bitrate video
   * stream.
   * - VIDEO_STREAM_LOW (1): Low-quality stream, that is, a low-resolution and low-bitrate video
   * stream.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setRemoteDefaultVideoStreamType(int streamType);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeAudioBlocklist(int[] uidList);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeAudioAllowlist(int[] uidList);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeVideoBlocklist(int[] uidList);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setSubscribeVideoAllowlist(int[] uidList);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: An invalid parameter is used. Set the parameter with a valid value.
   *   - -4: The built-in encryption mode is incorrect or the SDK fails to load the external
   * encryption library. Check the enumeration or reload the external encryption library.
   *   - -7: The SDK is not initialized. Initialize the `RtcEngine` instance before calling this
   * method.
   */
  public abstract int enableEncryption(boolean enabled, EncryptionConfig config);
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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The URL or configuration of transcoding is invalid; check your URL and transcoding
   * configurations.
   *   - -7: The SDK is not initialized before calling this method.
   *   - -19: The Media Push URL is already in use; use another URL instead.
   */
  public abstract int startRtmpStreamWithoutTranscoding(String url);

  /**
   * @brief Starts Media Push and sets the transcoding configuration.
   *
   * @details
   * Agora recommends that you use the server-side Media Push function. For details, see `Use RESTful
   * API`.
   * You can call this method to push a live audio-and-video stream to the specified CDN address and
   * set the transcoding configuration. This method can push media streams to only one CDN address at
   * a time, so if you need to push streams to multiple addresses, call this method multiple times.
   * Under one Agora project, the maximum number of concurrent tasks to push media streams is 200 by
   * default. If you need a higher quota, contact `technical support`.
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
   * @param transcoding The transcoding configuration for Media Push. See `LiveTranscoding`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The URL or configuration of transcoding is invalid; check your URL and transcoding
   * configurations.
   *   - -7: The SDK is not initialized before calling this method.
   *   - -19: The Media Push URL is already in use; use another URL instead.
   */
  public abstract int startRtmpStreamWithTranscoding(String url, LiveTranscoding transcoding);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int updateRtmpTranscoding(LiveTranscoding transcoding);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopRtmpStream(String url);

  /**
   * @brief Creates a data stream.
   *
   * @details
   * You can call this method to create a data stream and improve the reliability and ordering of data
   * transmission.
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: After setting `reliable` to `true`, if the recipient does not receive the data
   * within five seconds, the SDK triggers the `onStreamMessageError` callback and returns an error
   * code.
   *
   * @note
   * Each user can create up to five data streams during the lifecycle of `RtcEngine`. The data stream
   * will be destroyed when leaving the channel, and the data stream needs to be recreated if needed.
   * If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
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
   *
   * @return
   * - ID of the created data stream, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int createDataStream(boolean reliable, boolean ordered);

  /**
   * @brief Creates a data stream.
   *
   * @details
   * Compared to `createDataStream(boolean reliable, boolean ordered)`, this method does not guarantee the reliability of data
   * transmission. If a data packet is not received five seconds after it was sent, the SDK directly
   * discards the data.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note
   * Each user can create up to five data streams during the lifecycle of `RtcEngine`. The data stream
   * will be destroyed when leaving the channel, and the data stream needs to be recreated if needed.
   * If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   *
   * @param config The configurations for the data stream. See `DataStreamConfig`.
   *
   * @return
   * - ID of the created data stream, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int createDataStream(DataStreamConfig config);

  /**
   * @brief Sends data stream messages.
   *
   * @details
   * After calling `createDataStream(DataStreamConfig config)`, you can call this method to send data stream messages to
   * all users in the channel.
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
   * If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   * - This method needs to be called after `createDataStream(DataStreamConfig config)` and joining the channel.
   * - This method applies to broadcasters only.
   *
   * @param streamId The data stream ID. You can get the data stream ID by calling `createDataStream(DataStreamConfig config)`
   * .
   * @param message The message to be sent.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendStreamMessage(int streamId, byte[] message);

  /**
   * @brief Send Reliable message to remote uid in channel.
   *
   * @technical preview
   *
   * @param uid remote user id.
   * @param type Reliable Data Transmission tunnel message type.
   * @param message The sent data.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendRdtMessage(int uid, int type, byte[] message);

  /**
   * @brief Send media control message
   *
   * @technical preview
   *
   * @param uid remote user id. In particular, if the uid is set to 0, it means broadcasting the
   *     message to the entire channel.
   * @param message The sent data, max 1024 Bytes.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int sendMediaControlMessage(int uid, byte[] message);

  /**
   * Sets the video quality preferences.
   *
   * @param preferFrameRateOverImageQuality The video preference to be set:
   *                                        <ul>
   *                                        <li>True: Frame rate over image
   *                                        quality
   *                                        <li>False: Image quality over frame
   *                                        rate (default)
   *                                        </ul>
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  public abstract int setVideoQualityParameters(boolean preferFrameRateOverImageQuality);

  /**
   * @brief Sets the local video mirror mode.
   *
   * @param mode The local video mirror mode:
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. If you use a
   * front camera, the SDK enables the mirror mode by default; if you use a rear camera, the SDK
   * disables the mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enable the mirroring mode of the local view.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disable the mirroring mode of the local view.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setLocalVideoMirrorMode(int mode);

  /**
   * Gets the recommended encoder type.
   * @deprecated This method is deprecated.
   *
   * @return The encoder type:
   * <ul>
   *     <li>{@link Constants#HARDWARE_ENCODER HARDWARE ENCODER(1)}: The hardware encoder.</li>
   *     <li>{@link Constants#SOFTWARE_ENCODER SOFTWARE ENCODER(2)}: The software encoder.</li>
   * </ul>
   */
  @Deprecated
  public static int getRecommendedEncoderType() {
    //    return VideoEncoderUtils.getRecommendedEncoderType();
    return 0;
  }

  /**
   * @brief Switches between front and rear cameras.
   *
   * @details
   * You can call this method to dynamically switch cameras based on the actual camera availability
   * during the app's runtime, without having to restart the video stream or reconfigure the video
   * source.
   * This method and `switchCamera(String cameraId)` are both used to switch cameras. The difference is that
   * `switchCamera(String cameraId)` switches to a specific camera by specifying the camera ID, while this method
   * switches the direction of the camera (front or rear).
   * Call timing: This method must be called after the camera is successfully enabled, that is, after
   * the SDK triggers the `onLocalVideoStateChanged` callback and returns the local video state as
   * `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @note This method only switches the camera for the video stream captured by the first camera,
   * that is, the video source set to `VIDEO_SOURCE_CAMERA_PRIMARY` (0) when calling
   * `startCameraCapture`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int switchCamera();

  /**
   * @brief Switches cameras by camera ID.
   *
   * @details
   * You can call this method to dynamically switch cameras based on the actual camera availability
   * during the app's runtime, without having to restart the video stream or reconfigure the video
   * source.
   * This method and `switchCamera()` both are used to switch cameras. The difference is that
   * `switchCamera()` switches the camera direction (front or rear), while this method switches to
   * a specific camera by specifying the camera ID.
   * Call timing: This method must be called after the camera is successfully enabled, that is, after
   * the SDK triggers the `onLocalVideoStateChanged` callback and returns the local video state as
   * `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @note This method only switches the camera for the video stream captured by the first camera,
   * that is, the video source set to `VIDEO_SOURCE_CAMERA_PRIMARY` (0) when calling
   * `startCameraCapture`.
   *
   * @param cameraId The camera ID. You can get the camera ID through the Android native system API,
   * see `Camera.open()` and `CameraManager.getCameraIdList()` for details.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int switchCamera(String cameraId);

  /**
   * @brief Checks whether the device supports camera zoom.
   *
   * @details
   * Call timing: This method must be called after the SDK triggers the `onLocalVideoStateChanged`
   * callback and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @return
   * - `true`: The device supports camera zoom.
   * - `false`: The device does not support camera zoom.
   */
  public abstract boolean isCameraZoomSupported();

  /**
   * @brief Checks whether the device supports camera flash.
   *
   * @note
   * - This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback and
   * returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   * - The app enables the front camera by default. If your front camera does not support flash, this
   * method returns false. If you want to check whether the rear camera supports the flash function,
   * call `switchCamera()` before this method.
   *
   * @return
   * - `true`: The device supports camera flash.
   * - `false`: The device does not support camera flash.
   */
  public abstract boolean isCameraTorchSupported();

  /**
   * @brief Check whether the device supports the manual focus function.
   *
   * @note This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback
   * and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @return
   * - `true`: The device supports the manual focus function.
   * - `false`: The device does not support the manual focus function.
   */
  public abstract boolean isCameraFocusSupported();

  /**
   * @brief Checks whether the device supports manual exposure.
   *
   * @since v2.3.2.
   *
   * @note This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback
   * and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @return
   * - `true`: The device supports manual exposure.
   * - `false`: The device does not support manual exposure.
   */
  public abstract boolean isCameraExposurePositionSupported();

  /**
   * @brief Checks whether the device supports the face auto-focus function.
   *
   * @note This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback
   * and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @return
   * - `true`: The device supports the face auto-focus function.
   * - `false`: The device does not support the face auto-focus function.
   */
  public abstract boolean isCameraAutoFocusFaceModeSupported();

  /**
   * @brief Checks whether the device camera supports face detection.
   *
   * @return
   * - `true`: The device camera supports face detection.
   * - `false`: The device camera does not support face detection.
   */
  public abstract boolean isCameraFaceDetectSupported();

  /**
   * @brief Queries whether the current camera supports adjusting exposure value.
   *
   * @since v4.2.2
   *
   * @note
   * - This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback and
   * returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   * - Before calling `setCameraExposureFactor`, Agora recoomends that you call this method to query
   * whether the current camera supports adjusting the exposure value.
   * - By calling this method, you adjust the exposure value of the currently active camera, that is,
   * the camera specified when calling `setCameraCapturerConfiguration`.
   *
   * @return
   * - `true`: Success.
   * - `false`: Failure.
   */
  public abstract boolean isCameraExposureSupported();

  /**
   * @brief Sets the camera zoom factor.
   *
   * @note You must call this method after `enableVideo`. The setting result will take effect after
   * the camera is successfully turned on, that is, after the SDK triggers the
   * `onLocalVideoStateChanged` callback and returns the local video state as
   * `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @param factor Camera zoom factor. You can get the maximum zoom factor supported by the device by
   * calling the `getCameraMaxZoomFactor` method.
   *
   * @return
   * - The camera zoom `factor` value, if successful.
   * - < 0: if the method if failed.
   */
  public abstract int setCameraZoomFactor(float factor);

  /**
   * @brief Gets the maximum zoom ratio supported by the camera.
   *
   * @note This method must be called after the SDK triggers the `onLocalVideoStateChanged` callback
   * and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @return
   * The maximum zoom ratio supported by the camera.
   */
  public abstract float getCameraMaxZoomFactor();

  /**
   * @brief Sets the camera manual focus position.
   *
   * @note
   * - You must call this method after `enableVideo`. The setting result will take effect after the
   * camera is successfully turned on, that is, after the SDK triggers the `onLocalVideoStateChanged`
   * callback and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   * - After a successful method call, the SDK triggers the `onCameraFocusAreaChanged` callback.
   *
   * @param positionX The horizontal coordinate of the touchpoint in the view.
   * @param positionY The vertical coordinate of the touchpoint in the view.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setCameraFocusPositionInPreview(float positionX, float positionY);

  /**
   * @brief Sets the camera exposure position.
   *
   * @since v2.3.2.
   *
   * @note
   * - You must call this method after `enableVideo`. The setting result will take effect after the
   * camera is successfully turned on, that is, after the SDK triggers the `onLocalVideoStateChanged`
   * callback and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   * - After a successful method call, the SDK triggers the `onCameraExposureAreaChanged` callback.
   *
   * @param positionXinView The horizontal coordinate of the touchpoint in the view.
   * @param positionYinView The vertical coordinate of the touchpoint in the view.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setCameraExposurePosition(float positionXinView, float positionYinView);

  /**
   * @brief Enables or disables face detection for the local user.
   *
   * @details
   * Call timing: This method needs to be called after the camera is started (for example, by calling
   * `startPreview(Constants.VideoSourceType sourceType)` or `enableVideo` ).
   * Related callbacks: Once face detection is enabled, the SDK triggers the `onFacePositionChanged`
   * callback to report the face information of the local user, which includes the following:
   * - The width and height of the local video.
   * - The position of the human face in the local view.
   * - The distance between the human face and the screen.
   *
   * @param enabled Whether to enable face detection for the local user:
   * - `true`: Enable face detection.
   * - `false`: (Default) Disable face detection.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableFaceDetection(boolean enabled);

  /**
   * @brief Enables the camera flash.
   *
   * @note You must call this method after `enableVideo`. The setting result will take effect after
   * the camera is successfully turned on, that is, after the SDK triggers the
   * `onLocalVideoStateChanged` callback and returns the local video state as
   * `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @param isOn Whether to turn on the camera flash:
   * - `true`: Turn on the flash.
   * - `false`: (Default) Turn off the flash.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setCameraTorchOn(boolean isOn);

  /**
   * @brief Enables the camera auto-face focus function.
   *
   * @details
   * The SDK disables face autofocus by default. To enable face autofocus, call this method.
   * Call timing: This method must be called after the SDK triggers the `onLocalVideoStateChanged`
   * callback and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   *
   * @param enabled Whether to enable face autofocus:
   * - `true`: Enable the camera auto-face focus function.
   * - `false`: Disable face auto-focus.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setCameraAutoFocusFaceModeEnabled(boolean enabled);

  /**
   * @brief Sets the camera exposure value.
   *
   * @since v4.2.2
   *
   * @details
   * Insufficient or excessive lighting in the shooting environment can affect the image quality of
   * video capture. To achieve optimal video quality, you can use this method to adjust the camera's
   * exposure value.
   *
   * @note
   * - You must call this method after `enableVideo`. The setting result will take effect after the
   * camera is successfully turned on, that is, after the SDK triggers the `onLocalVideoStateChanged`
   * callback and returns the local video state as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` (1).
   * - Before calling this method, Agora recommends calling `isCameraExposureSupported` to check
   * whether the current camera supports adjusting the exposure value.
   * - By calling this method, you adjust the exposure value of the currently active camera, that is,
   * the camera specified when calling `setCameraCapturerConfiguration`.
   *
   * @param factor The camera exposure value. The default value is 0, which means using the default
   * exposure of the camera. The larger the value, the greater the exposure. When the video image is
   * overexposed, you can reduce the exposure value; when the video image is underexposed and the dark
   * details are lost, you can increase the exposure value. If the exposure value you specified is
   * beyond the range supported by the device, the SDK will automatically adjust it to the actual
   * supported range of the device.
   * The value range is [-20, 20].
   *
   */
  public abstract int setCameraExposureFactor(int factor);

  /**
   * @brief Retrieves the call ID.
   *
   * @details
   * When a user joins a channel on a client, a `callId` is generated to identify the call from the
   * client. You can call this method to get `callId`, and pass it in when calling methods such as
   * `rate` and `complain`.
   * Call timing: Call this method after joining a channel.
   *
   * @param callId Output parameter, the current call ID.
   *
   * @return
   * The current call ID.
   */
  public abstract String getCallId();

  /**
   * @brief Allows a user to rate a call after the call ends.
   *
   * @note Ensure that you call this method after leaving a channel.
   *
   * @param callId The current call ID. You can get the call ID by calling `getCallId`.
   * @param rating The value is between 1 (the lowest score) and 5 (the highest score).
   * @param description (Optional) A description of the call. The string length should be less than
   * 800 bytes.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   */
  public abstract int rate(String callId, int rating, String description);

  /**
   * @brief Allows a user to complain about the call quality after a call ends.
   *
   * @details
   * This method allows users to complain about the quality of the call. Call this method after the
   * user leaves the channel.
   *
   * @param callId The current call ID. You can get the call ID by calling `getCallId`.
   * @param description (Optional) A description of the call. The string length should be less than
   * 800 bytes.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -7: The method is called before `RtcEngine` is initialized.
   */
  public abstract int complain(String callId, String description);

  /**
   * @brief Gets the SDK version.
   *
   * @return
   * The SDK version number. The format is a string.
   */
  public static String getSdkVersion() {
    if (!RtcEngineImpl.initializeNativeLibs())
      return "";
    return RtcEngineImpl.nativeGetSdkVersion();
  }

  /**
   * Returns the media engine version.
   *
   * @return The string of the version number in char format.
   */
  @Deprecated
  public static String getMediaEngineVersion() {
    if (!RtcEngineImpl.initializeNativeLibs())
      return "";
    return RtcEngineImpl.nativeGetMediaEngineVersion();
  }

  /**
   * @brief Sets the log file.
   *
   * @details
   * Specifies an SDK output log file. The log file records all log data for the SDK’s operation.
   * Call timing: This method needs to be called immediately after `create(RtcEngineConfig config)`, otherwise the
   * output log may be incomplete.
   *
   * @note Ensure that the directory for the log file exists and is writable.
   *
   * @param filePath The complete path of the log files. These log files are encoded in UTF-8.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLogFile(String filePath);

  /**
   * @brief Sets the log output level of the SDK.
   *
   * @details
   * This method sets the output log level of the SDK. You can use one or a combination of the log
   * filter levels. The log level follows the sequence of `LOG_FILTER_OFF`, `LOG_FILTER_CRITICAL`,
   * `LOG_FILTER_ERROR`, `LOG_FILTER_WARN`, `LOG_FILTER_INFO`, and `LOG_FILTER_DEBUG`. Choose a level
   * to see the logs preceding that level.
   * If, for example, you set the log level to `LOG_FILTER_WARN`, you see the logs within levels
   * `LOG_FILTER_CRITICAL`, `LOG_FILTER_ERROR` and `LOG_FILTER_WARN`.
   *
   * @param filter The output log level of the SDK.
   * - LOG_FILTER_OFF (0): Do not output any log information.
   * - LOG_FILTER_DEBUG (0x080f): Output all log information. Set your log filter as DEBUG if you want
   * to get the most complete log file.
   * - LOG_FILTER_INFO (0x0f): Output `LOG_FILTER_CRITICAL`, `LOG_FILTER_ERROR`, `LOG_FILTER_WARN`,
   * and `LOG_FILTER_INFO` level log information. Agora recommends that you set the log level to this
   * level.
   * - LOG_FILTER_WARN (0x0e): Output `LOG_FILTER_CRITICAL`, `LOG_FILTER_ERROR` and `LOG_FILTER_WARN`
   * level log information.
   * - LOG_FILTER_ERROR (0x0c): Output `LOG_FILTER_CRITICAL` and `LOG_FILTER_ERROR` level log
   * information.
   * - LOG_FILTER_CRITICAL (0x08): Output `LOG_FILTER_CRITICAL` level log information.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLogFilter(int filter);

  /**
   * @brief Sets the output log level of the SDK.
   *
   * @details
   * Choose a level to see the logs preceding that level.
   *
   * @param level The log level. See `LogLevel`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLogLevel(int level);

  /**
   * @brief Sets the log file size.
   *
   * @details
   * By default, the SDK generates five SDK log files and five API call log files with the following
   * rules:
   * - The SDK log files are: `agorasdk.log`, `agorasdk.1.log`, `agorasdk.2.log`, `agorasdk.3.log`,
   * and `agorasdk.4.log`.
   * - The API call log files are: `agoraapi.log`, `agoraapi.1.log`, `agoraapi.2.log`,
   * `agoraapi.3.log`, and `agoraapi.4.log`.
   * - The default size of each SDK log file and API log file is 2,048 KB. These log files are encoded
   * in UTF-8.
   * - The SDK writes the latest logs in `agorasdk.log` or `agoraapi.log`.
   * - When `agorasdk.log` is full, the SDK processes the log files in the following order:1. Delete
   * the `agorasdk.4.log` file (if any).
   * 2. Rename `agorasdk.3.log` to `agorasdk.4.log`.
   * 3. Rename `agorasdk.2.log` to `agorasdk.3.log`.
   * 4. Rename `agorasdk.1.log` to `agorasdk.2.log`.
   * 5. Create a new `agorasdk.log` file.
   * - The overwrite rules for the `agoraapi.log` file are the same as for `agorasdk.log`.
   *
   * @note This method is used to set the size of the `agorasdk.log` file only and does not effect the
   * `agoraapi.log file`.
   *
   * @param fileSizeInKBytes The size (KB) of an `agorasdk.log` file. The value range is [128,20480].
   * The default value is 2,048 KB. If you set `fileSizeInKByte` smaller than 128 KB, the SDK
   * automatically adjusts it to 128 KB; if you set `fileSizeInKByte` greater than 20,480 KB, the SDK
   * automatically adjusts it to 20,480 KB.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setLogFileSize(long fileSizeInKBytes);

  /**
   * Upload current log file immediately to server.
   *  only use this when an error occurs
   *  block before log file upload success or timeout.
   *
   *  @return
   *  - 0: Success.
   *  - < 0: Failure.
   */
  public abstract String uploadLogFile();

  /**
   * Write the log to SDK . @technical preview
   *
   * You can use one of the level defined in LogLevel.
   *
   * @param level Sets the log level:
   * {@link Constants.LogLevel LogLevel}.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int writeLog(int level, String format, Object... args);

  /**
   * @brief Gets the C++ handle of the Native SDK.
   *
   * @details
   * This method retrieves the C++ handle of the SDK, which is used for registering the audio and
   * video frame observer.
   *
   * @return
   * The native handle of the SDK.
   */
  public abstract long getNativeHandle();

  /**
   * @brief Adds event handlers.
   *
   * @details
   * The SDK uses the `IRtcEngineEventHandler` class to send callbacks to the app. The app inherits
   * the methods of this class to receive these callbacks. All methods in this class have default
   * (empty) implementations. Therefore, apps only need to inherits callbacks according to the
   * scenarios. In the callbacks, avoid time-consuming tasks or calling APIs that can block the
   * thread, such as the sendStreamMessage method. Otherwise, the SDK may not work properly.
   *
   * @param handler Callback events to be added. See `IRtcEngineEventHandler`.
   *
   */
  public void addHandler(IRtcEngineEventHandler handler) {
    mInstance.addHandler(handler);
  }

  /**
   * @brief Removes the specified IRtcEngineEventHandler instance.
   *
   * @details
   * This method removes the specified callback handler. For callback events that you want to listen
   * for only once, call this method to remove the relevant callback handler after you have received
   * them.
   *
   * @param handler The callback handler to be deleted. See `IRtcEngineEventHandler`.
   *
   */
  public void removeHandler(IRtcEngineEventHandler handler) {
    mInstance.removeHandler(handler);
  }

  /**
   * Enables the Wi-Fi mode.
   * @deprecated This method is deprecated.
   * @param enable Whether to enable/disable the Wi-Fi mode:
   *               - `true`: Enable the Wi-Fi mode.
   *               - `false`: Disable the Wi-Fi mode.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract boolean enableHighPerfWifiMode(boolean enable);

  /**
   * <p>
   * Returns the native handler of the mediaplayer.
   */
  public abstract long getNativeMediaPlayer(int sourceId);

  /**
   * @brief Gets the warning or error description.
   *
   * @param error The error code reported by the SDK.
   *
   * @return
   * The specific error description.
   */
  public static String getErrorDescription(int error) {
    if (!RtcEngineImpl.initializeNativeLibs())
      return "";
    return RtcEngineImpl.nativeGetErrorDescription(error);
  }

  /**
   * Queries the HDR capability of the video module.
   *
   * @since v4.6.0
   * 
   * @technical preview
   * 
   * @return
   * - 0: Unsupported.
   * - 1: Supported.
   * - < 0: Unknown.
   */
  public abstract int queryHDRCapability(Constants.VIDEO_MODULE_TYPE moduleType);

  /**
   * @brief Queries the highest frame rate supported by the device during screen sharing.
   *
   * @since v4.2.0
   *
   * @details
   * Applicable scenarios: To ensure optimal screen sharing performance, particularly in enabling high
   * frame rates like 60 fps, Agora recommends you to query the device's maximum supported frame rate
   * using this method beforehand. This way, if the device cannot support such a high frame rate, you
   * can adjust the screen sharing stream accordingly to avoid any negative impact on the sharing
   * quality. If the device does not support high frame rate, you can reduce the frame rate of the
   * screen sharing stream appropriately when sharing the screen to ensure that the sharing effect
   * meets your expectation.
   *
   * @return
   * - The highest frame rate supported by the device, if the method is called successfully.
   *   - 0: The device supports the frame rate of up to 15 fps.
   *   - 1: The device supports the frame rate of up to 30 fps.
   *   - 2: The device supports the frame rate of up to 60 fps.
   * - < 0: Failure.
   */
  public abstract int queryScreenCaptureCapability();

  /**
   * Monitors external headset device events.
   *
   * @param monitor Whether to enable monitoring external headset device events.
   *                True/False.
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  @Deprecated public abstract void monitorHeadsetEvent(boolean monitor);

  /**
   * Monitors Bluetooth headset device events.
   *
   * @param monitor Whether to enable monitoring Bluetooth headset device events.
   *                True/False.
   * @return
   *         <ul>
   *         <li>0: Success.
   *         <li><0: Failure.
   *         </ul>
   */
  @Deprecated public abstract void monitorBluetoothHeadsetEvent(boolean monitor);

  /**
   * Sets the default audio route to the headset.
   *
   * @deprecated This method is deprecated.
   * @param enabled Sets whether or not the default audio route is to the headset:
   * <ul>
   *     <li>true: Set the default audio route to the headset.</li>
   *     <li>false: Do not set the default audio route to the headset.</li>
   * </ul>
   */
  @Deprecated public abstract void setPreferHeadset(boolean enabled);

  /**
   * @brief Provides technical preview functionalities or special customizations by configuring the
   * SDK with JSON options.
   *
   * @param parameters Pointer to the set parameters in a JSON string.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setParameters(String parameters);

  /**
   * Queries internal states
   * @param parameters JSON string
   * @return A JSON string
   */
  public abstract String getParameters(String parameters);

  /**
   * Gets the Agora SDK’s parameters for customization purposes.
   * This method is not disclosed yet. Contact <a
   * href="mailto:support@agora.io">support@agora.io</a> for more information.
   *
   */
  public abstract String getParameter(String parameter, String args);

  /**
   * @brief Registers the metadata observer.
   *
   * @details
   * You need to implement the `IMetadataObserver` class and specify the metadata type in this method.
   * This method enables you to add synchronized metadata in the video stream for more diversified
   * live interactive streaming, such as sending shopping links, digital coupons, and online quizzes.
   * A successful call of this method triggers the `getMaxMetadataSize` callback.
   *
   * @note Call this method before `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`.
   *
   * @param observer The metadata observer. See `IMetadataObserver`.
   * @param type The metadata type. The SDK currently only supports `VIDEO_METADATA`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerMediaMetadataObserver(IMetadataObserver observer, int type);

  /**
   * @brief Unregisters the specified metadata observer.
   *
   * @param observer The metadata observer. See `IMetadataObserver`.
   * @param type The metadata type. The SDK currently only supports `VIDEO_METADATA`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int unregisterMediaMetadataObserver(IMetadataObserver observer, int type);

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
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -2: The parameter is invalid.
   *   - -8: Internal state error. Probably because the user is not a broadcaster.
   */
  public abstract int startOrUpdateChannelMediaRelay(
      ChannelMediaRelayConfiguration channelMediaRelayConfiguration);

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
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no ongoing channel media relay.
   */
  public abstract int stopChannelMediaRelay();

  /**
   * @brief Pauses the media stream relay to all target channels.
   *
   * @details
   * After the cross-channel media stream relay starts, you can call this method to pause relaying
   * media streams to all target channels; after the pause, if you want to resume the relay, call
   * `resumeAllChannelMediaRelay`.
   *
   * @note Call this method after `startOrUpdateChannelMediaRelay`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no ongoing channel media relay.
   */
  public abstract int pauseAllChannelMediaRelay();

  /**
   * @brief Resumes the media stream relay to all target channels.
   *
   * @details
   * After calling the `pauseAllChannelMediaRelay` method, you can call this method to resume relaying
   * media streams to all destination channels.
   *
   * @note Call this method after `pauseAllChannelMediaRelay`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -5: The method call was rejected. There is no paused channel media relay.
   */
  public abstract int resumeAllChannelMediaRelay();

  // Auxiliary API
  /**
   * @brief Updates the channel media options after joining the channel.
   *
   * @param options The channel media options. See `ChannelMediaOptions`.
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
  public abstract int updateChannelMediaOptions(ChannelMediaOptions options);

  /**
   * @brief Whether to mute the recording signal.
   *
   * @details
   * If you have already called `adjustRecordingSignalVolume` to adjust the recording signal volume,
   * when you call this method and set it to `true`, the SDK behaves as follows:1. Records the
   * adjusted volume.
   * 2. Mutes the recording signal.
   * When you call this method again and set it to `false`, the recording signal volume will be
   * restored to the volume recorded by the SDK before muting.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param muted - `true`: Mute the recording signal.
   * - `false`: (Default) Do not mute the recording signal.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int muteRecordingSignal(boolean muted);

  /**
   * @brief Sets the format of the raw audio playback data before mixing.
   *
   * @details
   * The SDK triggers the `onPlaybackAudioFrameBeforeMixing` callback according to the sampling
   * interval.
   * Call timing: Call this method before joining a channel.
   *
   * @param sampleRate The sample rate returned in the callback, which can be set as 8000, 16000,
   * 32000, 44100, or 48000 Hz.
   * @param channel The number of audio channels. You can set the value as 1 or 2.
   * - 1: Mono.
   * - 2: Stereo.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setPlaybackAudioFrameBeforeMixingParameters(int sampleRate, int channel);

  /**
   * @brief Sets the format of audio data in the `onPlaybackAudioFrameBeforeMixing` callback.
   *
   * @details
   * Used to set the sample rate, number of channels, and number of samples per callback for the audio
   * data returned in the `onPlaybackAudioFrameBeforeMixing` callback.
   *
   * @param sampleRate Set the sample rate returned in the `onPlaybackAudioFrameBeforeMixing`
   * callback. It can be set as the following values:
   * - 8000.
   * - 16000
   * - 32000.
   * - 44100
   * - 48000 (Hz).
   * @param channel Set the number of channels for the audio data returned in the
   * `onPlaybackAudioFrameBeforeMixing` callback. It can be set to:
   * - 1: Mono.
   * - 2: Stereo.
   * @param samplesPerCall Set the sample rate of the audio data returned in the `onMixedAudioFrame`
   * callback. In the RTMP streaming scenario, it is recommended to set it to 1024.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setPlaybackAudioFrameBeforeMixingParameters(
      int sampleRate, int channel, int samplesPerCall);

  /**
   * @brief Turns on audio spectrum monitoring.
   *
   * @details
   * If you want to obtain the audio spectrum data of local or remote users, you can register the
   * audio spectrum observer and enable audio spectrum monitoring.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @param intervalInMS The interval (in milliseconds) at which the SDK triggers the
   * `onLocalAudioSpectrum` and `onRemoteAudioSpectrum` callbacks. The default value is 100. Do not
   * set this parameter to a value less than 10, otherwise calling this method would fail.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: Invalid parameters.
   */
  public abstract int enableAudioSpectrumMonitor(int intervalInMS);
  /**
   * @brief Disables audio spectrum monitoring.
   *
   * @details
   * After calling `enableAudioSpectrumMonitor`, if you want to disable audio spectrum monitoring, you
   * can call this method.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int disableAudioSpectrumMonitor();

  /**
   * @brief Registers an audio spectrum observer.
   *
   * @details
   * After successfully registering the audio spectrum observer and calling
   *  `enableAudioSpectrumMonitor` to enable the audio spectrum monitoring, the SDK reports the
   * callback that you implement in the `IAudioSpectrumObserver` class according to the time interval
   * you set.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @param observer The audio spectrum observer. See `IAudioSpectrumObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerAudioSpectrumObserver(IAudioSpectrumObserver observer);
  /**
   * @brief Unregisters the audio spectrum observer.
   *
   * @details
   * After calling `registerAudioSpectrumObserver`, if you want to disable audio spectrum monitoring,
   * you can call this method.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @param observer The audio spectrum observer. See `IAudioSpectrumObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int unRegisterAudioSpectrumObserver(IAudioSpectrumObserver observer);

  /**
   * @brief Retrieves the volume of the audio effects.
   *
   * @details
   * The volume is an integer ranging from 0 to 100. The default value is 100, which means the
   * original volume.
   *
   * @note Call this method after `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @return
   * - Volume of the audio effects, if this method call succeeds.
   * - < 0: Failure.
   */
  public abstract double getEffectsVolume();

  /**
   * @brief Sets the volume of the audio effects.
   *
   * @details
   * Call timing: Call this method after `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @param volume The playback volume. The value range is [0, 100]. The default value is 100, which
   * represents the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setEffectsVolume(double volume);

  /**
   * @brief Preloads a specified audio effect file into the memory.
   *
   * @details
   * Ensure the size of all preloaded files does not exceed the limit.
   * For the audio file formats supported by this method, see [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * Call timing: Agora recommends that you call this method before joining a channel.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.
   * @param filePath File path:
   * - Android: The file path, which needs to be accurate to the file name and suffix. Agora supports
   * URL addresses, absolute paths, or file paths that start with `/assets/`. You might encounter
   * permission issues if you use an absolute path to access a local file, so Agora recommends using a
   * URI address instead. For example:
   * `content://com.android.providers.media.documents/document/audio%3A14441`
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int preloadEffect(int soundId, String filePath);

  /**
   * Preloads a specified audio effect.
   *
   * This method preloads only one specified audio effect into the memory each time
   * it is called. To preload multiple audio effects, call this method multiple times.
   *
   * After preloading, you can call {@link RtcEngine#playEffect() playEffect} to play the preloaded
   * audio effect or call
   * {@link RtcEngine#playAllEffects() playAllEffects} to play all the preloaded audio effects.
   *
   * @note
   * - To ensure smooth communication, limit the size of the audio effect file.
   * - Agora recommends calling this method before joining the channel.
   * - If preloadEffect is called before playEffect is executed, the file resource will not be
   * closed after playEffect. The next time playEffect is executed, it will directly seek to play at
   * the beginning.
   * - If preloadEffect is not called before playEffect is executed, the resource will be destroyed
   * after playEffect. The next time playEffect is executed, it will try to reopen the file and play
   * it from the beginning.
   *
   * @param soundId  The ID of the audio effect.
   * @param filePath The absolute path of the local audio effect file or the URL
   * @param startPos The start position
   * of the online audio effect file. Supported audio formats: mp3, mp4, m4a, aac,
   * 3gp, mkv and wav.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int preloadEffect(int soundId, String filePath, int startPos);

  /**
   * @brief Plays the specified local or online audio effect file.
   *
   * @details
   * This method supports playing URI files starting with `content://`. For the audio file formats
   * supported by this method, see ` What formats of audio files does the Agora RTC SDK support`. If
   * the local music file does not exist, the SDK does not support the file format, or the the SDK
   * cannot access the music file URL, the SDK reports AUDIO_MIXING_REASON_CAN_NOT_OPEN.
   * To play multiple audio effect files at the same time, call this method multiple times with
   * different `soundId` and `filePath`. To achieve the optimal user experience, Agora recommends that
   * you do not playing more than three audio files at the same time.
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: After the playback of an audio effect file completes, the SDK triggers the
   * `onAudioEffectFinished` callback.
   *
   * @note If you need to play an online audio effect file, Agora recommends that you cache the online
   * audio effect file to your local device, call `preloadEffect` to preload the file into memory, and
   * then call this method to play the audio effect. Otherwise, you might encounter playback failures
   * or no sound during playback due to loading timeouts or failures.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.Attention: If you
   * have preloaded an audio effect into memory by calling `preloadEffect`, ensure that the value of
   * this parameter is the same as that of `soundId` in `preloadEffect`.
   * @param filePath The file path. The SDK supports URI addresses starting with `content://`, paths
   * starting with `/assets/`, URLs and absolute paths of local files. The absolute path needs to be
   * accurate to the file name and extension. Supported audio formats include MP3, AAC, M4A, MP4, WAV,
   * and 3GP. See [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * Attention: If you have preloaded an audio effect into memory by calling `preloadEffect`, ensure
   * that the value of this parameter is the same as that of `filePath` in `preloadEffect`.
   * @param loopCount The number of times the audio effect loops.
   * - ≥ 0: The number of playback times. For example, 1 means looping one time, which means playing
   * the audio effect two times in total.
   * - -1: Play the audio file in an infinite loop.
   * @param pitch The pitch of the audio effect. The value range is 0.5 to 2.0. The default value is
   * 1.0, which means the original pitch. The lower the value, the lower the pitch.
   * @param pan The spatial position of the audio effect. The value ranges between -1.0 and 1.0:
   * - -1.0: The audio effect is heard on the left of the user.
   * - 0.0: The audio effect is heard in front of the user.
   * - 1.0: The audio effect is heard on the right of the user.
   * @param gain The volume of the audio effect. The value range is 0.0 to 100.0. The default value is
   * 100.0, which means the original volume. The smaller the value, the lower the volume.
   * @param publish Whether to publish the audio effect to the remote users.
   * - `true`: Publish the audio effect to the remote users. Both the local user and remote users can
   * hear the audio effect.
   * - `false`: Do not publish the audio effect to the remote users. Only the local user can hear the
   * audio effect.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int playEffect(int soundId, String filePath, int loopCount, double pitch,
      double pan, double gain, boolean publish);

  /**
   * @brief Plays the specified local or online audio effect file.
   *
   * @details
   * This method supports playing URI files starting with `content://`. For the audio file formats
   * supported by this method, see ` What formats of audio files does the Agora RTC SDK support`. If
   * the local music file does not exist, the SDK does not support the file format, or the the SDK
   * cannot access the music file URL, the SDK reports AUDIO_MIXING_REASON_CAN_NOT_OPEN.
   * To play multiple audio effect files at the same time, call this method multiple times with
   * different `soundId` and `filePath`. To achieve the optimal user experience, Agora recommends that
   * you do not playing more than three audio files at the same time.
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: After the playback of an audio effect file completes, the SDK triggers the
   * `onAudioEffectFinished` callback.
   *
   * @note If you need to play an online audio effect file, Agora recommends that you cache the online
   * audio effect file to your local device, call `preloadEffect` to preload the file into memory, and
   * then call this method to play the audio effect. Otherwise, you might encounter playback failures
   * or no sound during playback due to loading timeouts or failures.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.Attention: If you
   * have preloaded an audio effect into memory by calling `preloadEffect`, ensure that the value of
   * this parameter is the same as that of `soundId` in `preloadEffect`.
   * @param filePath The file path. The SDK supports URI addresses starting with `content://`, paths
   * starting with `/assets/`, URLs and absolute paths of local files. The absolute path needs to be
   * accurate to the file name and extension. Supported audio formats include MP3, AAC, M4A, MP4, WAV,
   * and 3GP. See [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * Attention: If you have preloaded an audio effect into memory by calling `preloadEffect`, ensure
   * that the value of this parameter is the same as that of `filePath` in `preloadEffect`.
   * @param loopCount The number of times the audio effect loops.
   * - ≥ 0: The number of playback times. For example, 1 means looping one time, which means playing
   * the audio effect two times in total.
   * - -1: Play the audio file in an infinite loop.
   * @param pitch The pitch of the audio effect. The value range is 0.5 to 2.0. The default value is
   * 1.0, which means the original pitch. The lower the value, the lower the pitch.
   * @param pan The spatial position of the audio effect. The value ranges between -1.0 and 1.0:
   * - -1.0: The audio effect is heard on the left of the user.
   * - 0.0: The audio effect is heard in front of the user.
   * - 1.0: The audio effect is heard on the right of the user.
   * @param gain The volume of the audio effect. The value range is 0.0 to 100.0. The default value is
   * 100.0, which means the original volume. The smaller the value, the lower the volume.
   * @param publish Whether to publish the audio effect to the remote users:
   * - `true`: Publish the audio effect to the remote users. Both the local user and remote users can
   * hear the audio effect.
   * - `false`: Do not publish the audio effect to the remote users. Only the local user can hear the
   * audio effect.
   * @param startPos The playback position (ms) of the audio effect file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int playEffect(int soundId, String filePath, int loopCount, double pitch,
      double pan, double gain, boolean publish, int startPos);

  /**
   * @breif Plays all audio effects.
   *
   * @details
   * After calling `preloadEffect` multiple times to preload multiple audio effects into the 
   * memory, you can call this method to play all the specified audio effects for all users in
   * the channel.
   *
   * @param loopCount The number of times the audio effect loops:
   * - `-1`: Play the audio effect in an indefinite loop until you call `stopEffect` or 
   * `stopAllEffects.
   * - `0`: Play the audio effect once.
   * - `1`: Play the audio effect twice.
   * @param pitch The pitch of the audio effect. The value ranges between 0.5 and 2.0.
   * The default value is 1.0 (original pitch). The lower the value, the lower the pitch.
   * @param pan The spatial position of the audio effect. The value ranges between -1.0 and 1.0:
   * - -1.0: The audio effect shows on the left.
   * - 0.0: The audio effect shows ahead.
   * - 1.0: The audio effect shows on the right.
   * @param gain The volume of the audio effect. The value range is [0, 100].
   * The default value is 100 (original volume). The lower the value, the lower
   * the volume of the audio effect.
   * @param publish Whether to publish the specified audio effect to the remote users:
   * - `true`: Publish the audio effect to the remote users. Both the local user and 
   * remote users can hear the audio effect.
   * - `false`: (Default) Do not publish the audio effect to the remote users. Only the local 
   * user can hear the audio effect.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int playAllEffects(
      int loopCount, double pitch, double pan, double gain, boolean publish);

  /**
   * @brief Gets the volume of a specified audio effect file.
   *
   * @param soundId The ID of the audio effect file.
   *
   * @return
   * - ≥ 0: Returns the volume of the specified audio effect, if the method call is successful. The
   * value ranges between 0 and 100. 100 represents the original volume.
   * - < 0: Failure.
   */
  public abstract int getVolumeOfEffect(int soundId);

  /**
   * @brief Gets the volume of a specified audio effect file.
   *
   * @details
   * Call timing: Call this method after `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @param soundId The ID of the audio effect. The unique ID of each audio effect file.
   * @param volume The playback volume. The value range is [0, 100]. The default value is 100, which
   * represents the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVolumeOfEffect(int soundId, double volume);

  /**
   * @brief Pauses a specified audio effect file.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pauseEffect(int soundId);

  /**
   * @brief Pauses all audio effects.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int pauseAllEffects();

  /**
   * @brief Resumes playing a specified audio effect.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int resumeEffect(int soundId);

  /**
   * @brief Resumes playing all audio effect files.
   *
   * @details
   * After you call `pauseAllEffects` to pause the playback, you can call this method to resume the
   * playback.
   * Call timing: Call this method after `pauseAllEffects`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int resumeAllEffects();

  /**
   * @brief Stops playing a specified audio effect.
   *
   * @details
   * When you no longer need to play the audio effect, you can call this method to stop the playback.
   * If you only need to pause the playback, call `pauseEffect`.
   * Call timing: Call this method after the `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish)` or `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @param soundId The ID of the audio effect. Each audio effect has a unique ID.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopEffect(int soundId);

  /**
   * @brief Stops playing all audio effects.
   *
   * @details
   * When you no longer need to play the audio effect, you can call this method to stop the playback.
   * If you only need to pause the playback, call `pauseAllEffects`.
   * Call timing: Call this method after the `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish)` or `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopAllEffects();

  /**
   * @brief Releases a specified preloaded audio effect from the memory.
   *
   * @details
   * After loading the audio effect file into memory using `preloadEffect`, if you need to release the
   * audio effect file, call this method.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param soundId The ID of the audio effect. Each audio effect has a unique ID.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int unloadEffect(int soundId);

  /**
   * Releases all preloaded audio effects from the memory.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int unloadAllEffects();

  /**
   * @brief Retrieves the duration of the audio effect file.
   *
   * @note Call this method after joining a channel.
   *
   * @param filePath File path:
   * - Android: The file path, which needs to be accurate to the file name and suffix. Agora supports
   * URL addresses, absolute paths, or file paths that start with `/assets/`. You might encounter
   * permission issues if you use an absolute path to access a local file, so Agora recommends using a
   * URI address instead. For example:
   * `content://com.android.providers.media.documents/document/audio%3A14441`
   *
   * @return
   * - The total duration (ms) of the specified audio effect file, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int getEffectDuration(String filePath);

  /**
   * @brief Sets the playback position of an audio effect file.
   *
   * @details
   * After a successful setting, the local audio effect file starts playing at the specified position.
   *
   * @note Call this method after `playEffect`.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.
   * @param pos The playback position (ms) of the audio effect file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setEffectPosition(int soundId, int pos);

  /**
   * @brief Retrieves the playback position of the audio effect file.
   *
   * @note Call this method after `playEffect(int soundId, String filePath, int loopCount, double pitch, double pan, double gain, boolean publish, int startPos)`.
   *
   * @param soundId The audio effect ID. The ID of each audio effect file is unique.
   *
   * @return
   * - The playback position (ms) of the specified audio effect file, if the method call succeeds.
   * - < 0: Failure.
   */
  public abstract int getEffectCurrentPosition(int soundId);

  /**
   * @brief Registers a receiver object for the encoded video image.
   *
   * @details
   * If you only want to observe encoded video frames (such as H.264 format) without decoding and
   * rendering the video, Agora recommends that you implement one `IVideoEncodedFrameObserver` class
   * through this method.
   *
   * @note Call this method before joining a channel.
   *
   * @param receiver The video frame observer object. See `IVideoEncodedFrameObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerVideoEncodedFrameObserver(IVideoEncodedFrameObserver receiver);

  /**
   * @brief Registers or unregisters a facial information observer.
   *
   * @details
   * You can call this method to register the `onFaceInfo` callback to receive the facial information
   * processed by Agora speech driven extension. When calling this method to register a facial
   * information observer, you can register callbacks in the `IFaceInfoObserver` class as needed.
   * After successfully registering the facial information observer, the SDK triggers the callback you
   * have registered when it captures the facial information converted by the speech driven extension.
   * Applicable scenarios: Facial information processed by the Agora speech driven extension is BS
   * (Blend Shape) data that complies with ARkit standards. You can further process the BS data using
   * third-party 3D rendering engines, such as driving avatar to make mouth movements corresponding to
   * speech.
   *
   * @note
   * - Call this method before joining a channel.
   * - Before calling this method, you need to make sure that the speech driven extension has been
   * enabled by calling `enableExtension`.
   *
   * @param receiver Facial information observer, see `IFaceInfoObserver`. If you need to unregister a
   * facial information observer, pass in NULL.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerFaceInfoObserver(IFaceInfoObserver receiver);

  /**
   * @brief Takes a snapshot of a video stream.
   *
   * @details
   * This method takes a snapshot of a video stream from the specified user, generates a JPG image,
   * and saves it to the specified path.
   * Call timing: Call this method after joining a channel.
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
   * @param uid The user ID. Set uid as 0 if you want to take a snapshot of the local user's video.
   * @param filePath The local path (including filename extensions) of the snapshot. For example:
   * - Android: `/storage/emulated/0/Android/data/<package name>/files/example.jpg`
   * Attention: Ensure that the path you specify exists and is writable.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int takeSnapshot(int uid, String filePath);

  /**
   * @brief Takes a screenshot of the video at the specified observation point.
   *
   * @details
   * This method takes a snapshot of a video stream from the specified user, generates a JPG image,
   * and saves it to the specified path.
   * Call timing: Call this method after joining a channel.
   * Related callbacks: After a successful call of this method, the SDK triggers the `onSnapshotTaken`
   * callback to report whether the snapshot is successfully taken, as well as the details for that
   * snapshot.
   *
   * @note
   * - The method is asynchronous, and the SDK has not taken the snapshot when the method call
   * returns.
   * - When used for local video snapshots, this method takes a snapshot for the video streams
   * specified in `ChannelMediaOptions`.
   *
   * @param uid The user ID. Set uid as 0 if you want to take a snapshot of the local user's video.
   * @param config The configuration of the snaptshot. See `SnapshotConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int takeSnapshot(int uid, SnapshotConfig config);

  /**
   * @brief Enables or disables video screenshot and upload.
   *
   * @details
   * When video screenshot and upload function is enabled, the SDK takes screenshots and uploads
   * videos sent by local users based on the type and frequency of the module you set in
   * `ContentInspectConfig`. After video screenshot and upload, the Agora server sends the callback
   * notification to your app server in HTTPS requests and sends all screenshots to the third-party
   * cloud storage service.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @note
   * - Before calling this method, make sure you have enabled video screenshot and upload on Agora
   * console.
   * - When the video moderation module is set to video moderation via Agora self-developed extension(
   * `CONTENT_INSPECT_TYPE_SUPERVISE` ), the video screenshot and upload dynamic library
   * `agora_content_inspect_extension.so` is required. Deleting this library disables the screenshot
   * and upload feature.
   *
   * @param enabled Whether to enalbe video screenshot and upload:
   * - `true`: Enables video screenshot and upload.
   * - `false`: Disables video screenshot and upload.
   * @param config Screenshot and upload configuration. See `ContentInspectConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableContentInspect(boolean enabled, ContentInspectConfig config);

  public abstract int loadExtensionProvider(String path);

  /**
   * @brief Registers an extension.
   *
   * @details
   * For extensions external to the SDK (such as those from Extensions Marketplace and SDK
   * Extensions), you need to load them before calling this method. Extensions internal to the SDK
   * (those included in the full SDK package) are automatically loaded and registered after the
   * initialization of `RtcEngine`.
   * Call timing: - Agora recommends you call this method after the initialization of `RtcEngine` and
   * before joining a channel.
   * - For video extensions (such as the image enhancement extension), you need to call this method
   * after enabling the video module by calling `enableVideo` or `enableLocalVideo`.
   * - Before calling this method, you need to call `addExtension` to load the extension first.
   *
   * @note
   * - If you want to register multiple extensions, you need to call this method multiple times.
   * - The data processing order of different extensions in the SDK is determined by the order in
   * which the extensions are registered. That is, the extension that is registered first will process
   * the data first.
   *
   * @param provider The name of the extension provider.
   * @param extension The name of the extension.
   * @param sourceType Source type of the extension. See `MediaSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -3: The extension library is not loaded. Agora recommends that you check the storage location
   * or the name of the dynamic library.
   */
  public abstract int registerExtension(
      String provider, String extension, Constants.MediaSourceType sourceType);

  /**
   * Enable/Disable an extension.
   * By calling this function, you can dynamically enable/disable the extension without changing the
   * pipeline. For example, enabling/disabling Extension_A means the data will be adapted/bypassed
   * by Extension_A.
   *
   * NOTE: For compatibility reasons, if you haven't call registerExtension,
   * enableExtension will automatically register the specified extension.
   * We suggest you call registerExtension explicitly.
   *
   * @param provider The name of the extension provider, e.g. agora.io.
   * @param extension The name of the extension, e.g. agora.beauty.
   * @param enable Whether to enable the extension:
   * - true: (Default) Enable the extension.
   * - false: Disable the extension.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableExtension(String provider, String extension, boolean enable);

  /**
   * @brief Enables or disables extensions.
   *
   * @details
   * Call timing: Agora recommends that you call this method after joining a channel.
   * Related callbacks: When this method is successfully called within the channel, it triggers
   * `onStartedWithContext` or `onStoppedWithContext`.
   *
   * @note
   * - If you want to enable multiple extensions, you need to call this method multiple times.
   * - After a successful call of this method, you cannot load other extensions.
   *
   * @param provider The name of the extension provider.
   * @param extension The name of the extension.
   * @param enable Whether to enable the extension:
   * - `true`: Enable the extension.
   * - `false`: Disable the extension.
   * @param sourceType Source type of the extension. See `MediaSourceType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -3: The extension library is not loaded. Agora recommends that you check the storage location
   * or the name of the dynamic library.
   */
  public abstract int enableExtension(
      String provider, String extension, boolean enable, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the properties of the extension.
   *
   * @details
   * After enabling the extension, you can call this method to set the properties of the extension.
   * Call timing: Call this mehtod after calling `enableExtension`.
   * Related callbacks: After calling this method, it may trigger the `onEventWithContext` callback,
   * and the specific triggering logic is related to the extension itself.
   *
   * @note If you want to set properties for multiple extensions, you need to call this method
   * multiple times.
   *
   * @param provider The name of the extension provider.
   * @param extension The name of the extension.
   * @param key The key of the extension.
   * @param value The value of the extension key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExtensionProperty(
      String provider, String extension, String key, String value);

  /**
   * Sets the properties of an extension.
   *
   * @param provider The name of the extension provider, e.g. agora.io.
   * @param extension The name of the extension, e.g. agora.beauty.
   * @param key The key of the extension.
   * @param value The JSON formatted value of the extension key.
   * @param sourceType The source type of the extension, e.g. PRIMARY_CAMERA_SOURCE. See {@link
   *     Constants#MediaSourceType}.
   *
   * @return true, if get property success, otherwise false
   */
  public abstract int setExtensionProperty(String provider, String extension, String key,
      String value, Constants.MediaSourceType sourceType);

  /**
   * @brief Gets detailed information on the extensions.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param provider The name of the extension provider.
   * @param extension The name of the extension.
   * @param key The key of the extension.
   *
   * @return
   * - The extension information, if the method call succeeds.
   * - An empty string, if the method call fails.
   */
  public abstract String getExtensionProperty(String provider, String extension, String key);

  /**
   * @brief Gets detailed information on the extensions.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param provider The name of the extension provider.
   * @param extension The name of the extension.
   * @param key The key of the extension.
   * @param sourceType Source type of the extension. See `MediaSourceType`.
   *
   * @return
   * - The extension information, if the method call succeeds.
   * - An empty string, if the method call fails.
   */
  public abstract String getExtensionProperty(
      String provider, String extension, String key, Constants.MediaSourceType sourceType);

  /**
   * @brief Sets the properties of the extension provider.
   *
   * @details
   * You can call this method to set the attributes of the extension provider and initialize the
   * relevant parameters according to the type of the provider.
   * Call timing: Call this method before `enableExtension` and after `registerExtension`.
   *
   * @note If you want to set the properties of the extension provider for multiple extensions, you
   * need to call this method multiple times.
   *
   * @param provider The name of the extension provider.
   * @param key The key of the extension.
   * @param value The value of the extension key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExtensionProviderProperty(String provider, String key, String value);

  /**
   * Enable/Disable an extension.
   * By calling this function, you can dynamically enable/disable the extension without changing the
   * pipeline. For example, enabling/disabling Extension_A means the data will be adapted/bypassed
   * by Extension_A.
   *
   * NOTE: For compatibility reasons, if you haven't call registerExtension,
   * enableExtension will automatically register the specified extension.
   * We suggest you call registerExtension explicitly.
   *
   * @param provider The name of the extension provider, e.g. agora.io.
   * @param extension The name of the extension, e.g. agora.beauty.
   * @param extensionInfo The information for extension. See {@link ExtensionInfo}.
   * @param enable Whether to enable the extension:
   * - true: (Default) Enable the extension.
   * - false: Disable the extension.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableExtension(
      String provider, String extension, ExtensionInfo extensionInfo, boolean enable);

  /**
   * Sets the properties of an extension.
   *
   * @param provider The name of the extension provider, e.g. agora.io.
   * @param extension The name of the extension, e.g. agora.beauty.
   * @param extensionInfo The information for extension. See {@link ExtensionInfo}.
   * @param key The key of the extension.
   * @param value The JSON formatted value of the extension key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExtensionProperty(
      String provider, String extension, ExtensionInfo extensionInfo, String key, String value);

  /**
   * Gets the properties of an extension.
   *
   * @param provider The name of the extension provider, e.g. agora.io.
   * @param extension The name of the extension, e.g. agora.beauty.
   * @param extensionInfo The information for extension. See {@link ExtensionInfo}.
   * @param key The key of the extension.
   *
   * @return JSON formatted string of property's value; return null if failed
   */
  public abstract String getExtensionProperty(
      String provider, String extension, ExtensionInfo extensionInfo, String key);

  /**
   * @brief Starts screen capture.
   *
   * @since v3.7.0
   *
   * @details
   * Applicable scenarios: In the screen sharing scenario, you need to call this method to start
   * capturing the screen video stream.
   * Call timing: You can call this method either before or after joining the channel, with the
   * following differences:
   * - Call this method first and then call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join channel and set
   * `publishScreenCaptureVideo` to `true` to start screen sharing.
   * - Call this method after joining a channel, then call `updateChannelMediaOptions` and set
   * `publishScreenCaptureVideo` to `true` to start screen sharing.
   *
   * @note
   * - On the Android platform, if the user has not granted the app screen capture permission, the SDK
   * reports the `onPermissionError` `(2)` callback.
   * - On Android 9 and later, to avoid the application being killed by the system after going to the
   * background, Agora recommends you add the foreground service
   * `android.permission.FOREGROUND_SERVICE` to the `/app/Manifests/AndroidManifest.xml` file.
   * - Due to performance limitations, screen sharing is not supported on Android TV.
   * - Due to system limitations, if you are using Huawei phones, do not adjust the video encoding
   * resolution of the screen sharing stream during the screen sharing, or you could experience
   * crashes.
   * - Due to system limitations, some Xiaomi devices do not support capturing system audio during
   * screen sharing.
   * - To avoid system audio capture failure when sharing screen, Agora recommends that you set the
   * audio application scenario to `AUDIO_SCENARIO_GAME_STREAMING` by using the `setAudioScenario`
   * method before joining the channel.
   * - The billing for the screen sharing stream is based on the `dimensions` in
   * `VideoCaptureParameters`:
   *   - When you do not pass in a value, Agora bills you at 1280 × 720.
   *   - When you pass in a value, Agora bills you at that value.
   *
   * @param screenCaptureParameters The screen sharing encoding parameters. See
   * `ScreenCaptureParameters`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2 (Android platform): The system version is too low. Ensure that the Android API level is
   * not lower than 21.
   *   - -3 (Android platform): Unable to capture system audio. Ensure that the Android API level is
   * not lower than 29.
   */
  public abstract int startScreenCapture(ScreenCaptureParameters screenCaptureParameters);

  /**
   * @brief Configures `MediaProjection` outside of the SDK to capture screen video streams.
   *
   * @details
   * After successfully calling this method, the external `MediaProjection` you set will replace the
   * `MediaProjection` requested by the SDK to capture the screen video stream.
   * When the screen sharing is stopped or `RtcEngine` is destroyed, the SDK will automatically
   * release the MediaProjection.
   * Applicable scenarios: If you are able to apply for `MediaProjection`, you can directly use your
   * `MediaProjection` instead of the one applied for by the SDK. The following lists two applicable
   * scenarios:``
   * - On custom system devices, it can avoid system pop-ups (such as requiring user permission to
   * capture the screen) and directly start capturing the screen video stream.
   * - In a screen sharing process that involves one or more sub-processes, it can help avoid errors
   * that might occur when creating objects within these sub-processes, which could otherwise lead to
   * failures in screen capturing.
   * Call timing: Call this method after `startScreenCapture`.
   *
   * @note Before calling this method, you must first apply for `MediaProjection` permission.
   *
   * @param mediaProjection An `MediaProjection` object used to capture screen video streams.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setExternalMediaProjection(MediaProjection mediaProjection);

  /**
   * @brief Sets the screen sharing scenario.
   *
   * @since v4.2.0
   *
   * @details
   * When you start screen sharing or window sharing, you can call this method to set the screen
   * sharing scenario. The SDK adjusts the video quality and experience of the sharing according to
   * the scenario.
   *
   * @note Agora recommends that you call this method before joining a channel.
   *
   * @param screenScenario The screen sharing scenario. See `ScreenScenarioType`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setScreenCaptureScenario(Constants.ScreenScenarioType screenScenario);

  /**
   * @brief Stops screen capture.
   *
   * @since v3.7.0
   *
   * @details
   * Applicable scenarios: If you start screen capture by calling `startScreenCapture`, you need to
   * call this method to stop screen capture.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopScreenCapture();

  /**
   * @brief Sets video application scenarios.
   *
   * @details
   * After successfully calling this method, the SDK will automatically enable the best practice
   * strategies and adjust key performance metrics based on the specified scenario, to optimize the
   * video experience.
   *
   * @note Call this method before joining a channel.
   *
   * @param scenarioType The type of video application scenario. See
   * `VideoScenario`.`APPLICATION_SCENARIO_MEETING` (1) is suitable for meeting scenarios. The SDK
   * automatically enables the following strategies:
   * - In meeting scenarios where low-quality video streams are required to have a high bitrate, the
   * SDK automatically enables multiple technologies used to deal with network congestions, to enhance
   * the performance of the low-quality streams and to ensure the smooth reception by subscribers.
   * - The SDK monitors the number of subscribers to the high-quality video stream in real time and
   * dynamically adjusts its configuration based on the number of subscribers.
   *   - If nobody subscribers to the high-quality stream, the SDK automatically reduces its bitrate
   * and frame rate to save upstream bandwidth.
   *   - If someone subscribes to the high-quality stream, the SDK resets the high-quality stream to
   * the `VideoEncoderConfiguration` configuration used in the most recent calling of
   * `setVideoEncoderConfiguration`. If no configuration has been set by the user previously, the
   * following values are used:
   *     - Resolution: 960 × 540
   *     - Frame rate: 15 fps
   *     - Bitrate: 1000 Kbps
   * - The SDK monitors the number of subscribers to the low-quality video stream in real time and
   * dynamically enables or disables it based on the number of subscribers.Note: If the user has
   * called `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` to set that never send low-quality video stream (
   * `DISABLE_SIMULCAST_STREAM` ), the dynamic adjustment of the low-quality stream in meeting
   * scenarios will not take effect.
   *   - If nobody subscribes to the low-quality stream, the SDK automatically disables it to save
   * upstream bandwidth.
   *   - If someone subscribes to the low-quality stream, the SDK enables the low-quality stream and
   * resets it to the `SimulcastStreamConfig` configuration used in the most recent calling of
   * `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)`. If no configuration has been set by the user previously, the following
   * values are used:
   *     - Resolution: 480 × 272
   *     - Frame rate: 15 fps
   *     - Bitrate: 500 Kbps
   * `APPLICATION_SCENARIO_1V1` (2) This is applicable to the `one to one live` scenario. To meet the
   * requirements for low latency and high-quality video in this scenario, the SDK optimizes its
   * strategies, improving performance in terms of video quality, first frame rendering, latency on
   * mid-to-low-end devices, and smoothness under weak network conditions.Attention: This enumeration
   * value is only applicable to the broadcaster vs. broadcaster scenario.
   * `APPLICATION_SCENARIO_LIVESHOW` (3) This is applicable to the `show room` scenario. In this
   * scenario, fast video rendering and high image quality are crucial. The SDK implements several
   * performance optimizations, including automatically enabling accelerated audio and video frame
   * rendering to minimize first-frame latency (no need to call `enableInstantMediaRendering` ), and
   * B-frame encoding to achieve better image quality and bandwidth efficiency. The SDK also provides
   * enhanced video quality and smooth playback, even in poor network conditions or on lower-end
   * devices.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -1: A general error occurs (no specified reason).
   *   - -4: Video application scenarios are not supported. Possible reasons include that you use the
   * Voice SDK instead of the Video SDK.
   *   - -7: The `RtcEngine` object has not been initialized. You need to initialize the `RtcEngine`
   * object before calling this method.
   */
  public abstract int setVideoScenario(Constants.VideoScenario scenarioType);

  /**
   * Sets the video qoe preference.
   *
   * @since v4.2.1
   * You can call this method to set the expected QoE Preference.
   * The SDK will optimize the video experience for each preference you set.
   *
   * @param qoePreference he qoe preference type.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setVideoQoEPreference(Constants.QoEPreference qoePreference);

  /**
   * @brief Updates the screen capturing parameters.
   *
   * @since v3.7.0
   *
   * @note Call this method after starting screen sharing or window sharing.
   *
   * @param screenCaptureParameters The screen sharing encoding parameters. See
   * `ScreenCaptureParameters`.Attention: The video properties of the screen sharing stream only need
   * to be set through this parameter, and are unrelated to `setVideoEncoderConfiguration`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid.
   *   - -8: The screen sharing state is invalid. Probably because you have shared other screens or
   * windows. Try calling `stopScreenCapture` to stop the current sharing and start sharing the screen
   * again.
   */
  public abstract int updateScreenCaptureParameters(
      ScreenCaptureParameters screenCaptureParameters);

  /**
   * @brief Registers a raw video frame observer object.
   *
   * @details
   * If you want to observe raw video frames (such as YUV or RGBA format), Agora recommends that you
   * implement one `IVideoFrameObserver` class with this method.
   * When calling this method to register a video observer, you can register callbacks in the
   * `IVideoFrameObserver` class as needed. After you successfully register the video frame observer,
   * the SDK triggers the registered callbacks each time a video frame is received.
   * Applicable scenarios: After registering the raw video observer, you can use the obtained raw
   * video data in various video pre-processing scenarios, such as virtual backgrounds and image
   * enhacement by yourself.
   * Agora provides an open source sample project `beautyapi` on GitHub for your reference.
   * Call timing: Call this method before joining a channel.
   *
   * @note
   * When handling the video data returned in the callbacks, pay attention to the changes in the
   * `width` and `height` parameters, which may be adapted under the following circumstances:
   * - When network conditions deteriorate, the video resolution decreases incrementally.
   * - If the user adjusts the video profile, the resolution of the video returned in the callbacks
   * also changes.
   *
   * @param observer The observer instance. See `IVideoFrameObserver`. To release the instance, set
   * the value as NULL.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int registerVideoFrameObserver(IVideoFrameObserver observer);

  /**
   * @brief Creates a media player object.
   *
   * @details
   * Before calling any APIs in the `IMediaPlayer` class, you need to call this method to create an
   * instance of the media player. If you need to create multiple instances, you can call this method
   * multiple times.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @return
   * - An `IMediaPlayer` object, if the method call succeeds.
   * - An empty pointer, if the method call fails.
   */
  public abstract IMediaPlayer createMediaPlayer();

  /**
   * @brief Creates a recorder for audio and video.
   *
   * @details
   * Before starting to record audio and video streams, you need to call this method to create a recorder. The SDK supports recording multiple audio and video streams from local or remote users. You can call this method multiple times to create multiple recorders, and specify the channel name and the user ID of the stream to be recorded through the `info` parameter.
   * 
   * After successfully creating the recorder, you need to call `setMediaRecorderObserver` to register an observer for the recorder to listen for recording-related callbacks, and then call `startRecording` to start recording.
   *
   * @param info Information about the audio and video stream to be recorded. See `RecorderStreamInfo`.
   *
   * @return
   * - If the method call succeeds: Returns an `AgoraMediaRecorder` instance.
   * - If the method call fails: Returns a null pointer.
   */
  public abstract AgoraMediaRecorder createMediaRecorder(RecorderStreamInfo info);

  /**
   * @brief Destroys the audio and video recording object.
   *
   * @details
   * When you no longer need to record audio and video streams, you can call this method to destroy the corresponding audio and video recording object. If you are currently recording, call `stopRecording` to stop the recording before calling this method to destroy the audio and video recording object.
   *
   * @param mediaRecorder The `AgoraMediaRecorder` object to be destroyed.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract void destroyMediaRecorder(AgoraMediaRecorder mediaRecorder);

  /**
   * @brief Gets one `IMediaPlayerCacheManager` instance.
   *
   * @details
   * Before calling any APIs in the `IMediaPlayerCacheManager` class, you need to call this method to
   * get a cache manager instance of a media player.
   * Call timing: Make sure the `RtcEngine` is initialized before you call this method.
   *
   * @note The cache manager is a singleton pattern. Therefore, multiple calls to this method returns
   * the same instance.
   *
   * @return
   * The `IMediaPlayerCacheManager` instance.
   */
  public abstract IMediaPlayerCacheManager getMediaPlayerCacheManager();

  /**
   * get an H265Transcoder instance, which is used to
   * @return
   * {@link IH265Transcoder}
   */
  public abstract IH265Transcoder getH265Transcoder();

  /**
   * Enable or disable the external audio source local playback.
   *
   * * @param enabled Determines whether to enable the external audio source local playback:
   * - true: Enable the external audio source local playback.
   * - false: (default) Disable the external audio source local playback.
   * @return
   * -  0: Success.
   * - <0: Failure.
   */
  public abstract int enableExternalAudioSourceLocalPlayback(boolean enabled);

  /**
   * @brief Adjusts the volume of the custom audio track played remotely.
   *
   * @details
   * If you want to change the volume of the audio played remotely, you need to call this method
   * again.
   *
   * @note Ensure you have called the `createCustomAudioTrack` method to create a custom audio track
   * before calling this method.
   *
   * @param trackId The audio track ID. Set this parameter to the custom audio track ID returned in
   * `createCustomAudioTrack`.
   * @param volume The volume of the audio source. The value can range from 0 to 100. 0 means mute;
   * 100 means the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustCustomAudioPublishVolume(int trackId, int volume);

  /**
   * @brief Adjusts the volume of the custom audio track played locally.
   *
   * @details
   * If you want to change the volume of the audio to be played locally, you need to call this method
   * again.
   *
   * @note Ensure you have called the `createCustomAudioTrack` method to create a custom audio track
   * before calling this method.
   *
   * @param trackId The audio track ID. Set this parameter to the custom audio track ID returned in
   * `createCustomAudioTrack`.
   * @param volume The volume of the audio source. The value can range from 0 to 100. 0 means mute;
   * 100 means the original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int adjustCustomAudioPlayoutVolume(int trackId, int volume);

  /**
   * @brief Enables the virtual metronome.
   *
   * @details
   * - After enabling the virtual metronome, the SDK plays the specified audio effect file from the
   * beginning, and controls the playback duration of each file according to `beatsPerMinute` you set
   * in `AgoraRhythmPlayerConfig`. For example, if you set `beatsPerMinute` as `60`, the SDK plays one
   * beat every second. If the file duration exceeds the beat duration, the SDK only plays the audio
   * within the beat duration.
   * - By default, the sound of the virtual metronome is published in the channel. If you want the
   * sound to be heard by the remote users, you can set `publishRhythmPlayerTrack` in
   * `ChannelMediaOptions` as `true`.
   * Applicable scenarios: In music education, physical education and other scenarios, teachers
   * usually need to use a metronome so that students can practice with the correct beat. The meter is
   * composed of a downbeat and upbeats. The first beat of each measure is called a downbeat, and the
   * rest are called upbeats.
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After successfully calling this method, the SDK triggers the
   * `onRhythmPlayerStateChanged` callback locally to report the status of the virtual metronome.
   *
   * @param sound1 The absolute path or URL address (including the filename extensions) of the file
   * for the downbeat. For example,
   * `content://com.android.providers.media.documents/document/audio%203A14441`. For the audio file
   * formats supported by this method, see [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * @param sound2 The absolute path or URL address (including the filename extensions) of the file
   * for the upbeats. For example,
   * `content://com.android.providers.media.documents/document/audio%203A14441`. For the audio file
   * formats supported by this method, see [What audio file formats does the Agora RTC SDK support](https://docs.agora.io/en/help/general-product-inquiry/audio_format).
   * @param config The metronome configuration. See `AgoraRhythmPlayerConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -22: Cannot find audio effect files. Please set the correct paths for `sound1` and `sound2`.
   */
  public abstract int startRhythmPlayer(
      String sound1, String sound2, AgoraRhythmPlayerConfig config);

  /**
   * @brief Disables the virtual metronome.
   *
   * @details
   * After calling `startRhythmPlayer`, you can call this method to disable the virtual metronome.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int stopRhythmPlayer();

  /**
   * @brief Configures the virtual metronome.
   *
   * @details
   * - After calling `startRhythmPlayer`, you can call this method to reconfigure the virtual
   * metronome.
   * - After enabling the virtual metronome, the SDK plays the specified audio effect file from the
   * beginning, and controls the playback duration of each file according to `beatsPerMinute` you set
   * in `AgoraRhythmPlayerConfig`. For example, if you set `beatsPerMinute` as `60`, the SDK plays one
   * beat every second. If the file duration exceeds the beat duration, the SDK only plays the audio
   * within the beat duration.
   * - By default, the sound of the virtual metronome is published in the channel. If you want the
   * sound to be heard by the remote users, you can set `publishRhythmPlayerTrack` in
   * `ChannelMediaOptions` as `true`.
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After successfully calling this method, the SDK triggers the
   * `onRhythmPlayerStateChanged` callback locally to report the status of the virtual metronome.
   *
   * @param config The metronome configuration. See `AgoraRhythmPlayerConfig`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int configRhythmPlayer(AgoraRhythmPlayerConfig config);

  /**
   * @brief Sets the audio profile of the audio streams directly pushed to the CDN by the host.
   *
   * @deprecated v4.6.0.
   *
   * @details
   * When you set the `publishMicrophoneTrack` or `publishCustomAudioTrack` in the
   * `DirectCdnStreamingMediaOptions` as `true` to capture audios, you can call this method to set the
   * audio profile.
   *
   * @param profile The audio profile, including the sampling rate, bitrate, encoding mode, and the
   * number of channels.
   * - `DEFAULT`(0): The default value.
   *   - For the interactive streaming profile: A sample rate of 48 kHz, music encoding, mono, and a
   * bitrate of up to 64 Kbps.
   *   - For the communication profile: A sample rate of 32 kHz, audio encoding, mono, and a bitrate
   * of up to 18 Kbps.
   * - `SPEECH_STANDARD`(1): A sampling rate of 32 kHz, audio encoding, mono, and a bitrate of up to
   * 18 Kbps.
   * - `MUSIC_STANDARD`(2): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up to 64
   * Kbps.
   * - `MUSIC_STANDARD_STEREO`(3): A sampling rate of 48 kHz, music encoding, stereo, and a bitrate of
   * up to 80 Kbps.
   * - `MUSIC_HIGH_QUALITY`(4): A sampling rate of 48 kHz, music encoding, mono, and a bitrate of up
   * to 96 Kbps.
   * - `MUSIC_HIGH_QUALITY_STEREO`(5): A sampling rate of 48 kHz, music encoding, stereo, and a
   * bitrate of up to 128 Kbps.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int setDirectCdnStreamingAudioConfiguration(int profile);
  /**
   * @brief Sets the video profile of the media streams directly pushed to the CDN by the host.
   *
   * @deprecated v4.6.0.
   *
   * @details
   * This method only affects video streams captured by cameras or screens, or from custom video
   * capture sources. That is, when you set `publishCameraTrack` or `publishCustomVideoTrack` in
   * `DirectCdnStreamingMediaOptions` as `true` to capture videos, you can call this method to set the
   * video profiles.
   * If your local camera does not support the video resolution you set,the SDK automatically adjusts
   * the video resolution to a value that is closest to your settings for capture, encoding or
   * streaming, with the same aspect ratio as the resolution you set. You can get the actual
   * resolution of the video streams through the `onDirectCdnStreamingStats` callback.
   *
   * @param config Video profile. See `VideoEncoderConfiguration`.Note: During CDN live streaming,
   * Agora only supports setting `ORIENTATION_MODE` as `ORIENTATION_MODE_FIXED_LANDSCAPE` or
   * `ORIENTATION_MODE_FIXED_PORTRAIT`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int setDirectCdnStreamingVideoConfiguration(VideoEncoderConfiguration config);

  /**
   * @brief Gets the current Monotonic Time of the SDK.
   *
   * @details
   * Monotonic Time refers to a monotonically increasing time series whose value increases over time.
   * The unit is milliseconds.
   * In custom video capture and custom audio capture scenarios, in order to ensure audio and video
   * synchronization, Agora recommends that you call this method to obtain the current Monotonic Time
   * of the SDK, and then pass this value into the timestamp parameter in the captured video frame (
   * `VideoFrame` ) and audio frame ( `AudioFrame` ).
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @return
   * - ≥ 0: The method call is successful, and returns the current Monotonic Time of the SDK (in
   * milliseconds).
   * - < 0: Failure.
   */
  public abstract long getCurrentMonotonicTimeInMs();

  /**
   * @brief Starts pushing media streams to the CDN directly.
   *
   * @deprecated v4.6.0.
   *
   * @details
   * Aogra does not support pushing media streams to one URL repeatedly.
   * **Media options**
   * Agora does not support setting the value of `publishCameraTrack` and `publishCustomVideoTrack` as
   * `true`, or the value of `publishMicrophoneTrack` and `publishCustomAudioTrack` as `true` at the
   * same time. When choosing media setting options ( `DirectCdnStreamingMediaOptions` ), you can
   * refer to the following examples:
   * If you want to push audio and video streams captured by the host from a custom source, the media
   * setting options should be set as follows:
   * - `publishCustomAudioTrack` is set as `true` and call the `pushExternalAudioFrame` method
   * - `publishCustomVideoTrack` is set as `true` and call the `pushExternalVideoFrameById(AgoraVideoFrame frame, int videoTrackId)`
   * method
   * - `publishCameraTrack` is set as `false` (the default value)
   * - `publishMicrophoneTrack` is set as `false` (the default value)
   * As of v4.2.0, Agora SDK supports audio-only live streaming. You can set `publishCustomAudioTrack`
   * or `publishMicrophoneTrack` in `DirectCdnStreamingMediaOptions` as `true` and call
   * `pushExternalAudioFrame` to push audio streams.
   *
   * @note Agora only supports pushing one audio and video streams or one audio streams to CDN.
   *
   * @param eventHandler See `onDirectCdnStreamingStateChanged` and `onDirectCdnStreamingStats`.
   * @param publishUrl The CDN live streaming URL.
   * @param options The media setting options for the host. See `DirectCdnStreamingMediaOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int startDirectCdnStreaming(IDirectCdnStreamingEventHandler eventHandler,
      String publishUrl, DirectCdnStreamingMediaOptions options);

  /**
   * @brief Stops pushing media streams to the CDN directly.
   *
   * @deprecated v4.6.0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated public abstract int stopDirectCdnStreaming();

  /**
   * Change the media source during the pushing
   *
   * @deprecated v4.6.0.
   *
   * @note
   * This method is temporarily not supported
   *
   * @param options The direct cdn streaming media options: DirectCdnStreamingMediaOptions.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated
  public abstract int updateDirectCdnStreamingMediaOptions(DirectCdnStreamingMediaOptions options);

  /**
   * @brief Creates a custom video track.
   *
   * @details
   * To publish a custom video source, see the following steps:1. Call this method to create a video
   * track and get the video track ID.
   * 2. Call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel. In `ChannelMediaOptions`, set
   * `customVideoTrackId` to the video track ID that you want to publish, and set
   * `publishCustomVideoTrack` to `true`.
   * 3. Call `pushExternalVideoFrameById(VideoFrame frame, int videoTrackId)` and specify `videoTrackId` as the video track ID set
   * in step 2. You can then publish the corresponding custom video source in the channel.
   *
   * @return
   * - If the method call is successful, the video track ID is returned as the unique identifier of
   * the video track.
   * - If the method call fails, 0xffffffff is returned.
   */
  public abstract int createCustomVideoTrack();

  /**
   * Get an custom encoded video track id created by internal,which could used to publish or preview
   *
   * @return
   * - > 0: the useable video track id.
   * - < 0: Failure.
   */
  public abstract int createCustomEncodedVideoTrack(EncodedVideoTrackOptions encodedOpt);

  /**
   * @brief Destroys the specified video track.
   *
   * @param video_track_id The video track ID returned by calling the `createCustomVideoTrack` method.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int destroyCustomVideoTrack(int video_track_id);

  /**
   * destroy a created custom encoded video track id
   *
   * @param video_track_id The video track id which was created by createCustomEncodedVideoTrack
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int destroyCustomEncodedVideoTrack(int video_track_id);

  /**
   * @brief Sets up cloud proxy service.
   *
   * @details
   * When users' network access is restricted by a firewall, configure the firewall to allow specific
   * IP addresses and ports provided by Agora; then, call this method to enable the cloud `proxyType`
   * and set the cloud proxy type with the proxyType parameter.
   * After successfully connecting to the cloud proxy, the SDK triggers the `onConnectionStateChanged`
   * ( CONNECTION_STATE_CONNECTING, CONNECTION_CHANGED_SETTING_PROXY_SERVER ) callback.
   * To disable the cloud proxy that has been set, call the
   * `setCloudProxy(TRANSPORT_TYPE_NONE_PROXY)`.
   * To change the cloud proxy type that has been set, call the `setCloudProxy`
   * `(TRANSPORT_TYPE_NONE_PROXY) ` first, and then call the `setCloudProxy` to set the `proxyType`
   * you want.
   *
   * @note
   * - Agora recommends that you call this method before joining a channel.
   * - When a user is behind a firewall and uses the Force UDP cloud proxy, the services for Media
   * Push and cohosting across channels are not available.
   * - When you use the Force TCP cloud proxy, note that an error would occur when calling the
   * `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` method to play online music files in the HTTP protocol. The services for
   * Media Push and cohosting across channels use the cloud proxy with the TCP protocol.
   *
   * @param proxyType The type of the cloud proxy.
   * - TRANSPORT_TYPE_NONE_PROXY (0): The automatic mode. The SDK has this mode enabled by default. In
   * this mode, the SDK attempts a direct connection to SD-RTN™ and automatically switches to TCP/TLS
   * 443 if the attempt fails.
   * - TRANSPORT_TYPE_UDP_PROXY (1): The cloud proxy for the UDP protocol, that is, the Force UDP
   * cloud proxy mode. In this mode, the SDK always transmits data over UDP.
   * - TRANSPORT_TYPE_TCP_PROXY (2): The cloud proxy for the TCP (encryption) protocol, that is, the
   * Force TCP cloud proxy mode. In this mode, the SDK always transmits data over TCP/TLS 443.
   * This parameter is mandatory. The SDK reports an error if you do not pass in a value.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -2: The parameter is invalid.
   *   - -7: The SDK is not initialized.
   */
  public abstract int setCloudProxy(int proxyType);

  /**
   * @brief Configures the connection to the Agora private media server access module.
   *
   * @details
   * After successfully deploying the Agora private media server and integrating the 4.x RTC SDK into your internal network client, you can call this method to specify the Local Access Point and assign the access module to the SDK.  
   * Call timing: You must call this method before joining a channel.
   *
   * @note This method takes effect only after deploying the Agora hybrid cloud solution. You can `contact sales` to learn more about and deploy the Agora hybrid cloud.
   *
   * @param config Local Access Point configuration. See `LocalAccessPointConfiguration` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int setLocalAccessPoint(LocalAccessPointConfiguration config);

  /**
   * @brief Sets whether to enable the local playback of external audio source.
   *
   * @details
   * After calling this method to enable the local playback of external audio source, if you need to
   * stop local playback, you can call this method again and set `enabled` to `false`.
   * You can call `adjustCustomAudioPlayoutVolume` to adjust the local playback volume of the custom
   * audio track.
   *
   * @note Ensure you have called the `createCustomAudioTrack` method to create a custom audio track
   * before calling this method.
   *
   * @param trackId The audio track ID. Set this parameter to the custom audio track ID returned in
   * `createCustomAudioTrack`.
   * @param enabled Whether to play the external audio source:
   * - `true`: Play the external audio source.
   * - `false`: (Default) Do not play the external source.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableCustomAudioLocalPlayback(int trackId, boolean enabled);

  /**
   * @brief Sets audio advanced options.
   *
   * @details
   * If you have advanced audio processing requirements, such as capturing and sending stereo audio,
   * you can call this method to set advanced audio options.
   *
   * @note Call this method after calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`, `enableAudio` and `enableLocalAudio`.
   *
   * @param options The advanced options for audio. See `AdvancedAudioOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int setAdvancedAudioOptions(AdvancedAudioOptions options);

  /**
   * @brief Sets audio-video synchronization on the publishing side.
   *
   * @details
   * A single user may use two separate devices to send audio and video streams respectively. To ensure that the audio and video received are synchronized in time, you can call this method on the video publishing device and pass in the channel name and user ID of the audio publishing device. The SDK uses the timestamp of the sent audio stream as the reference and automatically adjusts the video stream accordingly. This ensures time synchronization of the received audio and video even if the two publishing devices have different uplink network conditions (e.g., one using Wi-Fi and the other using 4G).
   *
   * @note It is recommended that you call this method before joining the channel.
   *
   * @param channelId The name of the channel where the audio publishing device is located.
   * @param uid The user ID of the audio publishing device.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting advice.
   */
  public abstract int setAVSyncSource(String channelId, int uid);

  /**
   * @brief Sets whether to replace the current video feeds with images when publishing video streams.
   *
   * @details
   * When publishing video streams, you can call this method to replace the current video feeds with
   * custom images.
   * Once you enable this function, you can select images to replace the video feeds through the
   * `ImageTrackOptions` parameter. If you disable this function, the remote users see the video feeds
   * that you publish.
   * Call timing: Call this method after joining a channel.
   *
   * @param enabled Whether to replace the current video feeds with custom images:
   * - `true`: Replace the current video feeds with custom images.
   * - `false`: (Default) Do not replace the current video feeds with custom images.
   * @param options Image configurations. See `ImageTrackOptions`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public abstract int enableVideoImageSource(boolean enabled, ImageTrackOptions options);

  /**
   * @brief Gets the type of the local network connection.
   *
   * @details
   * You can use this method to get the type of network in use at any stage.
   *
   * @note You can call this method either before or after joining a channel.
   *
   * @return
   * - ≥ 0: The method call is successful, and the local network connection type is returned.
   *   - 0: The SDK disconnects from the network.
   *   - 1: The network type is LAN.
   *   - 2: The network type is Wi-Fi (including hotspots).
   *   - 3: The network type is mobile 2G.
   *   - 4: The network type is mobile 3G.
   *   - 5: The network type is mobile 4G.
   *   - 6: The network type is mobile 5G.
   * - < 0: The method call failed with an error code.
   *   - -1: The network type is unknown.
   */
  public abstract int getNetworkType();

  /**
   * @brief Gets the current NTP (Network Time Protocol) time.
   *
   * @details
   * In the real-time chorus scenario, especially when the downlink connections are inconsistent due
   * to network issues among multiple receiving ends, you can call this method to obtain the current
   * NTP time as the reference time, in order to align the lyrics and music of multiple receiving ends
   * and achieve chorus synchronization.
   *
   * @return
   * The Unix timestamp (ms) of the current NTP time.
   */
  public abstract long getNtpWallTimeInMs();

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
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The method is called before `RtcEngine` is initialized.
   */
  public abstract int startMediaRenderingTracing();

  /**
   * @brief Enables audio and video frame instant rendering.
   *
   * @since v4.1.1
   *
   * @details
   * After successfully calling this method, the SDK enables the instant frame rendering mode, which
   * can speed up the first frame rendering after the user joins the channel.
   * Applicable scenarios: Agora recommends that you enable this mode for the audience in a live
   * streaming scenario.
   * Call timing: Call this method before joining a channel.
   *
   * @note
   * Both the host and audience member need to call this method in order to experience instant
   * rendering of audio and video frames.
   * Once the method is successfully called, you can only cancel instant rendering by calling `destroy()`
   * to destroy the `RtcEngine` object.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   *   - -7: The method is called before `RtcEngine` is initialized.
   */
  public abstract int enableInstantMediaRendering();

  /**
   * 配置AudioAttribute
   *
   * @note
   * - 该方法在加入频道前调用
   * -
   *
   *
   * @param AudioAttributes
   *
   *
   *
   * @return
   * - 0: 方法调用成功
   *- < 0: 方法调用失败
   */
  public abstract int setupAudioAttributes(AudioAttributes attr);

  /**
   * @brief Checks whether the device supports the specified advanced feature.
   *
   * @since v4.3.0
   *
   * @details
   * Checks whether the capabilities of the current device meet the requirements for advanced features
   * such as virtual background and image enhancement.
   * Applicable scenarios: Before using advanced features, you can check whether the current device
   * supports these features based on the call result. This helps to avoid performance degradation or
   * unavailable features when enabling advanced features on low-end devices. Based on the return
   * value of this method, you can decide whether to display or enable the corresponding feature
   * button, or notify the user when the device's capabilities are insufficient.
   *
   * @param type The type of the advanced feature.
   * - FEATURE_VIDEO_VIRTUAL_BACKGROUND (1): Virutual background.
   * - FEATURE_VIDEO_BEAUTY_EFFECT (2): Image enhancement.
   *
   * @return
   * - `true`: The current device supports the specified feature.
   * - `false`: The current device does not support the specified feature.
   */
  public abstract boolean isFeatureAvailableOnDevice(int type);

  /**
   @brief Send audio metadata.
   @since v4.3.1
   @param metadata Audio Metadata.
   @return
   - 0: Success.
   - < 0: Failure.
   @technical preview
  */
  public abstract int sendAudioMetadata(byte[] metadata);
}
