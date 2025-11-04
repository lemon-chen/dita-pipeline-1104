package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The context information of the extension.
 */
public class ExtensionContext {
  /**
   * Whether the `uid` in `ExtensionContext` is valid:
   * - `true`: The `uid` is valid.
   * - `false`: The `uid` is invalid.
   */
  public boolean isValid;
  /**
   * The user ID. 0 represents a local user, while greater than 0 represents a remote user.
   */
  public int uid;
  /**
   * The name of the extension provider.
   */
  public String providerName;
  /**
   * The name of the extension.
   */
  public String extensionName;

  @CalledByNative
  public ExtensionContext() {
    isValid = false;
    uid = 0;
    providerName = "";
    extensionName = "";
  }

  @CalledByNative
  public ExtensionContext(int uid, String providerName, String extensionName) {
    this.isValid = true;
    this.uid = uid;
    this.providerName = providerName;
    this.extensionName = extensionName;
  }
}
