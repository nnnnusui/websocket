package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Attachment(
                                   val id:       Snowflake
 ,                                 val filename: String
 ,                                 val size:     Int
 ,                                 val url:      String
 ,@SerialName( "proxy_url") val proxyUrl: String
 ,                                 val height:   Int?
 ,                                 val width:    Int?
)