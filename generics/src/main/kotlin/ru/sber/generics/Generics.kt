package ru.sber.generics

import java.util.*

// 1.
fun <K,V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1 == p2
}

// 2.
fun <E : Comparable<E>> countGreaterThan(anArray: Array<E>, elem: E): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<E: Comparable<E>> {
    val list: MutableList<E> = mutableListOf()

    fun add(value: E) {
        list.add(value).apply { list.sort() }
    }
}

// 4.
class Stack<E> {
    private val list: MutableList<E> = mutableListOf()

    fun push(value: E) {
        list.add(value)
    }

    fun pop(): E? {
        return list.removeLastOrNull()
    }

    fun isEmpty() = list.isEmpty()
}