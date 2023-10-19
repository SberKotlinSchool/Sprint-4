package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int): (Int) -> Int = { it.toDouble().pow(pow).toInt() }

    fun buildPowFunction1(pow: Int): (Int) -> Int {
        val powFun: (Int) -> Int = { arg: Int ->
            arg.toDouble().pow(pow).toInt()
        }
        return powFun;
    }

    fun buildPowFunction2(pow: Int): (Int) -> Int {
        return fun(arg: Int): Int {
            return arg.toDouble().pow(pow).toInt()
        }
    }

    fun buildPowFunction3(pow: Int): (Int) -> Int = { arg -> arg.toDouble().pow(pow).toInt() }

}
