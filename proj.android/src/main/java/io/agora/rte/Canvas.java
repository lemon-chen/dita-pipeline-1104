package io.agora.rte;
import android.view.View;
import io.agora.rte.Rte;
import io.agora.rte.CanvasInitialConfig;
import io.agora.rte.CanvasConfig;
import io.agora.rte.exception.RteException;

/**
 * Canvas interface, used to set the video rendering view.
 * @since v4.4.0
 */
public class Canvas {
  /**
   * Construct a Canvas object.
   * @since v4.4.0
   * @param rte Rte object.
   * @param config CanvasInitialConfig initialization configuration object. Currently, a null
   *     pointer can be passed.
   */
  public Canvas(Rte rte, CanvasInitialConfig config) {
    mNativeHandle = nativeCreateCanvas(
        rte != null ? rte.getNativeHandle() : 0, config != null ? config.getNativeHandle() : 0);
  }

  @Override
  protected void finalize() {
    destroy();
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * @brief Adds a rendering view.
   *
   * @since v4.4.0
   * 
   * @details
   * Applicable scenarios: When you need to play a video using the player, you can call this method to add a view to the video renderer in order to display the video content.
   * Call timing: You must call this method before `setCanvas`.
   *
   * @note Currently, only one view is supported.
   *
   * @param view A `SurfaceView` object.
   * @param config The settings for the `View` object. Pass null for now.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void addView(View view, ViewConfig viewConfig) throws RteException {
    // Implementation
    nativeAddView(mNativeHandle, view, viewConfig != null ? viewConfig.getNativeHandle() : 0);
  }

  /**
   * @brief Removes a rendering view.
   *
   * @since v4.4.0
   *
   * @details
   * After you call `AddView` to add a view, you can call this method to remove the rendering view.
   * Call timing: This method must be called after `AddView`.
   *
   * @note Only one view can be removed at a time.
   *
   * @param view The view object to be removed.
   * @param config The settings of the `View` object. Pass null for now.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void removeView(View view, ViewConfig viewConfig) throws RteException {
    // Implementation
    nativeRemoveView(mNativeHandle, view, viewConfig != null ? viewConfig.getNativeHandle() : 0);
  }

  /**
   * @brief Gets the current video rendering configuration of the player.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method after `Canvas`.
   *
   * @param config Settings of the `Canvas` object. See `CanvasConfig` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void getConfigs(CanvasConfig config) throws RteException {
    // Implementation
    nativeGetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  /**
   * @brief Sets the video rendering configuration for the player.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method before `openWithUrl`.
   *
   * @param config Settings for the `Canvas` object. See `CanvasConfig` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setConfigs(CanvasConfig config) throws RteException {
    // Implementation
    nativeSetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  private void destroy() {
    nativeReleaseCanvas(mNativeHandle);
    mNativeHandle = 0;
  }

  private native long nativeCreateCanvas(long rteHandle, long configHandle);
  private native void nativeReleaseCanvas(long handle);
  private native void nativeAddView(long handle, View view, long viewConfigHandle);
  private native void nativeRemoveView(long handle, View view, long viewConfigHandle);
  private native void nativeGetConfigs(long handle, long configHandle);
  private native void nativeSetConfigs(long handle, long configHandle);

  private long mNativeHandle = 0;
}
