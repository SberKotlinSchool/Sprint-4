package ru.sber.generics

import kotlin.collections.ArrayList

// 1.
fun <K,V> compare(p1: Pair<K,V>, p2: Pair<K,V>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}


// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var counter: Int = 0

    anArray.forEach {
        if (it > elem)
            counter++
    }

    return counter
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
class Stack<E> (val capacity: Int) {
    private var items = ArrayList<E>(capacity)
    fun push(item: E) {
        if (items.size < capacity) {
            items.add(item)
        } else {
            println("Stack if full")
        }
    }

    fun pop(): E? {
        var popedItem: E? = null

        if (isEmpty()) {
            return popedItem
        } else {
            popedItem = items.last()
            items.removeLast()
        }
        println(popedItem)
        return popedItem
    }
    fun clear() {
        items.clear()
    }
    fun isEmpty() : Boolean = items.size == 0
}

