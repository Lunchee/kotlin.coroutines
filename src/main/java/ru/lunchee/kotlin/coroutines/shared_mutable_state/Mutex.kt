package ru.lunchee.kotlin.coroutines.shared_mutable_state

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// Mutex is used as a synchronization block which does not lock threads
fun main() = runBlocking {
    val mutex = Mutex()
    var counter = 0

    massiveRun(GlobalScope) {
        mutex.withLock {
            counter++
        }
    }

    println("Counter = $counter")
}