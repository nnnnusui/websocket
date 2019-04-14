package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildIntegrationsUpdate(
  @SerialName("guild_id") val guildId: Snowflake
): IsGatewayEvent