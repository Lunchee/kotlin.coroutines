package ru.lunchee.kotlin.coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    var primeNumbers = generateNumbersFrom(2)
    for (numberIndex in 1..10) {
        val primeNumber = primeNumbers.receive()
        println(primeNumber)
        primeNumbers = filter(primeNumbers, primeNumber)
    }

    coroutineContext.cancelChildren()
}

fun CoroutineScope.generateNumbersFrom(start: Int) = produce {
    var number = start
    while (true) send(number++)
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, primeNumber: Int) = produce {
    for (number in numbers) if (number % primeNumber != 0) send(number)
}