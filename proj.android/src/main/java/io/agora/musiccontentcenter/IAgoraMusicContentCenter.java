package io.agora.musiccontentcenter;

import io.agora.musiccontentcenter.internal.MusicContentCenterImpl;
import io.agora.rtc2.RtcEngine;

/**
 * @brief The `IAgoraMusicContentCenter` class.
 */
public abstract class IAgoraMusicContentCenter {
  private static IAgoraMusicContentCenter mInstance = null;

  public static synchronized IAgoraMusicContentCenter create(RtcEngine rtcEngine) {
    if (mInstance == null) {
      mInstance = new MusicContentCenterImpl(rtcEngine);
    }
    return mInstance;
  }

  /**
   * @brief Destroys the `IAgoraMusicContentCenter` instance.
   *
   * @details
   * You must call this method before calling the `destroy()` method of `RtcEngine`.
   *
   */
  public static synchronized void destroy() {
    if (mInstance == null) {
      return;
    }
    mInstance.doDestroy();
    mInstance = null;
  }

  /**
   * @brief Initializes `IAgoraMusicContentCenter`.
   *
   * @details
   * You must call this method to initialize `IAgoraMusicContentCenter` before calling any other methods in the `IAgoraMusicContentCenter` class.
   *
   * @param configuration The settings for `IAgoraMusicContentCenter`. See `MusicContentCenterConfiguration` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int initialize(MusicContentCenterConfiguration configuration);

  /**
   * @brief Renews the Token.
   *
   * @details
   * When the Token used for authentication is about to expire or has already expired, you can call this method to pass in a newly generated Token.
   *
   * @param token The new Token.
   *
   * @return
   * - 0: Method call succeeds.
   * - < 0: Method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int renewToken(String token);

  /**
   * @brief Creates a music player.
   *
   * @details
   * If you need to play music resources from the music content center, you must call this method first to create a music player.
   *
   * @return
   * - If the method call succeeds: returns an `IAgoraMusicPlayer` object.
   * - If the method call fails: returns a null pointer.
   */
  public abstract IAgoraMusicPlayer createMusicPlayer();

  /**
   * @brief Destroys the music player object.
   *
   * @details
   * When you no longer need to use the music player, you can call this method to destroy the music player object. If you need to use the music player again after destruction, you must call `createMusicPlayer` to recreate a music player object.
   * Call timing: You can call this method either before or after joining a channel, but make sure to call it before the `destroy()` method of `RtcEngine`.
   *
   * @param player The `IAgoraMusicPlayer` object.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting tips.
   */
  public abstract int destroyMusicPlayer(IAgoraMusicPlayer player);

  /**
   * @brief Registers the callback events for the music content center.
   *
   * @param eventHandler The callback events to be registered. See `IMusicContentCenterEventHandler` for details.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int registerEventHandler(IMusicContentCenterEventHandler eventHandler);

  /**
   * @brief Unregisters the event callback for the music content center.
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and suggested solutions.
   */
  public abstract int unregisterEventHandler();

  /**
   * @brief Preloads copyright music resources.
   *
   * @details
   * You can call this method to preload the copyright music resources you want to play. After the method is successfully called, the SDK triggers the `onPreLoadEvent` callback to report the event of preloading the copyright music resource.
   * Before calling this method to preload copyright music resources, you need to call `getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize)` or `searchMusic(String keyword, int page, int pageSize)`
   * to obtain the copyright music resources you want to play, and retrieve the music resource identifier (`songCode`) through the `onMusicCollectionResult` callback triggered by these methods.
   *
   * @note To destroy the `RtcEngine` object, make sure to call the `destroy()` method only after receiving the `onPreLoadEvent` callback.
   *
   * @param songCode The identifier of the copyright music resource.
   *
   * @return
   * A `requestId`, which is the unique identifier for this request.
   */
  public abstract String preload(long songCode);

  /**
   * @brief Preloads music resources.
   *
   * @deprecated use {@link #preload(long songCode)} instead.
   *
   * @details
   * You can call this method to preload the music resources you want to play. After this method is successfully called, the SDK triggers the `onPreLoadEvent` callback to report the preload event.
   * Before calling this method to preload music resources, you need to call `getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize)` or `searchMusic(String keyword, int page, int pageSize)`
   * to retrieve the music resources you want to play, and obtain the resource identifier (`songCode`) from the `onMusicCollectionResult` callback triggered by those methods.
   *
   * @note To destroy the `RtcEngine` object, make sure to call the `destroy()` method after receiving the `onPreLoadEvent` callback.
   *
   * @param songCode The identifier of the music resource.
   * @param jsonOption Extended JSON field.
   * Agora charges based on the application scenario you set in the `sceneType` field. Different scenarios are billed at different rates. Refer to the billing documentation for details.
   * - 1: Live streaming scenario: Karaoke and background music playback.
   * - 2: Live streaming scenario: Background music playback.
   * - 3: (Default) Voice chat scenario: Karaoke.
   * - 4: Voice chat scenario: Background music playback.
   * - 5: VR scenario: Karaoke and background music playback.
   *   If you need to switch to a different scenario, you must call this method again and pass the new `sceneType` value in this field.
   * Example: `{"sceneType":1}`
   *
   * @return
   * - 0: The method call succeeds.
   * - < 0: The method call fails. See `Error Codes` for details and troubleshooting.
   */
  @Deprecated public abstract int preload(long songCode, String jsonOption);

  /**
   * @brief Checks whether a music resource has been preloaded.
   *
   * @details
   * This method is a synchronous call. To preload a new music resource, you can call `preload(long songCode)`.
   *
   * @param songCode The identifier of the music resource, used to specify a music resource.
   *
   * @return
   * - 0: The method call is successful, and the music resource has been preloaded.
   * - < 0: The method call fails. Refer to `Error Codes` for details and suggested solutions.
   */
  public abstract int isPreloaded(long songCode);

  /**
   * @brief Retrieves all music charts.
   *
   * @details
   * When you call this method, the SDK triggers the `onMusicChartsResult` callback to report detailed information about the music charts.
   *
   * @return
   * A `requestId`, which is the unique identifier for this request.
   */
  public abstract String getMusicCharts();

  /**
   * @brief Retrieves the list of music resources from a specified music chart by its ID.
   *
   * @details
   * After a successful call to this method, the SDK triggers the `onMusicCollectionResult` callback to report detailed information about the music resources in the chart.
   *
   * @param musicChartId The ID of the music chart, which you can obtain through the `onMusicChartsResult` callback. You can also use the RESTful API to `get the full song list from the music library` or `get the incremental song list`.
   * @param page The current page number, starting from 1 by default.
   * @param pageSize The total number of pages in the current list of music resources. The maximum value is 50.
   *
   * @return
   * A `requestId`, which uniquely identifies this request.
   */
  public String getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize) {
    return getMusicCollectionByMusicChartId(musicChartId, page, pageSize, null);
  }

  /**
   * @brief Retrieves the list of music resources from a specified music chart by its ID.
   *
   * @details
   * After a successful call to this method, the SDK triggers the `onMusicCollectionResult` callback to report detailed information about the music resources in the chart.
   *
   * @param musicChartId The ID of the music chart, which you can obtain via the `onMusicChartsResult` callback. You can also use the RESTful API to `get the full music library list` or `get the incremental music list`.
   * @param page The current page number, starting from 1 by default.
   * @param pageSize The total number of pages for the music resource list. The maximum value is 50.
   * @param jsonOption An optional JSON extension field, default is NULL. You can use this field to filter the music resources you need. Currently, it supports filtering for scorable music resources and chorus segments of music resources:
   * | Key           | Value                                                                 | Example                  |
   * | ------------- | --------------------------------------------------------------------- | ------------------------ |
   * | pitchType     | Whether the music resource supports scoring:                          - 1: Scorable music resources. - 2: Non-scorable music resources. | {"pitchType":1}         |
   * | needHighPart  | Whether the chorus segment resource is needed:                        - `true`: Chorus segment required. - `false`: Chorus segment not required. | {"needHighPart":true}   |
   *
   * @return
   * A `requestId`, which is the unique identifier for this request.
   */
  public abstract String getMusicCollectionByMusicChartId(
      int musicChartId, int page, int pageSize, String jsonOption);

  /**
   * @brief Searches for music resources.
   *
   * @details
   * After this method is successfully called, the SDK triggers the `onMusicCollectionResult` callback to report the retrieved list of music resources.
   *
   * @param keyword The search keyword. Supports searching by song title or artist name.
   * @param page The target page number of the music resource list you want to retrieve.
   * @param pageSize The maximum number of music resources displayed per page. The maximum value is 50.
   *
   * @return
   * A `requestId` that uniquely identifies this request.
   */
  public String searchMusic(String keyword, int page, int pageSize) {
    return searchMusic(keyword, page, pageSize, null);
  }

  /**
   * @brief Searches for music resources.
   *
   * @details
   * After this method is successfully called, the SDK triggers the `onMusicCollectionResult` callback to report the retrieved list of music resources.
   *
   * @param keyword The search keyword. Supports searching by song title or artist name.
   * @param page The target page number of the music resource list you want to retrieve.
   * @param pageSize The maximum number of music resources displayed per page. The maximum value is 50.
   * @param jsonOption An optional JSON extension field. Defaults to NULL. You can use this field to filter the music resources you need. Currently supports filtering by whether the music resource supports scoring and whether it includes a chorus segment:
   * | Key           | Value                                                                                      | Example                 |
   * | ------------- | ------------------------------------------------------------------------------------------- | ----------------------- |
   * | pitchType     | Whether scoring is supported: - 1: Music resources that support scoring. - 2: Those that do not. | {"pitchType":1}         |
   * | needHighPart  | Whether a chorus segment is needed: - `true`: Chorus segment is required. - `false`: Not required. | {"needHighPart":true}   |
   *
   * @return
   * A `requestId` that uniquely identifies this request.
   */
  public abstract String searchMusic(String keyword, int page, int pageSize, String jsonOption);

  /**
   * @brief Retrieves the download URL for the lyrics of a music resource.
   *
   * @details
   * After this method is successfully called, the SDK triggers the `onLyricResult` callback to report the download URL of the lyrics.
   *
   * @param songCode The identifier of the music resource used to specify the music.
   * @param lyricType The type of lyrics:
   * - 0: xml format.
   * - 1: lrc format.
   *
   * @return
   * A `requestId` that uniquely identifies this request.
   */
  public abstract String getLyric(long songCode, int lyricType);

  /**
   * @brief Deletes a cached music resource.
   *
   * @details
   * You can call this method to delete a cached music resource. To delete multiple music resources, you can call this method multiple times.
   *
   * @note This method does not delete cached music resources that are currently playing.
   *
   * @param songCode The identifier of the music resource to be deleted.
   *
   * @return
   * - 0: The method call succeeds and the music resource is deleted.
   * - < 0: The method call fails. Refer to `Error Codes` for details and troubleshooting suggestions.
   */
  public abstract int removeCache(long songCode);

  /**
   * @brief Retrieves information about cached music resources.
   *
   * @details
   * Before calling this method, you need to pre-allocate a certain amount of memory to store information about the cached music resources. If you need to set the number of music resources that can be cached, you can configure it through the `configuration` parameter of `initialize`.
   * When you no longer need the cached music resources, you should release the memory in a timely manner to avoid memory leaks.
   *
   * @return
   * - If the method call succeeds, returns an array containing `MusicCacheInfo` objects.
   * - If the method call fails, returns NULL.
   */
  public abstract MusicCacheInfo[] getCaches();

  /**
   * @brief Retrieves detailed information about a specific music resource.
   *
   * @details
   * Before calling this method, you need to obtain the identifier of the corresponding music resource. You can do this by calling the `getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize)` or `searchMusic(String keyword, int page, int pageSize)` method to retrieve music resources, and obtain the music resource identifier (`songCode`) through the triggered `onMusicCollectionResult` callback.
   * After you call this method, the SDK triggers the `onSongSimpleInfoResult` callback to report the detailed information of the music resource.
   *
   * @param songCode The identifier of the music resource, used to specify the music resource.
   *
   * @return
   * A `requestId` that uniquely identifies this request.
   */
  public abstract String getSongSimpleInfo(long songCode);

/**
 * @brief Creates an internal identifier for the chorus clip of a music resource.
 *
 * @details
 * Applicable scenarios: Before you play the chorus clip of a music resource, you need to call this method to create an internal identifier for the chorus clip using the `jsonOption` parameter and the `songCode` of the music resource. This identifier serves as the unique reference for the resource. Once you get this identifier, you need to pass it as the `songCode` parameter when calling related methods to open, preload, or remove the resource.
 *
 * @param songCode The identifier of the music resource. You can obtain this by calling `getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize)` or `searchMusic(String keyword, int page, int pageSize)`, and retrieve the `songCode` from the `onMusicCollectionResult` callback triggered by these methods.
 * @param jsonOption Extended JSON field, defaults to NULL. Currently supports the following values:
 * | Key        | Value                                                                                                                                                                                                                                               | Example                        |
 * | ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------- |
 * | sceneType  | Channel profile: - 1: Live streaming scenario: karaoke and background music playback. - 2: Live streaming scenario: background music playback. - 3: (Default) Voice chat scenario: karaoke. - 4: Voice chat scenario: background music playback. - 5: VR scenario: karaoke and background music playback. Note: Agora charges based on the `sceneType` you provide. Different channel profiles have different pricing rates. Refer to the billing documentation for details. To switch to a different channel profile, you must call this method again with a new `sceneType` value. | {"sceneType":1}               |
 * | highPart   | Index of the chorus clip. You can obtain the index from the `onMusicCollectionResult` callback. The index starts from 0.                                                                                                                             | {"format": {"highpart": 0}}   |
 *
 * @return
 * - If the method call succeeds, returns the created internal identifier of the music resource.
 * - < 0: The method call fails. Refer to `Error Codes` for details and troubleshooting advice.
 */

  public abstract long getInternalSongCode(long songCode, String jsonOption);

  protected abstract void doDestroy();

  /**
   * @brief The loading state of music resources.
   */
  class PreloadState {
    private PreloadState() {}

    /**
     * 0: Music resource is loaded.
     */
    public static final int PRELOAD_STATE_COMPLETED = 0;
    /**
     * 1: Failed to load music resource.
     */
    public static final int PRELOAD_STATE_FAILED = 1;
    /**
     * 2: Music resources are loading.
     */
    public static final int PRELOAD_STATE_PRELOADING = 2;
    /**
     * 3: The cached music resource has been removed.
     */
    public static final int PRELOAD_STATE_REMOVED = 3;
  }

  /**
   * @brief Request status codes of the music content center.
   */
  class MusicContentCenterStateReason {
    private MusicContentCenterStateReason() {}

    /**
     * 0: Request succeeds.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_OK = 0;
    /**
     * 1: General error with no specific attribution.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_ERROR = 1;
    /**
     * 2: Gateway error. Possible causes include:
     * - The current Temporary Token has expired. Please regenerate the Temporary Token.
     * - The provided Temporary Token is invalid. Please make sure you are using an RTM Temporary Token.
     * - Network error. Please check your network connection.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY = 2;
    /**
     * 3: Permission error or the music resource does not exist. Make sure your project has enabled the Agora Copyright music Content Center permission. Please `contact technical support`.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE = 3;
    /**
     * 4: Internal data parsing error. Please `contact technical support`.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE = 4;
    /**
     * 5: Error occurs while loading music resources. Please `contact technical support`.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING = 5;
    /**
     * 6: Error occurs while decrypting the music resource. Please `contact technical support`.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION = 6;
    /**
     * 7: An internal HTTP error occurs. Please try again later.
     */
    public static final int MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL = 7;
  }

  /**
   * @brief Cache status of music resources.
   */
  class MusicCacheStatusType {
    private MusicCacheStatusType() {}

    /**
     * 0: Music resource is cached.
     */
    public static final int MUSIC_CACHE_STATUS_TYPE_CACHED = 0;
    /**
     * 1: Music resource is being cached.
     */
    public static final int MUSIC_CACHE_STATUS_TYPE_CACHING = 1;
  }
}
