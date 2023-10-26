package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = { num: Int -> num.pow(pow) }

    fun Int.pow(to: Int) = this.toDouble().pow(to.toDouble())

}
