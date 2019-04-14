package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class MessageDeleteBulk(
  @SerialName("ids"       )           val ids:       Array<Snowflake> // Message.id
 ,@SerialName("channel_id")           val channelId: Snowflake
 ,@SerialName("guild_id"  ) @Optional val guildId:   Snowflake?       =null
): IsGatewayEvent