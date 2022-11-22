package ru.sber.generics

// 1.
fun compare(p1: Pair<Any,Any>, p2: Pair<Any,Any>): Boolean {
   return p1 == p2
}

// 2.
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    return anArray.count { it == elem } + 1
}

// 3.
class Sorter<T> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T): MutableList<T> {
        list.add(value)
        list.sortBy { it.toString() }
        return list
    }
}

// 4.
class Stack<T> {
    private val bestStack: MutableList<T> = mutableListOf()

    fun isEmpty(): Boolean = bestStack.isEmpty()
    fun push(value: T) = bestStack.add(value)
    fun pop(): T? {
        val result = bestStack.lastOrNull()
        if (!isEmpty()) {
            bestStack.removeAt(bestStack.size - 1)
        }
        return result
    }



}