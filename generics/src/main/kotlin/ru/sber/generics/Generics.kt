package ru.sber.generics

// 1.
fun <T, S> compare(p1: Pair<T, S>, p2: Pair<T, S>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
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
    private val elements = arrayListOf<T>()

    fun push(element : T) {
        elements.add(element)
    }

    fun pop(): T {
        return elements.removeAt(elements.size - 1)
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    fun get(index: Int): T {
        return elements[index]
    }
}