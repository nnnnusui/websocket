package com.github.nnnnusui.discord.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Resumed(
  @SerialName("_trace") val _trace: Array<String>
): IsGatewayEvent