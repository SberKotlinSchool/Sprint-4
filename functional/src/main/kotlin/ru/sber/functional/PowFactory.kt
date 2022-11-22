package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень,
     * указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return { Math.pow(it.toDouble(), pow.toDouble()).toInt() }
    }
}
