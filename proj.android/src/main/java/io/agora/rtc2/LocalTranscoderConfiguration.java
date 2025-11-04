package io.agora.rtc2;

import io.agora.rtc2.video.VideoEncoderConfiguration;
import java.util.ArrayList;
import io.agora.rtc2.Constants;

/**
 * @brief The configuration of the video mixing on the local client.
 */
public class LocalTranscoderConfiguration {
  /**
   * The video streams for local video mixing. See `TranscodingVideoStream`.
   */
  public ArrayList<TranscodingVideoStream> transcodingVideoStreams;

  /**
   * Whether to use the timestamp when the primary camera captures the video frame as the timestamp
   * of the mixed video frame.
   * - true: (Default) Use the timestamp of the captured video frame as the timestamp of the mixed
   * video frame.
   * - false: Do not use the timestamp of the captured video frame as the timestamp of the mixed
   * video frame. Instead, use the timestamp when the mixed video frame is constructed.
   */
  public boolean syncWithPrimaryCamera;
  /**
   * The encoding configuration of the mixed video stream after the local video mixing. See
   * `VideoEncoderConfiguration`.
   */
  public VideoEncoderConfiguration videoOutputConfiguration;

  public LocalTranscoderConfiguration() {
    super();
    transcodingVideoStreams = new ArrayList<TranscodingVideoStream>();
    syncWithPrimaryCamera = true;
    videoOutputConfiguration = new VideoEncoderConfiguration();
  }

  /**
   * @brief The video streams for local video mixing.
   */
  public static class TranscodingVideoStream {
    /**
     * The user ID of the remote user.
     * @note Use this parameter only when the source type is `VIDEO_SOURCE_REMOTE` for local video
     * mixing.
     */
    public int remoteUserUid;

    /**
     * The video source type for local video mixing. See `VideoSourceType`.
     */
    public Constants.VideoSourceType sourceType;
    /**
     * The file path of local images.
     * Examples:
     * - Android: `/storage/emulated/0/Pictures/image.png`
     * @note Use this parameter only when the source type is the image for local video mixing.
     */
    public String imageUrl;
    /**
     * (Optional) Media player ID. Use the parameter only when you set `sourceType` to
     * `VIDEO_SOURCE_MEDIA_PLAYER`.
     */
    public int mediaPlayerId;
    /**
     * The relative lateral displacement of the top left corner of the video for local video mixing to
     * the origin (the top left corner of the canvas).
     */
    public int x;
    /**
     * The relative longitudinal displacement of the top left corner of the captured video to the origin
     * (the top left corner of the canvas).
     */
    public int y;
    /**
     * The width (px) of the video for local video mixing on the canvas.
     */
    public int width;
    /**
     * The height (px) of the video for local video mixing on the canvas.
     */
    public int height;
    /**
     * The number of the layer to which the video for the local video mixing belongs. The value range is
     * [0, 100].
     * - 0: (Default) The layer is at the bottom.
     * - 100: The layer is at the top.
     */
    public int zOrder;
    /**
     * The transparency of the video for local video mixing. The value range is [0.0, 1.0]. 0.0
     * indicates that the video is completely transparent, and 1.0 indicates that it is opaque.
     */
    public double alpha;
    /**
     * Whether to mirror the video for the local video mixing.
     * - `true`: Mirror the video for the local video mixing.
     * - `false`: (Default) Do not mirror the video for the local video mixing.
     * @note This parameter only takes effect on video source types that are cameras.
     */
    public boolean mirror;

    public TranscodingVideoStream() {
      this.remoteUserUid = 0;
      this.sourceType = Constants.VideoSourceType.VIDEO_SOURCE_CAMERA_PRIMARY;
      this.imageUrl = null;
      this.x = 0;
      this.y = 0;
      this.width = 0;
      this.height = 0;
      this.zOrder = 0;
      this.alpha = 1.0;
      this.mirror = false;
    }
  };
}
