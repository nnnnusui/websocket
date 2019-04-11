package com.github.nnnnusui.discord.enums

import com.github.nnnnusui.discord.event.*
import kotlinx.serialization.KSerializer

// @formatter:off
enum class EventType(val serializer: KSerializer<out GatewayEvent>){
    HELLO( Hello.serializer())
   ,READY(                      Ready                   .serializer())
   ,RESUMED(                    Resumed                 .serializer())
   ,CHANNEL_CREATE(             ChannelCreate           .serializer())
   ,CHANNEL_UPDATE(             ChannelUpdate           .serializer())
   ,CHANNEL_DELETE(             ChannelDelete           .serializer())
   ,CHANNEL_PINS_UPDATE(        ChannelPinsUpdate       .serializer())
   ,GUILD_CREATE(               GuildCreate             .serializer())
   ,GUILD_UPDATE(               GuildUpdate             .serializer())
   ,GUILD_DELETE(               GuildDelete             .serializer())
   ,GUILD_BAN_ADD(              GuildBanAdd             .serializer())
   ,GUILD_BAN_REMOVE(           GuildBanRemove          .serializer())
   ,GUILD_EMOJIS_UPDATE(        GuildEmojisUpdate       .serializer())
   ,GUILD_INTEGRATIONS_UPDATE(  GuildIntegrationsUpdate .serializer())
   ,GUILD_MEMBER_ADD(           GuildMemberAdd          .serializer())
   ,GUILD_MEMVER_REMOVE(        GuildMemberRemove       .serializer())
   ,GUILD_MEMBER_UPDATE(        GuildMemberUpdate       .serializer())
   ,GUILD_MEMBERS_CHUMK(        GuildMembersChunk       .serializer())
   ,GUILD_ROLE_CREATE(          GuildRoleCreate         .serializer())
   ,GUILD_ROLE_UPDATE(          GuildRoleUpdate         .serializer())
   ,GUILD_ROLE_DELETE(          GuildRoleDelete         .serializer())
   ,MESSAGE_CREATE(             MessageCreate           .serializer())
   ,MESSAGE_UPDATE(             MessageUpdate           .serializer())
   ,MESSAGE_DELETE(             MessageDelete           .serializer())
   ,MESSAGE_DELETE_BULK(        MessageDeleteBulk       .serializer())
   ,MESSAGE_REACTION_ADD(       MessageReactionAdd      .serializer())
   ,MESSAGE_REACTION_REMOVE(    MessageReactionRemove   .serializer())
   ,MESSAGE_REACTION_REMOVE_ALL(MessageReactionRemoveAll.serializer())
   ,PRESENCE_UPDATE(            PresenceUpdate          .serializer())
   ,TYPING_START(               TypingStart             .serializer())
   ,USER_UPDATE(                UserUpdate              .serializer())
   ,VOICE_STATE_UPDATE(         VoiceStateUpdate        .serializer())
   ,VOICE_SERVER_UPDATE(        VoiceServerUpdate       .serializer())
   ,WEBHOOKS_UPDATE(            WebhooksUpdate          .serializer())
    ;
}