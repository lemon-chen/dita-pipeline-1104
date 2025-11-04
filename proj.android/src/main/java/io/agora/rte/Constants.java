package io.agora.rte;

/**
 * The Constants class.
 * @since v4.4.0
 */
public class Constants {
  /**
   * @brief The state of the media player.
   *
   * @since v4.4.0
   */
  public enum PlayerState {
    /**
     * 0: Idle link state.
     */
    IDLE(0),
    /**
     * 1: Opening the URL resource. This status code is reported after you call `openWithUrl`.
     */
    OPENING(1),
    /**
     * 2: Successfully opens the URL resource. This status code is reported when `openWithUrl` successfully opens the URL resource.
     */
    OPEN_COMPLETED(2),
    /**
     * 3: Playing.
     */
    PLAYING(3),
    /**
     * 4: Pauses playback. This status code is reported when `pause` is successfully called.
     */
    PAUSED(4),
    /**
     * 5: Playback completed.
     */
    PLAYBACK_COMPLETED(5),
    /**
     * 6: Stop playback. This status code is reported when you successfully call `stop`.
     */
    STOPPED(6),
    /**
     * 7: Failure state. This status code is reported when an internal error occurs. If you receive this status, you need to first call `stop` and then call `openWithUrl` to reopen the resource.
     */
    FAILED(7);

    private int value;

    private PlayerState(int value) {
      this.value = value;
    }

    public static int getValue(PlayerState type) {
      return type.value;
    }

    public static PlayerState fromInt(int v) {
      for (PlayerState type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief Media player event types.
   *
   * @since v4.4.0
   */
  public enum PlayerEvent {
    /**
     * 0: Starts seeking to the specified position for playback.
     */
    SEEK_BEGIN(0),
    /**
     * 1: Jump to the specified position completed.
     */
    SEEK_COMPLETE(1),
    /**
     * 2: An error occurs when seeking to the specified playback position.
     */
    SEEK_ERROR(2),
    /**
     * 3: The currently buffered data is not sufficient for playback.
     */
    BUFFER_LOW(3),
    /**
     * 4: The currently buffered data is just enough to support playback.
     */
    BUFFER_RECOVER(4),
    /**
     * 5: Audio or video stutters.
     */
    FREEZE_START(5),
    /**
     * 6: Both audio and video stop freezing.
     */
    FREEZE_STOP(6),
    /**
     * 7: A single loop playback completes.
     */
    ONE_LOOP_PLAYBACK_COMPLETED(7),
    /**
     * 8: The Token is about to expire. After receiving this event, you need to regenerate a new Token and update the URL through `openWithUrl`.
     */
    AUTHENTICATION_WILL_EXPIRE(8),
    /**
     * 9: Due to network issues, falls back from receiving audio and video streams to receiving only the remote audio stream.
     */
    ABR_FALLBACK_TO_AUDIO_ONLY_LAYER(9),
    /**
     * 10: After the network connection is restored, recovers from receiving only the remote audio stream to receiving both audio and video streams.
     */
    ABR_RECOVER_FROM_AUDIO_ONLY_LAYER(10),

    /**
     * 11: Starts switching to a new URL.
     */
    SWITCH_BEGIN(11),
    /**
     * 12: Switch to the new URL is complete.
     */
    SWITCH_COMPLETE(12),
    /**
     * 13: An error occurs when switching to a new URL.
     */
    SWITCH_ERROR(13),
    /**
     * 14: The first video frame has been displayed.
     */
    FIRST_DISPLAYED(14),
    /**
     * 15: The number of cached files reaches the upper limit.
     */
    REACH_CACHE_FILE_MAX_COUNT(15),
    /**
     * 16: The cache file size reaches the upper limit.
     */
    REACH_CACHE_FILE_MAX_SIZE(16),
    /**
     * 17: Start attempting to open a new URL.
     */
    TRY_OPEN_START(17),
    /**
     * 18: Successfully opens a new URL.
     */
    TRY_OPEN_SUCCEED(18),
    /**
     * 19: Failed to open a new URL.
     */
    TRY_OPEN_FAILED(19),
    /**
     * 20: The current audio track changes.
     */
    AUDIO_TRACK_CHANGED(20);

    private int value;

    private PlayerEvent(int value) {
      this.value = value;
    }

    public static int getValue(PlayerEvent type) {
      return type.value;
    }

    public static PlayerEvent fromInt(int v) {
      for (PlayerEvent type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief RTE-related status codes and error codes.
   *
   * @since v4.4.0
   */
  public enum ErrorCode {
    /**
     * 0: Operation succeeds.
     */
    OK(0),
    /**
     * 1: General error (the cause of the error is not clearly categorized).
     */
    DEFAULT(1),
    /**
     * 2: An invalid parameter is set in the method. For example, the parameter contains illegal characters, or the passed-in object is null. Please reset the parameter.
     */
    INVALID_ARGUMENT(2),
    /**
     * 3: The current state does not support this operation, for example, the object has been destroyed or is invalid.
     */
    INVALID_OPERATION(3),
    /**
     * 4: Network error. Please check your network.
     */
    NETWORK_ERROR(4),
    /**
     * 5: Token authentication failed. Possible reasons include an invalid Token or an expired Token. Please check your Token.
     */
    AUTHENTICATION_FAILED(5),
    /**
     * 6: Media stream not found. For example, after joining the channel, no media stream is received from the host for more than 10 seconds.
     */
    STREAM_NOT_FOUND(6);

    private int value;

    private ErrorCode(int value) {
      this.value = value;
    }

    public static int getValue(ErrorCode type) {
      return type.value;
    }

    public static ErrorCode fromInt(int v) {
      for (ErrorCode type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief Video rendering mode.
   *
   * @since v4.4.0
   */
  public enum VideoRenderMode {
    /**
     * 0: Scales the video dimensions proportionally. Prioritizes filling the viewport. Any excess video content resulting from a mismatch between the video dimensions and the display viewport will be cropped.
     */
    HIDDEN(0),
    /**
     * 1: Scales the video dimensions proportionally, prioritizing full display of the video content.  
     * Black bars are added to fill any unoccupied areas of the display viewport caused by mismatched video and viewport dimensions.
     */
    FIT(1);

    private int value;

    private VideoRenderMode(int value) {
      this.value = value;
    }

    public static int getValue(VideoRenderMode type) {
      return type.value;
    }

    public static VideoRenderMode fromInt(int v) {
      for (VideoRenderMode type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief Video mirror mode.
   *
   * @since v4.4.0
   */
  public enum VideoMirrorMode {
    /**
     * 0: The SDK determines the mirror mode.
     * - Local video view mirror mode: If you use the front-facing camera, the SDK enables the local video view mirror mode by default; if you use the rear-facing camera, the SDK disables the local video view mirror mode by default.
     * - Remote user view mirror mode: The mirror mode for remote users is disabled by default.
     */
    AUTO(0),
    /**
     * 1: Enables mirror mode.
     */
    ENABLED(1),
    /**
     * 2: Disables mirror mode.
     */
    DISABLED(2);

    private int value;

    private VideoMirrorMode(int value) {
      this.value = value;
    }

    public static int getValue(VideoMirrorMode type) {
      return type.value;
    }

    public static VideoMirrorMode fromInt(int v) {
      for (VideoMirrorMode type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * Metadata type
   * @since v4.4.0
   */
  public enum PlayerMetadataType {
    /**
     * SEI type
     */
    SEI(0);

    private int value;

    private PlayerMetadataType(int value) {
      this.value = value;
    }

    public static int getValue(PlayerMetadataType type) {
      return type.value;
    }

    public static PlayerMetadataType fromInt(int v) {
      for (PlayerMetadataType type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief The quality layer of the subscribed video stream.
   *
   * @since v4.4.0
   *
   * @note If you need to customize the video resolution of `LAYER1` to `LAYER6` and subscribe to it, please contact technical support to enable the ABR feature.
   *
   */
  public enum AbrSubscriptionLayer {
    /**
     * 0: The highest quality high-quality video stream. This quality level provides the highest resolution.
     */
    HIGH(0),
    /**
     * 1: (Default) The lowest-quality video stream. This quality level has the lowest resolution.
     */
    LOW(1),
    /**
     * 2: Video quality level 1. The resolution of this quality level is only lower than `HIGH`.
     */
    LAYER1(2),
    /**
     * 3: Video quality level 2. The resolution of this quality level is lower than `LAYER1`.
     */
    LAYER2(3),
    /**
     * 4: Video quality level 3. The resolution of this level is lower than `LAYER2`.
     */
    LAYER3(4),
    /**
     * 5: Video quality level 4. The resolution of this quality level is lower than `LAYER3`.
     */
    LAYER4(5),
    /**
     * 6: Video quality level 5. The resolution of this quality level is lower than `LAYER4`.
     */
    LAYER5(6),
    /**
     * 7: Video quality level 6. The resolution of this quality level is lower than `LAYER5`.
     */
    LAYER6(7);

    private int value;

    private AbrSubscriptionLayer(int value) {
      this.value = value;
    }

    public static int getValue(AbrSubscriptionLayer type) {
      return type.value;
    }

    public static AbrSubscriptionLayer fromInt(int v) {
      for (AbrSubscriptionLayer type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }

  /**
   * @brief The quality level for video stream fallback.
   *
   * @since v4.4.0
   *
   * @note
   * Notes:
   * - To use `LAYER1` through `LAYER6`, you need to `contact technical support` to enable the ABR feature. Once enabled, you can customize the resolution for each level and select any level as the **lowest resolution** for video stream fallback. When the network condition is poor, the SDK uses this level as the lower bound and dynamically adjusts the resolution within this range based on network conditions.
   * - When customizing resolutions, make sure to sort the video quality levels (Layer) in descending order by resolution. If resolutions are the same, sort them in descending order by frame rate.
   *
   */
  public enum AbrFallbackLayer {
    /**
     * 0: Does not perform fallback processing on audio and video streams, but cannot guarantee the quality of the audio and video streams.
     */
    DISABLED(0),
    /**
     * 1: (Default) Falls back to the low-quality video stream. This layer has the lowest resolution.
     */
    LOW(1),
    /**
     * 2: First attempts to receive only the low-quality video stream; if the video cannot be displayed due to poor network conditions, then falls back to receiving only the subscribed remote audio stream.
     */
    AUDIO_ONLY(2),
    /**
     * 3: Falls back to video quality level 1. The resolution and bitrate of this level are only lower than those of the highest-quality video stream you subscribe to.
     */
    LAYER1(3),
    /**
     * 4: Falls back to video quality level 2. The resolution of this level is only lower than that of level 1.
     */
    LAYER2(4),
    /**
     * 5: Falls back to video quality level 3. This level has a resolution just lower than level 2.
     */
    LAYER3(5),
    /**
     * 6: Fallback to video quality level 4. The resolution of this level is only lower than that of level 3.
     */
    LAYER4(6),
    /**
     * 7: Falls back to video quality level 5. This level has a resolution just lower than level 4.
     */
    LAYER5(7),
    /**
     * 8: Falls back to video quality level 6. This level has a resolution just lower than level 5.
     */
    LAYER6(8);

    private int value;

    private AbrFallbackLayer(int value) {
      this.value = value;
    }

    public static int getValue(AbrFallbackLayer type) {
      return type.value;
    }

    public static AbrFallbackLayer fromInt(int v) {
      for (AbrFallbackLayer type : values()) {
        if (getValue(type) == v) {
          return type;
        }
      }
      return null;
    }
  }
}
