package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {
        GlobalScope.launch {
            println("Job 1: I run in Global Scope and execute independently.")
            delay(1000)
            println("Job 1: I was not affected by cancellation of the request")
        }

        launch {
            delay(100)
            println("Job 2: I am a child of the request coroutine")
            delay(1000)
            println("I will not execute this line if my parent coroutine is cancelled")
        }
    }

    delay(500)
    request.cancel()
    delay(1000)
    println("main: Who has survived request cancellation?")
}