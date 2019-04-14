package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.channel.Channel
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class ChannelCreate(
  val channel: Channel
): IsGatewayEvent{
    @Serializer(forClass = ChannelCreate::class)
    companion object: KSerializer<ChannelCreate> { // TODO try: interface[ChannelEvent] -> KSerializer<ChannelEvent>
        override fun serialize(encoder: Encoder, obj: ChannelCreate)
            { encoder.encodeSerializableValue(Channel.serializer(), obj.channel) }
        override fun deserialize(decoder: Decoder): ChannelCreate
            = ChannelCreate(decoder.decodeSerializableValue(Channel.serializer()))
    }
}