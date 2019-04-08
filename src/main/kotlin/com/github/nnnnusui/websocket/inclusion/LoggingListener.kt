package com.github.nnnnusui.websocket.inclusion

import java.net.http.WebSocket
import java.nio.ByteBuffer
import java.util.concurrent.CompletionStage
import java.util.logging.Logger

class LoggingListener(
  private val listener: WebSocket.Listener
 ,private val logger: Logger
): WebSocket.Listener{

    override fun onOpen(webSocket: WebSocket) {
        logger.info("Connected to the gateway.")
        listener.onOpen(webSocket)
    }
    override fun onClose(webSocket: WebSocket, statusCode: Int, reason: String?): CompletionStage<*>? {
        logger.info("WebSocket closed with code: $statusCode, reason: '$reason'")
        return listener.onClose(webSocket, statusCode, reason ?: "")
    }
    override fun onError(webSocket: WebSocket, error: Throwable) {
        logger.throwing("Websocket exception: $webSocket", "${this::class.simpleName}#onError()", error)
        listener.onError(webSocket, error)
    }

    override fun onText(webSocket: WebSocket, rawText: CharSequence, last: Boolean): CompletionStage<*>? {
        return listener.onText(webSocket, rawText, last)
    }
    override fun onBinary(webSocket: WebSocket, data: ByteBuffer, last: Boolean): CompletionStage<*>? {
        return listener.onBinary(webSocket, data, last)
    }
    override fun onPing(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*>? {
        return listener.onPing(webSocket, message)
    }
    override fun onPong(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*>? {
        return listener.onPong(webSocket, message)
    }
}