package com.github.nnnnusui.discord.entity

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Webhook(
  @SerialName("id"        )           val id:        Snowflake
 ,@SerialName("guild_id"  ) @Optional val guildId:   Snowflake? =null
 ,@SerialName("channel_id")           val channelId: Snowflake
 ,@SerialName("user"      ) @Optional val user:      User?      =null
 ,@SerialName("name"      )           val name:      String?
 ,@SerialName("avatar"    )           val avatar:    String?
 ,@SerialName("token"     )           val token:     String
)