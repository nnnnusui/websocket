package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Attachment(
  @SerialName("id"       ) val id:       Snowflake
 ,@SerialName("filename" ) val filename: String
 ,@SerialName("size"     ) val size:     Int
 ,@SerialName("url"      ) val url:      String
 ,@SerialName("proxy_url") val proxyUrl: String
 ,@SerialName("height"   ) val height:   Int?
 ,@SerialName("width"    ) val width:    Int?
)