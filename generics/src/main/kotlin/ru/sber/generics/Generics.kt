package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*
import kotlin.collections.ArrayList

fun<K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return ((p1.first == p2.first) && (p1.second == p2.second))
}

fun<T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count {it > elem}
}

class Sorter<T: Comparable<T>> {
    private var list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

class Stack<T> {
    private val list = mutableListOf<T>()

    fun push(element: T) {
        list.add(element)
    }

    fun pop(): T {
        return list.removeLast()
    }

    fun isEmpty() = list.isEmpty()
}