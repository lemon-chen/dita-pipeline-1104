package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;
import io.agora.mediaplayer.IMediaPlayerCustomDataProvider;

/**
 * @brief Information related to the media file to be played and the playback scenario
 * configurations.
 */
public class MediaPlayerSource {
  public MediaPlayerSource() {
    this.startPos = 0;
    this.enableCache = false;
    this.enableMultiAudioTrack = false;
    this.url = null;
    this.uri = null;
    this.autoPlay = true;
    this.provider = null;
  }
  /**
   * The URL of the media file to be played.
   * @note If you open a common media resource, pass in the value to `url`. If you open a custom media
   * resource, pass in the value to `provider`. Agora recommends that you do not pass in values to
   * both parameters in one call; otherwise, this call may fail.
   */
  String url;
  /**
   * The URI (Uniform Resource Identifier) of the media file.
   */
  String uri;
  /**
   * The starting position (ms) for playback. The default value is 0.
   */
  long startPos;
  /**
   * Whether to enable autoplay once the media file is opened:
   * - `true`: (Default) Yes.
   * - `false`: No.
   * @note If autoplay is disabled, you need to call the `play` method to play a media file after it
   * is opened.
   */
  boolean autoPlay;
  /**
   * Whether to cache the media file when it is being played:
   * - `true`: Enables caching.
   * - `false`: (Default) Disables caching.
   * @note
   * - Agora only supports caching on-demand audio and video streams that are not transmitted in HLS
   * protocol.
   * - If you need to enable caching, pass in a value to `uri`; otherwise, caching is based on the
   * `url` of the media file.
   * - If you enable this function, the Media Player caches part of the media file being played on
   * your local device, and you can play the cached media file without internet connection. The
   * statistics about the media file being cached are updated every second after the media file is
   * played. See `CacheStatistics`.
   */
  boolean enableCache;
  /**
   * Whether to allow the selection of different audio tracks when playing this media file:
   * - `true`: Allow to select different audio tracks.
   * - `false`: (Default) Do not allow to select different audio tracks.
   * If you need to set different audio tracks for local playback and publishing to the channel, you
   * need to set this parameter to `true`, and then call the `selectMultiAudioTrack` method to select
   * the audio track.
   */
  boolean enableMultiAudioTrack;
  /**
   * Whether the media resource to be opened is a live stream or on-demand video distributed through
   * Media Broadcast service:
   * - `true`: The media resource to be played is a live or on-demand video distributed through Media
   * Broadcast service.
   * - `false`: (Default) The media resource is not a live stream or on-demand video distributed
   * through Media Broadcast service.
   * @note If you need to open a live stream or on-demand video distributed through Broadcast
   * Streaming service, pass in the URL of the media resource to `url`, and set `isAgoraSource` as
   * `true`; otherwise, you don't need to set the `isAgoraSource` parameter.
   */
  Boolean isAgoraSource;
  /**
   * Whether the media resource to be opened is a live stream:
   * - `true`: The media resource is a live stream.
   * - `false`: (Default) The media resource is not a live stream.
   * If the media resource you want to open is a live stream, Agora recommends that you set this
   * parameter as `true` so that the live stream can be loaded more quickly.
   * @note If the media resource you open is not a live stream, but you set `isLiveSource` as `true`,
   * the media resource is not to be loaded more quickly.
   */
  Boolean isLiveSource;
  /**
   * The callback for custom media resource files. See `IMediaPlayerCustomDataProvider`.
   * @note If you open a custom media resource, pass in the value to `provider`. If you open a common
   * media resource, pass in the value to `url`. Agora recommends that you do not pass in values to
   * both `url` and `provider` in one call; otherwise, this call may fail.
   */
  IMediaPlayerCustomDataProvider provider;

  @CalledByNative
  public String getUrl() {
    return url;
  }

  @CalledByNative
  public String getUri() {
    return uri;
  }

  @CalledByNative
  public long getStartPos() {
    return startPos;
  }

  @CalledByNative
  public boolean isEnableCache() {
    return enableCache;
  }

  @CalledByNative
  public boolean isEnableMultiAudioTrack() {
    return enableMultiAudioTrack;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public void setStartPos(long startPos) {
    this.startPos = startPos;
  }

  public void setEnableCache(boolean enableCache) {
    this.enableCache = enableCache;
  }

  public void setEnableMultiAudioTrack(boolean enableMultiAudioTrack) {
    this.enableMultiAudioTrack = enableMultiAudioTrack;
  }

  public void setAutoPlay(boolean autoPlay) {
    this.autoPlay = autoPlay;
  }

  @CalledByNative
  public boolean isAutoPlay() {
    return autoPlay;
  }

  @CalledByNative
  public Boolean isAgoraSource() {
    return isAgoraSource;
  }

  @CalledByNative
  public Boolean isLiveSource() {
    return isLiveSource;
  }

  public void enableAgoraSource(boolean isAgoraSource) {
    this.isAgoraSource = isAgoraSource;
  }

  public void enableLiveSource(boolean isLiveSource) {
    this.isLiveSource = isLiveSource;
  }

  @CalledByNative
  public IMediaPlayerCustomDataProvider getProvider() {
    return provider;
  }

  public void setProvider(IMediaPlayerCustomDataProvider provider) {
    this.provider = provider;
  }

  @Override
  public String toString() {
    return "MediaPlayerSource{"
        + "url='" + url + '\'' + ", uri='" + uri + '\'' + ", startPos=" + startPos
        + ", enableCache=" + enableCache + ", autoPlay=" + autoPlay
        + ", enableMultiAudioTrack=" + enableMultiAudioTrack
        + ", isLiveSource=" + (isLiveSource != null ? isLiveSource.booleanValue() : null)
        + ", isAgoraSource=" + (isAgoraSource != null ? isAgoraSource.booleanValue() : null)
        + ", provider=" + provider + '}';
  }
}
