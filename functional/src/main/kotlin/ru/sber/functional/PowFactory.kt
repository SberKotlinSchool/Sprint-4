package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int = {
        it.toDouble().pow(pow).toInt()
    }
}


fun main() {
    println(PowFactory.buildPowFunction(2)(3))
}
