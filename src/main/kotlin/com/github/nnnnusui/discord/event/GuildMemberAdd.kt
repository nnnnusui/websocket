package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class GuildMemberAdd(
  @SerialName("guild_id" )           val guildId:  Snowflake
  /* --- Guild.Member objects --- */
 ,@SerialName("user"     )           val user:     User
 ,@SerialName("nick"     ) @Optional val nickname: String?          =null
 ,@SerialName("roles"    )           val roles:    Array<Snowflake> // Role.id
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("joined_at")           val joinedAt: Date
 ,@SerialName("deaf"     )           val isDeaf:   Boolean
 ,@SerialName("mute"     )           val isMute:   Boolean
): IsGatewayEvent{
    val guildMember by lazyOf(Guild.Member(user, nickname, roles, joinedAt, isDeaf, isMute))
}