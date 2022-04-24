package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("Unconfined, before delay: ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined, after delay: ${Thread.currentThread().name}")
    }

    launch {
        println("Confined to main, before delay: ${Thread.currentThread().name}")
        delay(500)
        println("Confined to main, after delay: ${Thread.currentThread().name}")
    }
}