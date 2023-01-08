package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = { digit: Int -> digit.toDouble().pow(pow.toDouble()).toInt() }

//    fun buildPowFunctionTest(pow: Int): (Double) -> Double {
//        return { digit -> digit.pow(pow.toDouble()) }
//        return fun(digit: Double) = digit.pow(pow.toDouble())
//    }
}
