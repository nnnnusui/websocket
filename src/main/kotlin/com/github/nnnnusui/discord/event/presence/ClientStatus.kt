package com.github.nnnnusui.discord.event.presence

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class ClientStatus(
  @SerialName("desktop") @Optional val desktop: String?=null
 ,@SerialName("mobile" ) @Optional val mobile:  String?=null
 ,@SerialName("web"    ) @Optional val web:     String?=null
)