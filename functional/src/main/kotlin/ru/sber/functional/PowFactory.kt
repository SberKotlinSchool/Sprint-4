package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int =
        { pow(it, pow) }

    private tailrec fun pow(x: Int, y: Int): Int {
        require(y >= 0) { "Возведение возможно только в положительную степень" }
        return if (y == 0)
            1
        else
            x * pow(x, y - 1)
    }
}
