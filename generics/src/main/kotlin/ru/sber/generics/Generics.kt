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
        list
            .apply{add(value)}
            .sort()
    }
}

// 4.
class Stack <T> {
    private val data: ArrayDeque<T> = ArrayDeque()

    fun isEmpty(): Boolean {
        return data.isEmpty()
    }

    fun push(i: T) {
        data.addFirst(i)
    }

    fun pop(): T {
        return data.removeFirst()
    }

}