package io.agora.rte;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Statistics of the media resource being played.
 *
 * @since v4.4.0
 */
public class PlayerStats {
  public PlayerStats() {
    mNativeHandle = nativeCreatePlayerStats();
  }

  @CalledByNative
  public PlayerStats(long nativeHandle) {
    mNativeHandle = nativeHandle;
    mIsNativeOwner = false;
  }

  @Override
  protected void finalize() {
    // Implementation
    destroy();
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  private void destroy() {
    if (mIsNativeOwner) {
      nativeReleasePlayerStats(mNativeHandle);
    }
    mNativeHandle = 0;
  }

  /**
   * Video decoding frame rate (fps).
   */
  public int videoDecodeFrameRate() {
    return nativeVideoDecodeFrameRate(mNativeHandle);
  }

  /**
   * Video rendering frame rate (fps).
   */
  public int videoRenderFrameRate() {
    return nativeVideoRenderFrameRate(mNativeHandle);
  }

  /**
   * Video bitrate (Kbps).
   */
  public int videoBitrate() {
    return nativeVideoBitrate(mNativeHandle);
  }

  /**
   * Audio bitrate (Kbps).
   */
  public int audioBitrate() {
    return nativeAudioBitrate(mNativeHandle);
  }

  private native long nativeCreatePlayerStats();
  private native void nativeReleasePlayerStats(long handle);

  private native int nativeVideoDecodeFrameRate(long handle);
  private native int nativeVideoRenderFrameRate(long handle);
  private native int nativeVideoBitrate(long handle);
  private native int nativeAudioBitrate(long handle);

  private long mNativeHandle = 0;
  private boolean mIsNativeOwner = true;
}
