package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Settings for the music content center.
 */
public class MusicContentCenterConfiguration {
  /**
   * The App ID of the project with content center enabled.
   */
  public String appId;
  /**
   * The RTM Token used for authentication when using the music content center.
   * @note
   * - Agora recommends that you use AccessToken2 for authentication. See `Deploying the Token Server` for details. When generating the Token, pass the `String` type `mccUid` to the `uid` parameter.
   * - When your Token is about to expire, you can call `renewToken` to provide a new Token.
   */
  public String token;
  /**
   * ID of the user to use the music content center. This ID can be the same as the `uid` you use to join the RTC channel, but it cannot be 0.
   */
  public long mccUid;
  /**
   * The number of cacheable music resources must not exceed 50.
   */
  public int maxCacheSize;
  /**
   * The event callbacks to be received. See `IMusicContentCenterEventHandler` for details.
   */
  public IMusicContentCenterEventHandler eventHandler;
  /**
   * @technical preview
   */
  public String mccDomain;

  public MusicContentCenterConfiguration() {
    this.appId = null;
    this.token = null;
    this.mccUid = 0;
    this.maxCacheSize = 10;
    eventHandler = null;
    this.mccDomain = null;
  }

  public MusicContentCenterConfiguration(String appid, String token, long mccUid, int maxCacheSize,
      IMusicContentCenterEventHandler eventHandler) {
    this.appId = appid;
    this.token = token;
    this.mccUid = mccUid;
    this.maxCacheSize = maxCacheSize;
    this.eventHandler = eventHandler;
    this.mccDomain = null;
  }

  public MusicContentCenterConfiguration(String appid, String token, long mccUid, int maxCacheSize,
      IMusicContentCenterEventHandler eventHandler, String domain) {
    this.appId = appid;
    this.token = token;
    this.mccUid = mccUid;
    this.maxCacheSize = maxCacheSize;
    this.eventHandler = eventHandler;
    this.mccDomain = domain;
  }

  @CalledByNative
  public String getAppId() {
    return this.appId;
  }

  @CalledByNative
  public String getToken() {
    return this.token;
  }

  @CalledByNative
  public long getMccUid() {
    return this.mccUid;
  }

  @CalledByNative
  public int getMaxCacheSize() {
    return maxCacheSize;
  }

  @CalledByNative
  public IMusicContentCenterEventHandler getEventHandler() {
    return eventHandler;
  }

  @CalledByNative
  public String getMccDomain() {
    return mccDomain;
  }
}
