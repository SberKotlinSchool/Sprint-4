package ru.sber.generics


// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1 == p2
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter { it > elem }.size
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value).apply { list.sort() }
    }
}

// 4.
class Stack<T> {
    private val list: MutableList<T> = mutableListOf()
    var size = 0
        private set

    fun isEmpty() = size == 0

    fun pop() = list[0].apply {
        list.removeAt(0)
        size--
    }

    fun push(value: T) = list.add(0, value).apply {
        size++
    }
}