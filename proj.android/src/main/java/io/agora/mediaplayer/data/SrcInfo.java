//
//  Agora RTC/MEDIA SDK
//
//  Created by Chenjianming in 2021-10.
//  Copyright (c) 2021 Agora.io. All rights reserved.
//
package io.agora.mediaplayer.data;

import io.agora.base.internal.CalledByNative;
/**
 * @brief Information about the video bitrate of the media resource being played.
 */
public class SrcInfo {
  /**
   * The video bitrate (Kbps) of the media resource being played.
   */
  private int bitrateInKbps;

  /**
   * The name of the media resource.
   */
  private String name;

  public SrcInfo() {}

  @CalledByNative
  public SrcInfo(int bitrateInKbps, String name) {
    this.bitrateInKbps = bitrateInKbps;
    this.name = name;
  }

  @CalledByNative
  public int getBitrateInKbps() {
    return bitrateInKbps;
  }

  @CalledByNative
  public String getName() {
    return name;
  }

  public void setBitrateInKbps(int bitrateInKbps) {
    this.bitrateInKbps = bitrateInKbps;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "SrcInfo{"
        + "bitrateInKbps=" + bitrateInKbps + ", name=" + name + '}';
  }
}
