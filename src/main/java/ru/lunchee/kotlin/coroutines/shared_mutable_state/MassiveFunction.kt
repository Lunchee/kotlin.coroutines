package ru.lunchee.kotlin.coroutines.shared_mutable_state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun massiveRun(scope: CoroutineScope, action: suspend () -> Unit) {
    val coroutineNumber = 100
    val actionRepeatTimes = 1000

    val time = measureTimeMillis {
        val jobs = List(coroutineNumber) {
            scope.launch {
                repeat(actionRepeatTimes) {
                    action()
                }
            }
        }
        jobs.forEach { it.join() }
    }

    println("Completed ${coroutineNumber * actionRepeatTimes} actions in $time ms")
}