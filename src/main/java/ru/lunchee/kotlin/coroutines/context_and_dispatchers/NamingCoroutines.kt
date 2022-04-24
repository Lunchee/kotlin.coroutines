package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")

    runBlocking {
        log("Started main coroutine")

        val valueOne = async(CoroutineName("Value One")) {
            delay(500)
            log("Computing Value One")
            252
        }

        val valueTwo = async(CoroutineName("Value Two")) {
            delay(1000)
            log("Computing Value Two")
            6
        }

        log("The answer is ${valueOne.await() / valueTwo.await()}")
    }
}