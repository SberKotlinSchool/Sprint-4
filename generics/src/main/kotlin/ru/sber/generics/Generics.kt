package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T: Comparable<*>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { compareValues(it, elem) > 0 }
}

// 3.
class Sorter<E: Comparable<*>> {
    val list: MutableList<E> = mutableListOf()

    fun add(value: E): Boolean {
        val added = list.add(value)
        if (added) {
            list.sortWith { val1, val2 -> compareValues(val1, val2) }
        }
        return added
    }
}

// 4.
class Stack<E> {

    private val stack = java.util.Stack<E>()

    fun push(value: E): E = stack.push(value)

    fun pop(): E = stack.pop()

    fun isEmpty() = stack.isEmpty()
}