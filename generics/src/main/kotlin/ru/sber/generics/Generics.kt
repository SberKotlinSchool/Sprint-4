package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return if (p1.first != null && p1.second != null
        && p2.first != null && p2.second != null
    ) {
        p1.first == p2.first && p1.second == p2.second
    } else {
        false
    }
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var greater = 0
    anArray.forEach { obj -> if (obj > elem) greater++ }
    return greater
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = ArrayList()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val stack: MutableList<T> = mutableListOf()

    fun push(elem: T) {
        stack.add(elem)
    }

    fun pop(): T? {
        val elem = stack.lastOrNull()
        if (!isEmpty()) {
            stack.removeLast()
        }
        return elem
    }

    fun isEmpty(): Boolean = stack.isEmpty()
}