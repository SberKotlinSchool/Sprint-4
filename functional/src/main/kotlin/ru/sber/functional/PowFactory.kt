package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun <T: Number> buildPowFunction(pow: Int): (T) -> T = {
        it.toDouble().pow(pow) as T
    }
}
