package ru.lunchee.kotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping: $i")
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000)
                println("I've just delayed for 1 sec because I'm not cancellable")
            }
        }
    }

    delay(1300)
    println("main: I'm tired of waiting")
    job.cancelAndJoin()
    println("main: Now I can quit")
}