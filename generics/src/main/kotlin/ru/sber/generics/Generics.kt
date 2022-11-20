package ru.sber.generics

import java.util.*

// 1.
fun compare(p1: Pair<*, *>, p2: Pair<*, *>) = Objects.equals(p1.first, p2.first) && Objects.equals(p1.second, p2.second)

// 2.
fun <E : Comparable<E>> countGreaterThan(anArray: Array<E>, elem: E) = anArray.count { it > elem }

// 3.
class Sorter<E: Comparable<E>> {
    val list = mutableListOf<E>()

    fun add(value: E) {
        for (i in 0 .. list.lastIndex) {
            if (value < list[i]) {
                list.add(i, value)
                return
            }
        }
        list.add(value)
    }
}

// 4.
class Stack<E> {

    val list: MutableList<E> = LinkedList()

    fun push(item: E): E {
        list.add(item)
        return item
    }

    fun pop(): E {
        return list.removeLast()
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}