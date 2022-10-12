package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int = { it pow pow }

    private infix fun Int.pow(pow: Int): Int {
        var result = 1
        for (i in 1.. pow) {
            result *= this
        }
        return result
    }
}
