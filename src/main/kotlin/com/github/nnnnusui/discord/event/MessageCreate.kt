package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.channel.Message
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class MessageCreate(
  val message: Message
): IsGatewayEvent{
    @Serializer(forClass = MessageCreate::class)
    companion object: KSerializer<MessageCreate> {
        override fun serialize(encoder: Encoder, obj: MessageCreate)
            { encoder.encodeSerializableValue(Message.serializer(), obj.message) }
        override fun deserialize(decoder: Decoder): MessageCreate
            = MessageCreate(decoder.decodeSerializableValue(Message.serializer()))
    }
}