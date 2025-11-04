package io.agora.rtc2;

import io.agora.base.internal.CalledByNative;

/**
 * The channel media options.
 */
public class EncodedVideoTrackOptions {
  /**
   * Whether to enable CC mode.
   * - TCC_ENABLED = 0: (Default) enable cc.
   * - TCC_DISABLED = 1: disable cc.
   */
  public int ccMode;
  /**
   * The codec type used for the encoded images.
   * - VIDEO_CODEC_VP8 = 1: VP8.
   * - VIDEO_CODEC_H264 = 2: H.264.
   * - VIDEO_CODEC_H265 = 3: (Default)H.265.
   * - VIDEO_CODEC_VP9 = 13: VP9.
   * - VIDEO_CODEC_GENERIC = 6: GENERIC.
   * - VIDEO_CODEC_GENERIC_H264 = 7: GENERIC_H264.
   * - VIDEO_CODEC_GENERIC_JPEG = 20: GENERIC_JPEG.
   */
  public int codecType;
  /**
   * Target bitrate (Kbps) for sending encoded video frame.
   */
  public int targetBitrate;

  public EncodedVideoTrackOptions() {
    ccMode = Constants.TCC_ENABLED;
    codecType = Constants.VIDEO_CODEC_H265;
    targetBitrate = 6500;
  }

  @CalledByNative
  public int getCcMode() {
    return ccMode;
  }

  @CalledByNative
  public int getCodecType() {
    return codecType;
  }

  @CalledByNative
  public int getTargetBitrate() {
    return targetBitrate;
  }
}
