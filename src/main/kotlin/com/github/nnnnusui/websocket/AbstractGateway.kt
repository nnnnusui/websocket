package com.github.nnnnusui.websocket

import java.net.URI
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.util.concurrent.CompletionStage

abstract class AbstractGateway(
      endpoint:        URI
 ,val completionStage: CompletionStage<*>? = null
): WebSocket.Listener {
    protected val buffer      = StringBuffer()
    var alive = true

    protected val websocketFuture = HttpClient
                                   .newHttpClient()
                                   .newWebSocketBuilder()
                                   .buildAsync(endpoint, this)
    lateinit protected var webSocket: WebSocket
    fun connect() { webSocket = websocketFuture.join() }

    fun run(){
        connect()
        Thread({ while (alive) packetDispatcher() }
              ,"Gateway Packet Dispatcher"  ).start()
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