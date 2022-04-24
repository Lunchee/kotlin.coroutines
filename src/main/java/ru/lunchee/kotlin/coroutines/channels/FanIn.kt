package ru.lunchee.kotlin.coroutines.channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private suspend fun sendString(string: String, channel: SendChannel<String>, intervalMillis: Long) {
    while (true) {
        channel.send(string)
        delay(intervalMillis)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()
    launch { sendString("Foo", channel, 200) }
    launch { sendString("Bar", channel, 500) }
    repeat(6) { println(channel.receive()) }

    coroutineContext.cancelChildren()
}