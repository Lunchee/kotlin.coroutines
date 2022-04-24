package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {
        repeat(3) { index ->
            launch {
                delay((index + 1) * 200L)
                println("Coroutine $index is done")
            }
        }

        println("request: I'm done and I don't explicitly join my children that are still active")
    }

    request.join()
    println("Now the request coroutine is finished")
}