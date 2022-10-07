package ru.sber.functional

import ru.sber.functional.PowFactory.buildPowFunction

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = { it: Number -> Math.pow(it.toDouble(), pow.toDouble()).toInt() }
}

fun main() {
    println(buildPowFunction(5).invoke(2))
}
