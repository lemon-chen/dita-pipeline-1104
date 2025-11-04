package io.agora.rtc2;

public class ExtensionInfo {
  /**
   * The type of media device.
   */
  public Constants.MediaSourceType mediaSourceType;
  /**
   *  The unique channel name for the AgoraRTC session in the string format. The string
   * length must be less than 64 bytes. Supported character scopes are:
   * - All lowercase English letters: a to z.
   * - All uppercase English letters: A to Z.
   * - All numeric characters: 0 to 9.
   * - The space character.
   * - Punctuation characters and other symbols, including: "!", "#", "$", "%", "&", "(", ")", "+",
   * "-",
   * ":", ";", "<", "=", ".", ">", "?", "@", "[", "]", "^", "_", " {", "}", "|", "~", ",".
   */
  public String channelId;
  /**
   * The id of the remote user on which the extension works.
   *
   * @note remoteUid = 0 means that the extension works on all remote streams.
   */
  public int remoteUid;

  /**
   * User ID: A 32-bit unsigned integer ranging from 1 to (2^32-1). It must be unique.
   */
  public int localUid;

  public ExtensionInfo() {
    this.mediaSourceType = Constants.MediaSourceType.UNKNOWN_MEDIA_SOURCE;
    this.remoteUid = 0;
    this.channelId = null;
    this.localUid = 0;
  }

  public ExtensionInfo(
      Constants.MediaSourceType mediaSourceType, int remoteUid, String channelId, int localUid) {
    this.mediaSourceType = mediaSourceType;
    this.remoteUid = remoteUid;
    this.channelId = channelId;
    this.localUid = localUid;
  }
}
