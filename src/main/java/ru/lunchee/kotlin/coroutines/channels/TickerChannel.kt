package ru.lunchee.kotlin.coroutines.channels

import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val ticker = ticker(delayMillis = 100, initialDelayMillis = 0)

    var nextElement = withTimeoutOrNull(1) { ticker.receive() }
    println("Initial element is available immediately: $nextElement")

    nextElement = withTimeoutOrNull(50) { ticker.receive() }
    println("Next element is not ready in 50ms: $nextElement")

    nextElement = withTimeoutOrNull(60) { ticker.receive() }
    println("Next element is ready in 100ms: $nextElement")

    println("Consumer pauses for 210ms")
    delay(210)

    nextElement = withTimeoutOrNull(1) { ticker.receive() }
    println("Next element is available immediately after large consumer delay: $nextElement")

    nextElement = withTimeoutOrNull(50) { ticker.receive() }
    println("Next element is not ready in 50ms: $nextElement")

    nextElement = withTimeoutOrNull(50) { ticker.receive() }
    println("Next element is ready in 90ms after consumer pause in 210ms: $nextElement")

    ticker.cancel()
}