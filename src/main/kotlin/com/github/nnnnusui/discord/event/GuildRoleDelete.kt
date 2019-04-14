package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class GuildRoleDelete(
  @SerialName("guild_id") val guildId: Snowflake
 ,@SerialName("role_id" ) val roleId:  Snowflake
): IsGatewayEvent