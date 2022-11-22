package ru.sber.generics

import java.util.*

// 1.
fun <E1, E2> compare(p1: Pair<E1, E2>, p2: Pair<E1, E2>): Boolean {
    return Objects.equals(p1.first, p2.first) && Objects.equals(p1.second, p2.second)
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    val list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {

    private var stack = mutableListOf<T>()

    fun push(element: T) {
        stack.add(element)
    }

    fun pop(): T {
        val last = stack.last()
        stack.removeLast()
        return last
    }

    fun isEmpty(): Boolean = stack.isEmpty()
}