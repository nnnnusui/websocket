package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildMembersChunk(
  @SerialName("guild_id") val guildId: Snowflake
 ,@SerialName("members" ) val members: Array<Guild.Member>
): IsGatewayEvent