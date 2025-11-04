package io.agora.rtc2.video;
import io.agora.rtc2.Constants;
/**
 * @brief Screenshot and upload configuration.
 *
 * @since v3.4.2.
 */
public class ContentInspectConfig {
  /**
   * 0: (Default) No actual function. Do not set `type` to this value.
   */
  public final static int CONTENT_INSPECT_TYPE_INVALID = 0;
  /**
   * @deprecated
   * Content inspect type moderation
   */
  @Deprecated public final static int CONTENT_INSPECT_TYPE_MODERATION = 1;
  /**
   * 2: Video screenshot and upload via Agora self-developed extension. The SDK takes screenshots of
   * videos sent by local users and upload them.
   */
  public final static int CONTENT_INSPECT_TYPE_SUPERVISE = 2;
  /**
   * 3: Video screenshot and upload via extensions from Agora Extensions Marketplace. SDK uses video
   * moderation extensions from Agora Extensions Marketplace to take screenshots of the video stream
   * in the channel and uploads them.
   */
  public final static int CONTENT_INSPECT_TYPE_IMAGE_MODERATION = 3;
  public static final int MAX_CONTENT_INSPECT_MODULE_COUNT = 32;
  /**
   * Additional information on the video content (maximum length: 1024 Bytes).
   * The SDK sends the screenshots and additional information on the video content to the Agora
   * server. Once the video screenshot and upload process is completed, the Agora server sends the
   * additional information and the callback notification to your server.
   */
  public String extraInfo;
  /**
   * (Optional) Server configuration related to uploading video screenshots via extensions from Agora
   * Extensions Marketplace. This parameter only takes effect when `type` in `ContentInspectModule` is
   * set to `CONTENT_INSPECT_TYPE_IMAGE_MODERATION`. If you want to use it, contact `technical
   * support`.
   */
  public String serverConfig;
  /**
   * Functional module. See `ContentInspectModule`.
   * A maximum of 32 `ContentInspectModule` instances can be configured, and the value range of
   * `MAX_CONTENT_INSPECT_MODULE_COUNT` is an integer in [1,32].
   * @note A function module can only be configured with one instance at most. Currently only the
   * video screenshot and upload function is supported.
   */
  public ContentInspectModule[] modules;
  /**
   * The number of functional modules, that is,the number of configured `ContentInspectModule`
   * instances, must be the same as the number of instances configured in `modules`. The maximum
   * number is 32.
   */
  public int moduleCount;

  /**
   * @brief `ContentInspectModule` class, a structure used to configure the frequency of video
   * screenshot and upload.
   */
  public static class ContentInspectModule {
    /**
     * Types of functional modules:
     * - CONTENT_INSPECT_TYPE_INVALID (0): (Default) This module has no actual function. Do not set to
     * this value.
     * - CONTENT_INSPECT_TYPE_SUPERVISE (2): Video screenshot and upload via Agora self-developed
     * extension. SDK takes screenshots of the video stream in the channel and uploads them.
     * - CONTENT_INSPECT_TYPE_IMAGE_MODERATION (3): Video screenshot and upload via extensions from
     * Agora Extensions Marketplace. SDK uses video moderation extensions from Agora Extensions
     * Marketplace to take screenshots of the video stream in the channel and uploads them.
     */
    public int type;
    /**
     * The frequency (s) of video screenshot and upload. The value should be set as larger than 0. The
     * default value is 0, the SDK does not take screenshots. Agora recommends that you set the value as
     * 10; you can also adjust it according to your business needs.
     */
    public int interval;
    /**
     * The position of the video observer. See `VideoModulePosition`.
     */
    public Constants.VideoModulePosition position;

    public ContentInspectModule() {
      /**
       * Default content inspect type is invalid.
       */
      type = CONTENT_INSPECT_TYPE_INVALID;
      /**
       * Default content inspect frequency is 0 seconds.
       */
      interval = 0;
      /**
       * Default content inspect position is VIDEO_MODULE_POSITION_PRE_ENCODER.
       */
      position = Constants.VideoModulePosition.VIDEO_MODULE_POSITION_PRE_ENCODER;
    }
  }

  public ContentInspectConfig() {
    modules = new ContentInspectModule[MAX_CONTENT_INSPECT_MODULE_COUNT];
    for (int i = 0; i < MAX_CONTENT_INSPECT_MODULE_COUNT; i++) {
      modules[i] = new ContentInspectModule();
    }
    moduleCount = 0;
  }
}
