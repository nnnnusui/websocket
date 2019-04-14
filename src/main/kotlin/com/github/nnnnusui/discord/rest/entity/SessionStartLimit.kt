package com.github.nnnnusui.discord.rest.entity

import kotlinx.serialization.Serializable

@Serializable
data class SessionStartLimit(
  val total:       Int
 ,val remaining:   Int
 ,val reset_after: Int
)