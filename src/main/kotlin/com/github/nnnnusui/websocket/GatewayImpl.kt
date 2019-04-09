package com.github.nnnnusui.websocket

import java.net.URI
import java.util.concurrent.LinkedBlockingDeque
import java.util.logging.Logger
import kotlin.concurrent.thread

open class GatewayImpl(
  endpoint: URI
 ,val logger: Logger? = null
): AbstractGateway(endpoint){
    private val sendQueue   = LinkedBlockingDeque<String>()

    init { thread { sendQueue.offer(readLine()) } }

    override fun packetDispatcher() {
        val payloads = sendQueue.take()
        webSocket.sendText(payloads, true)
        logger?.fine("Sent: $payloads")
        webSocket.request(1)
    }
    override fun messageHandler(message: String) { logger?.fine("Recive: $message") }
}