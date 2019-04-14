package com.github.nnnnusui.discord.entity.channel

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

// @formatter:off
@Serializable
data class Overwrite(
  @SerialName("id"   ) val id:    Snowflake
 ,@SerialName("type" ) val type:  Overwrite.Type // from String
 ,@SerialName("allow") val allow: Int            /* TODO permission bit set */
 ,@SerialName("deny" ) val deny:  Int            /* TODO permission bit set */
){
    @Serializable(with = Type.Companion::class)
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
