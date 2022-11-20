package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Any) -> Long = {
        val x = when (it) {
            is String -> it.toDouble()
            is Char -> it.toString().toDouble()
            is Number -> it.toDouble()
            else -> throw UnsupportedOperationException("Неподдерживаемый формат")
        }
        x.pow(pow).toLong()
    }

}
