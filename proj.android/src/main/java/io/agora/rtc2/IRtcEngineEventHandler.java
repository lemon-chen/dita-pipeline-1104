package io.agora.rtc2;

import android.graphics.Rect;
import java.util.Arrays;

/**
 * <p>Callback methods.</p>
 *
 * <p>The SDK uses the IRtcEngineEventHandler interface class to send callback event notifications
 to the application, and the application inherits the methods of this interface class to retrieve
 these event notifications. All methods in this interface class have their (empty) default
 implementations, and the application can inherit only some of the required events instead of all of
 them. In the callback methods, the application should avoid time-consuming tasks or call blocking
 APIs (such as SendMessage), otherwise, the SDK may not work properly.</p>
 */
public abstract class IRtcEngineEventHandler {
  // Enables callback event notifications to your application.

  /**
   * @deprecated Use the new {@link Constants} class
   *  with the same constants value
   */
  @Deprecated
  public static class Quality {
    public final static int UNKNOWN = Constants.QUALITY_UNKNOWN;
    public final static int EXCELLENT = Constants.QUALITY_EXCELLENT;
    public final static int GOOD = Constants.QUALITY_GOOD;
    public final static int POOR = Constants.QUALITY_POOR;
    public final static int BAD = Constants.QUALITY_BAD;
    public final static int VBAD = Constants.QUALITY_VBAD;
    public final static int DOWN = Constants.QUALITY_DOWN;
  }

  /**
   * The warning code.
   */
  public static class WarnCode {
    /**
     * 20: The request is pending, usually due to some module not being ready, and the SDK postponed
     * processing the request.
     */
    public final static int WARN_PENDING = Constants.WARN_PENDING;
    /**
     * 16: Fails to initialize the video function, possibly caused by lack of resources. The users
     * cannot see the video while the voice communication is not affected.
     */
    public final static int WARN_INIT_VIDEO = Constants.WARN_INIT_VIDEO;
    /**
     * 8: The specified view is invalid. You need to specify a view when using the video function.
     */
    public final static int WARN_INVALID_VIEW = Constants.WARN_INVALID_VIEW;
    /**
     * 103: No channel resources are available. Maybe because the server cannot allocate any channel
     * resource.
     */
    public final static int WARN_NO_AVAILABLE_CHANNEL = Constants.WARN_NO_AVAILABLE_CHANNEL;
    /**
     * 104: A timeout occurs when looking up the channel. When joining a channel, the SDK looks up
     * the specified channel. The warning usually occurs when the network condition is too poor for
     * the SDK to connect to the server.
     */
    public final static int WARN_LOOKUP_CHANNEL_TIMEOUT = Constants.WARN_LOOKUP_CHANNEL_TIMEOUT;
    /**
     * 105: The server rejects the request to look up the channel. The server cannot process this
     * request or the request is illegal.
     */
    public final static int WARN_LOOKUP_CHANNEL_REJECTED = Constants.WARN_LOOKUP_CHANNEL_REJECTED;
    /**
     * 106: A timeout occurs when opening the channel. Once the specific channel is found, the SDK
     * opens the channel. The warning usually occurs when the network condition is too poor for the
     * SDK to connect to the server.
     */
    public final static int WARN_OPEN_CHANNEL_TIMEOUT = Constants.WARN_OPEN_CHANNEL_TIMEOUT;
    /**
     * 107: The server rejects the request to open the channel. The server cannot process this
     * request or the request is illegal.
     */
    public final static int WARN_OPEN_CHANNEL_REJECTED = Constants.WARN_OPEN_CHANNEL_REJECTED;
    /**
     * 121: The ticket to open the channel is invalid.
     */
    public final static int WARN_OPEN_CHANNEL_INVALID_TICKET =
        Constants.WARN_OPEN_CHANNEL_INVALID_TICKET;
    /**
     * 122: Try another server.
     */
    public final static int WARN_OPEN_CHANNEL_TRY_NEXT_VOS =
        Constants.WARN_OPEN_CHANNEL_TRY_NEXT_VOS;
    /**
     * 701: An error occurs when opening the audio mixing file.
     */
    public final static int WARN_AUDIO_MIXING_OPEN_ERROR = Constants.WARN_AUDIO_MIXING_OPEN_ERROR;
    /**
     * 1014: Audio Device Module: A warning occurs in the playback device.
     */
    public final static int WARN_ADM_RUNTIME_PLAYOUT_WARNING =
        Constants.WARN_ADM_RUNTIME_PLAYOUT_WARNING;
    /**
     * 1016: Audio Device Module: A warning occurs in the recording device.
     */
    public final static int WARN_ADM_RUNTIME_RECORDING_WARNING =
        Constants.WARN_ADM_RUNTIME_RECORDING_WARNING;
    /**
     * 1019: Audio Device Module: No valid audio data is collected.
     */
    public final static int WARN_ADM_RECORD_AUDIO_SILENCE = Constants.WARN_ADM_RECORD_AUDIO_SILENCE;
    /**
     * 1033: Audio Device Module: The recording device is occupied.
     */
    public final static int WARN_ADM_RECORD_IS_OCCUPIED = Constants.WARN_ADM_RECORD_IS_OCCUPIED;
    /**
     * 1051: Audio Device Module: Howling is detected.
     */
    public final static int WARN_APM_HOWLING = Constants.WARN_APM_HOWLING;
  }

  /**
   * The error codes.
   */
  public static class ErrorCode {
    /**
     * 0: No error occurs.
     */
    public final static int ERR_OK = Constants.ERR_OK;
    /**
     * 1: A general error occurs (no specified reason).
     */
    public final static int ERR_FAILED = Constants.ERR_FAILED;
    /**
     * 2: An invalid parameter is used. For example, the specific channel name includes illegal
     * characters.
     */
    public final static int ERR_INVALID_ARGUMENT = Constants.ERR_INVALID_ARGUMENT;
    /**
     * 3: The SDK module is not ready. We recommend the following methods to solve this error:
     * - Check the audio device.
     * - Check the completeness of the app.
     * - Re-initialize the SDK.
     */
    public final static int ERR_NOT_READY = Constants.ERR_NOT_READY;
    /**
     * 4: The SDK does not support this function.
     */
    public final static int ERR_NOT_SUPPORTED = Constants.ERR_NOT_SUPPORTED;
    /**
     * 5: The request is rejected. This is for internal SDK internal use only, and is not return
     * to the app through any API or callback event.
     */
    public final static int ERR_REFUSED = Constants.ERR_REFUSED;
    /**
     * 6: The buffer size is not big enough to store the returned data.
     */
    public final static int ERR_BUFFER_TOO_SMALL = Constants.ERR_BUFFER_TOO_SMALL;
    /**
     * 7: The SDK is not initialized before calling this API.
     */
    public final static int ERR_NOT_INITIALIZED = Constants.ERR_NOT_INITIALIZED;
    /**
     * 9: No permission. Check if the user has granted access to the audio or video device.
     */
    public final static int ERR_NO_PERMISSION = Constants.ERR_NO_PERMISSION;
    /**
     * 10: An API timeout. Some APIs require the SDK to return the execution result, and this error
     * occurs if the request takes too long for the SDK to process.
     */
    public final static int ERR_TIMEDOUT = Constants.ERR_TIMEDOUT;
    /**
     * 11: The request is cancelled. This is for internal SDK internal use only, and is not return
     * to the application through any API or callback event.
     */
    public final static int ERR_CANCELED = Constants.ERR_CANCELED;
    /**
     * 12: The call frequency is too high. This is for internal SDK internal use only, and is not
     * return to the application through any API or callback event.
     */
    public final static int ERR_TOO_OFTEN = Constants.ERR_TOO_OFTEN;
    /**
     * 13: The SDK fails to bind to the network socket. This is for internal SDK internal use only,
     * and is not returned to the app through any method or callback.
     */
    public final static int ERR_BIND_SOCKET = Constants.ERR_BIND_SOCKET;
    /**
     * 14: The network is unavailable. This is for internal SDK internal use only, and is not
     * return to the application through any API or callback event.
     */
    public final static int ERR_NET_DOWN = Constants.ERR_NET_DOWN;
    /**
     * 17: The request to join the channel is rejected. This error usually occurs when:
     * - The user is already in the channel, and still calls the API to join the channel
     * - The user tries to join the channel during echo test. Wait until the echo test is finished.
     */
    public final static int ERR_JOIN_CHANNEL_REJECTED = Constants.ERR_JOIN_CHANNEL_REJECTED;
    /**
     * 18: The request to leave the channel is rejected. This error usually occurs:
     * - When the user left the channel and still calls the API to leave the channel. This error
     * stops once the user stops calling the method.
     * - When the user calls `leaveChannel` before joining the channel. No extra operation is
     * needed.
     */
    public final static int ERR_LEAVE_CHANNEL_REJECTED = Constants.ERR_LEAVE_CHANNEL_REJECTED;
    /**
     * 19: Resources are occupied, and cannot be reused.
     */
    public final static int ERR_ALREADY_IN_USE = Constants.ERR_ALREADY_IN_USE;
    /**
     * 20: The SDK gives up the request due to too many requests. This is for
     * internal use only, and does not return to the app through any method or callback.
     */
    public final static int ERR_ABORTED = Constants.ERR_ABORTED;
    /**
     * 21: On Windows, specific firewall settings can cause the SDK to fail to
     * initialize and crash.
     */
    public final static int ERR_INIT_NET_ENGINE = Constants.ERR_INIT_NET_ENGINE;
    /**
     * 22: The app uses too much of the system resource and the SDK
     * fails to allocate any resource.
     */
    public final static int ERR_RESOURCE_LIMITED = Constants.ERR_RESOURCE_LIMITED;
    /**
     * 101: The specified App ID is invalid. Please try to rejoin the channel with a valid App ID.
     */
    public final static int ERR_INVALID_APP_ID = Constants.ERR_INVALID_APP_ID;
    /**
     * 102: The specified channel name is invalid. Please try to rejoin the channel with a valid
     * channel name.
     */
    public final static int ERR_INVALID_CHANNEL_NAME = Constants.ERR_INVALID_CHANNEL_NAME;
    /**
     * 103: Fails to get server resources in the specified region.
     */
    public final static int ERR_NO_SERVER_RESOURCES = Constants.ERR_NO_SERVER_RESOURCES;
    /**
     * 109: The token expired due to one of the following reasons:
     * - Authorized Timestamp expired: The timestamp is represented by the number of seconds elapsed
     * since 1/1/1970. The user can use the Token to access the Agora service within 24 hours after
     * the Token is generated. If the user does not access the Agora service within 24 hours, this
     * Token will no longer be valid.
     * - Call Expiration Timestamp expired: The timestamp is the exact time when a user can no
     * longer use the Agora service (for example, when a user is forced to leave an ongoing call).
     * When a value is set for the Call Expiration Timestamp, it does not mean that the Dynamic Key
     * will expire, but that the user will be banned from the channel.
     */
    public final static int ERR_TOKEN_EXPIRED = Constants.ERR_TOKEN_EXPIRED;
    /**
     * 110: The token is invalid due to one of the following reasons:
     * - The App Certificate for the project is enabled in Console, but the user is still using the
     * App ID.
     * - Once the App Certificate is enabled, the user must use a token. The uid is mandatory, and
     * users must set the same uid as the one set in the `joinChannel` method.
     */
    public final static int ERR_INVALID_TOKEN = Constants.ERR_INVALID_TOKEN;
    /**
     * 111: The CONNECTION_INTERRUPTED callback. This applies to the Agora Web SDK only.
     */
    public final static int ERR_CONNECTION_INTERRUPTED = Constants.ERR_CONNECTION_INTERRUPTED;
    /**
     * 112: The CONNECTION_LOST callback. This applies to the Agora Web SDK only.
     */
    public final static int ERR_CONNECTION_LOST = Constants.ERR_CONNECTION_LOST;
    /**
     * 113: The user is not in the channel.
     */
    public final static int ERR_NOT_IN_CHANNEL = Constants.ERR_NOT_IN_CHANNEL;
    /**
     * 114: The data size is too big.
     */
    public final static int ERR_SIZE_TOO_LARGE = Constants.ERR_SIZE_TOO_LARGE;
    /**
     * 115: The bitrate is limited.
     */
    public final static int ERR_BITRATE_LIMIT = Constants.ERR_BITRATE_LIMIT;
    /**
     * 116: Too many data streams.
     */
    public final static int ERR_TOO_MANY_DATA_STREAMS = Constants.ERR_TOO_MANY_DATA_STREAMS;
    /**
     * 117: A timeout occurs for the data stream transmission.
     */
    public final static int ERR_STREAM_MESSAGE_TIMEOUT = Constants.ERR_STREAM_MESSAGE_TIMEOUT;
    /**
     * 119: Switching the user role fails. Please try to rejoin the channel.
     */
    public final static int ERR_SET_CLIENT_ROLE_NOT_AUTHORIZED =
        Constants.ERR_SET_CLIENT_ROLE_NOT_AUTHORIZED;
    /**
     * 120: Media stream decryption fails. The user may have used a different encryption password to
     * join the channel. Please check your settings or try rejoining the channel.
     */
    public final static int ERR_DECRYPTION_FAILED = Constants.ERR_DECRYPTION_FAILED;
    /**
     * 121: The user ID is invalid.
     */
    public final static int ERR_INVALID_USER_ID = Constants.ERR_INVALID_USER_ID;
    /**
     * 122: Data stream decryption fails. The user may have used a different encryption password to
     * join the channel or did't enable datastream encryption. Please check your settings or try
     * rejoining the channel.
     */
    public final static int ERR_DATASTREAM_DECRYPTION_FAILED =
        Constants.ERR_DATASTREAM_DECRYPTION_FAILED;
    /**
     * 123: The client is banned by the server.
     */
    public final static int ERR_CLIENT_IS_BANNED_BY_SERVER =
        Constants.ERR_CLIENT_IS_BANNED_BY_SERVER;
    /**
     * 130: Encryption is enabled when the user calls the {@link
     * io.agora.rtc2.RtcEngine#addPublishStreamUrl(String, boolean) addPublishStreamUrl} method (CDN
     * live streaming does not support encrypted streams).
     */
    public final static int ERR_ENCRYPTED_STREAM_NOT_ALLOWED_PUBLISH =
        Constants.ERR_ENCRYPTED_STREAM_NOT_ALLOWED_PUBLISH;
    /**
     * 131: License credential is invalid
     */
    public final static int ERR_LICENSE_CREDENTIAL_INVALID =
        Constants.ERR_LICENSE_CREDENTIAL_INVALID;
    /**
     * 134: The user account is invalid, usually because the data format of the user account is
     * incorrect.
     */
    public final static int ERR_INVALID_USER_ACCOUNT = Constants.ERR_INVALID_USER_ACCOUNT;
    /**
     * 157: The extension library is not integrated, such as the library for enabling deep-learning
     * noise reduction.
     */
    public final static int ERR_MODULE_NOT_FOUND = Constants.ERR_MODULE_NOT_FOUND;
    /**
     * 1001: Fails to load the media engine.
     */
    public final static int ERR_LOAD_MEDIA_ENGINE = Constants.ERR_LOAD_MEDIA_ENGINE;
    /**
     * 1005: General error on the audio device module (no specified reason).
     */
    public final static int ERR_ADM_GENERAL_ERROR = Constants.ERR_ADM_GENERAL_ERROR;
    /**
     * 1008: Audio Device Module: An error occurs in initializing the playback device.
     */
    public final static int ERR_ADM_INIT_PLAYOUT = Constants.ERR_ADM_INIT_PLAYOUT;
    /**
     * 1009: Audio Device Module: An error occurs when starting the playback device.
     */
    public final static int ERR_ADM_START_PLAYOUT = Constants.ERR_ADM_START_PLAYOUT;
    /**
     * 1010: Audio Device Module: An error occurs when stopping the playback device.
     */
    public final static int ERR_ADM_STOP_PLAYOUT = Constants.ERR_ADM_STOP_PLAYOUT;
    /**
     * 1011: Audio Device Module: An error occurs when initializing the recording device.
     */
    public final static int ERR_ADM_INIT_RECORDING = Constants.ERR_ADM_INIT_RECORDING;
    /**
     * 1012: Audio Device Module: An error occurs when starting the recording device.
     */
    public final static int ERR_ADM_START_RECORDING = Constants.ERR_ADM_START_RECORDING;
    /**
     * 1013: Audio Device Module: An error occurs when stopping the recording device.
     */
    public final static int ERR_ADM_STOP_RECORDING = Constants.ERR_ADM_STOP_RECORDING;
    /**
     * 1501: Video Device Module: The camera is not authorized.
     */
    public final static int ERR_VDM_CAMERA_NOT_AUTHORIZED = Constants.ERR_VDM_CAMERA_NOT_AUTHORIZED;
  }

  /**
   * @deprecated Use the new {@link Constants} class
   *  with the same constants value
   */
  @Deprecated
  public static class VideoProfile {
    /**
     *  160 x 120  @ 15 fps, 65 kbit/s
     */
    public final static int VIDEO_PROFILE_120P = Constants.VIDEO_PROFILE_120P;
    /**
     * 120 x 120  @ 15 fps, 50 kbit/s
     */
    public final static int VIDEO_PROFILE_120P_3 = Constants.VIDEO_PROFILE_120P_3;
    /**
     * 320 x 180  @ 15 fps, 140 kbit/s
     */
    public final static int VIDEO_PROFILE_180P = Constants.VIDEO_PROFILE_180P;
    /**
     * 180 x 180  @ 15 fps, 100 kbit/s
     */
    public final static int VIDEO_PROFILE_180P_3 = Constants.VIDEO_PROFILE_180P_3;
    /**
     * 240 x 180  @ 15 fps, 120 kbit/s
     */
    public final static int VIDEO_PROFILE_180P_4 = Constants.VIDEO_PROFILE_180P_4;
    /**
     * 320 x 240  @ 15 fps, 200 kbit/s
     */
    public final static int VIDEO_PROFILE_240P = Constants.VIDEO_PROFILE_240P;
    /**
     * 240 x 240  @ 15 fps, 140 kbit/s
     */
    public final static int VIDEO_PROFILE_240P_3 = Constants.VIDEO_PROFILE_240P_3;
    /**
     * 424 x 240  @ 15 fps, 220 kbit/s
     */
    public final static int VIDEO_PROFILE_240P_4 = Constants.VIDEO_PROFILE_240P_4;
    /**
     * 640 x 360  @ 15 fps, 400 kbit/s
     */
    public final static int VIDEO_PROFILE_360P = Constants.VIDEO_PROFILE_360P;
    /**
     * 360 x 360  @ 15 fps, 260 kbit/s
     */
    public final static int VIDEO_PROFILE_360P_3 = Constants.VIDEO_PROFILE_360P_3;
    /**
     * 640 x 360  @ 30 fps, 600 kbit/s
     */
    public final static int VIDEO_PROFILE_360P_4 = Constants.VIDEO_PROFILE_360P_4;
    /**
     * 360 x 360  @ 30 fps, 400 kbit/s
     */
    public final static int VIDEO_PROFILE_360P_6 = Constants.VIDEO_PROFILE_360P_6;
    /**
     * 480 x 360  @ 15 fps, 320 kbit/s
     */
    public final static int VIDEO_PROFILE_360P_7 = Constants.VIDEO_PROFILE_360P_7;
    /**
     * 480 x 360  @ 30 fps, 490 kbit/s
     */
    public final static int VIDEO_PROFILE_360P_8 = Constants.VIDEO_PROFILE_360P_8;
    /**
     * 640 x 480  @ 15 fps, 500 kbit/s
     */
    public final static int VIDEO_PROFILE_480P = Constants.VIDEO_PROFILE_480P;
    /**
     * 480 x 480  @ 15 fps, 400 kbit/s
     */
    public final static int VIDEO_PROFILE_480P_3 = Constants.VIDEO_PROFILE_480P_3;
    /**
     * 640 x 480  @ 30 fps, 750 kbit/s
     */
    public final static int VIDEO_PROFILE_480P_4 = Constants.VIDEO_PROFILE_480P_4;
    /**
     * 480 x 480  @ 30 fps, 600 kbit/s
     */
    public final static int VIDEO_PROFILE_480P_6 = Constants.VIDEO_PROFILE_480P_6;
    /**
     * 848 x 480  @ 15 fps, 610 kbit/s
     */
    public final static int VIDEO_PROFILE_480P_8 = Constants.VIDEO_PROFILE_480P_8;
    /**
     * 848 x 480  @ 30 fps, 930 kbit/s
     */
    public final static int VIDEO_PROFILE_480P_9 = Constants.VIDEO_PROFILE_480P_9;
    /**
     * 1280 x 720  @ 15 fps, 1130 kbit/s
     */
    public final static int VIDEO_PROFILE_720P = Constants.VIDEO_PROFILE_720P;
    /**
     * 1280 x 720  @ 30 fps, 1710 kbit/s
     */
    public final static int VIDEO_PROFILE_720P_3 = Constants.VIDEO_PROFILE_720P_3;
    /**
     * 960 x 720  @ 15 fps, 910 kbit/s
     */
    public final static int VIDEO_PROFILE_720P_5 = Constants.VIDEO_PROFILE_720P_5;
    /**
     * 960 x 720  @ 30 fps, 1380 kbit/s
     */
    public final static int VIDEO_PROFILE_720P_6 = Constants.VIDEO_PROFILE_720P_6;
    /**
     * 1920 x 1080  @ 15 fps, 2080 kbit/s
     */
    public final static int VIDEO_PROFILE_1080P = Constants.VIDEO_PROFILE_1080P;
    /**
     * 1920 x 1080  @ 30 fps, 3150 kbit/s
     */
    public final static int VIDEO_PROFILE_1080P_3 = Constants.VIDEO_PROFILE_1080P_3;
    /**
     * 1920 x 1080  @ 60 fps, 4780 kbit/s
     */
    public final static int VIDEO_PROFILE_1080P_5 = Constants.VIDEO_PROFILE_1080P_5;
    /**
     * 2560 x 1440  @ 30 fps, 4850 kbit/s
     */
    public final static int VIDEO_PROFILE_1440P = Constants.VIDEO_PROFILE_1440P;
    /**
     * 2560 x 1440  @ 60 fps, 7350 kbit/s
     */
    public final static int VIDEO_PROFILE_1440P_2 = Constants.VIDEO_PROFILE_1440P_2;
    /**
     * 3840 x 2160  @ 30 fps, 8910 kbit/s
     */
    public final static int VIDEO_PROFILE_4K = Constants.VIDEO_PROFILE_4K;
    /**
     * <p>3840 x 2160  @ 60 fps, 13500 kbit/s
     */
    public final static int VIDEO_PROFILE_4K_3 = Constants.VIDEO_PROFILE_4K_3;
    /**
     * <p>Default video profile: 640 x 360  @ 15 fps, 400 kbit/s
     */
    public final static int VIDEO_PROFILE_DEFAULT = Constants.VIDEO_PROFILE_DEFAULT;
  }

  /**
   * @deprecated Use the new {@link Constants} class
   *  with the same constants value
   */
  @Deprecated
  public static class ClientRole {
    /**
     * The host in a live broadcast.
     */
    public final static int CLIENT_ROLE_BROADCASTER = Constants.CLIENT_ROLE_BROADCASTER;
    /**
     * The audience in a live broadcast.
     */
    public final static int CLIENT_ROLE_AUDIENCE = Constants.CLIENT_ROLE_AUDIENCE;
  }

  /**
   * @deprecated Use the new {@link Constants} class
   *  with the same constants value
   */
  @Deprecated
  public static class UserOfflineReason {
    /**
     * The user has quit the call.
     */
    public final static int USER_OFFLINE_QUIT = Constants.USER_OFFLINE_QUIT;
    /**
     * The SDK timed out and the user dropped offline because it has not received any data package
     * for a period of time.
     */
    public final static int USER_OFFLINE_DROPPED = Constants.USER_OFFLINE_DROPPED;
  }

  /**
   * @brief The volume information of users.
   */
  public static class AudioVolumeInfo {
    /**
     * The user ID.
     * - In the local user's callback, `uid` is 0.
     * - In the remote users' callback, `uid` is the user ID of a remote user whose instantaneous volume
     * is the highest.
     */
    public int uid;

    /**
     * The volume of the user. The value ranges between 0 (the lowest volume) and 255 (the highest
     * volume). If the local user enables audio capturing and calls `muteLocalAudioStream` and set it as
     * `true` to mute, the value of `volume` indicates the volume of locally captured audio signal. If
     * the user calls `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)`, the value of `volume` indicates the volume after audio
     * mixing.
     */
    public int volume;

    /**
     * Voice activity status of the local user.
     * - 0: The local user is not speaking.
     * - 1: The local user is speaking.
     * @note
     * - The `vad` parameter does not report the voice activity status of remote users. In a remote
     * user's callback, the value of `vad` is always 1.
     * - To use this parameter, you must set `reportVad` to `true` when calling
     * `enableAudioVolumeIndication`.
     */
    public int vad;
    /**
     * The voice pitch of the local user. The value ranges between 0.0 and 4000.0.
     * @note The `voicePitch` parameter does not report the voice pitch of remote users. In the remote
     * users' callback, the value of `voicePitch` is always 0.0.
     */
    public double voicePitch;
  }

  /**
   * @brief Statistics of a call session.
   */
  public static class RtcStats {
    /**
     * Call duration of the local user in seconds, represented by an aggregate value.
     */
    public int totalDuration;
    /**
     * The number of bytes sent.
     */
    public int txBytes;
    /**
     * The number of bytes received.
     */
    public int rxBytes;
    /**
     * The actual bitrate (Kbps) while sending the local video stream.
     */
    public int txKBitRate;
    /**
     * The total number of audio bytes sent, represented by an aggregate value.
     */
    public int txAudioBytes;
    /**
     * The total number of audio bytes received, represented by an aggregate value.
     */
    public int rxAudioBytes;
    /**
     * The total number of video bytes sent, represented by an aggregate value.
     */
    public int txVideoBytes;
    /**
     * The total number of video bytes received, represented by an aggregate value.
     */
    public int rxVideoBytes;
    /**
     * The receiving bitrate (Kbps).
     */
    public int rxKBitRate;
    /**
     * The bitrate (Kbps) of sending the audio packet.
     */
    public int txAudioKBitRate;
    /**
     * The bitrate (Kbps) of receiving the audio.
     */
    public int rxAudioKBitRate;
    /**
     * The bitrate (Kbps) of sending the video.
     */
    public int txVideoKBitRate;
    /**
     * The bitrate (Kbps) of receiving the video.
     */
    public int rxVideoKBitRate;
    /**
     * The client-to-server delay (milliseconds).
     */
    public int lastmileDelay;
    /**
     * The system CPU usage (%).
     * @note
     * - The value of `cpuTotalUsage` is always reported as 0 in the `onLeaveChannel` callback.
     * - As of Android 8.1, you cannot get the CPU usage from this attribute due to system limitations.
     */
    public double cpuTotalUsage;
    /**
     * The round-trip time delay (ms) from the client to the local router.
     * @note On Android, to get `gatewayRtt`, ensure that you add the
     * `android.permission.ACCESS_WIFI_STATE` permission after `</application>` in the
     * `AndroidManifest.xml` file in your project.
     */
    public int gatewayRtt;
    /**
     * Application CPU usage (%).
     * @note
     * - The value of `cpuAppUsage` is always reported as 0 in the `onLeaveChannel` callback.
     * - As of Android 8.1, you cannot get the CPU usage from this attribute due to system limitations.
     */
    public double cpuAppUsage;
    /**
     * The number of users in the channel.
     */
    public int users;
    /**
     * The duration (ms) between the SDK starts connecting and the connection is established. If the
     * value reported is 0, it means invalid.
     */
    public int connectTimeMs;
    /**
     * The packet loss rate (%) from the client to the Agora server before applying the anti-packet-loss
     * algorithm.
     */
    public int txPacketLossRate;
    /**
     * The packet loss rate (%) from the Agora server to the client before using the anti-packet-loss
     * method.
     */
    public int rxPacketLossRate;
    /**
     * The memory ratio occupied by the app (%).
     * @note This value is for reference only. Due to system limitations, you may not get this value.
     */
    public double memoryAppUsageRatio;
    /**
     * The memory occupied by the system (%).
     * @note This value is for reference only. Due to system limitations, you may not get this value.
     */
    public double memoryTotalUsageRatio;
    /**
     * The memory size occupied by the app (KB).
     * @note This value is for reference only. Due to system limitations, you may not get this value.
     */
    public int memoryAppUsageInKbytes;
    /**
     * The local network acceleration state.
     * A value of 1 indicates that local network acceleration is active, while 0 indicates it is
     * inactive.
     * @technical preview
     */
    public int lanAccelerateState;
  }

  /**
   * @brief Results of the uplink and downlink last-mile network tests.
   */
  public static class LastmileProbeResult {
    /**
     * @brief Results of the uplink or downlink last-mile network test.
     */
    public static class LastmileProbeOneWayResult {
      /**
       * The packet loss rate (%).
       */
      public int packetLossRate;
      /**
       * The network jitter (ms).
       */
      public int jitter;
      /**
       * The estimated available bandwidth (bps).
       */
      public int availableBandwidth;
    }

    /**
     * The status of the last-mile network test, which includes:
     * - LASTMILE_PROBE_RESULT_COMPLETE (1): The last-mile network probe test is complete.
     * - LASTMILE_PROBE_RESULT_INCOMPLETE_NO_BWE (2): The last-mile network probe test is incomplete
     * because bandwidth prediction is not conducted. One possible reason is that testing resources were
     * temporarily limited.
     * - LASTMILE_PROBE_RESULT_UNAVAILABLE (3): The last-mile network probe test is not conducted.
     * Probably due to poor network conditions.
     */
    public short state;
    /**
     * The round-trip time (ms).
     */
    public int rtt;
    /**
     * Results of the uplink last-mile network test. See `LastmileProbeOneWayResult`.
     */
    public LastmileProbeOneWayResult uplinkReport = new LastmileProbeOneWayResult();
    /**
     * Results of the downlink last-mile network test. See `LastmileProbeOneWayResult`.
     */
    public LastmileProbeOneWayResult downlinkReport = new LastmileProbeOneWayResult();
  }

  /**
   * @brief The statistics of the local video stream.
   */
  public static class LocalVideoStats {
    /**
     * The ID of the local user.
     */
    public int uid;

    /**
     * The actual bitrate (Kbps) while sending the local video stream.
     * @note This value does not include the bitrate for resending the video after packet loss.
     */
    public int sentBitrate;
    /**
     * The actual frame rate (fps) while sending the local video stream.
     * @note This value does not include the frame rate for resending the video after packet loss.
     */
    public int sentFrameRate;
    /**
     * The frame rate (fps) for capturing the local video stream.
     */
    public int captureFrameRate;
    /**
     * The width (px) for capturing the local video stream.
     */
    public int captureFrameWidth;
    /**
     * The height (px) for capturing the local video stream.
     */
    public int captureFrameHeight;
    /**
     * The frame rate (fps) adjusted by the built-in video capture adapter (regulator) of the SDK for
     * capturing the local video stream. The regulator adjusts the frame rate of the video captured by
     * the camera according to the video encoding configuration.
     */
    public int regulatedCaptureFrameRate;
    /**
     * The width (px) adjusted by the built-in video capture adapter (regulator) of the SDK for
     * capturing the local video stream. The regulator adjusts the height and width of the video
     * captured by the camera according to the video encoding configuration.
     */
    public int regulatedCaptureFrameWidth;
    /**
     * The height (px) adjusted by the built-in video capture adapter (regulator) of the SDK for
     * capturing the local video stream. The regulator adjusts the height and width of the video
     * captured by the camera according to the video encoding configuration.
     */
    public int regulatedCaptureFrameHeight;
    /**
     * The output frame rate (fps) of the local video encoder.
     */
    public int encoderOutputFrameRate;
    /**
     * The output frame rate (fps) of the local video renderer.
     */
    public int rendererOutputFrameRate;
    /**
     * The target bitrate (Kbps) of the current encoder. This is an estimate made by the SDK based on
     * the current network conditions.
     */
    public int targetBitrate;
    /**
     * The target frame rate (fps) of the current encoder.
     */
    public int targetFrameRate;
    /**
     * The quality adaptation of the local video stream in the reported interval (based on the target
     * frame rate and target bitrate).
     * - ADAPT_NONE (0): The local video quality stays the same.
     * - ADAPT_UP_BANDWIDTH (1): The local video quality improves because the network bandwidth
     * increases.
     * - ADAPT_DOWN_BANDWIDTH (2): The local video quality deteriorates because the network bandwidth
     * decreases.
     * @since v2.4.0.
     */
    public int qualityAdaptIndication;
    /**
     * The bitrate (Kbps) while encoding the local video stream.
     * @note This value does not include the bitrate for resending the video after packet loss.
     */
    public int encodedBitrate;
    /**
     * The width of the encoded video (px).
     */
    public int encodedFrameWidth;
    /**
     * The height of the encoded video (px).
     */
    public int encodedFrameHeight;
    /**
     * The number of the sent video frames, represented by an aggregate value.
     */
    public int encodedFrameCount;
    /**
     * The codec type of the local video. See `VIDEO_CODEC_TYPE`.
     * - VIDEO_CODEC_VP8 (1): VP8.
     * - VIDEO_CODEC_H264 (2): H.264.
     * - VIDEO_CODEC_H265 (3): (Default) H.265.
     * @note In certain scenarios, such as low resolution of the captured video stream or limited device
     * performance, the SDK automatically adjusts to the H.264 encoding format.
     */
    public int codecType;
    /**
     * The video packet loss rate (%) from the local client to the Agora server before applying the
     * anti-packet loss strategies.
     */
    public int txPacketLossRate;
    /**
     * The brightness level of the video image captured by the local camera.
     * - CAPTURE_BRIGHTNESS_LEVEL_INVALID (-1): The SDK does not detect the brightness level of the
     * video image. Wait a few seconds to get the brightness level from `captureBrightnessLevel` in the
     * next callback.
     * - CAPTURE_BRIGHTNESS_LEVEL_NORMAL (0): The brightness level of the video image is normal.
     * - CAPTURE_BRIGHTNESS_LEVEL_BRIGHT (1): The brightness level of the video image is too bright.
     * - CAPTURE_BRIGHTNESS_LEVEL_DARK (2): The brightness level of the video image is too dark.
     */
    public int captureBrightnessLevel;
    /**
     * Whether we send dual stream now.
     */
    public boolean dualStreamEnabled;
    /**
     * The local video encoding acceleration type.
     * - 0: Software encoding is applied without acceleration.
     * - 1: Hardware encoding is applied for acceleration.
     */
    public int hwEncoderAccelerating;
    /**
     * @technical preview
     *
     * The encoder frame is sdr or hdr:
     * - encodedFrameDepth = 8: sdr.
     * - encodedFrameDepth = 10: hdr.
     */
    public int encodedFrameDepth;
  }

  /**
   * @brief Statistics of the remote video stream.
   */
  public static class RemoteVideoStats {
    /**
     * The user ID of the remote user sending the video stream.
     */
    public int uid;
    /**
     * Deprecated:
     * In scenarios where audio and video are synchronized, you can get the video delay data from
     * `networkTransportDelay` and `jitterBufferDelay` in `RemoteAudioStats`.
     * The video delay (ms).
     */
    public int delay;
    /**
     * End-to-end video latency (ms). That is, the time elapsed from the video capturing on the remote
     * user's end to the receiving and rendering of the video on the local user's end.
     */
    public int e2eDelay;
    /**
     * The width (pixels) of the video.
     */
    public int width;
    /**
     * The height (pixels) of the video.
     */
    public int height;
    /**
     * The bitrate (Kbps) of the remote video received since the last count.
     */
    public int receivedBitrate;
    /**
     * The frame rate (fps) of decoding the remote video.
     */
    public int decoderOutputFrameRate;
    /**
     * The frame rate (fps) of rendering the remote video.
     */
    public int rendererOutputFrameRate;
    /**
     * The packet loss rate (%) of the remote video.
     */
    public int frameLossRate;
    /**
     * The packet loss rate (%) of the remote video after using the anti-packet-loss technology.
     */
    public int packetLossRate;
    /**
     * The type of the video stream.
     * - VIDEO_STREAM_HIGH (0): High-quality stream, that is, a high-resolution and high-bitrate video
     * stream.
     * - VIDEO_STREAM_LOW (1): Low-quality stream, that is, a low-resolution and low-bitrate video
     * stream.
     */
    public int rxStreamType;
    /**
     * The total freeze time (ms) of the remote video stream after the remote user joins the channel. In
     * a video session where the frame rate is set to no less than 5 fps, video freeze occurs when the
     * time interval between two adjacent renderable video frames is more than 500 ms.
     */
    public int totalFrozenTime;
    /**
     * The total video freeze time as a percentage (%) of the total time the video is available. The
     * video is considered available as long as that the remote user neither stops sending the video
     * stream nor disables the video module after joining the channel.
     */
    public int frozenRate;
    /**
     * The amount of time (ms) that the audio is ahead of the video.
     * @note If this value is negative, the audio is lagging behind the video.
     */
    public int avSyncTimeMs;
    /**
     * The total active time (ms) of the video.
     * As long as the remote user or host neither stops sending the video stream nor disables the video
     * module after joining the channel, the video is available.
     */
    public long totalActiveTime;
    /**
     * The total duration (ms) of the remote video stream.
     */
    public long publishDuration;
    /**
     * The quality of the remote video stream in the reported interval.
     * The quality is determined by the Agora real-time video MOS (Mean Opinion Score) measurement
     * method. The return value range is [0, 500]. Dividing the return value by 100 gets the MOS
     * score, which ranges from 0 to 5. The higher the score, the better the video quality.
     * @note For textured video data, this parameter always returns 0.
     */
    public int mosValue;
    /**
     * Total number of video bytes received (bytes), represented by an aggregate value.
     */
    public int rxVideoBytes;
  }

  /**
   * @brief Local audio statistics.
   */
  public static class LocalAudioStats {
    /**
     * The number of audio channels.
     */
    public int numChannels;
    /**
     * The sampling rate (Hz) of sending the local user's audio stream.
     */
    public int sentSampleRate;
    /**
     * The average bitrate (Kbps) of sending the local user's audio stream.
     */
    public int sentBitrate;
    /**
     * The internal payload codec.
     */
    public int internalCodec;
    /**
     * The packet loss rate (%) from the local client to the Agora server before applying the
     * anti-packet loss strategies.
     */
    public int txPacketLossRate;
    /**
     * The audio device module delay (ms) when playing or recording audio.
     */
    public int audioDeviceDelay;
    /**
     * The playout delay of the device
     */
    public int audioPlayoutDelay;
    /**
     * The ear monitor delay (ms), which is the delay from microphone input to headphone output.
     */
    public int earMonitorDelay;
    /**
     * Acoustic echo cancellation (AEC) module estimated delay (ms), which is the signal delay between
     * when audio is played locally before being locally captured.
     */
    public int aecEstimatedDelay;
  };

  /**
   * @brief Audio statistics of the remote user.
   */
  public static class RemoteAudioStats {
    /**
     * The user ID of the remote user.
     */
    public int uid;
    /**
     * The quality of the audio stream sent by the user.
     * - QUALITY_UNKNOWN (0): The quality is unknown.
     * - QUALITY_EXCELLENT (1): The quality is excellent.
     * - QUALITY_GOOD (2): The network quality seems excellent, but the bitrate can be slightly lower
     * than excellent.
     * - QUALITY_POOR (3): Users can feel the communication is slightly impaired.
     * - QUALITY_BAD (4): Users cannot communicate smoothly.
     * - QUALITY_VBAD (5): The quality is so bad that users can barely communicate.
     * - QUALITY_DOWN (6): The network is down, and users cannot communicate at all.
     * - QUALITY_DETECTING (8): The last-mile probe test is in progress.
     */
    public int quality;
    /**
     * The network delay (ms) from the sender to the receiver.
     */
    public int networkTransportDelay;
    /**
     * The network delay (ms) from the audio receiver to the jitter buffer.
     * @note When the receiving end is an audience member and `audienceLatencyLevel` of
     * `ClientRoleOptions` is 1, this parameter does not take effect.
     */
    public int jitterBufferDelay;
    /**
     * The frame loss rate (%) of the remote audio stream in the reported interval.
     */
    public int audioLossRate;
    /**
     * The number of audio channels.
     */
    public int numChannels;
    /**
     * The sampling rate of the received audio stream in the reported interval.
     */
    public int receivedSampleRate;
    /**
     * The average bitrate (Kbps) of the received audio stream in the reported interval.
     */
    public int receivedBitrate;
    /**
     * The total freeze time (ms) of the remote audio stream after the remote user joins the channel. In
     * a session, audio freeze occurs when the audio frame loss rate reaches 4%.
     */
    public int totalFrozenTime;
    /**
     * The total audio freeze time as a percentage (%) of the total time when the audio is available.
     * The audio is considered available when the remote user neither stops sending the audio stream nor
     * disables the audio module after joining the channel.
     */
    public int frozenRate;
    /**
     * The quality of the remote audio stream in the reported interval. The quality is determined by the
     * Agora real-time audio MOS (Mean Opinion Score) measurement method. The return value range is [0,
     * 500]. Dividing the return value by 100 gets the MOS score, which ranges from 0 to 5. The higher
     * the score, the better the audio quality.
     * The subjective perception of audio quality corresponding to the Agora real-time audio MOS scores
     * is as follows:
     * | MOS score      | Perception of audio quality                                                                                                                                  |
     * | -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
     * | Greater than 4 | Excellent. The audio sounds clear and smooth.                                                                                                                |
     * | From 3.5 to 4  | Good. The audio has some perceptible impairment but still sounds clear.                                                                                      |
     * | From 3 to 3.5  | Fair. The audio freezes occasionally and requires attentive listening.                                                                                       |
     * | From 2.5 to 3  | Poor. The audio sounds choppy and requires considerable effort to understand.                                                                                |
     * | From 2 to 2.5  | Bad. The audio has occasional noise. Consecutive audio dropouts occur, resulting in some information loss. The users can communicate only with difficulty.   |
     * | Less than 2    | Very bad. The audio has persistent noise. Consecutive audio dropouts are frequent, resulting in severe information loss. Communication is nearly impossible. |
     */
    public int mosValue;
    /**
     * If the packet loss concealment (PLC) occurs for N consecutive times, freeze is considered as
     * PLC occurring for M consecutive times. freeze cnt = (n_plc - n) / m
     */
    public int frozenRateByCustomPlcCount;
    /**
     * The number of audio packet loss concealment
     */
    public int plcCount;

    /**
     * @technical preview
     * The number of times the remote audio stream has experienced freezing.
     */
    public int frozenCntByCustom;
    /**
     * @technical preview
     * The total duration (ms) that the remote audio stream has been in a frozen state.
     */
    public int frozenTimeByCustom;
    /**
     * The total active time (ms) between the start of the audio call and the callback of the remote
     * user.
     * The active time refers to the total duration of the remote user without the mute state.
     */
    public long totalActiveTime;
    /**
     * The total duration (ms) of the remote audio stream.
     */
    public long publishDuration;
    /**
     * The Quality of Experience (QoE) of the local user when receiving a remote audio stream.
     * - EXPERIENCE_QUALITY_GOOD (0): The QoE of the local user is good.
     * - EXPERIENCE_QUALITY_BAD (1): The QoE of the local user is poor.
     */
    public int qoeQuality;
    /**
     * Reasons why the QoE of the local user when receiving a remote audio stream is poor.
     * - EXPERIENCE_REASON_NONE (0): No reason, indicating a good QoE of the local user.
     * - REMOTE_NETWORK_QUALITY_POOR (1): The remote user's network quality is poor.
     * - LOCAL_NETWORK_QUALITY_POOR (2): The local user's network quality is poor.
     * - WIRELESS_SIGNAL_POOR (4): The local user's Wi-Fi or mobile network signal is weak.
     * - WIFI_BLUETOOTH_COEXIST (8): The local user enables both Wi-Fi and bluetooth, and their signals
     * interfere with each other. As a result, audio transmission quality is undermined.
     */
    public int qualityChangedReason;
    /**
     * Total number of audio bytes received (bytes) before network countermeasures, represented by
     * an aggregate value.
     */
    public int rxAudioBytes;
    /**
     * End-to-end audio delay (in milliseconds), which refers to the time from when the audio is
     * captured by the remote user to when it is played by the local user.
     */
    public int e2eDelay;
  }

  /**
   * @brief The information of the detected human face.
   */
  public static class AgoraFacePositionInfo {
    /**
     * The x-coordinate (px) of the human face in the local video. The horizontal position relative to
     * the origin, where the upper left corner of the captured video is the origin, and the x-coordinate
     * is the upper left corner of the watermark.
     */
    public int x;
    /**
     * The y-coordinate (px) of the human face in the local video. Taking the top left corner of the
     * captured video as the origin, the y coordinate represents the relative longitudinal displacement
     * of the top left corner of the human face to the origin.
     */
    public int y;
    /**
     * The width (px) of the human face in the captured video.
     */
    public int width;
    /**
     * The height (px) of the human face in the captured video.
     */
    public int height;
    /**
     * The distance between the human face and the device screen (cm).
     */
    public int distance;
  }

  /**
   * @brief The uplink network information.
   */
  public static class UplinkNetworkInfo {
    /**
     * The target video encoder bitrate (bps).
     */
    public int video_encoder_target_bitrate_bps;
  };

  /**
   * The statistics of the downlink network info.
   */
  public static class DownlinkNetworkInfo {
    /**
     * The lastmile buffer delay time in ms.
     */
    public int lastmile_buffer_delay_time_ms;
    /**
     * The bandwidth estimation bitrate in bps.
     */
    public int bandwidth_estimation_bps;
  };

  /**
   * @brief Indicators during video frame rendering progress.
   */
  public static class VideoRenderingTracingInfo {
    /**
     * The time interval (ms) from `startMediaRenderingTracing` to SDK triggering the
     * `onVideoRenderingTracingResult` callback. Agora recommends you call `startMediaRenderingTracing`
     * before joining a channel.
     */
    public int elapsedTime;
    /**
     * The time interval (ms) from `startMediaRenderingTracing` to `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
     * . A negative number indicates that `startMediaRenderingTracing` is called after calling
     * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`.
     */
    public int start2JoinChannel;
    /**
     * The time interval (ms) from `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to successfully joining
     * the channel.
     */
    public int join2JoinSuccess;
    /**
     * - If the local user calls `startMediaRenderingTracing` before successfully joining the channel,
     * this value is the time interval (ms) from the local user successfully joining the channel to the
     * remote user joining the channel.
     * - If the local user calls `startMediaRenderingTracing` after successfully joining the channel,
     * the value is the time interval (ms) from `startMediaRenderingTracing` to when the remote user
     * joins the channel.
     * @note
     * - If the local user calls `startMediaRenderingTracing` after the remote user joins the channel,
     * the value is 0 and meaningless.
     * - In order to reduce the time of rendering the first frame for remote users, Agora recommends
     * that the local user joins the channel when the remote user is in the channel to reduce this
     * value.
     */
    public int joinSuccess2RemoteJoined;
    /**
     * - If the local user calls `startMediaRenderingTracing` before the remote user joins the channel,
     * this value is the time interval (ms) from when the remote user joins the channel to when the
     * local user sets the remote view.
     * - If the local user calls `startMediaRenderingTracing` after the remote user joins the channel,
     * this value is the time interval (ms) from calling `startMediaRenderingTracing` to setting the
     * remote view.
     * @note
     * - If the local user calls `startMediaRenderingTracing` after setting the remote view, the value
     * is 0 and has no effect.
     * - In order to reduce the time of rendering the first frame for remote users, Agora recommends
     * that the local user sets the remote view before the remote user joins the channel, or sets the
     * remote view immediately after the remote user joins the channel to reduce this value.
     */
    public int remoteJoined2SetView;
    /**
     * - If the local user calls `startMediaRenderingTracing` before the remote user joins the channel,
     * this value is the time interval (ms) from the remote user joining the channel to subscribing to
     * the remote video stream.
     * - If the local user calls `startMediaRenderingTracing` after the remote user joins the channel,
     * this value is the time interval (ms) from `startMediaRenderingTracing` to subscribing to the
     * remote video stream.
     * @note
     * - If the local user calls `startMediaRenderingTracing` after subscribing to the remote video
     * stream, the value is 0 and has no effect.
     * - In order to reduce the time of rendering the first frame for remote users, Agora recommends
     * that after the remote user joins the channel, the local user immediately subscribes to the remote
     * video stream to reduce this value.
     */
    public int remoteJoined2UnmuteVideo;
    /**
     * - If the local user calls `startMediaRenderingTracing` before the remote user joins the channel,
     * this value is the time interval (ms) from when the remote user joins the channel to when the
     * local user receives the remote video stream.
     * - If the local user calls `startMediaRenderingTracing` after the remote user joins the channel,
     * this value is the time interval (ms) from `startMediaRenderingTracing` to receiving the remote
     * video stream.
     * @note
     * - If the local user calls `startMediaRenderingTracing` after receiving the remote video stream,
     * the value is 0 and has no effect.
     * - In order to reduce the time of rendering the first frame for remote users, Agora recommends
     * that the remote user publishes video streams immediately after joining the channel, and the local
     * user immediately subscribes to remote video streams to reduce this value.
     */
    public int remoteJoined2PacketReceived;
  };

  /**
   * @brief Layout information of a specific sub-video stream within the mixed stream.
   */
  public static class VideoLayout {
    /**
     * The channel name to which the sub-video stream belongs.
     */
    public String channelId;
    /**
     * User ID who published this sub-video stream.
     */
    public int uid;
    /**
     * Reserved for future use.
     */
    public String strUid;
    /**
     * Status of the sub-video stream on the video mixing canvas.
     * - 0: Normal. The sub-video stream has been rendered onto the mixing canvas.
     * - 1: Placeholder image. The sub-video stream has no video frames and is displayed as a
     * placeholder on the mixing canvas.
     * - 2: Black image. The sub-video stream is replaced by a black image.
     */
    public int videoState;
    /**
     * X-coordinate (px) of the sub-video stream on the mixing canvas. The relative lateral displacement
     * of the top left corner of the video for video mixing to the origin (the top left corner of the
     * canvas).
     */
    public int x;
    /**
     * Y-coordinate (px) of the sub-video stream on the mixing canvas. The relative longitudinal
     * displacement of the top left corner of the captured video to the origin (the top left corner of
     * the canvas).
     */
    public int y;
    /**
     * Width (px) of the sub-video stream.
     */
    public int width;
    /**
     * Heitht (px) of the sub-video stream.
     */
    public int height;

    @Override
    public String toString() {
      return "VideoLayout{"
          + "channelId='" + channelId + '\'' + ", uid=" + uid + ", strUid='" + strUid + '\''
          + ", videoState=" + videoState + ", x=" + x + ", y=" + y + ", width=" + width
          + ", height=" + height + '}';
    }
  }

  /**
   * @brief Detailed layout information of a mixed video stream.
   */
  public static class VideoLayoutInfo {
    /**
     * The width of the mixed video stream (px).
     */
    public int width;
    /**
     * The height of the mixed video stream (px).
     */
    public int height;
    /**
     * The number of layout information in the mixed video stream.
     */
    public int layoutCount;
    /**
     * The detailed layout information of a mixed video stream. See `VideoLayout`.
     */
    public VideoLayout[] layoutList;

    @Override
    public String toString() {
      return "VideoLayoutInfo{"
          + "width=" + width + ", height=" + height + ", layoutCount=" + layoutCount
          + ", layoutList=" + Arrays.toString(layoutList) + '}';
    }
  }

  /**
   * @brief Statistical information about a specific network path.
   *
   * @since 4.6.0
   */
  public static class PathStats {
    /**
     * Types of network path:
     * - LAN: Local Area Network.
     * - WiFi: Wireless Local Area Network.
     * - Mobile: Mobile network.
     */
    public int pathType;

    /**
     * The transmission bitrate of the path in Kbps.
     */
    public int txKBitRate;

    /**
     * The receiving bitrate of the path in Kbps.
     */
    public int rxKBitRate;

    @Override
    public String toString() {
      return "PathStats{"
          + "pathType=" + pathType + ", txKBitRate=" + txKBitRate + ", rxKBitRate=" + rxKBitRate
          + '}';
    }
  }

  /**
   * @brief Aggregates statistics of each network path in multipath transmission.
   *
   * @since 4.6.0
   */
  public static class MultipathStats {
    /**
     * The total number of bytes sent over LAN.
     */
    public int lanTxBytes;

    /**
     * The total number of bytes received over LAN.
     */
    public int lanRxBytes;

    /**
     * The total number of bytes sent over Wi-Fi.
     */
    public int wifiTxBytes;

    /**
     * The total number of bytes received over Wi-Fi.
     */
    public int wifiRxBytes;

    /**
     * The total number of bytes sent over the mobile network.
     */
    public int mobileTxBytes;

    /**
     * The total number of bytes received over the mobile network.
     */
    public int mobileRxBytes;

    /**
     * The number of currently active transmission paths.
     */
    public int activePathNum;

    /**
     * An array of statistics for each active transmission path. See `PathStats`.
     */
    public PathStats[] pathStats;

    @Override
    public String toString() {
      return "MultipathStats{"
          + "lanTxBytes=" + lanTxBytes + ", lanRxBytes=" + lanRxBytes
          + ", wifiTxBytes=" + wifiTxBytes + ", wifiRxBytes=" + wifiRxBytes
          + ", mobileTxBytes=" + mobileTxBytes + ", mobileRxBytes=" + mobileRxBytes
          + ", activePathNum=" + activePathNum + ", pathStats=" + Arrays.toString(pathStats) + '}';
    }
  }

  /**
   * @brief Reports an error during SDK runtime.
   *
   * @details
   * This callback indicates that an error (concerning network or media) occurs during SDK runtime. In
   * most cases, the SDK cannot fix the issue and resume running. The SDK requires the app to take
   * action or informs the user about the issue.
   *
   * @param err Error code.
   *
   */
  public void onError(int err) {}

  /**
   * @brief Occurs when a user joins a channel.
   *
   * @details
   * This callback notifies the application that a user joins a specified channel.
   * Call timing: The SDK triggers this callback when you call `joinChannel(String token, String channelId, String optionalInfo, int uid)`, `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * , `joinChannelWithUserAccount(String token, String channelName, String userAccount)`, `joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options)` , `joinChannelEx`
   * or `joinChannelWithUserAccountEx` to join a channel.
   *
   * @param channel The channel name.
   * @param uid The ID of the user who joins the channel.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the
   * SDK triggers this callback.
   *
   */
  public void onJoinChannelSuccess(String channel, int uid, int elapsed) {}

  /**
   * @brief Occurs when a user rejoins the channel.
   *
   * @details
   * Call timing: When a user loses connection with the server because of network problems, the SDK
   * automatically tries to reconnect and triggers this callback upon reconnection.
   *
   * @param channel The channel name.
   * @param uid The ID of the user who rejoins the channel.
   * @param elapsed Time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the SDK
   * triggers this callback.
   *
   */
  public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {}

  /**
   * @brief Occurs when a user leaves a channel.
   *
   * @details
   * You can obtain information such as the total duration of a call, and the data traffic that the
   * SDK transmits and receives.
   * Call timing: The SDK triggers this callback after you call `leaveChannel()`, `leaveChannel(LeaveChannelOptions options)`
   * , `leaveChannelEx(RtcConnection connection)`, or `leaveChannelEx(RtcConnection connection, LeaveChannelOptions options)` to leave a channel.
   *
   * @param stats Call statistics. See `RtcStats`.
   *
   */
  public void onLeaveChannel(RtcStats stats) {}

  /**
   * @brief Occurs when the user role or the audience latency level changes.
   *
   * @details
   * Call timing: This callback will be triggered in any of the following situations:
   * - Calling `setClientRole(int role)` or `setClientRole(int role, ClientRoleOptions options)` to set the user role or audience latency
   * level **after joining a channel**
   * - Calling `setClientRole(int role)` or `setClientRole(int role, ClientRoleOptions options)` and set the user role to `AUDIENCE`
   * **before joining a channel**.
   *
   * @note This callback will not be triggered when you call `setClientRole(int role)` or `setClientRole(int role, ClientRoleOptions options)`
   * and set the user role to `BROADCASTER` **before joining a channel**.
   *
   * @param oldRole Role that the user switches from:
   * - CLIENT_ROLE_BROADCASTER (1): Host.
   * - CLIENT_ROLE_AUDIENCE (2): Audience.
   * @param newRole Role that the user switches to:
   * - CLIENT_ROLE_BROADCASTER (1): Host.
   * - CLIENT_ROLE_AUDIENCE (2): Audience.
   * @param newRoleOptions Since
   * v4.1.0
   * Properties of the role that the user switches to. See `ClientRoleOptions`.
   *
   */
  public void onClientRoleChanged(int oldRole, int newRole, ClientRoleOptions newRoleOptions) {}

  /**
   * @brief Occurs when switching a user role fails.
   *
   * @details
   * This callback informs you about the reason for failing to switching and your current user role.
   * Call timing: The SDK triggers this callback when the local user calls `setClientRole(int role)` or
   * `setClientRole(int role, ClientRoleOptions options)` after joining a channel to switch the user role but the switching fails.
   *
   * @param reason The reason for a user role switch failure.
   * - CLIENT_ROLE_CHANGE_FAILED_TOO_MANY_BROADCASTERS (1): The number of hosts in the channel is
   * already at the upper limit.Note: This enumerator is reported only when the support for 128 users
   * is enabled. The maximum number of hosts is based on the actual number of hosts configured when
   * you enable the 128-user feature.
   * - CLIENT_ROLE_CHANGE_FAILED_NOT_AUTHORIZED (2): The request is rejected by the Agora server.
   * Agora recommends you prompt the user to try to switch their user role again.
   * - CLIENT_ROLE_CHANGE_FAILED_REQUEST_TIME_OUT (3): The request is timed out. Agora recommends you
   * prompt the user to check the network connection and try to switch their user role again.
   * Deprecated:
   * This enumerator is deprecated since v4.4.0 and is not recommended for use.
   * - CLIENT_ROLE_CHANGE_FAILED_CONNECTION_FAILED (4): The SDK connection fails. You can troubleshoot
   * the failure through the `reason` reported by `onConnectionStateChanged`.
   * Deprecated:
   * This enumerator is deprecated since v4.4.0 and is not recommended for use.
   * @param currentRole Current user role.
   * - CLIENT_ROLE_BROADCASTER (1): Host. A host can both send and receive streams.
   * - CLIENT_ROLE_AUDIENCE (2): Audience. An audience member can only receive streams.
   *
   */
  public void onClientRoleChangeFailed(int reason, int currentRole) {}

  /**
   * @brief Occurs when the local user registers a user account.
   *
   * @since v2.8.0.
   *
   * @details
   * After the local user successfully calls `registerLocalUserAccount` to register the user account
   * or calls `joinChannelWithUserAccount(String token, String channelName, String userAccount, ChannelMediaOptions options)` to join a channel, the SDK triggers the callback and
   * informs the local user's UID and User Account.
   *
   * @param uid The ID of the local user.
   * @param userAccount The user account of the local user.
   *
   */
  public void onLocalUserRegistered(int uid, String userAccount) {}
  /**
   * @brief Occurs when the SDK gets the user ID and user account of the remote user.
   *
   * @since v2.8.0.
   *
   * @details
   * After a remote user joins the channel, the SDK gets the UID and user account of the remote user,
   * caches them in a mapping table object, and triggers this callback on the local client.
   *
   * @param uid The user ID of the remote user.
   * @param userInfo The UserInfo object that contains the user ID and user account of the remote
   * user. See `UserInfo` for details.
   *
   */
  public void onUserInfoUpdated(int uid, UserInfo userInfo) {}

  /**
   * Occurs when the remote user state is updated.
   * @param uid The ID of the remote user.
   * @param state State of the remote user: Just & #REMOTE_USER_STATE
   * - `USER_STATE_MUTE_AUDIO(1 << 0)`: The remote user has muted the audio.
   * - `USER_STATE_MUTE_VIDEO(1 << 1)`: The remote user has muted the video.
   * - `USER_STATE_ENABLE_VIDEO(1 << 4)`: The remote user has enabled the video, which includes
   * video capturing and encoding.
   * - `USER_STATE_ENABLE_LOCAL_VIDEO(1 << 8)`: The remote user has enabled the local video
   * capturing.
   */
  public void onUserStateChanged(int uid, int state) {}

  /**
   * @brief Occurs when a remote user (in the communication profile)/ host (in the live streaming
   * profile) joins the channel.
   *
   * @details
   * - In a communication channel, this callback indicates that a remote user joins the channel. The
   * SDK also triggers this callback to report the existing users in the channel when a user joins the
   * channel.
   * - In a live-broadcast channel, this callback indicates that a host joins the channel. The SDK
   * also triggers this callback to report the existing hosts in the channel when a host joins the
   * channel. Agora recommends limiting the number of co-hosts to 32, with a maximum of 17 video
   * hosts.
   * Call timing: The SDK triggers this callback under one of the following circumstances:
   * - A remote user/host joins the channel.
   * - A remote user switches the user role to the host after joining the channel.
   * - A remote user/host rejoins the channel after a network interruption.
   *
   * @param uid The ID of the user or host who joins the channel.
   * @param elapsed Time delay (ms) from the local user calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * until this callback is triggered.
   *
   */
  public void onUserJoined(int uid, int elapsed) {}

  /**
   * @brief Occurs when a remote user (in the communication profile)/ host (in the live streaming
   * profile) leaves the channel.
   *
   * @details
   * There are generally two reasons for users to become offline:
   * - Leave the channel: When a user/host leaves the channel, the user/host sends a goodbye message.
   * - Drop offline: When no data packet of the user or host is received for a certain period of time
   * (20 seconds for the communication profile, and more for the live broadcast profile), the SDK
   * assumes that the user/host drops offline. A poor network connection may lead to false detections.
   * It is recommended to use the Agora RTM SDK for reliable offline detection.
   * Call timing: This callback is triggered when a remote user (in the communication profile) or host
   * (in the live streaming profile) leaves a channel.
   *
   * @param uid The ID of the user who leaves the channel or goes offline.
   * @param reason Reasons why a remote user (in the communication profile) or host (in the live
   * streaming profile) goes offline:
   * - USER_OFFLINE_QUIT (0): The user has quit the call. When the user leaves the channel, the user
   * sends a goodbye message.
   * - USER_OFFLINE_DROPPED (1): The SDK timed out and the user dropped offline because it has not
   * received any data package within a certain period of time. A poor network connection may lead to
   * false detection. It is recommended to use the Agora RTM SDK for reliable offline detection.
   * - USER_OFFLINE_BECOME_AUDIENCE (2): The user switches the user role from a broadcaster to an
   * audience.
   *
   */
  public void onUserOffline(int uid, int reason) {}

  /**
   * @brief Occurs when the network connection state changes.
   *
   * @since v2.3.2.
   *
   * @details
   * When the network connection state changes, the SDK triggers this callback and reports the current
   * connection state and the reason for the change.
   *
   * @param state The current connection state.
   * - CONNECTION_STATE_DISCONNECTED (1): The SDK is disconnected from Agora's edge server.
   * - CONNECTION_STATE_CONNECTING (2): The SDK is connecting to Agora's edge server.
   * - CONNECTION_STATE_CONNECTED (3): The SDK is connected to Agora's edge server.
   * - CONNECTION_STATE_RECONNECTING (4): The SDK tries to reconnect to the Agora edge server.
   * - CONNECTION_STATE_FAILED (5): The SDK fails to connect to Agora's edge server.
   * @param reason - CONNECTION_CHANGED_CONNECTING (0): The SDK is connecting to Agora's edge server.
   * - CONNECTION_CHANGED_JOIN_SUCCESS (1): The SDK has joined the channel successfully.
   * - CONNECTION_CHANGED_INTERRUPTED (2): The connection between the SDK and Agora's edge server is
   * interrupted.
   * - CONNECTION_CHANGED_BANNED_BY_SERVER (3): The connection is banned by the Agora server. For
   * example, when a user is kicked out of the channel, this code will be returned.
   * - CONNECTION_CHANGED_JOIN_FAILED (4): The SDK fails to join the channel. When the SDK fails to
   * join the channel for more than 20 minutes, this code will be returned and the SDK stops
   * reconnecting to the channel. You need to prompt the user to try to switch to another network and
   * rejoin the channel.
   * - CONNECTION_CHANGED_LEAVE_CHANNEL (5): The SDK has left the channel.
   * - CONNECTION_CHANGED_INVALID_APP_ID (6): Invalid App ID. You need to rejoin the channel with a
   * valid APP ID and make sure the App ID you are using is consistent with the one generated in the
   * Agora Console.
   * - CONNECTION_CHANGED_INVALID_CHANNEL_NAME (7): Invalid channel name. Rejoin the channel with a
   * valid channel name. A valid channel name is a string of up to 64 bytes in length. Supported
   * characters (89 characters in total):
   *   - All lowercase English letters: a to z.
   *   - All uppercase English letters: A to Z.
   *   - All numeric characters: 0 to 9.
   *   - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[",
   * "]", "^", "_", "{", "}", "|", "~", ","
   * - CONNECTION_CHANGED_INVALID_TOKEN (8): Invalid token. Possible reasons are as follows:
   *   - The App Certificate for the project is enabled in Agora Console, but you do not pass in a
   * token when joining a channel.
   *   - The uid specified when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel is inconsistent with
   * the uid passed in when generating the token.
   *   - The generated token and the token used to join the channel are not consistent.
   * Ensure the following:
   *   - When your project enables App Certificate, you need to pass in a token to join a channel.
   *   - The user ID specified when generating the token is consistent with the user ID used when
   * joining the channel.
   *   - The generated token is the same as the token passed in to join the channel.
   * - CONNECTION_CHANGED_TOKEN_EXPIRED (9): The token currently being used has expired. You need to
   * generate a new token on your server and rejoin the channel with the new token.
   * - CONNECTION_CHANGED_REJECTED_BY_SERVER (10): The user is banned by the server.
   * - CONNECTION_CHANGED_SETTING_PROXY_SERVER (11): The SDK tries to reconnect after setting a proxy
   * server.
   * - CONNECTION_CHANGED_RENEW_TOKEN (12): The connection state changed because the token is renewed.
   * - CONNECTION_CHANGED_CLIENT_IP_ADDRESS_CHANGED (13): Client IP address changed. If you receive
   * this code multiple times, You need to prompt the user to switch networks and try joining the
   * channel again.
   * - CONNECTION_CHANGED_KEEP_ALIVE_TIMEOUT (14): Timeout for the keep-alive of the connection
   * between the SDK and the Agora edge server. The SDK tries to reconnect to the server
   * automatically.
   * - CONNECTION_CHANGED_REJOIN_SUCCESS (15): The SDK has rejoined the channel successfully.
   * - CONNECTION_CHANGED_LOST (16): The connection between the SDK and the server is lost.
   * - CONNECTION_CHANGED_ECHO_TEST (17): The connection state changes due to the echo test.
   * - CONNECTION_CHANGED_CLIENT_IP_ADDRESS_CHANGED_BY_USER (18): The local IP address was changed by
   * the user.
   * - CONNECTION_CHANGED_SAME_UID_LOGIN (19): The user joined the same channel from different devices
   * with the same UID.
   * CAUTION: Joining the same RTC channel with the same UID is an undefined behavior. Agora does not
   * guarantee that `reason` 19 is always triggered under the same circumstance.
   * - CONNECTION_CHANGED_TOO_MANY_BROADCASTERS (20): The number of hosts in the channel has reached
   * the upper limit.
   *
   */
  public void onConnectionStateChanged(int state, int reason) {}

  /**
   * @brief Occurs when the connection between the SDK and the server is interrupted.
   *
   * @details
   * The SDK triggers this callback when it loses connection with the server for more than four
   * seconds after the connection is established. After triggering this callback, the SDK tries to
   * reconnect to the server. You can use this callback to implement pop-up reminders. The differences
   * between this callback and `onConnectionLost` are as follow:
   * - The SDK triggers the `onConnectionInterrupted` callback when it loses connection with the
   * server for more than four seconds after it successfully joins the channel.
   * - The SDK triggers the `onConnectionLost` callback when it loses connection with the server for
   * more than 10 seconds, whether or not it joins the channel.
   * If the SDK fails to rejoin the channel 20 minutes after being disconnected from Agora's edge
   * server, the SDK stops rejoining the channel.
   *
   */
  public void onConnectionInterrupted() {}

  /**
   * @brief Occurs when the SDK cannot reconnect to Agora's edge server 10 seconds after its
   * connection to the server is interrupted.
   *
   * @details
   * The SDK triggers this callback when it cannot connect to the server 10 seconds after calling the
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` method, regardless of whether it is in the channel. If the SDK fails to
   * rejoin the channel 20 minutes after being disconnected from Agora's edge server, the SDK stops
   * rejoining the channel.
   *
   */
  public void onConnectionLost() {}

  /**
   * @brief Occurs when the connection is banned by the Agora server.
   */
  public void onConnectionBanned() {}

  /**
   * @brief Occurs when the token expires in 30 seconds.
   *
   * @details
   * When receiving this callback, you need to generate a new token on your token server and you can
   * renew your token through one of the following ways:
   * - In scenarios involving one channel:
   *   - Call `renewToken` to pass in the new token.
   *   - Call `leaveChannel(LeaveChannelOptions options)` to leave the current channel and then pass in the new token when
   * you call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join a channel.
   * - In scenarios involving mutiple channels: Call `updateChannelMediaOptionsEx` to pass in the new
   * token.
   * Call timing: The SDK triggers this callback 30 seconds before the token expires, reminding the
   * app to update the token.
   *
   * @param token The token that is about to expire.
   *
   */
  public void onTokenPrivilegeWillExpire(String token) {}

  /**
   * @brief Occurs when the token expires.
   *
   * @details
   * The SDK triggers this callback if the token expires.
   * When receiving this callback, you need to generate a new token on your token server and you can
   * renew your token through one of the following ways:
   * - In scenarios involving one channel:
   *   - Call `renewToken` to pass in the new token.
   *   - Call `leaveChannel(LeaveChannelOptions options)` to leave the current channel and then pass in the new token when
   * you call `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join a channel.
   * - In scenarios involving mutiple channels: Call `updateChannelMediaOptionsEx` to pass in the new
   * token.
   *
   */
  public void onRequestToken() {}

  /**
   * Occurs when connection license verification fails
   * You can know the reason accordding to error code
   *
   * @param error license verify fail reason:
   * - `LICENSE_ERR_INVALID(1)`: Invalid license.
   * - `LICENSE_ERR_EXPIRE(2)`: License expired.
   * - `LICENSE_ERR_MINUTES_EXCEED(3)`: Exceed license minutes limit.
   * - `LICENSE_ERR_LIMITED_PERIOD(4)`: License use in limited period.
   * - `LICENSE_ERR_DIFF_DEVICES(5)`: Same license used in different devices at the same time.
   * - `LICENSE_ERR_INTERNAL(99)`: SDK internal error.
   */
  public void onLicenseValidationFailure(int error) {}

  /**
   * @brief Reports the volume information of users.
   *
   * @details
   * By default, this callback is disabled. You can enable it by calling
   * `enableAudioVolumeIndication`. Once this callback is enabled and users send streams in the
   * channel, the SDK triggers the `onAudioVolumeIndication` callback according to the time interval
   * set in `enableAudioVolumeIndication`. The SDK triggers two independent `onAudioVolumeIndication`
   * callbacks simultaneously, which separately report the volume information of the local user who
   * sends a stream and the remote users (up to three) whose instantaneous volume is the highest.
   *
   * @note
   * Once this callback is enabled, if the local user calls the `muteLocalAudioStream` method to mute,
   * the SDK continues to report the volume indication of the local user.
   * If a remote user whose volume is one of the three highest in the channel stops publishing the
   * audio stream for 20 seconds, the callback excludes this user's information; if all remote users
   * stop publishing audio streams for 20 seconds, the SDK stops triggering the callback for remote
   * users.
   *
   * @param speakers The volume information of the users. See `AudioVolumeInfo`. An empty `speakers`
   * array in the callback indicates that no remote user is in the channel or is sending a stream.
   * @param totalVolume The volume of the speaker. The value range is [0,255].
   * - In the callback for the local user, `totalVolume` is the volume of the local user who sends a
   * stream.
   * - In the callback for remote users, `totalVolume` is the sum of the volume of all remote users
   * (up to three) whose instantaneous volume is the highest. If the user calls `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)`
   * , then `totalVolume` is the volume after audio mixing.
   *
   */
  public void onAudioVolumeIndication(AudioVolumeInfo[] speakers, int totalVolume) {}

  /**
   * @brief Occurs when the most active remote speaker is detected.
   *
   * @details
   * After a successful call of `enableAudioVolumeIndication`, the SDK continuously detects which
   * remote user has the loudest volume. During the current period, the remote user whose volume is
   * detected as the loudest for the most times, is the most active user.
   * When the number of users is no less than two and an active remote speaker exists, the SDK
   * triggers this callback and reports the `uid` of the most active remote speaker.
   * - If the most active remote speaker is always the same user, the SDK triggers the
   * `onActiveSpeaker` callback only once.
   * - If the most active remote speaker changes to another user, the SDK triggers this callback again
   * and reports the `uid` of the new active remote speaker.
   *
   * @param uid The user ID of the most active speaker.
   *
   */
  public void onActiveSpeaker(int uid) {}

  /**
   * @brief Occurs when the video stops playing.
   *
   * @deprecated Use {@link onLocalVideoStateChanged(VideoSourceType, int, int)
   * onLocalVideoStateChanged} instead.
   *
   * @details
   * The application can use this callback to change the configuration of the `view` (for example,
   * displaying other pictures in the view) after the video stops playing.
   *
   */
  @Deprecated
  public void onVideoStopped() {}

  /**
   * @brief Occurs when the first local video frame is displayed on the local video view.
   *
   * @details
   * The SDK triggers this callback when the first local video frame is displayed on the local video
   * view.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param width The width (px) of the first local video frame.
   * @param height The height (px) of the first local video frame.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` or
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to join the channel to when the SDK triggers this callback. If `startPreview()`
   * / `startPreview(Constants.VideoSourceType sourceType)` is called before joining the channel, this parameter indicates the
   * time elapsed from calling `startPreview()` or `startPreview(Constants.VideoSourceType sourceType)` to when this event
   * occurred.
   *
   */
  public void onFirstLocalVideoFrame(
      Constants.VideoSourceType source, int width, int height, int elapsed) {}

  /**
   * @brief Occurs when the first video frame is published.
   *
   * @details
   * The SDK triggers this callback under one of the following circumstances:
   * - The local client enables the video module and calls `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * to join the channel successfully.
   * - The local client calls `muteLocalVideoStream` (`true`) and `muteLocalVideoStream` (`false`) in
   * sequence.
   * - The local client calls `disableVideo` and `enableVideo` in sequence.
   * - The local client calls `pushExternalVideoFrameById(AgoraVideoFrame frame, int videoTrackId)` to successfully push the video frame
   * to the SDK.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param elapsed Time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` or `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)`
   * until this callback is triggered.
   *
   */
  public void onFirstLocalVideoFramePublished(Constants.VideoSourceType source, int elapsed) {}

  /**
   * @brief Occurs when the first remote video frame is received and decoded.
   *
   * @deprecated
   * This callback is deprecated. Use `REMOTE_VIDEO_STATE_STARTING(1)` or
   * `REMOTE_VIDEO_STATE_DECODING(2)` in the {@link onRemoteVideoStateChanged
   * onRemoteVideoStateChanged} callback instead.
   *
   * @details
   * The SDK triggers this callback under one of the following circumstances:
   * - The remote user joins the channel and sends the video stream.
   * - The remote user stops sending the video stream and re-sends it after 15 seconds. Reasons for
   * such an interruption include:
   *   - The remote user leaves the channel.
   *   - The remote user drops offline.
   *   - The remote user calls `disableVideo` to disable video.
   *
   * @param uid The user ID of the remote user sending the video stream.
   * @param width The width (px) of the video stream.
   * @param height The height (px) of the video stream.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` or
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the SDK triggers this callback.
   *
   */
  public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {}

  /**
   * @brief Occurs when the renderer receives the first frame of the remote video.
   *
   * @note This callback is only triggered when the video frame is rendered by the SDK; it will not be
   * triggered if the user employs custom video rendering.You need to implement this independently
   * using methods outside the SDK.
   *
   * @param uid The user ID of the remote user sending the video stream.
   * @param width The width (px) of the video stream.
   * @param height The height (px) of the video stream.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, String optionalInfo, int uid)` or
   * `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the SDK triggers this callback.
   *
   */
  public void onFirstRemoteVideoFrame(int uid, int width, int height, int elapsed) {}

  /**
   * @brief Occurs when a remote user (in the communication profile) or a host (in the live streaming
   * profile) stops/resumes sending the audio stream.
   *
   * @details
   * The SDK triggers this callback when the remote user stops or resumes sending the audio stream by
   * calling the `muteLocalAudioStream` method.
   *
   * @note This callback does not work properly when the number of users (in the communication
   * profile) or hosts (in the live streaming channel) in a channel exceeds 32.
   *
   * @param uid The user ID.
   * @param muted Whether the remote user's audio stream is muted:
   * - `true`: User's audio stream is muted.
   * - `false`: User's audio stream is unmuted.
   *
   */
  public void onUserMuteAudio(int uid, boolean muted) {}

  /**
   * @brief Occurs when a remote user stops or resumes publishing the video stream.
   *
   * @details
   * When a remote user calls `muteLocalVideoStream` to stop or resume publishing the video stream,
   * the SDK triggers this callback to report to the local user the state of the streams published by
   * the remote user.
   *
   * @note This callback can be inaccurate when the number of users (in the communication profile) or
   * hosts (in the live streaming profile) in a channel exceeds 32.
   *
   * @param uid The user ID of the remote user.
   * @param muted Whether the remote user stops publishing the video stream:
   * - `true`: The remote user stops publishing the video stream.
   * - `false`: The remote user resumes publishing the video stream.
   *
   */
  public void onUserMuteVideo(int uid, boolean muted) {}

  /**
   * @brief Occurs when a remote user enables or disables the video module.
   *
   * @details
   * Once the video module is disabled, the user can only use a voice call. The user cannot send or
   * receive any video.
   * The SDK triggers this callback when a remote user enables or disables the video module by calling
   * the `enableVideo` or `disableVideo` method.
   *
   * @param uid The user ID of the remote user.
   * @param enabled - `true`: The video module is enabled.
   * - `false`: The video module is disabled.
   *
   */
  public void onUserEnableVideo(int uid, boolean enabled) {}

  /**
   * @brief Occurs when a specific remote user enables/disables the local video capturing function.
   *
   * @deprecated
   * This callback is deprecated and replaced by the {@link onRemoteVideoStateChanged
   * onRemoteVideoStateChanged} callback with the following parameters:
   * - `REMOTE_VIDEO_STATE_STOPPED(0)` and `REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED(5)`.
   * - `REMOTE_VIDEO_STATE_DECODING(2)` and `REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED(6)`.
   *
   * @details
   * The SDK triggers this callback when the remote user resumes or stops capturing the video stream
   * by calling the `enableLocalVideo` method.
   *
   * @param uid The user ID of the remote user.
   * @param enabled Whether the specified remote user enables/disables local video capturing:
   * - `true`: The video module is enabled. Other users in the channel can see the video of this
   * remote user.
   * - `false`: The video module is disabled. Other users in the channel can no longer receive the
   * video stream from this remote user, while this remote user can still receive the video streams
   * from other users.
   *
   */
  @Deprecated
  public void onUserEnableLocalVideo(int uid, boolean enabled) {}

  /**
   * @brief Occurs when the video size or rotation of a specified user changes.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param uid The ID of the user whose video size or rotation changes. (The `uid` for the local user
   * is 0. The video is the local user's video preview).
   * @param width The width (pixels) of the video stream.
   * @param height The height (pixels) of the video stream.
   * @param rotation The rotation information. The value range is [0,360).
   *
   */
  public void onVideoSizeChanged(
      Constants.VideoSourceType source, int uid, int width, int height, int rotation) {}

  /**
   * @brief Occurs when the remote audio state changes.
   *
   * @details
   * When the audio state of a remote user (in a voice/video call channel) or host (in a live
   * streaming channel) changes, the SDK triggers this callback to report the current state of the
   * remote audio stream.
   *
   * @note This callback does not work properly when the number of users (in the communication
   * profile) or hosts (in the live streaming channel) in a channel exceeds 32.
   *
   * @param uid The ID of the remote user whose audio state changes.
   * @param state Remote audio states.
   * - REMOTE_AUDIO_STATE_STOPPED (0): The remote audo is in the initial state. The SDK reports this
   * state in the case of `REMOTE_AUDIO_REASON_LOCAL_MUTED`, `REMOTE_AUDIO_REASON_REMOTE_MUTED`, or
   * `REMOTE_AUDIO_REASON_REMOTE_OFFLINE`.
   * - REMOTE_AUDIO_STATE_STARTING (1): The first remote audio packet is received.
   * - REMOTE_AUDIO_STATE_DECODING (2): The remote audio stream is decoded and plays normally. The SDK
   * reports this state in the case of `REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY`,
   * `REMOTE_AUDIO_REASON_LOCAL_UNMUTED` or `REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED`.
   * - REMOTE_AUDIO_STATE_FROZEN (3): The remote audio is frozen. The SDK reports this state in the
   * case of `REMOTE_AUDIO_REASON_NETWORK_CONGESTION`.
   * - REMOTE_AUDIO_STATE_FAILED (4): The remote audio fails to start. The SDK reports this state in
   * the case of `REMOTE_VIDEO_STATE_REASON_INTERNAL`.
   * @param reason The reason of the remote audio state change.
   * - REMOTE_AUDIO_REASON_INTERNAL (0): The SDK reports this reason when the audio state changes.
   * - REMOTE_AUDIO_REASON_NETWORK_CONGESTION (1): Network congestion.
   * - REMOTE_AUDIO_REASON_NETWORK_RECOVERY (2): Network recovery.
   * - REMOTE_AUDIO_REASON_LOCAL_MUTED (3): The local user stops receiving the remote audio stream or
   * disables the audio module.
   * - REMOTE_AUDIO_REASON_LOCAL_UNMUTED (4): The local user resumes receiving the remote audio stream
   * or enables the audio module.
   * - REMOTE_AUDIO_REASON_REMOTE_MUTED (5): The remote user stops sending the audio stream or
   * disables the audio module.
   * - REMOTE_AUDIO_REASON_REMOTE_UNMUTED (6): The remote user resumes sending the audio stream or
   * enables the audio module.
   * - REMOTE_AUDIO_REASON_REMOTE_OFFLINE (7): The remote user leaves the channel.
   * @param elapsed Time elapsed (ms) from the local user calling the `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` method until
   * the SDK triggers this callback.
   *
   */
  public void onRemoteAudioStateChanged(int uid, int state, int reason, int elapsed) {}

  /**
   * @brief Occurs when the audio publishing state changes.
   *
   * @param channel The channel name.
   * @param oldState The previous publishing state.
   * - PUB_STATE_IDLE(0): The initial publishing state after joining the channel.
   * - PUB_STATE_NO_PUBLISHED(1): Fails to publish the local stream.
   * - PUB_STATE_PUBLISHING(2): Publishing the local stream.
   * - PUB_STATE_PUBLISHED(3): Successfully publishes the local stream.
   * @param newState The current publishing state. 
   * - PUB_STATE_IDLE(0): The initial publishing state after joining the channel.
   * - PUB_STATE_NO_PUBLISHED(1): Fails to publish the local stream.
   * - PUB_STATE_PUBLISHING(2): Publishing the local stream.
   * - PUB_STATE_PUBLISHED(3): Successfully publishes the local stream.
   * @param elapseSinceLastState The time elapsed (ms) from the previous state to the current state.
   *
   */
  public void onAudioPublishStateChanged(
      String channel, int oldState, int newState, int elapseSinceLastState) {}

  /**
   * @brief Occurs when the video publishing state changes.
   *
   * @param channel The channel name.
   * @param source The type of the video source. See `VideoSourceType`.
   * @param oldState The previous publishing state.
   * - PUB_STATE_IDLE(0): The initial publishing state after joining the channel.
   * - PUB_STATE_NO_PUBLISHED(1): Fails to publish the local stream.
   * - PUB_STATE_PUBLISHING(2): Publishing the local stream.
   * - PUB_STATE_PUBLISHED(3): Successfully publishes the local stream.
   * @param newState The current publishing state.
   * - PUB_STATE_IDLE(0): The initial publishing state after joining the channel.
   * - PUB_STATE_NO_PUBLISHED(1): Fails to publish the local stream.
   * - PUB_STATE_PUBLISHING(2): Publishing the local stream.
   * - PUB_STATE_PUBLISHED(3): Successfully publishes the local stream.
   * @param elapseSinceLastState The time elapsed (ms) from the previous state to the current state.
   *
   */
  public void onVideoPublishStateChanged(Constants.VideoSourceType source, String channel,
      int oldState, int newState, int elapseSinceLastState) {}

  /**
   * @brief Occurs when the audio subscribing state changes.
   *
   * @param channel The channel name.
   * @param uid The user ID of the remote user.
   * @param oldState The previous subscription status.
   * - SUB_STATE_IDLE (0): The initial subscribing state after joining the channel.
   * - SUB_STATE_NO_SUBSCRIBED (1): Fails to subscribe to the remote stream. Possible reasons:
   *   - The remote user:
   *     - Calls `muteLocalAudioStream` (`true`) to stop sending the local audio stream.
   *     - Calls `disableAudio` to disable the local audio module.
   *     - Calls `enableLocalAudio` `false`) to disable the local audio capture.
   *     - The role of the remote user is audience.
   *   - The local user calls the following methods to stop receiving the remote audio streams:
   *     - Sets `autoSubscribeAudio` to `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` or
   * `updateChannelMediaOptions`, which means not to automatically subscribe to any audio streams.
   *     - Sets `muted` to `true` when calling `muteRemoteAudioStream` or `muteAllRemoteAudioStreams`,
   * which means stopping receiving the remote video streams.
   * - SUB_STATE_SUBSCRIBING (2): Subscribing.
   * - SUB_STATE_SUBSCRIBED (3): The remote stream is received, and the subscription is successful.
   * @param newState The current subscribing status.
   * - SUB_STATE_IDLE (0): The initial subscribing state after joining the channel.
   * - SUB_STATE_NO_SUBSCRIBED (1): Fails to subscribe to the remote stream. Possible reasons:
   *   - The remote user:
   *     - Calls `muteLocalAudioStream` (`true`) to stop sending the local audio stream.
   *     - Calls `disableAudio` to disable the local audio module.
   *     - Calls `enableLocalAudio` `false`) to disable the local audio capture.
   *     - The role of the remote user is audience.
   *   - The local user calls the following methods to stop receiving the remote audio streams:
   *     - Sets `autoSubscribeAudio` to `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` or
   * `updateChannelMediaOptions`, which means not to automatically subscribe to any audio streams.
   *     - Sets `muted` to `true` when calling `muteRemoteAudioStream` or `muteAllRemoteAudioStreams`,
   * which means stopping receiving the remote video streams.
   * - SUB_STATE_SUBSCRIBING (2): Subscribing.
   * - SUB_STATE_SUBSCRIBED (3): The remote stream is received, and the subscription is successful.
   * @param elapseSinceLastState The time elapsed (ms) from the previous state to the current state.
   *
   */
  public void onAudioSubscribeStateChanged(
      String channel, int uid, int oldState, int newState, int elapseSinceLastState) {}

  /**
   * @brief Occurs when the video subscribing state changes.
   *
   * @param channel The channel name.
   * @param uid The user ID of the remote user.
   * @param oldState The previous subscription status.
   * - SUB_STATE_IDLE (0): The initial subscribing state after joining the channel.
   * - SUB_STATE_NO_SUBSCRIBED (1): Fails to subscribe to the remote stream. Possible reasons:
   *   - The remote user:
   *     - Calls `muteLocalVideoStream` (`true`) to stop sending the local video streams.
   *     - Calls `disableVideo` to disable the local video module.
   *     - Calls `enableLocalVideo` (`false`) to disable the local video capture.
   *     - The role of the remote user is audience.
   *   - The local user calls the following methods to stop receiving the remote media stream:
   *     - Sets `autoSubscribeVideo` to `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` or
   * `updateChannelMediaOptions`, which means not to automatically subscribe to any video streams.
   *     - Sets `muted` to `true` when calling `muteRemoteVideoStream` or `muteAllRemoteVideoStreams`,
   * which means stopping receiving the remote video streams.
   * - SUB_STATE_SUBSCRIBING (2): Subscribing.
   * - SUB_STATE_SUBSCRIBED (3): The remote stream is received, and the subscription is successful.
   * @param newState The current subscribing status.
   * - SUB_STATE_IDLE (0): The initial subscribing state after joining the channel.
   * - SUB_STATE_NO_SUBSCRIBED (1): Fails to subscribe to the remote stream. Possible reasons:
   *   - The remote user:
   *     - Calls `muteLocalVideoStream` (`true`) to stop sending the local video streams.
   *     - Calls `disableVideo` to disable the local video module.
   *     - Calls `enableLocalVideo` (`false`) to disable the local video capture.
   *     - The role of the remote user is audience.
   *   - The local user calls the following methods to stop receiving the remote media stream:
   *     - Sets `autoSubscribeVideo` to `false` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` or
   * `updateChannelMediaOptions`, which means not to automatically subscribe to any video streams.
   *     - Sets `muted` to `true` when calling `muteRemoteVideoStream` or `muteAllRemoteVideoStreams`,
   * which means stopping receiving the remote video streams.
   * - SUB_STATE_SUBSCRIBING (2): Subscribing.
   * - SUB_STATE_SUBSCRIBED (3): The remote stream is received, and the subscription is successful.
   * @param elapseSinceLastState The time elapsed (ms) from the previous state to the current state.
   *
   */
  public void onVideoSubscribeStateChanged(
      String channel, int uid, int oldState, int newState, int elapseSinceLastState) {}

  /**
   * @brief Occurs when the remote video stream state changes.
   *
   * @note This callback does not work properly when the number of users (in the communication
   * profile) or hosts (in the live streaming channel) in a channel exceeds 32.
   *
   * @param uid The ID of the remote user whose video state changes.
   * @param state The state of the remote video:
   * - REMOTE_VIDEO_STATE_STOPPED (0): The remote video is in the initial state. This state is
   * reported in the case of `REMOTE_VIDEO_STATE_REASON_LOCAL_MUTED` (3),
   * `REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED` (5), or `REMOTE_VIDEO_STATE_REASON_REMOTE_OFFLINE` (7).
   * - REMOTE_VIDEO_STATE_STARTING (1): The first remote video packet is received.
   * - REMOTE_VIDEO_STATE_DECODING (2): The remote video stream is decoded and plays normally. This
   * state is reported in the case of `REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY` (2),
   * `REMOTE_VIDEO_STATE_REASON_LOCAL_UNMUTED` (4), `REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED` (6), or
   * `REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK_RECOVERY` (9).
   * - REMOTE_VIDEO_STATE_FROZEN (3): The remote video is frozen. This state is reported in the case
   * of `REMOTE_VIDEO_STATE_REASON_NETWORK_CONGESTION` (1) or
   * `REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK` (8).
   * - REMOTE_VIDEO_STATE_FAILED (4): The remote video fails to start. The SDK reports this state in
   * the case of `REMOTE_VIDEO_STATE_REASON_INTERNAL` (0).
   * @param reason The reason for the remote video state change:
   * - REMOTE_VIDEO_STATE_REASON_INTERNAL (0): Internal reasons.
   * - REMOTE_VIDEO_STATE_REASON_NETWORK_CONGESTION (1): Network congestion.
   * - REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY (2): Network recovery.
   * - REMOTE_VIDEO_STATE_REASON_LOCAL_MUTED (3): The local user stops receiving the remote video
   * stream or disables the video module.
   * - REMOTE_VIDEO_STATE_REASON_LOCAL_UNMUTED (4): The local user resumes receiving the remote video
   * stream or enables the video module.
   * - REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED (5): The remote user stops sending the video stream or
   * disables the video module.
   * - REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED (6): The remote user resumes sending the video stream
   * or enables the video module.
   * - REMOTE_VIDEO_STATE_REASON_REMOTE_OFFLINE (7): The remote user leaves the channel.
   * - REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK (8): The remote media stream falls back to the
   * audio-only stream due to poor network conditions.
   * - REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK_RECOVERY (9): The remote media stream switches back to
   * the video stream after the network conditions improve.
   * - REMOTE_VIDEO_STATE_REASON_CODEC_NOT_SUPPORT (13): The local video decoder does not support
   * decoding the remote video stream.
   * @param elapsed Time elapsed (ms) from the local user calling the `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` method until
   * the SDK triggers this callback.
   *
   */
  public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {}

  /**
   * @brief Occurs when the state of the media stream relay changes.
   *
   * @details
   * The SDK returns the state of the current media relay with any error message.
   *
   * @param state The state code:
   * - RELAY_STATE_IDLE (0): The SDK is initializing.
   * - RELAY_STATE_CONNECTING (1): The SDK tries to relay the media stream to the destination channel.
   * - RELAY_STATE_RUNNING (2): The SDK successfully relays the media stream to the destination
   * channel.
   * - RELAY_STATE_FAILURE (3): An error occurs. See `code` for the error code.
   * @param code The error code of the channel media relay.
   * - RELAY_OK (0): Everything is normal.
   * - RELAY_ERROR_SERVER_ERROR_RESPONSE (1): An error occurs in the server response.
   * - RELAY_ERROR_SERVER_NO_RESPONSE (2): No server response. This error may be caused by poor
   * network connections. If this error occurs when initiating a channel media relay, you can try
   * again later; if this error occurs during channel media relay, you can call `leaveChannel(LeaveChannelOptions options)`
   * to leave the channel. This error can also occur if the channel media relay service is not enabled
   * in the project. You can contact `technical support` to enable the service.
   * - RELAY_ERROR_NO_RESOURCE_AVAILABLE (3): The SDK fails to access the service, probably due to
   * limited resources of the server.
   * - RELAY_ERROR_FAILED_JOIN_SRC 4: The server fails to send the relay request.
   * - RELAY_ERROR_FAILED_JOIN_DEST 5: The server fails to accept the relay request.
   * - RELAY_ERROR_FAILED_PACKET_RECEIVED_FROM_SRC 6: The server fails to receive the media stream.
   * - RELAY_ERROR_FAILED_PACKET_SENT_TO_DEST 7: The server fails to send the media stream.
   * - RELAY_ERROR_SERVER_CONNECTION_LOST (8): The SDK disconnects from the server due to poor network
   * connections. You can call `leaveChannel(LeaveChannelOptions options)` to leave the channel.
   * - RELAY_ERROR_INTERNAL_ERROR (9): An internal error occurs in the server.
   * - RELAY_ERROR_SRC_TOKEN_EXPIRED (10): The token of the source channel has expired.
   * - RELAY_ERROR_DEST_TOKEN_EXPIRED (11): The token of the destination channel has expired.
   *
   */
  public void onChannelMediaRelayStateChanged(int state, int code) {}

  /**
   * @brief Occurs when the first audio frame is published.
   *
   * @details
   * The SDK triggers this callback under one of the following circumstances:
   * - The local client enables the audio module and calls `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` successfully.
   * - The local client calls `muteLocalAudioStream` (`true`) and `muteLocalAudioStream` (`false`) in
   * sequence.
   * - The local client calls `disableAudio` and `enableAudio` in sequence.
   * - The local client calls `pushExternalAudioFrame` to successfully push the audio frame to the
   * SDK.
   *
   * @param elapsed Time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the SDK
   * triggers this callback.
   *
   */
  public void onFirstLocalAudioFramePublished(int elapsed) {}

  /**
   * @brief Occurs when the SDK receives the first audio frame from a specific remote user.
   *
   * @param uid The user ID of the remote user.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the
   * SDK triggers this callback.
   *
   */
  public void onFirstRemoteAudioFrame(int uid, int elapsed) {}

  /**
   * @brief Occurs when the SDK decodes the first remote audio frame for playback.
   *
   * @details
   * The SDK triggers this callback under one of the following circumstances:
   * - The remote user joins the channel and sends the audio stream for the first time.
   * - The remote user's audio is offline and then goes online to re-send audio. It means the local
   * user cannot receive audio in 15 seconds. Reasons for such an interruption include:
   *   - The remote user leaves channel.
   *   - The remote user drops offline.
   *   - The remote user calls `muteLocalAudioStream` to stop sending the audio stream.
   *   - The remote user calls `disableAudio` to disable audio.
   *
   * @param uid The user ID of the remote user.
   * @param elapsed The time elapsed (ms) from the local user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until the
   * SDK triggers this callback.
   *
   */
  public void onFirstRemoteAudioDecoded(int uid, int elapsed) {}

  /**
   * @brief Occurs when the remote media stream falls back to the audio-only stream due to poor
   * network conditions or switches back to the video stream after the network conditions improve.
   *
   * @details
   * If you call `setRemoteSubscribeFallbackOption(int option)` and set `option` to
   * `STREAM_FALLBACK_OPTION_AUDIO_ONLY`, the SDK triggers this callback in the following situations:
   * - The downstream network condition is poor, and the subscribed video stream is downgraded to
   * audio-only stream.
   * - The downstream network condition has improved, and the subscribed stream has been restored to
   * video stream.
   *
   * @note Once the remote media stream switches to the low-quality video stream due to weak network
   * conditions, you can monitor the stream switch between a high-quality and low-quality stream in
   * the `onRemoteVideoStats` callback.
   *
   * @param uid The user ID of the remote user.
   * @param isFallbackOrRecover - `true`: The subscribed media stream falls back to audio-only due to
   * poor network conditions.
   * - `false`: The subscribed media stream switches back to the video stream after the network
   * conditions improve.
   *
   */
  public void onRemoteSubscribeFallbackToAudioOnly(int uid, boolean isFallbackOrRecover) {}

  /**
   * @brief Occurs when the local audio route changes.
   *
   * @param routing The current audio routing.
   * - AUDIO_ROUTE_DEFAULT (-1): The default audio route.
   * - AUDIO_ROUTE_HEADSET (0): The audio route is a headset with a microphone.
   * - AUDIO_ROUTE_EARPIECE (1): The audio route is an earpiece.
   * - AUDIO_ROUTE_HEADSETNOMIC (2): The audio route is a headset without a microphone.
   * - AUDIO_ROUTE_SPEAKERPHONE (3): The audio route is the speaker that comes with the device.
   * - AUDIO_ROUTE_LOUDSPEAKER (4): (For future use) The audio route is an external speaker.
   * - AUDIO_ROUTE_BLUETOOTH_DEVICE_HFP (5): The audio route is a Bluetooth device using the HFP
   * protocol.
   * - AUDIO_ROUTE_BLUETOOTH_DEVICE_A2DP (10): The audio route is a Bluetooth device using the A2DP
   * protocol.
   *
   */
  public void onAudioRouteChanged(int routing) {}

  /**
   * @brief Occurs when the camera turns on and is ready to capture the video.
   *
   * @deprecated Use {@link onLocalVideoStateChanged(VideoSourceType, int, int)
   * onLocalVideoStateChanged} instead.
   *
   * @details
   * This callback indicates that the camera has been successfully turned on and you can start to
   * capture video.
   *
   */
  @Deprecated
  public void onCameraReady() {}

  /**
   * @brief Occurs when the camera focus area changes.
   *
   * @details
   * The SDK triggers this callback when the local user changes the camera focus position by calling
   * `setCameraFocusPositionInPreview`.
   *
   * @param rect The focus rectangle in the local preview. See `Rect`.
   *
   */
  public void onCameraFocusAreaChanged(Rect rect) {}

  /**
   * @brief Occurs when the camera exposure area changes.
   *
   * @details
   * The SDK triggers this callback when the local user changes the camera exposure position by
   * calling `setCameraExposurePosition`.
   *
   * @param rect The focus rectangle in the local preview. See `Rect`.
   *
   */
  public void onCameraExposureAreaChanged(Rect rect) {}

  /**
   * @brief Reports the result of taking a video snapshot.
   *
   * @details
   * After a successful `takeSnapshot(int uid, String filePath)` method call, the SDK triggers this callback to report
   * whether the snapshot is successfully taken as well as the details for the snapshot taken.
   *
   * @param uid The user ID. One `uid` of 0 indicates the local user.
   * @param filePath The local path of the snapshot.
   * @param width The width (px) of the snapshot.
   * @param height The height (px) of the snapshot.
   * @param errCode The message that confirms success or gives the reason why the snapshot is not
   * successfully taken:
   * - 0: Success.
   * - < 0: Failure:
   *   - -1: The SDK fails to write data to a file or encode a JPEG image.
   *   - -2: The SDK does not find the video stream of the specified user within one second after the
   * `takeSnapshot(int uid, String filePath)` method call succeeds. The possible reasons are: local capture stops, remote
   * end stops publishing, or video data processing is blocked.
   *   - -3: Calling the `takeSnapshot(int uid, String filePath)` method too frequently.
   *
   */
  public void onSnapshotTaken(int uid, String filePath, int width, int height, int errCode) {}

  /**
   * @brief Reports the face detection result of the local user.
   *
   * @details
   * Once you enable face detection by calling `enableFaceDetection` `(true)`, you can get the
   * following information on the local user in real-time:
   * - The width and height of the local video.
   * - The position of the human face in the local view.
   * - The distance between the human face and the screen.
   * This value is based on the fitting calculation of the local video size and the position of the
   * human face.
   *
   * @note
   * - When it is detected that the face in front of the camera disappears, the callback will be
   * triggered immediately. When no human face is detected, the frequency of this callback to be
   * triggered wil be decreased to reduce power consumption on the local device.
   * - The SDK stops triggering this callback when a human face is in close proximity to the screen.
   * - On Android, the value of distance reported in this callback may be slightly different from the
   * actual `distance`. Therefore, Agora does not recommend using it for accurate calculation.
   *
   * @param imageWidth The width (px) of the video image captured by the local camera.
   * @param imageHeight The height (px) of the video image captured by the local camera.
   * @param faceRectArr Information of the detected face. See `AgoraFacePositionInfo`. The number of
   * `AgoraFacePositionInfo` array reported in this callback is based on the faces detected. The
   * length of the array can be 0, which means that no human face is detected in front of the camera.
   *
   */
  public void onFacePositionChanged(
      int imageWidth, int imageHeight, AgoraFacePositionInfo[] faceRectArr) {}

  /**
   * @brief Reports the statistics of the audio stream sent by each remote user.
   *
   * @details
   * The SDK triggers this callback once every two seconds to report the audio quality of each remote
   * user who is sending an audio stream. If a channel has multiple users sending audio streams, the
   * SDK triggers this callback as many times.
   *
   * @param uid The user ID of the remote user sending the audio stream.
   * @param quality Audio quality of the user.
   * - QUALITY_UNKNOWN (0): The quality is unknown.
   * - QUALITY_EXCELLENT (1): The quality is excellent.
   * - QUALITY_GOOD (2): The network quality seems excellent, but the bitrate can be slightly lower
   * than excellent.
   * - QUALITY_POOR (3): Users can feel the communication is slightly impaired.
   * - QUALITY_BAD (4): Users cannot communicate smoothly.
   * - QUALITY_VBAD (5): The quality is so bad that users can barely communicate.
   * - QUALITY_DOWN (6): The network is down, and users cannot communicate at all.
   * - QUALITY_DETECTING (8): The last-mile probe test is in progress.
   * @param delay The network delay (ms) from the sender to the receiver, including the delay caused
   * by audio sampling pre-processing, network transmission, and network jitter buffering.
   * @param lost The packet loss rate (%) of the audio packet sent from the remote user to the
   * receiver.
   *
   */
  public void onAudioQuality(int uid, int quality, short delay, short lost) {}

  /**
   * @brief Reports the statistics about the current call.
   *
   * @details
   * Call timing: The SDK triggers this callback once every two seconds after the user joins the
   * channel.
   *
   * @param stats Statistics of the RTC engine. See `RtcStats`.
   *
   */
  public void onRtcStats(RtcStats stats) {}

  /**
   * @brief Reports the last-mile network quality of the local user.
   *
   * @details
   * This callback reports the last-mile network conditions of the local user before the user joins
   * the channel. Last mile refers to the connection between the local device and Agora's edge server.
   * Before the user joins the channel, this callback is triggered by the SDK once
   * `startLastmileProbeTest` is called and reports the last-mile network conditions of the local
   * user.
   *
   * @param quality The last-mile network quality.
   * - QUALITY_UNKNOWN (0): The quality is unknown.
   * - QUALITY_EXCELLENT (1): The quality is excellent.
   * - QUALITY_GOOD (2): The network quality seems excellent, but the bitrate can be slightly lower
   * than excellent.
   * - QUALITY_POOR (3): Users can feel the communication is slightly impaired.
   * - QUALITY_BAD (4): Users cannot communicate smoothly.
   * - QUALITY_VBAD (5): The quality is so bad that users can barely communicate.
   * - QUALITY_DOWN (6): The network is down, and users cannot communicate at all.
   * - QUALITY_DETECTING (8): The last-mile probe test is in progress.
   *
   */
  public void onLastmileQuality(int quality) {}

  /**
   * @brief Reports the last mile network probe result.
   *
   * @details
   * The SDK triggers this callback within 30 seconds after the app calls `startLastmileProbeTest`.
   *
   * @param result The uplink and downlink last-mile network probe test result. See
   * `LastmileProbeResult`.
   *
   */
  public void onLastmileProbeResult(LastmileProbeResult result) {}

  /**
   * @brief Reports the last mile network quality of each user in the channel.
   *
   * @details
   * This callback reports the last mile network conditions of each user in the channel. Last mile
   * refers to the connection between the local device and Agora's edge server.
   * The SDK triggers this callback once every two seconds. If a channel includes multiple users, the
   * SDK triggers this callback as many times.
   * This callback provides feedback on network quality through sending and receiving broadcast
   * packets within the channel. Excessive broadcast packets can lead to broadcast storms. To prevent
   * broadcast storms from causing a large amount of data transmission within the channel, this
   * callback supports feedback on the network quality of up to 4 remote hosts simultaneously by
   * default.
   *
   * @note `txQuality` is `UNKNOWN` when the user is not sending a stream; `rxQuality` is `UNKNOWN`
   * when the user is not receiving a stream.
   *
   * @param uid The user ID. The network quality of the user with this user ID is reported. If the uid
   * is 0, the local network quality is reported.
   * @param txQuality Uplink network quality rating of the user in terms of the transmission bit rate,
   * packet loss rate, average RTT (Round-Trip Time) and jitter of the uplink network. This parameter
   * is a quality rating helping you understand how well the current uplink network conditions can
   * support the selected video encoder configuration. For example, a 1000 Kbps uplink network may be
   * adequate for video frames with a resolution of 640 × 480 and a frame rate of 15 fps in the
   * LIVE_BROADCASTING profile, but might be inadequate for resolutions higher than 1280 × 720.
   * - QUALITY_UNKNOWN (0): The quality is unknown.
   * - QUALITY_EXCELLENT (1): The quality is excellent.
   * - QUALITY_GOOD (2): The network quality seems excellent, but the bitrate can be slightly lower
   * than excellent.
   * - QUALITY_POOR (3): Users can feel the communication is slightly impaired.
   * - QUALITY_BAD (4): Users cannot communicate smoothly.
   * - QUALITY_VBAD (5): The quality is so bad that users can barely communicate.
   * - QUALITY_DOWN (6): The network is down, and users cannot communicate at all.
   * - QUALITY_DETECTING (8): The last-mile probe test is in progress.
   * @param rxQuality Downlink network quality rating of the user in terms of packet loss rate,
   * average RTT, and jitter of the downlink network.
   * - QUALITY_UNKNOWN (0): The quality is unknown.
   * - QUALITY_EXCELLENT (1): The quality is excellent.
   * - QUALITY_GOOD (2): The network quality seems excellent, but the bitrate can be slightly lower
   * than excellent.
   * - QUALITY_POOR (3): Users can feel the communication is slightly impaired.
   * - QUALITY_BAD (4): Users cannot communicate smoothly.
   * - QUALITY_VBAD (5): The quality is so bad that users can barely communicate.
   * - QUALITY_DOWN (6): The network is down, and users cannot communicate at all.
   * - QUALITY_DETECTING (8): The last-mile probe test is in progress.
   *
   */
  public void onNetworkQuality(int uid, int txQuality, int rxQuality) {}

  /**
   * @brief Reports the statistics of the local audio stream.
   *
   * @details
   * The SDK triggers this callback once every two seconds.
   *
   * @param stats Local audio statistics. See `LocalAudioStats`.
   *
   */
  public void onLocalAudioStats(LocalAudioStats stats) {}

  /**
   * @brief Reports the statistics of the local video stream.
   *
   * @details
   * The SDK triggers this callback once every two seconds to report the statistics of the local video
   * stream.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param stats The statistics of the local video stream. See `LocalVideoStats`.
   *
   */
  public void onLocalVideoStats(Constants.VideoSourceType source, LocalVideoStats stats) {}

  /**
   * @brief Reports the transport-layer statistics of each remote audio stream.
   *
   * @details
   * The SDK triggers this callback once every two seconds for each remote user who is sending audio
   * streams. If a channel includes multiple remote users, the SDK triggers this callback as many
   * times.
   *
   * @param stats The statistics of the received remote audio streams. See `RemoteAudioStats`.
   *
   */
  public void onRemoteAudioStats(RemoteAudioStats stats) {}

  /**
   * @brief Reports the statistics of the video stream sent by each remote users.
   *
   * @details
   * Reports the statistics of the video stream from the remote users. The SDK triggers this callback
   * once every two seconds for each remote user. If a channel has multiple users/hosts sending video
   * streams, the SDK triggers this callback as many times.
   *
   * @param stats Statistics of the remote video stream. See `RemoteVideoStats`.
   *
   */
  public void onRemoteVideoStats(RemoteVideoStats stats) {}

  /**
   * @deprecated
   * The statistics of the uploading local video streams once every two seconds.
   * @param sentBitrate Data sending bitrate (kbit/s) since last count.
   * @param sentFrameRate Data sending frame rate (fps) since last count.
   */
  public void onLocalVideoStat(int sentBitrate, int sentFrameRate) {}

  /**
   * @deprecated
   * The statistics of receiving remote video streams once every two seconds.
   *
   * @param uid User ID of the user whose video streams are received.
   * @param delay Time delay (ms).
   * @param receivedBitrate Data receiving bitrate (kbit/s).
   * @param receivedFrameRate Data receiving frame rate (fps).
   */
  public void onRemoteVideoStat(int uid, int delay, int receivedBitrate, int receivedFrameRate) {}

  /**
   * @brief Reports the transport-layer statistics of each remote audio stream.
   *
   * @deprecated Use {@link  onRemoteAudioStats(RemoteAudioStats) onRemoteAudioStats} instead.
   *
   * @details
   * This callback reports the transport-layer statistics, such as the packet loss rate and network
   * time delay after the local user receives an audio packet from a remote user. During a call, when
   * the user receives the audio packet sent by the remote user, the callback is triggered every 2
   * seconds.
   *
   * @param uid The ID of the remote user sending the audio streams.
   * @param delay The network delay (ms) from the remote user to the receiver.
   * @param lost The packet loss rate (%) of the audio packet sent from the remote user to the
   * receiver.
   * @param rxKBitrate The bitrate of the received audio (Kbps).
   *
   */
  @Deprecated
  public void onRemoteAudioTransportStats(int uid, int delay, int lost, int rxKBitRate) {}

  /**
   * @brief Reports the transport-layer statistics of each remote video stream.
   *
   * @deprecated Use {@link  onRemoteVideoStats(RemoteVideoStats) onRemoteVideoStats} instead.
   *
   * @details
   * This callback reports the transport-layer statistics, such as the packet loss rate and network
   * time delay after the local user receives a video packet from a remote user.
   * During a call, when the user receives the video packet sent by the remote user/host, the callback
   * is triggered every 2 seconds.
   *
   * @param uid The ID of the remote user sending the video packets.
   * @param delay The network delay (ms) from the sender to the receiver.
   * @param lost The packet loss rate (%) of the video packet sent from the remote user.
   * @param rxKBitRate The bitrate of the received video (Kbps).
   *
   */
  @Deprecated
  public void onRemoteVideoTransportStats(int uid, int delay, int lost, int rxKBitRate) {}

  /**
   * @brief Occurs when the playback state of the music file changes.
   *
   * @details
   * This callback occurs when the playback state of the music file changes, and reports the current
   * state and error code.
   *
   * @param state The playback state of the music file.
   * - AUDIO_MIXING_STATE_PLAYING (710): The music file is playing.
   * - AUDIO_MIXING_STATE_PAUSED (711): The music file pauses playing.
   * - AUDIO_MIXING_STATE_STOPPED (713): The music file stops playing.
   * - AUDIO_MIXING_STATE_FAILED (714): An exception occurs when playing the audio mixing file. The
   * SDK returns the specific reason in the `reasonCode` parameter.
   * @param reason Error code.
   * - AUDIO_MIXING_REASON_OK (0): No error.
   * - AUDIO_MIXING_REASON_CAN_NOT_OPEN (701): The SDK cannot open the music file.
   * - AUDIO_MIXING_REASON_TOO_FREQUENT_CALL (702): The SDK opens the music file too frequently.
   * - AUDIO_MIXING_REASON_INTERRUPTED_EOF (703): The music file playback is interrupted.
   * - AUDIO_MIXING_REASON_ONE_LOOP_COMPLETED (721): The music file completes a loop playback.
   * - AUDIO_MIXING_REASON_ALL_LOOPS_COMPLETED (723): The music file completes all loop playback.
   * - AUDIO_MIXING_REASON_STOPPED_BY_USER (724): The music file pauses playing by calling
   * `stopAudioMixing`.
   *
   */
  public void onAudioMixingStateChanged(int state, int reason) {}

  /**
   * @brief Reports the playback progress of a music file.
   *
   * @details
   * After you called the `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` method to play a music file, the SDK triggers this
   * callback every two seconds to report the playback progress.
   *
   * @param position The playback progress (ms).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  public void onAudioMixingPositionChanged(long position) {}

  /**
   * @brief Occurs when the playback of the local music file finishes.
   *
   * @deprecated Use {@link  onAudioMixingStateChanged(int, int) onAudioMixingStateChanged} instead.
   *
   * @details
   * After you call `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` to play a local music file, this callback occurs when the
   * playback finishes. If the call of `startAudioMixing(String filePath, boolean loopback, int cycle, int startPos)` fails, the error code
   * `WARN_AUDIO_MIXING_OPEN_ERROR` is returned.
   *
   */
  @Deprecated
  public void onAudioMixingFinished() {}

  /**
   * @brief Occurs when the playback of the local music file finishes.
   *
   * @details
   * This callback occurs when the local audio effect file finishes playing.
   *
   * @param soundId The ID of the audio effect. The unique ID of each audio effect file.
   *
   */
  public void onAudioEffectFinished(int soundId) {}

  /**
   * @brief Occurs when the state of virtual metronome changes.
   *
   * @details
   * When the state of the virtual metronome changes, the SDK triggers this callback to report the
   * current state of the virtual metronome. This callback indicates the state of the local audio
   * stream and enables you to troubleshoot issues when audio exceptions occur.
   *
   * @param state The current virtual metronome state.
   * - RHYTHM_PLAYER_STATE_IDLE (810): The virtual metronome is not enabled or is already disabled.
   * - RHYTHM_PLAYER_STATE_OPENING (811): Opening the beat files.
   * - RHYTHM_PLAYER_STATE_DECODING (812): Decoding the beat files.
   * - RHYTHM_PLAYER_STATE_PLAYING (813): The beat files are playing.
   * - RHYTHM_PLAYER_STATE_FAILED (814): Failed to start virtual metronome. You can use the reported
   * `errorCode` to troubleshoot the cause of the error, or you can try to start the virtual metronome
   * again.
   * @param reason Error codes and error messages for virtual metronome errors.
   * - RHYTHM_PLAYER_REASON_OK (0): The beat files are played normally without errors.
   * - RHYTHM_PLAYER_REASON_FAILED (1): General error with no clear cause.
   * - RHYTHM_PLAYER_REASON_CAN_NOT_OPEN (801): There is an error when opening the beat files.
   * - RHYTHM_PLAYER_REASON_CAN_NOT_PLAY (802): There is an error when playing beat files.
   * - RHYTHM_PLAYER_REASON_FILE_OVER_DURATION_LIMIT (803): The duration of the beat audio file
   * exceeds the limit. The maximum duration is 1.2 seconds.
   *
   */
  public void onRhythmPlayerStateChanged(int state, int reason) {}

  /**
   * @brief Occurs when the local audio stream state changes.
   *
   * @details
   * When the state of the local audio stream changes (including the state of the audio capture and
   * encoding), the SDK triggers this callback to report the current state. This callback indicates
   * the state of the local audio stream, and allows you to troubleshoot issues when audio exceptions
   * occur.
   *
   * @note When the state is `LOCAL_AUDIO_STREAM_STATE_FAILED` (3), you can view the error information
   * in the `error` parameter.
   *
   * @param state The state of the local audio.
   * - LOCAL_AUDIO_STREAM_STATE_STOPPED (0): The local video is in the initial state.
   * - LOCAL_AUDIO_STREAM_STATE_RECORDING (1): The local video capturing device starts successfully.
   * - LOCAL_AUDIO_STREAM_STATE_ENCODING (2): The first video frame is successfully encoded.
   * - LOCAL_AUDIO_STREAM_STATE_FAILED (3): Fails to start the local video.
   * @param reason Reasons for local audio state changes.
   * - LOCAL_AUDIO_STREAM_REASON_OK (0): The local video is normal.
   * - LOCAL_AUDIO_STREAM_REASON_FAILURE (1): No specified reason for the local audio failure. Remind
   * your users to try to rejoin the channel.
   * - LOCAL_AUDIO_STREAM_REASON_DEVICE_NO_PERMISSION (2): No permission to use the local video
   * capturing device. Remind your users to grant permission.
   * - LOCAL_AUDIO_STREAM_REASON_DEVICE_BUSY (3): The microphone is in use. Remind your users to check
   * whether another application occupies the microphone. Local audio capture automatically resumes
   * after the microphone is idle for about five seconds. You can also try to rejoin the channel after
   * the microphone is idle.
   * - LOCAL_AUDIO_STREAM_REASON_CAPTURE_FAILURE (4): The local video capture failed.
   * - LOCAL_AUDIO_STREAM_REASON_ENCODE_FAILURE (5): The local video encoding fails.
   * - LOCAL_AUDIO_STREAM_REASON_INTERRUPTED (8): The local audio capture is interrupted by system
   * calls, smart assistants, or alarm clocks. Prompt your users to end the phone call, smart
   * assistants, or alarm clock if the local audio capture is required.
   *
   */
  public void onLocalAudioStateChanged(int state, int reason) {}

  /**
   * @brief Occurs when the local video stream state changes.
   *
   * @details
   * When the status of the local video changes, the SDK triggers this callback to report the current
   * local video state and the reason for the state change.
   * Applicable scenarios: You can use this callback to stay updated on the state changes of the local
   * video stream, and take corresponding measures based on the reasons for the state changes, to
   * better manage and debug issues related to the video stream.
   * Call timing: - The SDK triggeres this callback under the following circumstances, with the
   * `state` as LOCAL_VIDEO_STREAM_STATE_FAILED, and the `reason` as
   * `LOCAL_VIDEO_STREAM_REASON_CAPTURE_FAILURE`:
   *   - For Android 9 and later versions, after an app is in the background for a period, the system
   * automatically revokes camera permissions.
   *   - For Android 6 and later versions, if the camera is held by a third-party app for a certain
   * duration and then released, the SDK triggers this callback and reports the
   * `onLocalVideoStateChanged` (`LOCAL_VIDEO_STREAM_STATE_CAPTURING,LOCAL_VIDEO_STREAM_REASON_OK`)
   * callback.
   *   - The camera starts normally, but does not output video frames for four consecutive seconds.
   * - When the camera outputs captured video frames, if the SDK detects 15 consecutive duplicate
   * video frames, it triggers this callback, with the `state` as `LOCAL_VIDEO_STREAM_STATE_CAPTURING`
   * and the `reason` as `LOCAL_VIDEO_STREAM_REASON_CAPTURE_FAILURE`.Note:
   * - Note that the video frame duplication detection is only available for video frames with a
   * resolution greater than 200 × 200, a frame rate greater than or equal to 10 fps, and a bitrate
   * less than 20 Kbps.
   * - Normally, if there is an error in video capturing, the issue can be troubleshooted through the
   * `reason` parameter in this callback. However, on some devices, when there is an issue with
   * capturing (such as freezing), the Android system will not throw any error callbacks, so the SDK
   * cannot report the reason for the change in local video status. In this case, you can determine if
   * there is no video frame being captured by checking the following: this callback reports the
   * `state` as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` or `LOCAL_VIDEO_STREAM_STATE_ENCODING`, and the
   * `captureFrameRate` in the `onLocalVideoStats` callback is 0.
   *
   * @note
   * - Note that the video frame duplication detection is only available for video frames with a
   * resolution greater than 200 × 200, a frame rate greater than or equal to 10 fps, and a bitrate
   * less than 20 Kbps.
   * - Normally, if there is an error in video capturing, the issue can be troubleshooted through the
   * `reason` parameter in this callback. However, on some devices, when there is an issue with
   * capturing (such as freezing), the Android system will not throw any error callbacks, so the SDK
   * cannot report the reason for the change in local video status. In this case, you can determine if
   * there is no video frame being captured by checking the following: this callback reports the
   * `state` as `LOCAL_VIDEO_STREAM_STATE_CAPTURING` or `LOCAL_VIDEO_STREAM_STATE_ENCODING`, and the
   * `captureFrameRate` in the `onLocalVideoStats` callback is 0.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param state - LOCAL_VIDEO_STREAM_STATE_STOPPED (0): The local video is in the initial state.
   * - LOCAL_VIDEO_STREAM_STATE_CAPTURING (1): The local video capturing device starts successfully.
   * - LOCAL_VIDEO_STREAM_STATE_ENCODING (2): The first video frame is successfully encoded.
   * - LOCAL_VIDEO_STREAM_STATE_FAILED (3): Fails to start the local video.
   * @param reason - LOCAL_VIDEO_STREAM_REASON_OK (0): The local video is normal.
   * - LOCAL_VIDEO_STREAM_REASON_FAILURE (1): No specified reason for the local video failure.
   * - LOCAL_VIDEO_STREAM_REASON_DEVICE_BUSY (3): The local video capturing device is in use. Prompt
   * the user to check if the camera is being used by another app, or try to rejoin the channel.
   * - LOCAL_VIDEO_STREAM_REASON_CAPTURE_FAILURE (4): The local video capture failed. Prompt the user
   * to check whether the video capture device is working properly, whether the camera is used by
   * another app, or try to rejoin the channel.
   * - LOCAL_VIDEO_STREAM_REASON_CODEC_NOT_SUPPORT (5): The local video encoding fails.
   * - LOCAL_VIDEO_STREAM_REASON_DEVICE_NOT_FOUND (8): Fails to find a local video capture device.
   * Remind the user to check whether the camera is connected to the device properly or the camera is
   * working properly, and then to rejoin the channel.
   * - LOCAL_VIDEO_STREAM_REASON_DEVICE_INTERRUPT (14): Video capture is interrupted. Possible reasons
   * include the following:
   *   - The camera is being used by another app. Prompt the user to check if the camera is being used
   * by another app.
   *   - The device is locked, or the current app has been switched to the background. You can use
   * foreground services to notify the operating system and ensure that the app can still collect
   * video when it switches to the background.
   * - LOCAL_VIDEO_STREAM_REASON_DEVICE_FATAL_ERROR (15): The video capture device encounters an
   * error. Prompt the user to close and restart the camera to restore functionality. If this
   * operation does not solve the problem, check if the camera has a hardware failure.
   * - LOCAL_VIDEO_STREAM_REASON_SCREEN_CAPTURE_FAILURE (21): The current window being captured has no
   * data.
   *
   */
  public void onLocalVideoStateChanged(Constants.VideoSourceType source, int state, int reason) {}

  /**
   * @brief Occurs when the local video event occurs.
   *
   * @details
   * This callback is triggered when a video event occurs. You can use this callback to get the reason for such an event.
   *
   * @param source The type of the video source. See `VideoSourceType`.
   * @param event The local video event type.
   * - LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_WINDOW_HIDDEN (1): The screen capture window is hidden.
   * - LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_WINDOW_RECOVER_FROM_HIDDEN (2): The screen capture window is recovered from hidden.
   * - LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_STOPPED_BY_USER (3): The screen capture is stopped by user.
   * - LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_SYSTEM_INTERNAL_ERROR (4): An internal error occurs during the screen capture.
   * 
   */
  public void onLocalVideoEvent(Constants.VideoSourceType source, int event) {}

  /**
   * @brief Occurs when the state of Media Push changes.
   *
   * @details
   * When the state of Media Push changes, the SDK triggers this callback and reports the URL address
   * and the current state of the Media Push. This callback indicates the state of the Media Push.
   * When exceptions occur, you can troubleshoot issues by referring to the detailed error
   * descriptions in the error code parameter.
   *
   * @param url The URL address where the state of the Media Push changes.
   * @param state The current state of the Media Push:
   * - RTMP_STREAM_PUBLISH_STATE_IDLE (0): The Media Push has not started or has ended.
   * - RTMP_STREAM_PUBLISH_STATE_CONNECTING (1): The streaming server and CDN server are being
   * connected.
   * - RTMP_STREAM_PUBLISH_STATE_RUNNING (2): The Media Push publishes. The SDK successfully publishes
   * the RTMP or RTMPS streaming and returns this state.
   * - RTMP_STREAM_PUBLISH_STATE_RECOVERING (3): The Media Push is recovering. When exceptions occur
   * to the CDN, or the Media Push is interrupted, the SDK tries to resume the Media Push and returns
   * this state.
   *   - If the SDK successfully resumes the streaming, RTMP_STREAM_PUBLISH_STATE_RUNNING (2) returns.
   *   - If the streaming does not resume within 60 seconds or server errors occur,
   * RTMP_STREAM_PUBLISH_STATE_FAILURE (4) returns. You can also reconnect to the server by calling
   * the `startRtmpStreamWithTranscoding` / `startRtmpStreamWithoutTranscoding` and `stopRtmpStream`
   * methods.
   * - RTMP_STREAM_PUBLISH_STATE_FAILURE (4): The Media push fails. See the errCode parameter for the
   * detailed error information.You can also try to publish streams again.
   * - RTMP_STREAM_PUBLISH_STATE_DISCONNECTING (5): The SDK is disconnecting from the Agora Media Push
   * server and CDN server. When you call `stopRtmpStream` to stop the Media Push normally, the SDK
   * reports the Media Push state as `RTMP_STREAM_PUBLISH_STATE_DISCONNECTING` and
   * `RTMP_STREAM_PUBLISH_STATE_IDLE` in sequence.
   * @param reason Reasons for the changes in the Media Push status:
   * - RTMP_STREAM_PUBLISH_REASON_OK (0): The Media Push publishes successfully.
   * - RTMP_STREAM_PUBLISH_REASON_INVALID_ARGUMENT (1): Invalid argument used. Check the parameter
   * setting.
   * - RTMP_STREAM_PUBLISH_REASON_ENCRYPTED_STREAM_NOT_ALLOWED (2): The Media Push is encrypted and
   * cannot be published.
   * - RTMP_STREAM_PUBLISH_REASON_CONNECTION_TIMEOUT (3): Timeout for the Media Push. You can try to
   * push the stream again.
   * - RTMP_STREAM_PUBLISH_REASON_INTERNAL_SERVER_ERROR (4): An error occurs in Agora streaming
   * server. You can try to publish media streams again.
   * - RTMP_STREAM_PUBLISH_REASON_RTMP_SERVER_ERROR (5): An error occurs in the CDN server.
   * - RTMP_STREAM_PUBLISH_REASON_TOO_OFTEN (6): A reserved parameter.
   * - RTMP_STREAM_PUBLISH_REASON_REACH_LIMIT (7): The host publishes more than 10 URLs. You can stop
   * publishing sreams to unnecessary URLs.
   * - RTMP_STREAM_PUBLISH_REASON_NOT_AUTHORIZED (8): The host manipulates other hosts' URLs. For
   * example, the host updates or stops other hosts' streams. Check your app logic.
   * - RTMP_STREAM_PUBLISH_REASON_STREAM_NOT_FOUND (9): Agora's server fails to find the media stream
   * of Media Push.
   * - RTMP_STREAM_PUBLISH_REASON_FORMAT_NOT_SUPPORTED (10): The format of the media push URL is not
   * supported. Check whether the URL format is correct.
   * - RTMP_STREAM_PUBLISH_REASON_NOT_BROADCASTER (11): The user role is not host, so the user cannot
   * use the Media Push function. Check your application code logic.
   * - RTMP_STREAM_PUBLISH_REASON_TRANSCODING_NO_MIX_STREAM (13): The `updateRtmpTranscoding` method
   * is called to update the transcoding configuration in a scenario where there is Media Push without
   * transcoding. Check your application code logic.
   * - RTMP_STREAM_PUBLISH_REASON_NET_DOWN (14): Errors occurred in the host's network.
   * - RTMP_STREAM_PUBLISH_REASON_INVALID_PRIVILEGE (16): Your project does not have permission to use
   * Media Push service.
   * - RTMP_STREAM_UNPUBLISH_REASON_OK (100): The Media Push has been stopped normally. After you call
   * `stopRtmpStream` to stop the Media Push, the SDK returns this value.
   *
   */
  public void onRtmpStreamingStateChanged(String url, int state, int reason) {}

  /**
   * @brief Reports events during the Media Push.
   *
   * @since v3.1.0.
   *
   * @param url The URL for Media Push.
   * @param event The event code of Media Push.
   * - RTMP_STREAMING_EVENT_FAILED_LOAD_IMAGE (1): An error occurs when you add a background image or
   * a watermark image in the Media Push.
   * - RTMP_STREAMING_EVENT_URL_ALREADY_IN_USE (2): The streaming URL is already being used for Media
   * Push. If you want to start new streaming, use a new streaming URL.
   * - RTMP_STREAMING_EVENT_ADVANCED_FEATURE_NOT_SUPPORT (3): The feature is not supported.
   * - RTMP_STREAMING_EVENT_REQUEST_TOO_OFTEN (4): Reserved.
   *
   */
  public void onRtmpStreamingEvent(String url, int event) {}

  /**
   * @brief Occurs when the publisher's transcoding is updated.
   *
   * @details
   * When the `LiveTranscoding` class in the `startRtmpStreamWithTranscoding` method updates, the SDK
   * triggers the `onTranscodingUpdated` callback to report the update information.
   *
   * @note If you call the `startRtmpStreamWithTranscoding` method to set the `LiveTranscoding` class
   * for the first time, the SDK does not trigger this callback.
   *
   */
  public void onTranscodingUpdated() {}

  /**
   * @brief Occurs when the local user receives the data stream from the remote user.
   *
   * @details
   * The SDK triggers this callback when the local user receives the stream message that the remote
   * user sends by calling the `sendStreamMessage` method.
   *
   * @note If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   *
   * @param uid The ID of the remote user sending the message.
   * @param streamId The stream ID of the received message.
   * @param data The data received.
   *
   */
  public void onStreamMessage(int uid, int streamId, byte[] data) {}

  /**
   * @brief Occurs when the local user does not receive the data stream from the remote user.
   *
   * @details
   * The SDK triggers this callback when the local user fails to receive the stream message that the
   * remote user sends by calling the `sendStreamMessage` method.
   *
   * @note If you need a more comprehensive solution for low-latency, high-concurrency, and scalable
   * real-time messaging and status synchronization, it is recommended to use `Signaling`.
   *
   * @param uid The ID of the remote user sending the message.
   * @param streamId The stream ID of the received message.
   * @param error Error code.
   * @param missed The number of lost messages.
   * @param cached Number of incoming cached messages when the data stream is interrupted.
   *
   */
  public void onStreamMessageError(int uid, int streamId, int error, int missed, int cached) {}

  /**
   * @brief Occurs when the local user receives data via Reliable Data Transmission (RDT) from a
   * remote user.
   *
   * @technical preview
   *
   * @param uid ID of the user who sends the data.
   * @param type The RDT stream type
   * @param data The data received.
   */
  public void onRdtMessage(int uid, int type, byte[] data) {}

  /**
   * @brief Occurs when the RDT tunnel state changed
   *
   * @technical preview
   *
   * @param uid ID of the user who sends the data.
   * @param state The RDT tunnel state
   */
  public void onRdtStateChanged(int uid, int state) {}

  /**
   * @brief Occurs when the local user receives media control message sent by a remote user.
   *
   * @technical preview
   *
   * @param uid ID of the user who sends the data.
   * @param data The data received.
   */
  public void onMediaControlMessage(int uid, byte[] data) {}

  /**
   * Occurs when the media engine is loaded.
   *
   */
  public void onMediaEngineLoadSuccess() {}

  /**
   * Occurs when the media engine starts.
   *
   */
  public void onMediaEngineStartCallSuccess() {}

  /**
   * @brief Occurs when the local network type changes.
   *
   * @details
   * This callback occurs when the connection state of the local user changes. You can get the
   * connection state and reason for the state change in this callback. When the network connection is
   * interrupted, this callback indicates whether the interruption is caused by a network type change
   * or poor network conditions.
   *
   * @param type Network types:
   * - NETWORK_TYPE_UNKNOWN (-1): The network type is unknown.
   * - NETWORK_TYPE_DISCONNECTED (0): The SDK disconnects from the network.
   * - NETWORK_TYPE_LAN (1): The network type is LAN.
   * - NETWORK_TYPE_WIFI (2): The network type is Wi-Fi (including hotspots).
   * - NETWORK_TYPE_MOBILE_2G (3) : The network type is mobile 2G.
   * - NETWORK_TYPE_MOBILE_3G (4): The network type is mobile 3G.
   * - NETWORK_TYPE_MOBILE_4G (5): The network type is mobile 4G.
   * - NETWORK_TYPE_MOBILE_5G (6): The network type is mobile 5G.
   *
   */
  public void onNetworkTypeChanged(int type) {}

  /**
   * Occurs when intra request from remote user is received.
   *
   * This callback is triggered once remote user needs one Key frame.
   *
   */
  public void onIntraRequestReceived() {}

  /**
   * @brief Occurs when the uplink network information changes.
   *
   * @details
   * The SDK triggers this callback when the uplink network information changes.
   *
   * @note This callback only applies to scenarios where you push externally encoded video data in
   * H.264 format to the SDK.
   *
   * @param info The uplink network information. See `UplinkNetworkInfo`.
   *
   */
  public void onUplinkNetworkInfoUpdated(UplinkNetworkInfo info) {}

  /**
   * @brief Reports the built-in encryption errors.
   *
   * @details
   * When encryption is enabled by calling `enableEncryption`, the SDK triggers this callback if an
   * error occurs in encryption or decryption on the sender or the receiver side.
   *
   * @param errorType Error types.
   * - ENCRYPTION_ERROR_INTERNAL_FAILURE (0): Internal reasons.
   * - ENCRYPTION_ERROR_DECRYPTION_FAILURE (1): Media stream decryption error. Ensure that the
   * receiver and the sender use the same encryption mode and key.
   * - ENCRYPTION_ERROR_ENCRYPTION_FAILURE (2): Media stream encryption error.
   * - ENCRYPTION_ERROR_DATASTREAM_DECRYPTION_FAILURE (3): Data stream decryption error. Ensure that
   * the receiver and the sender use the same encryption mode and key.
   * - ENCRYPTION_ERROR_DATASTREAM_ENCRYPTION_FAILURE (4): Data stream encryption error.
   *
   */
  public void onEncryptionError(int errorType) {}

  /**
   * @brief Occurs when the SDK cannot get the device permission.
   *
   * @details
   * When the SDK fails to get the device permission, the SDK triggers this callback to report which
   * device permission cannot be got.
   *
   * @param permission The type of the device permission.
   * - RECORD_AUDIO (0): Permission for the audio capture device.
   * - CAMERA (1): Permission for the camera.
   * - SCREEN_CAPTURE (2): Permission for screen sharing.
   *
   */
  public void onPermissionError(int permission) {}

  /**
   * Reports the user log upload result
   * @param requestId RequestId of the upload
   * @param success Is upload success
   * @param reason Reason of the upload, 0: OK, 1 Network Error, 2 Server Error.
   */
  public void onUploadLogResult(String requestId, boolean success, int reason) {}

  public void onContentInspectResult(int result) {}

  /**
   * @brief Reports the proxy connection state.
   *
   * @details
   * You can use this callback to listen for the state of the SDK connecting to a proxy. For example,
   * when a user calls `setCloudProxy` and joins a channel successfully, the SDK triggers this
   * callback to report the user ID, the proxy type connected, and the time elapsed fromthe user
   * calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until this callback is triggered.
   *
   * @param channel The channel name.
   * @param uid The user ID.
   * @param proxyType The proxy type connected.
   * - PROXY_TYPE_NONE_PROXY (0): Reserved for future use.
   * - PROXY_TYPE_UDP_CLOUD_PROXY (1): The cloud proxy for the UDP protocol, that is, the Force UDP
   * cloud proxy mode. In this mode, the SDK always transmits data over UDP.
   * - PROXY_TYPE_TCP_CLOUD_PROXY (2): The cloud proxy for the TCP (encryption) protocol, that is, the
   * Force TCP cloud proxy mode. In this mode, the SDK always transmits data over TCP/TLS 443.
   * - PROXY_TYPE_LOCAL_PROXY (3): Reserved for future use.
   * - PROXY_TYPE_TCP_AUTO_FALLBACK (4): Automatic mode. In this mode, the SDK attempts a direct
   * connection to SD-RTN™ and automatically switches to TCP/TLS 443 if the attempt fails.
   * @param localProxyIp Reserved for future use.
   * @param elapsed The time elapsed (ms) from the user calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` until this
   * callback is triggered.
   *
   */
  public void onProxyConnected(
      String channel, int uid, int proxyType, String localProxyIp, int elapsed) {}

  /**
   * @brief Video frame rendering event callback.
   *
   * @details
   * After calling the `startMediaRenderingTracing` method or joining a channel, the SDK triggers this
   * callback to report the events of video frame rendering and the indicators during the rendering
   * process. Developers can optimize the indicators to improve the efficiency of the first video
   * frame rendering.
   *
   * @param uid The user ID.
   * @param currentEvent The current video frame rendering event. See `MEDIA_TRACE_EVENT`.
   * @param tracingInfo The indicators during the video frame rendering process. Developers need to
   * reduce the value of indicators as much as possible in order to improve the efficiency of the
   * first video frame rendering. See `VideoRenderingTracingInfo`.
   *
   */
  public void onVideoRenderingTracingResult(
      int uid, Constants.MEDIA_TRACE_EVENT currentEvent, VideoRenderingTracingInfo tracingInfo) {}

  /**
   * @brief Occurs when there's an error during the local video mixing.
   *
   * @details
   * When you fail to call `startLocalVideoTranscoder` or `updateLocalTranscoderConfiguration`, the
   * SDK triggers this callback to report the reason.
   *
   * @param stream The video streams that cannot be mixed during video mixing. See
   * `TranscodingVideoStream`.
   * @param error The reason for local video mixing error.
   *
   */
  public void onLocalVideoTranscoderError(
      LocalTranscoderConfiguration.TranscodingVideoStream stream, int error) {}

  /**
   * @brief Occurs when the local user receives a mixed video stream carrying layout information.
   *
   * @details
   * When the local user receives a mixed video stream sent by the video mixing server for the first
   * time, or when there is a change in the layout information of the mixed stream, the SDK triggers
   * this callback, reporting the layout information of each sub-video stream within the mixed video
   * stream.
   *
   * @param uid User ID who published this mixed video stream.
   * @param info Layout information of a specific sub-video stream within the mixed stream. See .
   * `VideoLayoutInfo`
   *
   */
  public void onTranscodedStreamLayoutInfo(int uid, VideoLayoutInfo info) {}

  /**
   * @brief Occurs when the SDK receives audio metadata.
   * @since v4.3.1
   * @param metadata The pointer of metadata
   * @param length Size of metadata
   * @technical preview
   */
  public void onAudioMetadataReceived(int uid, byte[] data) {}

  /**
   * @brief Reports the multipath transmission statistics
   *
   * @post This callback is triggered after you set `enableMultipath` to `true` to enable multipath
   * transmission.
   *
   * @since 4.6.0
   *
   * @param stats The multipath statistics. See `MultipathStats`.
   */
  public void onMultipathStats(MultipathStats stats) {}

  /**
   * @brief Callback for `renewToken` call result.
   *
   * @since v4.6.0
   *
   * @details
   * This callback is triggered after the user calls the `renewToken` method to update the token, and
   * is used to notify the app of the result.
   *
   * @param token Token used for authentication.
   * @param code Error code. See `RenewTokenErrorCode`.
   *
   */
  public void onRenewTokenResult(String token, Constants.RenewTokenErrorCode code) {}
}
