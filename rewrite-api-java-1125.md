## 废弃

setRemoteDefaultVideoStreamType[2/2] - done

setRemoteVideoStreamType[2/2] - done

setRemoteVideoStreamTypeEx[2/2] - done

setAudioProfile[1/2] - done

onAudioQuality - done

onFirstRemoteAudioDecoded - done

onFirstRemoteAudioFrame - done

onRemoteAudioTransportStats - done

setExternalAudioSource[1/2] - done

onRemoteVideoTransportStats - done

onVideoStopped - done

seteLocalRenderMode[1/2] - done

setLocalVideoMirrorMode - done

setRemoteRenderMode - done

pushExternalVideoFrame[1/2] - done

onAudioMixingFinished - done

preload[1/2] - done

onCameraReady - done

setRemoteSubscribeFallbackOptions[2/2] - done

ClientRole - done

getRed - done

getBlue - done

getGreen - done

setRed - done

setGreen - done

setBlue - done


## 缺生成内容

joinChannel[1/2]

joinChannel[2/2]

joinChannelEx

joinChannelWithUserAccount[1/2]

joinChannelWithUserAccount[2/2]

joinChannelWithUserAccountEx

leaveChannel[2/2]

leaveChannelEx[1/2]

preloadChannelWithUserAccount

enableDualStreamMode[1/2]

enableDualStreamModeEx

muteLocalAudioStream

muteLocalVideoStream

muteRemoteAudioStream

setDualStreamMode[1/2]

setDualStreamMode[2/2]

setDualStreamModeEx

setRemoteVideoSubscriptionOptionsEx

enableAudioVolumeIndicationEx

enableVoiceAITuner

setAudioEffectParameters

setAudioEffectPreset

setLocalVoiceFormant

setVoiceBeautifierParameters

setVoiceBeautifierPreset

setSpatialAudioParams

registerAudioFrameObserver

setPlaybackAudioFrameBeforeMixingParameters[2/2]

createCustomAudioTrack

enableCustomAudioLocalPlayback

unregisterAudioSpectrumObserver

enableAudioSpectrumMonitor

enableLocalVideo

setVideoEncoderConfiguration

updateScreenCaptureConfiguration

enableContentInspect

takeSnapshot[2/2]

takeSnapshot[1/2]

getFaceShapeAreaOptions[1/2]

getFaceShapeBeautyOptions[1/2]

getFaceShapeBeautyOptions[2/2]

setBeautyEffectOptions[2/2]

setColorEnhanceOptions[1/2]

setColorEnhanceOptions[2/2]

setFaceShapeAreaOptions[1/2]

setFaceShapeAreaOptions[2/2]

setFaceShapeBeautyOptions[1/2]

setFaceShapeBeautyOptions[2/2]

setFilterEffectOptions[1/2]

setVideoDenoiserOptions[2/2]

setLocalRenderMode[2/2]

getObservedFramePosition

onPreEncodeVideoFrame

registerVideoEncodedFrameObserver

createCustomVideoTrack

playEffect[2/2]

muteAudio

muteVideo

openWithUrl

open[1/2]

open[2/2]

selectAudioTrack


## 有 guide 链接删除

preloadChannel


## 调用时机、适用场景、相关回调

setClientRole[1/2]

setClientRole[2/2]（少了一段详情）

enableVideo

updatePreloadChannelToken

muteremoteVideoStream

setAudioProfile[2/2]

setAudioScenario

enableLocalAudio（调用时机也重复）

setAINSMode

setLocalAudioMixer

setExternalMediaProjection（有多余的详见）

startScreenCapture

takeSnapshot[1/2]

startLocalVideoTranscoder（缺参数注意事项）

setLowlightEnhanceOptions[1/2]

setLowlightEnhanceOptions[2/2]

enableFaceDetection

startMediaRenderingTracing

startMediaRenderingTracingEx

setAudioMixingDualMonoMode

startAudioMixing[1/2]（缺参数注意）

## 多生成内容/重复

updateChannelMediaOptionsEx（多生成 note）

onRejoinChannelSuccess

onRenewTokenResult

onAudioSubscribeStateChanged

onVideoSubscribeStateChanged

adjustRecordingSignalVolume

onUserEnableLocalVideo（废弃信息）

takeSnapshotEx[2/2]

adjustAudioMixingPlayoutVolume

setEffectsVolume

setVolumeOfEffect

getLyric

getMusicCharts

onMusicChartsResult

onMusicCollectionResult

onRemoteAudioTransportStats

## 翻译有误

setSubscribeAudioAllowlist

setSubscribeAudioAllowlistEx

setSubscribeAudioBlocklist

setSubscribeAudioBlocklistEx

onFirstRemoteVideoDecoded

registerObserver

enableInEarMonitoring[1/2]

pushExternalAudioFrame

setVideoEncoderConfigurationEx

## 返回值

getAudioMixingPlayoutVolume

getAudioMixingPublishVolume

getAudioTrackCount

getCacheFileCount

getMaxCacheFileCount

## 无用的详见

addView

Canvas

Player

removeView

Rte

searchMusic

getConnectionStateEx

## Exception

getConfigs[1/3]

getConfigs[2/3]

getConfigs[3/3]

setConfigs[1/3]

setConfigs[2/3]

setConfigs[3/3]

setVideoRenderMode

getVideoRenderMode

setVideoMirrorMode

setAppId

setJsonParameter

getFromBridge

unregisterObserver

preloadWithUrl

setCanvas

play

stop

pause

seek

muteAudio

muteVideo

getPosition

getInfo

registerObserver

setAutoplay

getJsonParameter

setAbrSubscriptionLayer

getAbrSubscriptionLayer

setAbrFallbackLayer

getAbrFallbackLayer

## 术语相关

enableDualStreamMode[1/2]

enableDualStrreamMode[2/2]

enableDualStreamModeEx

setDualStreamMode[1/2]

setDualStreamMode[2/2]

startLocalAudioMixer

stopLocalAudioMixer

updateLocalAudioMixerConfiguration

startLocalVideoTranscoder

startSceenCaptureByDisplayId

setColorEnhanceOptions

onTranscodedStreamLayoutInfo

createDataStream[1/2]

createDataStream[2/2]

createDataStreamEx[2/2]

sendStreamMessage

sendStreamMessageEx

onStreamMessage

onStreamMessageError



registerLocalUserAccount
joinChannelWithUserAccount
joinChannelWithUserAccountEx
getUserInfoByUserAccount
getUserInfoByUserAccountEx
getUserInfoByUid
getUserInfoByUidEx