package io.agora.rte;
import io.agora.rte.exception.RteException;

/**
 * @brief RTE configuration class, used to configure the `RTE` object.
 *
 * @since v4.4.0
 */
public class Config {
  public Config() {
    mNativeHandle = nativeCreateConfig();
  }

  @Override
  protected void finalize() {
    nativeReleaseConfig(mNativeHandle);
  }

  public long getNativeHandle() {
    return mNativeHandle;
  };

  /**
   * @brief Sets the App ID.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: You must call this method before `initMediaEngine`.
   *
   * @param appId The App ID of your project, which you can obtain from the Console.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with the corresponding error information. You need to catch and handle the exception.
   */
  public void setAppId(String appId) throws RteException {
    nativeSetAppId(mNativeHandle, appId);
  }

  /**
   * Get the set App ID.
   * @since v4.4.0
   * @return String Returns the set App ID value.
   */
  public String getAppId() throws RteException {
    return nativeGetAppId(mNativeHandle);
  }

  /**
   * Set the log folder.
   * @since v4.4.0
   * @param logFolder The log folder.
   * @throws RteException
   * @technical preview
   */
  public void setLogFolder(String logFolder) throws RteException {
    nativeSetLogFolder(mNativeHandle, logFolder);
  }

  /**
   * Get the log folder.
   * @since v4.4.0
   * @return The log folder.
   * @throws RteException
   * @technical preview
   */
  public String getLogFolder() throws RteException {
    return nativeGetLogFolder(mNativeHandle);
  }

  /**
   * Set the log file size.
   * @since v4.4.0
   * @param logFileSize The log file size.
   * @throws RteException
   * @technical preview
   */
  public void setLogFileSize(int logFileSize) throws RteException {
    nativeSetLogFileSize(mNativeHandle, logFileSize);
  }

  /**
   * Get the log file size.
   * @since v4.4.0
   * @return The log file size.
   * @throws RteException
   * @technical preview
   */
  public int getLogFileSize() throws RteException {
    return nativeGetLogFileSize(mNativeHandle);
  }

  /**
   * Set the area code.
   * @since v4.4.0
   * @param areaCode The area code.
   * @throws RteException
   * @technical preview
   */
  public void setAreaCode(int areaCode) throws RteException {
    nativeSetAreaCode(mNativeHandle, areaCode);
  }

  /**
   * Get the area code.
   * @since v4.4.0
   * @return The area code.
   * @throws RteException
   * @technical preview
   */
  public int getAreaCode() throws RteException {
    return nativeGetAreaCode(mNativeHandle);
  }

  /**
   * Set the cloud proxy.
   * @since v4.4.0
   * @param cloudProxy The cloud proxy.
   * @throws RteException
   * @technical preview
   */
  public void setCloudProxy(String cloudProxy) throws RteException {
    nativeSetCloudProxy(mNativeHandle, cloudProxy);
  }

  /**
   * Get the cloud proxy.
   * @since v4.4.0
   * @return The cloud proxy.
   * @throws RteException
   * @technical preview
   */
  public String getCloudProxy() throws RteException {
    return nativeGetCloudProxy(mNativeHandle);
  }

  /**
   * @brief Configures technical preview or specially customized features of the SDK via JSON.
   *
   * @since v4.4.0
   *
   * @details
   * Applicable scenarios: You can call this method when you need to set private parameters or use customized features.
   * Call timing: This method must be called before `setConfigs(Config config)`.
   *
   * @param jsonParameter Parameters in JSON string format.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` with corresponding error information. You need to catch and handle the exception.
   */
  public void setJsonParameter(String jsonParameter) throws RteException {
    nativeSetJsonParameter(mNativeHandle, jsonParameter);
  }

  /**
   * Get the currently set JSON private parameters.
   * @since v4.4.0
   * @return String The set JSON formatted parameter set.
   */
  public String getJsonParameter() throws RteException {
    return nativeGetJsonParameter(mNativeHandle);
  }

  private native long nativeCreateConfig();
  private native void nativeReleaseConfig(long handle);

  private native void nativeSetAppId(long handle, String appId);
  private native String nativeGetAppId(long handle);
  private native void nativeSetLogFolder(long handle, String logFolder);
  private native String nativeGetLogFolder(long handle);
  private native void nativeSetLogFileSize(long handle, int logFileSize);
  private native int nativeGetLogFileSize(long handle);
  private native void nativeSetAreaCode(long handle, int areaCode);
  private native int nativeGetAreaCode(long handle);
  private native void nativeSetCloudProxy(long handle, String cloudProxy);
  private native String nativeGetCloudProxy(long handle);
  private native void nativeSetJsonParameter(long handle, String jsonParameter);
  private native String nativeGetJsonParameter(long handle);

  private long mNativeHandle = 0;
}
