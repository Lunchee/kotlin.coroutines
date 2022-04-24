package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import java.io.IOException
import kotlin.ArithmeticException
import kotlin.Long.Companion.MAX_VALUE

// The first exception wins, other get suppressed
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception with suppressed ${exception.suppressed?.contentToString()}")
    }

    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(MAX_VALUE)
            } finally {
                throw ArithmeticException()
            }
        }
        launch {
            delay(100)
            throw IOException()
        }
        delay(MAX_VALUE)
    }

    job.join()
}