package ru.sber.generics

// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>) =
    p1.first == p2.first && p1.second == p2.second

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T) =
    anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.apply {
            add(value)
            sort()
        }
    }
}

// 4.
class Stack<E> {
    private val stack = mutableListOf<E>()

    fun push(elem: E) = stack.add(elem)

    fun pop(): E? =
        if (stack.isEmpty()) null
        else stack.removeAt(stack.size - 1)

    fun isEmpty() = stack.size == 0
}