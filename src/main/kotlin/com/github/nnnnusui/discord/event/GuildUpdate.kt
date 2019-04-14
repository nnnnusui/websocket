package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.Guild
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class GuildUpdate(
  val guild: Guild
): IsGatewayEvent{
    @Serializer(forClass = GuildUpdate::class)
    companion object: KSerializer<GuildUpdate> {
        override fun serialize(encoder: Encoder, obj: GuildUpdate)
            { encoder.encodeSerializableValue(Guild.serializer(), obj.guild) }
        override fun deserialize(decoder: Decoder): GuildUpdate
            = GuildUpdate(decoder.decodeSerializableValue(Guild.serializer()))
    }
}