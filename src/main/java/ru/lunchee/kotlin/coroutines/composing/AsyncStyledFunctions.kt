package ru.lunchee.kotlin.coroutines.composing

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        println("Result is ${concurrentSum()}")
    }

    println("Completed in $time milliseconds")
}

private suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }

    one.await() + two.await()
}

private  suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000)
    return 29
}