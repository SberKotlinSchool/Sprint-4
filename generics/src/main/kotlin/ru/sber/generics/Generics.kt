package ru.sber.generics

import java.util.EmptyStackException

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean = p1.first == p2.first && p1.second == p2.second


// 2.
fun <T> countGreaterThan(anArray: Array<out Comparable<T>>, elem: T): Int = anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
        list
    }
}

// 4.
class Stack<T> {
    private val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.size == 0
    fun pop(): T {
        if (!isEmpty()) return elements.removeAt(elements.size - 1)
        throw EmptyStackException()
    }

    fun push(x: T) {
        elements.add(x)
    }
}