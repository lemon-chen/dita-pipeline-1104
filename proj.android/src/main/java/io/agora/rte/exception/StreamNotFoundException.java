package io.agora.rte.exception;
import io.agora.rte.Constants;
import io.agora.rte.exception.RteException;
import io.agora.base.internal.CalledByNative;

/**
 * The StreamNotFoundException class.
 * @since v4.4.0
 */
public class StreamNotFoundException extends RteException {
  @CalledByNative
  public StreamNotFoundException(String message) {
    super(message);
  }

  @Override
  /**
   * @brief Gets the error code carried by the `RteException`.
   *
   * @return
   * The error code carried by this exception. See `ErrorCode` for details.
   */
  public Constants.ErrorCode errorCode() {
    return Constants.ErrorCode.STREAM_NOT_FOUND;
  }
}
