package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) = { number: Int ->
        var result = 1
        repeat(pow) {
            result *= number
        }
        result
    }
}
