package com.github.nnnnusui.discord.entity.user

import com.github.nnnnusui.discord.entity.guild.Integration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Connection(
  @SerialName("id"          ) val id:           String             // account id
 ,@SerialName("name"        ) val name:         String
 ,@SerialName("type"        ) val type:         String             // service (twitch, youtube)
 ,@SerialName("revoked"     ) val isRevoked:    Boolean
 ,@SerialName("integrations") val integrations: Array<Integration>
)