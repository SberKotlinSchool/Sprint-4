package ru.sber.generics


// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1 == p2
}

// 2.
fun <C> countGreaterThan(anArray: Array<out Comparable<C>>, elem: C): Int {
    return anArray.count {it > elem}
}

// 3.
class Sorter<D: Comparable<D>> {
    val list: MutableList<D> = mutableListOf()

    fun add(value: D) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<E> {
    private val list = mutableListOf<E>()

    fun push(element: E){
        list.add(element)
    }
    fun pop(): E {
        return list.removeLast()
    }
    fun isEmpty() = list.isEmpty()
}