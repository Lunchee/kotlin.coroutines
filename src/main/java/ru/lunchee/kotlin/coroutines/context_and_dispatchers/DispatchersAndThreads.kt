package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
        println("parent context (main runBlocking): ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Default: ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("myThread")) {
        println("newSingleThreadContext: ${Thread.currentThread().name}")
    }
}