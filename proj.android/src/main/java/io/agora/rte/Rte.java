package io.agora.rte;

import io.agora.rte.NativeLibsLoader;
import io.agora.rte.Config;
import io.agora.rte.Observer;
import io.agora.rte.InitialConfig;
import io.agora.base.internal.CalledByNative;
import io.agora.rte.callback.AsyncCallback;
import io.agora.rte.exception.RteException;

/**
 * The Rte class, which is the basic interface of the Agora Real Time Engagement SDK.
 * @since v4.4.0
 */
public class Rte {
  public static final String TAG = "Rte";

  /**
   * @brief Bridges an RTE object from the `RtcEngine`.
   *
   * @since v4.4.0
   *
   * @details
   * The RTE object created by calling this method does not require initialization through `initMediaEngine`. If you have not previously created and initialized the RTC engine, you can first call `Rte` to create an RTE object, and then call `initMediaEngine` for initialization.  
   * Call timing: Before calling this method, make sure you have called `create(RtcEngineConfig config)` to initialize the RTC engine.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` and returns the corresponding error information. You need to catch and handle the exception.
   */
  public static synchronized Rte getFromBridge() throws RteException {
    long handle = nativeGetFromBridge();
    return new Rte(handle);
  }

  /**
   * @brief Constructs an `RTE` object.
   * @since v4.4.0
   * 
   * @details
   * Call timing: You must call this method before `initMediaEngine`.
   * @param intialConfig Initialization settings. You can currently pass in null.
   */
  public Rte(InitialConfig initialConfig) {
    NativeLibsLoader.initializeNativeLibs();

    mNativeHandle = nativeCreateRte(initialConfig);
  }

  private Rte(long nativeHandle) {
    mNativeHandle = nativeHandle;
    mGetFromBridge = true;
  }

  /**
   * @brief Destroys the `RTE` object.
   *
   * @since v4.4.0
   *
   * @details
   * This method releases all resources used by the `RTE` object.
   *
   */
  public synchronized void destroy() throws RteException {
    if (mNativeHandle != 0) {
      nativeReleaseRte(mNativeHandle);
      mNativeHandle = 0;

      if (!mGetFromBridge) {
        NativeLibsLoader.deinitializeNativeLibs();
      }
    }
  }

  @Override
  protected void finalize() {
    try {
      destroy();
    } catch (RteException e) {
      e.printStackTrace();
    }
  }

  public long getNativeHandle() {
    return mNativeHandle;
  }

  /**
   * @brief Initializes the RTE engine.
   *
   * @since v4.4.0
   *
   * @details
   * This method is asynchronous and returns the initialization result through a callback function.
   * Call timing: You need to call this method after creating the RTE object and setting the App ID.
   *
   * @param callback An asynchronous callback function used to return the engine initialization result. See `AsyncCallback` for details.
   *
   */
  public void initMediaEngine(AsyncCallback callBack) throws RteException {
    nativeInitMediaEngine(mNativeHandle, callBack);
  }

  /**
   * @brief Gets the configuration information of the `Rte` object.
   *
   * @since v4.4.0
   *
   * @details
   * Call timing: This method must be called after `Rte`.
   *
   * @param config The settings of the `Rte` object. See `Config` for details.
   *
   * @throws RteException If the method call fails, the SDK throws a `RteException` and returns the corresponding error information. You need to catch and handle the exception.
   */
  public void getConfigs(Config config) throws RteException {
    nativeGetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  /**
   * @brief Configures the information of the `RTE` object.
   *
   * @since v4.4.0
   *
   * @details
   * You can call this method to set the App ID and other information.  
   * Call timing: This method must be called after `Rte`.
   *
   * @param config Settings for the `RTE` object. See `Config` for details.
   *
   * @throws RteException If the method call fails, the SDK throws an `RteException` and returns the corresponding error information. You need to catch and handle the exception.
   */
  public void setConfigs(Config config) throws RteException {
    nativeSetConfigs(mNativeHandle, config != null ? config.getNativeHandle() : 0);
  }

  /**
   * @brief Constructs an `RTE` object.
   *
   * @since v4.4.0
   *
   * @details
   * The `RTE` object is used to organize and manage internal resources of `Rte`.
   * Call timing: You must call this method before `initMediaEngine`.
   *
   * @param initialConfig Initialization settings. You can currently pass in null.
   *
   */
  public void registerObserver(Observer observer) throws RteException {
    nativeRegisterObserver(mNativeHandle, observer != null ? observer.getNativeHandle() : 0);
  }

  /**
   * Unregister an rte observer.
   * @since v4.4.0
   * @param observer The object that observes rte callback events.
   * @throws RteException RteException.errorCode() may return the following ErrorCode:
   *  - ErrorCode.INVALID_OPERATION: The corresponding internal Rte object has been destroyed or is
   * invalid.
   *  - ErrorCode.INVALID_ARGUMENT: The observer object to be unregistered is null.
   * @return void
   */
  public void unregisterObserver(Observer observer) throws RteException {
    nativeUnregisterObserver(mNativeHandle, observer != null ? observer.getNativeHandle() : 0);
  }

  private static native long nativeGetFromBridge();
  private native long nativeCreateRte(InitialConfig initialConfig);
  private native void nativeReleaseRte(long handle);

  private native void nativeInitMediaEngine(long handle, AsyncCallback callBack);

  private native void nativeGetConfigs(long handle, long configHandle);
  private native void nativeSetConfigs(long handle, long configHandle);

  private native void nativeRegisterObserver(long handle, long observerHandle);
  private native void nativeUnregisterObserver(long handle, long observerHandle);

  private long mNativeHandle = 0;
  private boolean mGetFromBridge = false;
}
