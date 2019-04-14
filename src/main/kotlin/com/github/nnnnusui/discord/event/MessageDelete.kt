package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class MessageDelete(
  @SerialName("id"        )           val id:        Snowflake
 ,@SerialName("channel_id")           val channelId: Snowflake
 ,@SerialName("guild_id"  ) @Optional val guildId:   Snowflake?=null
): IsGatewayEvent