package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.Guild
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class GuildCreate(
  val guild: Guild
): IsGatewayEvent{
    @Serializer(forClass = GuildCreate::class)
    companion object: KSerializer<GuildCreate> {
        override fun serialize(encoder: Encoder, obj: GuildCreate)
            { encoder.encodeSerializableValue(Guild.serializer(), obj.guild) }
        override fun deserialize(decoder: Decoder): GuildCreate
            = GuildCreate(decoder.decodeSerializableValue(Guild.serializer()))
    }
}