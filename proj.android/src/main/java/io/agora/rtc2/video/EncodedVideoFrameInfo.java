package io.agora.rtc2.video;

import io.agora.base.internal.CalledByNative;
import io.agora.rtc2.Constants;
/**
 * @brief Information about externally encoded video frames.
 */
public class EncodedVideoFrameInfo {
  /**
   * The codec type of the video:
   * - VIDEO_CODEC_VP8 (1): VP8.
   * - VIDEO_CODEC_H264 (2): H.264.
   * - VIDEO_CODEC_H265 (3): (Default) H.265.
   * @note In certain scenarios, such as low resolution of the captured video stream or limited device
   * performance, the SDK automatically adjusts to the H.264 encoding format.
   */
  public int codecType;
  /**
   * Width (pixel) of the video frame.
   */
  public int width;
  /**
   * Height (pixel) of the video frame.
   */
  public int height;
  /**
   * The number of video frames per second.
   * When this parameter is not `0`, you can use it to calculate the Unix timestamp of externally
   * encoded video frames.
   */
  public int framesPerSecond;
  /**
   * The video frame type.
   * - 0: (Default) VIDEO_FRAME_TYPE_BLANK_FRAME, a blank frame.
   * - 3: VIDEO_FRAME_TYPE_KEY_FRAME, a key frame.
   * - 4: VIDEO_FRAME_TYPE_DELTA_FRAME, a Delta frame.
   * - 5: VIDEO_FRAME_TYPE_B_FRAME, a B frame.
   * - 6: VIDEO_FRAME_TYPE_UNKNOW, an unknown frame.
   */
  public int frameType;
  /**
   * Rotation information of the video frame, as the following:
   * - 0: (Default) 0 degree.
   * - 90: 90 degrees.
   * - 180: 180 degrees.
   * - 270: 270 degrees.
   */
  public int rotation;

  // trackId can be reserved for multiple video tracks, we need to create different ssrc
  // and additional payload for later implementation.
  /**
   * Reserved for future use.
   */
  public int trackId;
  /**
   * The Unix timestamp (ms) for capturing the external encoded video frames.
   */
  public long captureTimeMs;
  /**
   * This is a output parameter which means timestamp for decoding the video frame.
   */
  public long decodeTimeMs;
  /**
   * The type of video streams.
   */
  public int streamType;

  public EncodedVideoFrameInfo() {
    codecType = Constants.VIDEO_CODEC_H264;
    width = 0;
    height = 0;
    framesPerSecond = 0;
    frameType = Constants.VIDEO_FRAME_TYPE_BLANK_FRAME;
    rotation = Constants.VIDEO_ORIENTATION_0;
    trackId = 0;
    captureTimeMs = 0;
    decodeTimeMs = 0;
    streamType = Constants.VIDEO_STREAM_HIGH;
  }

  @CalledByNative
  public EncodedVideoFrameInfo(int codecType, int width, int height, int framesPerSecond,
      int frameType, int rotation, int trackId, long captureTimeMs, long decodeTimeMs,
      int streamType) {
    this.codecType = codecType;
    this.width = width;
    this.height = height;
    this.framesPerSecond = framesPerSecond;
    this.frameType = frameType;
    this.rotation = rotation;
    this.trackId = trackId;
    this.captureTimeMs = captureTimeMs;
    this.decodeTimeMs = decodeTimeMs;
    this.streamType = streamType;
  }

  @CalledByNative
  public int getCodecType() {
    return codecType;
  }

  @CalledByNative
  public int getWidth() {
    return width;
  }
  @CalledByNative
  public int getHeight() {
    return height;
  }
  @CalledByNative
  public int getFramesPerSecond() {
    return framesPerSecond;
  }
  @CalledByNative
  public int getFrameType() {
    return frameType;
  }
  @CalledByNative
  public int getRotation() {
    return rotation;
  }
  @CalledByNative
  public int getTrackId() {
    return trackId;
  }
  @CalledByNative
  public long getCaptureTimeMs() {
    return captureTimeMs;
  }
  @CalledByNative
  public long getDecodeTimeMs() {
    return decodeTimeMs;
  }
  @CalledByNative
  public int getStreamType() {
    return streamType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("codecType=").append(codecType);
    sb.append(" width=").append(width);
    sb.append(" height=").append(height);
    sb.append(" framesPerSecond=").append(framesPerSecond);
    sb.append(" frameType=").append(frameType);
    sb.append(" rotation=").append(rotation);
    sb.append(" trackId=").append(trackId);
    sb.append(" captureTimeMs=").append(captureTimeMs);
    sb.append(" decodeTimeMs=").append(decodeTimeMs);
    sb.append(" streamType=").append(streamType);
    return sb.toString();
  }
}
