package ru.lunchee.kotlin.coroutines.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch { printCoroutines() }
    println("Hello, ")
}

suspend fun printCoroutines() {
    delay(1000)
    println("coroutines")
}