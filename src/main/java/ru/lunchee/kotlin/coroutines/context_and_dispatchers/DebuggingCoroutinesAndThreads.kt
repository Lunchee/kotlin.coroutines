package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.*

fun log(message: String) = println("[${Thread.currentThread().name}] $message")

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")

    runBlocking {
        val firstResult = async {
            log("I'm computing first part")
            6
        }
        val secondResult = async {
            log("I'm computing second part")
            7
        }

        log("The result is ${firstResult.await() + secondResult.await()}")
    }
}