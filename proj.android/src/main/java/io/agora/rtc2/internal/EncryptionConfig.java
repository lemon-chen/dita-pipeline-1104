package io.agora.rtc2.internal;

/**
 * @brief Built-in encryption configurations.
 */
public class EncryptionConfig {
  /**
   * @brief The built-in encryption mode.
   *
   * @details
   * Agora recommends using AES_128_GCM2 or AES_256_GCM2 encrypted mode. These two modes support the
   * use of salt for higher security.
   *
   */
  public enum EncryptionMode {
    /**
     * 1: 128-bit AES encryption, XTS mode.
     */
    AES_128_XTS(1),
    /**
     * 2: 128-bit AES encryption, ECB mode.
     */
    AES_128_ECB(2),
    /**
     * 3: 256-bit AES encryption, XTS mode.
     */
    AES_256_XTS(3),
    /**
     * 4: 128-bit SM4 encryption, ECB mode.
     */
    SM4_128_ECB(4),
    /**
     * 5: 128-bit AES encryption, GCM mode.
     */
    AES_128_GCM(5),
    /**
     * 6: 256-bit AES encryption, GCM mode.
     */
    AES_256_GCM(6),
    /**
     * 7: (Default) 128-bit AES encryption, GCM mode. This encryption mode requires the setting of salt
     * (`encryptionKdfSalt`).
     */
    AES_128_GCM2(7),
    /**
     * 8: 256-bit AES encryption, GCM mode. This encryption mode requires the setting of salt
     * (`encryptionKdfSalt`).
     */
    AES_256_GCM2(8),
    /**
     * Enumerator boundary.
     */
    MODE_END(9);
    private int value;

    private EncryptionMode(int v) {
      value = v;
    }

    public int getValue() {
      return this.value;
    }
  }

  /**
   * The built-in encryption mode. See `EncryptionMode`. Agora recommends using `AES_128_GCM2` or
   * `AES_256_GCM2` encrypted mode. These two modes support the use of salt for higher security.
   */
  public EncryptionMode encryptionMode;

  /**
   * Encryption key in string type with unlimited length. Agora recommends using a 32-byte key.
   * @note If you do not set an encryption key or set it as `NULL`, you cannot use the built-in
   * encryption, and the SDK returns `-2`.
   */
  public String encryptionKey;
  /**
   * Salt, 32 bytes in length. Agora recommends that you use OpenSSL to generate salt on the server
   * side. See Media Stream Encryption for details.
   * @note This parameter takes effect only in `AES_128_GCM2` or `AES_256_GCM2` encrypted mode. In
   * this case, ensure that this parameter is not `0`.
   */
  public final byte[] encryptionKdfSalt = new byte[32];
  /**
   * Whether to enable data stream encryption:
   * - `true`: Enable data stream encryption.
   * - `false`: (Default) Disable data stream encryption.
   */
  public boolean datastreamEncryptionEnabled;

  public EncryptionConfig() {
    encryptionMode = EncryptionMode.AES_128_GCM2;
    encryptionKey = null;
    datastreamEncryptionEnabled = false;
    java.util.Arrays.fill(encryptionKdfSalt, (byte) 0);
  }
}
