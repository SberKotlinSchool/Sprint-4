package ru.sber.generics

// 1.
fun <T : Comparable<T>, T2 : Comparable<T2>> compare(p1: Pair<T, T2>, p2: Pair<T, T2>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter {
        it < elem
    }.size
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
    private val list: MutableList<T> = mutableListOf()
    fun isEmpty(): Boolean = list.size == 0
    fun push(v: T) {
        list.add(v)
    }
    fun pop(): T = list.removeAt(list.size - 1)
}
