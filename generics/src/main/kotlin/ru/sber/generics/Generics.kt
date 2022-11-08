package ru.sber.generics


// 1.
fun <F, S> compare(p1: Pair<F, S>, p2: Pair<F, S>): Boolean =
    p1 == p2

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int =
    anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        with(list) {
            val position = count { it < value }
            add(position, value)
        }
    }
}

// 4.
class Stack<T> {

    private val elements: MutableList<T> = mutableListOf()

    fun pop(): T? = elements.removeLastOrNull()

    fun push(element: T) {
        elements.add(element)
    }

    fun isEmpty(): Boolean = elements.isEmpty()
}
