//
//  Agora RTC/MEDIA SDK
//
//  Copyright (c) 2019 Agora.io. All rights reserved.
//
package io.agora.mediaplayer;

import io.agora.base.AudioFrame;
import io.agora.base.internal.CalledByNative;

/**
 * @brief The audio frame observer interface for the media player.
 */
public interface IMediaPlayerAudioFrameObserver {
  /**
   * @brief Occurs each time the player receives an audio frame.
   *
   * @details
   * After registering the audio frame observer, the callback occurs every time the player receives an
   * audio frame, reporting the detailed information of the audio frame.
   *
   * @param frame The audio frame information. See AudioFrame.
   *
   * @return
   * Passes in the audio data after setting the `mode` parameter in `registerAudioFrameObserver` for
   * handling audio data.
   */
  @CalledByNative AudioFrame onFrame(AudioFrame frame);
}
