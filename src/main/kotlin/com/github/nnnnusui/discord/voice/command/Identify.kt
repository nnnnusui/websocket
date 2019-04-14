package com.github.nnnnusui.discord.voice.command

import com.github.nnnnusui.discord.command.GatewayCommand
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val json = Json()

@Serializable
data class Identify(
  @SerialName("server_id" ) val serverId:  Snowflake
 ,@SerialName("user_id"   ) val userId:    Snowflake
 ,@SerialName("session_id") val sessionId: String
 ,@SerialName("token"     ) val token:     String
){
    fun toJson()
        = json.toJson(Identify.serializer(), this)
}