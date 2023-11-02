package ru.sber.generics


// 1.
fun <T, S> compare(p1: Pair<T, S>, p2: Pair<T, S>): Boolean {
    return p1 == p2
}
// 2.
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
// 4.
class Stack<T> {
    private val stack = mutableListOf<T>()

    fun pop(): T? = stack.ifEmpty { null }?.removeLast()

    fun push(element: T): Boolean = stack.add(element)

    fun isEmpty(): Boolean = stack.isEmpty()
}