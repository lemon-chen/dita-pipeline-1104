package io.agora.mediaplayer;

import io.agora.base.internal.CalledByNative;
import io.agora.mediaplayer.Constants;
import io.agora.mediaplayer.data.CacheStatistics;
import io.agora.mediaplayer.data.PlayerPlaybackStats;
import io.agora.mediaplayer.data.PlayerUpdatedInfo;
import io.agora.mediaplayer.data.SrcInfo;

/**
 * @brief The media player observer interface.
 */
public interface IMediaPlayerObserver {
  /**
   * @brief Reports the changes of playback state.
   *
   * @details
   * When the state of the media player changes, the SDK triggers this callback to report the current
   * playback state.
   *
   * @param state The playback state. See `MediaPlayerState`.
   * @param reason The reason for the changes in the media player status. See `MediaPlayerReason`.
   *
   */
  @CalledByNative
  void onPlayerStateChanged(Constants.MediaPlayerState state, Constants.MediaPlayerReason reason);

  /**
   * @brief Reports the playback progress of the media file.
   *
   * @details
   * When playing media files, the SDK triggers this callback every two second to report current
   * playback progress.
   *
   * @param positionMs The playback position (ms) of media files.
   * @param timeStampMs The NTP timestamp (ms) of the current playback progress.
   *
   */
  @CalledByNative void onPositionChanged(long positionMs, long timestampMs);

  /**
   * @brief Reports the player events.
   *
   * @details
   * - After calling the `seek` method, the SDK triggers the callback to report the results of the
   * seek operation.
   *
   * @param eventCode The player event. See `MediaPlayerEvent`.
   * @param elapsedTime The time (ms) when the event occurs.
   * @param message Information about the event.
   *
   */
  @CalledByNative
  void onPlayerEvent(Constants.MediaPlayerEvent eventCode, long elapsedTime, String message);

  /**
   * @brief Occurs when the media metadata is received.
   *
   * @details
   * The callback occurs when the player receives the media metadata and reports the detailed
   * information of the media metadata.
   *
   * @param type The type of media metadata. See `MediaPlayerMetadataType`.
   * @param data The detailed data of the media metadata.
   *
   */
  @CalledByNative void onMetaData(Constants.MediaPlayerMetadataType type, byte[] data);

  /**
   * @brief Reports the playback duration that the buffered data can support.
   *
   * @details
   * When playing online media resources, the SDK triggers this callback every two seconds to report
   * the playback duration that the currently buffered data can support.
   * - When the playback duration supported by the buffered data is less than the threshold (0 by
   * default), the SDK returns `PLAYER_EVENT_BUFFER_LOW` (6).
   * - When the playback duration supported by the buffered data is greater than the threshold (0 by
   * default), the SDK returns `PLAYER_EVENT_BUFFER_RECOVER` (7).
   *
   * @param playCachedBuffer The playback duration (ms) that the buffered data can support.
   *
   */
  @CalledByNative void onPlayBufferUpdated(long playCachedBuffer);

  /**
   * @brief Reports the events of preloaded media resources.
   *
   * @param src The URL of the media resource.
   * @param event Events that occur when media resources are preloaded. See `MediaPlayerPreloadEvent`.
   *
   */
  @CalledByNative void onPreloadEvent(String src, Constants.MediaPlayerPreloadEvent event);

  /**
   * @brief AgoraCDN Token has expired and needs to be set up with renewAgoraCDNSrcToken.
   *
   * @deprecated 4.6.0
   */
  @Deprecated @CalledByNative void onAgoraCDNTokenWillExpire();

  /**
   * @brief Occurs when the video bitrate of the media resource changes.
   *
   * @param from Information about the video bitrate of the media resource being played. See
   * `SrcInfo`.
   * @param to Information about the changed video bitrate of media resource being played. See
   * `SrcInfo`.
   *
   */
  @CalledByNative void onPlayerSrcInfoChanged(SrcInfo from, SrcInfo to);

  /**
   * @brief Occurs when information related to the media player changes.
   *
   * @details
   * When the information about the media player changes, the SDK triggers this callback. You can use
   * this callback for troubleshooting.
   *
   * @param info Information related to the media player. See `PlayerUpdatedInfo`.
   *
   */
  @CalledByNative void onPlayerInfoUpdated(PlayerUpdatedInfo info);

  /**
   * @brief Reports the statistics of the media file being cached.
   *
   * @details
   * After you call the `openWithMediaSource` method and set `enableCache` as `true`, the SDK triggers
   * this callback once per second to report the statistics of the media file being cached.
   *
   * @param stats The statistics of the media file being cached. See `CacheStatistics`.
   *
   */
  @CalledByNative void onPlayerCacheStats(CacheStatistics stats);

  /**
   * @brief Reports the statistics of the media file being played.
   *
   * @details
   * The SDK triggers this callback once per second to report the statistics of the media file being
   * played.
   *
   * @param stats The statistics of the media file. See `PlayerPlaybackStats`.
   *
   */
  @CalledByNative void onPlayerPlaybackStats(PlayerPlaybackStats stats);

  /**
   * @brief Reports the volume of the media player.
   *
   * @details
   * The SDK triggers this callback every 200 milliseconds to report the current volume of the media
   * player.
   *
   * @param volume The volume of the media player. The value ranges from 0 to 255.
   *
   */
  @CalledByNative void onAudioVolumeIndication(int volume);
}
