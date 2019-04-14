package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Ban(
  @SerialName("reason") val reason: String?
 ,@SerialName("user"  ) val user:   User
)