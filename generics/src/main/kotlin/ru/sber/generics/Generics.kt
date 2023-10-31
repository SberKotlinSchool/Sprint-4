package ru.sber.generics

import java.util.LinkedList

fun <R,T> compare(p1: Pair<R,T>, p2: Pair<R,T>) = p1.first == p2.first && p1.second == p2.second

fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

class Sorter<T : Comparable<T>> {
    val list = mutableListOf<T>()

    fun add(value: T) {
        list.let {
            it.add(value)
            it.sort()
        }
    }
}

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