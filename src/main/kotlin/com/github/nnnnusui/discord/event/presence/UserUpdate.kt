package com.github.nnnnusui.discord.event.presence

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.event.IsGatewayEvent
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

// @formatter:off
@Serializable
data class UserUpdate(
  val user: User
): IsGatewayEvent{
    @Serializer(forClass = UserUpdate::class)
    companion object: KSerializer<UserUpdate> {
        override fun serialize(encoder: Encoder, obj: UserUpdate)
            { encoder.encodeSerializableValue(User.serializer(), obj.user) }
        override fun deserialize(decoder: Decoder): UserUpdate
            = UserUpdate(decoder.decodeSerializableValue(User.serializer()))
    }
}