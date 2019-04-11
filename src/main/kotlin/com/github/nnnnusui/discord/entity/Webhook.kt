package com.github.nnnnusui.discord.entity

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName

// @formatter:off
data class Webhook(
                                             val id:        Snowflake
 ,@SerialName("guild_id"  ) @Optional val guildId:   Snowflake?=null
 ,@SerialName("channel_id")           val channelId: Snowflake
 ,                                 @Optional val user:      User?     =null
 ,                                           val name:      String?
 ,                                           val avatar:    String?
 ,                                           val token:     String
)