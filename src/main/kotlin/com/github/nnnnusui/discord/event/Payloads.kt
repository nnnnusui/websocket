package com.github.nnnnusui.discord.event

import com.github.nnnnusui.discord.enums.Opcode
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

// @formatter:off
@Serializable
data class Payloads(
  @SerialName("t" ) @Optional val t:  String? = null
 ,@SerialName("s" ) @Optional val s:  Int?    = null
 ,@SerialName("op")           val op: Opcode
 ,@SerialName("d" )           val d:  JsonElement
)