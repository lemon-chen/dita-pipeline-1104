package io.agora.rtc2.video;
import io.agora.rtc2.Constants;

/**
 * @brief The snapshot configuration.
 */
public class SnapshotConfig {
  /**
   * The local path (including filename extensions) of the snapshot. For example:
   * - Android: `/storage/emulated/0/Android/data/<package name>/files/example.jpg`
   * @note Ensure that the path you specify exists and is writable.
   */
  public String filePath = null;
  /**
   * The position of the snapshot video frame in the video pipeline. See `VideoModulePosition`.
   */
  public Constants.VideoModulePosition position =
      Constants.VideoModulePosition.VIDEO_MODULE_POSITION_PRE_ENCODER;
}
