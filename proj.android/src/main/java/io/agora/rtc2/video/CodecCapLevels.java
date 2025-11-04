package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;

/**
 * @brief The level of the codec capability.
 */
public class CodecCapLevels {
  /**
   * Hardware decoding capability level, which represents the device's ability to perform hardware
   * decoding on videos of different quality.
   */
  public int hwDecodingLevel;
  /**
   * Software decoding capability level, which represents the device's ability to perform software
   * decoding on videos of different quality.
   */
  public int swDecodingLevel;

  public CodecCapLevels() {
    this.hwDecodingLevel = Constants.CODEC_CAPABILITY_LEVEL_UNSPECIFIED;
    this.swDecodingLevel = Constants.CODEC_CAPABILITY_LEVEL_UNSPECIFIED;
  };

  @CalledByNative
  public CodecCapLevels(int hwDecLevel, int swDecLevel) {
    this.hwDecodingLevel = hwDecLevel;
    this.swDecodingLevel = swDecLevel;
  };
};
