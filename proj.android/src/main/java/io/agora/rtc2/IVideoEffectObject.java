package io.agora.rtc2;

/**
 * @brief Provides methods to manage and configure video effects, such as beauty, style makeup, and
 * filter.
 *
 * @since v4.6.0
 */
public interface IVideoEffectObject {
  /**
   * @brief Types of applicable video effect nodes.
   *
   * @since v4.6.0
   */
  public enum VIDEO_EFFECT_NODE_ID {
    /**
     * (1): Beauty effect node.
     */
    BEAUTY(1), // 1 << 0
    /**
     * (2): Style makeup effect node.
     */
    STYLE_MAKEUP(2), // 1 << 1
    /**
     * (4): Filter effect node.
     */
    FILTER(4); // 1 << 2

    private final int value;
    VIDEO_EFFECT_NODE_ID(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }
  }

  /**
   * @brief Actions that can be performed on video effect nodes.
   *
   * @since v4.6.0
   */
  public enum VIDEO_EFFECT_ACTION {
    /**
     * (1): Save the current parameters of the video effect node.
     */
    SAVE(1),
    /**
     * (2): Reset the video effect node to default parameters.
     */
    RESET(2);

    private final int value;
    VIDEO_EFFECT_ACTION(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }
  }

  /**
   * @brief Adds or updates the video effect for the specified node ID and template.
   *
   * @since v4.6.0
   *
   * @note
   * Priority rules:
   * - The STYLE_MAKEUP node takes precedence over the FILTER parameter.
   * - To apply the FILTER parameter, you must first remove the STYLE_MAKEUP node:
   * ```
   * removeVideoEffect(VIDEO_EFFECT_NODE_ID::STYLE_MAKEUP);
   * addOrUpdateVideoEffect(VIDEO_EFFECT_NODE_ID::FILTER, "template name");
   * ```
   *
   * @param nodeId The unique identifier or combination of identifiers for the video effect node. See
   * `VIDEO_EFFECT_NODE_ID`.
   * @param templateName The name of the effect template. If set to null or an empty string, the SDK
   * loads the default parameter configuration from the resource package.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure. See the error codes in `AgoraBaseConstants`.
   */
  int addOrUpdateVideoEffect(int nodeId, String templateName);

  /**
   * @brief Removes the video effect with the specified node ID.
   *
   * @since v4.6.0
   *
   * @param nodeId The unique identifier of the video effect node to remove.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int removeVideoEffect(int nodeId);

  /**
   * @brief Performs an action on the specified video effect node.
   *
   * @since v4.6.0
   *
   * @param nodeId The unique identifier of the video effect node.
   * @param actionId The action to perform on the video effect node. See `VIDEO_EFFECT_ACTION`.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int performVideoEffectAction(int nodeId, VIDEO_EFFECT_ACTION actionId);

  /**
   * @brief Sets the float parameter for video effects.
   *
   * @since v4.6.0
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   * @param value The float parameter value to set.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setVideoEffectFloatParam(String option, String key, float value);

  /**
   * @brief Sets an integer parameter for video effects.
   *
   * @since v4.6.0
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   * @param value The integer value to set.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setVideoEffectIntParam(String option, String key, int value);

  /**
   * @brief Sets the boolean parameter for video effects.
   *
   * @since v4.6.0
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   * @param value The boolean value to set.
   * - `true`: Set to enabled.
   * - `false`: Set to disabled.
   *
   * @return
   * - 0: Success.
   * - < 0: Failure.
   */
  int setVideoEffectBoolParam(String option, String key, boolean value);

  /**
   * @brief Retrieves `float` type parameters in video effects.
   *
   * @since v4.6.0
   *
   * @details
   * Used to retrieve the value of a `float` type parameter corresponding to the specified option and
   * key in video effects.
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   *
   * @return
   * - If the method call succeeds, returns the current float value.
   * - If the method call fails, returns 0.0f.
   */
  float getVideoEffectFloatParam(String option, String key);

  /**
   * @brief Retrieves integer parameters in video effects.
   *
   * @since v4.6.0
   *
   * @details
   * Used to retrieve integer-type parameters in video effects.
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   *
   * @return
   * - If the method call succeeds, returns the current parameter value.
   * - If the method call fails, returns 0.
   */
  int getVideoEffectIntParam(String option, String key);

  /**
   * @brief Gets the boolean parameter in video effects.
   *
   * @since v4.6.0
   *
   * @param option The identifier of the parameter category.
   * @param key The key name of the parameter.
   *
   * @return
   * - `true`: The parameter is enabled.
   * - `false`: The parameter is not enabled or does not exist.
   */
  boolean getVideoEffectBoolParam(String option, String key);
}