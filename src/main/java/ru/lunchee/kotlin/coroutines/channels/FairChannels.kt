package ru.lunchee.kotlin.coroutines.channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Ball(var hits: Int = 0)

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hits++
        println("$name $ball")
        delay(300)
        table.send(ball)
    }
}

fun main() = runBlocking {
    val table = Channel<Ball>()
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball())

    delay(2000)
    coroutineContext.cancelChildren()
}