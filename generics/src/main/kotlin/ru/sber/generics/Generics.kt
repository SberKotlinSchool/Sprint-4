package ru.sber.generics

import java.util.*

/**
 * Реализовать обобщенную функцию compare,
 * которая сравнивала бы два объекта класса Pair p1 и p2 и возвращала значение Boolean.
 */
fun <A,B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    if (p1 === p2) return true
    if (p1.first != p2.first) return false
    if (p1.second != p2.second) return false
    return true
}

/**
 * Реализовать обобщенную функцию, чтобы найти количество элементов в общем массиве, которое больше,
 * чем определенный элемент. int countGreaterThan принимает на вход массив и элемент,
 * с которым нужно сравнить все остальные элементы массива.
 */
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem}
}

/**
 * Реализовать обобщенный класс Sorter с параметром Т и подходящим ограничением,
 * который имеет свойство list:MutableList и функцию fun add(value:T).
 * С каждым вызовом функции передаваемое значение должно добавляться в список
 * и список должен оставаться в отсортированном виде.
 */
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

/**
 * Написать обобщенный стек. Минимально - реализовать функции вставки, извлечения и проверки на пустоту.
 */
class Stack<E> {

    private val stack = LinkedList<E>()

    fun push(el: E) {
        stack.push(el)
    }

    fun poll(): E {
        return stack.poll()
        stack.pop()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

}