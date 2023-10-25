package ru.sber.generics

// 1.
fun compare(p1: Pair<Any, Any>, p2: Pair<Any, Any>): Boolean {
    return p1 == p2
}

// 2.
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    return anArray.count { it.isGreater(elem) }
}

fun Any.isGreater(another: Any): Boolean {
    if (this is Comparable<*> && another is Comparable<*> && this::class == another::class) {
        return compareValues(this, another) > 0
    }
    return false
}

// 3.
class Sorter<T : Comparable<*>> {
    val list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)

        list.sortWith { a, b ->
            compareValues(a, b)
        }

    }
}

// 4.
class Stack<T> {
    val list = mutableListOf<T>()

    fun isEmpty() = list.isEmpty()

    fun push(elem: T) {
        list.add(elem)
    }

    fun pop(): T {
        return list.removeAt(list.size - 1)
    }

}