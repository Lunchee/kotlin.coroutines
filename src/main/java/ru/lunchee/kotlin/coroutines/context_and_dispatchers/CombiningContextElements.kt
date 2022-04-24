package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")

    runBlocking {
        launch(Dispatchers.Default + CoroutineName("My Coroutine")) {
            println("I'm working in ${Thread.currentThread().name}")
        }
    }
}