package io.agora.rtc2.live;

import io.agora.rtc2.Constants;
import io.agora.rtc2.video.AgoraImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @brief Transcoding configurations for Media Push.
 */
public class LiveTranscoding {
  /**
   * Audio sample rate (Hz).
   */
  public enum AudioSampleRateType {
    /**
     * 32000: 32 kHz.
     */
    TYPE_32000(32000),
    /**
     * (Default) 44100: 44.1 kHz.
     */
    TYPE_44100(44100),
    /**
     * 48000: 48 kHz.
     */
    TYPE_48000(48000);

    private int value;

    private AudioSampleRateType(int v) {
      value = v;
    }

    public static int getValue(AudioSampleRateType type) {
      return type.value;
    }
  }

  /**
   * @brief Video codec profile types.
   */
  public enum VideoCodecProfileType {
    /**
     * 66: Baseline video codec profile; generally used for video calls on mobile phones.
     */
    BASELINE(66),
    /**
     * 77: Main video codec profile; generally used in mainstream electronics such as MP4 players,
     * portable video players, PSP, and iPads.
     */
    MAIN(77),
    /**
     * 100: (Default) High video codec profile; generally used in high-resolution live streaming or
     * television.
     */
    HIGH(100);

    private int value;

    private VideoCodecProfileType(int v) {
      value = v;
    }

    public static int getValue(VideoCodecProfileType type) {
      return type.value;
    }
  }

  /**
   * @brief Self-defined audio codec profile.
   */
  public enum AudioCodecProfileType {
    /**
     * 0: (Default) LC-AAC.
     */
    LC_AAC(0),
    /**
     * 1: HE-AAC.
     */
    HE_AAC(1),
    /**
     * 2: HE-AAC v2.
     */
    HE_AAC_V2(2);

    private int value;

    private AudioCodecProfileType(int v) {
      value = v;
    }

    public static int getValue(AudioCodecProfileType type) {
      return type.value;
    }
  }

  /**
   * @brief The codec type of the output video.
   *
   * @since v3.2.0
   */
  public enum VideoCodecType {
    /**
     * 1: (Default) H.264.
     */
    H264(1),
    /**
     * 2: H.265.
     */
    H265(2);

    private int value;

    private VideoCodecType(int v) {
      value = v;
    }

    public static int getValue(VideoCodecType type) {
      return type.value;
    }
  }

  /**
   * The width of the video in pixels. The default value is 360.
   * - When pushing video streams to the CDN, the value range of `width` is [64,1920]. If the value is
   * less than 64, Agora server automatically adjusts it to 64; if the value is greater than 1920,
   * Agora server automatically adjusts it to 1920.
   * - When pushing audio streams to the CDN, set `width` and `height` as 0.
   */
  public int width;
  /**
   * The height of the video in pixels. The default value is 640.
   * - When pushing video streams to the CDN, the value range of` height` is [64,1080]. If the value
   * is less than 64, Agora server automatically adjusts it to 64; if the value is greater than 1080,
   * Agora server automatically adjusts it to 1080.
   * - When pushing audio streams to the CDN, set `width` and `height` as 0.
   */
  public int height;
  /**
   * The encoding bitrate (Kbps) of the video. This parameter does not need to be set; keeping the
   * default value `STANDARD_BITRATE` is sufficient. The SDK automatically matches the most suitable
   * bitrate based on the video resolution and frame rate you have set. For the correspondence between
   * video resolution and frame rate, see `Video profile`.
   */
  public int videoBitrate;
  /**
   * Frame rate (fps) of the output video stream set for Media Push. The default value is 15. The
   * value range is (0,30].
   * @note The Agora server adjusts any value over 30 to 30.
   */
  public int videoFramerate;
  /**
   * Latency mode:
   * - `true`: Low latency with unassured quality.
   * - `false`: (Default) High latency with assured quality.
   * 
   * @deprecated Deprecated as of v2.8.0. Agora does not recommend using this parameter.
   */
  @Deprecated public boolean lowLatency;
  /**
   * GOP (Group of Pictures) in fps of the video frames for Media Push. The default value is 30.
   */
  public int videoGop;

  /** watermark list */
  private ArrayList<AgoraImage> watermarkList;

  /**
   * add watermark to list
   *
   * @param watermark you want to add watermark.
   */
  public void addWatermark(AgoraImage watermark) {
    if (watermarkList == null) {
      watermarkList = new ArrayList<AgoraImage>();
    }
    watermarkList.add(watermark);
  }

  /**
   * remove watermark from list
   *
   * @param watermark you want to remove object.
   * @return if success, will return true. Otherwise, will return false.
   */
  public boolean removeWatermark(AgoraImage watermark) {
    if (watermarkList == null) {
      return false;
    }
    return watermarkList.remove(watermark);
  }

  /**
   * get object attribute of watermarkList
   *
   * @return watermark list
   */
  public ArrayList<AgoraImage> getWatermarkList() {
    return watermarkList;
  }

  /** backgroundImage list */
  private ArrayList<AgoraImage> backgroundImageList;

  /**
   * add background image to backgroundImageList
   *
   * @param backgroundImage you want to add background to list.
   */
  public void addBackgroundImage(AgoraImage backgroundImage) {
    if (backgroundImageList == null) {
      backgroundImageList = new ArrayList<AgoraImage>();
    }
    backgroundImageList.add(backgroundImage);
  }

  /**
   * remove background from background image list
   *
   * @param backgroundImage you want to remove background image
   * @return if success to remove, will return true. Otherwise, will return false.
   */
  public boolean removeBackgroundImage(AgoraImage backgroundImage) {
    if (backgroundImageList == null) {
      return false;
    }
    return backgroundImageList.remove(backgroundImage);
  }

  /**
   * get object attribute of backgroundImageList
   *
   * @return backgroundImage list
   */
  public ArrayList<AgoraImage> getBackgroundImageList() {
    return backgroundImageList;
  }

  /**
   * The audio sampling rate (Hz) of the output media stream. See `AudioSampleRateType`.
   */
  public AudioSampleRateType audioSampleRate;
  /**
   * Bitrate (Kbps) of the audio output stream for Media Push. The default value is 48, and the
   * highest value is 128.
   */
  public int audioBitrate;
  /**
   * The number of audio channels for Media Push. Agora recommends choosing 1 (mono), or 2 (stereo)
   * audio channels. Special players are required if you choose 3, 4, or 5.
   * - 1: (Default) Mono.
   * - 2: Stereo.
   * - 3: Three audio channels.
   * - 4: Four audio channels.
   * - 5: Five audio channels.
   */
  public int audioChannels;

  /**
   * Audio codec profile type for Media Push. See `AudioCodecProfileType`.
   */
  public AudioCodecProfileType audioCodecProfile;

  /**
   * Video codec profile type for Media Push. Set it as 66, 77, or 100 (default). See
   * `VideoCodecProfileType` for details.
   * @note If you set this parameter to any other value, Agora adjusts it to the default value.
   */
  public VideoCodecProfileType videoCodecProfile;
  /**
   * Video codec profile types for Media Push. See `VideoCodecType`.
   */
  public VideoCodecType videoCodecType;

  /**
   * Obsolete and not recommended for use. Use `getUserCount` instead.
   * The number of users in the Media Push. The value range is [0,17].
   * 
   * @deprecated This parameter is deprecated. We recommend using the {@link
   * LiveTranscoding#getUserCount() getUserCount} method.
   */
  @Deprecated public int userCount;

  /**
   * Obsolete and not recommended for use. Use `setBackgroundColor [1/2]` instead.
   * 
   * @deprecated This parameter is deprecated. We recommend using the {@link
   * LiveTranscoding#setBackgroundColor(int) setBackgroundColor} method to set the background
   * color.
   */
  @Deprecated public int backgroundColor;
  /**
   * Reserved property. Extra user-defined information to send SEI for the H.264/H.265 video stream to
   * the CDN live client. Maximum length: 4096 bytes. For more information on SEI, see SEI-related
   * questions.
   */
  public String userConfigExtraInfo;
  /**
   * Obsolete and not recommended for use.
   * The metadata sent to the CDN client.
   * 
   * @deprecated This parameter is deprecated.
   */
  @Deprecated public String metadata;

  /**
   * Manages the user layout configuration in the Media Push. Agora supports a maximum of 17
   * transcoding users in a Media Push channel. See `TranscodingUser`.
   */
  private Map<Integer, TranscodingUser> transcodingUsers;

  /**
   * Key sets:
   * - "lbhq": low bitrate high quality.
   * - "veo": video encoder optimum.
   * Value: `true` or `false`, representing whether the advanced feature is enabled.
   */
  private Map<String, Boolean> advancedFeatures;

  /**
   * Enables/Disables advanced features of the RTMP or RTMPS streaming with transcoding.
   *
   * @param featureName The name of the advanced feature. It contains LBHQ and VEO.
   * @param opened Whether to enable the advanced feature:
   *               - `true`: Enable the advanced feature.
   *               - `false`: (Default) Disable the advanced feature.
   */
  public void setAdvancedFeatures(String featureName, Boolean opened) {
    advancedFeatures.put(featureName, opened);
  }

  /**
   * Checks whether advanced features of the RTMP or RTMPS streaming with transcoding are enabled.
   *
   * @return The name of each advanced feature and whether the advanced feature is enabled.
   */
  public Map<String, Boolean> getAdvancedFeatures() {
    return advancedFeatures;
  }

  /**
   * @brief Transcoding configurations of each host.
   */
  public static class TranscodingUser {
    /**
     * The user ID of the host.
     */
    public int uid;

    public String userId;
    /**
     * The x coordinate (pixel) of the host's video on the output video frame (taking the upper left
     * corner of the video frame as the origin). The value range is [0, width], where width is the
     * `width` set in `LiveTranscoding`.
     */
    public int x;
    /**
     * The y coordinate (pixel) of the host's video on the output video frame (taking the upper left
     * corner of the video frame as the origin). The value range is [0, height], where height is the
     * `height` set in `LiveTranscoding`.
     */
    public int y;
    /**
     * The width (pixel) of the host's video.
     */
    public int width;
    /**
     * The height (pixel) of the host's video.
     */
    public int height;
    /**
     * The layer index number of the host's video. The value range is [0, 100].
     * - 0: (Default) The host's video is the bottom layer.
     * - 100: The host's video is the top layer.
     * @note
     * - If the value is less than 0 or greater than 100, `ERR_INVALID_ARGUMENT` error is returned.
     * - Setting zOrder to 0 is supported.
     */
    public int zOrder;
    /**
     * The transparency of the host's video. The value range is [0.0,1.0].
     * - 0.0: Completely transparent.
     * - 1.0: (Default) Opaque.
     */
    public float alpha;
    /**
     * The audio channel used by the host's audio in the output audio. The default value is 0, and the
     * value range is [0, 5].
     * - `0`: (Recommended) The defaut setting, which supports dual channels at most and depends on the
     * upstream of the host.
     * - `1`: The host's audio uses the FL audio channel. If the host's upstream uses multiple audio
     * channels, the Agora server mixes them into mono first.
     * - `2`: The host's audio uses the FC audio channel. If the host's upstream uses multiple audio
     * channels, the Agora server mixes them into mono first.
     * - `3`: The host's audio uses the FR audio channel. If the host's upstream uses multiple audio
     * channels, the Agora server mixes them into mono first.
     * - `4`: The host's audio uses the BL audio channel. If the host's upstream uses multiple audio
     * channels, the Agora server mixes them into mono first.
     * - `5`: The host's audio uses the BR audio channel. If the host's upstream uses multiple audio
     * channels, the Agora server mixes them into mono first.
     * - `0xFF` or a value greater than `5`: The host's audio is muted, and the Agora server removes the
     * host's audio.
     * @note If the value is not `0`, a special player is required.
     */
    public int audioChannel;

    public TranscodingUser() {
      uid = 0;
      alpha = 1;
    }
  }

  public LiveTranscoding() {
    width = 360;
    height = 640;
    videoBitrate = 400;
    videoCodecProfile = VideoCodecProfileType.HIGH;
    videoCodecType = VideoCodecType.H264;
    videoGop = 30;
    videoFramerate = 15;
    lowLatency = false;
    audioSampleRate = AudioSampleRateType.TYPE_44100;
    audioBitrate = 48;
    audioChannels = 1;
    audioCodecProfile = AudioCodecProfileType.LC_AAC;
    advancedFeatures = new HashMap<String, Boolean>();
    backgroundColor = 0xFF000000;
    userConfigExtraInfo = null;
    metadata = null;
    transcodingUsers = new HashMap<Integer, TranscodingUser>();
  }

  /**
   * Adds a user displaying the video in CDN live.
   *
   * @param user {@link TranscodingUser transcodingUser}.
   * @return
   * <ul>
   *     <li>0: Success.</li>
   *     <li><0: Failure.</li>
   * </ul>
   */
  public int addUser(TranscodingUser user) {
    if (user == null) {
      return -Constants.ERR_INVALID_ARGUMENT;
    }

    transcodingUsers.put(user.uid, user);
    userCount = transcodingUsers.size();
    return Constants.ERR_OK;
  }

  /**
   * Gets the user list in CDN live.
   *
   * This method retrieves all users in CDN live. The user list returned by this method is read-only
   * and should not be modified.
   *
   * @return User list in a array.
   */
  public final ArrayList<TranscodingUser> getUsers() {
    Collection<TranscodingUser> values = transcodingUsers.values();
    return new ArrayList<TranscodingUser>(values);
  }

  /**
   * Sets the users in batches in CDN live.
   *
   * This method sets all users involved in the CDN live stream. This method replaces the old user
   * data with the new user data.
   *
   * @param users All users involved in CDN live.
   */
  public void setUsers(ArrayList<TranscodingUser> users) {
    transcodingUsers.clear();
    if (users != null) {
      for (TranscodingUser user : users) {
        transcodingUsers.put(user.uid, user);
      }
    }
    userCount = transcodingUsers.size();
  }

  /**
   * Sets the users in batches in CDN live.
   * This method sets all users involved in CDN live. This method replaces the old user data with
   * the the new user data.
   *
   * @param users All users involved in CDN live.
   */
  public void setUsers(Map<Integer, TranscodingUser> users) {
    transcodingUsers.clear();
    if (users != null) {
      transcodingUsers.putAll(users);
    }

    userCount = transcodingUsers.size();
  }

  /**
   * Removes a user from CDN live.
   *
   * @param uid User ID of the user to remove from CDN live.
   * @return
   * <ul>
   *     <li>0: Success.
   *     <li>< 0: Failure.
   * </ul>
   */
  public int removeUser(int uid) {
    if (!transcodingUsers.containsKey(uid))
      return -Constants.ERR_INVALID_ARGUMENT;

    transcodingUsers.remove(uid);
    userCount = transcodingUsers.size();
    return Constants.ERR_OK;
  }

  /**
   * Gets the number of users transcoded in CDN live.
   *
   * @return Number of users transcoded in CDN live.
   */
  public int getUserCount() {
    return transcodingUsers.size();
  }

  /**
   * Gets the background color in hex.
   *
   * @return Background color to set in RGB hex value.
   */
  public int getBackgroundColor() {
    return this.backgroundColor;
  }

  /**
   * Sets the background color of the CDN live stream in the format of RGB hex.
   *
   * @param color Background color to set in RGB hex value. Value only, do not include a #. For
   *     example, 0xFFB6C1 (light pink). The default value is 0x000000 (black).
   */
  public void setBackgroundColor(int color) {
    this.backgroundColor = color;
  }

  /**
   * Sets the background color in RGB format.
   *
   * @param red Red component.
   * @param green Green component.
   * @param blue Blue component.
   */
  public void setBackgroundColor(int red, int green, int blue) {
    this.backgroundColor = (red << 16) | (green << 8) | blue;
  }

  /**
   * @deprecated
   * Gets the background color's red component.
   *
   * @return Background color's red component.
   */
  @Deprecated
  public int getRed() {
    return (backgroundColor >> 16) & 0x0ff;
  }

  /**
   * @deprecated
   * Gets the background color's green component.
   *
   * @return Background color's green component.
   */
  @Deprecated
  public int getGreen() {
    return (backgroundColor >> 8) & 0x0ff;
  }

  /**
   * @deprecated
   * Gets the background color's blue component.
   *
   * @return Background color's blue component.
   */
  @Deprecated
  public int getBlue() {
    return backgroundColor & 0x0ff;
  }

  /**
   * @deprecated
   * Sets the background color's red component.
   */
  @Deprecated
  public void setRed(int red) {
    int green = getGreen();
    int blue = getBlue();
    this.backgroundColor = (red << 16) | (green << 8) | blue;
  }

  /**
   * @deprecated
   * Sets the background color's green component.
   *
   */
  @Deprecated
  public void setGreen(int green) {
    int red = getRed();
    int blue = getBlue();
    this.backgroundColor = (red << 16) | (green << 8) | blue;
  }

  /**
   * @deprecated
   * Sets the background color's blue component.
   *
   */
  @Deprecated
  public void setBlue(int blue) {
    int red = getRed();
    int green = getGreen();
    this.backgroundColor = (red << 16) | (green << 8) | blue;
  }
}
