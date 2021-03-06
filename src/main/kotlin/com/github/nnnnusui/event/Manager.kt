package com.github.nnnnusui.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingDeque
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.starProjectedType

class Manager: CoroutineScope {
    override val coroutineContext = Dispatchers.Default
    private val queue    = LinkedBlockingDeque<IsEvent>()
    private val handlers = mutableMapOf< Any, Array<KFunction<IsEvent>> >()
    var alive = true

//    init { Thread({ while(alive) dispatchEvent() }, "IsEvent Dispatcher").start() }
    init { launch { while (alive) dispatchEvent() } }
    private fun dispatchEvent() {
        val event = queue.take()
        handlers.forEach{ instance, function ->
            function.filter { it.parameters[1].type == event::class.starProjectedType }
                    .forEach{ it.call(instance, event) }
        }
    }

    fun register(handler: Any){ handlers[handler] = getAnnotatedFunctions(handler) }
    fun fire(isEvent: IsEvent)    { queue.offer(isEvent) }

    @Suppress("UNCHECKED_CAST") // :'(
    private fun getAnnotatedFunctions(instance: Any): Array<KFunction<IsEvent>> // if <out IsEvent>, Be told "Projection is redundant:"
                  = instance::class.members
        /* is function          */ .filter { it is KFunction }
        /* arguments.size == 1  */ .filter { it.parameters.size == 2 }
        /* fun(arg: <T: IsEvent>) */ .filter { (it.parameters[1].type.classifier as KClass<*>).isSubclassOf(IsEvent::class) }
                                   .map    { it as KFunction<IsEvent> }.toTypedArray()
}