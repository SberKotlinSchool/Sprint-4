package ru.sber.generics

// 1.
fun <K : Comparable<K>, V : Comparable<V>> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    val keys = p1.first.compareTo(p2.first)
    val values = p1.second.compareTo(p2.second)
    if (keys != 0) return if (keys > 0) return true else false
    if (values != 0) return if (values > 0) return true else false
    return true
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T) = anArray.filter { it.compareTo(elem) > 0 }.count()

// 3.
class Sorter<T : Comparable<T>> {
    val list = mutableListOf<T>()


    fun add(value: T) =
        if (list.size == 0) list.add(value)
        else for (i in 0 until list.size) if (list.get(i).compareTo(value) >= 0 && i <= list.size) {
            list.add(i, value)
            break
        } else if (i + 1 == list.size) {
            list.add(value)
        }
}

// 4.
class Stack<T> {

    private var elements = mutableListOf<T>()

    fun push(element: T) {
        elements.add(element)
    }

    fun pop(): T? {
        val element = elements.getOrNull(elements.lastIndex)
        elements.removeLast()
        return element
    }

    fun isEmpty() = elements.isEmpty()
}