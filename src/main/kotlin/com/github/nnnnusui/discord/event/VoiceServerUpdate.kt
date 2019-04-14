package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class VoiceServerUpdate(
  @SerialName("token"   ) val token:    String
 ,@SerialName("guild_id") val guildId:  Snowflake
 ,@SerialName("endpoint") val endpoint: String
): IsGatewayEvent