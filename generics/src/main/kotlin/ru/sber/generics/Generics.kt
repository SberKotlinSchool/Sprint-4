package ru.sber.generics

fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
//    return (p1 == p2)
    return ((p1.first == p2.first) && (p1.second == p2.second))
}

fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filterIndexed { _, it -> it > elem }.size
}

class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
            .also { list.sort() }
    }
}

class Stack<T : Comparable<T>> {

    val list: MutableList<T> = mutableListOf()
    fun push(value: T) = list.add(value)

    fun pop(): T = list.removeLast()

    fun isEmpty(): Boolean = list.isEmpty()

}