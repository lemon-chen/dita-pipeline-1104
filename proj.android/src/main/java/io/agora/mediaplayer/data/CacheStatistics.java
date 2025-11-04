package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Statistics about the media files being cached.
 */
public class CacheStatistics {
  @CalledByNative
  public CacheStatistics() {
    fileSize = 0;
    cacheSize = 0;
    downloadSize = 0;
  }

  /**
   * The size (bytes) of the media file being played.
   */
  private long fileSize;
  /**
   * The size (bytes) of the media file that you want to cache.
   */
  private long cacheSize;
  /**
   * The size (bytes) of the media file that has been downloaded.
   */
  private long downloadSize;

  public long getCacheSize() {
    return cacheSize;
  }

  public long getDownloadSize() {
    return downloadSize;
  }

  public long getFileSize() {
    return fileSize;
  }

  @CalledByNative
  public void setCacheSize(long cacheSize) {
    this.cacheSize = cacheSize;
  }

  @CalledByNative
  public void setDownloadSize(long downloadSize) {
    this.downloadSize = downloadSize;
  }

  @CalledByNative
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  @Override
  public String toString() {
    return "CacheStatistics{"
        + "fileSize=" + fileSize + ", cacheSize=" + cacheSize + ", downloadSize=" + downloadSize
        + '}';
  }
}
