package com.github.nnnnusui.discord.rest.entity

import kotlinx.serialization.Serializable

@Serializable
data class GetGatewayBot(
  val url:    String
 ,val shards: Int
 ,val session_start_limit: SessionStartLimit
)