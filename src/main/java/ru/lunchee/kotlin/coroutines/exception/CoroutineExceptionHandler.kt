package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    val job = GlobalScope.launch(handler) {
        throw AssertionError("From launch")
    }
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException("From async") // Nothing will be printed until await() is called
    }

    joinAll(job, deferred)
}