package io.agora.mediaplayer;

import io.agora.base.VideoFrame;
import io.agora.base.internal.CalledByNative;
import io.agora.mediaplayer.Constants;

public interface IMediaPlayerVideoFrameObserver {
  /**
   * @brief Occurs each time the player receives a video frame.
   *
   * @details
   * After registering the video frame observer, the callback occurs every time the player receives a
   * video frame, reporting the detailed information of the video frame.
   *
   * @param frame The video frame information. See VideoFrame.
   *
   */
  @CalledByNative void onFrame(VideoFrame frame);
}
