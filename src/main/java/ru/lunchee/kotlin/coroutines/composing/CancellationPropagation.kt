package ru.lunchee.kotlin.coroutines.composing

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    try {
        println("Result is ${failedConcurrentFun()}")
    } catch (exception: Exception) {
        println("Computation failed: $exception")
    }
}

suspend fun failedConcurrentFun(): Int = coroutineScope {
    val one = async {
        try {
            delay(Long.MAX_VALUE)
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }

    one.await() + two.await()
}