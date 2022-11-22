package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
}

// 3.
class Sorter<T: Comparable<T>>{
    val list: ArrayList<T> = arrayListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private val storage: ArrayList<T> = arrayListOf()

    fun pop(): T? {
        val size = storage.size
        if (size > 0) {
            val ret = storage[size - 1]
            storage.removeAt(size - 1)
            return ret
        }
        return null
    }

    fun push(elem: T) {
        storage.add(elem)
    }

    fun isEmpty(): Boolean {
        return storage.isEmpty()
    }

}