package com.github.nnnnusui.discord.entity.voice

import kotlinx.serialization.SerialName

// @formatter:off
data class VoiceRegion(
                                   val id:           String  // unique ID
 ,                                 val name:         String
 ,@SerialName("vip"       ) val isVipOnly:    Boolean
 ,@SerialName("optimal"   ) val isOptimal:    Boolean
 ,@SerialName("deprecated") val isDeprecated: Boolean
 ,@SerialName("custom"    ) val isCustom:     Boolean
)