package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Watermark image configurations.
 *
 * @since 4.6.0
 *
 * @details
 * Configuration options for setting the watermark image to be added.
 *
 */
public class WatermarkOptions {
  /**
   * @brief The location of the target area relative to the screen or window. If you do not set this
   *
   * @since 4.6.0
   * parameter, the SDK selects the whole screen or window.
   */
  public static class Rectangle {
    /**
     * The horizontal offset from the top-left corner.
     */
    public int x = 0;
    /**
     * The vertical offset from the top-left corner.
     */
    public int y = 0;
    /**
     * The width of the target area.
     */
    public int width = 0;
    /**
     * The height of the target area.
     */
    public int height = 0;

    public Rectangle() {
      x = 0;
      y = 0;
      width = 0;
      height = 0;
    }
    public Rectangle(int x_, int y_, int width_, int height_) {
      x = x_;
      y = y_;
      width = width_;
      height = height_;
    }
  }

  /**
   * Whether the watermark is visible in the local preview view:
   * - `true`: (Default) The watermark is visible in the local preview view.
   * - `false`: The watermark is not visible in the local preview view.
   */
  public boolean visibleInPreview = true;

  /**
   * The area to display the watermark image in landscape mode. See `Rectangle`.
   */
  public Rectangle positionInLandscapeMode = new Rectangle();

  /**
   * The area to display the watermark image in portrait mode. See `Rectangle`.
   */
  public Rectangle positionInPortraitMode = new Rectangle();

  /**
   * Layer order of the watermark image. The default value is 0.
   * @since 4.6.0
   */
  public int zOrder = 0;

  /**
   * @brief Gets the position of the watermark in landscape mode.
   *
   * @since 4.6.0
   *
   * @details
   * Gets the position of the watermark in landscape mode.
   *
   * @return
   * When the method call succeeds, it returns an integer array with four elements representing the x,
   * y, width, and height of the watermark rectangle.
   */
  @CalledByNative
  public int[] getPositionInLandscapeMode() {
    Rectangle src = positionInLandscapeMode;
    int[] rect = new int[4];
    if (null != src) {
      rect[0] = src.x;
      rect[1] = src.y;
      rect[2] = src.width;
      rect[3] = src.height;
    }
    return rect;
  }

  /**
   * @brief Gets the position of the watermark in portrait mode.
   *
   * @since 4.6.0
   *
   * @details
   * Gets the position of the watermark in portrait mode.
   *
   * @return
   * When the method call succeeds, it returns an integer array of four elements representing the x,
   * y, width, and height of the watermark rectangle.
   */
  @CalledByNative
  public int[] getPositionInPortraitMode() {
    Rectangle src = positionInPortraitMode;
    int[] rect = new int[4];
    if (null != src) {
      rect[0] = src.x;
      rect[1] = src.y;
      rect[2] = src.width;
      rect[3] = src.height;
    }
    return rect;
  }

  /**
   * @brief Checks whether the watermark is visible in the local video preview.
   *
   * @since 4.6.0
   *
   * @details
   * Checks whether the watermark is visible in the local video preview.
   *
   * @return
   * - `true`: The watermark is visible in the local video preview.
   * - `false`: The watermark is not visible in the local video preview.
   */
  @CalledByNative
  public boolean isVisibleInPreview() {
    return visibleInPreview;
  }

  /**
   * @brief Gets the Z-order index of the watermark image.
   *
   * @since 4.6.0
   *
   * @details
   * Used to get the Z-order index of the watermark image for rendering.
   *
   * @return
   * If the method call succeeds, returns the Z-order index of the watermark image.
   */
  @CalledByNative
  public int getzOrder() {
    return zOrder;
  }
}
