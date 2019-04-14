package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class ChannelPinsUpdate(
  @SerialName("channel_id"        )          val channelId:        Snowflake
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("last_pin_timestamp")@Optional val lastPinTimestamp: Date?=null
): IsGatewayEvent