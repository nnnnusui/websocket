package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.entity.guild.UnavailableGuild
import com.github.nnnnusui.discord.entity.user.User
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

// @formatter:off
@Serializable
data class Ready(
  @SerialName("v"               )           val version:         Int
 ,@SerialName("user"            )           val user:            User
 ,@SerialName("private_channels")           val privateChannels: Array<JsonElement>      // TODO Array<PrivateChannel>
 ,@SerialName("guilds"          )           val guilds:          Array<UnavailableGuild>
 ,@SerialName("session_id"      )           val sessionId:       String
 ,@SerialName("_trace"          )           val _trace:          Array<String>
 ,@SerialName("shard"           ) @Optional val shard:           Array<Int>?             =null

 ,@SerialName("user_settings"   )  val userSettings: JsonElement // diff to doc // Not listed in the docs.
 ,@SerialName("relationships"   ) val relationships: JsonElement // diff to doc // Not listed in the docs.
 ,@SerialName("presences"       ) val presences:     JsonElement // diff to doc // Not listed in the docs.
): IsGatewayEvent