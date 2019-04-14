package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.Emoji
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildEmojisUpdate(
  @SerialName("guild_id") val guildId: Snowflake
 ,@SerialName("emojis"  ) val emojis:  Array<Emoji>
): IsGatewayEvent