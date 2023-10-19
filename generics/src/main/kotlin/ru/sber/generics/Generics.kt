package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <A,B> compare(p1: Pair<A,B>, p2: Pair<A,B>): Boolean =
    p1.first == p2.first && p1.second == p2.second

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int =
    anArray.count { value -> value > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = ArrayList()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val stack = ArrayList<T>()

    fun push(element: T) = stack.add(element)
    fun pop(): T {
        val last = stack.last()
        stack.remove(last)
        return last
    }
    fun isEmpty() = stack.isEmpty()
}