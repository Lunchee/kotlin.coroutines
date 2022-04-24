package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } catch (exception: CancellationException) {
                // No need to do it in usual code. Catch Cancellation Exception only for debugging.
                println("Child caught Cancellation Exception")
            } finally {
                println("Child is cancelled")
            }
        }

        yield()
        child.cancelAndJoin()
        yield()
        println("Parent is not cancelled")
    }

    job.join()
}