package io.agora.mediaplayer;
import io.agora.base.internal.CalledByNative;
import java.nio.ByteBuffer;

public interface IMediaPlayerCustomDataProvider {
  /**
   * @brief Occurs when the SDK reads the media resource data.
   *
   * @details
   * When you call the `openWithMediaSource` method to open a media resource, the SDK triggers this
   * callback and request you to pass in the buffer of the media resource data.
   *
   * @param buffer An input parameter. Data buffer (bytes). Write the `bufferSize` data reported by
   * the SDK into this parameter.
   * @param bufferSize The length of the data buffer (bytes).
   *
   * @return
   * - If the data is read successfully, pass in the length of the data (bytes) you actually read in
   * the return value.
   * - If reading the data fails, pass in 0 in the return value.
   */
  @CalledByNative int onReadData(ByteBuffer buffer, int bufferSize);

  /**
   * @brief Occurs when the SDK seeks the media resource data.
   *
   * @details
   * When you call the `openWithMediaSource` or `open(String url, long startPos)` method to open a custom media resource,
   * the SDK triggers this callback to request the specified location in the media resource.
   *
   * @param offset An input parameter. The offset of the target position relative to the starting
   * point, in bytes. The value can be positive or negative.
   * @param whence An input parameter. The starting point. You can set it as one of the following
   * values:
   * - 0: The starting point is the head of the data, and the actual data offset after seeking is
   * `offset`.
   * - 1: The starting point is the current position, and the actual data offset after seeking is the
   * current position plus `offset`.
   * - 2: The starting point is the end of the data, and the actual data offset after seeking is the
   * whole data length plus `offset`.
   * - 65536: Do not perform position seeking, return the file size. Agora recommends that you use
   * this parameter value when playing pure audio files such as MP3 and WAV.
   *
   * @return
   * - When `whence` is `65536`, the media file size is returned.
   * - When `whence` is `0`, `1`, or `2`, the actual data offset after the seeking is returned.
   * - -1: Seeking failed.
   */
  @CalledByNative long onSeek(long offset, int whence);
}
