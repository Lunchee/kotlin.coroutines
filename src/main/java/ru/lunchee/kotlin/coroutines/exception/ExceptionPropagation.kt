package ru.lunchee.kotlin.coroutines.exception

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

fun main() = runBlocking {
    val job = GlobalScope.launch {
        // `launch` propagates an exception to the parent coroutine immediately
        // But as we have launched it from Global Scope, the exception is merely printed to console
        println("Throwing an exception from launch")
        throw IndexOutOfBoundsException()
    }
    job.join()
    println("The failed job is finished by now")

    // Same for actor
    val sendChannel = GlobalScope.actor<String> {
        for (message in channel) {
            println("actor: '$message'")
            throw RuntimeException()
        }
    }
    sendChannel.send("Throw an exception, plz")
    delay(50) // waiting for actor to complete

    val deferred = GlobalScope.async { // Propagates exception to the parent coroutine if in same scope
        println("Throwing an exception from async")
        throw ArithmeticException()
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (exception: Exception) {
        println("Caught Arithmetic Exception")
    }

    println("Finishing main")
}