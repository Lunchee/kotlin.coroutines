package ru.lunchee.kotlin.coroutines.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val result = withTimeoutOrNull(1300) {
        repeat(1000) { i ->
            println("I'm sleeping: $i")
            delay(500)
        }
    }
    println("$result")

    withTimeout(1300) {
        repeat(1000) { i ->
            println("I'm sleeping: $i")
            delay(500)
        }
    }
}