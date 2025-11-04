package io.agora.rtc2.video;

import java.nio.ByteBuffer;
import io.agora.base.internal.CalledByNative;

/**
 * @brief Configures the format, size, and pixel buffer of the watermark image.
 *
 * @since 4.6.0
 *
 * @details
 * Defines the buffer data structure of the watermark image, including image width, height, format,
 * length, and image data buffer.
 *
 */
public class WatermarkBuffer {
  /**
   * Specifies the watermark buffer format as I420.
   */
  public final static int WATERMARK_BUFFER_FORMAT_I420 = 1;

  /**
   * Specifies the watermark buffer format as BGRA.
   */
  public final static int WATERMARK_BUFFER_FORMAT_BGRA = 2;

  /**
   * Specifies the watermark buffer format as NV21.
   */
  public final static int WATERMARK_BUFFER_FORMAT_NV21 = 3;

  /**
   * Specifies the watermark buffer format as RGBA.
   */
  public final static int WATERMARK_BUFFER_FORMAT_RGBA = 4;

  /**
   * Stores the watermark image data.
   */
  public ByteBuffer buffer;

  /**
   * Width of the watermark buffer, in pixels.
   */
  public int width;

  /**
   * Height of the watermark buffer, in pixels.
   */
  public int height;

  /**
   * Format of the watermark image:
   * - `WATERMARK_BUFFER_FORMAT_I420`: I420 format.
   * - `WATERMARK_BUFFER_FORMAT_BGRA`: BGRA format.
   * - `WATERMARK_BUFFER_FORMAT_NV21`: NV21 format.
   * - `WATERMARK_BUFFER_FORMAT_RGBA`: RGBA format.
   */
  public int format;

  /**
   * @brief Retrieves the buffer of the watermark image.
   *
   * @since 4.6.0
   *
   * @details
   * This method is used to retrieve the buffer of the watermark image or the watermark image data
   * buffer.
   *
   * @return
   * Returns a `ByteBuffer` object containing the watermark image data if the method call succeeds.
   */
  @CalledByNative
  public ByteBuffer getBuffer() {
    return buffer;
  }

  /**
   * @brief Gets the width of the watermark image.
   *
   * @since 4.6.0
   *
   * @details
   * Used to get the width of the watermark image.
   *
   * @return
   * When the method call succeeds, it returns the width of the watermark image in pixels.
   */
  @CalledByNative
  public int getWidth() {
    return width;
  }

  /**
   * @brief Gets the height of the watermark image.
   *
   * @since 4.6.0
   *
   * @details
   * Used to get the height of the watermark image.
   *
   * @return
   * If the method call succeeds, it returns the height of the watermark image in pixels.
   */
  @CalledByNative
  public int getHeight() {
    return height;
  }

  /**
   * @brief Retrieves the format of the watermark image.
   *
   * @since 4.6.0
   *
   * @details
   * Used to retrieve the format of the watermark image.
   *
   * @return
   * If the method call succeeds, it returns the format of the watermark image buffer.
   */
  @CalledByNative
  public int getFormat() {
    return format;
  }
}
