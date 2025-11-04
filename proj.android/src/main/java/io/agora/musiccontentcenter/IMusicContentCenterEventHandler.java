package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;

public interface IMusicContentCenterEventHandler {
  /**
   * @brief Reports the event of preloading music resources.
   *
   * @details
   * After you call the `preload(long songCode, String jsonOption)` or `preload(long songCode)` method to preload a music resource, the SDK triggers this callback.
   *
   * @param requestId The request ID. A unique identifier for this request.
   * @param songCode The ID of the music resource, used to identify a music asset.
   * @param percent The current loading progress of the music resource, with a value range of [0,100].
   * @param lyricUrl The download URL of the lyrics.
   * @param state The current loading state of the music resource:
   * - 0: Music resource is fully loaded.
   * - 1: Music resource failed to load.
   * - 2: Music resource is loading.
   * @param reason The request status code from the Music Content Center:
   * - MUSIC_CONTENT_CENTER_STATE_REASON_OK (0): Request succeeded.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_ERROR (1): General error with no specific attribution.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY (2): Gateway error. Possible causes include:
   *   - The current Temporary Token has expired. Please regenerate the Token.
   *   - The provided Token is invalid. Make sure you are using an RTM Token.
   *   - Network error. Please check your network connection.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE (3): Permission error or music resource does not exist. Make sure your project has enabled access to the Music Content Center. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE (4): Internal data parsing error. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING (5): Error occurred while loading the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION (6): Error occurred while decrypting the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL (7): Internal HTTP error. Please try the request again later.
   *
   */
  @CalledByNative
  void onPreLoadEvent(
      String requestId, long songCode, int percent, String lyricUrl, int state, int reason);

  /**
   * @brief Callback for retrieving a list of music resources.
   *
   * @details
   * This callback is triggered by the SDK when you call the `getMusicCollectionByMusicChartId(int musicChartId, int page, int pageSize)` method to retrieve a list of music resources from a specific chart, or the `searchMusic(String keyword, int page, int pageSize)` method to search for music resources. It reports detailed information about the music resources in the chart.
   *
   * @param requestId The request ID. A unique identifier for this request.
   * @param reason The request status code from the music content center:
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_OK (0)`: Request succeeded.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_ERROR (1)`: General error without specific attribution.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY (2)`: Gateway error. Possible causes include:
   *   - The current Temporary Token has expired. Please regenerate the Temporary Token.
   *   - The provided Temporary Token is invalid. Please ensure you are using an RTM Token.
   *   - Network error. Please check your network connection.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE (3)`: Permission error or music resource not found. Please ensure your project has enabled access to the Agora Music Content Center. Contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE (4)`: Internal data parsing error. Contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING (5)`: Error occurred while loading music resources. Contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION (6)`: Error occurred while decrypting music resources. Contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL (7)`: Internal HTTP error. Please try again later.
   * @param page The current page number, starting from 1 by default.
   * @param pageSize The maximum number of music resources displayed per page, up to 50.
   * @param total The total number of music resources in the list.
   * @param list Detailed information about the list of music resources. See `Music`.
   *
   */
  @CalledByNative
  void onMusicCollectionResult(
      String requestId, int page, int pageSize, int total, Music[] list, int reason);

  /**
   * @brief Callback for retrieving music charts.
   *
   * @details
   * This callback is triggered after you call the `getMusicCharts` method to retrieve all available music charts.
   *
   * @param requestId The request ID. A unique identifier for this request.
   * @param reason The request status code from the music content center:
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_OK` (0): Request succeeded.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_ERROR` (1): General error with no specific cause.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY` (2): Gateway error. Possible reasons include:
   *   - The current Token has expired. Please regenerate the Token.
   *   - The provided Token is invalid. Make sure you are using an RTM Token.
   *   - Network error. Please check your network connection.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE` (3): Permission error or music resource not found. Make sure your project has enabled access to the Agora Music Content Center. Please contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE` (4): Internal data parsing error. Please contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING` (5): Error occurred while loading music resources. Please contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION` (6): Error occurred while decrypting music resources. Please contact technical support.
   * - `MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL` (7): Internal HTTP error. Please try again later.
   * @param list The list of currently playable music charts. See `MusicChartInfo` for details.
   *
   */
  @CalledByNative void onMusicChartsResult(String requestId, MusicChartInfo[] list, int reason);

  /**
   * @brief Callback for lyric download URL.
   *
   * @details
   * After you call `getLyric` to retrieve the download URL for the specified song's lyrics, the SDK triggers this callback.
   *
   * @param requestId The request ID. A unique identifier for this request.
   * @param songCode The code of the music resource, used to identify the music resource.
   * @param lyricUrl The download URL for the lyrics.
   * @param reason The request status code from the Music Content Center:
   * - MUSIC_CONTENT_CENTER_STATE_REASON_OK (0): Request succeeded.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_ERROR (1): General error without a specific cause.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY (2): Gateway error. Possible reasons include:
   *   - The current Temporary Token has expired. Please regenerate the Token.
   *   - The provided Token is invalid. Make sure you are using an RTM Token.
   *   - Network error. Please check your network connection.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE (3): Permission error or the music resource does not exist. Make sure your project has enabled the Music Content Center permission. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE (4): Internal data parsing error. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING (5): Error occurred while loading the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION (6): Error occurred while decrypting the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL (7): Internal HTTP error. Please try the request again later.
   *
   */
  @CalledByNative void onLyricResult(String requestId, long songCode, String lyricUrl, int reason);

  /**
   * @brief Callback for detailed information of a music resource.
   *
   * @details
   * This callback is triggered after you call `getSongSimpleInfo` to retrieve detailed information about a music resource.
   *
   * @param requestId The request ID. A unique identifier for this request.
   * @param songCode The identifier of the music resource.
   * @param simpleInfo The related information of the music resource, including:
   * - Start and end time (ms) of the chorus segment
   * - Download URL for the chorus lyrics
   * - Duration (ms) of the chorus segment
   * - Song title
   * - Artist name
   * @param reason The request status code from the Music Content Center:
   * - MUSIC_CONTENT_CENTER_STATE_REASON_OK (0): Request succeeded.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_ERROR (1): General error with no specific cause.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_GATEWAY (2): Gateway error. Possible causes include:
   *   - The current Temporary Token has expired. Please generate a new one.
   *   - The provided Token is invalid. Make sure you are using an RTM Token.
   *   - Network error. Please check your network connection.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_PERMISSION_AND_RESOURCE (3): Permission error or the music resource does not exist. Make sure your project has enabled Music Content Center permissions. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_INTERNAL_DATA_PARSE (4): Internal data parsing error. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_LOADING (5): Error occurred while loading the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_MUSIC_DECRYPTION (6): Error occurred while decrypting the music resource. Please contact technical support.
   * - MUSIC_CONTENT_CENTER_STATE_REASON_HTTP_INTERNAL (7): Internal HTTP error. Please try again later.
   *
   */
  @CalledByNative
  void onSongSimpleInfoResult(String requestId, long songCode, String simpleInfo, int reason);
}
