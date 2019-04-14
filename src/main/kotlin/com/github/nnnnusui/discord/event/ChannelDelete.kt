package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.channel.Channel
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class ChannelDelete(
  val channel: Channel
): IsGatewayEvent{
    @Serializer(forClass = ChannelDelete::class)
    companion object: KSerializer<ChannelDelete> {
        override fun serialize(encoder: Encoder, obj: ChannelDelete)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelDelete
            = ChannelDelete(decoder.decodeSerializableValue(Channel.serializer()))
    }
}