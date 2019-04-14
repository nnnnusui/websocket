package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.permission.Role
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildRoleCreate(
  @SerialName("guild_id") val guildId: Snowflake
 ,@SerialName("role"    ) val role:    Role
): IsGatewayEvent