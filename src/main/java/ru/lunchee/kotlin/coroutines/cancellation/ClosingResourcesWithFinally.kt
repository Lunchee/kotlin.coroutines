package ru.lunchee.kotlin.coroutines.cancellation

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping: $i")
                delay(500)
            }
        } finally {
            println("I'm running finally")
        }
    }

    delay(1300)
    println("main: I'm tired of waiting")
    job.cancelAndJoin()
    println("main: Now I can quit")
}