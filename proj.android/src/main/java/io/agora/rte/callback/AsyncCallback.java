package io.agora.rte.callback;

import io.agora.base.internal.CalledByNative;
import io.agora.rte.Error;

/**
 * @brief Callback for asynchronous operation result.
 *
 * @since v4.4.0
 *
 * @details
 * Call timing: The SDK triggers this callback when the asynchronous operation is completed to report the result.
 *
 * @param error Status or error code. See `Error` for details.
 *
 */
public interface AsyncCallback { @CalledByNative void onResult(Error error); }
