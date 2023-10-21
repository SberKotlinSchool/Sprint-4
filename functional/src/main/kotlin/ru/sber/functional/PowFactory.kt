package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (arg: Int) -> Int {
        fun powFunction(arg: Int, pow: Int): Int {
            return when (pow) {
                0 -> 1
                1 -> arg
                else -> arg * powFunction(arg, pow - 1)
            }
        }
        return { arg -> powFunction(arg, pow) }
    }

    // c использованием kotlin.math
//    fun buildPowFunction(pow: Int) = { arg: Int -> arg.toDouble().pow(pow).toInt() }
}
