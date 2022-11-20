package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>) = p1.first == p2.first && p1.second == p2.second


// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T) = anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) = list.apply { add(value) }.sort()

}

// 4.
class Stack<E> {
    private val stack: ArrayDeque<E> = ArrayDeque()

    fun isEmpty(): Boolean = stack.isEmpty()
    fun push(elem: E): E {
        stack.addFirst(elem)
        return elem
    }

    fun pop(): E = stack.removeFirst()
}