package ru.sber.generics

// 1.
fun <A, B> compare(pair1: Pair<A, B>, pair2: Pair<A, B>) = pair1 == pair2


// 2.
fun <T : Comparable<T>> countGreaterThan(array: Array<T>, element: T) = array.filter { it > element }.size


// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) = list.add(value).apply { list.sort() }
}


// 4.
class Stack<T> {
    private val list: MutableList<T> = mutableListOf()
    var size = 0
        private set

    fun isEmpty() = size == 0
    fun pop() = list[0].apply {
        list.removeAt(0)
        size -= 1
    }

    fun push(value: T) = list.add(0, value).apply {
        size += 1
    }
}