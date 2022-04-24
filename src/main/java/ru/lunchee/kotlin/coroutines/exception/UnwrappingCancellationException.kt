package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import java.io.IOException

// Cancellation exceptions are unwrapped automatically
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught original $exception")
    }

    val job = GlobalScope.launch(handler) {
        val inner = launch {
            launch {
                launch {
                    throw IOException()
                }
            }
        }
        try {
            inner.join()
        } catch (e: CancellationException) {
            println("Rethrowing Cancellation Exception with original cause")
            throw e
        }
    }

    job.join()
}