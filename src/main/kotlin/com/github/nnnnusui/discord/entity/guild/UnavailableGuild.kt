package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class UnavailableGuild(
  @SerialName("unavailable") val isUnavailable: Boolean
 ,@SerialName("id"         ) val id:            Snowflake
)