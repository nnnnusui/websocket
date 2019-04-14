package com.github.nnnnusui.discord.entity.voice

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class VoiceRegion(
  @SerialName("id"        ) val id:           String  // unique ID
 ,@SerialName("name"      ) val name:         String
 ,@SerialName("vip"       ) val isVipOnly:    Boolean
 ,@SerialName("optimal"   ) val isOptimal:    Boolean
 ,@SerialName("deprecated") val isDeprecated: Boolean
 ,@SerialName("custom"    ) val isCustom:     Boolean
)