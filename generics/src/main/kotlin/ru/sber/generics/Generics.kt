package ru.sber.generics

import java.util.*

// 1.
fun <F, S> compare(p1: Pair<F, S>, p2: Pair<F, S>) =
    p1.first == p2.first && p1.second == p2.second

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    var list = mutableListOf<T>()

    fun add(value: T) {
        list.let {
            it.add(value)
            it.sort()
        }
    }
}

// 4.
class Stack<T> {

    private val stack = LinkedList<T>()

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun push(elem: T) {
        stack.push(elem)
    }

    fun pop(): T {
        return stack.pollFirst()
    }

    fun clear() {
        stack.clear()
    }

}