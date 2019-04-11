package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.user.User
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Ban(
  val reason: String?
 ,val user:   User
)