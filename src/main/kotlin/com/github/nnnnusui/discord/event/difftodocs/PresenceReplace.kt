package com.github.nnnnusui.discord.event.difftodocs

import com.github.nnnnusui.discord.event.IsGatewayEvent
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement

@Serializable
data class PresenceReplace(
  val array: JsonElement?
): IsGatewayEvent{
    @Serializer(forClass = PresenceReplace::class)
    companion object: KSerializer<PresenceReplace> {
        override fun serialize(encoder: Encoder, obj: PresenceReplace)
            { encoder.encodeNull() }

        override fun deserialize(decoder: Decoder): PresenceReplace
            = PresenceReplace(decoder.decodeNull())
    }
}