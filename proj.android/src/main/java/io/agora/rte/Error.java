package io.agora.rte;

import io.agora.base.internal.CalledByNative;

/**
 * @brief This class provides methods to retrieve error codes and error messages.
 *
 * @since v4.4.0
 */
public class Error {
  public Error() {
    // Implementation
    mNativeHandle = nativeCreateError();
  }

  @CalledByNative
  public Error(long nativeHandle) {
    mNativeHandle = nativeHandle;
    mIsNativeOwner = false;
  }

  @Override
  protected void finalize() {
    // Implementation
    destroy();
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * @brief Gets the error code returned from an API call.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You can call this method to get the error code when the API call fails.
   *
   * @return
   * The error code. See `ErrorCode` for details.
   */
  public Constants.ErrorCode code() {
    return Constants.ErrorCode.fromInt(nativeGetCode(mNativeHandle));
  }

  /**
   * @brief Retrieves detailed error information.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: When the API call fails, you can call this method to retrieve error information to help troubleshoot the issue.
   *
   * @return
   * Error information.
   */
  public String message() {
    return nativeGetMessage(mNativeHandle);
  }

  private void destroy() {
    if (mIsNativeOwner) {
      nativeReleaseError(mNativeHandle);
    }
    mNativeHandle = 0;
  }

  private native long nativeCreateError();
  private native void nativeReleaseError(long handle);

  private native int nativeGetCode(long handle);
  private native String nativeGetMessage(long handle);

  private long mNativeHandle = 0;
  private boolean mIsNativeOwner = true;
}
