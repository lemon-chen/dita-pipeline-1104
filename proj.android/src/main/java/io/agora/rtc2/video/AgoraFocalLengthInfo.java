package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Focal length information supported by the camera, including the camera direction and focal
 * length type.
 */
public class AgoraFocalLengthInfo {
  /**
   * The camera direction. See `CAMERA_DIRECTION`.
   */
  public int cameraDirection;

  /**
   * The focal length type. See `CAMERA_FOCAL_LENGTH_TYPE`.
   */
  public int focalLengthType;

  @CalledByNative
  public AgoraFocalLengthInfo(int cameraDirection, int focalLengthType) {
    this.cameraDirection = cameraDirection;
    this.focalLengthType = focalLengthType;
  }

  @Override
  public String toString() {
    return "AgoraFocalLengthInfo{"
        + "cameraDirection=" + cameraDirection + ", focalLengthType=" + focalLengthType + '}';
  }
}
