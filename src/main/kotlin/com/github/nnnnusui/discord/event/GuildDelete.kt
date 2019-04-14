package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.UnavailableGuild
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class GuildDelete(
  val unavailableGuild: UnavailableGuild
): IsGatewayEvent{
    @Serializer(forClass = GuildDelete::class)
    companion object: KSerializer<GuildDelete> {
        override fun serialize(encoder: Encoder, obj: GuildDelete)
            { encoder.encodeSerializableValue(UnavailableGuild.serializer(), obj.unavailableGuild) }
        override fun deserialize(decoder: Decoder): GuildDelete
            = GuildDelete(decoder.decodeSerializableValue(UnavailableGuild.serializer()))
    }
}