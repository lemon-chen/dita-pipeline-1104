package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The information of the user.
 */
public class UserInfo {
  /**
   * The user ID.
   */
  public int uid;
  /**
   * User account.
   */
  public String userAccount;

  public UserInfo() {}
  public UserInfo(int uid, String userAccount) {
    this.uid = uid;
    this.userAccount = userAccount;
  }

  @CalledByNative
  public void SetUid(int uid) {
    this.uid = uid;
  }

  @CalledByNative
  public void SetUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }
}
