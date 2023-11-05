package ru.sber.generics

// 1.
fun <T : Comparable<T>, U : Comparable<U>> compare(p1: Pair<T, U>, p2: Pair<T, U>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
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
    private val items = mutableListOf<T>()

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return items.removeAt(items.size - 1)
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}