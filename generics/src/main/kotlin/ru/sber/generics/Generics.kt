package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1 == p2
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

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
    private val stack = mutableListOf<T>()

    fun push(element: T) {
        stack.add(element)
    }

    fun pop(): T? {
        if (stack.isEmpty()) return null
        val element = stack.last()
        stack.removeLast()
        return element
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}