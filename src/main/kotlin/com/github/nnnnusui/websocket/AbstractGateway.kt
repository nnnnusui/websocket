package com.github.nnnnusui.websocket

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.util.concurrent.CompletionStage

abstract class AbstractGateway(
  val endpoint:        URI
 ,val completionStage: CompletionStage<*>? = null
): CoroutineScope, WebSocket.Listener {
    override val coroutineContext = Dispatchers.Default
    protected val buffer      = StringBuffer()
    var alive = true

    lateinit protected var websocketListener: WebSocket.Listener
    lateinit protected var webSocket:       WebSocket
    val websocketBuilder =  HttpClient
                           .newHttpClient()
                           .newWebSocketBuilder()
    protected fun connect() { webSocket = websocketBuilder
                                         .buildAsync(endpoint, websocketListener)
                                         .join() }

    fun run(listener: WebSocket.Listener = this){
        check(!this::websocketListener.isInitialized) { "${this}: Already running." }
        websocketListener = listener
        connect()
        launch{ while (alive) packetDispatcher() }
    }
    abstract protected fun packetDispatcher()

    override fun onText(webSocket: WebSocket, data: CharSequence, last: Boolean): CompletionStage<*>? {
        buffer.append(data)
        webSocket.request(1)
        if (!last) return completionStage

        val message = buffer.toString()
        buffer.setLength(0)
        messageHandler(message)
        return completionStage
    }
    abstract protected fun messageHandler(message: String)
}