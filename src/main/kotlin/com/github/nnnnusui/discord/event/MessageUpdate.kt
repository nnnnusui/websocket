package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.channel.Message
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class MessageUpdate(
  val message: Message
): IsGatewayEvent{
    @Serializer(forClass = MessageUpdate::class)
    companion object: KSerializer<MessageUpdate> {
        override fun serialize(encoder: Encoder, obj: MessageUpdate)
            { encoder.encodeSerializableValue(Message.serializer(), obj.message) }
        override fun deserialize(decoder: Decoder): MessageUpdate
            = MessageUpdate(decoder.decodeSerializableValue(Message.serializer()))
    }
}