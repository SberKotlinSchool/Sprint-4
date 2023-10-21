package ru.sber.generics

import java.util.*

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <E : Comparable<E>> countGreaterThan(anArray: Array<E>, elem: E): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    val list = mutableListOf<T>()

    fun add(value: T) = list.also { it.add(value) }.also { it.sort() }
}

// 4.
class Stack<T> {
    private var data = LinkedList<T>()

    fun push(value: T) {
        data.add(value)
    }

    fun pop(): T {
        return data.pollLast()
    }

    fun isEmpty(): Boolean {
        return data.size == 0
    }
}