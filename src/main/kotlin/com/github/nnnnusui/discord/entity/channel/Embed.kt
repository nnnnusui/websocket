package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class Embed(
  @Optional val title:       String?=null
 ,@Optional val type:        String?=null
 ,@Optional val description: String?=null
 ,@Optional val url:         String?=null
 ,@Serializable(with = ISO8601Serializer::class)
  @Optional val timestamp:   Date?              =null
 ,@Optional val color:       Int?               =null // TODO color code
 ,@Optional val footer:      Embed.Footer?      =null
 ,@Optional val image:       Embed.Image?       =null
 ,@Optional val thumbnail:   Embed.Thumbnail?   =null
 ,@Optional val video:       Embed.Video?       =null
 ,@Optional val provider:    Embed.Provider?    =null
 ,@Optional val author:      Embed.Author?      =null
 ,@Optional val fields:      Array<Embed.Field>?=null
){
    @Serializable
    data class Thumbnail(
                                       @Optional val url:      String?=null
     ,@SerialName( "proxy_url") @Optional val proxyUrl: String?=null
     ,                                 @Optional val height:   Int?   =null
     ,                                 @Optional val width:    Int?   =null
    )
    @Serializable
    data class Video(
      @Optional val url:    String?=null
     ,@Optional val height: Int?   =null
     ,@Optional val width:  Int?   =null
    )
    @Serializable
    data class Image(
                                       @Optional val url:      String?=null
     ,@SerialName( "proxy_url") @Optional val proxyUrl: String?=null
     ,                                 @Optional val height:   Int?   =null
     ,                                 @Optional val width:    Int?   =null
    )
    @Serializable
    data class Provider(
      @Optional val name: String?=null
     ,@Optional val url:  String?=null
    )
    @Serializable
    data class Author(
                                            @Optional val name:         String?=null
     ,                                      @Optional val url:          String?=null
     ,@SerialName( "icon_url"      ) @Optional val iconUrl:      String?=null
     ,@SerialName( "proxy_icon_url") @Optional val proxyIconUrl: String?=null
    )
    @Serializable
    data class Footer(
                                                      val text:         String
     ,@SerialName( "icon_url"      ) @Optional val iconUrl:      String?=null
     ,@SerialName( "proxy_icon_url") @Optional val proxyIconUrl: String?=null
    )
    @Serializable
    data class Field(
                                              val name:     String
     ,                                        val value:    String
     ,@SerialName( "inline") @Optional val isInline: Boolean?=null
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