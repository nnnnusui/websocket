package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.channel.Channel
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class ChannelUpdate(
  val channel: Channel
): IsGatewayEvent{
    @Serializer(forClass = ChannelUpdate::class)
    companion object: KSerializer<ChannelUpdate> {
        override fun serialize(encoder: Encoder, obj: ChannelUpdate)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelUpdate
            = ChannelUpdate(decoder.decodeSerializableValue(Channel.serializer()))
    }
}