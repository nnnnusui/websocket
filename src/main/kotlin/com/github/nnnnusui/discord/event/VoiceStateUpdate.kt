package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.voice.VoiceState
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class VoiceStateUpdate(
  val voiceState: VoiceState
): IsGatewayEvent{
    @Serializer(forClass = VoiceStateUpdate::class)
    companion object: KSerializer<VoiceStateUpdate> {
        override fun serialize(encoder: Encoder, obj: VoiceStateUpdate)
            { encoder.encodeSerializableValue(VoiceState.serializer(), obj.voiceState) }
        override fun deserialize(decoder: Decoder): VoiceStateUpdate
            = VoiceStateUpdate(decoder.decodeSerializableValue(VoiceState.serializer()))
    }
}