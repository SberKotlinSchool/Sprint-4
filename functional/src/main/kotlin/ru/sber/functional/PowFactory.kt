package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int {
        return if(pow == 0){
            {n: Int -> 1}
        } else {n: Int -> n * buildPowFunction(pow - 1)(n)}
    }
}
