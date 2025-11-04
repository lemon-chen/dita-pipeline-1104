package io.agora.spatialaudio;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The spatial position of the remote user or the media player.
 */
public class RemoteVoicePositionInfo {
  /**
   * The coordinates in the world coordinate system. This parameter is an array of length 3, and the
   * three values represent the front, right, and top coordinates in turn.
   */
  public float[] position;
  /**
   * The unit vector of the x axis in the coordinate system. This parameter is an array of length 3,
   * and the three values represent the front, right, and top coordinates in turn.
   */
  public float[] forward;

  public RemoteVoicePositionInfo() {
    position = new float[] {0.0f, 0.0f, 0.0f};
    forward = new float[] {0.0f, 0.0f, 0.0f};
  }

  @CalledByNative
  public float[] getPosition() {
    return position;
  }

  @CalledByNative
  public float[] getForward() {
    return forward;
  }
}
