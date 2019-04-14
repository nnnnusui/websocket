package com.github.nnnnusui.discord.command

import com.github.nnnnusui.discord.enums.Opcode
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

val json = Json()

// @formatter:off
interface GatewayCommand {
    val opcode: Opcode
    fun toJson(): JsonElement
}

@Serializable
data class Identify(
  val token: String
 ,val properties: ConnectionProperties
 ,@Optional val compress: Boolean = false
 ,@Optional val large_threshold: Int = 50
 ,@Optional val shard: Array<Int> = arrayOf<Int>()
 ,@Optional val presence: UpdateStatus? = null
): GatewayCommand{
    override val opcode = Opcode.IDENTIFY
    override fun toJson(): JsonElement
        = json.toJson(Identify.serializer(), this)

    @Serializable
    data class ConnectionProperties(
      val os: String
     ,val browser: String
     ,val device: String
    )
    enum class UpdateStatus{
        ONLINE
       ,DND         // Do Not Disturb
       ,IDLE        // AFK
       ,INVISIBLE   // Invisible and shown as offline
       ,OFFLINE
    }
}

@Serializable
data class Resume(
  val token: String
 ,val session_id: String
 ,val seq: Int
): GatewayCommand {
    override val opcode = Opcode.RESUME
    override fun toJson(): JsonElement
            = json.toJson(Resume.serializer(), this)
}