package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught ${throwable}")
    }

    val job = GlobalScope.launch(exceptionHandler) {
        launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled by parent until all children terminate")
                    delay(100)
                    println("Children one terminates")
                }
            }
        }

        launch {
            delay(10)
            println("Second child throws an Exception")
            throw ArithmeticException()
        }
    }

    job.join()
    println("Joined job")
}