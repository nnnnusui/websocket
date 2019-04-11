package com.github.nnnnusui.discord.rest

import com.github.nnnnusui.discord.Client
import com.github.nnnnusui.discord.enums.Endpoints
import com.github.nnnnusui.discord.rest.entity.GetGatewayBot
import com.github.nnnnusui.http.enums.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class REST(private val client: Client){
    val httpClient = HttpClient.newHttpClient()

    fun request(endpoint: Endpoints.Endpoint, data: JsonObject?= null): GetGatewayBot{
        val request = HttpRequest.newBuilder().apply {
                uri(URI.create(endpoint.url))
                    header("Accept", "application/json")
                    header("Authorization", client.token)
                    header("UserAgent", "xxx.dev")
                if (endpoint.method == HttpMethod.GET) {
                    if (data != null)
                        uri(URI.create("${endpoint.url}?${data.entries.joinToString("&") { "${it.key}=${it.value}" }}"))
                    GET()
                    return@apply
                } else {
                    HttpRequest.BodyPublishers.ofString(data.toString())
                }
            }.build()
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return Json.parse(GetGatewayBot.serializer(), response.body())
    }
}