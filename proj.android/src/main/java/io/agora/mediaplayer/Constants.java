//
//  Agora RTC/MEDIA SDK
//
//  Created by Tongjiangyong in 2019-11.
//  Copyright (c) 2019 Agora.io. All rights reserved.
//
package io.agora.mediaplayer;

import io.agora.base.internal.CalledByNative;

public class Constants {
  /**
   * @brief The playback state.
   */
  public enum MediaPlayerState {
    /**
     * -1: The player state is unknown.
     */
    PLAYER_STATE_UNKNOWN(-1),
    /**
     * 0: The default state. The media player returns this state code before you open the media resource
     * or after you stop the playback.
     */
    PLAYER_STATE_IDLE(0),
    /**
     * 1: Opening the media resource.
     */
    PLAYER_STATE_OPENING(1),
    /**
     * 2: Opens the media resource successfully.
     */
    PLAYER_STATE_OPEN_COMPLETED(2),
    /**
     * 3: The media resource is playing.
     */
    PLAYER_STATE_PLAYING(3),
    /**
     * 4: Pauses the playback.
     */
    PLAYER_STATE_PAUSED(4),
    /**
     * 5: The playback is complete.
     */
    PLAYER_STATE_PLAYBACK_COMPLETED(5),
    /**
     * 6: The loop is complete.
     */
    PLAYER_STATE_PLAYBACK_ALL_LOOPS_COMPLETED(6),
    /**
     * 7: The playback stops.
     */
    PLAYER_STATE_STOPPED(7),
    /**
     *Player pausing (internal)
     */
    PLAYER_STATE_PAUSING_INTERNAL(50),
    /**
     * Player stopping (internal)
     */
    PLAYER_STATE_STOPPING_INTERNAL(51),
    /**
     * Player seeking state (internal)
     */
    PLAYER_STATE_SEEKING_INTERNAL(52),
    /**
     * Player getting state (internal)
     */
    PLAYER_STATE_GETTING_INTERNAL(53),
    /**
     * None state for state machine (internal)
     */
    PLAYER_STATE_NONE_INTERNAL(54),
    /**
     * Do nothing state for state machine (internal)
     */
    PLAYER_STATE_DO_NOTHING_INTERNAL(55),
    /**
     * Player set track state (internal)
     */
    PLAYER_STATE_SET_TRACK_INTERNAL(56),
    /**
     * 100: The media player fails to play the media resource.
     */
    PLAYER_STATE_FAILED(100);

    private int value;

    private MediaPlayerState(int v) {
      value = v;
    }

    public static int getValue(MediaPlayerState type) {
      return type.value;
    }

    @CalledByNative("MediaPlayerState")
    public static MediaPlayerState fromNativeIndex(int nativeIndex) {
      return getStateByValue(nativeIndex);
    }

    public static MediaPlayerState getStateByValue(int value) {
      MediaPlayerState[] states = values();
      for (MediaPlayerState state : states) {
        if (state.value == value) {
          return state;
        }
      }
      return PLAYER_STATE_UNKNOWN;
    }
  }

/**
 * @brief Reasons for the changes in the media player status.
 */
  public enum MediaPlayerReason {
    /**
     * 0: No error.
     */
    PLAYER_REASON_NONE(0),
    /**
     * -1: Invalid arguments.
     */
    PLAYER_REASON_INVALID_ARGUMENTS(-1),
    /**
     * -2: Internal error.
     */
    PLAYER_REASON_INTERNAL(-2),
    /**
     * -3: No resource.
     */
    PLAYER_REASON_NO_RESOURCE(-3),
    /**
     * -4: Invalid media resource.
     */
    PLAYER_REASON_INVALID_MEDIA_SOURCE(-4),
    /**
     * -5: The media stream type is unknown.
     */
    PLAYER_REASON_UNKNOWN_STREAM_TYPE(-5),
    /**
     * -6: The object is not initialized.
     */
    PLAYER_REASON_OBJ_NOT_INITIALIZED(-6),
    /**
     * -7: The codec is not supported.
     */
    PLAYER_REASON_CODEC_NOT_SUPPORTED(-7),
    /**
     * -8: Invalid renderer.
     */
    PLAYER_REASON_VIDEO_RENDER_FAILED(-8),
    /**
     * -9: An error with the internal state of the player occurs.
     */
    PLAYER_REASON_INVALID_STATE(-9),
    /**
     * -10: The URL of the media resource cannot be found.
     */
    PLAYER_REASON_URL_NOT_FOUND(-10),
    /**
     * -11: Invalid connection between the player and the Agora Server.
     */
    PLAYER_REASON_INVALID_CONNECTION_STATE(-11),
    /**
     * -12: The playback buffer is insufficient.
     */
    PLAY_REASON_SRC_BUFFER_UNDERFLOW(-12),
    /**
     * -13: The playback is interrupted.
     */
    PLAYER_REASON_INTERRUPTED(-13),
    /**
     * -14: The SDK does not support the method being called.
     */
    PLAYER_REASON_NOT_SUPPORTED(-14),
    /**
     * -15: The authentication information of the media resource is expired.
     */
    PLAYER_REASON_TOKEN_EXPIRED(-15),
    /**
     * ip expired
     */
    PLAYER_REASON_IP_EXPIRED(-16),

    /**
     * -17: An unknown error.
     */
    PLAYER_REASON_UNKNOWN(-17);
    private int value;

    private MediaPlayerReason(int v) {
      value = v;
    }

    public static int getValue(MediaPlayerReason type) {
      return type.value;
    }

    @CalledByNative("MediaPlayerReason")
    public static MediaPlayerReason fromNativeIndex(int nativeIndex) {
      return getErrorByValue(nativeIndex);
    }

    public static MediaPlayerReason getErrorByValue(int value) {
      MediaPlayerReason[] errors = values();
      for (MediaPlayerReason error : errors) {
        if (error.value == value) {
          return error;
        }
      }
      return PLAYER_REASON_UNKNOWN;
    }
  }

/**
 * @brief Media player events.
 */
  public enum MediaPlayerEvent {
    /**
     * -1: An unknown event.
     */
    PLAYER_EVENT_UNKNOWN(-1),
    /**
     * 0: The player begins to seek to a new playback position.
     */
    PLAYER_EVENT_SEEK_BEGIN(0),
    /**
     * 1: The player finishes seeking to a new playback position.
     */
    PLAYER_EVENT_SEEK_COMPLETE(1),
    /**
     * 2: An error occurs when seeking to a new playback position.
     */
    PLAYER_EVENT_SEEK_ERROR(2),
    /**
     * 5: The audio track used by the player has been changed.
     */
    PLAYER_EVENT_AUDIO_TRACK_CHANGED(5),
    /**
     * 6: The currently buffered data is not enough to support playback.
     */
    PLAYER_EVENT_BUFFER_LOW(6),
    /**
     * 7: The currently buffered data is just enough to support playback.
     */
    PLAYER_EVENT_BUFFER_RECOVER(7),
    /**
     * 8: The audio or video playback freezes.
     */
    PLAYER_EVENT_FREEZE_START(8),
    /**
     * 9: The audio or video playback resumes without freezing.
     */
    PLAYER_EVENT_FREEZE_STOP(9),
    /**
     * 10: The player starts switching the media resource.
     */
    PLAYER_EVENT_SWITCH_BEGIN(10),
    /**
     * 11: Media resource switching is complete.
     */
    PLAYER_EVENT_SWITCH_COMPLETE(11),
    /**
     * 12: Media resource switching error.
     */
    PLAYER_EVENT_SWITCH_ERROR(12),
    /**
     * 13: The first video frame is rendered.
     */
    PLAYER_EVENT_FIRST_DISPLAYED(13),
    /**
     * 14: The cached media files reach the limit in number.
     */
    PLAYER_EVENT_REACH_CACHE_FILE_MAX_COUNT(14),
    /**
     * 15: The cached media files reach the limit in aggregate storage space.
     */
    PLAYER_EVENT_REACH_CACHE_FILE_MAX_SIZE(15),
    /**
     * Triggered when a retry is required to open the media
     */
    PLAYER_EVENT_TRY_OPEN_START(16),
    /**
     * Triggered when the retry to open the media is successful
     */
    PLAYER_EVENT_TRY_OPEN_SUCCEED(17),
    /**
     * Triggered when retrying to open media fails
     */
    PLAYER_EVENT_TRY_OPEN_FAILED(18),
    /**
     * Triggered when an http redirect occurs
     * @technical preview
     */
    PLAYER_EVENT_HTTP_REDIRECT(19);

    private int value;

    private MediaPlayerEvent(int v) {
      value = v;
    }

    public static int getValue(MediaPlayerEvent type) {
      return type.value;
    }

    @CalledByNative("MediaPlayerEvent")
    public static MediaPlayerEvent fromNativeIndex(int nativeIndex) {
      return getEventByValue(nativeIndex);
    }

    public static MediaPlayerEvent getEventByValue(int value) {
      MediaPlayerEvent[] events = values();
      for (MediaPlayerEvent event : events) {
        if (event.value == value) {
          return event;
        }
      }
      return PLAYER_EVENT_UNKNOWN;
    }
  }

  /**
   * @brief Events that occur when media resources are preloaded.
   */
  public enum MediaPlayerPreloadEvent {
    /**
     * 0: Starts preloading media resources.
     */
    PLAYER_PRELOAD_EVENT_BEGIN(0),
    /**
     * 1: Preloading media resources is complete.
     */
    PLAYER_PRELOAD_EVENT_COMPLETE(1),
    /**
     * 2: An error occurs when preloading media resources.
     */
    PLAYER_PRELOAD_EVENT_ERROR(2);

    private int value;

    private MediaPlayerPreloadEvent(int v) {
      value = v;
    }

    public static int getValue(MediaPlayerPreloadEvent type) {
      return type.value;
    }

    @CalledByNative("MediaPlayerPreloadEvent")
    public static MediaPlayerPreloadEvent fromNativeIndex(int nativeIndex) {
      return getTypeByValue(nativeIndex);
    }

    public static MediaPlayerPreloadEvent getTypeByValue(int value) {
      MediaPlayerPreloadEvent[] evnets = values();
      for (MediaPlayerPreloadEvent event : evnets) {
        if (event.value == value) {
          return event;
        }
      }
      return PLAYER_PRELOAD_EVENT_ERROR;
    }
  }

  /**
   * @brief The type of media metadata.
   */
  public enum MediaPlayerMetadataType {
    /**
     * 0: The type is unknown.
     */
    PLAYER_METADATA_TYPE_UNKNOWN(0),
    /**
     * 1: The type is SEI.
     */
    PLAYER_METADATA_TYPE_SEI(1);

    private int value;

    private MediaPlayerMetadataType(int v) {
      value = v;
    }

    public static int getValue(MediaPlayerMetadataType type) {
      return type.value;
    }

    @CalledByNative("MediaPlayerMetadataType")
    public static MediaPlayerMetadataType fromNativeIndex(int nativeIndex) {
      return getTypeByValue(nativeIndex);
    }

    public static MediaPlayerMetadataType getTypeByValue(int value) {
      MediaPlayerMetadataType[] types = values();
      for (MediaPlayerMetadataType type : types) {
        if (type.value == value) {
          return type;
        }
      }
      return PLAYER_METADATA_TYPE_UNKNOWN;
    }
  }
  /**
   * @brief The type of the media stream.
   */
  public enum MediaStreamType {
    /**
     * 0: The type is unknown.
     */
    STREAM_TYPE_UNKNOWN(0),
    /**
     * 1: The video stream.
     */
    STREAM_TYPE_VIDEO(1),
    /**
     * 2: The audio stream.
     */
    STREAM_TYPE_AUDIO(2),
    /**
     * 3: The subtitle stream.
     */
    STREAM_TYPE_SUBTITLE(3);

    private int value;

    private MediaStreamType(int v) {
      value = v;
    }

    public static int getValue(MediaStreamType type) {
      return type.value;
    }
  }

  /**
   * @brief The channel mode.
   */
  public enum AudioDualMonoMode {
    /**
     * 0: Original mode.
     */
    AUDIO_DUAL_MONO_STEREO(0),
    /**
     * 1: Left channel mode. This mode replaces the audio of the right channel with the audio of the
     * left channel, which means the user can only hear the audio of the left channel.
     */
    AUDIO_DUAL_MONO_L(1),
    /**
     * 2: Right channel mode. This mode replaces the audio of the left channel with the audio of the
     * right channel, which means the user can only hear the audio of the right channel.
     */
    AUDIO_DUAL_MONO_R(2),
    /**
     * 3: Mixed channel mode. This mode mixes the audio of the left channel and the right channel, which
     * means the user can hear the audio of the left channel and the right channel at the same time.
     */
    AUDIO_DUAL_MONO_MIX(3);

    private int value;
    private AudioDualMonoMode(int v) {
      value = v;
    }

    public static int getValue(AudioDualMonoMode mode) {
      return mode.value;
    }
  }

  public static final int PLAYER_REASON_NOT_INIT = -1;

  /**
   * @brief Media mode type
   */
  public static final int PLAYER_RENDER_MODE_HIDDEN = 1;
  public static final int PLAYER_RENDER_MODE_FIT = 2;
  public static final int PLAYER_RENDER_MODE_ADAPTIVE = 3;
}
