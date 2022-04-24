package ru.lunchee.kotlin.coroutines.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("I'm sleeping: $i")
            delay(500)
        }
    }

    delay(1300)

    println("I'm tired of waiting")
    job.cancel()
    job.join()
    println("main: Now I can quit")
}