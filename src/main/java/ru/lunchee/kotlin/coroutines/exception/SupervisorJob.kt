package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import kotlin.Long.Companion.MAX_VALUE

// In Supervision Job exceptions are propagated only downwards.
fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val firstChild = launch {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }
        val secondChild = launch {
            firstChild.join()
            println("First child is cancelled, but second one is still active")
            try {
                delay(MAX_VALUE)
            } finally {
                println("Second child is cancelled because supervisor is cancelled")
            }
        }

        firstChild.join()
        println("Cancelling supervisor")
        supervisor.cancelAndJoin()
    }
}