package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
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
        return list.apply { add(value) }.sort()
    }
}

// 4.
class Stack <E> {
    private val stack = mutableListOf<E>()

    fun push(element: E) {
        stack.add(element)
    }

    fun pop() : E {
        return stack.removeFirst()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

}