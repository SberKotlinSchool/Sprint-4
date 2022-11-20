package ru.sber.generics

import java.util.*
import kotlin.collections.ArrayDeque

// 1.
fun <L, R> compare(p1: Pair<L, R>, p2: Pair<L, R>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter <T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.apply {
            add(value)
            sort()
        }
    }
}

// 4.
class Stack<E> {

    val elementData: ArrayDeque<E> = ArrayDeque()

    fun push(item: E): E {
        elementData.add(item)
        return item
    }

    fun pop(): E {
        return elementData.removeLast()
    }

    fun isEmpty(): Boolean {
        return elementData.isEmpty()
    }

}