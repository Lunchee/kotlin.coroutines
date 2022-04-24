package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import kotlin.Long.Companion.MAX_VALUE

// supervisorScope can be used instead of coroutineScope for same purposes
fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("Child is sleeping")
                    delay(MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }

            yield()
            println("Throwing exception from scope")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        println("Caught assertion error") // But cancellation is not propagated here because of supervisor scope
    }
}