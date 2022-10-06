package ru.sber.generics

import java.util.*
import kotlin.collections.ArrayList

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1.first==p2.first && p1.second==p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { member -> member.compareTo(elem) > 0 }
}

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
    val innerStack = LinkedList<T>()
    fun push(elem : T) = innerStack.push(elem)

    fun pop() : T = innerStack.pop()

    fun isEmpty() = innerStack.isEmpty()
}