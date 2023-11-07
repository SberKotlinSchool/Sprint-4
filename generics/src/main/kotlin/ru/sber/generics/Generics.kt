package ru.sber.generics

// 1.
fun <T, E> compare(p1: Pair<T, E>, p2: Pair<T, E>): Boolean = p1 == p2

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.count { it > elem }


// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val list: MutableList<T> = mutableListOf()

    fun push(element: T) = list.add(element)

    fun pop() = list.removeLast()

    fun isEmpty() = list.isEmpty()
}