package ru.lunchee.kotlin.coroutines.cancellation

import kotlinx.coroutines.*

fun main() = runBlocking {
    notCooperativeCoroutine(this)
    cooperativeCoroutineWithStatusCheck(this)
    cooperativeCoroutiveWithYield(this)
}

suspend fun notCooperativeCoroutine(coroutineScope: CoroutineScope) {
    val startTime = System.currentTimeMillis()

    val job = coroutineScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var sleptTimes = 0

        while (sleptTimes < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping: ${sleptTimes++}")
                nextPrintTime += 500
            }
        }
    }

    delay(1300)
    println("I'm tired of waiting")
    job.cancelAndJoin()
    println("Now I can quit")
}

suspend fun cooperativeCoroutineWithStatusCheck(coroutineScope: CoroutineScope) {
    val startTime = System.currentTimeMillis()

    val job = coroutineScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var sleptTimes = 0

        while (isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping: ${sleptTimes++}")
                nextPrintTime += 500
            }
        }
    }

    delay(1300)
    println("I'm tired of waiting")
    job.cancelAndJoin()
    println("Now I can quit")
}

suspend fun cooperativeCoroutiveWithYield(coroutineScope: CoroutineScope) {
    val startTime = System.currentTimeMillis()

    val job = coroutineScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var sleptTimes = 0

        while (sleptTimes < 5) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("I'm sleeping: ${sleptTimes++}")
                nextPrintTime += 500
            }
            yield()
        }
    }

    delay(1300)
    println("I'm tired of waiting")
    job.cancelAndJoin()
    println("Now I can quit")
}