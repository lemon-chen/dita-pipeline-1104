package io.agora.rtc2;

import androidx.annotation.NonNull;

import io.agora.rtc2.internal.RtcEngineImpl;
import java.lang.ref.WeakReference;

/**
 * The `AgoraMediaRecorder` class, for recording the audio and video on the client.
 * `AgoraMediaRecorder` can record the following content:
 * - The audio captured by the local microphone and encoded in AAC format by the SDK.
 * - The video captured by the local camera and encoded by the SDK.
 *
 * @since v3.5.2
 *
 * @note
 * In the `COMMUNICATION` channel profile, this function is unavailable when there are users using
 * versions of the SDK earlier than v3.0.0 in the channel.
 */
public class AgoraMediaRecorder {
  /** -1: An error occurs during the recording. See `error` message for the reason. */
  public static final int RECORDER_STATE_ERROR = -1;
  /** 2: The audio and video recording is started. */
  public static final int RECORDER_STATE_START = 2;
  /** 3: The audio and video recording is stopped. */
  public static final int RECORDER_STATE_STOP = 3;

  /** 0: No error occurs. */
  public static final int RECORDER_REASON_NONE = 0;
  /** 1: The SDK fails to write the recorded data to a file. */
  public static final int RECORDER_REASON_WRITE_FAILED = 1;
  /**
   * 2: The SDK does not detect audio and video streams to be recorded, or audio and video streams
   * are interrupted for more than five seconds during recording.
   */
  public static final int RECORDER_REASON_NO_STREAM = 2;
  /** 3: The recording duration exceeds the upper limit. */
  public static final int RECORDER_REASON_OVER_MAX_DURATION = 3;
  /** 4: The recording configuration changes. */
  public static final int RECORDER_REASON_CONFIG_CHANGED = 4;

  /** 1: Record audio only. */
  public static final int STREAM_TYPE_AUDIO = 0x1;
  /** 2: Record video only. */
  public static final int STREAM_TYPE_VIDEO = 0x2;
  /** 3: Record both audio and video. */
  public static final int STREAM_TYPE_BOTH = STREAM_TYPE_AUDIO | STREAM_TYPE_VIDEO;

  /** 1: MP4 format. */
  public static final int CONTAINER_MP4 = 1;

  /**
   * @brief Audio and video stream recording configuration.
   *
   * @since v3.5.2
   */
  public static class MediaRecorderConfiguration {
    /**
     * The absolute path where the recording file is saved locally. The path must include the file name and extension. For example:
     * - Android: `/storage/emulated/0/Android/data/<package name>/files/example.mp4`
     * @note Make sure the specified path exists and is writable.
     */
    public String storagePath;
    /**
     * The format of the recording file. Currently, only `CONTAINER_MP4` is supported.
     */
    public int containerFormat = CONTAINER_MP4;
    /**
     * Recording content:
     * - `STREAM_TYPE_AUDIO`: Audio only.
     * - `STREAM_TYPE_VIDEO`: Video only.
     * - `STREAM_TYPE_BOTH`: (Default) Both audio and video.
     */
    public int streamType = STREAM_TYPE_BOTH;
    /**
     * Maximum recording duration in milliseconds. The default value is 120000.
     */
    public int maxDurationMs = 120000;
    /**
     * Interval for updating recording information, in milliseconds. The valid range is [1000, 10000]. The SDK triggers the `onRecorderInfoUpdated` callback based on this value to report the updated recording information.
     */
    public int recorderInfoUpdateInterval = 0;
    /**
     * Width (px) of the recorded video. The maximum value of width × height must not exceed 3840 × 2160.
     *                     This parameter is required only when you call `createMediaRecorder` and set the `recorderStreamType`
     * of `RecorderStreamInfo` to 0.
     */
    public int width;
    /**
     * Height (px) of the recorded video. The maximum value of width × height must not exceed 3840 × 2160.
     *                     This parameter is required only when you call `createMediaRecorder` and set the `recorderStreamType`
     * of `RecorderStreamInfo` to 1.
     */
    public int height;
    /**
     * Frame rate for recording video. The maximum value must not exceed 30, such as: 5, 10, 15, 24, 30, etc.
     *                     You only need to pass this parameter when calling `createMediaRecorder` and setting the `recorderStreamType`
     * of `RecorderStreamInfo` to 1.
     */
    public int fps;
    /**
     * Sampling rate (Hz) for recording audio. You can set it to 16000, 32000, 44100, or 48000.
     * This parameter is required only when you call `createMediaRecorder` and set the `recorderStreamType` 
     * of `RecorderStreamInfo` to 1.
     */
    public int sample_rate;
    /**
     * Number of channels for audio capturing:
     * - 1: Mono
     * - 2: Stereo
     * This parameter is required only when you call `createMediaRecorder` and set the `recorderStreamType` in `RecorderStreamInfo` to 1.
     */
    public int channel_num;
    /**
     * The type of the video source for recording. See `VideoSourceType`.
     *                     You only need to specify this parameter when calling `createMediaRecorder` and setting
     * the `recorderStreamType` of `RecorderStreamInfo` to 1.
     */
    public int videoSourceType;

    public MediaRecorderConfiguration(String storagePath, int containerFormat, int streamType,
        int maxDurationMs, int recorderInfoUpdateInterval) {
      this.storagePath = storagePath;
      this.containerFormat = containerFormat;
      this.streamType = streamType;
      this.maxDurationMs = maxDurationMs;
      this.recorderInfoUpdateInterval = recorderInfoUpdateInterval;
      this.width = 1280;
      this.height = 720;
      this.fps = 30;
      this.sample_rate = 48000;
      this.channel_num = 1;
      this.videoSourceType = 0;
    }

    public MediaRecorderConfiguration(String storagePath, int containerFormat, int streamType,
        int maxDurationMs, int recorderInfoUpdateInterval, int width, int height, int fps,
        int sample_rate, int channel_num, int videoSourceType) {
      this.storagePath = storagePath;
      this.containerFormat = containerFormat;
      this.streamType = streamType;
      this.maxDurationMs = maxDurationMs;
      this.recorderInfoUpdateInterval = recorderInfoUpdateInterval;
      this.width = width;
      this.height = height;
      this.fps = fps;
      this.sample_rate = sample_rate;
      this.channel_num = channel_num;
      this.videoSourceType = videoSourceType;
    }
  }

  WeakReference<RtcEngineImpl> mEngineReference;

  String mChannelId;

  @NonNull RecorderStreamInfo mStreamInfo;

  public AgoraMediaRecorder(RtcEngineImpl engine, @NonNull RecorderStreamInfo info) {
    mEngineReference = new WeakReference<RtcEngineImpl>(engine);
    mStreamInfo = info;
    if (info.channelId == null) {
      mStreamInfo.channelId = "";
    }
  }

  static String getChannelId(RecorderStreamInfo info) {
    return (info != null ? info.channelId : null);
  }

  static int getUserId(RecorderStreamInfo info) {
    return (info != null ? info.uid : Constants.DEFAULT_CONNECTION_ID);
  }

  static int getStreamType(RecorderStreamInfo info) {
    return (info != null ? info.recorderStreamType : 0);
  }
  /**
   * @brief Registers an `IMediaRecorderCallback` observer.
   *
   * @since v4.0.0
   *
   * @details
   * This method sets the callback for audio and video recording, so that the app can be notified of the recording status and information of the audio and video streams during the recording process.
   * Before calling this method, make sure that:
   * - The `RtcEngine` object has been created and initialized.
   * - The audio and video recording object has been created via `createMediaRecorder`.
   *
   * @param callback The callback for audio and video stream recording. See `IMediaRecorderCallback` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public int setMediaRecorderObserver(IMediaRecorderCallback callback) {
    RtcEngineImpl engine = null;
    if (mEngineReference == null || (engine = mEngineReference.get()) == null) {
      mEngineReference = null;
      return -7;
    }
    return engine.setMediaRecorderObserver(callback, getUserId(mStreamInfo),
        getChannelId(mStreamInfo), true, getStreamType(mStreamInfo));
  }

  /**
   * @brief Starts recording audio and video streams.
   *
   * @details
   * This method starts recording audio and video streams. The Agora SDK supports recording both local and remote users' audio and video streams simultaneously.
   * Before starting the recording, make sure that:
   * - You have created a media recorder object using `createMediaRecorder`.
   * - You have registered a media recorder observer using `setMediaRecorderObserver` to listen for recording-related callbacks.
   * - You have joined a channel.
   * 
   * This method supports recording the following data:
   * - Audio captured by the microphone in AAC format.
   * - Video captured by the camera in H.264 or H.265 format.
   * 
   * After recording starts, if the video resolution changes during the recording process, the SDK stops the recording. If the audio sample rate or number of channels changes, the SDK continues recording and generates a single MP4 file.
   * A recording file is successfully generated only when recordable audio or video streams are detected. If no recordable stream is available, or if the audio or video stream is interrupted for more than 5 seconds during recording, the SDK stops the recording and triggers the `onRecorderStateChanged` (`RECORDER_STATE_ERROR, RECORDER_REASON_NO_STREAM`) callback.
   *
   * @note
   * - If you want to record the local audio and video streams, make sure the local user role is set to broadcaster before starting the recording.
   * - If you want to record remote users' audio and video streams, make sure you have subscribed to the remote users' audio and video streams before starting the recording.
   *
   * @param config The configuration for audio and video stream recording. See `MediaRecorderConfiguration`.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting.
   *   - -2: Invalid parameter. Make sure that:
   *     - The specified file path for saving the recording is correct and writable.
   *     - The specified recording file format is correct.
   *     - The maximum recording duration is set correctly.
   *   - -4: The current state of `RtcEngine` does not support this operation. This may occur if recording is already in progress or if it stopped due to an error.
   *   - -7: The method is called before `RtcEngine` is initialized. Make sure you have created the `AgoraMediaRecorder` object before calling this method.
   */
  public int startRecording(MediaRecorderConfiguration config) {
    RtcEngineImpl engine = null;
    if (mEngineReference == null || (engine = mEngineReference.get()) == null) {
      mEngineReference = null;
      return -7;
    }
    return engine.startRecording(config.storagePath, config.containerFormat, config.streamType,
        config.maxDurationMs, config.recorderInfoUpdateInterval, getUserId(mStreamInfo),
        getChannelId(mStreamInfo), true, getStreamType(mStreamInfo), config.width, config.height,
        config.fps, config.sample_rate, config.channel_num, config.videoSourceType);
  }

  /**
   * @brief Stops recording audio and video streams.
   *
   * @note After calling `startRecording`, if you want to stop the recording, you must call this method; otherwise, the generated recording file may not play properly.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails:
   *   - -7: The method is called before the `RtcEngine` is initialized. Make sure that the `Recorder` object has been created before calling this method.
   */
  public int stopRecording() {
    RtcEngineImpl engine = null;
    if (mEngineReference == null || (engine = mEngineReference.get()) == null) {
      mEngineReference = null;
      return -7;
    }
    return engine.stopRecording(
        getChannelId(mStreamInfo), getUserId(mStreamInfo), true, getStreamType(mStreamInfo));
  }

  public void release() {
    RtcEngineImpl engine = null;
    if (mEngineReference != null && (engine = mEngineReference.get()) != null
        && mStreamInfo != null) {
      engine.releaseRecorder(
          getChannelId(mStreamInfo), getUserId(mStreamInfo), mStreamInfo.recorderStreamType);
    }
    mEngineReference = null;
    return;
  }
}
