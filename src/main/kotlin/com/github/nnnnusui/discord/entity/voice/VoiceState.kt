package com.github.nnnnusui.discord.entity.voice

import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class VoiceState(
  @SerialName("guild_id"  ) @Optional val guildId:    Snowflake?    =null
  /* Not listed in the docs.                       */
 ,@SerialName("self_video")           val selfVideo:  Boolean
  /* --------------------------------------------- */
 ,@SerialName("channel_id")           val channelId:  Snowflake?
 ,@SerialName("user_id"   )           val userId:     Snowflake
 ,@SerialName("member"    ) @Optional val member:     Guild.Member? =null
 ,@SerialName("session_id")           val sessionId:  String
 ,@SerialName("deaf"      )           val isDeaf:     Boolean
 ,@SerialName("mute"      )           val isMute:     Boolean
 ,@SerialName("self_deaf" )           val isSelfDeaf: Boolean
 ,@SerialName("self_mute" )           val isSelfMute: Boolean
 ,@SerialName("suppress"  )           val isSuppress: Boolean
)