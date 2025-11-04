package io.agora.rtc2.video;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.view.View;
import io.agora.rtc2.Constants;

/**
 * @brief Attributes of the video canvas object.
 */
public class VideoCanvas {
  /**
   * User ID that publishes the video source.
   */
  public int uid;
  /**
   * The ID of the user who publishes a specific sub-video stream within the mixed video stream.
   */
  public int subviewUid;
  /**
   *
   * 1: Uniformly scale the video until it fills the visible boundaries (cropped).
   * One dimension of the video may have clipped contents.
   */
  public static final int RENDER_MODE_HIDDEN = 1;
  /**
   * 2: Uniformly scale the video until one of its dimension
   * fits the boundary (zoomed to fit). Areas that are not filled due to the
   * disparity in the aspect ratio are filled with black.
   */
  public static final int RENDER_MODE_FIT = 2;
  /**
   * 3: This mode is deprecated and Agora does not recommend using it.
   */
  public static final int RENDER_MODE_ADAPTIVE = 3;

  public static final int VIEW_SETUP_MODE_REPLACE = 0;

  public static final int VIEW_SETUP_MODE_ADD = 1;

  public static final int VIEW_SETUP_MODE_REMOVE = 2;
  /**
   * The video display window. It must be one `SurfaceView` or `TextureView` object.
   * @note In one `VideoCanvas`, you can only choose to set either `view` or `surfaceTexture`. If both
   * are set, only the settings in `view` take effect.
   */
  public View view;
  /**
   * A container that provides video images. When you use this member, you need to implement texture
   * drawing in OPENGL yourself. See `SurfaceTexture`.
   * @note In one `VideoCanvas`, you can only choose to set either `view` or `surfaceTexture`. If both
   * are set, only the settings in `view` take effect.
   */
  public SurfaceTexture surfaceTexture;
  /**
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   */
  public int renderMode;
  /**
   * - VIDEO_MIRROR_MODE_AUTO (0): The SDK determines whether to enable the mirror mode. If you use a
   * front camera, the SDK enables the mirror mode by default; if you use a rear camera, the SDK
   * disables the mirror mode by default.
   * - VIDEO_MIRROR_MODE_ENABLED (1): Enable the mirroring mode of the local view.
   * - VIDEO_MIRROR_MODE_DISABLED (2): Disable the mirroring mode of the local view.
   * @note
   * - For the mirror mode of the local video view: If you use a front camera, the SDK enables the
   * mirror mode by default; if you use a rear camera, the SDK disables the mirror mode by default.
   * - For the remote user: The mirror mode is disabled by default.
   */
  public int mirrorMode;
  /**
   * Setting mode of the view.
   * - VIEW_SETUP_MODE_REPLACE (0): (Default) Replaces a view.
   * - VIEW_SETUP_MODE_ADD (1): Adds a view.
   * - VIEW_SETUP_MODE_REMOVE (2): Deletes a view.
   * @note When you no longer need to use a certain view, it is recommended to delete the view by
   * setting `setupMode` to VIEW_SETUP_MODE_REMOVE, otherwise it may lead to leak of rendering
   * resources.
   */
  public int setupMode = VIEW_SETUP_MODE_REPLACE;
  /**
   * The type of the video source. See `VideoSourceType`.
   */
  public int sourceType;
  /**
   * The ID of the media player. You can get the Device ID by calling `getMediaPlayerId`.
   */
  public int mediaPlayerId;
  /**
   * (Optional) Display area for the video frame. See `Rect`. `width` and `height` represent the video
   * pixel width and height of the area. The default value is null (width or height is 0), which means
   * that the actual resolution of the video frame is displayed.
   */
  public Rect rect;
  /**
   * (Optional) Whether to enable alpha mask rendering:
   * - `true`: Enable alpha mask rendering.
   * - `false`: (Default) Disable alpha mask rendering.
   * Alpha mask rendering can create images with transparent effects and extract portraits from
   * videos. When used in combination with other methods, you can implement effects such as
   * portrait-in-picture and watermarking.
   */
  public boolean enableAlphaMask;
  /**
   * The background color of the video canvas in RGBA format. The default value is 0x00000000, which
   * represents black.
   * @note
   * - If you are developing with Kotlin, and encounter a compilation error after setting this
   * parameter to `0xFFFFFFFF`, you can use `(0xFFFFFFFF).toInt()` or
   * `android.graphics.Color.parseColor("#FFFFFFFF")`.
   * - Currently, the `backgroundColor` parameter does not support the alpha channel when using the
   * RGBA format.
   */
  public int backgroundColor;
/**
 * The observation position of the video frame in the video link. See `VideoModulePosition`.
 */

  public Constants.VideoModulePosition position =
      Constants.VideoModulePosition.VIDEO_MODULE_POSITION_POST_CAPTURER;

  public VideoCanvas(View view) {
    uid = 0;
    this.view = view;
    this.renderMode = RENDER_MODE_HIDDEN;
    this.backgroundColor = 0;
  }

  public VideoCanvas(View view, int renderMode, int uid) {
    this.uid = uid;
    this.view = view;
    this.renderMode = renderMode;
    this.backgroundColor = 0;
  }

  public VideoCanvas(View view, int renderMode, int uid, int subviewUid) {
    this.uid = uid;
    this.subviewUid = subviewUid;
    this.view = view;
    this.renderMode = renderMode;
    this.backgroundColor = 0;
  }
}
