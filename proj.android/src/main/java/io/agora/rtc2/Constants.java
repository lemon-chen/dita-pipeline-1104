package io.agora.rtc2;

/**
 * Rating of the media or network quality.
 */
public class Constants {
  /**
   * 1: The last-mile probe result is complete.
   */
  public final static int LASTMILE_PROBE_RESULT_COMPLETE = 1;
  /**
   * 2: The last-mile network probe test is incomplete and the bandwidth estimation is not
   * available, probably due to limited test resources.
   */
  public final static int LASTMILE_PROBE_RESULT_INCOMPLETE_NO_BWE = 2;
  /**
   * 3: The last-mile network probe test is not carried out, probably due to poor network
   * conditions.
   */
  public final static int LASTMILE_PROBE_RESULT_UNAVAILABLE = 3;
  /**
   * The quality is unknown.
   */
  public final static int QUALITY_UNKNOWN = 0;
  /**
   * The quality is excellent.
   */
  public final static int QUALITY_EXCELLENT = 1;
  /**
   *  The quality is quite good, but the bitrate may be slightly lower than excellent.
   */
  public final static int QUALITY_GOOD = 2;
  /**
   * Users can feel the communication slightly impaired.
   */
  public final static int QUALITY_POOR = 3;
  /**
   * Users can communicate not very smoothly.
   */
  public final static int QUALITY_BAD = 4;
  /**
   * The quality is so bad that users can barely communicate.
   */
  public final static int QUALITY_VBAD = 5;
  /**
   * Users cannot communicate at all.
   */
  public final static int QUALITY_DOWN = 6;
  /**
   * 7: (For future use) The network quality cannot be detected.
   */
  public final static int QUALITY_UNSUPPORTED = 7;
  /**
   * 8: Detecting the network quality.
   */
  public final static int QUALITY_DETECTING = 8;
  /**
   * The specified view is invalid. It is required to specify a view when using the video call
   * function.
   */
  public final static int WARN_INVALID_VIEW = 8;
  /**
   * Failed to initialize the video function.
   */
  public final static int WARN_INIT_VIDEO = 16;
  /**
   * The request is pending, usually due to some module not being ready, and the SDK postponed
   * processing the request.
   */
  public final static int WARN_PENDING = 20;
  /**
   * No channel resources are available. Maybe because the server cannot allocate any channel
   * resource.
   */
  public final static int WARN_NO_AVAILABLE_CHANNEL = 103;
  /**
   * A timeout when looking up the channel. When joining a channel, the SDK looks up the specified
   * channel. The warning usually occurs when the network condition is too poor to connect to the
   * server.
   */
  public final static int WARN_LOOKUP_CHANNEL_TIMEOUT = 104;
  /**
   * The server rejected the request to look up the channel. The server cannot process this request
   * or the request is illegal.
   */
  public final static int WARN_LOOKUP_CHANNEL_REJECTED = 105;
  /**
   * A timeout when opening the channel. Once the specific channel is found, the SDK opens the
   * channel. The warning usually occurs when the network condition is too poor to connect to the
   * server.
   */
  public final static int WARN_OPEN_CHANNEL_TIMEOUT = 106;
  /**
   * The server rejected the request to open the channel. The server cannot process this request or
   * the request is illegal.
   */
  public final static int WARN_OPEN_CHANNEL_REJECTED = 107;
  /**
   * A timeout when switching the live video.
   */
  public final static int WARN_SWITCH_LIVE_VIDEO_TIMEOUT = 111;
  /**
   * A timeout when setting the client role in the broadcast mode.
   */
  public final static int WARN_SET_CLIENT_ROLE_TIMEOUT = 118;
  /**
   * The client role is not authorized.
   */
  public final static int WARN_SET_CLIENT_ROLE_NOT_AUTHORIZED = 119;
  /**
   * The ticket to open the channel is invalid.
   */
  public final static int WARN_OPEN_CHANNEL_INVALID_TICKET = 121;
  /**
   * Try connecting to another server.
   */
  public final static int WARN_OPEN_CHANNEL_TRY_NEXT_VOS = 122;
  /**
   * Error in opening the audio mixing.
   */
  public final static int WARN_AUDIO_MIXING_OPEN_ERROR = 701;
  /**
   * Audio Device Module: A warning in the runtime playback device.
   */
  public final static int WARN_ADM_RUNTIME_PLAYOUT_WARNING = 1014;
  /**
   * Audio Device Module: A warning in the runtime recording device.
   */
  public final static int WARN_ADM_RUNTIME_RECORDING_WARNING = 1016;
  /**
   * Audio Device Module: No valid audio data is collected.
   */
  public final static int WARN_ADM_RECORD_AUDIO_SILENCE = 1019;
  /**
   * Audio Device Module: The recorded audio volume is too low.
   */
  public final static int WARN_ADM_RECORD_AUDIO_LOWLEVEL = 1031;
  /**
   * Audio Device Module: The playback audio volume is too low.
   */
  public final static int WARN_ADM_PLAYOUT_AUDIO_LOWLEVEL = 1032;
  /**
   * Audio Device Module: The recording device is occupied.
   */
  public final static int WARN_ADM_RECORD_IS_OCCUPIED = 1033;
  /**
   * Audio Device Module: Howling is detected.
   */
  public final static int WARN_APM_HOWLING = 1051;
  /**
   * Audio Device Module: The device is in the glitch state.
   */
  public final static int WARN_ADM_GLITCH_STATE = 1052;
  /**
   * Audio Device Module: The settings are improper.
   */
  public final static int WARN_ADM_IMPROPER_SETTINGS = 1053;
  /**
   * Audio Device Module: The device is in the pop state.
   */
  public final static int WARN_ADM_POP_STATE = 1055;

  /**
   * No error occurs.
   */
  public final static int ERR_OK = 0;
  /**
   * A general error occurs (no specified reason).
   */
  public final static int ERR_FAILED = 1;
  /**
   * An invalid parameter is used. For example, the specific channel name includes illegal
   * characters.
   */
  public final static int ERR_INVALID_ARGUMENT = 2;
  /**
   * The SDK module is not ready. We recommend the following methods to solve this error:
   * - Check the audio device.
   * - Check the completeness of the app.
   * - Re-initialize the SDK.
   */
  public final static int ERR_NOT_READY = 3;
  /**
   * The SDK does not support this function.
   */
  public final static int ERR_NOT_SUPPORTED = 4;
  /**
   * The request is rejected. This is for internal SDK internal use only, and it will not return to
   * the application through any API or callback event.
   */
  public final static int ERR_REFUSED = 5;
  /**
   * The buffer size is not big enough to store the returned data.
   */
  public final static int ERR_BUFFER_TOO_SMALL = 6;
  /**
   * The SDK is not initialized before calling this API.
   */
  public final static int ERR_NOT_INITIALIZED = 7;
  /**
   * The state is invalid.
   */
  public static final int ERR_INVALID_STATE = 8;
  /**
   * No permission. Check if the user has granted access to the audio or video device.
   */
  public final static int ERR_NO_PERMISSION = 9;
  /**
   * An API timeout. Some APIs require the SDK to return the execution result, and this error occurs
   * if the request takes too long for the SDK to process.
   */
  public final static int ERR_TIMEDOUT = 10;
  /**
   * The request is cancelled. This is for internal SDK internal use only, and it will not return to
   * the application through any API or callback event.
   */
  public final static int ERR_CANCELED = 11;
  /**
   * The call frequency is too high. This is for internal SDK internal use only, and it will not
   * return to the application through any API or callback event.
   */
  public final static int ERR_TOO_OFTEN = 12;
  /**
   * The SDK fails to bind to the network socket. This is for internal SDK internal use only, and
   * is not returned to the app through any method or callback.
   */
  public final static int ERR_BIND_SOCKET = 13;
  /**
   * The network is unavailable. This is for internal SDK internal use only, and it will not return
   * to the application through any API or callback event.
   */
  public final static int ERR_NET_DOWN = 14;
  /**
   * The request to join the channel is rejected. This error usually occurs when the user is already
   * in the channel, and still calls the API to join the channel, for example, {@link
   * joinChannel()}.
   */
  public final static int ERR_JOIN_CHANNEL_REJECTED = 17;
  /**
   * The request to leave the channel is rejected. This error usually occurs when the user has
   * already left the channel, and still calls the API to leave the channel, for example, {@link
   * leaveChannel()}.
   */
  public final static int ERR_LEAVE_CHANNEL_REJECTED = 18;
  /**
   * Resources are occupied, and cannot be reused.
   */
  public final static int ERR_ALREADY_IN_USE = 19;
  /**
   * The SDK gives up the request due to too many requests. This is for
   * internal use only, and does not return to the app through any method or callback.
   */
  public final static int ERR_ABORTED = 20;
  /**
   * On Windows, specific firewall settings can cause the SDK to fail to
   * initialize and crash.
   */
  public final static int ERR_INIT_NET_ENGINE = 21;
  /**
   * The app uses too much of the system resource and the SDK
   * fails to allocate any resource.
   */
  public final static int ERR_RESOURCE_LIMITED = 22;
  /**
   * The specified App ID is invalid.
   */
  public final static int ERR_INVALID_APP_ID = 101;
  /**
   * The specified channel name is invalid.
   */
  public final static int ERR_INVALID_CHANNEL_NAME = 102;
  /**
   * Fails to get server resources in the specified region.
   */
  public final static int ERR_NO_SERVER_RESOURCES = 103;
  /**
   * <p>The Token expired due to one of the following reasons:
   <ol>
   <li>Authorized Timestamp expired: The timestamp is represented by the number of seconds
   elapsed since 1/1/1970. The user can use the Token to access the Agora service within five
   minutes after the Token is generated. If the user does not access the Agora service after
   five minutes, this Token will no longer be valid.</li> <li>Call Expiration Timestamp expired:
   The timestamp indicates the exact time when a user can no longer use the Agora service (for
   example, when a user is forced to leave an ongoing call). When the value is set for the Call
   Expiration Timestamp, it does not mean that the Dynamic Key will be expired, but that the
   user will be kicked out of the channel.</il></ol>
   */
  public final static int ERR_TOKEN_EXPIRED = 109;
  /**
   * The Token is invalid due to one of the following reasons: The App Certificate for the project
   * is enabled on the Dashboard, but the user is still using the App ID. Once the App Certificate
   * is enabled, the user must use a Token. The uid field is mandatory, and users must set the same
   * uid when setting the uid parameter when calling joinChannel.
   */
  public final static int ERR_INVALID_TOKEN = 110;
  /**
   * The CONNECTION_INTERRUPTED callback. This applies to the Agora Web SDK only.
   */
  public final static int ERR_CONNECTION_INTERRUPTED = 111;
  /**
   * The CONNECTION_LOST callback. This applies to the Agora Web SDK only.
   */
  public final static int ERR_CONNECTION_LOST = 112;
  /**
   * The user is not in the channel.
   */
  public final static int ERR_NOT_IN_CHANNEL = 113;
  /**
   * The data size is too big.
   */
  public final static int ERR_SIZE_TOO_LARGE = 114;
  /**
   * The bitrate is limited.
   */
  public final static int ERR_BITRATE_LIMIT = 115;
  /**
   * Too many data streams.
   */
  public final static int ERR_TOO_MANY_DATA_STREAMS = 116;
  /**
   * A timeout occurs for the data stream transmission.
   */
  public final static int ERR_STREAM_MESSAGE_TIMEOUT = 117;
  /**
   * Switching the user role fails. Please try to rejoin the channel.
   */
  public final static int ERR_SET_CLIENT_ROLE_NOT_AUTHORIZED = 119;
  /**
   * Failed to decrypt mediastream.
   */
  public final static int ERR_DECRYPTION_FAILED = 120;
  /**
   * The user ID is invalid.
   */
  public final static int ERR_INVALID_USER_ID = 121;
  /**
   * Failed to decrypt datastream.
   */
  public final static int ERR_DATASTREAM_DECRYPTION_FAILED = 122;
  /**
   * The client is banned by the server.
   */
  public final static int ERR_CLIENT_IS_BANNED_BY_SERVER = 123;
  /**
   * Encryption is enabled when the user calls the {@link
   * io.agora.rtc2.RtcEngine#addPublishStreamUrl(String, boolean) addPublishStreamUrl} method (CDN
   * live streaming does not support encrypted streams).
   */
  public final static int ERR_ENCRYPTED_STREAM_NOT_ALLOWED_PUBLISH = 130;
  /**
   * License credential is invalid
   */
  public final static int ERR_LICENSE_CREDENTIAL_INVALID = 131;
  /**
   * The user account is invalid, usually because the data format of the user account is incorrect.
   */
  public final static int ERR_INVALID_USER_ACCOUNT = 134;
  /**
   * The extension library is not integrated, such as the library for enabling deep-learning noise
   * reduction.
   */
  public final static int ERR_MODULE_NOT_FOUND = 157;

  /**
   * 200: Unsupported pcm format.
   */
  public final static int ERR_PCMSEND_FORMAT = 200;
  /**
   * 201: Buffer overflow, the pcm send rate too quickly.
   */
  public final static int ERR_PCMSEND_BUFFEROVERFLOW = 201;

  // RDT error code: 250~270
  /**
   * 250: The user does not exist
   * @technical preview
   */
  public final static int ERR_RDT_USER_NOT_EXIST = 250;
  /**
   * 251: The RDT state with the user is not ready
   * @technical preview
   */
  public final static int ERR_RDT_USER_NOT_READY = 251;
  /**
   * 252: The RDT data stream is blocked
   * @technical preview
   */
  public final static int ERR_RDT_DATA_BLOCKED = 252;
  /**
   * 253: The RDT CMD stream exceeds the limit (size <= 256 Bytes, freq <= 100/sec)
   * @technical preview
   */
  public final static int ERR_RDT_CMD_EXCEED_LIMIT = 253;
  /**
   * 254: The RDT DATA stream exceeds the limit (size <= 128 KBytes, speed <= 4 Mbps)
   * @technical preview
   */
  public final static int ERR_RDT_DATA_EXCEED_LIMIT = 254;
  /**
   * 255: The RDT encryption error. The SDK Failed to process RDT data encryption/decryption
   * @technical preview
   */
  public final static int ERR_RDT_ENCRYPTION = 255;

  // 1001~2000
  /**
   * Failed to load the media engine.
   */
  public final static int ERR_LOAD_MEDIA_ENGINE = 1001;
  /**
   * General error on the Audio Device Module (no classified reason).
   */
  public final static int ERR_ADM_GENERAL_ERROR = 1005;
  /**
   * Audio Device Module: Error in initializing the playback device.
   */
  public final static int ERR_ADM_INIT_PLAYOUT = 1008;
  /**
   * Audio Device Module: Error in starting the playback device.
   */
  public final static int ERR_ADM_START_PLAYOUT = 1009;
  /**
   * Audio Device Module: Error in stopping the playback device.
   */
  public final static int ERR_ADM_STOP_PLAYOUT = 1010;
  /**
   * Audio Device Module: Error in initializing the recording device.
   */
  public final static int ERR_ADM_INIT_RECORDING = 1011;
  /**
   * Audio Device Module: Error in starting the recording device.
   */
  public final static int ERR_ADM_START_RECORDING = 1012;
  /**
   * Audio Device Module: Error in stopping the recording device.
   */
  public final static int ERR_ADM_STOP_RECORDING = 1013;

  public final static int ERR_AUDIO_BT_SCO_FAILED = 1030;
  /**
   * Video Device Module: The camera is not authorized.
   */
  public final static int ERR_VDM_CAMERA_NOT_AUTHORIZED = 1501;

  /**
   *  0, 160 x 120  @ 15 fps, 65 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_120P = 0;
  /**
   * 120 x 120  @ 15 fps, 50 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_120P_3 = 2;
  /**
   * 320 x 180  @ 15 fps, 140 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_180P = 10;
  /**
   * 180 x 180  @ 15 fps, 100 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_180P_3 = 12;
  /**
   * 240 x 180  @ 15 fps, 120 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_180P_4 = 13;
  /**
   * 320 x 240  @ 15 fps, 200 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_240P = 20;
  /**
   * 240 x 240  @ 15 fps, 140 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_240P_3 = 22;
  /**
   * 424 x 240  @ 15 fps, 220 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_240P_4 = 23;
  /**
   * 640 x 360  @ 15 fps, 400 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P = 30;
  /**
   * 360 x 360  @ 15 fps, 260 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_3 = 32;
  /**
   * 640 x 360  @ 30 fps, 600 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_4 = 33;
  /**
   * 360 x 360  @ 30 fps, 400 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_6 = 35;
  /**
   * 480 x 360  @ 15 fps, 320 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_7 = 36;
  /**
   * 480 x 360  @ 30 fps, 490 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_8 = 37;
  /**
   * 640 x 360  @ 15 fps, 600 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_9 = 38;
  /**
   * 640 x 360  @ 24 fps, 800 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_10 = 39;
  /**
   * 640 x 360  @ 24 fps, 1000 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_360P_11 = 100;
  /**
   * 640 x 480  @ 15 fps, 500 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P = 40;
  /**
   * 480 x 480  @ 15 fps, 400 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_3 = 42;
  /**
   * 640 x 480  @ 30 fps, 750 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_4 = 43;
  /**
   * 480 x 480  @ 30 fps, 600 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_6 = 45;
  /**
   * 848 x 480  @ 15 fps, 610 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_8 = 47;
  /**
   * 848 x 480  @ 30 fps, 930 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_9 = 48;
  /**
   * 640 x 480  @ 10 fps, 400 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_480P_10 = 49;
  /**
   * 1280 x 720  @ 15 fps, 1130 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_720P = 50;
  /**
   * 1280 x 720  @ 30 fps, 1710 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_720P_3 = 52;
  /**
   * 960 x 720  @ 15 fps, 910 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_720P_5 = 54;
  /**
   * 960 x 720  @ 30 fps, 1380 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_720P_6 = 55;
  /**
   * 1920 x 1080  @ 15 fps, 2080 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_1080P = 60;
  /**
   * 1920 x 1080  @ 30 fps, 3150 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_1080P_3 = 62;
  /**
   * 1920 x 1080  @ 60 fps, 4780 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_1080P_5 = 64;
  /**
   * 2560 x 1440  @ 30 fps, 4850 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_1440P = 66;
  /**
   * 2560 x 1440  @ 60 fps, 7350 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_1440P_2 = 67;
  /**
   * 3840 x 2160  @ 30 fps, 8910 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_4K = 70;
  /**
   * <p>3840 x 2160  @ 60 fps, 13500 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_4K_3 = 72;
  /**
   * <p>Default video profile: 640 x 360  @ 15 fps, 400 kbit/s
   */
  @Deprecated public final static int VIDEO_PROFILE_DEFAULT = VIDEO_PROFILE_360P;

  /**
   * 0: The default audio profile.
   * - In the Communication profile, the default value is the same with
   * `AUDIO_PROFILE_SPEECH_STANDARD`(1).
   * - In the Live-broadcast profile, it represents a sample rate of 48 kHz, music encoding, mono,
   * and a bitrate of up to 64 Kbps.
   */
  public final static int AUDIO_PROFILE_DEFAULT = 0;
  /**
   * 1: A sample rate of 32 kHz, audio encoding, mono, and a bitrate up to 18 Kbps.
   */
  public final static int AUDIO_PROFILE_SPEECH_STANDARD = 1;
  /**
   * 2: A sample rate of 48 kHz, music encoding, mono, and a bitrate of up to 64 Kbps.
   */
  public final static int AUDIO_PROFILE_MUSIC_STANDARD = 2;
  /**
   * 3: A sample rate of 48 kHz, music encoding, stereo, and a bitrate of up to 80
   * Kbps.
   */
  public final static int AUDIO_PROFILE_MUSIC_STANDARD_STEREO = 3;
  /**
   * 4: A sample rate of 48 kHz, music encoding, mono, and a bitrate of up to 96 Kbps.
   */
  public final static int AUDIO_PROFILE_MUSIC_HIGH_QUALITY = 4;
  /**
   * 5: A sample rate of 48 kHz, music encoding, stereo, and a bitrate of up to 128 Kbps.
   */
  public final static int AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO = 5;

  /**
   * 0: (Recommended) The default audio scenario.
   */
  public final static int AUDIO_SCENARIO_DEFAULT = 0;
  /**
   * 3: (Recommended) The live gaming scenario, which needs to enable the gaming audio effects in
   * the speaker mode in a live broadcast scenario. Choose this scenario to
   * achieve high-fidelity music playback.
   */
  public final static int AUDIO_SCENARIO_GAME_STREAMING = 3;
  /**
   * 5: The chatroom scenario, which needs to keep recording when setClientRole to audience.
   * Normally, app developer can also use mute api to achieve the same result,
   * and we implement this 'non-orthogonal' behavior only to make API backward compatible.
   */
  public final static int AUDIO_SCENARIO_CHATROOM = 5;

  /**
   * 7: The chorus scenario.
   */
  public final static int AUDIO_SCENARIO_CHORUS = 7;

  /**
   * 8: The Meeting scenario.
   */
  public final static int AUDIO_SCENARIO_MEETING = 8;

  /**
   * 9: The AI server scenario.
   * @technical preview
   */
  public final static int AUDIO_SCENARIO_AI_SERVER = 9;

  /**
   * 10: The AI client scenario.
   */
  public final static int AUDIO_SCENARIO_AI_CLIENT = 10;

  /**
   * Turn off voice beautifier effects and use the original voice.
   */
  public final static int VOICE_BEAUTIFIER_OFF = 0x00000000;
  /**
   * Turn off audio effects and use the original voice.
   */
  public final static int AUDIO_EFFECT_OFF = 0x00000000;
  /**
   * A more magnetic voice.
   *
   * @note
   * Agora recommends using this enumerator to process a male-sounding voice; otherwise, you may
   * experience vocal distortion.
   */
  public final static int CHAT_BEAUTIFIER_MAGNETIC = 0x01010100;
  /**
   * A fresher voice.
   *
   * @note
   * Agora recommends using this enumerator to process a female-sounding voice; otherwise, you may
   * experience vocal distortion.
   */
  public final static int CHAT_BEAUTIFIER_FRESH = 0x01010200;
  /**
   * A more vital voice.
   *
   * @note
   * Agora recommends using this enumerator to process a female-sounding voice; otherwise, you may
   * experience vocal distortion.
   */
  public final static int CHAT_BEAUTIFIER_VITALITY = 0x01010300;
  /**
   * Singing beautifier effect.
   *
   * - If you call {@link RtcEngine#setVoiceBeautifierPreset
   * setVoiceBeautifierPreset(SINGING_BEAUTIFIER)}, you can beautify a male-sounding voice and add a
   * reverberation effect that sounds like singing in a small room. Agora recommends not using
   * `setVoiceBeautifierPreset(SINGING_BEAUTIFIER)` to process a female-sounding voice; otherwise,
   * you may experience vocal distortion.
   * - If you call {@link RtcEngine#setVoiceBeautifierParameters
   * setVoiceBeautifierParameters(SINGING_BEAUTIFIER, param1, param2)}, you can beautify a male- or
   * female-sounding voice and add a reverberation effect.
   *
   * @since v3.3.0
   */
  public final static int SINGING_BEAUTIFIER = 0x01020100;
  /**
   * A more vigorous voice.
   */
  public final static int TIMBRE_TRANSFORMATION_VIGOROUS = 0x01030100;
  /**
   * A deeper voice.
   */
  public final static int TIMBRE_TRANSFORMATION_DEEP = 0x01030200;
  /**
   * A mellower voice.
   */
  public final static int TIMBRE_TRANSFORMATION_MELLOW = 0x01030300;
  /**
   * A falsetto voice.
   */
  public final static int TIMBRE_TRANSFORMATION_FALSETTO = 0x01030400;
  /**
   * A fuller voice.
   */
  public final static int TIMBRE_TRANSFORMATION_FULL = 0x01030500;
  /**
   * A clearer voice.
   */
  public final static int TIMBRE_TRANSFORMATION_CLEAR = 0x01030600;
  /**
   * A more resounding voice.
   */
  public final static int TIMBRE_TRANSFORMATION_RESOUNDING = 0x01030700;
  /**
   * A more ringing voice.
   */
  public final static int TIMBRE_TRANSFORMATION_RINGING = 0x01030800;
  /**
   * A ultra high quality voice.
   */
  public final static int ULTRA_HIGH_QUALITY_VOICE = 0x01040100;
  /**
   * An audio effect typical of a KTV venue.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_KTV = 0x02010100;
  /**
   * An audio effect typical of a concert hall.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_VOCAL_CONCERT = 0x02010200;
  /**
   * An audio effect typical of a recording studio.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_STUDIO = 0x02010300;
  /**
   * An audio effect typical of a vintage phonograph.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_PHONOGRAPH = 0x02010400;
  /**
   * A virtual stereo effect that renders monophonic audio as stereo audio.
   *
   * @note
   * Call {@link RtcEngine#setAudioProfile setAudioProfile} and set the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_STANDARD_STEREO(3)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator; otherwise, the enumerator setting does not take effect.
   */
  public final static int ROOM_ACOUSTICS_VIRTUAL_STEREO = 0x02010500;
  /**
   * A more spatial audio effect.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_SPACIAL = 0x02010600;
  /**
   * A more ethereal audio effect.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_ETHEREAL = 0x02010700;
  /**
   * A 3D voice effect that makes the voice appear to be moving around the user. The default cycle
   * period of the 3D voice effect is 10 seconds. To change the cycle period, call {@link
   * RtcEngine#setAudioEffectParameters setAudioEffectParameters} after this method.
   *
   * @note
   * - Call {@link RtcEngine#setAudioProfile setAudioProfile} and set the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_STANDARD_STEREO(3)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator; otherwise, the enumerator setting does not take effect
   * - If the 3D voice effect is enabled, users need to use stereo audio playback devices to hear
   * the anticipated voice effect.
   */
  public final static int ROOM_ACOUSTICS_3D_VOICE = 0x02010800;
  /**
   * virtual suround sound.
   *
   * @note
   * - Agora recommends using this enumerator to process virtual suround sound; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling \ref
   * IRtcEngine::setAudioProfile "setAudioProfile" and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_VIRTUAL_SURROUND_SOUND = 0x02010900;
  /**
   * An audio effect for chorus.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int ROOM_ACOUSTICS_CHORUS = 0x02010D00;
  /**
   * The voice of a middle-aged man.
   *
   * @note
   * - Agora recommends using this enumerator to process a male-sounding voice; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_UNCLE = 0x02020100;
  /**
   * The voice of an old man.
   *
   * @note
   * - Agora recommends using this enumerator to process a male-sounding voice; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_OLDMAN = 0x02020200;
  /**
   * The voice of a boy.
   *
   * @note
   * - Agora recommends using this enumerator to process a male-sounding voice; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_BOY = 0x02020300;
  /**
   * The voice of a young woman.
   *
   * @note
   * - Agora recommends using this enumerator to process a female-sounding voice; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_SISTER = 0x02020400;
  /**
   * The voice of a girl.
   *
   * @note
   * - Agora recommends using this enumerator to process a female-sounding voice; otherwise, you may
   * not hear the anticipated voice effect.
   * - To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_GIRL = 0x02020500;
  /**
   * The voice of Pig King, a character in Journey to the West who has a voice like a growling bear.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_PIGKING = 0x02020600;
  /**
   * The voice of Hulk.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int VOICE_CHANGER_EFFECT_HULK = 0x02020700;
  /**
   * An audio effect typical of R&B music.
   *
   * @note
   * Call {@link RtcEngine#setAudioProfile setAudioProfile} and set the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator; otherwise, the enumerator setting does not take effect.
   */
  public final static int STYLE_TRANSFORMATION_RNB = 0x02030100;
  /**
   * An audio effect typical of popular music.
   *
   * @note
   * Call {@link RtcEngine#setAudioProfile setAudioProfile} and set the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator; otherwise, the enumerator setting does not take effect.
   */
  public final static int STYLE_TRANSFORMATION_POPULAR = 0x02030200;
  /**
   * A pitch correction effect that corrects the user's pitch based on the pitch of the natural C
   * major scale. To change the basic mode and tonic pitch, call {@link
   * RtcEngine#setAudioEffectParameters setAudioEffectParameters} after this method.
   *
   * @note
   * To achieve better audio effect quality, Agora recommends calling {@link
   * RtcEngine#setAudioProfile setAudioProfile} and setting the `profile` parameter to
   * `AUDIO_PROFILE_MUSIC_HIGH_QUALITY(4)` or `AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO(5)` before
   * setting this enumerator.
   */
  public final static int PITCH_CORRECTION = 0x02040100;
  /**
   * Turn off voice conversion effects and use the original voice.
   *
   * @since v3.3.1.
   */
  public final static int VOICE_CONVERSION_OFF = 0x00000000;
  /**
   * A gender-neutral voice. To avoid audio distortion, ensure that you use this enumerator to
   * process a female-sounding voice.
   *
   * @since v3.3.1.
   */
  public final static int VOICE_CHANGER_NEUTRAL = 0x03010100;
  /**
   * A sweet voice. To avoid audio distortion, ensure that you use this enumerator to process a
   * female-sounding voice.
   *
   * @since v3.3.1.
   */
  public final static int VOICE_CHANGER_SWEET = 0x03010200;
  /**
   * A steady voice. To avoid audio distortion, ensure that you use this enumerator to process a
   * male-sounding voice.
   *
   * @since v3.3.1.
   */
  public final static int VOICE_CHANGER_SOLID = 0x03010300;
  /**
   * A deep voice. To avoid audio distortion, ensure that you use this enumerator to process a
   * male-sounding voice.
   *
   * @since v3.3.1.
   */
  public final static int VOICE_CHANGER_BASS = 0x03010400;
  /**
   * A voice like a cartoon character.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_CARTOON = 0x03010500;
  /**
   * A voice like a child.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_CHILDLIKE = 0x03010600;
  /**
   * A voice like a phone operator.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_PHONE_OPERATOR = 0x03010700;
  /**
   * A monster voice.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_MONSTER = 0x03010800;
  /**
   * A voice like Transformers.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_TRANSFORMERS = 0x03010900;
  /**
   * A voice like Groot.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_GROOT = 0x03010A00;
  /**
   * A voice like Darth Vader.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_DARTH_VADER = 0x03010B00;
  /**
   * A rough female voice.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_IRON_LADY = 0x03010C00;
  /**
   * A voice like Crayon Shin-chan.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_SHIN_CHAN = 0x03010D00;
  /**
   * A voice like a castrato.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_GIRLISH_MAN = 0x03010E00;
  /**
   * A voice like chipmunk.
   *
   * @since v4.1.0.
   */
  public final static int VOICE_CHANGER_CHIPMUNK = 0x03010F00;

  /**
   * Turn off headphone EQ and use the original voice.
   */
  public final static int HEADPHONE_EQUALIZER_OFF = 0x00000000;
  /**
   * For over-ear headphones.
   */
  public final static int HEADPHONE_EQUALIZER_OVEREAR = 0x04000001;
  /**
   * For in-ear headphones.
   */
  public final static int HEADPHONE_EQUALIZER_INEAR = 0x04000002;

  /**
   * 0: Communication.
   * Use this profile when there are two users in the channel.
   */
  public final static int CHANNEL_PROFILE_COMMUNICATION = 0;
  /**
   * 1: (Default) Live Broadcast.
   * Use this profile when there are more than two users in the channel.
   */
  public final static int CHANNEL_PROFILE_LIVE_BROADCASTING = 1;
  /**
   * 2: Gaming.
   * This profile is deprecated.
   */
  public final static int CHANNEL_PROFILE_GAME = 2;
  /**
   * 3: Cloud Gaming.
   * This profile is deprecated.
   */
  public final static int CHANNEL_PROFILE_CLOUD_GAMING = 3;

  /**
   * 4: Communication 1v1.
   * This profile is deprecated.
   */
  public final static int CHANNEL_PROFILE_COMMUNICATION_1v1 = 4;

  /**
   * The broadcaster.
   */
  public final static int CLIENT_ROLE_BROADCASTER = 1;
  /**
   * The audience.
   */
  public final static int CLIENT_ROLE_AUDIENCE = 2;

  /**
   * 1: Low latency. A low latency audience's jitter buffer is 1.2 second.
   */
  public final static int AUDIENCE_LATENCY_LEVEL_LOW_LATENCY = 1;
  /**
   * 2: Ultra low latency. An ultra low latency audience's jitter buffer is 0.5 second.
   */
  public final static int AUDIENCE_LATENCY_LEVEL_ULTRA_LOW_LATENCY = 2;

  /**
   * 0: The user has quit the call.
   */
  public final static int USER_OFFLINE_QUIT = 0;
  /**
   * 1. The SDK timed out and the user dropped offline because it has not received any data package
   * for a period of time.
   */
  public final static int USER_OFFLINE_DROPPED = 1;
  /**
   * 2. Triggered when the client role has changed from the broadcaster to the audience.
   */
  public final static int USER_OFFLINE_BECOME_AUDIENCE = 2;

  // Capture brightness level.
  /**
   * -1: The SDK does not detect the brightness level of the video image. Wait a few seconds to get
   * the brightness level from `CAPTURE_BRIGHTNESS_LEVEL_TYPE` in the next callback.
   */
  public final static int CAPTURE_BRIGHTNESS_LEVEL_INVALID = -1;
  /** 0: The brightness level of the video image is normal. */
  public final static int CAPTURE_BRIGHTNESS_LEVEL_NORMAL = 0;
  /** 1: The brightness level of the video image is too bright. */
  public final static int CAPTURE_BRIGHTNESS_LEVEL_BRIGHT = 1;
  /** 2: The brightness level of the video image is too dark. */
  public final static int CAPTURE_BRIGHTNESS_LEVEL_DARK = 2;

  // Inject stream status
  /** The external video stream imported successfully. */
  public final static int INJECT_STREAM_STATUS_START_SUCCESS = 0;
  /** The external video stream already exists. */
  public final static int INJECT_STREAM_STATUS_START_ALREADY_EXISTS = 1;
  /** The external video stream import is unauthorized */
  public final static int INJECT_STREAM_STATUS_START_UNAUTHORIZED = 2;
  /** Import external video stream timeout. */
  public final static int INJECT_STREAM_STATUS_START_TIMEDOUT = 3;
  /** The external video stream failed to import. */
  public final static int INJECT_STREAM_STATUS_START_FAILED = 4;
  /** The xternal video stream imports successfully. */
  public final static int INJECT_STREAM_STATUS_STOP_SUCCESS = 5;
  /** No external video stream is found. */
  public final static int INJECT_STREAM_STATUS_STOP_NOT_FOUND = 6;
  /** The external video stream is stopped from being unauthorized. */
  public final static int INJECT_STREAM_STATUS_STOP_UNAUTHORIZED = 7;
  /** Importing the external video stream timeout. */
  public final static int INJECT_STREAM_STATUS_STOP_TIMEDOUT = 8;
  /** Importing the external video stream failed. */
  public final static int INJECT_STREAM_STATUS_STOP_FAILED = 9;
  /** The external video stream is broken. */
  public final static int INJECT_STREAM_STATUS_BROKEN = 10;

  // Format pf the quality report
  /**
   * The quality report in JSON format.
   */
  public static final int QUALITY_REPORT_FORMAT_JSON = 0;
  /**
   * The quality report in HTML format.
   */
  public static final int QUALITY_REPORT_FORMAT_HTML = 1;

  /**
   * 1: Uniformly scale the video until it fills the visible boundaries (cropped). One dimension of
   * the video may have clipped contents.
   */
  public static final int RENDER_MODE_HIDDEN = 1;
  /**
   * 2: Uniformly scale the video until one of its dimension fits the boundary (zoomed to fit).
   * Areas that are not filled due to the disparity in the aspect ratio will be filled with black.
   */
  public static final int RENDER_MODE_FIT = 2;
  /**
   * 3: This mode is deprecated.
   */
  public static final int RENDER_MODE_ADAPTIVE = 3;

  // Local video mirror mode
  /**
   * The default mirror mode, that is, the mode set by the SDK.
   */
  public static final int VIDEO_MIRROR_MODE_AUTO = 0;
  /**
   * Enable the mirror mode.
   */
  public static final int VIDEO_MIRROR_MODE_ENABLED = 1;
  /**
   * Disable the mirror mode
   */
  public static final int VIDEO_MIRROR_MODE_DISABLED = 2;

  /** 0: No content hint.*/
  public static final int SCREEN_CAPTURE_CONTENT_HINT_NONE = 0;

  /**
   * 1: Motion-intensive content. Choose this option if you prefer smoothness or when you are
   * sharing a video clip, movie, or video game.
   */
  public static final int SCREEN_CAPTURE_CONTENT_HINT_MOTION = 1;

  /**
   * 2: Motionless content. Choose this option if you prefer sharpness or when you are sharing a
   * picture, PowerPoint slide, or text.
   */
  public static final int SCREEN_CAPTURE_CONTENT_HINT_DETAILS = 2;

  /**
   * Video captured by the camera.
   */
  public static final int VIDEO_SOURCE_CAMERA_PRIMARY = 0;
  /**
   * Video captured by the secondary camera.
   */
  public static final int VIDEO_SOURCE_CAMERA_SECONDARY = 1;
  /**
   * Video for screen sharing.
   */
  public static final int VIDEO_SOURCE_SCREEN_PRIMARY = 2;
  /**
   * Video for secondary screen sharing.
   */
  public static final int VIDEO_SOURCE_SCREEN_SECONDARY = 3;
  /**
   * Not define.
   */
  public static final int VIDEO_SOURCE_CUSTOM = 4;
  /**
   * Video for media player sharing.
   */
  public static final int VIDEO_SOURCE_MEDIA_PLAYER = 5;
  /**
   * Video for png image.
   */
  public static final int VIDEO_SOURCE_RTC_IMAGE_PNG = 6;
  /**
   * Video for png image.
   */
  public static final int VIDEO_SOURCE_RTC_IMAGE_JPEG = 7;
  /**
   * Video for png image.
   */
  public static final int VIDEO_SOURCE_RTC_IMAGE_GIF = 8;
  /**
   * Remote video received from network.
   */
  public static final int VIDEO_SOURCE_REMOTE = 9;
  /**
   * Video for transcoded.
   */
  public static final int VIDEO_SOURCE_TRANSCODED = 10;

  public static final int VIDEO_SOURCE_UNKNOWN = 100;

  /**
   * Do not output any log.
   */
  public static final int LOG_FILTER_OFF = 0;
  /**
   * Output all the API logs.
   */
  public static final int LOG_FILTER_DEBUG = 0x80f;
  /**
   * Output logs of the CRITICAL, ERROR, WARNING, and INFO level.
   */
  public static final int LOG_FILTER_INFO = 0x0f;
  /**
   * Output logs of the CRITICAL, ERROR, and WARNING level.
   */
  public static final int LOG_FILTER_WARNING = 0x0e;
  /**
   * Output logs of the CRITICAL and ERROR level.
   */
  public static final int LOG_FILTER_ERROR = 0x0c;
  /**
   * Output logs of the CRITICAL level.
   */
  public static final int LOG_FILTER_CRITICAL = 0x08;

  /**
   * Do not output any log file.
   */
  public static final int LOG_LEVEL_NONE = 0x0000;
  /**
   * (Recommended) Output log files of the Info level.
   */
  public static final int LOG_LEVEL_INFO = 0x0001;
  /**
   * Output log files of the Warning level.
   */
  public static final int LOG_LEVEL_WARNING = 0x0002;
  /**
   * Output log files of the Error level.
   */
  public static final int LOG_LEVEL_ERROR = 0x0004;
  /**
   * Output log files of the Critical level.
   */
  public static final int LOG_LEVEL_FATAL = 0x0008;

  /**
   * -1: The default audio route.
   */
  public static final int AUDIO_ROUTE_DEFAULT = -1;
  /**
   * 0: Headset.
   */
  public static final int AUDIO_ROUTE_HEADSET = 0;
  /**
   * 1: Earpiece. The SDK uses the in-call volume.
   */
  public static final int AUDIO_ROUTE_EARPIECE = 1;
  /**
   * 2: Headset with no microphone.
   */
  public static final int AUDIO_ROUTE_HEADSETNOMIC = 2;
  /**
   * 3: Speakerphone.
   */
  public static final int AUDIO_ROUTE_SPEAKERPHONE = 3;
  /**
   * 4: Loudspeaker
   */
  public static final int AUDIO_ROUTE_LOUDSPEAKER = 4;
  /**
   * 5: Bluetooth Device HFP.
   */
  public static final int AUDIO_ROUTE_BLUETOOTH_DEVICE_HFP = 5;
  /**
   * 6: USB device.
   */
  public static final int AUDIO_ROUTE_USBDEVICE = 6;
  /**
   * 7: HDMI device.
   */
  public static final int AUDIO_ROUTE_HDMI = 7;
  /**
   * 8: USB headset.
   */
  public static final int AUDIO_ROUTE_USB_HEADSET = 8;
  /**
   * 10: Bluetooth Device A2DP.
   */
  public static final int AUDIO_ROUTE_BLUETOOTH_DEVICE_A2DP = 10;

  /**
   * 0: The high-stream video, that is, the video stream featuring in high resolution
   * and high bitrate.
   */
  public static final int VIDEO_STREAM_HIGH = 0;
  /**
   * 1: The low-stream video, that is, the video stream featuring in low resolution and
   * low bitrate.
   */
  public static final int VIDEO_STREAM_LOW = 1;
  /**
   * High Priority, if you set a user with high priority, then streams of
   * this user will have high priority than streams of other users with normal default priority.
   * i.e., the SDK will take priority into account when deciding which user's stream need to
   * fallback when network congestion occurs.
   */
  public static final int USER_PRIORITY_HIGH = 50;
  /**
   * Default priority.
   */
  public static final int USER_PRIORITY_NORANL = 100;

  /**
   * Hardware encoder.
   */
  public static final int HARDWARE_ENCODER = 0;
  /**
   * Software encoder.
   */
  public static final int SOFTWARE_ENCODER = 1;
  /**
   * Read-only mode, users only read the AudioFrame data without modifying anything. For example,
   * when users acquire data with the Agora SDK, and push RTMP streams by themselves.
   */
  public static final int RAW_AUDIO_FRAME_OP_MODE_READ_ONLY = 0;
  /**
   *  Read and write mode, users read the data from AudioFrame, modify it and then play it. For
   * example, when users have their own sound-effect processing module, and want to do voice
   * pre-processing, such as a voice change.
   */
  public static final int RAW_AUDIO_FRAME_OP_MODE_READ_WRITE = 2;

  /**
   * `0x01`: The position for observing the playback audio of all remote users after mixing, which
   * enables the SDK to trigger the {@link onPlaybackFrame onPlaybackFrame} callback.
   */
  public static final int POSITION_PLAYBACK = 0x01;
  /**
   * `0x01 << 1`: The position for observing the recorded audio of the local user, which enables the
   * SDK to trigger the {@link onRecordFrame onRecordFrame} callback.
   */
  public static final int POSITION_RECORD = 0x01 << 1;
  /**
   * `0x01 << 2`: The position for observing the mixed audio of the local user and all remote users,
   * which enables the SDK to trigger the {@link onMixedFrame onMixedFrame} callback.
   */
  public static final int POSITION_MIXED = 0x01 << 2;
  /**
   * `0x01 << 3`: The position for observing the audio of a single remote user before mixing,
   * which enables the SDK to trigger the {@link onPlaybackFrameBeforeMixing
   * onPlaybackFrameBeforeMixing} or {@link onPlaybackFrameBeforeMixingEx
   * onPlaybackFrameBeforeMixingEx} callback.
   */
  public static final int POSITION_BEFORE_MIXING = 0x01 << 3;
  /**
   * `0x01 << 4`: The position for observing the ear monitoring audio of the local user,
   * which enables the SDK to trigger the {@link onEarMonitoringAudioFrame
   * onEarMonitoringAudioFrame} callback.
   */
  public static final int POSITION_EAR_MONITORING = 0x01 << 4;

  public static final int MEDIA_ENGINE_RECORDING_ERROR = 0;
  public static final int MEDIA_ENGINE_PLAYOUT_ERROR = 1;
  public static final int MEDIA_ENGINE_RECORDING_WARNING = 2;
  public static final int MEDIA_ENGINE_PLAYOUT_WARNING = 3;
  public static final int MEDIA_ENGINE_AUDIO_FILE_MIX_FINISH = 10;
  // Media engine role changed
  public static final int MEDIA_ENGINE_ROLE_BROADCASTER_SOLO = 20;
  public static final int MEDIA_ENGINE_ROLE_BROADCASTER_INTERACTIVE = 21;
  public static final int MEDIA_ENGINE_ROLE_AUDIENCE = 22;
  public static final int MEDIA_ENGINE_ROLE_COMM_PEER = 23;

  // Network type
  /** -1: The network type is unknown. */
  public static final int NETWORK_TYPE_UNKNOWN = -1;
  /** 0: The SDK disconnects from the network. */
  public static final int NETWORK_TYPE_DISCONNECTED = 0;
  /** 1: The network type is LAN. */
  public static final int NETWORK_TYPE_LAN = 1;
  /** 2: The network type is Wi-Fi (including hotspots). */
  public static final int NETWORK_TYPE_WIFI = 2;
  /** 3: The network type is mobile 2G. */
  public static final int NETWORK_TYPE_MOBILE_2G = 3;
  /** 4: The network type is mobile 3G. */
  public static final int NETWORK_TYPE_MOBILE_3G = 4;
  /** 5: The network type is mobile 4G. */
  public static final int NETWORK_TYPE_MOBILE_4G = 5;
  /** 6: The network type is mobile 5G. */
  public static final int NETWORK_TYPE_MOBILE_5G = 6;

  // RTMP stream lifecycle
  /**
   * Bound to the channel lifecycle.
   */
  public static final int STREAM_LIFE_CYCLE_BIND2CHANNEL = 1;
  /**
   * Bound to the owner of the RTMP stream.
   */
  public static final int STREAM_LIFE_CYCLE_BIND2OWNER = 2;

  /**
   * 1: mic audio file recording.
   */
  public static final int AUDIO_FILE_RECORDING_MIC = 1;
  /**
   * 2: playback audio file recording.
   */
  public static final int AUDIO_FILE_RECORDING_PLAYBACK = 2;
  /**
   * 3: mixed audio file recording, include mic and playback.
   */
  public static final int AUDIO_FILE_RECORDING_MIXED = 3;

  /**
   * Low quality, file size is around 1.2 MB after 10 minutes of recording.
   */
  public static final int AUDIO_RECORDING_QUALITY_LOW = 0;
  /**
   * Medium quality, file size is around 2 MB after 10 minutes of recording.
   */
  public static final int AUDIO_RECORDING_QUALITY_MEDIUM = 1;
  /**
   * High quality, file size is around 3.75 MB after 10 minutes of recording.
   */
  public static final int AUDIO_RECORDING_QUALITY_HIGH = 2;
  /**
   * 3: Ultra High quality. For example, the size of an AAC file with a sample rate of 32,000 Hz and
   * 10-minute recording is approximately 7.5 MB.
   */
  public static final int AUDIO_RECORDING_QUALITY_ULTRA_HIGH = 3;

  /**
   * 1: mic audio frame observer
   */
  public static final int AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIC = 1;
  /**
   * 2: playback audio frame observer
   */
  public static final int AUDIO_ENCODED_FRAME_OBSERVER_POSITION_PLAYBACK = 2;
  /**
   * 3: mixed audio frame observer
   */
  public static final int AUDIO_ENCODED_FRAME_OBSERVER_POSITION_MIXED = 3;

  /**
   * 1: codecType AAC; sampleRate 16000; quality low which around 1.2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_16000_LOW = 0x010101;
  /**
   * 2: codecType AAC; sampleRate 16000; quality medium which around 2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_16000_MEDIUM = 0x010102;
  /**
   * 3: codecType AAC; sampleRate 32000; quality low which around 1.2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_32000_LOW = 0x010201;
  /**
   * 4: codecType AAC; sampleRate 32000; quality medium which around 2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_32000_MEDIUM = 0x010202;
  /**
   * 5: codecType AAC; sampleRate 32000; quality high which around 3.5 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_32000_HIGH = 0x010203;
  /**
   * 6: codecType AAC; sampleRate 48000; quality medium which around 2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_48000_MEDIUM = 0x010302;
  /**
   * 7: codecType AAC; sampleRate 48000; quality high which around 3.5 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_AAC_48000_HIGH = 0x010303;
  /**
   * 11: codecType OPUS; sampleRate 16000; quality low which around 1.2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_OPUS_16000_LOW = 0x020101;
  /**
   * 12: codecType OPUS; sampleRate 16000; quality medium which around 2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_OPUS_16000_MEDIUM = 0x020102;
  /**
   * 13: codecType OPUS; sampleRate 48000; quality medium which around 2 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_OPUS_48000_MEDIUM = 0x020302;
  /**
   * 14: codecType OPUS; sampleRate 48000; quality high which around 3.5 MB after 10 minutes
   */
  public static final int AUDIO_ENCODING_TYPE_OPUS_48000_HIGH = 0x020303;

  /** None */
  public final static int MEDIA_TYPE_NONE = 0;
  /** Audio only */
  public final static int MEDIA_TYPE_AUDIO_ONLY = 1;
  /** Video only */
  public final static int MEDIA_TYPE_VIDEO_ONLY = 2;
  /** Audio and video */
  public final static int MEDIA_TYPE_AUDIO_AND_VIDEO = 3;

  public final static int STREAM_FALLBACK_OPTION_DISABLED = 0;

  public final static int STREAM_FALLBACK_OPTION_VIDEO_STREAM_LOW = 1;

  public final static int STREAM_FALLBACK_OPTION_AUDIO_ONLY = 2;

  /** Local video event type */
  /**
   * 1: The screen capture window is hidden.
   */
  public final static int LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_WINDOW_HIDDEN = 1;
  /**
   * 2: The screen capture window is recovered from hidden.
   */
  public final static int LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_WINDOW_RECOVER_FROM_HIDDEN = 2;
  /**
   * 3: The screen capture is stopped by user.
   */
  public final static int LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_STOPPED_BY_USER = 3;
  /**
   * 4: An internal error occurs during the screen capture.
   */
  public final static int LOCAL_VIDEO_EVENT_TYPE_SCREEN_CAPTURE_SYSTEM_INTERNAL_ERROR = 4;

  /** Local video state types */
  /** Initial state */
  public final static int LOCAL_VIDEO_STREAM_STATE_STOPPED = 0;
  /** The capturer starts successfully. */
  public final static int LOCAL_VIDEO_STREAM_STATE_CAPTURING = 1;
  /** The first video frame is successfully encoded. */
  public final static int LOCAL_VIDEO_STREAM_STATE_ENCODING = 2;
  /** The local video fails to start. */
  public final static int LOCAL_VIDEO_STREAM_STATE_FAILED = 3;

  /** Reasons for the local video failure. */
  /**
   * 0: The local video is normal.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_OK = 0;
  /**
   * 1: No specified reason for the local video failure.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_FAILURE = 1;
  /**
   * 2: No permission to use the local video device.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_DEVICE_NO_PERMISSION = 2;
  /**
   * 3: The local video capturer is in use.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_DEVICE_BUSY = 3;
  /**
   * 4: The local video capture fails. Check whether the capturer is working properly.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_CAPTURE_FAILURE = 4;
  /**
   * 5: The local video encoding fails.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_CODEC_NOT_SUPPORT = 5;
  /**
   * 8: The local video device not found.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_DEVICE_NOT_FOUND = 8;
  /**
   * 14: Video capture was interrupted, possibly due to the camera being occupied
   * or some policy reasons such as background termination.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_DEVICE_INTERRUPT = 14;
  /**
   * 15: The device may need to be shut down and restarted to restore camera function,
   * or there may be a persistent hardware problem.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_DEVICE_FATAL_ERROR = 15;
  /**
   * 25: The screen capture window is hidden.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_SCREEN_CAPTURE_WINDOW_HIDDEN = 25;
  /**
   * 26: The screen capture window is recovered from hidden.
   */
  public final static int LOCAL_VIDEO_STREAM_REASON_SCREEN_CAPTURE_WINDOW_RECOVER_FROM_HIDDEN = 26;

  /** Local audio state types*/
  /**
   * 0: The local audio is in the initial state.
   */
  public final static int LOCAL_AUDIO_STREAM_STATE_STOPPED = 0;
  /**
   * 1: The capturer starts successfully.
   */
  public final static int LOCAL_AUDIO_STREAM_STATE_RECORDING = 1;
  /**
   * 2: The first audio frame is successfully encoded.
   */
  public final static int LOCAL_AUDIO_STREAM_STATE_ENCODING = 2;
  /**
   * 3: The local audio fails to start.
   */
  public final static int LOCAL_AUDIO_STREAM_STATE_FAILED = 3;

  /** Reasons for the local audio failure. */
  /**
   * 0: The local audio is normal.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_OK = 0;
  /**
   * 1: No specified reason for the local audio failure.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_FAILURE = 1;
  /**
   * 2: No permission to use the local audio device.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_DEVICE_NO_PERMISSION = 2;
  /**
   * 3: The microphone is in use.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_DEVICE_BUSY = 3;
  /**
   * 4: The local audio capture failed. Check whether the audio capture device is working properly.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_CAPTURE_FAILURE = 4;
  /**
   * 5: The local audio encoding fails.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_ENCODE_FAILURE = 5;
  /**
   * 8: The local audio capturing is interrupted by the system call.
   */
  public final static int LOCAL_AUDIO_STREAM_REASON_INTERRUPTED = 8;

  /** Remote audio state */
  /**
   * 0: The remote audio is in the default state, probably due to
   * REMOTE_AUDIO_REASON_LOCAL_DISABLE_AUDIO(3), REMOTE_AUDIO_REASON_REMOTE_MUTED(5), or
   * REMOTE_AUDIO_REASON_REMOTE_OFFLINE(7).
   */
  public final static int REMOTE_AUDIO_STATE_STOPPED = 0;
  /**
   * 1: The first remote audio packet is received.
   */
  public final static int REMOTE_AUDIO_STATE_STARTING = 1;
  /**
   * 2: The remote audio stream is decoded and plays normally, probably due to
   * REMOTE_AUDIO_REASON_NETWORK_RECOVERY(2), REMOTE_AUDIO_REASON_LOCAL_ENABLE_AUDIO(4), or
   * REMOTE_AUDIO_REASON_REMOTE_UNMUTED(6).
   */
  public final static int REMOTE_AUDIO_STATE_DECODING = 2;
  /**
   * 3: The remote audio is frozen, probably due to REMOTE_AUDIO_REASON_NETWORK_CONGESTION(1).
   */
  public final static int REMOTE_AUDIO_STATE_FROZEN = 3;
  /**
   * 4: The remote audio fails to start, probably due to REMOTE_AUDIO_REASON_INTERNAL(0).
   */
  public final static int REMOTE_AUDIO_STATE_FAILED = 4;

  /** Reasons for a remote audio state change. */
  /**
   * 0: Internal reasons.
   */
  public final static int REMOTE_AUDIO_REASON_INTERNAL = 0;
  /**
   * 1: Network congestion.
   */
  public final static int REMOTE_AUDIO_REASON_NETWORK_CONGESTION = 1;
  /**
   * 2: Network recovery.
   */
  public final static int REMOTE_AUDIO_REASON_NETWORK_RECOVERY = 2;
  /**
   * 3: The local user stops receiving the remote audio stream or disables the audio module.
   */
  public final static int REMOTE_AUDIO_REASON_LOCAL_MUTED = 3;
  /**
   * 4: The local user resumes receiving the remote audio stream or enables the audio module.
   */
  public final static int REMOTE_AUDIO_REASON_LOCAL_UNMUTED = 4;
  /**
   * 5: The remote user stops sending the audio stream or disables the audio module.
   */
  public final static int REMOTE_AUDIO_REASON_REMOTE_MUTED = 5;
  /**
   * 6: The remote user resumes sending the audio stream or enables the audio module.
   */
  public final static int REMOTE_AUDIO_REASON_REMOTE_UNMUTED = 6;
  /**
   * 7: The remote user leaves the channel.
   */
  public final static int REMOTE_AUDIO_REASON_REMOTE_OFFLINE = 7;
  /**
   * 8: The local user does not receive any audio packet from remote user.
   */
  public final static int REMOTE_AUDIO_REASON_NO_PACKET_RECEIVE = 8;
  /**
   * 9: The local user receives remote audio packet but fails to play.
   */
  public final static int REMOTE_AUDIO_REASON_LOCAL_PLAY_FAILED = 9;

  /** Remote video state. */
  /** Default state */
  public final static int REMOTE_VIDEO_STATE_STOPPED = 0;
  /** 1: video packet has been received, but not decoded yet. */
  public final static int REMOTE_VIDEO_STATE_STARTING = 1;
  /** 2: Remote video stream is decoded and plays normally. */
  public final static int REMOTE_VIDEO_STATE_DECODING = 2;
  /** 3: Remote video is frozen, probably due to network issue. */
  public final static int REMOTE_VIDEO_STATE_FROZEN = 3;
  /** 4: Failed. */
  public final static int REMOTE_VIDEO_STATE_FAILED = 4;

  /**  Reasons for a remote video state change. */
  /**
   * 0: Internal reasons.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_INTERNAL = 0;
  /**
   * 1: Network congestion.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_NETWORK_CONGESTION = 1;
  /**
   * 2: Network recovery.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY = 2;
  /**
   * 3: The local user stops receiving the remote video stream or disables the video module.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_LOCAL_MUTED = 3;
  /**
   * 4: The local user resumes receiving the remote video stream or enables the video module.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_LOCAL_UNMUTED = 4;
  /**
   * 5: The remote user stops sending the video stream or disables the video module.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED = 5;
  /**
   * 6: The remote user resumes sending the video stream or enables the video module.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED = 6;
  /**
   * 7: The remote user leaves the channel.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_REMOTE_OFFLINE = 7;
  /**
   * 8: The remote media stream falls back to the audio-only stream due to poor network conditions.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK = 8;
  /**
   * 9: The remote media stream switches back to the video stream after the network conditions
   * improve.
   */
  public final static int REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK_RECOVERY = 9;
  /**12: The remote user sdk(only for iOS) in background.
   */
  public static final int REMOTE_VIDEO_STATE_REASON_SDK_IN_BACKGROUND = 12;
  /**
   * 13: The remote video stream is not supported by the decoder.
   */
  public static final int REMOTE_VIDEO_STATE_REASON_CODEC_NOT_SUPPORT = 13;

  /** The audio mixing state types */
  /**
   * 710: The audio mixing file is playing.
   */
  public final static int AUDIO_MIXING_STATE_PLAYING = 710;
  /**
   * 711: The audio mixing file pauses playing.
   */
  public final static int AUDIO_MIXING_STATE_PAUSED = 711;
  /**
   * 713: The audio mixing file stops playing.
   */
  public final static int AUDIO_MIXING_STATE_STOPPED = 713;
  /**
   * 714: An exception occurs when playing the audio mixing file. See the errorCode for details.
   */
  public final static int AUDIO_MIXING_STATE_FAILED = 714;

  /** The audio mixing error type. */
  /**
   * 701: An error occurs in opening the audio mixing file.
   */
  public final static int AUDIO_MIXING_REASON_CAN_NOT_OPEN = 701;
  /**
   * 702: The SDK opens the audio mixing file too frequently.
   */
  public final static int AUDIO_MIXING_REASON_TOO_FREQUENT_CALL = 702;
  /**
   * 721: The audio mixing file is played once.
   */
  public final static int AUDIO_MIXING_REASON_ONE_LOOP_COMPLETED = 721;
  /**
   * 723: The audio mixing file is all played out.
   * */
  public final static int AUDIO_MIXING_REASON_ALL_LOOPS_COMPLETED = 723;
  /**
   * 724: The audio mixing file stopped by user
   * */
  public final static int AUDIO_MIXING_REASON_STOPPED_BY_USER = 724;
  /**
   * 726: The audio mixing playback has resumed by user
   * */
  public final static int AUDIO_MIXING_REASON_RESUMED_BY_USER = 726;
  /**
   * 703: The audio mixing file playback is interrupted.
   */
  public final static int AUDIO_MIXING_REASON_INTERRUPTED_EOF = 703;
  /**
   * 0: No error.
   */
  public final static int AUDIO_MIXING_REASON_OK = 0;

  /** Video codec types VIDEO_CODEC_TYPE*/
  public final static int VIDEO_CODEC_VP8 = 1;
  public final static int VIDEO_CODEC_H264 = 2;
  public final static int VIDEO_CODEC_H265 = 3;
  public final static int VIDEO_CODEC_VP9 = 13;
  public final static int VIDEO_CODEC_GENERIC = 6;
  public final static int VIDEO_CODEC_GENERIC_H264 = 7;
  public final static int VIDEO_CODEC_GENERIC_JPEG = 20;

  /** Transport CC enabled */
  public final static int TCC_ENABLED = 0;
  public final static int TCC_DISABLED = 1;

  public final static int AUDIO_CODEC_OPUS = 1;
  public final static int AUDIO_CODEC_AACLC = 8;
  public final static int AUDIO_CODEC_HEAAC = 9;
  public final static int AUDIO_CODEC_HEAAC2 = 11;

  /** Packetize Mode H264PacketizeMode*/
  public final static int PACKETIZE_MODE_H264_NON_INTERLEAVED = 0;
  public final static int PACKETIZE_MODE_H264_SINGLE_NAL_UNIT = 1;

  /** Video Frame Type. VIDEO_FRAME_TYPE */
  public final static int VIDEO_FRAME_TYPE_BLANK_FRAME = 0;
  public final static int VIDEO_FRAME_TYPE_KEY_FRAME = 3;
  public final static int VIDEO_FRAME_TYPE_DELTA_FRAME = 4;
  public final static int VIDEO_FRAME_TYPE_B_FRAME = 5;
  public final static int VIDEO_FRAME_TYPE_UNKNOWN = 6;

  /** clockwise rotation. VIDEO_ORIENTATION*/
  /**
   * 0: No rotation.
   */
  public final static int VIDEO_ORIENTATION_0 = 0;
  /**
   * 90: 90 degrees.
   */
  public final static int VIDEO_ORIENTATION_90 = 90;
  /**
   * 180: 180 degrees.
   */
  public final static int VIDEO_ORIENTATION_180 = 180;
  /**
   * 270: 270 degrees.
   */
  public final static int VIDEO_ORIENTATION_270 = 270;

  /**
   * The state of the rhythm player.
   */
  /**
   * 810: The rhythm player is idle.
   */
  public final static int RHYTHM_PLAYER_STATE_IDLE = 810;
  /**
   * 811: The rhythm player is opening files.
   */
  public final static int RHYTHM_PLAYER_STATE_OPENING = 811;
  /**
   * 812: Files opened successfully, the rhythm player starts decoding files.
   */
  public final static int RHYTHM_PLAYER_STATE_DECODING = 812;
  /**
   * 813: Files decoded successfully, the rhythm player starts mixing the two files and playing
   * back them locally.
   */
  public final static int RHYTHM_PLAYER_STATE_PLAYING = 813;
  /**
   * 814: The rhythm player is starting to fail, and you need to check the error code for
   * detailed failure reasons
   */
  public final static int RHYTHM_PLAYER_STATE_FAILED = 814;

  /** Reasons for a license verify fail. */
  /**
   * 1: Invalid license
   */
  public final static int LICENSE_ERR_INVALID = 1;
  /**
   * 2: License expired
   */
  public final static int LICENSE_ERR_EXPIRE = 2;
  /**
   * 3: Exceed license minutes limit
   */
  public final static int LICENSE_ERR_MINUTES_EXCEED = 3;
  /**
   * 4: License use in limited period
   */
  public final static int LICENSE_ERR_LIMITED_PERIOD = 4;
  /**
   * 5: Same license used in different devices at the same time
   */
  public final static int LICENSE_ERR_DIFF_DEVICES = 5;
  /**
   * 99: SDK internal error
   */
  public final static int LICENSE_ERR_INTERNAL = 99;

  /**
   The error codes of the rhythm player.
   */
  /**
   * 0: The rhythm player works well.
   */
  public static final int RHYTHM_PLAYER_REASON_OK = 0;
  /**
   * 1: he rhythm player occurs a internal error.
   */
  public static final int RHYTHM_PLAYER_REASON_FAILED = 1;
  /**
   * 801: The rhythm player can not open the file.
   */
  public static final int RHYTHM_PLAYER_REASON_CAN_NOT_OPEN = 801;
  /**
   * 802: The rhythm player can not play the file.
   */
  public static final int RHYTHM_PLAYER_REASON_CAN_NOT_PLAY = 802;
  /**
   * 803: The file duration over the limit. The file duration limit is 1.2 seconds.
   */
  public static final int RHYTHM_PLAYER_REASON_FILE_OVER_DURATION_LIMIT = 803;

  /**
   * The stream publish state.
   */
  /**
   * 0: The initial publishing state after joining the channel.
   */
  public static final int PUB_STATE_IDLE = 0;
  /**
   *  1: Fails to publish the local stream.
   */
  public static final int PUB_STATE_NO_PUBLISHED = 1;
  /**
   *  2: Publishing the local stream.
   */
  public static final int PUB_STATE_PUBLISHING = 2;
  /**
   *  3: Successfully publishes the local stream.
   */
  public static final int PUB_STATE_PUBLISHED = 3;

  /**
   * The stream subscribe state.
   */
  /**
   *  0: The initial subscribing state after joining the channel.
   */
  public static final int SUB_STATE_IDLE = 0;
  /**
   *  1: Fails to subscribing to the remote stream.
   */
  public static final int SUB_STATE_NO_SUBSCRIBED = 1;
  /**
   *  2: Subscribing.
   */
  public static final int SUB_STATE_SUBSCRIBING = 2;
  /**
   *  3: Subscribes to and receives the remote stream successfully.
   */
  public static final int SUB_STATE_SUBSCRIBED = 3;

  /** Events during the RTMP or RTMPS streaming. */
  /**
   * 1: An error occurs when you add a background image or a watermark image to the RTMP or RTMPS
   * stream.
   */
  public final static int RTMP_STREAMING_EVENT_FAILED_LOAD_IMAGE = 1;
  /**
   * 2: The streaming URL is already being used for CDN live streaming. If you want to start new
   * streaming, use a new streaming URL.
   *
   * @since v3.4.5
   */
  public final static int RTMP_STREAMING_EVENT_URL_ALREADY_IN_USE = 2;
  /**
   * 3: advanced feature not support
   */
  public final static int RTMP_STREAMING_EVENT_ADVANCED_FEATURE_NOT_SUPPORT = 3;
  /**
   * 4: Client request too frequently.
   */
  public final static int RTMP_STREAMING_EVENT_REQUEST_TOO_OFTEN = 4;

  /** The RTMP or RTMPS streaming state. */
  /**
   * 0: The RTMP streaming has not started or has ended.
   *
   * This state is also reported after you remove
   * an RTMP address from the CDN by calling `removePublishStreamUrl`.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_IDLE = 0;
  /**
   * 1: The SDK is connecting to the streaming server and the RTMP server.
   *
   * This state is reported after you call `addPublishStreamUrl`.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_CONNECTING = 1;
  /**
   * 2: The RTMP streaming publishes. The SDK successfully publishes the RTMP streaming and
   * returns this state.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_RUNNING = 2;
  /**
   * 3: The RTMP streaming is recovering. When exceptions occur to the CDN, or the streaming is
   * interrupted, the SDK tries to resume RTMP streaming and reports this state.
   *
   * - If the SDK successfully resumes the streaming, `RTMP_STREAM_PUBLISH_STATE_RUNNING(2)` is
   * reported.
   * - If the streaming does not resume within 60 seconds or server errors occur,
   * `RTMP_STREAM_PUBLISH_STATE_FAILURE(4)` is reported. You can also reconnect to the server by
   * calling `removePublishStreamUrl` and `addPublishStreamUrl`.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_RECOVERING = 3;
  /**
   * 4: The RTMP streaming fails. See the `errCode` parameter for the detailed error information.
   * You can also call `addPublishStreamUrl` to publish the RTMP streaming again.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_FAILURE = 4;
  /**
   * 5: The SDK is disconnecting to Agora's streaming server and the CDN server. This state is
   * triggered after you call the \ref IRtcEngine::removePublishStreamUrl "removePublishStreamUrl"
   * method.
   */
  public final static int RTMP_STREAM_PUBLISH_STATE_DISCONNECTING = 5;

  /**
   * Error codes of the RTMP streaming.
   */
  /**
   * 0: The RTMP or RTMPS streaming publishes successfully.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_OK = 0;
  /**
   * 1. Invalid argument used. If, for example, you do not call the \ref
   *    IRtcEngine::setLiveTranscoding "setLiveTranscoding" method to configure the LiveTranscoding
   *    parameters before calling the addPublishStreamUrl method, the SDK returns this error. Check
   *    whether you set the parameters in the *setLiveTranscoding* method properly.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_INVALID_ARGUMENT = 1;
  /**
   * 2: The RTMP or RTMPS streaming is encrypted and cannot be published.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_ENCRYPTED_STREAM_NOT_ALLOWED = 2;
  /**
   * 3. Timeout for the RTMP or RTMPS streaming. Call the \ref IRtcEngine::addPublishStreamUrl
   * "addPublishStreamUrl" method to publish the streaming again.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_CONNECTION_TIMEOUT = 3;
  /**
   * 4. An error occurs in Agora's streaming server. Call the `addPublishStreamUrl` method to
   * publish the streaming again.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_INTERNAL_SERVER_ERROR = 4;
  /**
   * 5: An error occurs in the CDN server.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_RTMP_SERVER_ERROR = 5;
  /**
   * 6: The RTMP or RTMPS streaming publishes too frequently.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_TOO_OFTEN = 6;
  /**
   * 7: The host publishes more than 10 URLs. Delete the unnecessary URLs before adding new ones.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_REACH_LIMIT = 7;
  /**
   * 8: The host manipulates other hosts' URLs. Check your app logic.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_NOT_AUTHORIZED = 8;
  /**
   * 9: Agora server fails to find the RTMP or RTMPS streaming.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_STREAM_NOT_FOUND = 9;
  /**
   * 10: The format of the RTMP or RTMPS streaming URL is not supported. Check whether the URL
   * format is correct.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_FORMAT_NOT_SUPPORTED = 10;
  /**
   * 11: Current role is not broadcaster. Check whether the role of the current channel.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_NOT_BROADCASTER = 11;
  /**
   * 13: Call updateTranscoding, but no mix stream.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_TRANSCODING_NO_MIX_STREAM = 13;
  /**
   * 14: Network error.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_NET_DOWN = 14;
  /**
   * 15: User AppId have not authorized to push stream.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_INVALID_APPID = 15;
  /**
   * 16: invalid privilege.
   */
  public final static int RTMP_STREAM_PUBLISH_REASON_INVALID_PRIVILEGE = 16;
  /**
   * 100: The streaming has been stopped normally. After you call
   * \ref IRtcEngine::removePublishStreamUrl "removePublishStreamUrl"
   * to stop streaming, the SDK returns this value.
   *
   * @since v3.4.5
   */
  public final static int RTMP_STREAM_UNPUBLISH_REASON_OK = 100;

  /**
   * Error type of encryption.
   */
  /**
   * 0: Internal reason.
   */
  public final static int ENCRYPTION_ERROR_INTERNAL_FAILURE = 0;
  /**
   * 1: MediaStream decryption errors. Ensure that the receiver and the sender use the same
   * encryption mode and key.
   */
  public final static int ENCRYPTION_ERROR_DECRYPTION_FAILURE = 1;
  /**
   * 2: MediaStream encryption errors.
   */
  public final static int ENCRYPTION_ERROR_ENCRYPTION_FAILURE = 2;
  /**
   * 3: DataStream decryption errors. Ensure that the receiver and the sender use the same
   * encryption mode and key.
   */
  public final static int ENCRYPTION_ERROR_DATASTREAM_DECRYPTION_FAILURE = 3;
  /**
   * 4: DataStream encryption errors.
   */
  public final static int ENCRYPTION_ERROR_DATASTREAM_ENCRYPTION_FAILURE = 4;

  public final static int USER_STATE_MUTE_AUDIO = 1 << 0;
  public final static int USER_STATE_MUTE_VIDEO = 1 << 1;
  public final static int USER_STATE_ENABLE_VIDEO = 1 << 4;
  public final static int USER_STATE_ENABLE_LOCAL_VIDEO = 1 << 8;

  /**
   * Type of permission.
   */
  public final static int RECORD_AUDIO = 0;
  public final static int CAMERA = 1;
  public final static int SCREEN_CAPTURE = 2;

  /**
   * The default connection ID.
   */
  public final static int DEFAULT_CONNECTION_ID = 0;

  // Connection state type
  /**
   * 1: The SDK is disconnected from Agora edge server.
   * <ul>
   *   <li>This is the initial state before calling the `joinChannel` method.</li>
   *   <li>The SDK also enters this state when the app calls the {@link RtcEngine#leaveChannel
   * leaveChannel} method.</li>
   * </ul>
   */
  public static final int CONNECTION_STATE_DISCONNECTED = 1;
  /**
   * 2: The SDK is connecting to Agora edge server.
   * <ul>
   *   <li>When the app calls the `joinChannel` method, the SDK starts to establish a connection to
   * the specified channel, triggers the {@link IRtcEngineEventHandler#onConnectionStateChanged
   * onConnectionStateChanged} callback, and switches to the {@link
   * Constants#CONNECTION_STATE_CONNECTING CONNECTION_STATE_CONNECTING} state. <li>When a user
   * successfully joins a channel, the SDK triggers the {@link
   * IRtcEngineEventHandler#onConnectionStateChanged onConnectionStateChanged} callback and switches
   * to the {@link Constants#CONNECTION_STATE_CONNECTED CONNECTION_STATE_CONNECTED} state. <li>After
   * the SDK joins the channel and when it finishes initializing the media engine, the SDK triggers
   * the {@link IRtcEngineEventHandler#onJoinChannelSuccess onJoinChannelSuccess} callback.
   * </ul>
   *
   */
  public static final int CONNECTION_STATE_CONNECTING = 2;
  /**
   * 3: The SDK is connected to Agora edge server and has joined a channel. You can now publish or
   * subscribe to a media stream in the channel. If the connection to the channel is lost because,
   * for example, the network is down or switched, the SDK triggers:
   * <ul>
   *    <li>The {@link IRtcEngineEventHandler#onConnectionInterrupted onConnectionInterrupted}
   * (deprecated) callback. <li>The {@link IRtcEngineEventHandler#onConnectionStateChanged
   * onConnectionStateChanged} callback, and switches to the {@link
   * Constants#CONNECTION_STATE_RECONNECTING CONNECTION_STATE_RECONNECTING} state.
   * </ul>
   */
  public static final int CONNECTION_STATE_CONNECTED = 3;
  /**
   * 4: The SDK keeps rejoining the channel after being disconnected from a joined channel because
   * of network issues.
   * <ul>
   *    <li>If the SDK cannot join the channel within 10 seconds after being
   * disconnected from Agora edge server, the SDK triggers the {@link
   * IRtcEngineEventHandler#onConnectionLost onConnectionLost} (deprecated) callback, stays in the
   * {@link Constants#CONNECTION_STATE_RECONNECTING CONNECTION_STATE_RECONNECTING} state, and keeps
   * rejoining the channel.
   *    <li>If the SDK fails to rejoin the channel 20 minutes after being
   * disconnected from Agora edge server, the SDK triggers the {@link
   * IRtcEngineEventHandler#onConnectionStateChanged onConnectionStateChanged} callback, switches to
   * the {@link Constants#CONNECTION_STATE_FAILED CONNECTION_STATE_FAILED} state, and stops
   * rejoining the channel.
   * </ul>
   */
  public static final int CONNECTION_STATE_RECONNECTING = 4;
  /**
   * 5: The SDK fails to connect to Agora edge server or join the channel.
   * You must call the {@link RtcEngine#leaveChannel() leaveChannel} method to leave this state and
   * call the `joinChannel` method again to rejoin the channel. If the SDK is banned from joining
   * the channel by the Agora server (through the RESTful API), the SDK triggers the {@link
   * IRtcEngineEventHandler#onConnectionBanned onConnectionBanned} and {@link
   * IRtcEngineEventHandler#onConnectionStateChanged onConnectionStateChanged} callbacks.
   */
  public static final int CONNECTION_STATE_FAILED = 5;

  // Reason for the connection state change.
  /** 0: The SDK is connecting to Agora edge server. */
  public static final int CONNECTION_CHANGED_CONNECTING = 0;
  /** 1: The SDK has joined the channel successfully. */
  public static final int CONNECTION_CHANGED_JOIN_SUCCESS = 1;
  /** 2: The connection between the SDK and Agora edge server is interrupted. */
  public static final int CONNECTION_CHANGED_INTERRUPTED = 2;
  /** 3: The connection between the SDK and Agora edge server is banned by Agora edge server. */
  public static final int CONNECTION_CHANGED_BANNED_BY_SERVER = 3;
  /**
   * 4: The SDK fails to join the channel for more than 20 minutes and stops reconnecting to the
   * channel.
   */
  public static final int CONNECTION_CHANGED_JOIN_FAILED = 4;
  /** 5: The SDK has left the channel. */
  public static final int CONNECTION_CHANGED_LEAVE_CHANNEL = 5;
  /** 6:  The specified App ID is invalid. Try to rejoin the channel with a valid App ID. */
  public static final int CONNECTION_CHANGED_INVALID_APP_ID = 6;
  /**
   * 7: The specified channel name is invalid. Try to rejoin the channel with a valid channel name.
   */
  public static final int CONNECTION_CHANGED_INVALID_CHANNEL_NAME = 7;
  /**
   * 8: The generated token is invalid probably due to the following reasons:
   * <ul>
   *     <li>The App Certificate for the project is enabled in Console, but you do not use the
   * token.
   *     <li>The uid that you specify in the `joinChannel` method is different from the uid that you
   * pass for generating the token.
   * </ul>
   */
  public static final int CONNECTION_CHANGED_INVALID_TOKEN = 8;
  /** 9: The token has expired. Generate a new token from your server. */
  public static final int CONNECTION_CHANGED_TOKEN_EXPIRED = 9;
  /** 10: The user is banned by the server. */
  public static final int CONNECTION_CHANGED_REJECTED_BY_SERVER = 10;
  /** 11: The SDK tries to reconnect after setting a proxy server. */
  public static final int CONNECTION_CHANGED_SETTING_PROXY_SERVER = 11;
  /** 12: The token renews. */
  public static final int CONNECTION_CHANGED_RENEW_TOKEN = 12;
  /**
   * 13: The client IP address has changed, probably due to a change of the network type, IP
   * address, or network port.
   */
  public static final int CONNECTION_CHANGED_CLIENT_IP_ADDRESS_CHANGED = 13;
  /**
   * 14: Timeout for the keep-alive of the connection between the SDK and Agora edge server.
   * The connection state changes to {@link Constants#CONNECTION_STATE_RECONNECTING}(4).
   */
  public static final int CONNECTION_CHANGED_KEEP_ALIVE_TIMEOUT = 14;
  /**
   * 15: The SDK has rejoined the channel successfully.
   */
  public static final int CONNECTION_CHANGED_REJOIN_SUCCESS = 15;
  /**
   * 16: The connection between the SDK and the server is lost.
   */
  public static final int CONNECTION_CHANGED_LOST = 16;
  /**
   * 17: The change of connection state is caused by echo test.
   */
  public static final int CONNECTION_CHANGED_ECHO_TEST = 17;
  /**
   * 18: The local IP Address is changed by user.
   */
  public static final int CONNECTION_CHANGED_CLIENT_IP_ADDRESS_CHANGED_BY_USER = 18;
  /**
  /* 19: There is a disconnection due to joining channel on another device with the same uid.
   */
  public static final int CONNECTION_CHANGED_SAME_UID_LOGIN = 19;
  /**
   * 20: The connection is failed due to too many broadcasters in the channel.
   */
  public static final int CONNECTION_CHANGED_TOO_MANY_BROADCASTERS = 20;
  /**
   * 21: The connection is failed due to license validation failure.
   */
  public static final int CONNECTION_CHANGED_LICENSE_VALIDATION_FAILURE = 21;
  /**
   * 22: The connection is failed due to certification verify failure.
   */
  public static final int CONNECTION_CHANGED_CERTIFICATION_VERYFY_FAILURE = 22;

  /**
   * 0: The state is normal.
   */
  public final static int RELAY_OK = 0;
  /**
   * 1: An error occurs in the server response.
   */
  public final static int RELAY_ERROR_SERVER_ERROR_RESPONSE = 1;
  /**
   * 2: No server response. You can call the leaveChannel method to leave the channel.
   */
  public final static int RELAY_ERROR_SERVER_NO_RESPONSE = 2;
  /**
   * 3: The SDK fails to access the service, probably due to limited resources of the server.
   */
  public final static int RELAY_ERROR_NO_RESOURCE_AVAILABLE = 3;
  /**
   * 4: Fails to send the relay request.
   */
  public final static int RELAY_ERROR_FAILED_JOIN_SRC = 4;
  /**
   * 5: Fails to accept the relay request.
   */
  public final static int RELAY_ERROR_FAILED_JOIN_DEST = 5;
  /**
   * 6: The server fails to receive the media stream.
   */
  public final static int RELAY_ERROR_FAILED_PACKET_RECEIVED_FROM_SRC = 6;
  /**
   * 7: The server fails to send the media stream.
   */
  public final static int RELAY_ERROR_FAILED_PACKET_SENT_TO_DEST = 7;
  /**
   * 8: The SDK disconnects from the server due to poor network connections. You can call the
   * leaveChannel method to leave the channel.
   */
  public final static int RELAY_ERROR_SERVER_CONNECTION_LOST = 8;
  /**
   * 9: An internal error occurs in the server.
   */
  public final static int RELAY_ERROR_INTERNAL_ERROR = 9;
  /**
   * 10: The token of the source channel has expired.
   */
  public final static int RELAY_ERROR_SRC_TOKEN_EXPIRED = 10;
  /**
   * 11: The token of the destination channel has expired.
   */
  public final static int RELAY_ERROR_DEST_TOKEN_EXPIRED = 11;

  /**
   * 0: The user disconnects from the server due to poor network connections.
   */
  public final static int RELAY_EVENT_NETWORK_DISCONNECTED = 0;
  /**
   * 1: The network reconnects.
   */
  public final static int RELAY_EVENT_NETWORK_CONNECTED = 1;
  /**
   * 2: The user joins the source channel.
   */
  public final static int RELAY_EVENT_PACKET_JOINED_SRC_CHANNEL = 2;
  /**
   * 3: The user joins the destination channel.
   */
  public final static int RELAY_EVENT_PACKET_JOINED_DEST_CHANNEL = 3;
  /**
   * 4: The SDK starts relaying the media stream to the destination channel.
   */
  public final static int RELAY_EVENT_PACKET_SENT_TO_DEST_CHANNEL = 4;
  /**
   * 5: The server receives the video stream from the source channel.
   */
  public final static int RELAY_EVENT_PACKET_RECEIVED_VIDEO_FROM_SRC = 5;
  /**
   * 6: The server receives the audio stream from the source channel.
   */
  public final static int RELAY_EVENT_PACKET_RECEIVED_AUDIO_FROM_SRC = 6;
  /**
   * 7: The destination channel is updated.
   */
  public final static int RELAY_EVENT_PACKET_UPDATE_DEST_CHANNEL = 7;
  /**
   * 8: The destination channel update fails due to internal reasons.
   * Deprecated from 4.1.0
   */
  public final static int RELAY_EVENT_PACKET_UPDATE_DEST_CHANNEL_REFUSED = 8;
  /**
   * 9: The destination channel does not change, which means that the destination channel fails to
   * be updated.
   */
  public final static int RELAY_EVENT_PACKET_UPDATE_DEST_CHANNEL_NOT_CHANGE = 9;
  /**
   * 10: The destination channel name is NULL.
   */
  public final static int RELAY_EVENT_PACKET_UPDATE_DEST_CHANNEL_IS_NULL = 10;
  /**
   * 11: The video profile is sent to the server.
   */
  public final static int RELAY_EVENT_VIDEO_PROFILE_UPDATE = 11;
  /**
   * 12: pause send packet to dest channel success.
   */
  public final static int RELAY_EVENT_PAUSE_SEND_PACKET_TO_DEST_CHANNEL_SUCCESS = 12;
  /**
   * 13: pause send packet to dest channel failed.
   */
  public final static int RELAY_EVENT_PAUSE_SEND_PACKET_TO_DEST_CHANNEL_FAILED = 13;
  /**
   * 14: resume send packet to dest channel success.
   */
  public final static int RELAY_EVENT_RESUME_SEND_PACKET_TO_DEST_CHANNEL_SUCCESS = 14;
  /**
   * 15: pause send packet to dest channel failed.
   */
  public final static int RELAY_EVENT_RESUME_SEND_PACKET_TO_DEST_CHANNEL_FAILED = 15;
  /**
   * 0: The SDK is initializing.
   */
  public final static int RELAY_STATE_IDLE = 0;
  /**
   * 1: The SDK tries to relay the media stream to the destination channel.
   */
  public final static int RELAY_STATE_CONNECTING = 1;
  /**
   * 2: The SDK successfully relays the media stream to the destination channel.
   */
  public final static int RELAY_STATE_RUNNING = 2;
  /**
   * 3: A failure occurs.
   */
  public final static int RELAY_STATE_FAILURE = 3;

  /**
   * 1: Do not add an audio filter to the in-ear monitor.
   */
  public static final int EAR_MONITORING_FILTER_NONE = (1 << 0);
  /**
   * 2: Add an audio filter to the in-ear monitor.
   */
  public static final int EAR_MONITORING_FILTER_BUILT_IN_AUDIO_FILTERS = (1 << 1);
  /**
   * 4: Enable noise suppression to the in-ear monitor.
   */
  public static final int EAR_MONITORING_FILTER_NOISE_SUPPRESSION = (1 << 2);
  /**
   * 32768: Enable audio filters by reuse post-processing filter to the in-ear monitor.
   * This bit is intended to be used in exclusive mode, which means, if this bit is set, all other
   * bits will be disregarded.
   */
  public static final int EAR_MONITORING_FILTER_REUSE_POST_PROCESSING_FILTER = (1 << 15);
  /**
   * 0: connectivity first
   */
  public final static int LOCAL_RPOXY_CONNECTIVITY_FIRST = 0;
  /**
   * 1: local only
   */
  public final static int LOCAL_RPOXY_LOCAL_ONLY = 1;

  /**
   * 0: QoE of the local user is good.
   */
  public final static int EXPERIENCE_QUALITY_GOOD = 0;
  /**
   * 1: QoE of the local user is poor.
   */
  public final static int EXPERIENCE_QUALITY_BAD = 1;

  /**
   * 0: No reason, indicating good QoE of the local user.
   */
  public final static int EXPERIENCE_REASON_NONE = 0;
  /**
   * 1: The remote user's network quality is poor.
   */
  public final static int REMOTE_NETWORK_QUALITY_POOR = 1;
  /**
   * 2: The local user's network quality is poor.
   */
  public final static int LOCAL_NETWORK_QUALITY_POOR = 2;
  /**
   * 4: The local user's Wi-Fi or mobile network signal is weak.
   */
  public final static int WIRELESS_SIGNAL_POOR = 4;
  /**
   * 8: The local user enables both Wi-Fi and bluetooth, and their signals interfere with each
   * other. As a result, audio transmission quality is undermined.
   */
  public final static int WIFI_BLUETOOTH_COEXIST = 8;

  // proxy Transport type
  /**
   * 0: The automatic mode. In this mode, the SDK attempts a direct connection to SD-RTN, and
   * automatically switches to TLS 443 if the attempt fails. As of v3.6.2, the SDK has this mode
   * enabled by default.
   */
  public final static int TRANSPORT_TYPE_NONE_PROXY = 0;
  /**
   * 1: The cloud proxy for the UDP protocol, that is, the Force UDP cloud proxy mode. In this mode,
   * the SDK always transmits data over UDP.
   */
  public final static int TRANSPORT_TYPE_UDP_PROXY = 1;
  /**
   * 2: The cloud proxy for the TCP (encryption) protocol, that is, the Force TCP cloud proxy mode.
   * In this mode, the SDK always transmits data over TLS 443.
   *
   * @since v3.6.2
   */
  public final static int TRANSPORT_TYPE_TCP_PROXY = 2;
  /**
   * 0: Reserved for future use.
   *
   * @since v3.6.2
   */
  public final static int PROXY_TYPE_NONE_PROXY = 0;
  /**
   * 1: The cloud proxy for the UDP protocol, that is, the Force UDP cloud proxy mode. In this mode,
   * the SDK always transmits data over UDP.
   *
   * @since v3.6.2
   */
  public final static int PROXY_TYPE_UDP_CLOUD_PROXY = 1;
  /**
   * 2: The cloud proxy for the TCP (encryption) protocol, that is, the Force TCP cloud proxy mode.
   * In this mode, the SDK always transmits data over TLS 443.
   *
   * @since v3.6.2
   */
  public final static int PROXY_TYPE_TCP_CLOUD_PROXY = 2;
  /**
   * 3: Reserved for future use.
   *
   * @since v3.6.2
   */
  public final static int PROXY_TYPE_LOCAL_PROXY = 3;
  /**
   * 4: The automatic mode. In this mode, the SDK attempts a direct connection to SD-RTN, and
   * automatically switches to TLS 443 if the attempt fails.
   *
   * @since v3.6.2
   */
  public final static int PROXY_TYPE_TCP_AUTO_FALLBACK = 4;
  /**
   * 5: The http proxy.
   *
   * @since v4.2.0
   */
  public final static int PROXY_TYPE_HTTP = 5;
  /**
   * 6: The https proxy.
   *
   * @since v4.2.0
   */
  public final static int PROXY_TYPE_HTTPS = 6;

  /**
   * 16: The user refuses to grant screen capture permission to the application.
   *
   * @since v3.7.0
   */
  public static final int ERR_SCREEN_CAPTURE_PERMISSION_DENIED = 16;
  /**
   * 2: Due to system limitations, screen capture is not available on systems earlier than Android 5
   * (that is, Android API level 21). The SDK reports this error code when you
   * call {@link RtcEngine#startScreenCapture startScreenCapture} on systems earlier than Android 5.
   *
   * @since v3.7.0
   */
  public final static int ERR_SCREEN_CAPTURE_SYSTEM_NOT_SUPPORTED = 2;
  /**
   * 3: Due to system limitations, system audio cannot be captured on systems earlier than Android
   * 10 (that is, API level 29). The SDK reports this error when you call {@link
   * RtcEngine#startScreenCapture startScreenCapture} and set `captureAudio` as `true` on systems
   * later than Android 5 (API level 21) and earlier than Android 10 (API level 29).
   *
   * @since v3.7.0
   */
  public final static int ERR_SCREEN_CAPTURE_SYSTEM_AUDIO_NOT_SUPPORTED = 3;

  public final static int CONTENT_INSPECT_NEUTRAL = 1;

  public final static int CONTENT_INSPECT_SEXY = 2;

  public final static int CONTENT_INSPECT_PORN = 3;

  /**
   * Video codec capability levels.
   *
   * @since v4.2.2
   */

  /** No specified level */
  public final static int CODEC_CAPABILITY_LEVEL_UNSPECIFIED = -1;
  /** Only provide basic support for the codec type */
  public final static int CODEC_CAPABILITY_LEVEL_BASIC_SUPPORT = 5;
  /** Can process 1080p video at a rate of approximately 30 fps. */
  public final static int CODEC_CAPABILITY_LEVEL_1080P30FPS = 10;
  /** Can process 1080p video at a rate of approximately 60 fps. */
  public final static int CODEC_CAPABILITY_LEVEL_1080P60FPS = 20;
  /** Can process 4k video at a rate of approximately 30 fps. */
  public final static int CODEC_CAPABILITY_LEVEL_4K60FPS = 30;

  /**
   * FeatureType
   * @since v4.1.1.3
   */
  public final static int FEATURE_VIDEO_VIRTUAL_BACKGROUND = 1;

  public final static int FEATURE_VIDEO_BEAUTY_EFFECT = 2;

  /**
   * The video track of the video source is not started.
   */
  public final static int VT_ERR_VIDEO_SOURCE_NOT_READY = 1;
  /**
   * The video source type is not supported.
   */
  public final static int VT_ERR_INVALID_VIDEO_SOURCE_TYPE = 2;
  /**
   * The image url is not correctly of image source.
   */
  public final static int VT_ERR_INVALID_IMAGE_PATH = 3;
  /**
   * The image format not the type png/jpeg/gif of image source.
   */
  public final static int VT_ERR_UNSUPPORT_IMAGE_FORMAT = 4;
  /**
   * The layout is invalid such as width is zero.
   */
  public final static int VT_ERR_INVALID_LAYOUT = 5;
  /**
   * Internal error.
   */
  public final static int VT_ERR_INTERNAL = 20;

  /**
   * @brief Reliable Data Transmission Tunnel message stream type
   *
   * @technical preview
   */
  /**
   * Command stream type.
   * Characterized by: reliability, high priority, and not affected by congestion control.
   * Transmission limits: a maximum of 256 bytes per packet, and 100 packets per second.
   */
  public final static int RDT_STREAM_CMD = 0;
  /**
   * Data stream type.
   * Characterized by: reliability, low priority, and affected by congestion control.
   * Transmission limits: a maximum of 128 KBytes per packet, with a rate of 4 Mbps.
   */
  public final static int RDT_STREAM_DATA = 1;
  /**
   * Reliable Data Transmission stream type count
   */
  public final static int RDT_STREAM_COUNT = 2;

  /**
   * @brief Reliable Data Transmission tunnel state
   *
   * @technical preview
   */
  /**
   * The RDT tunnel is in the initial or is closed.
   */
  public final static int RDT_STATE_CLOSED = 0;
  /**
   * The RDT tunnel is open, and data can only be sent in this state.
   */
  public final static int RDT_STATE_OPENED = 1;
  /**
   * The send buffer of the RDT tunnel is full. RDT_STREAM_DATA cannot be sent,
   * but RDT_STREAM_CMD can be sent, as the latter is not affected by congestion control.
   */
  public final static int RDT_STATE_BLOCKED = 2;
  /**
   * The RDT tunnel is in a suspended state because SDK has disconnected.
   * It will automatically resume to the RDT_STATE_OPENED state after rejoining the channel.
   */
  public final static int RDT_STATE_PENDING = 3;
  /**
   * The RDT channel is broken, and the data being sent and received will be cleared.
   * It will automatically resume to the RDT_STATE_OPENED state later.
   * Reason for occurrence: The remote user actively called the API to leave the
   * channel and then rejoined the channel, without being detected by this end.
   */
  public final static int RDT_STATE_BROKEN = 4;

  /** Media type. */
  public enum MediaType {
    /**
     * No audio and video.
     */
    NONE(0),
    /**
     * Audio only.
     */
    AUDIO_ONLY(1),
    /**
     * Video only.
     */
    VIDEO_ONLY(2),
    /**
     * Audio and video.
     */
    AUDIO_AND_VIDEO(3);

    private int value;
    private MediaType(int v) {
      value = v;
    }

    public static int getValue(MediaType type) {
      return type.value;
    }
  }

  /**
   * @brief The audio profile.
   */
  public enum AudioProfile {
    /**
     * 0: The default audio profile.
     * - For the interactive streaming profile: A sample rate of 48 kHz, music encoding, mono, and a
     * bitrate of up to 64 Kbps.
     * - For the communication profile: A sample rate of 32 kHz, audio encoding, mono, and a bitrate of
     * up to 18 Kbps.
     */
    DEFAULT(Constants.AUDIO_PROFILE_DEFAULT),
    /**
     * 1: A sample rate of 32 kHz, audio encoding, mono, and a bitrate of up to 18 Kbps.
     */
    SPEECH_STANDARD(Constants.AUDIO_PROFILE_SPEECH_STANDARD),
    /**
     * 2: A sample rate of 48 kHz, music encoding, mono, and a bitrate of up to 64 Kbps.
     */
    MUSIC_STANDARD(Constants.AUDIO_PROFILE_MUSIC_STANDARD),
    /**
     * 3: A sample rate of 48 kHz, music encoding, stereo, and a bitrate of up to 80 Kbps. To implement
     * stereo audio, you also need to call `setAdvancedAudioOptions` and set `audioProcessingChannels`
     * to `AGORA_AUDIO_STEREO_PROCESSING` in `AdvancedAudioOptions`.
     */
    MUSIC_STANDARD_STEREO(Constants.AUDIO_PROFILE_MUSIC_STANDARD_STEREO),
    /**
     * 4: A sample rate of 48 kHz, music encoding, mono, and a bitrate of up to 96 Kbps.
     */
    MUSIC_HIGH_QUALITY(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY),
    /**
     * 5: A sample rate of 48 kHz, music encoding, stereo, and a bitrate of up to 128 Kbps. To implement
     * stereo audio, you also need to call `setAdvancedAudioOptions` and set `audioProcessingChannels`
     * to `AGORA_AUDIO_STEREO_PROCESSING` in `AdvancedAudioOptions`.
     */
    MUSIC_HIGH_QUALITY_STEREO(Constants.AUDIO_PROFILE_MUSIC_HIGH_QUALITY_STEREO);

    private int value;
    private AudioProfile(int v) {
      value = v;
    }

    public static int getValue(AudioProfile type) {
      return type.value;
    }
  }

  /**
   *
   * Sets the audio application scenarios.
   */
  public enum AudioScenario {
    /**
     * 0: (Recommended) The default audio scenario.
     */
    DEFAULT(Constants.AUDIO_SCENARIO_DEFAULT),
    /**
     * 3: (Recommended) The live gaming scenario, which needs to enable the gaming audio effects in
     * the speaker mode in a live broadcast scenario. Choose this scenario to
     * achieve high-fidelity music playback.
     */
    GAME_STREAMING(Constants.AUDIO_SCENARIO_GAME_STREAMING),
    /**
     * 5: The chatroom scenario, which needs to keep recording when setClientRole to audience.
     * Normally, app developer can also use mute api to achieve the same result,
     * and we implement this 'non-orthogonal' behavior only to make API backward compatible.
     */
    CHATROOM(Constants.AUDIO_SCENARIO_CHATROOM);

    private int value;
    private AudioScenario(int v) {
      value = v;
    }

    public static int getValue(AudioScenario type) {
      return type.value;
    }
  }

  /**
   * @brief The output log level of the SDK.
   */
  public enum LogLevel {
    /**
     * 0: Do not output any log information.
     */
    LOG_LEVEL_NONE(0x0000),
    /**
     * 0x0001: (Default) Output `FATAL`, `ERROR`, `WARN`, and `INFO` level log information. We recommend
     * setting your log filter to this level.
     */
    LOG_LEVEL_INFO(0x0001),
    /**
     * 0x0002: Output `FATAL`, `ERROR`, and `WARN` level log information.
     */
    LOG_LEVEL_WARN(0x0002),
    /**
     * 0x0004: Output `FATAL` and `ERROR` level log information.
     */
    LOG_LEVEL_ERROR(0x0004),
    /**
     * 0x0008: Output `FATAL` level log information.
     */
    LOG_LEVEL_FATAL(0x0008);

    private int value;
    private LogLevel(int v) {
      value = v;
    }

    public static int getValue(LogLevel type) {
      return type.value;
    }
  }

  /**
   * @brief The audio source type.
   */
  public enum AudioSourceType {
    /**
     * 0: (Default) Microphone.
     */
    AUDIO_SOURCE_MICROPHONE(0),
    /**
     * 1: Custom audio stream.
     */
    AUDIO_SOURCE_CUSTOM(1),
    /**
     * 2: Media player.
     */
    AUDIO_SOURCE_MEDIA_PLAYER(2),
    /**
     * 3: System audio stream captured during screen sharing.
     */
    AUDIO_SOURCE_LOOPBACK_RECORDING(3),
    /**
     * 4: Audio of mixed stream from the local client.
     */
    AUDIO_SOURCE_MIXED_STREAM(4),
    /**
     * 5: Audio stream from a specified remote user.
     */
    AUDIO_SOURCE_REMOTE_USER(5),
    /**
     * 6: Mixed audio streams from all users in the current channel.
     */
    AUDIO_SOURCE_REMOTE_CHANNEL(6),
    /**
     * 100: An unknown audio source.
     */
    AUDIO_SOURCE_UNKNOWN(100);

    private int value;
    public int getValue() {
      return this.value;
    }
    private AudioSourceType(int v) {
      value = v;
    }

    public static int getValue(AudioSourceType type) {
      return type.value;
    }

    public static AudioSourceType fromInt(int v) {
      for (AudioSourceType type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return AUDIO_SOURCE_UNKNOWN;
    }
  }

  /**
   * @brief The type of the video source.
   */
  public enum VideoSourceType {
    /**
     * 0: (Default) The primary camera.
     */
    VIDEO_SOURCE_CAMERA_PRIMARY(0),
    /**
     * 1: The secondary camera.
     */
    VIDEO_SOURCE_CAMERA_SECONDARY(1),
    /**
     * 2: The primary screen.
     */
    VIDEO_SOURCE_SCREEN_PRIMARY(2),
    /**
     * 3: The secondary screen.
     */
    VIDEO_SOURCE_SCREEN_SECONDARY(3),
    /**
     * 4: A custom video source.
     */
    VIDEO_SOURCE_CUSTOM(4),
    /**
     * 5: The media player.
     */
    VIDEO_SOURCE_MEDIA_PLAYER(5),
    /**
     * 6: One PNG image.
     */
    VIDEO_SOURCE_RTC_IMAGE_PNG(6),
    /**
     * 7: One JPEG image.
     */
    VIDEO_SOURCE_RTC_IMAGE_JPEG(7),
    /**
     * 8: One GIF image.
     */
    VIDEO_SOURCE_RTC_IMAGE_GIF(8),
    /**
     * 9: One remote video acquired by the network.
     */
    VIDEO_SOURCE_REMOTE(9),
    /**
     * 10: One transcoded video source.
     */
    VIDEO_SOURCE_TRANSCODED(10),
    /**
     * 11: The third camera.
     */
    VIDEO_SOURCE_CAMERA_THIRD(11),
    /**
     * 12: The fourth camera.
     */
    VIDEO_SOURCE_CAMERA_FOURTH(12),
    /**
     * 15: Video processed by the speech driven extension.
     */
    VIDEO_SOURCE_SPEECH_DRIVEN(15),
    /**
     * 100: An unknown video source.
     */
    VIDEO_SOURCE_UNKNOWN(100);

    private int value;
    public int getValue() {
      return this.value;
    }
    private VideoSourceType(int v) {
      value = v;
    }

    public static int getValue(VideoSourceType type) {
      return type.value;
    }

    public static VideoSourceType fromInt(int v) {
      for (VideoSourceType type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return VIDEO_SOURCE_UNKNOWN;
    }
  }

  /**
   * @brief The frame position of the video observer.
   */
  public enum VideoModulePosition {
    /**
     * 1: The location of the locally collected video data after preprocessing corresponds to the
     * `onCaptureVideoFrame` callback. The observed video here has the effect of video pre-processing,
     * which can be verified by enabling image enhancement, virtual background, or watermark.
     */
    VIDEO_MODULE_POSITION_POST_CAPTURER(1),
    /**
     * 2: The pre-renderer position , which corresponds to the video data in the `onRenderVideoFrame`
     * callback.
     */
    VIDEO_MODULE_POSITION_PRE_RENDERER(1 << 1),
    /**
     * 4: The pre-encoder position , which corresponds to the video data in the `onPreEncodeVideoFrame`
     * callback. The observed video here has the effects of video pre-processing and encoding
     * pre-processing.
     * - To verify the pre-processing effects of the video, you can enable image enhancement, virtual
     * background, or watermark.
     * - To verify the pre-encoding processing effect, you can set a lower frame rate (for example, 5
     * fps).
     */
    VIDEO_MODULE_POSITION_PRE_ENCODER(1 << 2),
    /**
     * 8: The position after local video capture and before pre-processing. The observed video here does
     * not have pre-processing effects, which can be verified by enabling image enhancement, virtual
     * background, or watermarks.
     */
    VIDEO_MODULE_POSITION_POST_CAPTURER_ORIGIN(1 << 3);

    private int value;
    private VideoModulePosition(int v) {
      value = v;
    }

    public static int getValue(VideoModulePosition position) {
      return position.value;
    }
  }

  /**
   * @brief The mode in which the video stream is sent.
   */
  public enum SimulcastStreamMode {
    /**
     * -1: By default, do not send the low-quality video stream until a subscription request for the
     * low-quality video stream is received from the receiving end, then automatically start sending
     * low-quality video stream.
     */
    AUTO_SIMULCAST_STREAM(-1),
    /**
     * 0: Never send low-quality video stream.
     */
    DISABLE_SIMULCAST_STREAM(0),
    /**
     * 1: Always send low-quality video stream.
     */
    ENABLE_SIMULCAST_STREAM(1);

    private int value;
    private SimulcastStreamMode(int v) {
      value = v;
    }

    public static int getValue(SimulcastStreamMode type) {
      return type.value;
    }
  }

  /**
   * @brief The type of video streams.
   */
  public enum VideoStreamType {
    /**
     * 0: High-quality video stream, that is, a video stream with the highest resolution and bitrate.
     */
    VIDEO_STREAM_HIGH(0),
    /**
     * 1: Low-quality video stream, that is, a video stream with the lowest resolution and bitrate.
     */
    VIDEO_STREAM_LOW(1),
    /**
     * 4. Video stream layer 1. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_HIGH.
     */
    VIDEO_STREAM_LAYER_1(4),
    /**
     * 5: Video stream layer 2. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_LAYER_1.
     */
    VIDEO_STREAM_LAYER_2(5),
    /**
     * 6: Video stream layer 3. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_LAYER_2.
     */
    VIDEO_STREAM_LAYER_3(6),
    /**
     * 7: Video stream layer 4. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_LAYER_3.
     */
    VIDEO_STREAM_LAYER_4(7),
    /**
     * 8: Video stream layer 5. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_LAYER_4.
     */
    VIDEO_STREAM_LAYER_5(8),
    /**
     * 9: Video stream layer 6. The resolution of this quality level is only lower than that of
     * VIDEO_STREAM_LAYER_5.
     */
    VIDEO_STREAM_LAYER_6(9);
    private int value;
    private VideoStreamType(int v) {
      value = v;
    }

    public int getValue() {
      return value;
    }
  }

  /**
   * @brief Options for handling audio and video stream fallback when network conditions are weak.
   */
  public enum StreamFallbackOptions {
    /**
     * 0: No fallback processing is performed on audio and video streams, the quality of the audio and
     * video streams cannot be guaranteed.
     */
    STREAM_FALLBACK_OPTION_DISABLED(0),
    /**
     * 1: Only receive low-quality (low resolution, low bitrate) video stream.
     */
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LOW(1),
    /**
     * 2: When the network conditions are weak, try to receive the low-quality video stream first. If
     * the video cannot be displayed due to extremely weak network environment, then fall back to
     * receiving audio-only stream.
     */
    STREAM_FALLBACK_OPTION_AUDIO_ONLY(2),
    /**
     3~8: If the receiver SDK uses {@link RtcEngine#setRemoteSubscribeFallbackOption
     setRemoteSubscribeFallbackOption}, it will receive one of the streams from
     agora::rtc::VIDEO_STREAM_LAYER_1 to agora::rtc::VIDEO_STREAM_LAYER_6 if the related layer
     exists when the network condition is poor. The lower bound of fallback depends on the
     STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_X.
   */
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_1(3),
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_2(4),
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_3(5),
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_4(6),
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_5(7),
    STREAM_FALLBACK_OPTION_VIDEO_STREAM_LAYER_6(8);
    private int value;
    private StreamFallbackOptions(int v) {
      value = v;
    }

    public int getValue() {
      return value;
    }
  }

  /**
   * @brief The external video frame encoding type.
   */
  public enum ExternalVideoSourceType {
    /**
     * 0: The video frame is not encoded.
     */
    VIDEO_FRAME(0),
    /**
     * 1: The video frame is encoded.
     */
    ENCODED_VIDEO_FRAME(1);

    private int value;
    private ExternalVideoSourceType(int v) {
      value = v;
    }

    public static int getValue(ExternalVideoSourceType type) {
      return type.value;
    }
  }

  /**
   * @brief The video application scenarios.
   */
  public enum VideoScenario {
    /**
     * 0: (Default) The general scenario.
     */
    APPLICATION_SCENARIO_GENERAL(0),
    /**
     * 1: The meeting scenario.
     * `APPLICATION_SCENARIO_MEETING` (1) is suitable for meeting scenarios. The SDK automatically
     * enables the following strategies:
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
     * dynamically enables or disables it based on the number of subscribers.
     *   - If nobody subscribes to the low-quality stream, the SDK automatically disables it to save
     * upstream bandwidth.
     *   - If someone subscribes to the low-quality stream, the SDK enables the low-quality stream and
     * resets it to the `SimulcastStreamConfig` configuration used in the most recent calling of
     * `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)`. If no configuration has been set by the user previously, the following
     * values are used:
     *     - Resolution: 480 × 272
     *     - Frame rate: 15 fps
     *     - Bitrate: 500 Kbps
     * @note If the user has called `setDualStreamMode(Constants.SimulcastStreamMode mode, SimulcastStreamConfig streamConfig)` to set that never send low-quality video
     * stream ( `DISABLE_SIMULCAST_STREAM` ), the dynamic adjustment of the low-quality stream in
     * meeting scenarios will not take effect.
     */
    APPLICATION_SCENARIO_MEETING(1),
    /**
     * 2: 1v1 video call scenario.
     * `APPLICATION_SCENARIO_1V1` (2) This is applicable to the `one to one live` scenario. To meet the
     * requirements for low latency and high-quality video in this scenario, the SDK optimizes its
     * strategies, improving performance in terms of video quality, first frame rendering, latency on
     * mid-to-low-end devices, and smoothness under weak network conditions.
     * @note This enumeration value is only applicable to the broadcaster vs. broadcaster scenario.
     */
    APPLICATION_SCENARIO_1V1(2),
    /**
     * 3. Live show scenario.
     * `APPLICATION_SCENARIO_LIVESHOW` (3) This is applicable to the `show room` scenario. In this
     * scenario, fast video rendering and high image quality are crucial. The SDK implements several
     * performance optimizations, including automatically enabling accelerated audio and video frame
     * rendering to minimize first-frame latency (no need to call `enableInstantMediaRendering` ), and
     * B-frame encoding to achieve better image quality and bandwidth efficiency. The SDK also provides
     * enhanced video quality and smooth playback, even in poor network conditions or on lower-end
     * devices.
     */
    APPLICATION_SCENARIO_LIVESHOW(3);

    private int value;
    private VideoScenario(int v) {
      value = v;
    }

    public static int getValue(VideoScenario type) {
      return type.value;
    }
  }

  /**
   *
   * Sets the video qoe preference.
   */
  public enum QoEPreference {
    /**
     * 0: Default QoE type, balance the delay, picture quality and fluency.
     */
    VIDEO_QOE_PREFERENCE_BALANCE(1),
    /**
     * 1: lower the e2e delay.
     */
    VIDEO_QOE_PREFERENCE_DELAY_FIRST(2),
    /**
     * 1: picture quality.
     */
    VIDEO_QOE_PREFERENCE_PICTURE_QUALITY_FIRST(3),
    /**
     * 0: more fluency.
     */
    VIDEO_QOE_PREFERENCE_FLUENCY_FIRST(4);

    private int value;
    private QoEPreference(int v) {
      value = v;
    }

    public static int getValue(QoEPreference type) {
      return type.value;
    }
  }

  /**
   * @brief The type of the audio track.
   */
  public enum AudioTrackType {
    /**
     * 0: Mixable audio tracks. This type of audio track supports mixing with other audio streams (such
     * as audio streams captured by microphone) and playing locally or publishing to channels after
     * mixing. The latency of mixable audio tracks is higher than that of direct audio tracks.
     */
    AUDIO_TRACK_MIXABLE(0),
    /**
     * 1: Direct audio tracks. This type of audio track will replace the audio streams captured by the
     * microphone and does not support mixing with other audio streams. The latency of direct audio
     * tracks is lower than that of mixable audio tracks.
     * @note If `AUDIO_TRACK_DIRECT` is specified for this parameter, you must set
     * `publishMicrophoneTrack` to `false` in `ChannelMediaOptions` when calling `joinChannel(String token, String channelId, int uid, ChannelMediaOptions options)` to
     * join the channel; otherwise, joining the channel fails and returns the error code -2.
     */
    AUDIO_TRACK_DIRECT(1);
    private int value;
    private AudioTrackType(int v) {
      value = v;
    }
    public static int getValue(AudioTrackType type) {
      return type.value;
    }
  }

  /**
   * Bytes per sample
   */
  public enum BytesPerSample {
    /**
     * two bytes per sample
     */
    TWO_BYTES_PER_SAMPLE(2);

    private int value;
    private BytesPerSample(int v) {
      value = v;
    }
    public static int getValue(BytesPerSample type) {
      return type.value;
    }
  }

  /**
   * @brief The channel mode.
   */
  public enum AudioMixingDualMonoMode {
    /**
     * 0: Original mode.
     */
    AUDIO_MIXING_DUAL_MONO_AUTO(0),
    /**
     * 1: Left channel mode. This mode replaces the audio of the right channel with the audio of the
     * left channel, which means the user can only hear the audio of the left channel.
     */
    AUDIO_MIXING_DUAL_MONO_L(1),
    /**
     * 2: Right channel mode. This mode replaces the audio of the left channel with the audio of the
     * right channel, which means the user can only hear the audio of the right channel.
     */
    AUDIO_MIXING_DUAL_MONO_R(2),
    /**
     * 3: Mixed channel mode. This mode mixes the audio of the left channel and the right channel, which
     * means the user can hear the audio of the left channel and the right channel at the same time.
     */
    AUDIO_MIXING_DUAL_MONO_MIX(3);

    private int value;
    private AudioMixingDualMonoMode(int v) {
      value = v;
    }

    public static int getValue(AudioMixingDualMonoMode type) {
      return type.value;
    }
  }

  /**
   * @brief Media source type.
   */
  public enum MediaSourceType {
    /**
     * 0: Audio playback device.
     */
    AUDIO_PLAYOUT_SOURCE(0),
    /**
     * 1: Audio capturing device.
     */
    AUDIO_RECORDING_SOURCE(1),
    /**
     * 2: The primary camera.
     */
    PRIMARY_CAMERA_SOURCE(2),
    /**
     * 3: A secondary camera.
     */
    SECONDARY_CAMERA_SOURCE(3),
    /**
     * 4: Video captured by primary screen capturer.
     */
    PRIMARY_SCREEN_SOURCE(4),
    /**
     * 5: Video captured by secondary screen capturer.
     */
    SECONDARY_SCREEN_SOURCE(5),
    /**
     * 6: Custom video source.
     */
    CUSTOM_VIDEO_SOURCE(6),
    /**
     * 7: Video for media player sharing.
     */
    MEDIA_PLAYER_SOURCE(7),
    /**
     * 8: Video for png image.
     */
    RTC_IMAGE_PNG_SOURCE(8),
    /**
     * 9: Video for jpeg image.
     */
    RTC_IMAGE_JPEG_SOURCE(9),
    /**
     * 10: Video for gif image.
     */
    RTC_IMAGE_GIF_SOURCE(10),
    /**
     * 11: Remote video received from network.
     */
    REMOTE_VIDEO_SOURCE(11),
    /**
     * 12: Video for transcoded.
     */
    TRANSCODED_VIDEO_SOURCE(12),
    /**
     * 13: Video processed by the speech driven extension.
     */
    SPEECH_DRIVEN_VIDEO_SOURCE(13),
    /**
     * 100: Unknown media source.
     */
    UNKNOWN_MEDIA_SOURCE(100);

    private int value;
    private MediaSourceType(int v) {
      value = v;
    }

    public static int getValue(MediaSourceType type) {
      if (type != null) {
        return type.value;
      }
      return UNKNOWN_MEDIA_SOURCE.value;
    }
  }
  ;

  /**
   * @brief The midrange frequency for audio equalization.
   */
  public enum AUDIO_EQUALIZATION_BAND_FREQUENCY {
    /**
     * 0: 31 Hz.
     */
    AUDIO_EQUALIZATION_BAND_31(0),
    /**
     * 1: 62 Hz.
     */
    AUDIO_EQUALIZATION_BAND_62(1),
    /**
     * 2: 125 Hz.
     */
    AUDIO_EQUALIZATION_BAND_125(2),
    /**
     * 3: 250 Hz.
     */
    AUDIO_EQUALIZATION_BAND_250(3),
    /**
     * 4: 500 Hz.
     */
    AUDIO_EQUALIZATION_BAND_500(4),
    /**
     * 5: 1 kHz.
     */
    AUDIO_EQUALIZATION_BAND_1K(5),
    /**
     * 6: 2 kHz.
     */
    AUDIO_EQUALIZATION_BAND_2K(6),
    /**
     * 7: 4 kHz.
     */
    AUDIO_EQUALIZATION_BAND_4K(7),
    /**
     * 8: 8 kHz.
     */
    AUDIO_EQUALIZATION_BAND_8K(8),
    /**
     * 9: 16 kHz.
     */
    AUDIO_EQUALIZATION_BAND_16K(9);

    private int value;
    public int getValue() {
      return this.value;
    }
    private AUDIO_EQUALIZATION_BAND_FREQUENCY(int v) {
      value = v;
    }

    public static AUDIO_EQUALIZATION_BAND_FREQUENCY fromInt(int v) {
      for (AUDIO_EQUALIZATION_BAND_FREQUENCY type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief Audio reverberation types.
   */
  public enum AUDIO_REVERB_TYPE {
    /**
     * 0: The level of the dry signal (dB). The value is between -20 and 10.
     */
    AUDIO_REVERB_DRY_LEVEL(0),
    /**
     * 1: The level of the early reflection signal (wet signal) (dB). The value is between -20 and 10.
     */
    AUDIO_REVERB_WET_LEVEL(1),
    /**
     * 2: The room size of the reflection. The value is between 0 and 100.
     */
    AUDIO_REVERB_ROOM_SIZE(2),
    /**
     * 3: The length of the initial delay of the wet signal (ms). The value is between 0 and 200.
     */
    AUDIO_REVERB_WET_DELAY(3),
    /**
     * 4: The reverberation strength. The value is between 0 and 100.
     */
    AUDIO_REVERB_STRENGTH(4);

    private int value;
    public int getValue() {
      return this.value;
    }
    private AUDIO_REVERB_TYPE(int v) {
      value = v;
    }

    public static AUDIO_REVERB_TYPE fromInt(int v) {
      for (AUDIO_REVERB_TYPE type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief Voice AI tuner sound types.
   */
  public enum VOICE_AI_TUNER_TYPE {
    /**
     * 0: Mature male voice. A deep and magnetic male voice.
     */
    VOICE_AI_TUNER_MATURE_MALE(0),
    /**
     * 1: Fresh male voice. A fresh and slightly sweet male voice.
     */
    VOICE_AI_TUNER_FRESH_MALE(1),
    /**
     * 2: Elegant female voice. A deep and charming female voice.
     */
    VOICE_AI_TUNER_ELEGANT_FEMALE(2),
    /**
     * 3: Sweet female voice. A high-pitched and cute female voice.
     */
    VOICE_AI_TUNER_SWEET_FEMALE(3),
    /**
     * 4: Warm male singing. A warm and melodious male voice.
     */
    VOICE_AI_TUNER_WARM_MALE_SINGING(4),
    /**
     * 5: Gentle female singing. A soft and delicate female voice.
     */
    VOICE_AI_TUNER_GENTLE_FEMALE_SINGING(5),
    /**
     * 6: Husky male singing. A unique husky male voice.
     */
    VOICE_AI_TUNER_HUSKY_MALE_SINGING(6),
    /**
     * 7: Warm elegant female singing. A warm and mature female voice.
     */
    VOICE_AI_TUNER_WARM_ELEGANT_FEMALE_SINGING(7),
    /**
     * 8: Powerful male singing. A strong and powerful male voice.
     */
    VOICE_AI_TUNER_POWERFUL_MALE_SINGING(8),
    /**
     * 9: Dreamy female singing. A dreamy and soft female voice.
     */
    VOICE_AI_TUNER_DREAMY_FEMALE_SINGING(9);

    private int value;
    public int getValue() {
      return this.value;
    }
    private VOICE_AI_TUNER_TYPE(int v) {
      value = v;
    }

    public static VOICE_AI_TUNER_TYPE fromInt(int v) {
      for (VOICE_AI_TUNER_TYPE type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * Thread priority type.
   */
  public enum ThreadPriorityType {
    /**
     * 0: Lowest priority.
     */
    LOWEST(0),
    /**
     * 1: Low priority.
     */
    LOW(1),
    /**
     * 2: Normal priority.
     */
    NORMAL(2),
    /**
     * 3: High priority.
     */
    HIGH(3),
    /**
     * 4. Highest priority.
     */
    HIGHEST(4),
    /**
     * 5. Critical priority.
     */
    CRITICAL(5);
    private int value;
    private ThreadPriorityType(int v) {
      value = v;
    }

    public static int getValue(ThreadPriorityType type) {
      return type.value;
    }
  }

  /**
   * @brief The rendering state of the media frame.
   */
  public enum MEDIA_TRACE_EVENT {
    /**
     * 0: The video frame has been rendered.
     */
    MEDIA_TRACE_EVENT_VIDEO_RENDERED(0),
    /**
     * 1: The video frame has been decoded.
     */
    MEDIA_TRACE_EVENT_VIDEO_DECODED(1);
    private int value;
    private MEDIA_TRACE_EVENT(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }

    public static MEDIA_TRACE_EVENT fromInt(int value) {
      for (MEDIA_TRACE_EVENT type : values()) {
        if (type.getValue() == value) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief The screen sharing scenario.
   */
  public enum ScreenScenarioType {
    /**
     * 1: (Default) Document. This scenario prioritizes the video quality of screen sharing and reduces
     * the latency of the shared video for the receiver. If you share documents, slides, and tables, you
     * can set this scenario.
     */
    SCREEN_SCENARIO_DOCUMENT(1),
    /**
     * 2: Game. This scenario prioritizes the smoothness of screen sharing. If you share games, you can
     * set this scenario.
     */
    SCREEN_SCENARIO_GAMING(2),
    /**
     * 3: Video. This scenario prioritizes the smoothness of screen sharing. If you share movies or live
     * videos, you can set this scenario.
     */
    SCREEN_SCENARIO_VIDEO(3);
    private int value;
    private ScreenScenarioType(int v) {
      value = v;
    }

    public static int getValue(ScreenScenarioType type) {
      return type.value;
    }
  }

  /**
   * @brief The transmission mode of data over multiple network paths.
   *
   * @since 4.6.0
   */
  public enum MultipathMode {
    /**
     * 0: Duplicate mode, where data is sent over multiple paths simultaneously.
     * This mode ensures redundancy and can improve reliability in data transmission.
     */
    MULTIPATH_MODE_DUPLICATE(0),
    /**
     * 1: Dynamic transmission mode. The SDK dynamically selects the optimal path for data
     * transmission based on the current network conditions to improve transmission performance.
     */
    MULTIPATH_MODE_DYNAMIC(1);
    private int value;
    private MultipathMode(int v) {
      value = v;
    }

    public static int getValue(MultipathMode mode) {
      return mode.value;
    }
  }

  /**
   * @brief Network path types used in multipath transmission.
   *
   * @since 4.6.0
   */
  public enum MultipathType {
    /**
     * (0): LAN type, indicates data is transmitted over a local area network. Suitable for high-speed,
     * low-latency connections.
     */
    MULTIPATH_TYPE_LAN(0),
    /**
     * (1): Wi-Fi type, indicates data is transmitted over a Wi-Fi network. Suitable for wireless
     * connections with moderate latency.
     */
    MULTIPATH_TYPE_WIFI(1),
    /**
     * (2): Mobile network type, indicates data is transmitted over a mobile network. Suitable for
     * scenarios with higher latency and unstable connections.
     */
    MULTIPATH_TYPE_MOBILE(2),
    /**
     * (99): Unknown type, indicates an unrecognized path type. Can be used for error handling or
     * fallback scenarios.
     */
    MULTIPATH_TYPE_UNKNOWN(99);
    private int value;
    public int getValue() {
      return this.value;
    }
    private MultipathType(int v) {
      value = v;
    }

    public static MultipathType fromInt(int v) {
      for (MultipathType type : values()) {
        if (type.getValue() == v) {
          return type;
        }
      }
      return MULTIPATH_TYPE_UNKNOWN;
    }
  }

  /**
   * @brief Represents the error codes after calling `renewToken`.
   *
   * @since 4.6.0
   */
  public enum RenewTokenErrorCode {
    /**
     * (0): Token updated successfully.
     */
    RENEW_TOKEN_SUCCESS(0),
    /**
     * (1): Token update failed. It is recommended to generate a new Token and retry `renewToken`.
     */
    RENEW_TOKEN_FAILURE(1),
    /**
     * (2): Token update failed because the provided Token has expired. It is recommended to generate a
     * new Token with a longer expiration time and retry `renewToken`.
     */
    RENEW_TOKEN_TOKEN_EXPIRED(2),
    /**
     * (3): Token update failed because the provided Token is invalid. Common reasons include: the
     * project has enabled App Certificate in the Agora Console but did not use a Token when joining the
     * channel; the uid specified in `joinChannel` is inconsistent with the uid used when generating the
     * Token; the channel name specified in `joinChannel` is inconsistent with the one used when
     * generating the Token. It is recommended to check the Token generation process, generate a new
     * Token, and retry `renewToken`.
     */
    RENEW_TOKEN_INVALID_TOKEN(3),
    /**
     * (4): Token update failed because the channel name in the Token does not match the current
     * channel. It is recommended to check the channel name, generate a new Token, and retry
     * `renewToken`.
     */
    RENEW_TOKEN_INVALID_CHANNEL_NAME(4),
    /**
     * (5): Token update failed because the App ID in the Token does not match the current App ID. It is
     * recommended to check the App ID, generate a new Token, and retry `renewToken`.
     */
    RENEW_TOKEN_INCONSISTENT_APPID(5),
    /**
     * (6): Token update was canceled because a new request was initiated, and the previous request was
     * canceled.
     */
    RENEW_TOKEN_CANCELED_BY_NEW_REQUEST(6);
    private int value;
    private RenewTokenErrorCode(int v) {
      value = v;
    }

    public static int getValue(RenewTokenErrorCode code) {
      return code.value;
    }

    public static RenewTokenErrorCode fromInt(int v) {
      for (RenewTokenErrorCode type : values()) {
        if (type.getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  public enum VIDEO_MODULE_TYPE {
    /** Video capture module */
    VIDEO_MODULE_CAPTURER(0),
    /** Video software encoder module */
    VIDEO_MODULE_SOFTWARE_ENCODER(1),
    /** Video hardware encoder module */
    VIDEO_MODULE_HARDWARE_ENCODER(2),
    /** Video software decoder module */
    VIDEO_MODULE_SOFTWARE_DECODER(3),
    /** Video hardware decoder module */
    VIDEO_MODULE_HARDWARE_DECODER(4),
    /** Video render module */
    VIDEO_MODULE_RENDERER(5);
    private int value;
    private VIDEO_MODULE_TYPE(int v) {
      value = v;
    }

    public static int getValue(VIDEO_MODULE_TYPE type) {
      return type.value;
    }
  }
}
