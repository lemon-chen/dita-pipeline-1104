package io.agora.rtc2;

import android.view.SurfaceView;
import io.agora.base.internal.CalledByNative;

/**
 * @brief The configuration of the audio and video call loop test.
 */
public class EchoTestConfiguration {
  /**
   * The view used to render the local user's video. This parameter is only applicable to scenarios
   * testing video devices, that is, when `enableVideo` is true.
   */
  public SurfaceView view = null;
  /**
   * Whether to enable the audio device for the loop test:
   * - `true`: (Default) Enable the audio device. To test the audio device, set this parameter as
   * true.
   * - `false`: Disable the audio device.
   */
  public boolean enableAudio = true;
  /**
   * Whether to enable the video device for the loop test:
   * - `true`: (Default) Enable the video device. To test the video device, set this parameter as
   * true.
   * - `false`: Disable the video device.
   */
  public boolean enableVideo = true;
  /**
   * The token used to secure the audio and video call loop test. If you do not enable App Certificate
   * in Agora Console, you do not need to pass a value in this parameter; if you have enabled App
   * Certificate in Agora Console, you must pass a token in this parameter; the `uid` used when you
   * generate the token must be 0xFFFFFFFF, and the channel name used must be the channel name that
   * identifies each audio and video call loop tested. For server-side token generation, see .
   */
  public String token = null;
  /**
   * The channel name that identifies each audio and video call loop. To ensure proper loop test
   * functionality, the channel name passed in to identify each loop test cannot be the same when
   * users of the same project (App ID) perform audio and video call loop tests on different devices.
   */
  public String channelId = null;

  /**
   * Set the time interval or delay for returning the results of the audio and video loop test. The
   * value range is [2,10], in seconds, with the default value being 2 seconds.
   * - For audio loop tests, the test results will be returned according to the time interval you set.
   * - For video loop tests, the video will be displayed in a short time, after which the delay will
   * gradually increase until it reaches the delay you set.
   */
  public int intervalInSeconds = 2;

  @CalledByNative
  public EchoTestConfiguration(SurfaceView view, boolean enableAudio, boolean enableVideo,
      String token, String channelId, int intervalInSeconds) {
    this.view = view;
    this.enableAudio = enableAudio;
    this.enableVideo = enableVideo;
    this.token = token;
    this.channelId = channelId;
    this.intervalInSeconds = intervalInSeconds;
  }

  @CalledByNative
  public EchoTestConfiguration(
      SurfaceView view, boolean enableAudio, boolean enableVideo, String token, String channelId) {
    this(view, enableAudio, enableVideo, token, channelId, 10);
  }

  @CalledByNative
  public EchoTestConfiguration() {
    this.view = null;
    this.enableAudio = true;
    this.enableVideo = true;
    this.token = null;
    this.channelId = null;
  }
}
