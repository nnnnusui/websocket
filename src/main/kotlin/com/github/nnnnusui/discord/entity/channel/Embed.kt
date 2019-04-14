package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class Embed(
  @SerialName("title"      ) @Optional val title:       String?             =null
 ,@SerialName("type"       ) @Optional val type:        String?             =null
 ,@SerialName("description") @Optional val description: String?             =null
 ,@SerialName("url"        ) @Optional val url:         String?             =null
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("timestamp"  ) @Optional val timestamp:   Date?               =null
 ,@SerialName("color"      ) @Optional val color:       Int?                =null // TODO color code
 ,@SerialName("footer"     ) @Optional val footer:      Embed.Footer?       =null
 ,@SerialName("image"      ) @Optional val image:       Embed.Image?        =null
 ,@SerialName("thumbnail"  ) @Optional val thumbnail:   Embed.Thumbnail?    =null
 ,@SerialName("video"      ) @Optional val video:       Embed.Video?        =null
 ,@SerialName("provider"   ) @Optional val provider:    Embed.Provider?     =null
 ,@SerialName("author"     ) @Optional val author:      Embed.Author?       =null
 ,@SerialName("fields"     ) @Optional val fields:      Array<Embed.Field>? =null
){
    @Serializable
    data class Thumbnail(
      @SerialName("url"      ) @Optional val url:      String? =null
     ,@SerialName("proxy_url") @Optional val proxyUrl: String? =null
     ,@SerialName("height"   ) @Optional val height:   Int?    =null
     ,@SerialName("width"    ) @Optional val width:    Int?    =null
    )
    @Serializable
    data class Video(
      @SerialName("url"   ) @Optional val url:    String? =null
     ,@SerialName("height") @Optional val height: Int?    =null
     ,@SerialName("width" ) @Optional val width:  Int?    =null
    )
    @Serializable
    data class Image(
      @SerialName("url"      ) @Optional val url:      String? =null
     ,@SerialName("proxy_url") @Optional val proxyUrl: String? =null
     ,@SerialName("height"   ) @Optional val height:   Int?    =null
     ,@SerialName("width"    ) @Optional val width:    Int?    =null
    )
    @Serializable
    data class Provider(
      @SerialName("name") @Optional val name: String? =null
     ,@SerialName("url" ) @Optional val url:  String? =null
    )
    @Serializable
    data class Author(
      @SerialName("name"          ) @Optional val name:         String? =null
     ,@SerialName("url"           ) @Optional val url:          String? =null
     ,@SerialName("icon_url"      ) @Optional val iconUrl:      String? =null
     ,@SerialName("proxy_icon_url") @Optional val proxyIconUrl: String? =null
    )
    @Serializable
    data class Footer(
      @SerialName("text"          )           val text:         String
     ,@SerialName("icon_url"      ) @Optional val iconUrl:      String? =null
     ,@SerialName("proxy_icon_url") @Optional val proxyIconUrl: String? =null
    )
    @Serializable
    data class Field(
      @SerialName("name"  )           val name:     String
     ,@SerialName("value" )           val value:    String
     ,@SerialName("inline") @Optional val isInline: Boolean? =null
    )
    enum class Limits(val limit: Int){
        TITLE(       256)
       ,DESCRIPTION(2048)
       ,FIELDS(       25)
       ,FIELD_NAME(  256)
       ,FIELD_VALUE(1024)
       ,FOOTER_TEXT(2048)
       ,AUTHOR_NAME( 256)
        ;
    }
}