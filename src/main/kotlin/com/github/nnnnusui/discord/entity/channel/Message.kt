package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.guild.Guild
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
import java.util.Date

// @formatter:off
@Serializable
data class Message(
  @SerialName("id"              )           val id:                Snowflake
 ,@SerialName("channel_id"      )           val channelId:         Snowflake
 ,@SerialName("guild_id"        ) @Optional val guildId:           Snowflake?           =null
 ,@SerialName("author"          )           val author:            User
 ,@SerialName("member"          ) @Optional val member:            Guild.Member?        =null
 ,@SerialName("content"         )           val content:           String
 ,@Serializable(with = ISO8601Serializer::class)    val timestamp:         Date
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("edited_timestamp")           val editedTimestamp:   Date?
 ,@SerialName("tts"             )           val isTts:             Boolean
 ,@SerialName("mention_everyone")           val isMentionEveryone: Boolean
 ,@SerialName("mentions"        )           val mentions:          Array<User      >
 ,@SerialName("mention_roles"   )           val mentionRoles:      Array<Snowflake >    // array of role object ids
 ,@SerialName("attachments"     )           val attachments:       Array<Attachment>
 ,@SerialName("embeds"          )           val embeds:            Array<Embed     >
 ,@SerialName("reactions"       ) @Optional val reactions:         Array<Reaction  >?   =null
 ,@SerialName("nonce"           ) @Optional val nonce:             Snowflake?           = null // nullable
 ,@SerialName("pinned"          )           val isPinned:          Boolean
 ,@SerialName("webhook_id"      ) @Optional val webhookId:         Snowflake?           =null
 ,@SerialName("type"            )           val type:              Message.Type         // from Int
 ,@SerialName("activity"        ) @Optional val activity:          Message.Activity?    =null
 ,@SerialName("application"     ) @Optional val application:       Message.Application? =null
){
    val isNonce get() = nonce == null

    @Serializable(with = Type.Companion::class)
    enum class Type(val code: Int){
        DEFAULT(               0)
       ,RECIPIENT_ADD(         1)
       ,RECIPIENT_REMOVE(      2)
       ,CALL(                  3)
       ,CHANNEL_NAME_CHANGE(   4)
       ,CHANNEL_ICON_CHANGE(   5)
       ,CHANNEL_PINNED_MESSAGE(6)
       ,GUILD_MEMBER_JOIN(     7)
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

    @Serializable
    data class Activity(
      @SerialName("type"    )           val type:    Activity.Type // from Int
     ,@SerialName("party_id") @Optional val partyId: String?       =null
    ){
        enum class Type(val code: Int){
            JOIN(        0)
           ,SPECTATE(    1)
           ,LISTEN(      2)
           ,JOIN_REQUEST(3)
            ;
            @Serializer(forClass = Type::class)
            companion object: KSerializer<Type> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("DefaultMessageNotificationLevel <-> code: Int")
            override fun serialize(encoder: Encoder, obj: Type)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): Type {
                val num = decoder.decodeInt()
                return Type.values().firstOrNull { it.code == num } ?: throw IllegalArgumentException("invalid Message.DefaultMessageNotificationLevel:$num")
            }
        }
        }
    }
    @Serializable
    data class Application(
      @SerialName("id"         )           val id:          Snowflake
     ,@SerialName("cover_image") @Optional val coverImage:  String?   =null
     ,@SerialName("description")           val description: String
     ,@SerialName("icon"       )           val icon:        String
     ,@SerialName("name"       )           val name:        String
    )
}