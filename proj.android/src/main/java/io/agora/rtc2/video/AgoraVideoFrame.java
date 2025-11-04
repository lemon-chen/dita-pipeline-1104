package io.agora.rtc2.video;

import io.agora.base.AlphaStitchMode;

/**
 * @brief The external video frame.
 */
public class AgoraVideoFrame {
  /**
   * -1: No video format.
   */
  public static final int FORMAT_NONE = -1;
  /**
   * 10: The video format is TEXTURE_2D.
   */
  public static final int FORMAT_TEXTURE_2D = 10;
  /**
   * 11: The video format is TEXTURE_OES.
   */
  public static final int FORMAT_TEXTURE_OES = 11;
  /**
   * 1: The video format is I420.
   */
  public static final int FORMAT_I420 = 1;
  /**
   * 2: The video format is BGRA.
   */
  public static final int FORMAT_BGRA = 2;
  /**
   * 3: The video format is NV21.
   */
  public static final int FORMAT_NV21 = 3;
  /**
   * 4: The video format is RGBA.
   */
  public static final int FORMAT_RGBA = 4;
  /**
   * 16: Video frame in the format of I422.
   */
  public static final int FORMAT_I422 = 16;

  /**
   * -1: No buffer type.
   */
  public static final int BUFFER_TYPE_NONE = -1;
  /**
   * 1: The buffer type is buffer.
   */
  public static final int BUFFER_TYPE_BUFFER = 1;
  /**
   * 2: The buffer type is array.
   */
  public static final int BUFFER_TYPE_ARRAY = 2;
  /**
   * 3: The buffer type is texture.
   */
  public static final int BUFFER_TYPE_TEXTURE = 3;

  public AgoraVideoFrame() {
    format = 10; // GL_TEXTURE_2D
    timeStamp = 0;
    stride = 0;
    height = 0;
    textureID = 0;
    syncMode = true;
    transform = null;
    eglContext10 = null;
    eglContext14 = null;
    buf = null;
    cropLeft = 0;
    cropTop = 0;
    cropRight = 0;
    cropBottom = 0;
    rotation = 0;
    alphaStitchMode = AlphaStitchMode.ALPHA_NO_STITCH;
  }
  /**
   * The format of the video data:
   * - 10: TEXTURE_2D
   * - 11: TEXTURE_OES, usually the data captured by the camera is in this format.
   * - 1: I420
   * - 3: NV21
   * - 4: RGBA
   * - 16: I422
   */
  public int format;
  /**
   * Timestamp (ms) of the incoming video frame. An incorrect timestamp results in frame loss or
   * unsynchronized audio and video.
   */
  public long timeStamp;
  /**
   * Line spacing of the incoming video frame, which must be in pixels instead of bytes. For textures,
   * it is the width of the texture.
   */
  public int stride;
  /**
   * Height of the incoming video frame.
   */
  public int height;

  /**
   * Texture ID of the frame. This parameter only applies to video data in Texture format.
   */
  public int textureID;
  /**
   * Set whether to enable the synchronization mode. After enabling, the SDK waits while Texture
   * processing. This parameter only applies to video data in Texture format.
   * - `true`: Enable sync mode.
   * - `false`: Disable sync mode.
   */
  public boolean syncMode;
  /**
   * Additional transform of Texture frames. This parameter only applies to video data in Texture
   * format.
   */
  public float[] transform;
  /**
   * EGLContext10. This parameter only applies to video data in Texture format.
   */
  public javax.microedition.khronos.egl.EGLContext eglContext10;
  /**
   * EGLContext14. This parameter only applies to video data in Texture format.
   */
  public android.opengl.EGLContext eglContext14;

  // Non-texture frame
  /**
   * Video frame buffer.
   */
  public byte[] buf;
  /**
   * Raw data related parameter. The number of pixels trimmed from the left. The default value is 0.
   */
  public int cropLeft;
  /**
   * Raw data related parameter. The number of pixels trimmed from the top. The default value is 0.
   */
  public int cropTop;
  /**
   * Raw data related parameter. The number of pixels trimmed from the right. The default value is 0.
   */
  public int cropRight;
  /**
   * Raw data related parameter. The number of pixels trimmed from the bottom. The default value is 0.
   */
  public int cropBottom;
  /**
   * Raw data related parameter. The clockwise rotation of the video frame. You can set the rotation
   * angle as 0, 90, 180, or 270. The default value is 0.
   */
  public int rotation;
  /**
   * When the video frame contains alpha channel data, it represents the relative position of
   * `alphaBuffer` and the video frame.
   * - ALPHA_NO_STITCH 0: (Default) Only video frame, that is, `alphaBuffer` is not stitched with the
   * video frame.
   * - ALPHA_STITCH_UP (1): `alphaBuffer` is above the video frame.
   * - ALPHA_STITCH_BELOW (2): `alphaBuffer` is below the video frame.
   * - ALPHA_STITCH_LEFT (3): `alphaBuffer` is to the left of the video frame.
   * - ALPHA_STITCH_RIGHT (4): `alphaBuffer` is to the right of the video frame.
   */
  public AlphaStitchMode alphaStitchMode;

  @Override
  public String toString() {
    return "AgoraVideoFrame{"
        + "format=" + format + ", timeStamp=" + timeStamp + ", stride=" + stride
        + ", height=" + height + ", textureID=" + textureID
        + ", buf.length=" + (buf != null ? buf.length : 0) + ", cropLeft=" + cropLeft
        + ", cropTop=" + cropTop + ", cropRight=" + cropRight + ", cropBottom=" + cropBottom
        + ", rotation=" + rotation + ", alphaStitchMode=" + alphaStitchMode + '}';
  }

  /* Note
   * 1. stride
   *    Stride is in unit of pixel, not byte
   * 2. About frame width and height
   *    No field defined for width. However, it can be deduced by:
   *       croppedWidth = (strideInPixels - cropLeft - cropRight)
   *    And
   *       croppedHeight = (height - cropTop - cropBottom)
   * 3. About crop
   *    _________________________________________________________________.....
   *    |                        ^                                      |  ^
   *    |                        |                                      |  |
   *    |                     cropTop                                   |  |
   *    |                        |                                      |  |
   *    |                        v                                      |  |
   *    |                ________________________________               |  |
   *    |                |                              |               |  |
   *    |                |                              |               |  |
   *    |<-- cropLeft -->|          valid region        |<- cropRight ->|
   *    |                |                              |               | height
   *    |                |                              |               |
   *    |                |_____________________________ |               |  |
   *    |                        ^                                      |  |
   *    |                        |                                      |  |
   *    |                     cropBottom                                |  |
   *    |                        |                                      |  |
   *    |                        v                                      |  v
   *    _________________________________________________________________......
   *    |                                                               |
   *    |<------------------------ stride ----------------------------->|
   *
   *    If your buffer contains garbage data, you can crop them. E.g. frame size is
   *    360 x 640, often the buffer stride is 368, i.e. there extra 8 pixels on the
   *    right are for padding, and should be removed. In this case, you can set:
   *    stride = 368;
   *    height = 640;
   *    cropRight = 8;
   *    // cropLeft, cropTop, cropBottom are default to 0
   */
}
