package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = { n: Number -> n.toDouble().pow(pow) }
}

fun main() {
    val powFunc = PowFactory.buildPowFunction(10)

    println(powFunc(2L))
    println(powFunc(3))
    println(PowFactory.buildPowFunction(2)(3))
}