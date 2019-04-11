package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
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
import java.lang.IllegalArgumentException
import java.util.Date

// @formatter:off
@Serializable
data class Channel(
                                                         val id:                   Snowflake
 ,                                                       val type:                 Type
 ,@SerialName( "guild_id"             ) @Optional val guildId:              Snowflake?       =null
 ,@SerialName( "position"             ) @Optional val sortingPosition:      Int?             =null
 ,@SerialName( "permission_overwrites") @Optional val permissionOverwrites: Array<Overwrite>?=null
 ,                                             @Optional val name:                 String?          =null
 ,                                             @Optional val topic:                String?          = null // nullable
 ,@SerialName( "nsfw"                 ) @Optional val isNsfw:               Boolean?         =null
 ,@SerialName( "last_message_id"      ) @Optional val lastMessageId:        Snowflake?       = null // nullable
 ,                                             @Optional val bitrate:              Int?             =null
 ,@SerialName( "user_limit"           ) @Optional val userLimit:            Int?             =null
 ,@SerialName( "rate_limit_per_user"  ) @Optional val rateLimitPerUser:     Int?             =null
 ,                                             @Optional val recipients:           Array<User>?     =null
 ,                                             @Optional val icon:                 String?          = null // nullable
 ,@SerialName( "owner_id"             ) @Optional val ownerId:              Snowflake?       =null
 ,@SerialName( "application_id"       ) @Optional val applicationId:        Snowflake?       =null
 ,@SerialName( "parent_id"            ) @Optional val parentId:             Snowflake?       = null // nullable
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName( "last_pin_timestamp"   ) @Optional val lastPinTimestamp:     Date?            =null
){
    enum class Type(val code: Int){
        GUILD_TEXT(    0)
       ,DM(            1)
       ,GUILD_VOICE(   2)
       ,GROUP_DM(      3)
       ,GUILD_CATEGORY(4)
       ,GUILD_NEWS(    5)
       ,GUILD_STORE(   6)
        ;
        @Serializer(forClass = Type::class)
        companion object: KSerializer<Type> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("DefaultMessageNotificationLevel <-> code: Int")
            override fun serialize(encoder: Encoder, obj: Type)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): Type {
                val num = decoder.decodeInt()
                return Type.values().firstOrNull { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
}


