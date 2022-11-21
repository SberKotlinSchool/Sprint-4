package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) : (Double) -> Double {
        return { arg: Double -> arg.pow(pow.toDouble()) }
    }
}
