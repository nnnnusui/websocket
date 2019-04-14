package com.github.nnnnusui.discord.rest.entity

import kotlinx.serialization.Serializable

@Serializable
data class GetGateway(
  val url: String
)