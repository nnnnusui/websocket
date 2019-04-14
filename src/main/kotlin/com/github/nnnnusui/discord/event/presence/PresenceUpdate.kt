package com.github.nnnnusui.discord.event.presence

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.event.IsGatewayEvent
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

// @formatter:off
@Serializable
data class PresenceUpdate(
  @SerialName("user"         )           val user:         User
 ,@SerialName("roles"        )@Optional  val roles:        Array<Snowflake>?     =null
 ,@SerialName("game"         )           val game:         Activity?
 ,@SerialName("guild_id"     )@Optional  val guildId:      Snowflake?            =null
 ,@SerialName("status"       )           val status:       PresenceUpdate.Status
 ,@SerialName("activities"   )           val activities:   Array<Activity>
 ,@SerialName("client_status")           val clientStatus: ClientStatus

 ,@SerialName("nick"         )@Optional  val nickname:     String?                =null // diff to doc // Not listed in the docs.
): IsGatewayEvent{
    @Serializable(with = Status.Companion::class)
    enum class Status{
        IDLE, DND, ONLINE, OFFLINE;
        @Serializer(forClass = Status::class)
        companion object: KSerializer<Status> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class} <-> String(lower case)")
            override fun serialize(encoder: Encoder, obj: Status)
                    { encoder.encodeString(obj.name.toLowerCase()) }
            override fun deserialize(decoder: Decoder): Status {
                val str = decoder.decodeString()
                return try { Status.valueOf(str.toUpperCase()) }
                       catch (exception: IllegalArgumentException)
                           { throw IllegalArgumentException("invalid Enum.name `$str` (${this::class})") }
            }
        }
    }
}