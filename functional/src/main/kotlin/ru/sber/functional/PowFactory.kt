package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */

    infix fun Int.a(arg: Int): Double {
        return this.toDouble().pow(arg)
    }

    fun buildPowFunction(pow: Int): (Int) -> Int {

        return { param -> param.toDouble().pow(pow).toInt() }
    }
}
