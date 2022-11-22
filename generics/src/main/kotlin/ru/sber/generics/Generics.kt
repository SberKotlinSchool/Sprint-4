package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun compare(p1: Pair<*, *>, p2: Pair<*, *>): Boolean {
    return (p1.first == p2.first
            && p1.second == p2.second)
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count {_elem ->
        _elem > elem
    }
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
    private val stack : MutableList<T> = mutableListOf()
    fun pop() : T {
        val first = stack.last()
        stack.removeAt(stack.lastIndex)
        return first
    }

    fun push(element : T) {
        stack.add(element)
    }

    fun isEmpty() : Boolean {
        return stack.isEmpty()
    }
}