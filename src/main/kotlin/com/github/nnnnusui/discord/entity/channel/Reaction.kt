package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.Emoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Reaction(
                            val count:  Int
 ,@SerialName( "me") val isMine: Boolean
 ,                          val emoji:  Emoji   // partial
)