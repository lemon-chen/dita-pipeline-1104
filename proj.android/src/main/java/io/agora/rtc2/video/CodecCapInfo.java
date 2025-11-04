package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.video.CodecCapLevels;

/**
 * @brief The codec capability of the SDK.
 */
public class CodecCapInfo {
  /**
   * The video codec types:
   * - 1: VP8
   * - 2: H.264.
   * - 3: (Default) H.265.
   */
  public int codecType;
  /**
   * The bit mask of the codec type:
   * - (0): The device does not support encoding or decoding.
   * - (1 << 0): The device supports hardware decoding.
   * - (1 << 1): The device supports hardware encoding.
   * - (1 << 2): The device supports software decoding.
   * - (1 << 3): The device supports software ecoding.
   */
  public int codecCapMask;

  /**
   * Codec capability of the SDK. See `CodecCapLevels`.
   */
  public CodecCapLevels codecLevels;

  @CalledByNative
  public CodecCapInfo(int codecType, int codecCapMask, CodecCapLevels levels) {
    this.codecType = codecType;
    this.codecCapMask = codecCapMask;
    this.codecLevels = levels;
  }

  @CalledByNative
  public void SetCodecType(int codecType) {
    this.codecType = codecType;
  }

  @CalledByNative
  public void SetCodecCapMask(int codecCapMask) {
    this.codecCapMask = codecCapMask;
  }
}
