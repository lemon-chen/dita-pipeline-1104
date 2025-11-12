package io.agora.rtc2.video;

import io.agora.base.VideoFrame;
import io.agora.base.internal.CalledByNative;

/**
 * @brief The video frame delegate protocol.
 *
 * @details
 * This protocol defines the callbacks for processing video frames. You can implement these methods
 * to receive and process video frames captured by local devices or received from remote users.
 */
public interface IVideoFrameObserver {
  /**
   * Observer works as a pure renderer and will not modify the original frame.
   */
  int PROCESS_MODE_READ_ONLY = 0;

  /**
   * Observer works as a filter that will process the video frame and affect the following frame
   * processing in SDK.
   */
  int PROCESS_MODE_READ_WRITE = 1;

  /**
   * The position after capturing the video data, which corresponds to the onCaptureVideoFrame
   * callback.
   */
  int POSITION_POST_CAPTURER = 1;
  /**
   * The position before receiving the remote video data, which corresponds to the
   * onRenderVideoFrame callback.
   */
  int POSITION_PRE_RENDERER = 1 << 1;
  /**
   * The position before encoding the video data, which corresponds to the onPreEncodeVideoFrame
   * callback.
   */
  int POSITION_PRE_ENCODER = 1 << 2;

  /**
   * 0: Default format.
   */
  int VIDEO_PIXEL_DEFAULT = 0;
  /**
   * 1: I420.
   */
  int VIDEO_PIXEL_I420 = 1;
  /**
   * 2: BGRA.
   */
  int VIDEO_PIXEL_BGRA = 2;
  /**
   * 3: NV21.
   */
  int VIDEO_PIXEL_NV21 = 3;
  /**
   * 4: RGBA.
   */
  int VIDEO_PIXEL_RGBA = 4;
  /**
   * 8: NV12.
   */
  int VIDEO_PIXEL_NV12 = 8;
  /**
   * 10: GL_TEXTURE_2D
   */
  int VIDEO_TEXTURE_2D = 10;
  /**
   * 11: GL_TEXTURE_OES
   */
  int VIDEO_TEXTURE_OES = 11;
  /**
   * 16: I422.
   */
  int VIDEO_PIXEL_I422 = 16;
  /**
   * 18: I010. 10bit I420 data.
   * @technical preview
   */
  int VIDEO_PIXEL_I010 = 18;
  /**
   * @brief Occurs each time the SDK receives a video frame captured by local devices.
   *
   * @details
   * You can get raw video data collected by the local device through this callback and preprocess it
   * as needed. Once the preprocessing is complete, you can directly modify `videoFrame` in this
   * callback, and set the return value to `true` to send the modified video data to the SDK.
   * If you need to send the preprocessed data to the SDK, you need to call `getVideoFrameProcessMode`
   * first to set the video processing mode to read and write mode ( PROCESS_MODE_READ_WRITE ).
   * Applicable scenarios: - Preprocess the locally collected video data before it is processed by the
   * SDK. For example, get video data through this callback and process it with filters, watermarks,
   * cropping, rotation, etc.
   * - Get information about the locally collected video data before it is processed by the SDK. For
   * example, the original width, height, frame rate of the video frame, etc.
   * Call timing: After the successful registration of the video data observer, each time the SDK
   * captures a video frame.
   *
   * @note
   * - If the video data type you get is RGBA, the SDK does not support processing the data of the
   * alpha channel.
   * - It is recommended that you ensure the modified parameters in `videoFrame` are consistent with
   * the actual situation of the video frames in the video frame buffer. Otherwise, it may cause
   * unexpected rotation, distortion, and other issues in the local preview and remote video display.
   * The default video format that you get from this callback may be I420Buffer or TextureBuffer. The
   * texture format of TextureBuffer can be either 0ES format or RGB format. If you need video data in
   * other formats, you can set the expected data format in the return value of the
   * `getVideoFormatPreference` callback.
   *
   * @param sourceType Video source types, including cameras, screens, or media player. See
   * `VideoSourceType`.
   * @param videoFrame The video frame. See `VideoFrame`.Note: The default value of the video frame
   * data format obtained through this callback is as follows:
   * - Android: I420 or RGB (GLES20.GL_TEXTURE_2D)
   *
   * @return
   * - When the video processing mode is `PROCESS_MODE_READ_ONLY`:
   *   - `true`: Reserved for future use.
   *   - `false`: Reserved for future use.
   * - When the video processing mode is `PROCESS_MODE_READ_WRITE`:
   *   - `true`: Sets the SDK to receive the video frame.
   *   - `false`: Sets the SDK to discard the video frame.
   */
  @CalledByNative boolean onCaptureVideoFrame(int sourceType, VideoFrame videoFrame);

  /**
   * @brief Occurs each time the SDK receives a video frame before encoding.
   *
   * @details
   * After you successfully register the video frame observer, the SDK triggers this callback each
   * time it receives a video frame. In this callback, you can get the video data before encoding and
   * then process the data according to your particular scenarios.
   * After processing, you can send the processed video data back to the SDK in this callback.
   *
   * @note
   * - If you need to send the preprocessed data to the SDK, you need to call
   * `getVideoFrameProcessMode` first to set the video processing mode to read and write mode (
   * PROCESS_MODE_READ_WRITE ).
   * - To get the video data captured from the second screen before encoding, you need to set
   * `VIDEO_MODULE_POSITION_PRE_ENCODER` (1 << 2) as a frame position through
   * `getObservedFramePosition`.
   * - The video data that this callback gets has been preprocessed, with its content cropped and
   * rotated, and the image enhanced.
   * - It is recommended that you ensure the modified parameters in `videoFrame` are consistent with
   * the actual situation of the video frames in the video frame buffer. Otherwise, it may cause
   * unexpected rotation, distortion, and other issues in the local preview and remote video display.
   *
   * @param sourceType The type of the video source. See `VideoSourceType`.
   * @param videoFrame The video frame. See `VideoFrame`.Note: The default value of the video frame
   * data format obtained through this callback is as follows:
   * - Android: I420 or RGB (GLES20.GL_TEXTURE_2D)
   *
   * @return
   * - When the video processing mode is `PROCESS_MODE_READ_ONLY`:
   *   - `true`: Reserved for future use.
   *   - `false`: Reserved for future use.
   * - When the video processing mode is `PROCESS_MODE_READ_WRITE`:
   *   - `true`: Sets the SDK to receive the video frame.
   *   - `false`: Sets the SDK to discard the video frame.
   */
  @CalledByNative boolean onPreEncodeVideoFrame(int sourceType, VideoFrame videoFrame);

  /**
   * Occurs each time the SDK receives a video frame decoded by the MediaPlayer.
   *
   * After you successfully register the video frame observer, the SDK triggers this callback each
   * time a video frame is decoded. In this callback, you can get the video data decoded by the
   * MediaPlayer. You can then pre-process the data according to your scenarios.
   *
   * After pre-processing, you can send the processed video data back to the SDK by setting the
   * `videoFrame` parameter in this callback.
   *
   * @note
   * - This callback will not be affected by the return values of \ref getVideoFrameProcessMode
   * "getVideoFrameProcessMode", \ref getRotationApplied "getRotationApplied", \ref getMirrorApplied
   * "getMirrorApplied", \ref getObservedFramePosition "getObservedFramePosition".
   * - On Android, this callback is not affected by the return value of \ref
   * getVideoFormatPreference "getVideoFormatPreference"
   *
   * @param videoFrame A pointer to the video frame: VideoFrame
   * @param mediaPlayerId of the mediaPlayer.
   * @return Determines whether to ignore the current video frame if the pre-processing fails:
   * - true: Do not ignore.
   * - false: Ignore, in which case this method does not sent the current video frame to the SDK.
   */
  @CalledByNative boolean onMediaPlayerVideoFrame(VideoFrame videoFrame, int mediaPlayerId);

  /**
   * @brief Occurs each time the SDK receives a video frame and prompts you to set the process mode of
   * the video frame.
   *
   * @details
   * After you successfully register the video frame observer, the SDK triggers this callback each
   * time it receives a video frame. You need to set your preferred process mode in the return value
   * of this callback.
   *
   * @return
   * The process mode of the video frame:
   * - PROCESS_MODE_READ_ONLY (0): (Default) Only reads the video frame.
   * - PROCESS_MODE_READ_WRITE (1): Reads and writes the video frame.
   */
  @CalledByNative int getVideoFrameProcessMode();

  /**
   * @brief Sets the format of the raw video data output by the SDK.
   *
   * @details
   * You need to register the callback when calling the `registerVideoFrameObserver` method. After you
   * successfully register the video frame observer, the SDK triggers this callback each time it
   * receives a video frame. You need to set your preferred video data in the return value of this
   * callback.
   *
   * @note
   * The default types of pixel format ( VIDEO_PIXEL_DEFAULT ) for the raw video are as follows:
   * - On the Android platform, the default video frame type may be I420Buffer or TextureBuffer. The
   * texture format of TextureBuffer type may be OES or RGB. If the returned video frame type is
   * VIDEO_PIXEL_DEFAULT when you call `getVideoFormatPreference`, you need to adapt to I420Buffer or
   * TextureBuffer when processing video data. The cases where the video frame type is fixed as
   * I420Buffer include but are not limited to:
   *   - Specific devices, such as: LG G5 SE (H848), Google Pixel 4a, Samsung Galaxy A7, or Xiaomi Mi
   * Max.
   *   - Image enhancement extension has been integrated and video noise reduction or low-light
   * enhancement function has been enabled.
   *
   * @return
   * Sets the raw data format of the SDK output.
   * - VIDEO_PIXEL_DEFAULT (0): Raw video.
   * - VIDEO_PIXEL_I420 (1): I420.
   * - VIDEO_PIXEL_RGBA (4): RGBA.
   * - VIDEO_PIXEL_I422 (16): I422.
   */
  @CalledByNative int getVideoFormatPreference();

  /**
   * @brief Occurs each time the SDK receives a video frame, and prompts you whether to rotate the
   * captured video.
   *
   * @details
   * If you want to rotate the captured video according to the `rotation` member in the `VideoFrame`
   * class, ensure that you register this callback when calling `registerVideoFrameObserver`. After
   * you successfully register the video frame observer, the SDK triggers this callback each time it
   * receives a video frame. You need to set whether to rotate the video frame in the return value of
   * this callback.
   *
   * @note On the Android platform, the supported video data formats for this callback are: I420,
   * RGBA, and Texture.
   *
   * @return
   * Sets whether to rotate the captured video:
   * - `true`: Rotate the captured video.
   * - `false`: (Default) Do not rotate the captured video.
   */
  @CalledByNative boolean getRotationApplied();

  /**
   * @brief Occurs each time the SDK receives a video frame and prompts you whether or not to mirror
   * the captured video.
   *
   * @details
   * If the video data you want to obtain is a mirror image of the original video, you need to
   * register this callback when calling `registerVideoFrameObserver`. After you successfully register
   * the video frame observer, the SDK triggers this callback each time it receives a video frame. You
   * need to set whether or not to mirror the video frame in the return value of this callback.
   *
   * @note
   * - On the Android platform, the supported video data formats for this callback are: I420, RGBA,
   * and Texture.
   * - Both this method and the `setVideoEncoderConfiguration` method support setting the mirroring
   * effect. Agora recommends that you only choose one method to set it up. Using both methods at the
   * same time causes the mirroring effect to overlap, and the mirroring settings fail.
   *
   * @return
   * Sets whether or not to mirror the captured video:
   * - `true`: Mirror the captured video.
   * - `false`: (Default) Do not mirror the captured video.
   */
  @CalledByNative boolean getMirrorApplied();

  /**
   * @brief Sets the frame position for the video observer.
   *
   * @details
   * After successfully registering the video data observer, the SDK uses this callback to determine
   * whether to trigger `onCaptureVideoFrame`, `onRenderVideoFrame` and `onPreEncodeVideoFrame`
   * callback at each specific video frame processing position, so that you can observe the locally
   * collected video data, the video data sent by the remote end, and the video data before encoding.
   * You can set one or more positions you need to observe by modifying the return value according to
   * your scenario:
   * - `VIDEO_MODULE_POSITION_POST_CAPTURER` (1 << 0): The position after capturing the video data,
   * which corresponds to the `onCaptureVideoFrame` callback.
   * - `VIDEO_MODULE_POSITION_PRE_RENDERER` (1 << 1): The position of the received remote video data
   * before rendering, which corresponds to the `onRenderVideoFrame` callback.
   * - `VIDEO_MODULE_POSITION_PRE_ENCODER` (1 << 2): The position before encoding the video data,
   * which corresponds to the `onPreEncodeVideoFrame` callback.
   *
   * @note
   * - Use '|' (the OR operator) to observe multiple frame positions.
   * - This callback observes `VIDEO_MODULE_POSITION_POST_CAPTURER` (1 << 0) and
   * `VIDEO_MODULE_POSITION_PRE_RENDERER` (1 << 1) by default.
   * - To conserve system resources, you can reduce the number of frame positions that you want to
   * observe.
   * - When the video processing mode is `PROCESS_MODE_READ_WRITE` and the observation position is set
   * to `VIDEO_MODULE_POSITION_PRE_ENCODER` | `VIDEO_MODULE_POSITION_POST_CAPTURER`, the
   * `getMirrorApplied` does not take effect; you need to modify the video processing mode or the
   * position of the observer.
   *
   * @return
   * A bit mask that controls the frame position of the video observer.
   * - `VIDEO_MODULE_POSITION_POST_CAPTURER` (1 << 0): The position after capturing the video data,
   * which corresponds to the `onCaptureVideoFrame` callback.
   * - `VIDEO_MODULE_POSITION_PRE_RENDERER` (1 << 1): The position of the received remote video data
   * before rendering, which corresponds to the `onRenderVideoFrame` callback.
   * - `VIDEO_MODULE_POSITION_PRE_ENCODER` (1 << 2): The position before encoding the video data,
   * which corresponds to the `onPreEncodeVideoFrame` callback.
   */
  @CalledByNative int getObservedFramePosition();

  /**
   * @brief Occurs each time the SDK receives a video frame sent by the remote user.
   *
   * @details
   * After you successfully register the video frame observer, the SDK triggers this callback each
   * time it receives a video frame. In this callback, you can get the video data sent from the remote
   * end before rendering, and then process it according to the particular scenarios.
   * The default video format that you get from this callback may be I420Buffer or TextureBuffer. The
   * texture format of TextureBuffer can be either 0ES format or RGB format. If you need video data in
   * other formats, you can set the expected data format in the return value of the
   * `getVideoFormatPreference` callback.
   *
   * @note
   * - If you need to send the preprocessed data to the SDK, you need to call
   * `getVideoFrameProcessMode` first to set the video processing mode to read and write mode (
   * PROCESS_MODE_READ_WRITE ).
   * - If the video data type you get is RGBA, the SDK does not support processing the data of the
   * alpha channel.
   * - It is recommended that you ensure the modified parameters in `videoFrame` are consistent with
   * the actual situation of the video frames in the video frame buffer. Otherwise, it may cause
   * unexpected rotation, distortion, and other issues in the local preview and remote video display.
   *
   * @param uid The user ID of the remote user who sends the current video frame.
   * @param videoFrame The video frame. See `VideoFrame`.Note: The default value of the video frame
   * data format obtained through this callback is as follows:
   * - Android: I420 or RGB (GLES20.GL_TEXTURE_2D)
   * @param channelId The channel ID.
   *
   * @return
   * - When the video processing mode is `PROCESS_MODE_READ_ONLY`:
   *   - `true`: Reserved for future use.
   *   - `false`: Reserved for future use.
   * - When the video processing mode is `PROCESS_MODE_READ_WRITE`:
   *   - `true`: Sets the SDK to receive the video frame.
   *   - `false`: Sets the SDK to discard the video frame.
   */
  @CalledByNative boolean onRenderVideoFrame(String channelId, int uid, VideoFrame videoFrame);
}
