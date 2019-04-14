package com.github.nnnnusui.websocket

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