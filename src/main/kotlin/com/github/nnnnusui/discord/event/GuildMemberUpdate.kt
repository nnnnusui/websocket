package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildMemberUpdate(
  @SerialName("guild_id") val guildId: Snowflake
 ,@SerialName("toles"   ) val roles:   Array<Snowflake> // Role.id
 ,@SerialName("user"    ) val user:    User
 ,@SerialName("nick"    ) val nick:    String
): IsGatewayEvent