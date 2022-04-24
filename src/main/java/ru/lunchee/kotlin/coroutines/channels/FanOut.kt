package ru.lunchee.kotlin.coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun CoroutineScope.produceNumbers() = produce<Int> {
    var number = 1
    while (true) {
        send(number++)
        delay(100)
    }
}

private fun CoroutineScope.launchProcessor(id: Int, numbers: ReceiveChannel<Int>) = launch {
    for (number in numbers) println("Processor #$id received $number")
}

fun main() = runBlocking {
    val numbers = produceNumbers()
    repeat(5) { launchProcessor(it, numbers)}
    delay(950)
    numbers.cancel()
}