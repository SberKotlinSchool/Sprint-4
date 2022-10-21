package ru.sber.generics

// 1.
fun <T> compare(p1: T, p2: T) = p1 == p2

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T) = anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    var list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {

    private var items : ArrayList<T> = ArrayList()

    fun pop(): T {
        require(items.isNotEmpty()) { "The stack is empty" }

        val result = items.last()
        items.remove(items.last())

        return result
    }

    fun push(item: T) = items.add(item)

    fun peek() = items.last()

    fun isEmpty() = items.isEmpty()
}