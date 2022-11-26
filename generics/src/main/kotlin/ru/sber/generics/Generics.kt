package ru.sber.generics

// 1.
fun compare(p1: Pair<Any, Any>, p2: Pair<Any, Any>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    return anArray.count { it.hashCode() > elem.hashCode() }
}

// 3.
class Sorter<T : Comparable<T>> {

    var list = mutableListOf<T>()

    fun add(value: T) {
        list = (this.list + mutableListOf(value)) as MutableList<T>
        list.sort()
    }
}

// 4.
class Stack<T> {

    private var stack = mutableListOf<T>()

    fun isEmpty(): Boolean{
        return stack.isEmpty()
    }

    fun push(value : T) {
        stack = (mutableListOf(value) + stack).toMutableList()
    }

    fun pop() : T? {

        if (!isEmpty()) {
            val result : T = stack[0]
            stack = stack.subList(1,stack.size)
            return result
        }
        return null
    }

}