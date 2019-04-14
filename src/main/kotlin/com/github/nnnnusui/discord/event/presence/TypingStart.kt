package com.github.nnnnusui.discord.event.presence

import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.event.IsGatewayEvent
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class TypingStart(
  @SerialName("channel_id")           val channelId: Snowflake
 ,@SerialName("guild_id"  ) @Optional val guildId:   Snowflake? =null
 ,@SerialName("user_id"   )           val userId:    Snowflake
 ,@SerialName("timestamp" )           val timestamp: Int        // unix time (in seconds) of when the user started typing
): IsGatewayEvent