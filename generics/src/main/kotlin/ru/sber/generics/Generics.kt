package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>) = p1 == p2

// 2.
fun <T: Comparable<T>>countGreaterThan(anArray: Array<T>, elem: T) = anArray.count { it > elem }

// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    val data = mutableListOf<T>()

    fun push(value: T) = data.add(value)

    fun pop() = data.removeLastOrNull()

    fun isEmpty() = data.isEmpty()
}