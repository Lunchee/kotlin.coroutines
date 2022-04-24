package ru.lunchee.kotlin.coroutines.shared_mutable_state

import kotlinx.coroutines.*

// Data is changed in the same thread
fun main() = runBlocking {
    var counter = 0
    massiveRun(CoroutineScope(newSingleThreadContext("CounterContext"))) { counter++ }
    println("Counter = $counter")
}