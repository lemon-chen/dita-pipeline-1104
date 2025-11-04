package io.agora.rte;
import io.agora.rte.Rect;
import io.agora.rte.exception.RteException;

/**
 * @brief This class provides methods for configuring video rendering of the player.
 *
 * @since v4.4.0
 */
public class CanvasConfig {
  public CanvasConfig() {
    mNativeHandle = nativeCreateCanvasConfig();
  }

  @Override
  protected void finalize() {
    destroy();
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * Set the video render mode.
   * @since v4.4.0
   * @param mode The render mode to set. Refer to Constants.VideoRenderMode type, default is
   *     Constants.VideoRenderMode.HIDDEN.
   * @throws RteException RteException.errorCode() may return the following ErrorCode:
   *  - ErrorCode.INVALID_ARGUMENT: The mode parameter is set to an illegal value.
   * @return void
   */
  public void setVideoRenderMode(Constants.VideoRenderMode mode) throws RteException {
    if (mode == null) {
      throw new RteException(
          "mode is null", Constants.ErrorCode.getValue(Constants.ErrorCode.INVALID_ARGUMENT));
    }

    nativeSetVideoRenderMode(mNativeHandle, Constants.VideoRenderMode.getValue(mode));
  }

  /**
   * @brief Gets the currently set mirror mode.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You need to call this method after `getConfigs(CanvasConfig config)`.
   *
   * @return
   * The currently set mirror mode. See `VideoMirrorMode` for details.
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public Constants.VideoRenderMode getVideoRenderMode() throws RteException {
    return Constants.VideoRenderMode.fromInt(nativeGetVideoRenderMode(mNativeHandle));
  }

  /**
   * @brief Sets the video mirror mode.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method before `setConfigs(CanvasConfig config)`.
   *
   * @param mode The mirror mode. See `VideoMirrorMode` for details. The default value is `AUTO`, which lets the SDK determine the mirror mode. By default, the mirror mode for remote users is disabled.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setVideoMirrorMode(Constants.VideoMirrorMode mode) throws RteException {
    if (mode == null) {
      throw new RteException(
          "mode is null", Constants.ErrorCode.getValue(Constants.ErrorCode.INVALID_ARGUMENT));
    }
    nativeSetVideoMirrorMode(mNativeHandle, Constants.VideoMirrorMode.getValue(mode));
  }

  /**
   * Get the video mirror mode.
   * @since v4.4.0
   * @return VideoMirrorMode
   */
  public Constants.VideoMirrorMode getVideoMirrorMode() throws RteException {
    return Constants.VideoMirrorMode.fromInt(nativeGetVideoMirrorMode(mNativeHandle));
  }

  public void setCropArea(Rect rect) throws RteException {
    nativeSetCropArea(mNativeHandle, rect);
  }

  public Rect getCropArea() throws RteException {
    return nativeGetCropArea(mNativeHandle);
  }

  private void destroy() {
    nativeReleaseCanvasConfig(mNativeHandle);
    mNativeHandle = 0;
  }

  private native long nativeCreateCanvasConfig();
  private native void nativeReleaseCanvasConfig(long handle);

  private native void nativeSetVideoRenderMode(long handle, int mode);
  private native int nativeGetVideoRenderMode(long handle);

  private native void nativeSetVideoMirrorMode(long handle, int mode);
  private native int nativeGetVideoMirrorMode(long handle);

  private native void nativeSetCropArea(long handle, Rect rect);
  private native Rect nativeGetCropArea(long handle);

  private long mNativeHandle = 0;
}
