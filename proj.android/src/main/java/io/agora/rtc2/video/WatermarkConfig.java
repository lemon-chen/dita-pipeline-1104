package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Used to configure watermark-related information.
 *
 * @since 4.6.0
 */
public class WatermarkConfig {
  /**
   * Specifies the watermark type as image.
   */
  public static final int WATERMARK_TYPE_IMAGE = 0;

  /**
   * Specifies the watermark type as buffer.
   */
  public static final int WATERMARK_TYPE_BUFFER = 1;

  /**
   * Unique identifier for the watermark, usually generated using UUID.
   */
  public String id;

  /**
   * Type of the watermark:
   * - `WATERMARK_TYPE_IMAGE`: image type.
   * - `WATERMARK_TYPE_BUFFER`: Buffer type.
   */
  public int type;

  /**
   * Buffer containing watermark image data. See `WatermarkBuffer`.
   */
  public WatermarkBuffer buffer = new WatermarkBuffer();

  /**
   * URL address used to load the watermark image.
   */
  public String imageUrl;

  /**
   * Options defining the position and size of the watermark. See `WatermarkOptions`.
   */
  public WatermarkOptions options = new WatermarkOptions();

  /**
   * @brief Retrieves the unique ID of the watermark.
   *
   * @since 4.6.0
   *
   * @details
   * Used to obtain the unique identifier of the watermark from a `WatermarkBuffer` or
   * `WatermarkConfig` instance.
   *
   * @return
   * When the method call succeeds, it returns the unique ID of the watermark. When it fails, it
   * returns an empty string.
   */
  @CalledByNative
  public String getId() {
    return id;
  }

  /**
   * @brief Gets the type of the watermark.
   *
   * @since 4.6.0
   *
   * @details
   * Used to get the type information in the current watermark configuration.
   *
   * @return
   * If the method call succeeds, returns the watermark type.
   * - `WATERMARK_TYPE_IMAGE`: Image watermark.
   * - `WATERMARK_TYPE_BUFFER`: Buffer watermark.
   */
  @CalledByNative
  public int getType() {
    return type;
  }

  /**
   * @brief Gets the watermark buffer.
   *
   * @since 4.6.0
   *
   * @return The buffer containing the watermark image data.
   */
  @CalledByNative
  public WatermarkBuffer getBuffer() {
    return buffer;
  }

  /**
   * @brief Gets the URL of the watermark image.
   *
   * @since 4.6.0
   *
   * @return
   * When the method call succeeds, it returns the URL of the watermark image. When it fails, it
   * returns an empty string.
   */
  @CalledByNative
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * @brief Retrieves watermark display options.
   *
   * @since 4.6.0
   *
   * @details
   * Used to retrieve the currently configured watermark display options.
   *
   * @return
   * The `WatermarkOptions` object, if the method call succeeds.
   */
  @CalledByNative
  public WatermarkOptions getOptions() {
    return options;
  }
}
