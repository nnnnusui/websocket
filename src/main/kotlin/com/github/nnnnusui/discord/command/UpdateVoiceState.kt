package com.github.nnnnusui.discord.command

import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.enums.Opcode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class UpdateVoiceState(
  @SerialName("guild_id"  ) val guildId:    Snowflake
 ,@SerialName("channel_id") val channelId:  Snowflake? // null -> disconnecting
 ,@SerialName("self_mute" ) val isSelfMute: Boolean
 ,@SerialName("self_deaf" ) val isSelfDeaf: Boolean
): GatewayCommand{
    override val opcode = Opcode.VOICE_STATE_UPDATE
    override fun toJson(): JsonElement
            = json.toJson(UpdateVoiceState.serializer(), this)
}