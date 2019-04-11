package com.github.nnnnusui.discord.entity.user

import com.github.nnnnusui.discord.entity.guild.Integration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Connection(
                                val id:           String             // account id
 ,                              val name:         String
 ,                              val type:         String             // service (twitch, youtube)
 ,@SerialName("revoked") val isRevoked:    Boolean
 ,                              val integrations: Array<Integration>
)