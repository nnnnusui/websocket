package com.github.nnnnusui.discord.event.presence

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName
import kotlin.IllegalArgumentException

/*
 * Bots are only able to send name, type, and optionally url.
 */
// @formatter:off
@Serializable
data class Activity(
  @SerialName("name"          )           val name:           String
  /* Not listed in the docs.        */
 ,@SerialName("id"            )           val id:             String
 ,@SerialName("session_id"    ) @Optional val sessionId:      Snowflake? =null
 ,@SerialName("created_at"    )           val createdAt:      Long
  /* ------------------------------ */
 ,@SerialName("type"          )           val type:           Activity.Type
 ,@SerialName("url"           ) @Optional val url:            String?              = null // nullable
 ,@SerialName("timestamps"    ) @Optional val timestamps:     Activity.Timestamps? =null
 ,@SerialName("application_id") @Optional val applicationId:  Snowflake?           =null
 ,@SerialName("details"       ) @Optional val details:        String?              = null // nullable
 ,@SerialName("state"         ) @Optional val state:          String?              = null // nullable
 ,@SerialName("party"         ) @Optional val party:          Activity.Party?      =null
 ,@SerialName("assets"        ) @Optional val assets:         Activity.Assets?     =null
 ,@SerialName("secrets"       ) @Optional val secrets:        Activity.Secrets?    =null
 ,@SerialName("instance"      ) @Optional val isInstance:     Boolean?             =null
 ,@SerialName("flags"         ) @Optional val flags:          Int?                 =null // TODO activity flags OR d together ...?
){
    @Serializable(with = Type.Companion::class)
    enum class Type(val code: Int){
        GAME(     0)
       ,STREAMING(1)
       ,LISTENING(2)
        ;
        @Serializer(forClass = Type::class)
        companion object: KSerializer<Type> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class} <-> code: Int")
            override fun serialize(encoder: Encoder, obj: Type)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): Type {
                val num = decoder.decodeInt()
                return Type.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    @Serializable
    data class Timestamps(
      @SerialName("start") @Optional val start: Long?=null // milliseconds unix time
     ,@SerialName("end"  ) @Optional val end:   Long?=null // milliseconds unix time
    )
    @Serializable
    data class Party(
      @SerialName("id"  ) @Optional val id:   String?     =null
     ,@SerialName("size") @Optional val size: Array<Int>? =null
    )
    @Serializable
    data class Assets(
      @SerialName("large_image") @Optional val largeImage: String? =null
     ,@SerialName("large_text" ) @Optional val largeText:  String? =null
     ,@SerialName("small_image") @Optional val smallImage: String? =null
     ,@SerialName("small_text" ) @Optional val smallText:  String? =null
    )
    @Serializable
    data class Secrets(
      @SerialName("join"    ) @Optional val join:     String? =null
     ,@SerialName("spectate") @Optional val spectate: String? =null
     ,@SerialName("match"   ) @Optional val match:    String? =null
    )
    //data class Flags
}