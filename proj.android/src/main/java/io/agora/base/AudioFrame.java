//
//  Agora RTC/MEDIA SDK
//
//  Copyright (c) 2019 Agora.io. All rights reserved.
//
package io.agora.base;

import java.nio.ByteBuffer;

import io.agora.base.internal.CalledByNative;

/**
 * @brief Raw audio data.
 */
public class AudioFrame {
  /**
   * The data buffer of the audio frame. When the audio frame uses a stereo channel, the data buffer
   * is interleaved.
   * The size of the data buffer is as follows: `buffer` = `samples` × `channels` × `bytesPerSample`.
   */
  public ByteBuffer buffer; // ByteBuffer format audio pcm data
  /**
   * The number of samples per channel in the audio frame.
   */
  public int sampleRataHz; // audio sample rate
  /**
   * The number of bytes per sample. For PCM, this parameter is generally set to 16 bits (2 bytes).
   */
  public int bytesPerSample; // audio data size per sample
  /**
   * The number of audio channels (the data are interleaved if it is stereo).
   * - 1: Mono.
   * - 2: Stereo.
   */
  public int channelNums; // audio channel numbers
  /**
   * The number of samples per channel in the audio frame.
   */
  public int samplesPerChannel; // samples of per audio channel
  /**
   * The timestamp (ms) of the audio frame.
   */
  public long timestamp; // audio frame timestamp

  @CalledByNative
  public AudioFrame(ByteBuffer buffer, int sampleRataHz, int bytesPerSample, int channelNums,
      int samplesPerChannel, long timestamp) {
    this.sampleRataHz = sampleRataHz;
    this.bytesPerSample = bytesPerSample;
    this.channelNums = channelNums;
    this.samplesPerChannel = samplesPerChannel;
    this.timestamp = timestamp;
    this.buffer = buffer;
  }

  @CalledByNative
  public ByteBuffer getByteBuffer() {
    return buffer;
  }

  @CalledByNative
  public int getBytesPerSample() {
    return bytesPerSample;
  }

  @CalledByNative
  public int getChannelNums() {
    return channelNums;
  }

  @CalledByNative
  public int getSampleRataHz() {
    return sampleRataHz;
  }

  @CalledByNative
  public int getSamplesPerChannel() {
    return samplesPerChannel;
  }

  @CalledByNative
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "AudioFrame{sampleRataHz=" + sampleRataHz + ", bytesPerSample=" + bytesPerSample
        + ", channelNums=" + channelNums + ", samplesPerChannel=" + samplesPerChannel
        + ", timestamp=" + timestamp + '}';
  }
}
