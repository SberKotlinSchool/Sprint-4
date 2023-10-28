package ru.sber.generics

fun <T, S> compare(p1: Pair<T, S>, p2: Pair<T, S>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter { it > elem }.size
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

class Stack<T> {

    private val stack = mutableListOf<T>()

    fun push(element: T): Boolean = stack.add(element)

    fun pop(): T? = stack.ifEmpty { null }?.removeLast()

    fun isEmpty(): Boolean = stack.isEmpty()

}