package com.github.nnnnusui.discord.voice

import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.event.VoiceServerUpdate
import com.github.nnnnusui.discord.voice.command.Identify
import com.github.nnnnusui.websocket.AbstractGateway
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import java.net.URI
import java.net.http.WebSocket
import java.util.concurrent.LinkedBlockingDeque
import java.util.logging.Logger

class Gateway(
  private val connectionData: VoiceServerUpdate
 ,private val logger  : Logger? = null
): AbstractGateway(URI.create("ws://${connectionData.endpoint}")){
    private val sendQueue   = LinkedBlockingDeque<String>()
    private val json        = Json()
    override fun packetDispatcher() {
        while (true) {
            val payloads = sendQueue.take()
            webSocket.sendText(payloads, true)
            logger?.fine("Sent: $payloads")
            webSocket.request(1)
        }
    }

    override fun onOpen(webSocket: WebSocket) { println("isOpen")
        queue(
            Opcode.IDENTIFY
           ,Identify(
                serverId  = connectionData.guildId
                ,userId    = Snowflake("")
                ,sessionId = "???"
                ,token     = connectionData.token
            ).toJson()
        )
    }
    override fun messageHandler(message: String) {
        val payload = Json.parse(Payload.serializer(), message)
        println(payload)
        when(payload.op){
            Opcode.READY -> println("READY!")
        }
    }

    private fun queue(opcode: Opcode, data: JsonElement) {
        val payloads = Payload( op = opcode
                               ,d  = data   )
        sendQueue.offer(Json.stringify(Payload.serializer(), payloads))
    }
}
@Serializable
data class Payload(
  val op:   Opcode
 ,val d: JsonElement
)