package ru.sber.generics

import java.util.*
import kotlin.collections.ArrayDeque


// 1.
fun <T, U> compare(p1: Pair<T, U>, p2: Pair<T, U>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<*>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { compareValues(it, elem) > 0 }
}

// 3.
class Sorter<T : Comparable<T>> {
    var list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        Collections.sort(list)
    }
}

// 4.
class Stack<T> {
    val stack = ArrayDeque<T>()

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun push(s: T) {
        stack.addLast(s)
    }

    fun pop(): T? {
        return stack.removeLastOrNull()
    }
}
