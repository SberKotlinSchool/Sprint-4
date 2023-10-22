package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int = 2) = {
        value: Int -> value.toFloat().pow(pow).toInt()
    }


}

fun main() {
    println(PowFactory.buildPowFunction(3)(2))
}
