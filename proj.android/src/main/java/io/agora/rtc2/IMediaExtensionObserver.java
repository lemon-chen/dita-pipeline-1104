package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.ExtensionContext;

/**
 * @brief The media extension observer interface.
 */
public interface IMediaExtensionObserver {
  /**
   * @brief The event callback of the extension.
   *
   * @details
   * To listen for events while the extension is running, you need to register this callback.
   *
   * @param extContext The context information of the extension, see `ExtensionContext`.
   * @param key The key of the extension.
   * @param value The value of the extension key.
   *
   */
  @CalledByNative
  default void onEventWithContext(ExtensionContext extContext, String key, String value){};
  /**
   * @brief Occurs when the extension is enabled.
   *
   * @details
   * The callback is triggered after the extension is successfully enabled.
   *
   * @param extContext The context information of the extension, see `ExtensionContext`.
   *
   */
  @CalledByNative default void onStartedWithContext(ExtensionContext extContext){};
  /**
   * @brief Occurs when the extension is disabled.
   *
   * @details
   * The callback is triggered after the extension is successfully disabled.
   *
   * @param extContext The context information of the extension, see `ExtensionContext`.
   *
   */
  @CalledByNative default void onStoppedWithContext(ExtensionContext extContext){};
  /**
   * @brief Occurs when the extension runs incorrectly.
   *
   * @details
   * In case of extension enabling failure or runtime errors, the extension triggers this callback and
   * reports the error code along with the reasons.
   *
   * @param extContext The context information of the extension, see `ExtensionContext`.
   * @param error Error code. For details, see the extension documentation provided by the extension
   * provider.
   * @param message Reason. For details, see the extension documentation provided by the extension
   * provider.
   *
   */
  @CalledByNative
  default void onErrorWithContext(ExtensionContext extContext, int error, String message){};
}
