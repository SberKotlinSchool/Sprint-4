package ru.sber.generics

// 1.
fun <T, U> compare(p1: Pair<T, U>, p2: Pair<T, U>): Boolean =
        p1.first?.equals(p2.first) == true && p1.second?.equals(p2.second) == true

// 2.
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int =
        anArray.filter {
             if (it is Comparable<*> && it::class == elem::class)
                 it as Comparable<Any> > elem
              else false
        }.size

// 3.
class Sorter<T: Comparable<T>> {

    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val stack = mutableListOf<T>()

    private val size
        get() = stack.size

    fun push(value: T) = stack.add(value)

    fun pop(): T = stack.removeAt(size-1)

    fun isEmpty() = stack.isEmpty()
}