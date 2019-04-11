package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.*
import com.github.nnnnusui.discord.entity.channel.Channel
import com.github.nnnnusui.discord.entity.channel.Message
import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.guild.UnavailableGuild
import com.github.nnnnusui.discord.entity.permission.Role
import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.entity.voice.VoiceState
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement

// @formatter:off
@Serializable
data class Ready(
  @SerialName("v") val version: Int
    ,val user_settings: JsonElement // diff to doc
 ,val user: User //User
 ,@Optional val shard: Array<Int> = arrayOf<Int>()
 ,val relationships: JsonElement // diff to doc
 ,val presences: JsonElement // diff to doc
 ,val session_id: String
 ,val private_channels: Array<JsonElement> //Array<PrivateChannel>
 ,val guilds: Array<UnavailableGuild> //Array<Guild>
 ,val _trace: Array<String>
): GatewayEvent{
}
@Serializable
data class Resumed(
    val _trace: Array<String>
): GatewayEvent
// @formatter:off



@Serializable
data class ChannelCreate(
  val channel: Channel
): GatewayEvent{
    @Serializer(forClass = ChannelCreate::class)
    companion object: KSerializer<ChannelCreate> {
        override fun serialize(encoder: Encoder, obj: ChannelCreate)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelCreate
            = ChannelCreate(decoder.decodeSerializableValue(Channel.serializer()))
    }
}
@Serializable
data class ChannelUpdate(
  val channel: Channel
): GatewayEvent{
    @Serializer(forClass = ChannelUpdate::class)
    companion object: KSerializer<ChannelUpdate> {
        override fun serialize(encoder: Encoder, obj: ChannelUpdate)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelUpdate
            = ChannelUpdate(decoder.decodeSerializableValue(Channel.serializer()))
    }
}
@Serializable
data class ChannelDelete(
  val channel: Channel
): GatewayEvent{
    @Serializer(forClass = ChannelDelete::class)
    companion object: KSerializer<ChannelDelete> {
        override fun serialize(encoder: Encoder, obj: ChannelDelete)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelDelete
            = ChannelDelete(decoder.decodeSerializableValue(Channel.serializer()))
    }
}
@Serializable
data class ChannelPinsUpdate(
  val channelId: Snowflake
 ,@Optional val lastPinTimestamp: String?=null //ISO8601 timestamp
): GatewayEvent// nonComplete ISO8601 timestamp


@Serializable
data class GuildCreate(
  val guild: Guild
): GatewayEvent{
    @Serializer(forClass = GuildCreate::class)
    companion object: KSerializer<GuildCreate> {
        override fun serialize(encoder: Encoder, obj: GuildCreate)
            { encoder.encodeSerializableValue(Guild.serializer(), obj.guild) }
        override fun deserialize(decoder: Decoder): GuildCreate
            = GuildCreate(decoder.decodeSerializableValue(Guild.serializer()))
    }
}
@Serializable
data class GuildUpdate(
  val guild: Guild
): GatewayEvent{
    @Serializer(forClass = GuildUpdate::class)
    companion object: KSerializer<GuildUpdate> {
        override fun serialize(encoder: Encoder, obj: GuildUpdate)
            { encoder.encodeSerializableValue(Guild.serializer(), obj.guild) }
        override fun deserialize(decoder: Decoder): GuildUpdate
            = GuildUpdate(decoder.decodeSerializableValue(Guild.serializer()))
    }
}
@Serializable
data class GuildDelete(
  val unavailableGuild: UnavailableGuild
): GatewayEvent{
    @Serializer(forClass = GuildDelete::class)
    companion object: KSerializer<GuildDelete> {
        override fun serialize(encoder: Encoder, obj: GuildDelete)
            { encoder.encodeSerializableValue(UnavailableGuild.serializer(), obj.unavailableGuild) }
        override fun deserialize(decoder: Decoder): GuildDelete
            = GuildDelete(decoder.decodeSerializableValue(UnavailableGuild.serializer()))
    }
}
@Serializable
data class GuildBanAdd(
  val guild_id: Snowflake
 ,val user: User
): GatewayEvent
@Serializable
data class GuildBanRemove(
  val guild_id: Snowflake
 ,val user: User
): GatewayEvent
@Serializable
data class GuildEmojisUpdate(
  val guild_id: Snowflake
 ,val emojis: Array<Emoji>
): GatewayEvent
@Serializable
data class GuildIntegrationsUpdate(
  val guild_id: Snowflake
): GatewayEvent
@Serializable
data class GuildMemberAdd(
  val guild_id: Snowflake
 ,val user: User
 ,@Optional val nick: String?=null
 ,val roles: Array<Snowflake>
 ,val joined_at: String?
 ,val deaf: Boolean
 ,val mute: Boolean
): GatewayEvent{
//    val guildMember by lazyOf(Guild.Member(user, nick, roles, joined_at, deaf, mute))
}
@Serializable
data class GuildMemberRemove(
  val guild_id: Snowflake
 ,val user: User
): GatewayEvent
@Serializable
data class GuildMemberUpdate(
  val guild_id: Snowflake
 ,val roles: Array<Snowflake>
 ,val user: User
 ,val nick: String
): GatewayEvent
@Serializable
data class GuildMembersChunk(
  val guild_id: Snowflake
 ,val members: Array<Guild.Member>
): GatewayEvent
@Serializable
data class GuildRoleCreate(
  val guild_id: Snowflake
 ,val role: Role
): GatewayEvent
@Serializable
data class GuildRoleUpdate(
  val guild_id: Snowflake
 ,val role: Role
): GatewayEvent
@Serializable
data class GuildRoleDelete(
  val guild_id: Snowflake
 ,val role_id: Snowflake
): GatewayEvent

@Serializable
data class MessageCreate(
  val message: Message
): GatewayEvent{
    @Serializer(forClass = MessageCreate::class)
    companion object: KSerializer<MessageCreate> {
        override fun serialize(encoder: Encoder, obj: MessageCreate)
            { encoder.encodeSerializableValue(Message.serializer(), obj.message) }
        override fun deserialize(decoder: Decoder): MessageCreate
            = MessageCreate(decoder.decodeSerializableValue(Message.serializer()))
    }
}
@Serializable
data class MessageUpdate(
  val message: Message
): GatewayEvent{
    @Serializer(forClass = MessageUpdate::class)
    companion object: KSerializer<MessageUpdate> {
        override fun serialize(encoder: Encoder, obj: MessageUpdate)
            { encoder.encodeSerializableValue(Message.serializer(), obj.message) }
        override fun deserialize(decoder: Decoder): MessageUpdate
            = MessageUpdate(decoder.decodeSerializableValue(Message.serializer()))
    }
}
@Serializable
data class MessageDelete(
  val id: Snowflake
 ,val channel_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
): GatewayEvent
@Serializable
data class MessageDeleteBulk(
  val ids: Array<Snowflake>
 ,val channel_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
): GatewayEvent
@Serializable
data class MessageReactionAdd(
  val user_id: Snowflake
 ,val channel_id: Snowflake
 ,val message_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
 ,val emoji: Emoji // partial
): GatewayEvent
@Serializable
data class MessageReactionRemove(
  val user_id: Snowflake
 ,val channel_id: Snowflake
 ,val message_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
 ,val emoji: Emoji // partial
): GatewayEvent
@Serializable
data class MessageReactionRemoveAll(
  val channel_id: Snowflake
 ,val message_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
): GatewayEvent

@Serializable
data class PresenceUpdate(
  val user: User
     ,@Optional val roles: Array<Snowflake>?=null // diff to doc
     ,@Optional val guild_id: Snowflake?=null     // diff to doc
 ,val game: Activity?
 ,val status: String // "idle", "dnd", "online", or "offline"
 ,val activities: Array<Activity>
 ,val client_status: ClientStatus // ClientStatus object
): GatewayEvent
@Serializable
data class ClientStatus(
    @Optional val desktop: String?=null
    ,@Optional val mobile:  String?=null
    ,@Optional val web:     String?=null
)
// @formatter:off
@Serializable
data class Activity(
  val name: String
 ,val type: Int    // enum ActivityType
 ,@Optional val url:            String? = null // nullable
 ,@Optional val timestamps:     Activity.Timestamps?=null // Timestamps
 ,@Optional val application_id: Snowflake?=null
 ,@Optional val details:        String? = null // nullable
 ,@Optional val state:          String?   = null // nullable
 ,@Optional val party:          Activity.Party?=null // Party object
 ,@Optional val assets:         Activity.Assets?=null // Assets object
 ,@Optional val secrets:        Activity.Secrets?=null // Secrets object
 ,@Optional val instance:       Boolean?=null
 ,@Optional val flags:          Int?=null // activity flags OR d together ...?
){
    @Serializable
    data class Timestamps(
      @Optional val start: Int?=null
     ,@Optional val end:   Int?=null
    )
    @Serializable
    data class Party(
      @Optional val id: String?=null
     ,@Optional val size: Array<Int>?=null
    )
    @Serializable
    data class Assets(
      @Optional val large_image: String?=null
     ,@Optional val large_text:  String?=null
     ,@Optional val small_image: String?=null
     ,@Optional val small_text:  String?=null
    )
    @Serializable
    data class Secrets(
      @Optional val join:     String?=null
     ,@Optional val spectate: String?=null
     ,@Optional val match:    String?=null
    )
    //data class Flags
}// nonComplete enum ActivityType, activity flags OR d


@Serializable
data class TypingStart(
  val channel_id: Snowflake
 ,@Optional val guild_id: Snowflake?=null
 ,val user_id: Snowflake
 ,val timestamp: Int  // unix time (in seconds) of when the user started typing
): GatewayEvent
@Serializable
data class UserUpdate(
  val user: User
): GatewayEvent{
    @Serializer(forClass = UserUpdate::class)
    companion object: KSerializer<UserUpdate> {
        override fun serialize(encoder: Encoder, obj: UserUpdate)
            { encoder.encodeSerializableValue(User.serializer(), obj.user) }
        override fun deserialize(decoder: Decoder): UserUpdate
            = UserUpdate(decoder.decodeSerializableValue(User.serializer()))
    }
}

@Serializable
data class VoiceStateUpdate(
  val voiceState: VoiceState
): GatewayEvent{
    @Serializer(forClass = VoiceStateUpdate::class)
    companion object: KSerializer<VoiceStateUpdate> {
        override fun serialize(encoder: Encoder, obj: VoiceStateUpdate)
            { encoder.encodeSerializableValue(VoiceState.serializer(), obj.voiceState) }
        override fun deserialize(decoder: Decoder): VoiceStateUpdate
            = VoiceStateUpdate(decoder.decodeSerializableValue(VoiceState.serializer()))
    }
}
@Serializable
data class VoiceServerUpdate(
  val token: String
 ,val guild_id: Snowflake
 ,val endpoint: String
): GatewayEvent

@Serializable
data class WebhooksUpdate(
  val guild_id: Snowflake
 ,val channel_id: Snowflake
): GatewayEvent
