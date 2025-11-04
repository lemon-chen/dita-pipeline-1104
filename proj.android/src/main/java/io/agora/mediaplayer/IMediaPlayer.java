//
//  Agora RTC/MEDIA SDK
//
//  Created by Wei Wu in 2020-12.
//  Copyright (c) 2020 Agora.io. All rights reserved.
//

package io.agora.mediaplayer;

import android.net.Uri;
import android.view.View;
import io.agora.mediaplayer.data.MediaPlayerSource;
import io.agora.mediaplayer.data.MediaStreamInfo;
import io.agora.rtc2.SpatialAudioParams;
import io.agora.rtc2.audio.IAudioSpectrumObserver;

public interface IMediaPlayer {
  /**
   * @brief Gets the ID of the media player.
   *
   * @return
   * - Success. The ID of the media player.
   * - < 0: Failure.
   */
  int getMediaPlayerId();

  /**
   * @brief Opens a media file through a URI address.
   *
   * @note This method is called asynchronously.
   *
   * @param uri The URI (Uniform Resource Identifier) of the media file.
   * @param startPos The starting position (ms) for playback. The default value is 0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int open(Uri uri, long startPos);

  /**
   * @brief Opens the media resource.
   *
   * @details
   * This method supports playing URI files starting with `content://`.
   * Call timing: This method can be called either before or after joining the channel.
   * Related callbacks: After calling this method, the SDK triggers the `onPlayerStateChanged`
   * callback. After receiving the report of the playback status as `PLAYER_STATE_OPEN_COMPLETED`, you
   * can call the `play` method to play the media file.
   *
   * @note This method is called asynchronously.
   *
   * @param url The path of the media file. Both local path and online path are supported.
   * @param startPos The starting position (ms) for playback. Default value is 0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int open(String url, long startPos);

  /**
   * @brief Opens a media file and configures the playback scenarios.
   *
   * @details
   * This method supports opening different types of media files, including URI files starting with
   * `content://`, media files in the `assets` folder, and setting the playback options.
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @note This method is called asynchronously. If you need to play a media file, make sure you
   * receive the `onPlayerStateChanged` callback reporting `PLAYER_STATE_OPEN_COMPLETED` before
   * calling the `play` method to play the file.
   *
   * @param source Media resources. See `MediaPlayerSource`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int openWithMediaSource(MediaPlayerSource source);

  /**
   * @brief Plays the media file.
   *
   * @details
   * Call timing: - Call this method after calling `open(String url, long startPos)` or `openWithMediaSource` opening a
   * media file and receiving a `onPlayerStateChanged` callback reporting the status as
   * PLAYER_STATE_OPEN_COMPLETED.
   * - Call the method after calling `seek`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int play();

  /**
   * @brief Pauses the playback.
   *
   * @details
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int pause();

  /**
   * @brief Stops playing the media track.
   *
   * @details
   * After calling this method to stop playback, if you want to play again, you need to call `open(String url, long startPos)`
   * or `openWithMediaSource` to open the media resource.
   * Call timing: Call this method after play.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int stop();

  /**
   * @brief Resumes playing the media file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int resume();

  /**
   * @brief Seeks to a new playback position.
   *
   * @details
   * - If you call `seek` after the playback has completed (upon receiving callback
   * `onPlayerStateChanged` reporting playback status as PLAYER_STATE_PLAYBACK_COMPLETED or
   * PLAYER_STATE_PLAYBACK_ALL_LOOPS_COMPLETED ), the SDK will play the media file from the specified
   * position. At this point, you will receive callback `onPlayerStateChanged` reporting playback
   * status as PLAYER_STATE_PLAYING.
   * - If you call `seek` while the playback is paused, upon successful call of this method, the SDK
   * will seek to the specified position. To resume playback, call `resume` or `play` .
   * Call timing: You can call this method either before or after joining a channel.
   * Related callbacks: After successfully calling this method, you will receive the `onPlayerEvent`
   * callback, reporting the result of the seek operation to the new playback position.
   *
   * @param newPos The new playback position (ms).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int seek(long newPos);

  /**
   * @brief Sets the pitch of the current media resource.
   *
   * @note Call this method after calling `open(String url, long startPos)`.
   *
   * @param pitch Sets the pitch of the local music file by the chromatic scale. The default value is
   * 0, which means keeping the original pitch. The value ranges from -12 to 12, and the pitch value
   * between consecutive values is a chromatic value. The greater the absolute value of this
   * parameter, the higher or lower the pitch of the local music file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setAudioPitch(int pitch);

  /**
   * @brief Sets whether to mute the media file.
   *
   * @details
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param muted Whether to mute the media file:
   * - `true`: Mute the media file.
   * - `false`: (Default) Unmute the media file.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int mute(boolean muted);

  /**
   * @brief Reports whether the media resource is muted.
   *
   * @return
   * - `true`: Reports whether the media resource is muted.
   * - `false`: Reports whether the media resource is muted.
   */
  boolean getMute();

  /**
   * @brief Adjusts the local playback volume.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param volume The local playback volume, which ranges from 0 to 100:
   * - 0: Mute.
   * - 100: (Default) The original volume.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int adjustPlayoutVolume(int volume);

  /**
   * @brief Gets the local playback volume.
   *
   * @return
   * The local playback volume, which ranges from 0 to 100.
   * - 0: Mute.
   * - 100: (Default) The original volume.
   */
  int getPlayoutVolume();

  /**
   * @brief Gets current local playback progress.
   *
   * @return
   * - Returns the current playback progress (ms) if the call succeeds.
   * - < 0: Failure. See `MediaPlayerReason`.
   */
  long getPlayPosition();

  /**
   * @brief Gets the duration of the media resource.
   *
   * @return
   * - If the method call succeeds, the SDK returns the total duration(ms) of the media file.
   * - < 0: Failure.
   */
  long getDuration();

  /**
   * @brief Gets current playback state.
   *
   * @return
   * The current playback state. See `MediaPlayerState`.
   */
  Constants.MediaPlayerState getState();

  /**
   * @brief Gets the number of the media streams in the media resource.
   *
   * @note Call this method after you call `open(String url, long startPos)` and receive the `onPlayerStateChanged`
   * callback reporting the state `PLAYER_STATE_OPEN_COMPLETED`.
   *
   * @return
   * - The number of the media streams in the media resource if the method call succeeds.
   * - < 0: Failure. See `MediaPlayerReason`.
   */
  int getStreamCount();

  /**
   * @brief Sets the view.
   *
   * @details
   * Call timing: You can call this method either before or after joining a channel.
   *
   * @param videoView The render view.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setView(View videoView);

  /**
   * @brief Sets the render mode of the media player.
   *
   * @param mode Sets the render mode of the view:
   * - RENDER_MODE_HIDDEN (1): Hidden mode. Uniformly scale the video until it fills the visible
   * boundaries (cropped). One dimension of the video may have clipped contents.
   * - RENDER_MODE_FIT (2): Fit mode. Uniformly scale the video until one of its dimension fits the
   * boundary (zoomed to fit). Areas that are not filled due to the disparity in the aspect ratio are
   * filled with black.
   * - RENDER_MODE_ADAPTIVE (3): Adaptive mode.
   * Deprecated:
   * This enumerator is deprecated and not recommended for use.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setRenderMode(int mode);

  /**
   * @brief Gets the detailed information of the media stream.
   *
   * @details
   * Call timing: Call this method after calling `getStreamCount`.
   *
   * @param index The index of the media stream. This parameter must be less than the return value of
   * `getStreamCount`.
   *
   * @return
   * - If the call succeeds, returns the detailed information of the media stream. See
   * `MediaStreamInfo`.
   * - `NULL` is returned, if the method call fails.
   */
  MediaStreamInfo getStreamInfo(int index);

  /**
   * @brief Sets the loop playback.
   *
   * @details
   * If you want to loop, call this method and set the number of the loops.
   * When the loop finishes, the SDK triggers `onPlayerStateChanged` and reports the playback state as
   * PLAYER_STATE_PLAYBACK_ALL_LOOPS_COMPLETED.
   *
   * @param loopCount The number of times the audio effect loops:
   * - ≥ 0: Number of times for playing. For example, setting it to 0 means no loop playback, playing
   * only once; setting it to 1 means loop playback once, playing a total of twice.
   * - -1: Play the audio file in an infinite loop.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setLoopCount(int loopCount);

  /**
   * @brief Sets the channel mode of the current audio file.
   *
   * @details
   * Call this method after calling `open(String url, long startPos)`.
   *
   * @param speed The playback speed. Agora recommends that you set this to a value between 30 and
   * 400, defined as follows:
   * - 30: 0.3 times the original speed.
   * - 100: The original speed.
   * - 400: 4 times the original speed.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setPlaybackSpeed(int speed);

  /**
   * @brief Selects the audio track used during playback.
   *
   * @details
   * After getting the track index of the audio file, you can call this method to specify any track to
   * play. For example, if different tracks of a multi-track file store songs in different languages,
   * you can call this method to set the playback language.
   *
   * @note You need to call this method after calling `getStreamInfo` to get the audio stream index
   * value.
   *
   * @param index The index of the audio track.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int selectAudioTrack(int index);

  /**
   * @brief Selects the audio tracks that you want to play on your local device and publish to the
   * channel respectively.
   *
   * @details
   * You can call this method to determine the audio track to be played on your local device and
   * published to the channel.
   * Before calling this method, you need to open the media file with the `openWithMediaSource` method
   * and set `enableMultiAudioTrack` in `MediaPlayerSource` as `true`.
   * Applicable scenarios: For example, in KTV scenarios, the host can choose to play the original
   * sound locally and publish the accompaniment track to the channel.
   *
   * @param playoutTrackIndex The index of audio tracks for local playback. You can obtain the index
   * through `getStreamInfo`.
   * @param publishTrackIndex The index of audio tracks to be published in the channel. You can obtain
   * the index through `getStreamInfo`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int selectMultiAudioTrack(int playoutTrackIndex, int publishTrackIndex);

  /**
   * @brief Sets media player options.
   *
   * @details
   * The media player supports setting options through `key` and `value`.
   * The difference between this method and `setPlayerOptionString` is that the `value` parameter of
   * this method is of type Int, while the `value` of `setPlayerOptionString` is of type String. These
   * two methods cannot be used together.
   * Applicable scenarios: Scenarios that require technical previews or special customization
   * features. In general, you do not need to call this method; you can simply use the default options
   * provided by the media player.
   * Call timing: Call this method before the `open(String url, long startPos)` or `openWithMediaSource` method.
   *
   * @param key The key of the option.
   * @param value The value of the key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setPlayerOption(String key, int value);

  /**
   * @brief Sets media player options.
   *
   * @details
   * The media player supports setting options through `key` and `value`.
   * The difference between this method and `setPlayerOption` is that the `value` parameter of this
   * method is of type String, while the `value` of `setPlayerOption` is of type String. These two
   * methods cannot be used together.
   * Applicable scenarios: Scenarios that require technical previews or special customization
   * features. In general, you do not need to call this method; you can simply use the default options
   * provided by the media player.
   * Call timing: Call this method before the `open(String url, long startPos)` or `openWithMediaSource` method.
   *
   * @param key The key of the option.
   * @param value The value of the key.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setPlayerOptionString(String key, String value);

  /**
   * take screenshot while playing  video
   * @param filename the filename of screenshot file
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int takeScreenshot(String filename);

  /**
   * select internal subtitles in video
   * @param index the index of the internal subtitles
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int selectInternalSubtitle(int index);

  /**
   * set an external subtitle for video
   * @param url The URL of the subtitle file that you want to load.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setExternalSubtitle(String url);

  /**
   * @brief Adjusts the volume of the media file for publishing.
   *
   * @details
   * After connected to the Agora server, you can call this method to adjust the volume of the media
   * file heard by the remote user.
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param volume The volume, which ranges from 0 to 400:
   * - 0: Mute.
   * - 100: (Default) The original volume.
   * - 400: Four times the original volume (amplifying the audio signals by four times).
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int adjustPublishSignalVolume(int volume);

  /**
   * @brief Gets the volume of the media file for publishing.
   *
   * @return
   * - ≥ 0: The remote playback volume.
   * - < 0: Failure.
   */
  int getPublishSignalVolume();

  /**
   * @brief Gets the path of the media resource being played.
   *
   * @return
   * The path of the media resource being played.
   */
  String getPlaySrc();

  /**
   * @brief Switches the media resource being played.
   *
   * @details
   * You can call this method to switch the media resource to be played according to the current
   * network status. For example:
   * - When the network is poor, the media resource to be played is switched to a media resource
   * address with a lower bitrate.
   * - When the network is good, the media resource to be played is switched to a media resource
   * address with a higher bitrate.
   * After calling this method, if you receive the `onPlayerEvent` callback report the
   * `PLAYER_EVENT_SWITCH_COMPLETE` event, the switching is successful. If the switching fails, the
   * SDK will automatically retry 3 times. If it still fails, you will receive the `onPlayerEvent`
   * callback reporting the `PLAYER_EVENT_SWITCH_ERROR` event indicating an error occurred during
   * media resource switching.
   *
   * @note
   * - Ensure that you call this method after `open(String url, long startPos)`.
   * - To ensure normal playback, pay attention to the following when calling this method:
   *   - Do not call this method when playback is paused.
   *   - Do not call the `seek` method during switching.
   *   - Before switching the media resource, make sure that the playback position does not exceed the
   * total duration of the media resource to be switched.
   *
   * @param src The URL of the media resource.
   * @param syncPts Whether to synchronize the playback position (ms) before and after the switch:
   * - `true`: Synchronize the playback position before and after the switch.
   * - `false`: (Default) Do not synchronize the playback position before and after the switch.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int switchSrc(String src, boolean syncPts);

  /**
   * @brief Preloads a media resource.
   *
   * @details
   * You can call this method to preload a media resource into the playlist. If you need to preload
   * multiple media resources, you can call this method multiple times.
   * After calling this method, if you receive the `PLAYER_PRELOAD_EVENT_COMPLETE` event in the
   * `onPreloadEvent` callback, the preload is successful; If you receive the
   * `PLAYER_PRELOAD_EVENT_ERROR` event in the `onPreloadEvent` callback, the preload fails.
   * If the preload is successful and you want to play the media resource, call `playPreloadedSrc`; if
   * you want to clear the playlist, call `stop()`.
   *
   * @note
   * - Before calling this method, ensure that you have called `open(String url, long startPos)` or `openWithMediaSource`
   * to open the media resource successfully.
   * - Agora does not support preloading duplicate media resources to the playlist. However, you can
   * preload the media resources that are being played to the playlist again.
   *
   * @param src The URL of the media resource.
   * @param startPos The starting position (ms) for playing after the media resource is preloaded to
   * the playlist. When preloading a live stream, set this parameter to 0.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int preloadSrc(String src, long startPos);

  /**
   * @brief Plays preloaded media resources.
   *
   * @details
   * After calling the `preloadSrc` method to preload the media resource into the playlist, you can
   * call this method to play the preloaded media resource. After calling this method, if you receive
   * the `onPlayerStateChanged` callback which reports the `PLAYER_STATE_PLAYING` state, the playback
   * is successful.
   * If you want to change the preloaded media resource to be played, you can call this method again
   * and specify the URL of the new media resource that you want to preload. If you want to replay the
   * media resource, you need to call `preloadSrc` to preload the media resource to the playlist again
   * before playing. If you want to clear the playlist, call the `stop()` method.
   *
   * @note If you call this method when playback is paused, this method does not take effect until
   * playback is resumed.
   *
   * @param src The URL of the media resource in the playlist must be consistent with the `src` set by
   * the `preloadSrc` method; otherwise, the media resource cannot be played.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int playPreloadedSrc(String src);

  /**
   * @brief Unloads media resources that are preloaded.
   *
   * @note This method cannot release the media resource being played.
   *
   * @param src The URL of the media resource.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int unloadSrc(String src);

  /**
   * @brief Destroys the media player instance.
   *
   * @return
   * - ≥ 0: Success. Returns the ID of media player instance.
   * - < 0: Failure.
   */
  int destroy();

  /**
   * @brief Registers a media player observer.
   *
   * @details
   * Call timing: This method can be called either before or after joining the channel.
   *
   * @param playerObserver The player observer, listening for events during the playback. See
   * `IMediaPlayerObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int registerPlayerObserver(IMediaPlayerObserver playerObserver);

  /**
   * @brief Releases a media player observer.
   *
   * @param playerObserver The player observer, listening for events during the playback. See
   * `IMediaPlayerObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int unRegisterPlayerObserver(IMediaPlayerObserver playerObserver);

  /**
   * @brief Registers an audio frame observer object.
   *
   * @param audioFrameObserver The audio frame observer, reporting the reception of each audio frame.
   * See `IMediaPlayerAudioFrameObserver`.
   * @param mode The use mode of the audio frame:
   * - RAW_AUDIO_FRAME_OP_MODE_READ_ONLY (0): (Default) Read only mode. For example, when users
   * acquire the data with the Agora SDK, then push the RTMP or RTMPS streams.
   * - RAW_AUDIO_FRAME_OP_MODE_READ_WRITE (2): Read and write mode: Users read the data from
   * AudioFrame, modify it, and then play it. For example, when users have their own audio-effect
   * processing module and perform some voice pre-processing, such as a voice change.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int registerAudioFrameObserver(IMediaPlayerAudioFrameObserver audioFrameObserver, int mode);

  /**
   * Registers the audio frame spectrum observer.
   *
   * @param observer The pointer to the {@link media::base::IAudioSpectrumObserver
   *     IAudioSpectrumObserver} object.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int registerMediaPlayerAudioSpectrumObserver(IAudioSpectrumObserver observer, int intervalInMS);

  /**
   * Releases the audio frame spectrum observer.
   * @param observer The pointer to the {@link media::base::IAudioSpectrumObserver
   *     IAudioSpectrumObserver} object.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int unregisterMediaPlayerAudioSpectrumObserver(IAudioSpectrumObserver observer);

  /**
   * @brief Sets the channel mode of the current audio file.
   *
   * @details
   * In a stereo music file, the left and right channels can store different audio data. According to
   * your needs, you can set the channel mode to original mode, left channel mode, right channel mode,
   * or mixed channel mode. For example, in the KTV scenario, the left channel of the music file
   * stores the musical accompaniment, and the right channel stores the singing voice. If you only
   * need to listen to the accompaniment, call this method to set the channel mode of the music file
   * to left channel mode; if you need to listen to the accompaniment and the singing voice at the
   * same time, call this method to set the channel mode to mixed channel mode.
   *
   * @note
   * - Call this method after calling `open(String url, long startPos)`.
   * - This method only applies to stereo audio files.
   *
   * @param mode The channel mode.
   * - AUDIO_DUAL_MONO_STEREO (0): Original mode.
   * - AUDIO_DUAL_MONO_L (1): Left channel mode. This mode replaces the audio of the right channel
   * with the audio of the left channel, which means the user can only hear the audio of the left
   * channel.
   * - AUDIO_DUAL_MONO_R (2): Right channel mode. This mode replaces the audio of the left channel
   * with the audio of the right channel, which means the user can only hear the audio of the right
   * channel.
   * - AUDIO_DUAL_MONO_MIX (3): Mixed channel mode. This mode mixes the audio of the left channel and
   * the right channel, which means the user can hear the audio of the left channel and the right
   * channel at the same time.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setAudioDualMonoMode(int mode);

  /**
   * @brief Registers a video frame observer object.
   *
   * @details
   * You need to implement the `IMediaPlayerVideoFrameObserver` class in this method and register
   * callbacks according to your scenarios. After you successfully register the video frame observer,
   * the SDK triggers the registered callbacks each time a video frame is received.
   *
   * @param videoFrameObserver The video observer, reporting the reception of each video frame. See
   * `IMediaPlayerVideoFrameObserver`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int registerVideoFrameObserver(IMediaPlayerVideoFrameObserver videoFrameObserver);

  /**
   * Open the Agora CDN media source.
   *
   * @deprecated 4.6.0
   *
   * @param src The src of the media file that you want to play.
   * @param startPos The  playback position (ms).
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated int openWithAgoraCDNSrc(String src, long startPos);

  /**
   * Gets the number of  Agora CDN lines.
   *
   * @deprecated 4.6.0
   *
   * @return
   * - > 0: number of CDN.
   * - <= 0: Failure.
   */
  @Deprecated int getAgoraCDNLineCount();

  /**
   * Switch Agora CDN lines.
   *
   * @deprecated 4.6.0
   *
   * @param index Specific CDN line index.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated int switchAgoraCDNLineByIndex(int index);

  /**
   * Gets the line of the current CDN.
   *
   * @deprecated 4.6.0
   *
   * @return
   * - >= 0: Specific line.
   * - < 0: Failure.
   */
  @Deprecated int getCurrentAgoraCDNIndex();

  /**
   * Enable automatic CDN line switching.
   *
   * @deprecated 4.6.0
   *
   * @param enable Whether enable.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated int enableAutoSwitchAgoraCDN(boolean enable);

  /**
   * Update the CDN source token and timestamp.
   *
   * @deprecated 4.6.0
   *
   * @param token token.
   * @param ts ts.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated int renewAgoraCDNSrcToken(String token, long ts);

  /**
   * Switch the CDN source.
   *
   * @deprecated 4.6.0
   *
   * @param src Specific src.
   * @param syncPts Live streaming must be set to false.
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  @Deprecated int switchAgoraCDNSrc(String src, boolean syncPts);

  /**
   * @brief Enables or disables the spatial audio effect for the media player.
   *
   * @details
   * After successfully setting the spatial audio effect parameters of the media player, the SDK
   * enables the spatial audio effect for the media player, and the local user can hear the media
   * resources with a sense of space.
   * If you need to disable the spatial audio effect for the media player, set the `params` parameter
   * to null.
   *
   * @param params The spatial audio effect parameters of the media player. See `SpatialAudioParams`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setSpatialAudioParams(SpatialAudioParams params);
}
