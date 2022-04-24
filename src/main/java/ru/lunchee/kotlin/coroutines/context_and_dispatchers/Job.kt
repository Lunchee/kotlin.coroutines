package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("${coroutineContext[Job]}")
}