package ru.sber.functional

import kotlin.math.*

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = {
        number: Int -> number.toDouble().pow(pow).toInt()
    }
}
