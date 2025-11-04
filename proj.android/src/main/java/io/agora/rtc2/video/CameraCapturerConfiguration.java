package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The camera capturer preference.
 */
public class CameraCapturerConfiguration {
  /**
   * @brief The camera direction.
   */
  public enum CAMERA_DIRECTION {
    /**
     * 0: The rear camera.
     */
    CAMERA_REAR(0),
    /**
     * 1: (Default) The front camera.
     */
    CAMERA_FRONT(1),
    /**
     * 2: The external camera.
     */
    CAMERA_EXTRAL(2);

    private int value;

    private CAMERA_DIRECTION(int v) {
      value = v;
    }

    @CalledByNative("CAMERA_DIRECTION")
    public int getValue() {
      return this.value;
    }
  }

  /**
   * @brief The camera focal length types.
   */
  public enum CAMERA_FOCAL_LENGTH_TYPE {
    /**
     * 0: (Default) Standard lens.
     */
    CAMERA_FOCAL_LENGTH_DEFAULT(0),
    /**
     * 1: Wide-angle lens.
     */
    CAMERA_FOCAL_LENGTH_WIDE_ANGLE(1),
    /**
     * 2: Ultra-wide-angle lens.
     */
    CAMERA_FOCAL_LENGTH_ULTRA_WIDE(2),
    /**
     * Telephoto lens.
     */
    CAMERA_FOCAL_LENGTH_TELEPHOTO(3);

    private int value;

    private CAMERA_FOCAL_LENGTH_TYPE(int v) {
      value = v;
    }

    @CalledByNative("CAMERA_FOCAL_LENGTH_TYPE")
    public int getValue() {
      return this.value;
    }
  }

  /**
   * (Optional) The camera direction. See `CAMERA_DIRECTION`.
   */
  public CAMERA_DIRECTION cameraDirection = null;

  /**
   * (Optional) The camera ID. The default value is the camera ID of the front camera. You can get the
   * camera ID through the Android native system API, see `Camera.open()` and
   * `CameraManager.getCameraIdList()` for details.
   * @note
   * - This parameter and `cameraDirection` are mutually exclusive in specifying the camera; you can
   * choose one based on your needs. The differences are as follows:
   *   - Specifying the camera via `cameraDirection` is more straightforward. You only need to
   * indicate the camera direction (front or rear), without specifying a specific camera ID; the SDK
   * will retrieve and confirm the actual camera ID through Android native system APIs.
   *   - Specifying via `cameraId` allows for more precise identification of a particular camera. For
   * devices with multiple cameras, where `cameraDirection` cannot recognize or access all available
   * cameras, it is recommended to use `cameraId` to specify the desired camera ID directly.
   */
  public String cameraId = null;

  /**
   * (Optional) The camera focal length type. See `CAMERA_FOCAL_LENGTH_TYPE`.
   * @note To set the focal length type of the camera, it is only supported to specify the camera
   * through `cameraDirection`, and not supported to specify it through `cameraId`.
   */
  public CAMERA_FOCAL_LENGTH_TYPE cameraFocalLengthType = null;

  /**
   * @brief The format of the video frame.
   */
  static public class CaptureFormat {
    /**
     * The width (px) of the video frame. The default value is 960.
     */
    public int width;
    /**
     * The height (px) of the video frame. The default value is 540.
     */
    public int height;
    /**
     * The video frame rate (fps). The default value is 15.
     */
    public int fps;

    public CaptureFormat(int width, int height, int fps) {
      this.width = width;
      this.height = height;
      this.fps = fps;
    }

    public CaptureFormat() {
      this.width = 960;
      this.height = 540;
      this.fps = 15;
    }

    @CalledByNative("CaptureFormat")
    public int getHeight() {
      return height;
    }

    @CalledByNative("CaptureFormat")
    public int getWidth() {
      return width;
    }

    @CalledByNative("CaptureFormat")
    public int getFps() {
      return fps;
    }

    @Override
    public String toString() {
      return "CaptureFormat{"
          + "width=" + width + ", height=" + height + ", fps=" + fps + '}';
    }
  }

  /**
   * (Optional) The format of the video frame. See `CaptureFormat`.
   */
  public CaptureFormat captureFormat;

  /**
   * (Optional) Whether to follow the video aspect ratio set in `setVideoEncoderConfiguration`:
   * - `true`: (Default) Follow the set video aspect ratio. The SDK crops the captured video according
   * to the set video aspect ratio and synchronously changes the local preview screen and the video
   * frame in `onCaptureVideoFrame` and `onPreEncodeVideoFrame`.
   * - `false`: Do not follow the system default audio playback device. The SDK does not change the
   * aspect ratio of the captured video frame.
   */
  public Boolean followEncodeDimensionRatio = null;

  /**
   * The camera capture configuration.
   *
   * @param cameraDirection The camera direction. For details, see {@link CAMERA_DIRECTION
   *     CAMERA_DIRECTION}.
   */
  public CameraCapturerConfiguration(CAMERA_DIRECTION cameraDirection) {
    this.cameraDirection = cameraDirection;
    this.captureFormat = new CaptureFormat(0, 0, 0);
  }

  public CameraCapturerConfiguration(
      CAMERA_DIRECTION cameraDirection, CAMERA_FOCAL_LENGTH_TYPE cameraFocalLengthType) {
    this.cameraDirection = cameraDirection;
    this.cameraFocalLengthType = cameraFocalLengthType;
    this.captureFormat = new CaptureFormat(0, 0, 0);
  }

  /**
   * The camera capture configuration.
   *
   * @param captureFormat The camera capture format. For details, see {@link
   *     CaptureFormat }.
   */
  public CameraCapturerConfiguration(CaptureFormat captureFormat) {
    this.captureFormat = captureFormat;
  }

  /**
   * The camera capture configuration.
   *
   * @param cameraDirection The camera direction. For details, see {@link CAMERA_DIRECTION
   *     CAMERA_DIRECTION}.
   * @param captureFormat The camera capture format. For details, see {@link
   *     CaptureFormat }.
   */
  public CameraCapturerConfiguration(
      CAMERA_DIRECTION cameraDirection, CaptureFormat captureFormat) {
    this.cameraDirection = cameraDirection;
    this.captureFormat = captureFormat;
  }

  public CameraCapturerConfiguration(CAMERA_DIRECTION cameraDirection,
      CAMERA_FOCAL_LENGTH_TYPE cameraFocalLengthType, CaptureFormat captureFormat) {
    this.cameraDirection = cameraDirection;
    this.cameraFocalLengthType = cameraFocalLengthType;
    this.captureFormat = captureFormat;
  }

  @CalledByNative
  public CAMERA_DIRECTION getCameraDirection() {
    return cameraDirection;
  }

  @CalledByNative
  public String getCameraId() {
    return cameraId;
  }

  @CalledByNative
  public CAMERA_FOCAL_LENGTH_TYPE getCameraFocalLengthType() {
    return cameraFocalLengthType;
  }

  @CalledByNative
  public CaptureFormat getCaptureFormat() {
    return captureFormat;
  }

  @CalledByNative
  public Boolean isFollowEncodeDimensionRatio() {
    return followEncodeDimensionRatio;
  }

  @Override
  public String toString() {
    return "CameraCapturerConfiguration{"
        + "cameraDirection=" + cameraDirection + ", captureDimensions=" + captureFormat
        + ", cameraId=" + cameraId + ", followEncodeDimensionRatio=" + followEncodeDimensionRatio
        + ", cameraFocalLengthType=" + cameraFocalLengthType + '}';
  }
}
