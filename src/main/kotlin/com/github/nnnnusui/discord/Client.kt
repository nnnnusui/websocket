package com.github.nnnnusui.discord

import com.github.nnnnusui.discord.enums.ConnectionStatus
import com.github.nnnnusui.discord.enums.Endpoints
import com.github.nnnnusui.discord.rest.REST
import com.github.nnnnusui.websocket.inclusion.CoroutineListener
import com.github.nnnnusui.websocket.inclusion.LoggingListener
import java.net.URI
import java.util.logging.Logger
import com.github.nnnnusui.event.Manager as EventManager

// @formatter:off
class Client(
  val token: String
 ,val shard: Int     = 0
 ,val maxShards: Int = 1
 ,val logger: Logger?=null
){
    var status = ConnectionStatus.DISCONNECTED
//    lateinit var botUser: User

    val restClient = REST(this)
    lateinit var gateway: Gateway
        private set

    val eventManager = EventManager()
    fun connect() {
        check(status == ConnectionStatus.DISCONNECTED) {"status not equal 'DISCONNECTED': $status"}

        status  = ConnectionStatus.CONNECTING
        val endpoint = restClient.request(Endpoints.Gateway.Bot.get()).url
        gateway = Gateway(this, URI.create(endpoint), logger)
        gateway.run(LoggingListener(gateway, logger!!))
        status  = ConnectionStatus.CONNECTED
    }


    fun addHandler(handler: Any)    { eventManager.register(handler) }
}