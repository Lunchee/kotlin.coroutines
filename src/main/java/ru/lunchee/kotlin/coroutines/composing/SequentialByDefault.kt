package ru.lunchee.kotlin.coroutines.composing

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time milliseconds")
}

private  suspend fun doSomethingUsefulOne(): Int {
    delay(1000)
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000)
    return 29
}