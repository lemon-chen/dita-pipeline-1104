package io.agora.rte.exception;
import io.agora.rte.Constants;
import io.agora.base.internal.CalledByNative;

/**
 * @brief Rte exception information.
 *
 * @since v4.4.0
 */
public class RteException extends Exception {
  public RteException(String message) {
    super(message);
  }

  @CalledByNative
  public RteException(String message, int errorCode) {
    super(message);
    this.mErrorCode = errorCode;
  }

  /**
   * @brief Gets the error code carried by the `RteException`.
   *
   * @return
   * The error code carried by this exception. See `ErrorCode` for details.
   */
  public Constants.ErrorCode errorCode() {
    return Constants.ErrorCode.fromInt(mErrorCode);
  }

  private int mErrorCode;
}
