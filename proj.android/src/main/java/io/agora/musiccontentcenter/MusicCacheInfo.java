package io.agora.musiccontentcenter;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Information about cached music resources.
 */
public class MusicCacheInfo {
  /**
   * The identifier of the music resource, used to identify the music resource.
   */
  public long songCode;
  /**
   * Cache status of music resources:
   * - `MUSIC_CACHE_STATUS_TYPE_CACHED` (0): The music resource is cached.
   * - `MUSIC_CACHE_STATUS_TYPE_CACHING` (1): The music resource is being cached.
   */
  public int status;

  public MusicCacheInfo() {}

  @CalledByNative
  public MusicCacheInfo(long songCode, int status) {
    this.songCode = songCode;
    this.status = status;
  }

  @CalledByNative
  public long getSongCode() {
    return songCode;
  }

  @CalledByNative
  public int getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "MusicCacheInfo{"
        + "songCode=" + songCode + ", status=" + status + '}';
  }
}
