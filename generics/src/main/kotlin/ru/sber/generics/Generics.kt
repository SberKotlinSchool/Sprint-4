package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return (p1.first?.equals(p2.first) == true) && (p1.second?.equals(p2.second) == true)
}

// 2.1
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    var result = 0
    for (current in anArray) {
        if (current is Comparable<*> && elem::class == current::class) {
            current as Comparable<Any>
            if (current > elem) result++
        }
    }
    return result
}
// 2.2 сигнатура не из задания
fun <T : Comparable<T>> countGreaterThan_2(anArray: Array<T>, elem: T): Int {
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

// 4. LIFO
class Stack<T> {
    val list: MutableList<T> = mutableListOf()

    fun push(value: T) {
        list.add(value)
    }

    fun pop(): T {
        val lastOne = list.size - 1
        val elem = list.get(lastOne)
        list.removeAt(lastOne)
        return elem
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}