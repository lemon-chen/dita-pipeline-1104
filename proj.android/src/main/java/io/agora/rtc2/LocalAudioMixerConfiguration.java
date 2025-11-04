package io.agora.rtc2;

import java.util.ArrayList;
import io.agora.rtc2.Constants;

/**
 * @brief The configurations for mixing the local audio.
 */
public class LocalAudioMixerConfiguration {
  /**
   * The source of the audio streams that are mixed locally. See `MixedAudioStream`.
   */
  public ArrayList<MixedAudioStream> audioInputStreams;
  /**
   * Whether the mxied audio stream uses the timestamp of the audio frames captured by the local
   * microphone.
   * - `true`: (Default) Yes. Set to this value if you want all locally captured audio streams
   * synchronized.
   * - `false`: No. The SDK uses the timestamp of the audio frames at the time when they are mixed.
   */
  public boolean syncWithLocalMic;

  public LocalAudioMixerConfiguration() {
    audioInputStreams = new ArrayList<MixedAudioStream>();
    syncWithLocalMic = true;
  }

  /**
   * @brief The source of the audio streams that are mixed locally.
   */
  public static class MixedAudioStream {
    /**
     * The user ID of the remote user.
     * @note Set this parameter if the source type of the locally mixed audio steams is
     * AUDIO_SOURCE_REMOTE_USER.
     */
    public int remoteUserUid;

    /**
     * The channel name. This parameter signifies the channel in which users engage in real-time audio
     * and video interaction. Under the premise of the same App ID, users who fill in the same channel
     * ID enter the same channel for audio and video interaction. The string length must be less than 64
     * bytes. Supported characters (89 characters in total):
     * - All lowercase English letters: a to z.
     * - All uppercase English letters: A to Z.
     * - All numeric characters: 0 to 9.
     * - "!", "#", "$", "%", "&", "(", ")", "+", "-", ":", ";", "<", "=", ".", ">", "?", "@", "[", "]",
     * "^", "_", "{", "}", "|", "~", ","
     * @note Set this parameter if the source type of the locally mixed audio streams is
     * AUDIO_SOURCE_REMOTE_CHANNEL or AUDIO_SOURCE_REMOTE_USER.
     */
    public String channelId;
    /**
     * The type of the audio source. See `AudioSourceType`.
     */
    public Constants.AudioSourceType sourceType;
    /**
     * The audio track ID. Set this parameter to the custom audio track ID returned in
     * `createCustomAudioTrack`.
     * @note Set this parameter if the source type of the locally mixed audio steams is
     * AUDIO_SOURCE_CUSTOM.
     */
    public int trackId;

    public MixedAudioStream() {
      this.sourceType = Constants.AudioSourceType.AUDIO_SOURCE_UNKNOWN;
      this.remoteUserUid = 0;
      this.channelId = "";
      this.trackId = 0;
    }
  };
}
