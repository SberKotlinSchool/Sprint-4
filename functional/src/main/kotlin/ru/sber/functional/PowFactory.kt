package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) : (Int) -> Int {

        return {it.toDouble().pow(pow).toInt()}
//        return {
//            var sum = it
//            for(i  in 1 until pow)
//                sum*=it
//            sum
//        } или второй вариант без приведения типов
    }
}
