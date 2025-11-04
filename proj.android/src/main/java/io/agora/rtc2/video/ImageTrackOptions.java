package io.agora.rtc2.video;

/**
 * @brief Image configurations.
 */
public class ImageTrackOptions {
  /**
   * The image URL. Supported formats of images include JPEG, JPG, PNG and GIF. This method supports
   * adding an image from the local absolute or relative file path.
   * @note On the Android platform, adding images from `/assets/` is not supported.
   */
  private String imageUrl;
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * The frame rate of the video streams being published. The value range is [1,30]. The default value
   * is 1.
   */
  private int fps;
  public int getFps() {
    return fps;
  }

  private VideoEncoderConfiguration.MIRROR_MODE_TYPE mirrorMode;
  public int getMirrorMode() {
    return mirrorMode.getValue();
  }

  public ImageTrackOptions(String url, int fps) {
    this.imageUrl = url;
    this.fps = fps;
    this.mirrorMode = VideoEncoderConfiguration.MIRROR_MODE_TYPE.MIRROR_MODE_DISABLED;
  }

  public ImageTrackOptions(
      String url, int fps, VideoEncoderConfiguration.MIRROR_MODE_TYPE mirrorMode) {
    this.imageUrl = url;
    this.fps = fps;
    this.mirrorMode = mirrorMode;
  }
}
