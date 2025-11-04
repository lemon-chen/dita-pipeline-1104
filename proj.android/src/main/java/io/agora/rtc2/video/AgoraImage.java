package io.agora.rtc2.video;

/**
 * @brief Image properties.
 *
 * @details
 * This class sets the properties of the watermark and background images in the live video.
 *
 */
public class AgoraImage {
  /**
   * The HTTP/HTTPS URL address of the image in the live video. The maximum length of this parameter
   * is 1024 bytes.
   */
  public String url;
  /**
   * The x-coordinate (px) of the image on the video frame (taking the upper left corner of the video
   * frame as the origin).
   */
  public int x;
  /**
   * The y-coordinate (px) of the image on the video frame (taking the upper left corner of the video
   * frame as the origin).
   */
  public int y;
  /**
   * The width (px) of the image on the video frame.
   */
  public int width;
  /**
   * The height (px) of the image on the video frame.
   */
  public int height;
  /**
   * The layer index of the watermark or background image. When you use the watermark array to add a
   * watermark or multiple watermarks, you must pass a value to `zOrder` in the range [1,255];
   * otherwise, the SDK reports an error. In other cases, `zOrder` can optionally be passed in the
   * range [0,255], with 0 being the default value. 0 means the bottom layer and 255 means the top
   * layer.
   */
  public int zOrder;
  /**
   * The transparency of the watermark or background image. The range of the value is [0.0,1.0]:
   * - 0.0: Completely transparent.
   * - 1.0: (Default) Opaque.
   */
  public double alpha;

  public AgoraImage() {
    this.url = null;
    this.x = 0;
    this.y = 0;
    this.width = 0;
    this.height = 0;
    this.zOrder = 0;
    this.alpha = 1.0;
  }

  public AgoraImage(String url) {
    this.url = url;
    this.x = 0;
    this.y = 0;
    this.width = 0;
    this.height = 0;
    this.zOrder = 0;
    this.alpha = 1.0;
  }
}
