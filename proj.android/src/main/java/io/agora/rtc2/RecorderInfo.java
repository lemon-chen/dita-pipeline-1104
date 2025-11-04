package io.agora.rtc2;
import io.agora.base.internal.CalledByNative;
/**
 * @brief Recording file information.
 *
 * @since v3.5.2
 */
public class RecorderInfo {
  /**
   * The absolute storage path of the recording file.
   */
  public String fileName;
  /**
   * Duration of the recorded file, in milliseconds.
   */
  public int durationMs;
  /**
   * Size of the recording file, in bytes.
   */
  public int fileSize;

  @CalledByNative
  public RecorderInfo(String fileName, int durationMs, int fileSize) {
    this.fileName = fileName;
    this.durationMs = durationMs;
    this.fileSize = fileSize;
  }
}
