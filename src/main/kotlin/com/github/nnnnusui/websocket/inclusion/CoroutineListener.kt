package com.github.nnnnusui.websocket.inclusion

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.http.WebSocket
import java.nio.ByteBuffer
import java.util.concurrent.CompletionStage

class CoroutineListener(
  private val listener: WebSocket.Listener
): CoroutineScope, WebSocket.Listener{
    override val coroutineContext = Dispatchers.Default // do not understand :'(

    override fun onOpen(webSocket: WebSocket) {launch{
        listener.onOpen(webSocket)
    }}
    override fun onClose(webSocket: WebSocket, statusCode: Int, reason: String?): CompletionStage<*>? {launch{
        listener.onClose(webSocket, statusCode, reason ?: "")
    };return null}

    override fun onText(webSocket: WebSocket, rawText: CharSequence, last: Boolean): CompletionStage<*>? {launch{
        listener.onText(webSocket, rawText, last)
    };return null}
    override fun onBinary(webSocket: WebSocket, data: ByteBuffer, last: Boolean): CompletionStage<*>? {launch{
        listener.onBinary(webSocket, data, last)
    };return null}
    override fun onError(webSocket: WebSocket, error: Throwable) {launch{
        listener.onError(webSocket, error)
    }}
    override fun onPing(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*>? {launch{
        listener.onPing(webSocket, message)
    };return null}
    override fun onPong(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*>? {launch{
        listener.onPong(webSocket, message)
    };return null}
}