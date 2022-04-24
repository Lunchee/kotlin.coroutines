package ru.lunchee.kotlin.coroutines.context_and_dispatchers

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class Activity : CoroutineScope {
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job


    fun create() {
        job = Job()
    }

    fun destroy() {
        job.cancel()
    }

    fun doSomething() {
        repeat(10) { index ->
            launch {
                delay((index + 1) * 200L)
                println("Coroutine $index is done")
            }
        }
    }
}

fun main() = runBlocking {
    val activity = Activity()
    activity.create()

    activity.doSomething()
    println("Launched coroutines")
    delay(1000)

    println("Destroying activity")
    activity.destroy()
    delay(1000)
}