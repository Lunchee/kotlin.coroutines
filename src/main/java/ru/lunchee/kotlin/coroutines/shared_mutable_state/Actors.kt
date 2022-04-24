package ru.lunchee.kotlin.coroutines.shared_mutable_state

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

sealed class CounterMessage
object IncreaseCounter : CounterMessage()
class GetCounter(val response: CompletableDeferred<Int>) : CounterMessage()

private fun CoroutineScope.counterActor() = actor<CounterMessage> {
    var counter = 0

    for (message in channel) {
        when (message) {
            is IncreaseCounter -> counter++
            is GetCounter -> message.response.complete(counter)
        }
    }
}

fun main() = runBlocking {
    val counterActor = counterActor()
    massiveRun(GlobalScope) {
        counterActor.send(IncreaseCounter)
    }

    val counterResponse = CompletableDeferred<Int>()
    counterActor.send(GetCounter(counterResponse))
    println("Counter = ${counterResponse.await()}")

    counterActor.close()
    return@runBlocking
}