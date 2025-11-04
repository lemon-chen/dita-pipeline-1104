package io.agora.rtc2;

import android.content.Context;
import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.internal.AgoraExtension;
import io.agora.rtc2.IRtcEngineEventHandler;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Configurations for the `RtcEngineConfig` instance.
 */
public class RtcEngineConfig {
  /**
   * The event handler for `RtcEngine`. See `IRtcEngineEventHandler`.
   */
  public IRtcEngineEventHandler mEventHandler;
  /**
   * The context of Android Activity.
   */
  public Context mContext;
  /**
   * The App ID issued by Agora for your project. Only users in apps with the same App ID can join the
   * same channel and communicate with each other. An App ID can only be used to create one
   * `RtcEngine` instance. To change your App ID, call `destroy()` to destroy the current
   * `RtcEngine` instance, and then create a new one.
   */
  public String mAppId;

  /**
   * The region for connection. This is an advanced feature and applies to scenarios that have
   * regional restrictions. For details on supported regions, see `AreaCode`. The area codes support
   * bitwise operation.
   */
  public int mAreaCode;

  /**
   * The channel profile.
   * - CHANNEL_PROFILE_COMMUNICATION (0): Communication. Agora recommends using the live streaming
   * profile for a better audio and video experience.
   * - CHANNEL_PROFILE_LIVE_BROADCASTING (1): (Default) Live streaming.
   * - CHANNEL_PROFILE_GAME (2): Gaming.
   * Deprecated:
   * Use CHANNEL_PROFILE_LIVE_BROADCASTING instead.
   * - CHANNEL_PROFILE_CLOUD_GAMING (3): Interaction. The scenario is optimized for latency. Use this
   * profile if the use case requires frequent interactions between users.
   * Deprecated:
   * Use CHANNEL_PROFILE_LIVE_BROADCASTING instead.
   */
  public int mChannelProfile;

  /**
   * The license used for verification when connectting channel. Charge according to the license
   */
  public String mLicense;

  /**
   * The audio scenarios. Under different audio scenarios, the device uses different volume types.
   * - `AUDIO_SCENARIO_DEFAULT`(0): (Default) Automatic scenario, where the SDK chooses the
   * appropriate audio quality according to the user role and audio route.
   * - `AUDIO_SCENARIO_GAME_STREAMING`(3): High-quality audio scenario, where users mainly play music.
   * - `AUDIO_SCENARIO_CHATROOM`(5): Chatroom scenario, where users need to frequently switch the user
   * role or mute and unmute the microphone.
   * - `AUDIO_SCENARIO_CHORUS`(7): Real-time chorus scenario, where users have good network conditions
   * and require ultra-low latency.
   * - `AUDIO_SCENARIO_MEETING`(8): Meeting scenario that mainly involves the human voice.
   * - `AUDIO_SCENARIO_AI_CLIENT`(10): AI conversation scenario, which is only applicable to scenarios
   * where the user interacts with the conversational AI agent created by `Conversational AI Engine`.
   * @note Before using this enumeration, you need to call `getAudioDeviceInfo` to see whether the
   * audio device supports ultra-low-latency capture and playback. To experience ultra-low latency,
   * you need to ensure that your audio device supports ultra-low latency (
   * `isLowLatencyAudioSupported` = `true`).
   */
  public int mAudioScenario;

  /**
   * Whether to automatically register the Agora extensions when initializing `RtcEngine`:
   * - `true`: (Default) Automatically register the Agora extensions when initializing `RtcEngine`.
   * - `false`: Do not register the Agora extensions when initializing `RtcEngine`. You need to call
   * `enableExtension` to register the Agora extensions.
   */
  public boolean mAutoRegisterAgoraExtensions;

  /**
   * Extension libraries.
   */
  public List<String> mExtensionList;
  /**
   * The `IMediaExtensionObserver` instance.
   */
  public IMediaExtensionObserver mExtensionObserver;
  /**
   * Sets the log file size. See `LogConfig`.
   * By default, the SDK generates five SDK log files and five API call log files with the following
   * rules:
   * - The SDK log files are: `agorasdk.log`, `agorasdk.1.log`, `agorasdk.2.log`, `agorasdk.3.log`,
   * and `agorasdk.4.log`.
   * - The API call log files are: `agoraapi.log`, `agoraapi.1.log`, `agoraapi.2.log`,
   * `agoraapi.3.log`, and `agoraapi.4.log`.
   * - The default size of each SDK log file and API log file is 2,048 KB. These log files are encoded
   * in UTF-8.
   * - The SDK writes the latest logs in `agorasdk.log` or `agoraapi.log`.
   * - When `agorasdk.log` is full, the SDK processes the log files in the following order:1. Delete
   * the `agorasdk.4.log` file (if any).
   * 2. Rename `agorasdk.3.log` to `agorasdk.4.log`.
   * 3. Rename `agorasdk.2.log` to `agorasdk.3.log`.
   * 4. Rename `agorasdk.1.log` to `agorasdk.2.log`.
   * 5. Create a new `agorasdk.log` file.
   * - The overwrite rules for the `agoraapi.log` file are the same as for `agorasdk.log`.
   */
  public LogConfig mLogConfig;

  /**
   * Thread priority type.
   * see {@link io.agora.rtc2.Constants#ThreadPriorityType ThreadPriorityType}
   */
  public Integer mThreadPriority;
  /**
   * Specifies the storage directory for the `.so` files. The storage directory must be a valid and
   * private directory of the app, which can be obtained using `Context.getDir()`.
   * - If you set this parameter, the SDK automatically loads the `.so` files in the directory you
   * specify, so that the app dynamically loads the required `.so` files when it runs, thereby
   * reducing the package size.
   * - If you do not set this parameter or set it to null, the SDK loads the `.so` files from the
   * default app's `native unreal bpLibraryPath` when compiling the app, which increases the package
   * size compared to the previous method.
   * @note
   * - This method is applicable when you integrate the SDK manually but not when you integrate the
   * SDK with Maven Central or JitPack.
   * - Ensure the specified directory exists; otherwise, the `RtcEngine` initialization fails.
   */
  public String mNativeLibPath;

  /**
   * Whether to enable domain name restriction:
   * - `true`: Enables the domain name restriction. This value is suitable for scenarios where IoT
   * devices use IoT cards for network access. The SDK will only connect to servers in the domain name
   * or IP whitelist that has been reported to the operator.
   * - `false`: (Default) Disables the domain name restriction. This value is suitable for most common
   * scenarios.
   */
  public boolean mDomainLimit;

  /**
   * @brief The region for connection, which is the region where the server the SDK connects to is
   * located.
   */
  public static class AreaCode {
    public final static int AREA_CODE_NONE = 0;
    /**
     * Mainland China.
     */
    public final static int AREA_CODE_CN = 0x00000001;
    /**
     * North America.
     */
    public final static int AREA_CODE_NA = 0x00000002;
    /**
     * Europe.
     */
    public final static int AREA_CODE_EU = 0x00000004;
    /**
     * Asia, excluding Mainland China.
     */
    public final static int AREA_CODE_AS = 0x00000008;
    /**
     * Japan.
     */
    public final static int AREA_CODE_JP = 0x00000010;
    /**
     * India.
     */
    public final static int AREA_CODE_IN = 0x00000020;
    /** Russia */
    public final static int AREA_CODE_RU = 0x00001000;
    /**
     * Global.
     */
    public final static int AREA_CODE_GLOB = 0xFFFFFFFF;

    private AreaCode() {}
  };

  /**
   * @brief Configuration of Agora SDK log files.
   */
  public static class LogConfig {
    /**
     * The complete path of the log files. Agora recommends using the default log directory. If you need
     * to modify the default directory, ensure that the directory you specify exists and is writable.
     * The default path is /storage/emulated/0/Android/data/<packagename>/files/agorasdk.log.
     */
    public String filePath;
    /**
     * The size (KB) of an `agorasdk.log` file. The value range is [128,20480]. The default value is
     * 2,048 KB. If you set `fileSizeInKByte` smaller than 128 KB, the SDK automatically adjusts it to
     * 128 KB; if you set `fileSizeInKByte` greater than 20,480 KB, the SDK automatically adjusts it to
     * 20,480 KB.
     */
    public int fileSizeInKB;
    /**
     * The output level of the SDK log file. See `LogLevel`.
     * For example, if you set the log level to WARN, the SDK outputs the logs within levels FATAL,
     * ERROR, and WARN.
     */
    public int level = Constants.LogLevel.getValue(Constants.LogLevel.LOG_LEVEL_INFO);

    @CalledByNative("LogConfig")
    public String getFilePath() {
      return filePath;
    }

    @CalledByNative("LogConfig")
    public int getFileSize() {
      return fileSizeInKB;
    }

    @CalledByNative("LogConfig")
    public int getLevel() {
      return level;
    }
  }

  public RtcEngineConfig() {
    mEventHandler = null;
    mContext = null;
    mAppId = "";
    mChannelProfile = Constants.CHANNEL_PROFILE_LIVE_BROADCASTING;
    mLicense = "";
    mAudioScenario = Constants.AUDIO_SCENARIO_DEFAULT;
    mAreaCode = AreaCode.AREA_CODE_GLOB;
    mExtensionList = new ArrayList<String>();
    mExtensionObserver = null;
    mLogConfig = new LogConfig();
    mThreadPriority = null;
    mDomainLimit = false;
    mAutoRegisterAgoraExtensions = true;
  }

  /**
   * @brief Adds the extension.
   *
   * @param providerName The name of the extension to add.
   *
   */
  public void addExtension(String providerName) {
    mExtensionList.add(providerName);
  }

  @CalledByNative
  public Context getContext() {
    return mContext;
  }

  @CalledByNative
  public String getAppId() {
    return mAppId;
  }

  @CalledByNative
  public int getChannelProfile() {
    return mChannelProfile;
  }

  @CalledByNative
  public String getLicense() {
    return mLicense;
  }

  @CalledByNative
  public int getAudioScenario() {
    return mAudioScenario;
  }

  @CalledByNative
  public int getAreaCode() {
    return mAreaCode;
  }

  @CalledByNative
  public IMediaExtensionObserver getExtensionObserver() {
    return mExtensionObserver;
  }

  @CalledByNative
  public LogConfig getLogConfig() {
    return mLogConfig;
  }

  @CalledByNative
  public Integer getThreadPriority() {
    return mThreadPriority;
  }

  @CalledByNative
  public boolean getDomainLimit() {
    return mDomainLimit;
  }

  @CalledByNative
  public boolean getAutoRegisterAgoraExtensions() {
    return mAutoRegisterAgoraExtensions;
  }

  @CalledByNative
  public String getNativeLibPath() {
    return mNativeLibPath;
  }
}
