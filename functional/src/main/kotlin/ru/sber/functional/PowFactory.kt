package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return { result -> result.toDouble().pow(pow.toDouble()).toInt() }
    }
}

fun main() {
    val functionSquare= PowFactory.buildPowFunction(2)
    val number = 5
    println(functionSquare(number))

    val functionCube = PowFactory.buildPowFunction(3)
    println(functionCube(number))
}