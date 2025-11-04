package io.agora.musiccontentcenter;

import io.agora.mediaplayer.IMediaPlayer;

public interface IAgoraMusicPlayer extends IMediaPlayer {
  /**
   * @brief Opens a music resource by its resource ID.
   *
   * @details
   * Before calling this method, make sure the music resource you want to play has been fully loaded. You can call the `isPreloaded` method to check whether the music resource has been preloaded, or listen for the `onPreLoadEvent` callback.
   * After calling this method, the `onPlayerStateChanged` callback will be triggered. Once you receive a playback state report of `PLAYER_STATE_OPEN_COMPLETED`, you can call the `play` method to play the media file.
   *
   * @note If the music resource you want to open is protected by digital rights management, you must use this method to open it. For music resources that are not protected by digital rights management, you can choose to open them using either this method or the `open(String url, long startPos)` method in the `IMediaPlayer` class.
   *
   * @param songCode The resource ID of the music resource, used to identify the music.
   * @param startPos The starting playback position in milliseconds. The default value is 0.
   *
   * @return
   * - 0: The method call was successful.
   * - < 0: The method call failed. Refer to `Error Codes` for details and troubleshooting suggestions.
   */
  int open(long songCode, long startPos);

  /**
   * @brief Opens a music resource via URL.
   *
   * @details
   * Calling this method triggers the `onPlayerStateChanged` callback. After receiving a report that the playback state is `PLAYER_STATE_OPEN_COMPLETED`, you can call the `play` method to play the media file.
   *
   * @note If the music resource you want to open is protected by digital rights, you must use this method to open it. For music resources that are not protected by digital rights, you can choose to open them using this method or the `open(String url, long startPos)` method under the `IMediaPlayer` class.
   *
   * @param url The path to the music resource, supporting both local and online files.
   * @param startPos The starting position for playback in milliseconds. Default is 0.
   *
   * @return
   * - 0: Method call succeeded.
   * - < 0: Method call failed. See `Error Codes` for details and troubleshooting suggestions.
   */
  @Override int open(String url, long startPos);

  /**
   * @brief Destroys the music player.
   *
   * @deprecated use {@link IAgoraMusicContentCenter#destroyMusicPlayer(IAgoraMusicPlayer player)}
   * instead.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  @Deprecated @Override int destroy();

  /**
   * @brief Gets the identifier or URL of the currently playing music resource.
   *
   * @details
   * You need to call this method after opening the music resource:
   * - If you open the music resource using the `open(Uri uri, long startPos)` method, this method returns the identifier of the music resource (`songCode`).
   * - If you open the music resource using the `open(String url, long startPos)` method, this method returns the URL of the music resource.
   *
   * @return
   * - If the method call succeeds, returns the identifier or URL of the music resource.
   * - If the method call fails, returns NULL.
   */
  @Override String getPlaySrc();

  /**
   * @brief Stops playing the music resource.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting tips.
   */
  @Override int stop();

  /**
   * @brief Sets the playback mode for a music track.
   *
   * @details
   * You can call this method to enable the original vocals, accompaniment, or vocal guide. If you do not call this method to set the mode, the accompaniment is played by default; if the music resource does not include accompaniment, the original vocals are played.
   * Applicable scenarios: In entertainment scenarios such as online karaoke or talent shows, if you need to play licensed music provided by the Agora Content Center, you can call this method to set the playback mode.
   * Call timing: You must call this method after `createMusicPlayer`.
   *
   * @note You can get detailed information about the music resource through the `onMusicCollectionResult` callback, and use the `list` parameter in the callback to check which playback modes are supported for the licensed music.
   *
   * @param mode The playback mode. See `MusicPlayMode` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting.
   *   - -2: Invalid parameter. Please reset the parameter.
   */
  int setPlayMode(MusicPlayMode mode);

  /**
   * @brief Playback modes for music resources.
   */
  enum MusicPlayMode {
    /**
     * 0: Original singer.
     */
    MUSIC_PLAY_MODE_ORIGINAL(0),
    /**
     * 1: Accompaniment.
     */
    MUSIC_PLAY_MODE_ACCOMPANY(1),
    /**
     * 2: Accompaniment guide.
     */
    MUSIC_PLAY_MODE_LEAD_SING(2);

    private final int value;

    MusicPlayMode(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public static MusicPlayMode valueOf(int value) {
      switch (value) {
        case 0:
          return MUSIC_PLAY_MODE_ORIGINAL;
        case 1:
          return MUSIC_PLAY_MODE_ACCOMPANY;
        case 2:
          return MUSIC_PLAY_MODE_LEAD_SING;
        default:
          return null;
      }
    }
  }
}
