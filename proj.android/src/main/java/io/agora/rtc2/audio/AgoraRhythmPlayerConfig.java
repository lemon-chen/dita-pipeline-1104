package io.agora.rtc2.audio;

import io.agora.base.internal.CalledByNative;

/**
 * @brief The metronome configuration.
 */
public class AgoraRhythmPlayerConfig {
  /**
   * The number of beats per measure, which ranges from 1 to 9. The default value is 4, which means
   * that each measure contains one downbeat and three upbeats.
   */
  public int beatsPerMeasure;
  /**
   * The beat speed (beats/minute), which ranges from 60 to 360. The default value is 60, which means
   * that the metronome plays 60 beats in one minute.
   */
  public int beatsPerMinute;

  public AgoraRhythmPlayerConfig() {
    this.beatsPerMeasure = 4;
    this.beatsPerMinute = 60;
  }

  @CalledByNative
  public int getBeatsPerMeasure() {
    return beatsPerMeasure;
  }

  @CalledByNative
  public int getBeatsPerMinute() {
    return beatsPerMinute;
  }
}
