package ru.sber.functional

import java.awt.geom.QuadCurve2D
import kotlin.math.pow
object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
//    fun buildPowFunction(pow: Int) = { value: Int -> value.toDouble().pow(pow) }

    fun buildPowFunction(pow: Int) = { value: Int -> when{
        ( pow > 0 ) -> IntRange(1, pow ).fold(1.toDouble()) { a, b -> a * value }
        ( pow < 0 ) -> IntRange( pow + 1 , 0 ).fold(1.toDouble()) { a, b -> a / value }
        else -> 1.0
    } }

    fun buildPowFunctionReqursive(pow: Int): (Int) -> Double{

        var localPow = pow

        fun myPow(value: Int): Double{
            if (localPow > 0 ) {
                localPow -= 1
                return value * myPow(value)
            }
            else if (localPow < 0 ) {
                    localPow += 1
                    return 1.0 / ( value / myPow(value) )
            }
            else return 1.0

        }

        return ::myPow
    }
}
fun main(){

    println(PowFactory.buildPowFunction(-2)(4))
}
