package ru.sber.generics

import kotlin.collections.ArrayDeque

// 1.
fun <T> compare(p1: T, p2: T): Boolean {
    return p1 == p2
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter { it > elem }.size
}

// 3.
class Sorter<T: Any>(vararg value: T){
    val list: MutableList<T> = mutableListOf()

    init {
        list.addAll(value)
    }

    fun add(value: T) {
        list.add(value)
        list.sortBy { it.hashCode() }
    }
}

// 4.
class Stack<T : Any>(vararg value: T) {

    private val stack: ArrayDeque<T> = ArrayDeque()

    init {
        stack.addAll(value)
    }
    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun pop(): T {
        return stack.removeLast()
    }

    fun push(value: T) {
        stack.add(value)
    }

}