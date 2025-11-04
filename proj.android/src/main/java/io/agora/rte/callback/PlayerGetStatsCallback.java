package io.agora.rte.callback;
import io.agora.base.internal.CalledByNative;
import io.agora.rte.Player;
import io.agora.rte.Error;
import io.agora.rte.PlayerStats;

/**
 * The PlayerGetStatsCallback class.
 * @since v4.4.0
 */
public interface PlayerGetStatsCallback {
  /**
   * @brief This callback handles the result of the asynchronous operation to retrieve media resource statistics.
   *
   * @details
   * Call timing: The SDK triggers this callback to report the result when the asynchronous operation to retrieve media resource information is complete.
   *
   * @param playerStats Statistics of the media resource. See `PlayerStats` for details.
   * @param error Status and error information. See `Error` for details.
   *
   */
  @CalledByNative void onResult(PlayerStats playerStats, Error error);
}
