package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (arg: Int) -> Int {
        fun powf(arg1: Int, pow: Int): Int {
            return if (pow == 1) arg1
            else if (pow % 2 == 1) powf(arg1 * arg1, pow - 1)
            else powf(arg1, pow / 2).let {
                it * it
            }
        }
        return { arg ->
            powf(arg, pow)
        }
    }
}
