package ru.sber.generics


fun <T0, T1> compare(p1: Pair<T0, T1>, p2: Pair<T0, T1>): Boolean =
    (p1.first?.equals(p2.first) == true && p1.second?.equals(p2.second) == true)


fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int =
    anArray.filter { it > elem }.count()

class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

class Stack<T : Any> {
    private val storage = arrayListOf<T>()

    fun push(element: T) {
        storage.add(element)
    }

    fun pop(): T? {
        if (storage.size == 0) {
            return null
        }
        return storage.removeAt(storage.size - 1)
    }

    fun isEmpty(): Boolean {
        return (storage.size == 0)
    }

}