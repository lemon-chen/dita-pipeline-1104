package io.agora.rtc2.proxy;
import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;
import java.util.ArrayList;

/**
 * @brief Local Access Point configuration.
 */
public class LocalAccessPointConfiguration {
  /**
   * @brief Configuration information of the log server.
   */
  public static class LogUploadServerInfo {
    /**
     * The domain name of the log server.
     */
    public String serverDomain = null;
    /**
     * The storage path of the log on the server.
     */
    public String serverPath = null;
    /**
     * The port of the log server.
     */
    public int serverPort = 0;
    /**
     * Whether the log server uses the HTTPS protocol:
     * - `true`: Uses the HTTPS protocol.
     * - `false`: Uses the HTTP protocol.
     */
    public boolean serverHttps = true;
    public LogUploadServerInfo() {
      serverDomain = null;
      serverPath = null;
      serverPort = 0;
      serverHttps = true;
    }

    public LogUploadServerInfo(String domain, String path, int port, boolean https) {
      serverDomain = domain;
      serverPath = path;
      serverPort = port;
      serverHttps = https;
    }

    @CalledByNative("LogUploadServerInfo")
    public String getServerDomain() {
      return serverDomain;
    }

    @CalledByNative("LogUploadServerInfo")
    public String getServerPath() {
      return serverPath;
    }

    @CalledByNative("LogUploadServerInfo")
    public int getServerPort() {
      return serverPort;
    }

    @CalledByNative("LogUploadServerInfo")
    public boolean getServerHttps() {
      return serverHttps;
    }
  };

  /**
   * @brief Advanced options for the Local Access Point.
   */
  public static class AdvancedConfigInfo {
    /**
     * Custom log upload server. By default, the SDK uploads logs to the Agora log server. You can use this parameter to change the log upload server. See `LogUploadServerInfo`.
     */
    public LogUploadServerInfo logUploadServer = null;

    public AdvancedConfigInfo() {
      logUploadServer = new LogUploadServerInfo();
    }

    @CalledByNative("AdvancedConfigInfo")
    public LogUploadServerInfo getLogUploadServerInfo() {
      return logUploadServer;
    }
  };
  /**
   * The list of internal IP addresses for the Local Access Point. Either `ipList` or `domainList` must be provided.
   */
  public ArrayList<String> ipList = null;
  /**
   * The domain name list for the Local Access Point. The SDK resolves the IP addresses of the Local Access Point based on the domain names you provide. The domain name resolution timeout is 10 seconds. At least one of `ipList` or `domainList` must be provided. If you specify both IP addresses and domain names, the SDK merges and deduplicates the IP addresses resolved from the domain names and the ones you specify, then randomly connects to one IP address to achieve load balancing.
   */
  public ArrayList<String> domainList = null;
  /**
   * Intranet certificate verification domain. If the value is empty, the SDK uses the default certificate verification domain `secure-edge.local`.
   */
  public String verifyDomainName = null;
  /**
   * Connection mode:
   * - LOCAL_RPOXY_CONNECTIVITY_FIRST (0): The SDK first attempts to connect to the specified Agora private media server; if the connection to the specified Agora private media server fails, it connects to the Agora SD-RTN™.
   * - LOCAL_RPOXY_LOCAL_ONLY (1): The SDK only attempts to connect to the specified Agora private media server.
   */
  public int mode = Constants.LOCAL_RPOXY_CONNECTIVITY_FIRST;
  /**
    * Whether to disable vos-aut:
    - true: (Default)disable vos-aut.
    - false: not disable vos-aut
  */
  public boolean disableAut = true;
  /**
   * Advanced options for the Local Access Point. See `AdvancedConfigInfo` for details.
   */
  public AdvancedConfigInfo advancedConfig = null;
}
