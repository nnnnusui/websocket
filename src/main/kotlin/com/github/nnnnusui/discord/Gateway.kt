package com.github.nnnnusui.discord

import com.github.nnnnusui.discord.command.Identify
import com.github.nnnnusui.discord.command.Resume
import com.github.nnnnusui.discord.event.Payloads
import com.github.nnnnusui.discord.enums.ConnectionStatus
import com.github.nnnnusui.discord.enums.EventType
import com.github.nnnnusui.discord.enums.Opcode
import com.github.nnnnusui.discord.event.Hello
import com.github.nnnnusui.websocket.AbstractGateway
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import java.net.URI
import java.net.http.WebSocket
import java.util.concurrent.CompletionStage
import java.util.concurrent.LinkedBlockingDeque
import java.util.logging.Logger

// @formatter:off
class Gateway(
  private val client  : Client
 ,            endpoint: URI
 ,private val logger  : Logger? = null
): AbstractGateway(endpoint) {

    private var ready = true
    private var sessionId: String?    = null
    private var lastSequence: Int?    = null
    private var heartbeatTaskThread   = Job()
    private var heartbeatAckReceived  = false
        set(value) { if (value) logger?.finer("Received heartbeat ACK"); field = value }

    private val json        = Json()
    private val sendQueue   = LinkedBlockingDeque<Payloads>()
    private val rateLimiter = RateLimiter(100, 60 * 1000)

    override fun packetDispatcher(){ ;println('.')
        while (ready && !rateLimiter.isLimited()) {
            val payloads = sendQueue.take()
            val jsonText = json.stringify(Payloads.serializer(), payloads)
            webSocket.sendText(jsonText, true)
            logger?.fine("Sent: $payloads")
            rateLimiter.increment()
            webSocket.request(1)
        }
        Thread.sleep(100)
    }

    override fun onOpen(webSocket: WebSocket){
        client.status = ConnectionStatus.CONNECTED
        val command = if (sessionId == null) { logger?.info("Authenticating...")
                        Identify(token      = client.token
                                ,properties = Identify.ConnectionProperties("who knows", "who knows", "who knows")
                                ,shard      = arrayOf(client.shard, client.maxShards) )
                      } else                 { logger?.info("Resuming the session...")
                        Resume(  session_id = sessionId!!
                                ,token      = client.token
                                ,seq        = lastSequence!! )
                      }
        queue(command.opcode, command.toJson())
    }
    override fun onClose(webSocket: WebSocket, statusCode: Int, reason: String): CompletionStage<*>? {
        logger?.info("WebSocket closed with code: $statusCode, reason: '$reason'")
        sendQueue.clear()
        Thread.sleep(1000)
        connect()
        return null
    }
    override fun onError(webSocket: WebSocket, error: Throwable) {
        logger?.throwing("${this}: WebSocket exception.", "${this::onError}", error)
        onClose(webSocket, -1, "Error")
    }

    override fun messageHandler(message: String){
        val payloads = Json.parse(Payloads.serializer(), message)
        logger?.fine("Receive: $payloads")

        when(payloads.op){
            Opcode.HEARTBEAT_ACK   -> heartbeatAckReceived = true
            Opcode.HELLO           -> hello(payloads)
            Opcode.DISPATCH        -> dispatch(payloads)
            Opcode.RECONNECT       -> webSocket.sendClose(4001, "Received reconnect request")
            Opcode.INVALID_SESSION -> webSocket.sendClose(4990, "Invalid Session")
            else                   -> logger?.warning("Recived a packet with unknown opcode: ${payloads.op}")
        }
    }
    private fun hello(payloads: Payloads) {
        val hello  = json.fromJson(Hello.serializer(), payloads.d)
        heartbeatAckReceived = true
        heartbeatTaskThread  = launch { heartbeatTask(hello.heartbeatInterval) }
    }
    private suspend fun heartbeatTask(interval: Long){
        while (true) {
            if (heartbeatAckReceived) {
                heartbeatAckReceived = false
                queue(Opcode.HEARTBEAT, JsonPrimitive(lastSequence))
            } else {
                webSocket.sendClose(4000, "Heartbeat ACK wasn't received")
            }
            delay(interval)
        }
    }

    private fun dispatch(payloads: Payloads) {
        lastSequence = payloads.s

        val serializer = EventType.valueOf(payloads.t!!).serializer
        try {
            val gatewayEvent = json.fromJson(serializer, payloads.d)
            client.eventManager.fire(gatewayEvent)
        } catch (exception: Exception){
            exception.stackTrace
            println("payloadType: ${payloads.t}")
        }
    }

    private fun queue(opcode: Opcode, data: JsonElement) {
        val payloads = Payloads( op = opcode
                                ,d  = data   )
        sendQueue.offer(payloads)
    }
}




class RateLimiter(
  private val rate: Int
 ,private val period: Long
){
    private var times = mutableListOf<Long>()

    fun isLimited(): Boolean {synchronized(times){
        times.removeIf { it < System.currentTimeMillis() - period }
        return times.size >= rate
    }}
    fun increment() {synchronized(times){
        times.add(System.currentTimeMillis())
    }}
}