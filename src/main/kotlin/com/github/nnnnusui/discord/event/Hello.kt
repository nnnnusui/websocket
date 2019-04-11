package com.github.nnnnusui.discord.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hello(
  @SerialName( "heartbeat_interval")val heartbeatInterval: Long
 ,@SerialName( "_trace")            val _trace           : Array<String>
): GatewayEvent