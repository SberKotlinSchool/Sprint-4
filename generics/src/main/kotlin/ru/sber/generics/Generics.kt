package ru.sber.generics


// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
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
        val index = list.binarySearch(value)
        val insertionIndex = if (index >= 0) index else -(index + 1)
        list.add(insertionIndex, value)
    }
}

// 4.
class Stack<T> {

    private val list = mutableListOf<T>()

    fun push(element: T) {
        list.add(element)
    }

    fun pop(): T {
        return try {
            list.removeLast()
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Stack is empty.")
        }
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}