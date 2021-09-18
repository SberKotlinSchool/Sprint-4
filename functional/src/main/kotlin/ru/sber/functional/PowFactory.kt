package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) : (Int) -> Int {

       // return {it.toDouble().pow(pow).toInt()}
        return { numberToPow ->
            var powResult = numberToPow
            repeat(pow-1){powResult *= numberToPow }
            powResult
        }
        //сделал через репит, больше нравится теперь чем 1 вариант
    }
}
