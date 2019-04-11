package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName

// @formatter:off
@Serializable
data class Overwrite(
  val id:    Snowflake
 ,val type:  Overwrite.Type // from String
 ,val allow: Int            /* TODO permission bit set */
 ,val deny:  Int            /* TODO permission bit set */
){
    enum class Type{
        ROLE, MEMBER;
        @Serializer(forClass = Type::class)
        companion object: KSerializer<Type> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("DefaultMessageNotificationLevel <-> String(lower case)")
            override fun serialize(encoder: Encoder, obj: Type)
                    { encoder.encodeString(obj.name.toLowerCase()) }
            override fun deserialize(decoder: Decoder): Type {
                val str = decoder.decodeString()
                return try { Type.valueOf(str.toUpperCase()) }
                       catch (exception: IllegalArgumentException)
                           { throw IllegalArgumentException("invalid Enum.name `$str` (${this::class})") }
            }
        }
    }
}
