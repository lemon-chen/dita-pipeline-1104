//
//  AgoraMediaFilterExtensionDelegate.h
//  Agora SDK
//
//  Created by LLF on 2020-9-21.
//  Copyright (c) 2020 Agora. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AgoraAudioFilterDelegate.h"
#import "AgoraVideoFilterDelegate.h"
#import "AgoraVideoSinkDelegate.h"

typedef NS_ENUM(NSInteger, AgoraExtProviderType) {
 AgoraExtProviderTypeLocalAudioFilter = 0,
 AgoraExtProviderTypeRemoteAudioFilter = 1,
 AgoraExtProviderTypeLocalVideoFilter = 2,
 AgoraExtProviderTypeRemoteVideoFilter = 3,
 AgoraExtProviderTypeLocalVideoSink = 4,
 AgoraExtProviderTypeRemoteVideoSink = 5,
 AgoraExtProviderTypeUnknown = 6,
};

typedef NS_ENUM(NSInteger, AgoraExtLogLevel) {
  AgoraExtLogLevelNone = 0x0000,
  AgoraExtLogLevelInfo = 0x0001,
  AgoraExtLogLevelWarn = 0x0002,
  AgoraExtLogLevelError = 0x0004,
  AgoraExtLogLevelFatal = 0x0008,
};

@protocol AgoraExtControlDelegate <NSObject>
/**
 * write log into sdk.
 * @param level logging level
 * @param message logging message string
 * @return
 * - 0, if succeeds
 * - <0, if error happens
 */
- (NSInteger)log:(AgoraExtLogLevel)level
         message:(NSString * _Nullable)message NS_SWIFT_NAME(log(_:message:));

- (NSInteger)fireEvent:(NSString * _Nonnull)provider
             extension:(NSString * _Nullable)extension
                   key:(NSString * _Nullable)key
                 value:(NSString * _Nullable)value NS_SWIFT_NAME(fireEvent(_:extension:key:value:));

@end

@protocol AgoraExtProviderDelegate <NSObject>

- (NSArray<AgoraExtensionMetaInfo*> * _Nonnull)getExtenionMetaInfoList NS_SWIFT_NAME(getExtenionMetaInfoList());

- (void)setExtensionControl:(id<AgoraExtControlDelegate> _Nullable)control NS_SWIFT_NAME(setExtensionControl(_:));
- (id<AgoraAudioFilterDelegate> _Nullable)createAudioFilter NS_SWIFT_NAME(createAudioFilter());
- (id<AgoraVideoFilterDelegate> _Nullable)createVideoFilter NS_SWIFT_NAME(createVideoFilter());
- (id<AgoraVideoSinkDelegate> _Nullable)createVideoSink NS_SWIFT_NAME(createVideoSink());

@end

/**
 * @brief The `AgoraMediaFilterExtensionDelegate` class.
 * If you prefer to use cpp to implement Extension Plugin, you MUST implement the following methods:
 * - (NSString * _Nonnull)vendor;
 * - - (void * _Nullable)mediaFilterRawProvider; 
 * 
 * If you prefer to use ObjC to implement Extension Plugin, you MUST implement the following methods:
 * - (NSString * _Nonnull)vendor;
 * - (id<AgoraExtProviderDelegate> _Nullable)mediaFilterProvider; 
 */
@protocol AgoraMediaFilterExtensionDelegate <NSObject>
/** 
 * Media filter (audio filter or video fitler) name. It shoud be unique.
 */
- (NSString * _Nonnull)vendor NS_SWIFT_NAME(vendor());

@optional
/** 
 * Meida filter (audio filter or video filter or video sink) pointer.
 * This pointer MUST implement the `AgoraExtProviderDelegate` interface.
 */
- (id<AgoraExtProviderDelegate> _Nullable)mediaFilterProvider NS_SWIFT_NAME(mediaFilterProvider());

/**
 * Meida filter (audio filter or video filter) pointer.
 * This pointer MUST implement the `IExtensionProvider` interface.
 */
- (void * _Nullable)mediaFilterRawProvider NS_SWIFT_NAME(mediaFilterRawProvider());

@end
