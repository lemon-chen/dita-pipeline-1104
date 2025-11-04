package io.agora.rtc2;
import io.agora.base.internal.CalledByNative;
// Media Recorder Callback Methods
/**
 * The `IMediaRecorderCallback` interface.
 *
 * @since v4.0.0
 */
public interface IMediaRecorderCallback {
  /**
   * @brief Callback for changes in recording state.
   *
   * @details
   * The SDK triggers this callback when the recording state of the audio and video stream changes. It reports the current state of the recording and the reason for the change.
   *
   * @param channelId Name of the channel.
   * @param uid User ID.
   * @param state Current recording state:
   * - RECORDER_STATE_ERROR (-1): An error occurred during recording of the audio and video stream.
   * - RECORDER_STATE_START (2): Recording of the audio and video stream starts.
   * - RECORDER_STATE_STOP (3): Recording of the audio and video stream stops.
   * @param reason Reason for the recording state change:
   * - RECORDER_REASON_NONE (0): Everything is working normally.
   * - RECORDER_REASON_WRITE_FAILED (1): Failed to write the recording file.
   * - RECORDER_REASON_NO_STREAM (2): No available audio and video stream to record or the stream was interrupted for more than 5 seconds.
   * - RECORDER_REASON_OVER_MAX_DURATION (3): The recording duration exceeds the maximum limit.
   * - RECORDER_REASON_CONFIG_CHANGED (4): The recording configuration has changed.
   *
   */
  @CalledByNative void onRecorderStateChanged(String channelId, int uid, int state, int reason);

  /**
   * @brief Recording information update callback.
   *
   * @details
   * After you successfully register this callback and start audio and video stream recording, 
   * the SDK periodically triggers this callback based on the value of `recorderInfoUpdateInterval` 
   * that you set in `MediaRecorderConfiguration`, reporting the current recording file's name, duration, and size.
   *
   * @param channelId The channel name.
   * @param uid The user ID.
   * @param info The recording file information. See `RecorderInfo` for details.
   *
   */
  @CalledByNative void onRecorderInfoUpdated(String channelId, int uid, RecorderInfo info);
}
