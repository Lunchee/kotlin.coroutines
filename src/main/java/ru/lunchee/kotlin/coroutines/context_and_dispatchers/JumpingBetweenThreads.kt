package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    System.setProperty("kotlinx.coroutines.debug", "on")

    newSingleThreadContext("Context 1").use { contextOne ->
        newSingleThreadContext("Context 2").use { contextTwo ->
            runBlocking(contextOne) {
                log("Started in Context One")

                withContext(contextTwo) {
                    log("Working in Context Two")
                }

                log("Back to Context One")
            }
        }
    }
}